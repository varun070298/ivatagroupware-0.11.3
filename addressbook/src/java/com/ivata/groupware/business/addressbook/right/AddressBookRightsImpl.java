/*
 * Copyright (c) 2001 - 2005 ivata limited.
 * All rights reserved.
 * -----------------------------------------------------------------------------
 * ivata groupware may be redistributed under the GNU General Public
 * License as published by the Free Software Foundation;
 * version 2 of the License.
 *
 * These programs are free software; you can redistribute them and/or
 * modify them under the terms of the GNU General Public License
 * as published by the Free Software Foundation; version 2 of the License.
 *
 * These programs are distributed in the hope that they will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License in the file LICENSE.txt for more
 * details.
 *
 * If you would like a copy of the GNU General Public License write to
 *
 * Free Software Foundation, Inc.
 * 59 Temple Place - Suite 330
 * Boston, MA 02111-1307, USA.
 *
 *
 * To arrange commercial support and licensing, contact ivata at
 *                  http://www.ivata.com/contact.jsp
 * -----------------------------------------------------------------------------
 * $Log: AddressBookRightsImpl.java,v $
 * Revision 1.5  2005/04/29 02:48:13  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.4  2005/04/28 18:47:10  colinmacleod
 * Fixed XHMTL, styles and resin compatibility.
 * Added support for URL rewriting.
 *
 * Revision 1.3  2005/04/10 20:09:38  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:08  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:22  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/11/12 18:17:10  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 15:57:06  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/11/03 15:34:42  colinmacleod
 * Changed todo comments to all caps.
 *
 * Revision 1.1  2004/07/13 19:41:14  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.right;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.BusinessLogic;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.addressbook.person.employee.EmployeeDO;
import com.ivata.groupware.business.addressbook.person.group.GroupConstants;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.groupware.business.addressbook.person.group.right.RightConstants;
import com.ivata.groupware.business.addressbook.person.group.right.RightDO;
import com.ivata.groupware.business.addressbook.person.group.right.detail.RightDetailDO;
import com.ivata.groupware.container.persistence.QueryPersistenceManager;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.util.SystemException;


/**
 * <p>Address book rights determine what each user can and cannot do within the
 * address book subsystem.</p>
 *
 * @since 2002-07-22
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.5 $
 */
public class AddressBookRightsImpl
        extends BusinessLogic implements AddressBookRights {
    /**
     * Persistence manger used to store/retrieve data objects.
     */
    private QueryPersistenceManager persistenceManager;

    /**
     * Construct a new address book rights instance.
     *
     * @param persistenceManager used to store objects in db.
     */
    public AddressBookRightsImpl(QueryPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    /**
     * <p>Change user rights for group.</p>
     *
     * @param id of group
     * @param rights collection of group ids which will have ACCESS right to that group
     * @param set to one of the <code>ACCESS_...</code> constants in <code>RightConstants</code>.
     */
    public void amendRightsForGroup(final SecuritySession securitySession,
            final GroupDO group,
            final Collection rights,
            final Integer access)
            throws SystemException {
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);

        try {
            // find detail for group rights
            RightDetailDO rightDetail =
                (RightDetailDO)
                persistenceManager.findByPrimaryKey(persistenceSession,
                    RightDetailDO.class,
                    RightConstants.DETAIL_PERSON_GROUP_MEMBER);

            // I am only working with groupIds which user can view.
            Collection groupIdsCanViewByUser =
                persistenceManager.find(persistenceSession,
                    "rightByUserNameAccessDetail",
                    new Object [] {
                        securitySession.getUser().getName(),
                        RightConstants.ACCESS_VIEW,
                        RightConstants.DETAIL_PERSON_GROUP_MEMBER
                    });
            // find rights for GROUP with access level specified
            List tmp =
                persistenceManager.find(persistenceSession,
                    "rightByUserNameAccessDetailTargetId",
                    new Object [] {
                        securitySession.getUser().getName(),
                        access,
                        RightConstants.DETAIL_PERSON_GROUP_MEMBER,
                        group.getId()
                    });

            // go thro' and remove the ones which are there already
            for (int i = 0; i < tmp.size(); i++) {
                RightDO right = (RightDO) tmp.get(i);
                Integer groupId = right.getGroup().getId();

                if (groupIdsCanViewByUser.contains(groupId)) {
                    if (rights.contains(groupId)) {
                        rights.remove(groupId);
                    } else if ( !GroupConstants.equals(groupId, 
                            GroupConstants.GROUP_ADMINISTRATOR) ) {
                        persistenceManager.remove(persistenceSession, right);
                    }
                }
            }

            // if you have right to add, amend or remove, so you have right to view
            // -> check if you have view right, if not -> create
            Collection viewRights = null;
            if (!RightConstants.ACCESS_VIEW.equals(access)) {
                viewRights =
                    persistenceManager.find(persistenceSession,
                        "rightByAccessDetailTargetId",
                        new Object [] {
                            RightConstants.ACCESS_VIEW,
                            RightConstants.DETAIL_PERSON_GROUP_MEMBER,
                            group.getId()
                        });
            }

            for (Iterator i = rights.iterator(); i.hasNext();) {
                Integer groupId = (Integer) i.next();
                GroupDO thisGroup = (GroupDO)
                    persistenceManager.findByPrimaryKey(persistenceSession,
                        GroupDO.class,
                        groupId);

                RightDO right = new RightDO();
                right.setAccess(access);
                right.setDetail(rightDetail);
                right.setGroup(thisGroup);
                right.setTargetId(group.getId());
                persistenceManager.add(persistenceSession, right);

                // if any of the other rights apply, the view right automatically
                // does too
                if (!RightConstants.ACCESS_VIEW.equals(access)
                        && !viewRights.contains(groupId)) {
                    RightDO viewRight = new RightDO();
                    viewRight.setAccess(RightConstants.ACCESS_VIEW);
                    viewRight.setDetail(rightDetail);
                    viewRight.setGroup(thisGroup);
                    viewRight.setTargetId(group.getId());
                    persistenceManager.add(persistenceSession, viewRight);
                }
            }
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }

    /**
     * <p>TODO: add a comment here.</p>
     */
    public boolean canAddEmployeeToPerson(final SecuritySession securitySession,
            final PersonDO person)
            throws SystemException {
        return true;
    }

    /**
     * <p>Find out if a used is allowed to add entries to a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @return <code>true</code> if the user is entitled to add to the group,
     *     otherwise <code>false</code>.
     */
    public boolean canAddToGroup(final SecuritySession securitySession,
            final GroupDO group)
            throws SystemException {
        if (didUserCreateGroup(securitySession, group)) {
            return true;
        } else {
            return canUser(securitySession, group, RightConstants.ACCESS_ADD);
        }
    }

    /**
     * <p>TODO: add a comment here.</p>
     */
    public boolean canAmendEmployee(final SecuritySession securitySession,
            final EmployeeDO employeeDO)
            throws SystemException {
        // TODO:
        return true;
    }

    /**
     * <p>Find out if a used is allowed to amend entries in a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @return <code>true</code> if the user is entitled to amend entries in the
     *      group, otherwise <code>false</code>.
     */
    public boolean canAmendInGroup(final SecuritySession securitySession,
            final GroupDO group)
            throws SystemException {
        if (didUserCreateGroup(securitySession, group)) {
            return true;
        } else {
            return canUser(securitySession, group, RightConstants.ACCESS_AMEND);
        }
    }

    /**
     * <p>TODO: add a comment here.</p>
     */
    public boolean canRemoveEmployee(final SecuritySession securitySession,
            final EmployeeDO employeeDO)
            throws SystemException {
        // TODO:
        return true;
    }

    /**
     * <p>Find out if a used is allowed to remove entries from a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @return <code>true</code> if the user is entitled to remove from the
     *     group, otherwise <code>false</code>.
     */
    public boolean canRemoveFromGroup(final SecuritySession securitySession,
            final GroupDO group)
            throws SystemException {
        if (didUserCreateGroup(securitySession, group)) {
            return true;
        } else {
            return canUser(securitySession, group, RightConstants.ACCESS_REMOVE);
        }
    }

    /**
     * <p>Internal helper method. Find out if a user is allowed to access
     * entries in a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @param access the access level as defined in {@link
     *      com.ivata.groupware.business.addressbook.person.group.right.RightConstants
     *      RightConstants}.
     * @return <code>true</code> if the user is entitled to access entries in the
     *      group, otherwise <code>false</code>.
     */
    public boolean canUser(final SecuritySession securitySession,
            final GroupDO group,
            final Integer access)
            throws SystemException {
        // for now, everyone else can do pretty much everything :-)
        // only private address books are sacred
        GroupDO addressBook = group.getAddressBook();
        // is this a parent address book?  if so, only return true if this
        // is the address book of the current user
        if (GroupConstants.equals(addressBook.getParent().getId(),
                GroupConstants.ADDRESS_BOOK_PRIVATE)) {
            return addressBook.getName().equals(securitySession.getUser()
                    .getName());
        }
        return true;
    }

    /**
     * <p>Did user created this group?</p>
     * @param userName
     * @param groupId
     * @return
     */
    private boolean didUserCreateGroup(final SecuritySession securitySession,
            final GroupDO group)
            throws SystemException {
        return securitySession.getUser().equals(group.getCreatedBy());
    }

    /**
     * <p>Find the unique identifiers of all addressBooks which can be accessed by the
     * group specified, with the access level given.</p>
     *
     * @param groupId unique identifier of the group for which to search for
     *    other groups.
     * @param access the access level as defined in {@link
     *      com.ivata.groupware.business.addressbook.person.group.right.RightConstants
     *      RightConstants}.
     * @return a <code>Collection</code> of <code>Integer</code> instances,
     *      matching all groups which can be access with this level of access
     *      by the group specified.
     */
    public Collection findAddressBooksByGroupAccess(final SecuritySession securitySession,
            final GroupDO group,
            final Integer access)
            throws SystemException {
        Vector groups = new Vector();
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);

        try {
            // find all the ids, but take only public addressBooks
            Collection groupIds =
                persistenceManager.find(persistenceSession,
                    "rightByGroupIdAccessDetail",
                    new Object [] {
                        group.getId(),
                        RightConstants.ACCESS_VIEW,
                        RightConstants.DETAIL_PERSON_GROUP_MEMBER
                    });
            for (Iterator i = groupIds.iterator(); i.hasNext(); ) {
                RightDO right = (RightDO) i.next();

                GroupDO tmpGroup = (GroupDO)
                    persistenceManager.findByPrimaryKey(persistenceSession,
                        GroupDO.class,
                        right.getTargetId());
                if (GroupConstants.equals(tmpGroup.getParent().getId(),
                        GroupConstants.ADDRESS_BOOK_PUBLIC)) {
                    groups.add(tmpGroup);
                }
            }
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }

        return groups;
    }

    /**
     * <p>Find groups which have <code>access</code> to group.
     * Return only those groups which can be see by that user.</p>
     *
     * @param userName user which is trying find rights
     * @param id of group which we are interesting
     * @param access find rights with this access
     * @return Collection of IDS of groups which have <code>access</code> to that group
     */
    public Collection findRightsForGroup(final SecuritySession securitySession,
            final GroupDO group,
            final Integer access)
            throws SystemException {
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);

        try {
            // I will send only groupIds which user can view, no more.
            Collection groupIdsCanViewByUser =
                persistenceManager.find(persistenceSession,
                    "rightByUserNameAccessDetail",
                    new Object [] {
                        securitySession.getUser().getName(),
                        RightConstants.ACCESS_VIEW,
                        RightConstants.DETAIL_PERSON_GROUP_MEMBER
                    });

            // find rights for GROUP
            List tmp =
                persistenceManager.find(persistenceSession,
                    "rightByUserNameAccessDetailTargetId",
                    new Object [] {
                        securitySession.getUser().getName(),
                        access,
                        RightConstants.DETAIL_PERSON_GROUP_MEMBER,
                        group.getId()
                    });

            List rights = new Vector();
            for (int i = 0; i < tmp.size(); i++) {
                RightDO right = (RightDO) tmp.get(i);
                Integer groupId = right.getGroup().getId();

                if (groupIdsCanViewByUser.contains(groupId) &&
                        !GroupConstants.equals(groupId,
                                GroupConstants.GROUP_ADMINISTRATOR)) {
                    rights.add(groupId);
                }
            }
            return rights;
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }
}

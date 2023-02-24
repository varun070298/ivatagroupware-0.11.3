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
 * $Log: GroupDO.java,v $
 * Revision 1.4  2005/04/28 18:47:10  colinmacleod
 * Fixed XHMTL, styles and resin compatibility.
 * Added support for URL rewriting.
 *
 * Revision 1.3  2005/04/10 20:09:36  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:07  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:31  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.6  2004/12/31 18:22:13  colinmacleod
 * Added override for displayValue.
 *
 * Revision 1.5  2004/11/12 15:57:05  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.4  2004/07/13 19:41:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:06  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:51  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.3  2003/07/29 15:45:55  jano
 * we have new field in groupDO
 *
 * Revision 1.2  2003/06/02 22:04:53  colin
 * added comparison method
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.7  2003/02/04 17:43:45  colin
 * copyright notice
 *
 * Revision 1.6  2003/01/10 10:29:23  jano
 * we need information about user who created group
 *
 * Revision 1.5  2003/01/09 10:01:52  jano
 * we are not storing group's rights in geoupDO now
 * we are using methods in addressBookRightsBean
 *
 * Revision 1.4  2002/12/12 15:05:55  jano
 * rights for groups
 *
 * Revision 1.3  2002/06/21 12:48:58  colin
 * restructured com.ivata.groupware.web
 *
 * Revision 1.2  2002/06/17 07:28:54  colin
 * improved and extended javadoc documentation
 *
 * Revision 1.1  2002/06/13 11:21:24  colin
 * first version with rose model integration.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.person.group;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.container.persistence.NamedDO;
import com.ivata.groupware.web.tree.TreeNode;

/**
 * <p>Represents a group of people. This can be a company, a department or
 * a team within the addressbook. On the basis of these groupings, access
 * rights are assigned throughout the system.</p>
 *
 * @since 2002-05-15
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 *
 * @hibernate.class
 *      table="person_group"
 * @hibernate.cache
 *      usage="read-write"
 */
public class GroupDO extends NamedDO implements TreeNode {
    /**
     * Refer to {@link Logger}.
     */
    private static Logger logger = Logger.getLogger(GroupDO.class);
    /**
     * <p>Store a clear-text description of the function and purpose of this
     * group.</p>
     */
    private String description;

    /**
     * <p>Store the the person who is head of this group. In the case
     * where the group represents a company department, this should be the
     * manager of that department.</p>
     */
    private PersonDO head;

    /**
     * <p>Store the name of the group. This name should uniquely identify the
     * group in clear text.</p>
     */
    private String name;
    /**
     * <p>Parent group - group which contains this group.</p>
     */
    private GroupDO parent;

    /**
     * <p>All the people in this group.</p>
     */
    private Set people = new HashSet();

    /**
     * <p>All the users in this group.</p>
     */
    private Set users = new HashSet();

    /**
     * <p>
     * Address book group - used to reference the address book this group is in.
     * </p>
     *
     * @return Address book group - used to reference the address book this
     * group is in.
     */
    public final GroupDO getAddressBook() {
        // check this is not an address book itself
        assert (parent != null);
        if (GroupConstants.equals(getId(), GroupConstants.ADDRESS_BOOK)
                || GroupConstants.equals(GroupConstants.ADDRESS_BOOK,
                        parent.getId())) {
            return this;
        }
        GroupDO addressBook = this;
        assert (addressBook.parent.parent != null);
        
        // go thro' all parents till we find the address book.
        while (!GroupConstants.equals(
                        addressBook.parent.parent.getId(),
                        GroupConstants.ADDRESS_BOOK)) {
            addressBook = addressBook.parent;
            // if you get this, it is because the group tree has become unstable
            // : this group has no parent which is an address book anywhere up
            // the tree.
            if (addressBook.parent.parent == null) {
                throw new NullPointerException("ERROR in GroupDO: the group "
                        + "hierarchy is invalid: group '"
                        + name
                        + "' ("
                        + getId()
                        + ") has no address book as a parent.");
            }
        }
        return addressBook;
    }

    /**
     * <p>Get  a clear-text description of the function and purpose of this
     * group.</p>
     *
     * @return  a clear-text description of the function and purpose of this
     *     group.
     * @hibernate.property
     */
    public final String getDescription() {
        return description;
    }

    /**
     * <p>
     * For a group, the value displayed in a choice box is just the name,
     * </p>
     *
     * @see com.ivata.mask.valueobject.ValueObject#getDisplayValue()
     */
    public final String getDisplayValue() {
        return name;
    }

    /**
     * <p>Get the the person who is head of this group. In the case where
     * the group represents a company department, this should be the manager of
     * that department.</p>
     *
     * @return the the person who is head of this group.
     * @hibernate.many-to-one
     */
    public PersonDO getHead() {
        return head;
    }
    /**
     * <p>Get the name of the group. This name should uniquely identify the
     * group within its place in the heirarchy in clear text.</p>
     *
     * @return the name of this group, a clear-text string which identifies the
     *     group uniquely within its place in the group heirarchy.
     * @hibernate.property
     *      column="name"
     */
    public final String getName() {
        return name;
    }

    /**
     * <p>
     * Get the parent group of this group - the group which contains this one.
     * </p>
     *
     * @return the parent group of this group.
     * @hibernate.many-to-one
     *      column="parent"
     */
    public final GroupDO getParent () {
        return parent;
    }

    /**
     * <p>All the people in this group.</p>
     *
     * @return the people in this group.
     *
     * @hibernate.set
     * @hibernate.collection-key
     *      column="person_group"
     * @hibernate.collection-one-to-many
     *      cascade="all"
     *      class="com.ivata.groupware.business.addressbook.person.PersonDO"
     */
    public final Set getPeople() {
        return people;
    }

    /**
     * <p>All the users in this group.</p>
     *
     * @return the users in this group.
     *
     * @hibernate.set
     *      role="person_user"
     *      table="person_group_member"
     * @hibernate.collection-key
     *      column="person_group"
     * @hibernate.collection-many-to-many
     *      class="com.ivata.groupware.admin.security.user.UserDO"
     *      column="person_user"
     */
    public final Set getUsers() {
        return users;
    }

    /**
     * <p>Set a clear-text description of the function and purpose of this
     * group.</p>
     *
     * @param description  a clear-text description of the function and purpose
     *     of this group.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }
    /**
     * <p>Set the person who is head of this group. In the case where
     * the group represents a company department, this should be the manager of
     * that department.</p>
     *
     * @param headParam the person who is head of this group.
     */
    public void setHead(PersonDO headParam) {
        if (logger.isDebugEnabled()) {
            logger.debug("setHead before: '" + head + "', after: '"
                    + headParam + "'");
        }

        head = headParam;
    }
    /**
     * <p>Set the name of the group. This name should uniquely identify the
     * group in clear text.</p>
     *
     * @param nameParam the new name to set for this group.
     */
    public void setName(String nameParam) {
        if (logger.isDebugEnabled()) {
            logger.debug("setName before: '" + name + "', after: '"
                    + nameParam + "'");
        }

        name = nameParam;
    }
    /**
     * <p>Set the parent of this group.</p>
     *
     * @param parentParam the parent of this group.
     */
    public void setParent(GroupDO parentParam) {
        if (logger.isDebugEnabled()) {
            logger.debug("setParent before: '" + parent
                    + "', after: '" + parentParam + "'");
        }

        parent = parentParam;
    }
    /**
     * <p>All the people in this group.</p>
     *
     * @param peopleParam the people in this group.
     */
    public void setPeople(Set peopleParam) {
        if (logger.isDebugEnabled()) {
            logger.debug("setPeople before: '" + people
                    + "', after: '" + peopleParam + "'");
        }

        people = peopleParam;
    }
    /**
     * <p>All the users in this group.</p>
     *
     * @param usersParam the users in this group.
     */
    public void setUsers(Set usersParam) {
        if (logger.isDebugEnabled()) {
            logger.debug("setUsers before: '" + users
                    + "', after: '" + usersParam+ "'");
        }

        users = usersParam;
    }
}

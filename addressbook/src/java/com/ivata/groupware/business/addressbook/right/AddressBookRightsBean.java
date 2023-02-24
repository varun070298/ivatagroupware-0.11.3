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
 * $Log: AddressBookRightsBean.java,v $
 * Revision 1.3  2005/04/10 20:09:37  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:08  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:21  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.7  2004/11/12 18:17:10  colinmacleod
 * Ordered imports.
 *
 * Revision 1.6  2004/11/12 15:57:06  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.5  2004/11/03 15:34:42  colinmacleod
 * Changed todo comments to all caps.
 *
 * Revision 1.4  2004/07/13 19:41:14  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/02/01 22:00:33  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:52  colinmacleod
 * Moved open portal to sourceforge.
 *
 * Revision 1.5  2003/11/03 11:28:24  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.3  2003/10/15 13:47:36  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.6  2003/08/19 14:53:30  jano
 * *** empty log message ***
 *
 * Revision 1.5  2003/08/13 13:33:34  jano
 * addressBook exttension: next level
 *
 * Revision 1.4  2003/08/05 14:57:35  jano
 * addressBook extension
 *
 * Revision 1.3  2003/05/27 14:08:41  colin
 * added amendRightsForGroup
 *
 * Revision 1.2  2003/05/12 13:46:16  colin
 * added new methods for finding rights
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.9  2003/02/04 17:43:45  colin
 * copyright notice
 *
 * Revision 1.8  2003/01/09 10:51:25  jano
 * I need only one method for finding right for group
 *
 * Revision 1.7  2003/01/08 17:15:12  jano
 * We will use new methods for finding and changing rights for GROUP
 *
 * Revision 1.6  2003/01/03 17:16:56  jano
 * fixing findr problem in canUser
 *
 * Revision 1.5  2003/01/02 16:41:00  jano
 * taking VIEW of
 *
 * Revision 1.4  2002/12/12 15:03:41  jano
 * rights for groups
 *
 * Revision 1.3  2002/09/09 08:38:31  colin
 * moved user handling to new security bean
 *
 * Revision 1.2  2002/08/30 09:49:45  colin
 * changed canUser... methods to just can...
 *
 * Revision 1.1  2002/07/26 13:06:56  colin
 * first compiling version of address book rights
 * mail server methods currently all return false
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.right;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.addressbook.person.employee.EmployeeDO;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.SystemException;


/**
 * <p>Address book rights determine what each user can and cannot do within the
 * address book subsystem.</p>
 *
 * @since 2002-07-22
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @ejb.bean
 *      name="AddressBookRights"
 *      display-name="AddressBookRights"
 *      type="Stateless"
 *      view-type="both"
 *      local-jndi-name="AddressBookRightsLocal"
 *      jndi-name="AddressBookRightsRemote"
 *
 * @ejb.transaction
 *      type = "Required"
 *
 *  @ejb.home
 *      generate="false"
 *      remote-class="com.ivata.groupware.business.addressbook.right.AddressBookRightsRemoteHome"
 *
 *  @ejb.interface
 *      remote-class="com.ivata.groupware.business.addressbook.right.AddressBookRightsRemote"
 */
public class AddressBookRightsBean implements SessionBean {


    /**
     * <p>Provides the session bean with container-specific information.</p>
     */
    SessionContext sessionContext;

    /**
     * <p>Change user rights for group.</p>
     *
     * @param id of group
     * @param userName user which trying to change rights
     * @param rights collection of group ids which will have ACCESS right to that group
     * @param set to one of the <code>ACCESS_...</code> constants in <code>RightConstants</code>.
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public void amendRightsForGroup(final SecuritySession securitySession,
            final GroupDO group,
            final Collection rights,
            final Integer access) throws SystemException {
        getAddressBookRights().amendRightsForGroup(securitySession, group, rights, access);
    }

    /**
     * <p>TODO: add a comment here.</p>
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canAddEmployeeToPerson(final SecuritySession securitySession,
            final PersonDO person) throws SystemException {
        return getAddressBookRights().canAddEmployeeToPerson(securitySession, person);
    }

    /**
     * <p>Find out if a used is allowed to add entries to a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @return <code>true</code> if the user is entitled to add to the group,
     *     otherwise <code>false</code>.
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canAddToGroup(final SecuritySession securitySession,
            final GroupDO group) throws SystemException {
        return getAddressBookRights().canAddToGroup(securitySession, group);
    }

    /**
     * <p>TODO: add a comment here.</p>
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canAmendEmployee(final SecuritySession securitySession,
            final EmployeeDO employeeDO) throws SystemException {
        return getAddressBookRights().canAmendEmployee(securitySession, employeeDO);
    }

    /**
     * <p>Find out if a used is allowed to amend entries in a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @return <code>true</code> if the user is entitled to amend entries in the
     *      group, otherwise <code>false</code>.
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canAmendInGroup(final SecuritySession securitySession,
            final GroupDO group) throws SystemException {
        return getAddressBookRights().canAmendInGroup(securitySession, group);
    }

    /**
     * <p>TODO: add a comment here.</p>
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canRemoveEmployee(final SecuritySession securitySession,
            final EmployeeDO employeeDO) throws SystemException {
        return getAddressBookRights().canRemoveEmployee(securitySession, employeeDO);
    }

    /**
     * <p>Find out if a used is allowed to remove entries from a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @return <code>true</code> if the user is entitled to remove from the
     *     group, otherwise <code>false</code>.
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canRemoveFromGroup(final SecuritySession securitySession,
            final GroupDO group) throws SystemException {
        return getAddressBookRights().canRemoveFromGroup(securitySession, group);
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
            final Integer access) throws SystemException {
        return getAddressBookRights().canUser(securitySession, group, access);
    }

    /**
     * <p>Called by the container to notify an entity object it has been
     * activated.</p>
     */
    public void ejbActivate() {}

    /**
     * <p>Called by the container just after the bean has been created.</p>
     *
     * @exception CreateException if any error occurs. Never thrown by this
     *     class.
     *
     * @ejb.create-method
     */
    public void ejbCreate() throws CreateException {}

    /**
     * <p>Called by the container to notify the entity object it will be
     * deactivated. Called just before deactivation.</p>
     */
    public void ejbPassivate() {}

    /**
     * <p>This method is called by the container when the bean is about
     * to be removed.</p>
     *
     * <p>This method will be called after a client calls the <code>remove</code>
     * method of the remote/local home interface.</p>
     *
     * @throws RemoveException if any error occurs. Currently never thrown by
     *     this class.
     */
    public void ejbRemove() {}
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
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public Collection findAddressBooksByGroupAccess(final SecuritySession securitySession,
            final GroupDO group,
            final Integer access) throws SystemException {
        return getAddressBookRights().findAddressBooksByGroupAccess(securitySession, group, access);
    }

    /**
     * <p>Find groups which have <code>access</code> to group.
     * Return only those groups which can be see by that user.</p>
     *
     * @param userName user which is trying find rights
     * @param id of group which we are interesting
     * @param access find rights with this access
     * @return Collection of IDS of groups which have <code>access</code> to that group
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public Collection findRightsForGroup(final SecuritySession securitySession,
            final GroupDO group,
            final Integer access) throws SystemException {
        return getAddressBookRights().findRightsForGroup(securitySession, group, access);
    }
    /**
     * Get the addressbook implementation from the <code>PicoContainer</code>.
     */
    private AddressBookRights getAddressBookRights()
            throws SystemException {
        PicoContainer container = PicoContainerFactory.getInstance()
            .getGlobalContainer();
        return (AddressBookRights) container.getComponentInstance(AddressBookRights.class);
    }

    /**
     * <p>Set up the context for this entity object. The session bean stores the
     * context for later use.</p>
     *
     * @param sessionContext the new context which the session object should
     *     store.
     */
    public final void setSessionContext(final SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}

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
 * $Log: AddressBookRights.java,v $
 * Revision 1.2  2005/04/09 17:19:08  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:21  colinmacleod
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

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.addressbook.person.employee.EmployeeDO;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.mask.util.SystemException;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Apr 18, 2004
 * @version $Revision: 1.2 $
 */
public interface AddressBookRights {
    /**
     * <p>Change user rights for group.</p>
     *
     * @param id of group
     * @param rights collection of group ids which will have ACCESS right to that group
     * @param set to one of the <code>ACCESS_...</code> constants in <code>RightConstants</code>.
     */
    public abstract void amendRightsForGroup(
        SecuritySession securitySession,
        GroupDO group,
        Collection rights,
        Integer access)
        throws SystemException;
    /**
     * <p>TODO: add a comment here.</p>
     */
    public abstract boolean canAddEmployeeToPerson(
        SecuritySession securitySession,
        PersonDO person)
        throws SystemException;
    /**
     * <p>Find out if a used is allowed to add entries to a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @return <code>true</code> if the user is entitled to add to the group,
     *     otherwise <code>false</code>.
     */
    public abstract boolean canAddToGroup(
        SecuritySession securitySession,
        GroupDO group)
        throws SystemException;
    /**
     * <p>TODO: add a comment here.</p>
     */
    public abstract boolean canAmendEmployee(
        SecuritySession securitySession,
        EmployeeDO employeeDO)
        throws SystemException;
    /**
     * <p>Find out if a used is allowed to amend entries in a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @return <code>true</code> if the user is entitled to amend entries in the
     *      group, otherwise <code>false</code>.
     */
    public abstract boolean canAmendInGroup(
        SecuritySession securitySession,
        GroupDO group)
        throws SystemException;
    /**
     * <p>TODO: add a comment here.</p>
     */
    public abstract boolean canRemoveEmployee(
        SecuritySession securitySession,
        EmployeeDO employeeDO)
        throws SystemException;
    /**
     * <p>Find out if a used is allowed to remove entries from a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @return <code>true</code> if the user is entitled to remove from the
     *     group, otherwise <code>false</code>.
     */
    public abstract boolean canRemoveFromGroup(
        SecuritySession securitySession,
        GroupDO group)
        throws SystemException;
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
    public abstract boolean canUser(
        SecuritySession securitySession,
        GroupDO group,
        Integer access)
        throws SystemException;
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
    public abstract Collection findAddressBooksByGroupAccess(
        SecuritySession securitySession,
        GroupDO group,
        Integer access)
        throws SystemException;
    /**
     * <p>Find groups which have <code>access</code> to group.
     * Return only those groups which can be see by that user.</p>
     *
     * @param userName user which is trying find rights
     * @param id of group which we are interesting
     * @param access find rights with this access
     * @return Collection of IDS of groups which have <code>access</code> to that group
     */
    public abstract Collection findRightsForGroup(
        SecuritySession securitySession,
        GroupDO group,
        Integer access)
        throws SystemException;
}
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
 * $Log: SecurityRights.java,v $
 * Revision 1.2  2005/04/09 17:19:04  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:44  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/12 15:56:45  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/07/13 19:41:11  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security.right;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.mask.util.SystemException;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Apr 18, 2004
 * @version $Revision: 1.2 $
 */
public interface SecurityRights {
    /**
     * <p>See if a user has sufficient rights to add user to the system - it's meen to everyOne group.</p>
     *
     * @param userName the user who wants to add another user.
     * @param personId the unique identifier of the person who will be added.
     * @return <code>true</code> if this action is authorized by the system,
     * otherwise <code>false</code>.
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canAddUser(SecuritySession securitySession)
        throws SystemException;
    /**
     * <p>See if a user has sufficient rights to amend user in the
     * system - it's meen in everyone group.</p>
     *
     * @param userName the user who wants to add another user.
     * @param userNameAmend the user who should be amended.
     * @return <code>true</code> if this action is authorized by the system,
     * otherwise <code>false</code>.
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canAmendUser(SecuritySession securitySession)
        throws SystemException;
    /**
     * <p>See if a user has sufficient rights to remove user from the
     * system - it's meen from everone group.</p>
     *
     * @param userName the user who wants to add another user.
     * @param userNameRemove the user who should be removed.
     * @return <code>true</code> if this action is authorized by the system,
     * otherwise <code>false</code>.
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canRemoveUser(SecuritySession securitySession)
        throws SystemException;
    /**
     * <p>Internal helper method. Find out if a user is allowed to access
     * entries in a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @param access the access level as defined in {@link
     *      com.ivata.groupware.security.person.group.right.RightConstants
     *      RightConstants}.
     * @return <code>true</code> if the user is entitled to access entries in the
     *      group, otherwise <code>false</code>.
     */
    public boolean canUser(
        SecuritySession securitySession,
        Integer access)
        throws SystemException;
}
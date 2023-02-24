/*
 * $Id: SecurityServer.java,v 1.2 2005/04/09 17:19:57 colinmacleod Exp $
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
 * $Log: SecurityServer.java,v $
 * Revision 1.2  2005/04/09 17:19:57  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:41  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/12 15:57:18  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/09/30 15:15:58  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.4  2004/07/13 19:41:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:05  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/03/21 20:16:57  colinmacleod
 * Changed from class singleton to factory interface.
 *
 * Revision 1.1.1.1  2004/01/27 20:57:45  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.4  2003/12/16 15:06:09  jano
 * fixing functionaality
 *
 * Revision 1.3  2003/11/13 16:03:15  jano
 * commitng everything to CVS
 * can deploy and application is ruuning, can login into
 *
 * Revision 1.2  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.1.1.1  2003/10/13 20:50:07  colin
 * Restructured portal into subprojects
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security.server;

import java.io.Serializable;

import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.mask.util.SystemException;

/**
 * You must define a class which implements this interface, and set it as
 * in the setting <code>securitySessionServer</code>.
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 */
public interface SecurityServer extends Serializable {
    /**
     * Add a new user to the system.
     *
     * @param userName user name to add.
     * @param fullName full name under which the user will be filed.
     * @throws BusinessException if this user cannot be added.
     */
    void addUser(final SecuritySession securitySession,
            final String userName,
            final String fullName)
        throws SystemException;

    /**
     * Check the password for a user is correct.
     *
     * @param userName name of the user for whom to check the password.
     * @param password the new password value to check against the system.
     * @throws BusinessException if the password cannot be checked for any
     *     reason.
     */
    void checkPassword(final SecuritySession securitySession,
            final String userName,
            final String password)
        throws SystemException;

    String getSystemUserName(final SecuritySession securitySession,
            final String userName);
    String getUserNameFromSystemUserName(final SecuritySession securitySession,
            final String systemUserName);

    /**
     * Find out if a user name is used or not.
     * @param user user to check
     * @throws SystemException if the user name cannot be checked for any
     * reason.
     */
    boolean isUser(final SecuritySession securitySession,
            final String userName)
            throws SystemException;

    /**
     * Login to an authentication server using the user name and password
     * provided.
     *
     * @param user user to login to the server.
     * @param password used to login to the server
     * @return valid session for this username password combination.
     * @throws BusinessException if this user cannot be authenticated.
     */
    SecuritySession login(final UserDO user,
            final String password)
        throws SystemException;

    /**
     * Login as a guest user to an authentication server.
     *
     * @return valid session for the guest user.
     * @throws BusinessException if this user cannot be authenticated.
     */
    SecuritySession loginGuest()
        throws SystemException;

    /**
     * <p>Remove the user with the given name from the system.</p>
     *
     * @param userName name of the user to be removed.
     * @throws BusinessException if this user cannot be removed.
     */
    void removeUser(final SecuritySession securitySession,
            final String userName)
        throws SystemException;

    /**
     * Set the password for a user.
     *
     * @param userName name of the user for whom to set the password.
     * @param password the new password value to set.
     * @throws BusinessException if the password cannot be set for any
     *     reason.
     */
    void setPassword(final SecuritySession securitySession,
            final String userName,
            final String password)
        throws SystemException;

}

/*
 * Copyright (c) 2001 - 2005 ivata limited.
 * All rights reserved.
 * -----------------------------------------------------------------------------
 * ivata groupware may be redistributed under the GNU General Public
 * License as published by the Free Software Foundation;
 * version 2 of the License.
 *
 * These programs are free software; you can redistribute them and/or
 * modify them under the terms of the GNU General License
 * as published by the Free Software Foundation; version 2 of the License.
 *
 * These programs are distributed in the hope that they will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General License in the file LICENSE.txt for more
 * details.
 *
 * If you would like a copy of the GNU General License write to
 *
 * Free Software Foundation, Inc.
 * 59 Temple Place - Suite 330
 * Boston, MA 02111-1307, USA.
 *
 *
 * To arrange commercial support and licensing, contact ivata at
 *                  http://www.ivata.com/contact.jsp
 * -----------------------------------------------------------------------------
 * $Log: Security.java,v $
 * Revision 1.3  2005/04/10 19:42:41  colinmacleod
 * *** empty log message ***
 *
 * Revision 1.2  2005/04/09 17:19:56  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:39  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/11/12 18:16:07  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 15:57:18  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/11/03 16:07:28  colinmacleod
 * Many bugfixes.
 * Added new addUser method.
 *
 * Revision 1.1  2004/09/30 15:15:58  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.1  2004/07/13 19:41:11  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security;

import javax.ejb.EJBException;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.mask.util.SystemException;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Mar 29, 2004
 * @version $Revision: 1.3 $
 */
public interface Security {
    public String BUNDLE_PATH = "security";
    /**
     * <p>Add a new user to the system.</p>
     *
     * @param securitySession checks the current site user is allowed to perform
     *   the action
     * @param user the user to be amended.
     */
    UserDO addUser(SecuritySession securitySession,
            UserDO user)
                throws SystemException;

    /**
     * <p>Amend a user in the system.</p>
     *
     * @param securitySession checks the current site user is allowed to perform
     *   the action
     * @param user the user to be amended.
     */
    void amendUser(SecuritySession securitySession,
            UserDO user)
                throws SystemException;

    /**
     * <p>Check a password is correct for a user.</p>
     *
     * @param userName
     * @param password
     * @throws InvalidFieldValueException if any of the parameters are
     *     <code>null</code>.
     * @throws InvalidFieldValueException if any of the parameters are
     *     <code>null</code>.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    void checkPassword(SecuritySession securitySession, String password)
        throws SystemException;
    /**
     * Find a user given the user name.
     *
     * @param securitySession valid security session.
     * @param userName name of the user to find.
     */
    UserDO findUserByName(SecuritySession securitySession, String userName)
            throws SystemException;

    /**
     * <p>This method add prefix to username.</p>
     *
     * @param userName
     * @return prefix_userName
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    String getSystemUserName(final SecuritySession securitySession,
            String userName)
        throws SystemException;

    /**
     * <p>This method is converting SystemUserName to userName, it's oposite method to getSystemUserName.</p>
     * @param systemUserName
     * @return
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    String getUserNameFromSystemUserName(final SecuritySession securitySession,
            String systemUserName)
        throws SystemException;

    boolean isUser(SecuritySession securitySession,
            String userNameParam) throws SystemException;
    /**
     * <p>Find out if a user is currently enabled
     * @param userName
     * @throws InvalidFieldValueException if <code>userName</code> is
     *     <code>null</code>.
     * @return <code>true</code> if the user is currently enabled, otherwise
     * <code>false</code>.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    boolean isUserEnabled(SecuritySession securitySession,
        String userName)
        throws SystemException;
    /**
     * <p>Login to the system. This method confirms the user name and password
     * against system settings and logs the user into the mail server, if this
     * is the desired form of authentication.</p>
     *
     * @param user to log into the remote system.
     * @param password the clear-text password to log into the remote system.
     * @throws EJBException if the person cannot log in.
     * @return the mail server used to access the mail system, or
     * <code>null</code> if another form of authentication is being used.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    SecuritySession login(UserDO user, String password)
        throws SystemException;

    /**
     * @param userName
     * @return
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    String loginAgain(SecuritySession securitySession, String userName)
        throws SystemException;

    SecuritySession loginGuest()
        throws SystemException;

    /**
     * <p>Remove a user from the system. <strong>Note:</strong> this can have dire
     * consequences and will delete all entries this user has ever made in the
     * system. Consider using <code>enableUser</code> instead.</p>
     *
     * @param userName the name of the user who is doing the removing. <strong>This
     *     is not the name of the user to be removed!</strong>
     * @param userNameRemove the name of the user to be removed.
     * @see #enableUser
     * @throws InvalidFieldValueException if any of the parameters are
     *     <code>null</code>.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    void removeUser(SecuritySession securitySession,
        String userNameRemove)
        throws SystemException;

    /**
     * <p>Restore user is he was delted.</p>
     * @param userName who is doing this operation
     * @param restoreUserName who is going to be restored
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    void restoreUser(SecuritySession securitySession,
        String restoreUserName)
        throws SystemException;
    /**
     * <p>Set the password of a user.</p>
     *
     * @param userName the name of the user who is changing the password in the
     *     system. <strong>This is not be the user name whose password is to be
     *     changed!</strong>
     * @param userNamePassword the name of the user for whom to change the
     *     password.
     * @param password the new value of the password (unencrypted) for the user.
     * @throws InvalidFieldValueException if any of the parameters are
     *     <code>null</code>.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    void setPassword(
        SecuritySession securitySession,
        String userNamePassword,
        String password)
        throws SystemException;

}
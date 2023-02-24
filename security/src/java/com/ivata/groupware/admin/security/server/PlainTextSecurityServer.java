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
 * $Log: PlainTextSecurityServer.java,v $
 * Revision 1.3  2005/04/10 20:09:48  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:57  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:41  colinmacleod
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
 * Revision 1.2  2004/11/03 16:04:58  colinmacleod
 * Fixed persistence sessions left open.
 *
 * Revision 1.1  2004/09/30 15:15:58  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.2  2004/07/13 19:41:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.1  2004/03/21 20:16:24  colinmacleod
 * First version. Plain text security server allows for open portal to function without a mail server.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security.server;

import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.defaults.DefaultPicoContainer;

import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.groupware.container.persistence.QueryPersistenceManager;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.util.SystemException;

/**
 * Simple security server which compares passwords against plain text values in
 * the CMP layer.
 *
 * <p>
 * This security server is not very secure! You are advised not to use this but
 * to set up an <strong>IMAP</strong> server with the  <code>MailServer</code>
 * class from the <code>webmail</code> subproject.
 * </p>
 *
 * @since 2004-05-11
 * @version $Revision: 1.3 $
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 */
public class PlainTextSecurityServer implements SecurityServer {
    /**
     * Persistence manger used to store/retrieve data objects, or retrieve a
     * new persistence session.
     */
    private QueryPersistenceManager persistenceManager;

    /**
     * Construct and initialize the Securtiy Server implementation.
     *
     * @param persistenceManager persistence manager used to store/retrieve data
     *     objects.
     */
    public PlainTextSecurityServer(QueryPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    /**
     * Add a new user to the system.
     *
     * @param userName user name to add.
     * @param fullName full name under which the user will be filed.
     * @throws BusinessException if this user cannot be added.
     */
    public void addUser(final SecuritySession securitySession,
            final String userName,
            final String fullName) throws SystemException {
        // this server does not need to do anything additional to add a user
    }
    /**
     * Check the password for a user is correct.
     *
     * @param userName name of the user for whom to check the password.
     * @param password the new password value to check against the system.
     * @throws BusinessException if the password cannot be checked for any
     *     reason.
     */
    public void checkPassword(final SecuritySession securitySession,
            final String userName,
            final String password) throws SystemException {
        PersistenceSession persistenceSession =
            persistenceManager.openSession();
        try {

            UserDO user = (UserDO) persistenceManager.findInstance(persistenceSession,
                "securityUserByName",
                new Object[] { userName });

            String userPassword = user.getPassword();
            if (password == null) {
                if (userPassword != null) {
                    throw new SystemException("Null password specified - "
                        + "not null in data store for user '"
                        + userName
                        + "'.");
                }
            } else if (!password.equals(userPassword)) {
                throw new SystemException("Passwords do not match for "
                    + "user '"
                    + userName
                    + "'.");
            }
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }
    /**
     * @see com.ivata.groupware.admin.security.server.SecurityServer#getSystemUserName(String)
     */
    public final String getSystemUserName(final SecuritySession securitySession,
            final String userName) {
        return userName;
    }

    /**
     * @see com.ivata.groupware.admin.security.server.SecurityServer#getUserNameFromSystemUserName(String)
     */
    public final String getUserNameFromSystemUserName(
            final SecuritySession securitySession,
            final String systemUserName) {
        return systemUserName;
    }

    /**
     * Refer to {@link }.
     *
     * @param userNameParam
     * @return
     * @see com.ivata.groupware.admin.security.server.SecurityServer#isUser(java.lang.String)
     */
    public boolean isUser(final SecuritySession securitySession,
            String userNameParam) {
        return false;
    }
    /**
     * <p>Login to an authentication server using the user name and password
     * provided.</p>
     *
     * @param user user to login to the server.
     * @param password used to login to the server
     * @return valid session for this username password combination.
     * @throws BusinessException if this user cannot be authenticated.
     */
    public SecuritySession login(final UserDO user,
            final String password) throws SystemException {
        checkPassword(loginGuest(), user.getName(), password);
        PicoContainer globalContainer = PicoContainerFactory.getInstance()
            .getGlobalContainer();
        MutablePicoContainer sessionContainer = new DefaultPicoContainer(globalContainer);
        PlainTextSecuritySession session =
            new PlainTextSecuritySession(sessionContainer, user);
        sessionContainer.registerComponentInstance(SecuritySession.class, session);
        session.setPassword(password);
        return session;
    }

    /**
     * @see com.ivata.groupware.admin.security.server.SecurityServer#login()
     */
    public SecuritySession loginGuest() throws SystemException {
        PicoContainer globalContainer = PicoContainerFactory.getInstance()
            .getGlobalContainer();
        UserDO guestUser = new UserDO();
        guestUser.setDeleted(false);
        guestUser.setEnabled(true);
        guestUser.setName("guest");
        MutablePicoContainer sessionContainer = new DefaultPicoContainer(globalContainer);
        SecuritySession session = new PlainTextSecuritySession(sessionContainer, guestUser);
        sessionContainer.registerComponentInstance(SecuritySession.class, session);
        return session;
    }

    /**
     * <p>Remove the user with the given name from the system.</p>
     *
     * @param userName name of the user to be removed.
     * @throws BusinessException if this user cannot be removed.
     */
    public void removeUser(final SecuritySession securitySession,
            final String userName) throws SystemException {
        // don't need to do anything additional to remove a user for this server
    }

    /**
     * <p>Set the password for a user.</p>
     *
     * @param userName name of the user for whom to set the password.
     * @param password the new password value to set.
     * @throws BusinessException if the password cannot be set for any
     *     reason.
     */
    public final void setPassword(final SecuritySession securitySession,
            final String userName,
            final String password) throws SystemException {
        PersistenceSession persistenceSession;
        persistenceSession = persistenceManager.openSession();
        try {
            UserDO user = (UserDO) persistenceManager.findInstance(persistenceSession,
                "securityUserByName",
                new Object[] { userName });
            user.setPassword(password);
            persistenceManager.amend(persistenceSession, user);
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    };

}

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
 * $Log: SecurityBean.java,v $
 * Revision 1.3  2005/04/10 19:42:41  colinmacleod
 * *** empty log message ***
 *
 * Revision 1.2  2005/04/09 17:19:56  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:40  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/11/12 18:16:07  colinmacleod
 * Ordered imports.
 *
 * Revision 1.2  2004/11/12 15:57:18  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/09/30 15:15:58  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.5  2004/07/13 19:41:11  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:05  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/03/21 20:21:49  colinmacleod
 * Converted to using factory method for security server (rather than singleton).
 *
 * Revision 1.2  2004/02/01 22:00:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:45  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.7  2003/11/03 11:28:24  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.6  2003/10/24 13:18:12  jano
 * fixing some bugs
 *
 * Revision 1.5  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.4  2003/10/16 07:53:41  jano
 * fixing XDoclet
 *
 * Revision 1.3  2003/10/15 12:35:33  jano
 * going back to 1.1
 *
 * Revision 1.1  2003/10/13 20:50:06  colin
 * Initial revision
 *
 * Revision 1.10  2003/09/11 13:10:10  jano
 * fixing bugs with ranaming userName and with removing user from system
 *
 * Revision 1.9  2003/07/25 11:49:45  jano
 * adding functionality for addressBook extension
 *
 * Revision 1.8  2003/06/04 09:56:17  jano
 * if new user is not enable it's not necesary check email
 *
 * Revision 1.7  2003/05/22 07:58:49  jano
 * we have new method in SecurityBean getUserNameFromSystemUserName
 *
 * Revision 1.6  2003/04/09 09:43:42  jano
 * *** empty log message ***
 *
 * Revision 1.5  2003/04/09 08:49:22  jano
 * handling data of removing user
 *
 * Revision 1.4  2003/04/01 15:01:01  jano
 * fixing bug with addUserToPerson
 *
 * Revision 1.3  2003/03/14 10:25:44  jano
 * adding backdoor man functionality
 * backdoor man = briezky
 *
 * Revision 1.2  2003/02/25 14:38:12  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.1  2003/02/24 18:53:57  colin
 * added to admin
 *
 * Revision 1.8  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.7  2002/12/03 15:39:49  jano
 * automaticaly put new user to USER group
 *
 * Revision 1.6  2002/11/20 16:59:06  jano
 * I changed message that you can add email in contact list or add at least one user alias
 *
 * Revision 1.5  2002/10/23 12:44:56  jano
 * fixing bug in getSystemUserName
 *
 * Revision 1.4  2002/10/23 09:16:39  jano
 * new method for genereting SystemUserName,
 * it's userName with prefix
 *
 * Revision 1.3  2002/10/18 08:37:39  colin
 * bugfixes in addUser, amendUser and setPassword
 *
 * Revision 1.2  2002/09/10 06:52:22  colin
 * added boolean enable to addUserToPerson
 *
 * Revision 1.1  2002/09/09 08:35:24  colin
 * new security EJB takes over role of
 *  logging into the system
 *  maintaining user names and passwords
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.SystemException;

/**
 * <p>The security bean is responsible for adding, removing and amending users
 * to the system, and for logging in in the first place.</p>
 *
 * @since 2002-09-08
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @ejb.bean
 *      name="Security"
 *      display-name="Security"
 *      type="Stateless"
 *      view-type="remote"
 *      jndi-name="SecurityRemote"
 *
 * @ejb.transaction
 *      type = "Required"
 *
 *  @ejb.home
 *      generate="false"
 *      remote-class="com.ivata.groupware.admin.security.SecurityRemoteHome"
 *
 *  @ejb.interface
 *      remote-class="com.ivata.groupware.admin.security.SecurityRemote"
 */
public class SecurityBean implements SessionBean {
    /**
     * <p>Provides the session bean with container-specific information.</p>
     */
    SessionContext sessionContext;

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
    public void checkPassword(final SecuritySession securitySession,
            final String password) throws SystemException {
        getSecurity().checkPassword(securitySession, password);
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
     * Find a user given the user name.
     *
     * @param securitySession valid security session.
     * @param userName name of the user to find.
     */
    public UserDO findUserByName(final SecuritySession securitySession,
            final String userName)
            throws SystemException {
        return getSecurity().findUserByName(securitySession, userName);
    }

    /**
     * Get the security implementation.
     *
     * @return valid security implementation.
     */
    private Security getSecurity() throws SystemException {
        PicoContainer container = PicoContainerFactory.getInstance()
            .getGlobalContainer();
        return (Security) container.getComponentInstance(Security.class);
    }

    /**
     * <p>This method add prefix to username.</p>
     *
     * @param userName
     * @return prefix_userName
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public final String getSystemUserName(final SecuritySession securitySession,
            final String userName)
            throws SystemException {
        return getSecurity().getSystemUserName(securitySession, userName);
    }

    /**
     * <p>This method is converting SystemUserName to userName, it's oposite method to getSystemUserName.</p>
     * @param systemUserName
     * @return
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public final String getUserNameFromSystemUserName(
            final SecuritySession securitySession,
            final String systemUserName)
            throws SystemException {
        return getSecurity().getUserNameFromSystemUserName(securitySession,
                systemUserName);
    }

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
    public boolean isUserEnabled(final SecuritySession securitySession,
            final String userName)
            throws SystemException {
        return getSecurity().isUserEnabled(securitySession, userName);
    }

    /**
     * <p>Login to the system. This method confirms the user name and password
     * against system settings and logs the user into the mail server, if this
     * is the desired form of authentication.</p>
     *
     * @param userName this user name is used to log into the remote system.
     * @param password the clear-text password to log into the remote system.
     * @throws EJBException if the person cannot log in.
     * @return the mail server used to access the mail system, or
     * <code>null</code> if another form of authentication is being used.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public SecuritySession login(final UserDO user,
            final String password)
            throws SystemException {
        return getSecurity().login(user, password);
    }

    /**
     * <p>if userName briezky is trying login -> find first admin .</p>
     * @param userName
     * @return
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public String loginAgain(final SecuritySession securitySession,
            final String userName)
            throws SystemException {
        return getSecurity().loginAgain(securitySession, userName);
    }

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
    public void removeUser(final SecuritySession securitySession,
            final String userNameRemove) throws SystemException {
        getSecurity().removeUser(securitySession, userNameRemove);
    }

    /**
     * <p>Restore user is he was delted.</p>
     * @param userName who is doing this operation
     * @param restoreUserName who is going to be restored
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public void restoreUser(final SecuritySession securitySession,
            final String restoreUserName)
            throws SystemException {
        getSecurity().restoreUser(securitySession, restoreUserName);
    }

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
    public final void setPassword(final SecuritySession securitySession,
            final String userNamePassword,
            final String password) throws SystemException {
        getSecurity().setPassword(securitySession, userNamePassword, password);
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

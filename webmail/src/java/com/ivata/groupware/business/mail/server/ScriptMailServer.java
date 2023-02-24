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
 * $Log: ScriptMailServer.java,v $
 * Revision 1.3  2005/04/10 18:47:43  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:20:01  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:20  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.8  2004/11/12 18:16:08  colinmacleod
 * Ordered imports.
 *
 * Revision 1.7  2004/11/12 15:57:25  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.6  2004/11/03 16:18:09  colinmacleod
 * Changed return type of getUserAliases to List.
 *
 * Revision 1.5  2004/07/18 21:59:15  colinmacleod
 * Removed Person from User - now you need to use addressbook/persistence manager to find the person (makes the app run faster.)
 *
 * Revision 1.4  2004/07/13 19:48:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:39  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:07:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:59:57  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.5  2004/01/12 14:02:23  jano
 * fixing bugs
 *
 * Revision 1.4  2003/10/28 13:27:51  jano
 * Committed webmail,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.3  2003/10/23 21:31:05  colin
 * Fixed singletons; removed double-checked locking.
 *
 * Revision 1.2  2003/10/15 14:11:33  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/02/27 17:24:25  peter
 * getFolderModified - script return value trimmed
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 * Revision 1.9  2003/02/04 17:39:22  colin
 * copyright notice
 * Revision 1.8  2002/11/19 14:38:29  colin
 * ed getFolderModified
 * Revision 1.7  2002/11/05 16:13:29  jano
 * we need to read setting in echa exec method,
 * Revision 1.6  2002/10/24 15:50:08  jano
 * filing bug with spaces in exec and SUDO
 * Revision 1.5  2002/10/23 09:18:08  jano
 * there is a new method for generating SystemUserName
 * Revision 1.4  2002/09/09 11:03:08  colin
 * made session classes serializable
 * Revision 1.3  2002/09/09 08:44:52  colin
 * made new mail session class to abstract the non-serializable
 * * JavaMail session
 * Revision 1.2  2002/08/30 14:20:36  peter
 * fixed the previous state
 * Revision 1.2  2002/08/11 11:42:03  colin
 * made initialization use path directly rather than the
 * settings
 * Revision 1.1  2002/07/26 13:10:18  colin
 * first version of mail server support
 * user rights currently not implemented
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.mail.server;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.ivata.groupware.admin.script.ScriptExecutor;
import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.business.addressbook.AddressBook;
import com.ivata.mask.util.CollectionHandling;
import com.ivata.mask.util.SystemException;


/**
 * <p>This implementation of the mail server uses scripts
 * to perform each of the actions.</p>
 *
 * <p>The scripts must have the same name as each of the
 * methods and use the same parameters. The location of the
 * scripts within the system is set using the system setting
 * 'pathScriptMailServer'.</p>
 *
 * <p>For each script, a return value of <code>0</code> is taken
 * as success. Any other value results in a {@link
 * GroupwareException GroupwareException} being thrown, with
 * the contents of the standard error stream as its message.</p>
 *
 * @since 2002-07-20
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class ScriptMailServer extends JavaMailServer implements MailServer {
    String[] environmentVariables = null;
    private ScriptExecutor scriptExecutor;
    /**
     * <p>Stores the sole instance of this class.</p>
     */
    String scriptPath;

    private Settings settings;
    /**
     * This prefix is appended to user names to get the underlying system
     * equivalent.
     */
    private String userNamePrefix;
    AddressBook addressBook;

    /**
     * <p>Create a script mail server.</p>
     */
    public ScriptMailServer(final ScriptExecutor scriptExecutor,
            final String userNamePrefix,
            final AddressBook addressBook,
            final Settings settings
            ) {
        super(addressBook, settings);
        this.scriptExecutor = scriptExecutor;
        this.settings = settings;
        this.userNamePrefix = userNamePrefix;
        this.addressBook = addressBook;
   }

    /**
     * <p>Add a new user to the system. </p>
     *
     * <p>This method calls the <code>addUser</code> script.</p>
     *
     * @param userName it's userName in System
     * @param fileAs it's file as of user
     * @throws GroupwareException containing the content of the standard
     * error stream, if the script returns non-zero.
     */
    public void addUser(final SecuritySession securitySession,
            final String userName,
            final String fileAs)
            throws SystemException {
        String[] argv = new String[2];

        argv[0] = getSystemUserName(securitySession, userName);
        argv[1] = fileAs;

        scriptExecutor.exec("addUser", argv);
        argv = new String[3];

        argv[0] = getSystemUserName(securitySession, userName);
        argv[1] = getSystemUserName(securitySession,
                securitySession.getUser().getName());
        argv[2] = getSystemUserName(securitySession,
                securitySession.getPassword());
        scriptExecutor.exec("makeMaildir", argv);
    }

    /**
     * <p>Gets the contents of mailing list with the name provided.</p>
     *
     * <p>This method calls the script called <code>getList</code>.</p>
     *
     * @param name the name of the list to be retrieved.
     * @return a <code>Collection</code> containing
     * <code>UserLocal</code>
     * instances of each of the users in the list.
     * @throws GroupwareException containing the content of the standard
     * error stream, if the script returns non-zero.
     */
    public final Collection getList(final SecuritySession securitySession,
            final String name)
        throws SystemException {
        String[] argv = new String[1];

        argv[0] = name;

        return CollectionHandling.convertFromLines(scriptExecutor.exec("getList", argv));
    }

   /**
    * <p>This method add prefix to username.</p>
    *
    * @param userName
    * @return prefix_userName
    */
   public final String getSystemUserName(final SecuritySession securitySession,
           final String userName)  {
       //server.getJndiPrefix();
       //ApplicationServer.get .getInstance().getJndiPrefix();
       if (userNamePrefix.length() > 0) {
           return userNamePrefix + "_" + userName;
       } else {
           return userName;
       }
   }

    /**
     * <p>Gets all of the email aliases for the user provided.</p>
     *
     * <p>This method calls the script <code>getUserAliases</code>.</p>
     *
     * @param userName the user for whom to get the email aliases.
     * @return a <code>Collection</code> containing
     * <code>String</code> values for each of the aliases.
     * @throws GroupwareException containing the content of the standard
     * error stream, if the script returns non-zero.
     */
    public List getUserAliases(final SecuritySession securitySession,
            final String userName)
        throws SystemException {
        String[] argv = new String[1];

        argv[0] = getSystemUserName(securitySession, userName);

        return CollectionHandling.convertFromLines(scriptExecutor.exec("getUserAliases", argv));
    }

    /**
     * <p>Get the email addresss this user's mail is forwarded to.</p>
     *
     * <p>This method calls the script <code>getUserForwarding</code>.</p>
     *
     * @param userName the user for whom to activate/deactive email
     * forwarding.
     * @return email address all email for this user is forwarded to, or
     * <code>null</code> if there is no forwarding for this user.
     * @throws GroupwareException containing the content of the standard
     * error stream, if the script returns non-zero.
     */
    public final String getUserForwarding(final SecuritySession securitySession,
            final String userName)
        throws SystemException {
        String[] argv = new String[1];

        argv[0] = getSystemUserName(securitySession, userName);

        return scriptExecutor.exec("getUserForwarding", argv);
    }

    /**
     * <p>This method is converting SystemUserName to userName, it's oposite method to getSystemUserName.</p>
     * @param systemUserName
     * @return
     */
    public final String getUserNameFromSystemUserName(final SecuritySession securitySession,
            final String systemUserName) {
        if ((userNamePrefix.length() > 0) && systemUserName.startsWith(userNamePrefix)) {
            return systemUserName.substring(userNamePrefix.length() + 1);
        } else {
            return systemUserName;
        }
    }

    /**
     * <p>Gets the current vacation method for the user provided. </p>
     *
     * <p>This method calls the script
     * <code>getVacationMessage</code>.</p>
     *
     * @param userName the user for whom to set the vacation message.
     * @return the current vacation message for this user, or
     * <code>null</code> if
     * the user does not have a vacation message.
     * @throws GroupwareException containing the content of the standard
     * error stream, if the script returns non-zero.
     */
    public final String getVacationMessage(final SecuritySession securitySession,
            final String userName)
        throws SystemException {
        String[] argv = new String[1];

        argv[0] = getSystemUserName(securitySession, userName);

        String vacationMessage = scriptExecutor.exec("getVacationMessage", argv);

        // TODO: we're assuming that an empty message = no message
        return vacationMessage.equals("") ? null : vacationMessage;
    }

    /**
     * <p>Removes an existing mailing list from the system.</p>
     *
     * <p>The <code>removeList</code> script is called with
     * <code>name</code> as its only parameter.</p>
     *
     * @param name the name of the list to remove.
     * @throws GroupwareException containing the content of the standard
     * error stream, if the script returns non-zero.
     */
    public void removeList(final SecuritySession securitySession,
            final String name) throws SystemException {
        String[] argv = new String[1];

        argv[0] = name;
        scriptExecutor.exec("removeList", argv);
    }

    /**
     * <p>Remove a user from the email system. <strong>Note:</strong> this user is
     * not removed
     * from the address book, only the email server. Comments by this user
     * will still appear in the system, though the user will no longer
     * have an email account.</p>
     *
     * <p>This is good for employees who have left the company. We still
     * want to see their contributions, but they no longer have access to
     * the system themselves.</p>
     *
     * <p>This method calls the <code>removeUser</code> script.</p>
     *
     * @param userName the user to remove from the email system.
     * @throws GroupwareException containing the content of the standard
     * error stream, if the script returns non-zero.
     */
    public void removeUser(final SecuritySession securitySession,
            final String userName) throws SystemException {
        String[] argv = new String[1];

        argv[0] = getSystemUserName(securitySession, userName);
        scriptExecutor.exec("removeUser", argv);
    }

    /**
     * <p>Adds or amends a mailing list with the name and users
     * provided.</p>
     *
     * <p>This method calls the <code>setList</code> script with the name
     * as the first parameter and the user names of each of the users as
     * following parameters.</p>
     *
     * @param name the name of the list to be added.
     * @param users <code>Collection</code> containing
     * <code>UserLocal</code> instances of each of the users in the list.
     * Note that if you are changing an existing list, this array should
     * contain all of the users, not just the new ones.
     * @throws GroupwareException containing the content of the standard
     * error stream, if the script returns non-zero.
     */
    public final void setList(final SecuritySession securitySession,
            final String name,
            final Collection users)
        throws SystemException {
        String[] argv = new String[users.size() + 1];
        argv[0] = name;
        int index = 1;

        for (Iterator i = users.iterator(); i.hasNext(); ++index) {
            argv[index] = (String) i.next();
        }

        scriptExecutor.exec("setList", argv);
    }

    /**
     * <p>Set a user's password in the system. This method calls the
     * <code>setPassword</code> script with <code>userName</code> and
     * <code>password</code> as parameters.</p>
     *
     * @param userName the user for whom to set the password.
     * @param password new value of the password.
     * @throws GroupwareException containing the content of the standard
     * error stream, if the script returns non-zero.
     */
    public final void setPassword(final SecuritySession securitySession,
            final String userName,
            final String password)
            throws SystemException {
        String[] argv = new String[3];

        argv[0] = getSystemUserName(securitySession, userName);
        argv[1] = password;
        // this parameter should be no-zero to indicate conversion of error
        // messages to localized keys
        argv[2] = "1";
        scriptExecutor.exec("setPassword", argv);
    }

    /**
     * <p>Gets all of the email aliases for the user provided.</p>
     *
     * <p>This method calls the script <code>setUserAliases</code>.</p>
     *
     * @param userName the user for whom to get the email aliases.
     * @param aliases a <code>Collection</code> containing
     * <code>String</code> values for each of the aliases.
     * @throws GroupwareException containing the content of the standard
     * error stream, if the script returns non-zero.
     */
    public final void setUserAliases(final SecuritySession securitySession,
            final String userName,
            final Collection aliases)
        throws SystemException {
        String[] argv = new String[aliases.size() + 1];

        argv[0] = getSystemUserName(securitySession, userName);

        int index = 1;

        for (Iterator i = aliases.iterator(); i.hasNext(); ++index) {
            argv[index] = (String) i.next();
        }

        scriptExecutor.exec("setUserAliases", argv);
    }

    /**
     * <p>Set an email addresss to forward this user's mail to. If
     * <code>address</code> is set to <code>null</code> then any previous
     * email
     * forwarding is removed.
     *
     * @param userName the user for whom to activate/deactive email
     * forwarding.
     * @param address email address to forward all email for this user to.
     * If this
     * address is set to <code>null</code> then any previous forwarding is
     * removed.
     * @throws GroupwareException containing the content of the standard
     * error
     * stream, if the script returns non-zero.
     *
     *
     */
    public final void setUserForwarding(final SecuritySession securitySession,
            final String userName,
            final String address)
        throws SystemException {
        String[] argv = new String[2];

        argv[0] = getSystemUserName(securitySession, userName);
        argv[1] = address;
        scriptExecutor.exec("setUserForwarding", argv);
    }

    /**
     * <p>Sets the vacation method for the user provided. This message
     * will be sent
     * to all mails received at this address until it has been cleared, by
     * calling
     * this method again with a <code>null</code> value for the
     * <code>message</code>
     * parameter.</p>
     *
     * <p>This method calls the <code>setVacationMessage</code> script
     * with <code>user name</code> and <code>message</code> as parameters.
     * If the <code>message</code> parameter is <code>null</code>, then
     * the
     * script is called with just the
     * <code>user name</code> parameter.</p>
     *
     * @param userName the user for whom to set the vacation message.
     * @param messageParam the new vacation message for this user. Set to
     * <code>null</code> to remove any existing vacation message.
     * @throws GroupwareException containing the content of the standard
     * error stream, if the script returns non-zero.
     */
    public final void setVacationMessage(final SecuritySession securitySession,
            final String userName,
            final String messageParam)
            throws SystemException {
        String[] argv = new String[2];
        String message = messageParam;

        // TODO: possibly handle vacation message removal differently here
        // (null = remove vacation message - we just set it to the empty string)
        if (message == null) {
            message = "";
        }

        argv[0] = getSystemUserName(securitySession, userName);
        argv[1] = message;
        scriptExecutor.exec("setVacationMessage", argv);
    }

    /**
     * Refer to {@link }.
     *
     * @param userNameParam
     * @return
     * @see com.ivata.groupware.admin.security.server.SecurityServer#isUser(java.lang.String)
     */
    public boolean isUser(final SecuritySession securitySession,
            final String userNameParam) throws SystemException {
        String[] argv = new String[1];

        argv[0] = getSystemUserName(securitySession, userNameParam);

        String isUser = scriptExecutor.exec("isUser", argv).trim();
        return "true".equals(isUser);
    }

}

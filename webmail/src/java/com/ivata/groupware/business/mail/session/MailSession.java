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
 * $Log: MailSession.java,v $
 * Revision 1.2  2005/04/09 17:20:01  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:16  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.6  2004/11/12 18:16:08  colinmacleod
 * Ordered imports.
 *
 * Revision 1.5  2004/11/12 15:57:25  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
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
 * Revision 1.1.1.1  2004/01/27 20:59:58  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 14:11:33  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.3  2003/02/04 17:39:22  colin
 * copyright notice
 *
 * Revision 1.2  2002/09/09 11:00:38  colin
 * made session classes serializable :-)
 *
 * Revision 1.3  2002/09/09 08:44:52  colin
 * made new mail session class to abstract the non-serializable JavaMail session
 *
 * Revision 1.1  2002/09/09 08:26:37  colin
 * first version of MailSession logs into session everytime
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.mail.session;

import java.security.NoSuchProviderException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.naming.AuthenticationException;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.AbstractSecuritySession;
import com.ivata.groupware.admin.security.server.SecurityServerException;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.mask.util.SystemException;


/**
 * <p><code>javax.mail.Session</code> is not serializable and cannot
 * be passed from client to server. This class stores the login details
 * an provides a new session instance when required, by logging in
 * again.</p>
 *
 * @since 2002-09-08
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public class MailSession extends AbstractSecuritySession {
    /**
     * <p>Contains all of the properties necessary to log into the
     * <code>javax.mail.Session</code>.</p>
     */
    private Properties mailProperties = null;

    /**
     * <p>Used to autheticate this mail session, whenever the
     * <code>javax.mail.Session</code> is recreated.</p>
     */
    public MailAuthenticator authenticator;

    /**
     * Construct a new mail session.
     */
    public MailSession(PicoContainer container, UserDO user)
            throws SystemException {
        super(container, user);
    }

    /**
     * <p>Logs into the mail session for the first time. This stores the
     * user name and password so that the session can be continued again
     * later.</p>
     *
     * @userName name of the user to log in.
     * @param password clear-text user's password to log into the mail
     * server.
     * @param mailProperties all of the mail properties necessary to login
     * to the system.
     * @throws AuthenticationException thrown by JavaMail if the user cannot
     *      login.
     * @return newly created mail session.
     */
    public Session login(final String password,
            final Properties mailProperties)
        throws SecurityServerException, NoSuchProviderException,
            MessagingException {
        authenticator = new MailAuthenticator(
                mailProperties.getProperty("mail.user"), password);
        this.mailProperties = mailProperties;

        return getJavaMailSession();
    }

    /**
     * <p>Return the current mail session as a
     * <code>javax.mail.Session</code> instance. This involves logging in
     * again.</p>
     *
     * @throws AuthenticationException thrown by JavaMail if the user cannot login.
     * @throws NoSuchProviderException thrown by JavaMail if the user cannot login.
     * @throws MessagingException thrown by JavaMail if the user cannot login.
     * @return the current mail session as a
     * <code>javax.mail.Session</code> instance.
     */
    public Session getJavaMailSession()
        throws SecurityServerException, NoSuchProviderException,
            MessagingException {
        Session javaSession = Session.getInstance(mailProperties, authenticator);
        Store store = javaSession.getStore("imap");
        if (!store.isConnected()) {
            store.connect();
        }
        store.close();
        return javaSession;
    }
}

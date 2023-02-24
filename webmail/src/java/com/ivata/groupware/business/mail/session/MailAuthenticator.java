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
 * $Log: MailAuthenticator.java,v $
 * Revision 1.2  2005/04/09 17:20:01  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:16  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
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
 * Revision 1.1  2002/09/09 08:25:50  colin
 * moved authenticator to session subpackage
 *
 * Revision 1.2  2002/08/30 14:20:36  peter
 * fixed the previous state
 *
 * Revision 1.1  2002/08/30 09:18:22  colin
 * added to repository late - sorry peter
 *
 * Revision 1.1  2002/07/15 07:51:04  colin
 * added new Mail EJB and local interface to settings
 *
 * Revision 1.2  2002/07/01 10:24:07  colin
 * improved documentation and added imports
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.mail.session;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


/**
 * <p>Handles email authentication within the {@link
 * com.ivata.groupware.web.tag.LoginTag LoginTag}.</p>
 *
 * @since 2001-09-08
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public class MailAuthenticator extends Authenticator implements Serializable {
    /**
     * <p>This username will be used to log into the mail account.</p>
     */
    String userName;

    /**
     * <p>Password used to check user against mail account.</p>
     */
    String password;

    /**
     * <p>Construct an Authenticator with the given username and
     * password.</p>
     *
     * @param userName username used to create a password authentication
     * in
     * <code>getPasswordAuthentication</code>
     * @param password username used to create a password
     * authentication in <code>getPasswordAuthentication</code>
     * @see #getPasswordAuthentication(  )
     *
     */
    public MailAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * <p>Return a PasswordAuthentication for the username and password
     * given in
     * the constructor.</p>
     *
     * @return PasswordAuthentication instantiated using the username and
     * password given in the constructor
     *
     */
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }

    /**
     * <p>Serialize the object to the output stream provided.</p>
     * @exception IOException thrown by
     * <code>ObjectOutputStream.defaultWriteObject(  )</code>
     * @param oos the output stream to serialize the object to
     *
     */
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    /**
     * <p>Serialize the object from the input stream provided.</p>
     * @exception ClassNotFoundException thrown by
     * <code>ObjectInputStream.defaultReadObject(  )</code>.
     * @exception IOException thrown by
     * <code>ObjectInputStream.defaultReadObject(  )</code>.
     * @param ois the input stream to serialize the object from
     *
     */
    private void readObject(final ObjectInputStream ois)
        throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }
}

/*
 * Copyright (c) 2001 - 2005 ivata limited.
 * All rights reserved.
 * ---------------------------------------------------------
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
 * ---------------------------------------------------------
 * $Log: MailSetupConstants.java,v $
 * Revision 1.1  2005/04/11 10:03:43  colinmacleod
 * Added setup feature.
 *
 * ---------------------------------------------------------
 */
package com.ivata.groupware.business.mail.struts;

/**
 * Utility class to store literals we need for setup (in
 * <code>SetupAction</code> and <code>SetupForm</code>).
 *
 * @since ivata groupware 0.11 (2005-04-07)
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.1 $
 */
public final class MailSetupConstants {
    /**
     * Email folder namespace to use for Courier.
     */
    public static final String COURIER_FOLDER_NAMESPACE = "INBOX.";
    /**
     * This string in the output of port 143 identifies Courier.
     */
    public static final String COURIER_SIGNATURE = "Courier-IMAP";
    /**
     * Email folder namespace to use for Cyrus.
     */
    public static final String CYRUS_FOLDER_NAMESPACE = "INBOX.";
    /**
     * This string in the output of port 143 identifies Cyrus.
     */
    public static final String CYRUS_SIGNATURE = "Cyrus IMAP";
    /**
     * Mail domain to give you the right idea.
     */
    public static final String DEFAULT_MAIL_DOMAIN = "mycompany.com";
    /**
     * Default path for scripts.
     */
    public static final String DEFAULT_SCRIPTS_PATH = "/usr/local/ivatagroupware";
    /**
     * IMAP port - for receiving mails.
     */
    public static final int IMAP_PORT = 143;
    /**
     * This is the path of the <strong>EXIM</strong> scripts we use by default,
     * relative to the <strong>ivata groupware</strong> scripts' path.
     */
    public static final String SCRIPT_PATH_EXIM = "/mailserver/exim";
    /**
     * This is the path of the <strong>SUDO</strong> scripts we use by default,
     * relative to the <strong>ivata groupware</strong> scripts' path.
     */
    public static final String SCRIPT_PATH_SUDO = "/mailserver/sudo";
    /**
     * IMAP port - for sending mails.
     */
    public static final int SMTP_PORT = 25;
    /**
     * Interval to wait before re-checking <code>available</code> on a socket
     * input stream. The process will wait a total of
     * <code>SOCKET_WAIT_NUMBER</code> times  <code>SOCKET_WAIT_INTERVAL</code>
     * milliseconds before giving up on any more socket output.
     */
    public static final long SOCKET_WAIT_INTERVAL = 50;
    /**
     * Number of times to wait before deciding a socket input stream has no more
     * information. The process will wait a total of
     * <code>SOCKET_WAIT_NUMBER</code> times  <code>SOCKET_WAIT_INTERVAL</code>
     * milliseconds before giving up on any more socket output.
     */
    public static final int SOCKET_WAIT_NUMBER = 20;
    /**
     * Private default constructor enforces utility class behavior.
     */
    private MailSetupConstants() {
    }
}

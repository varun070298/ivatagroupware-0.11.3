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
 * $Log: MessageNotFoundException.java,v $
 * Revision 1.2  2005/04/09 17:20:01  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:17  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
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
 * Revision 1.1.1.1  2004/01/27 20:59:57  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 14:11:33  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.3  2003/02/04 17:39:21  colin
 * copyright notice
 *
 * Revision 1.2  2002/11/19 14:12:16  colin
 * added 'additional information' constructor
 *
 * Revision 1.1  2002/11/12 09:11:28  colin
 * added with restructuring of mail subsystem
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.mail.message;

import com.ivata.mask.util.SystemException;


/**
 * <p>Thrown by {@link com.ivata.groupware.business.mail.MailBean MailBean} if there is
 * a requested mail cannot be located.</p>
 *
 * @since 2002-11-10
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public class MessageNotFoundException extends SystemException {
    /**
     * <p>Creates a new <code>MailNotFoundException</code> using the given
     * details about the message which could not be found.</p>
     *
     * @param folderName the name of the folder where the message was being
     *     looked for.
     * @param messageId the unique identifier of the message which could not be
     *     found.
     */
    public MessageNotFoundException(String folderName, String messageId) {
        this (folderName, messageId, (Throwable)null);
    }
    /**
     * <p>Creates a new <code>MailNotFoundException</code> using the given
     * details about the message which could not be found.</p>
     *
     * @param folderName the name of the folder where the message was being
     *     looked for.
     * @param messageId the unique identifier of the message which could not be
     *     found.
     * @param cause root exception which caused this to happen.
     */
    public MessageNotFoundException(String folderName, String messageId,
            Throwable cause) {
        super("ERROR in Mail: no message with id '"
                + messageId
                + "' in folder '"
                + folderName
                + "'.",
                cause);
    }

    /**
     * <p>Creates a new <code>MailNotFoundException</code> using the given
     * details about the message which could not be found. This constructor
     * provides extra information about a message which could not be located due
     * to programming or configuration errors.</p>
     *
     * @param folderName the name of the folder where the message was being
     *     looked for.
     * @param messageId the unique identifier of the message which could not be
     *     found.
     * @param additionalInformation a clear message indicating an additional
     *     cause of the exception (such as "this folder does not exist").
     */
    public MessageNotFoundException(String folderName, String messageId,
            String additionalInformation) {
        this (folderName, messageId, additionalInformation, null);
    }
    /**
     * <p>Creates a new <code>MailNotFoundException</code> using the given
     * details about the message which could not be found. This constructor
     * provides extra information about a message which could not be located due
     * to programming or configuration errors.</p>
     *
     * @param folderName the name of the folder where the message was being
     *     looked for.
     * @param messageId the unique identifier of the message which could not be
     *     found.
     * @param additionalInformation a clear message indicating an additional
     *     cause of the exception (such as "this folder does not exist").
     */
    public MessageNotFoundException(String folderName, String messageId,
            String additionalInformation, Throwable cause) {
        super("ERROR in Mail: could not retrieve message '"
                + messageId
                + "' from folder '"
                + folderName
                + "': "
                + additionalInformation
                + ".",
                cause);
    }
}

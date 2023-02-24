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
 * $Log: MailConstants.java,v $
 * Revision 1.2  2005/04/09 17:20:00  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:15  colinmacleod
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
 * Revision 1.1.1.1  2004/01/27 20:59:56  colinmacleod
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
 * Revision 1.2  2002/11/17 20:02:23  colin
 * added sort constants
 *
 * Revision 1.1  2002/11/12 11:39:22  colin
 * first version in CVS. added constants for thread handling.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.mail;


/**
 * <p>Constants within the mail subsystem, to indicate how mail
 * messages relate to one another.</p>
 *
 * @since 2002-11-10
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public class MailConstants {
    /**
     * <p>Default state for a message - indicates a new message with no
     * association to previous messages.</p>
     */
    public static final Integer THREAD_NEW = new Integer(0);

    /**
     * <p>Indicates that the new message is a reply to an existing mail,
     * addressed only to that mail's sender (not the other
     * recipients).</p>
     */
    public static final Integer THREAD_REPLY = new Integer(1);

    /**
     * <p>Indicates that the new message is a reply to an existing mail,
     * addressed both to that mail's sender and the other (CC)
     * recipients.</p>
     */
    public static final Integer THREAD_REPLY_ALL = new Integer(2);

    /**
     * <p>Indicates that the new message is created by forwarding other
     * messages.</p>
     */
    public static final Integer THREAD_FORWARD = new Integer(3);

    /**
     * <p>Indicates that the new message is created by editing an existing
     * message.</p>
     */
    public static final Integer THREAD_DRAFT = new Integer(4);

    /**
     * <p>Indicates a list of messages should be sorted by the message
     * folder.</p>
     */
    public static final Integer SORT_FOLDER = new Integer(0);

    /**
     * <p>Indicates a list of messages should be sorted by the message
     * id.</p>
     */
    public static final Integer SORT_ID = new Integer(1);

    /**
     * <p>Indicates a list of messages should be sorted by the message
     * text.</p>
     */
    public static final Integer SORT_TEXT = new Integer(2);

    /**
     * <p>Indicates a list of messages should be sorted by the message
     * subject.</p>
     */
    public static final Integer SORT_SUBJECT = new Integer(3);

    /**
     * <p>Indicates a list of messages should be sorted by the message
     * recipients.</p>
     */
    public static final Integer SORT_RECIPIENTS = new Integer(4);

    /**
     * <p>Indicates a list of messages should be sorted by the "Carbon
     * Copy" recipients.</p>
     */
    public static final Integer SORT_RECIPIENTS_CC = new Integer(5);

    /**
     * <p>Indicates a list of messages should be sorted by the "Blind
     * Carbon Copy" recipients.</p>
     */
    public static final Integer SORT_RECIPIENTS_BCC = new Integer(6);

    /**
     * <p>Indicates a list of messages should be sorted by the message
     * sent date.</p>
     */
    public static final Integer SORT_SENT = new Integer(7);

    /**
     * <p>Indicates a list of messages should be sorted by the message
     * senders.</p>
     */
    public static final Integer SORT_SENDERS = new Integer(8);

    /**
     * <p>Indicates a list of messages should be sorted by the message
     * received date.</p>
     */
    public static final Integer SORT_RECEIVED = new Integer(9);

    /**
     * <p>Indicates a list of messages should be sorted by the message
     * size in bytes.</p>
     */
    public static final Integer SORT_SIZE = new Integer(10);
}

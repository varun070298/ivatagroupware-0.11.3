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
 * $Log: RightConstants.java,v $
 * Revision 1.2  2005/04/09 17:19:07  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:35  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/03/21 21:16:06  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:57:51  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.7  2003/08/19 14:53:30  jano
 * *** empty log message ***
 *
 * Revision 1.6  2003/08/15 14:04:05  peter
 * directory constant fixed
 *
 * Revision 1.5  2003/08/13 13:44:44  jano
 * mising DETAIL_DIRECTORY
 *
 * Revision 1.4  2003/08/13 13:35:53  jano
 * we don't need addressBook detail
 *
 * Revision 1.3  2003/07/31 08:48:30  jano
 * new detail for multiple addressBook
 *
 * Revision 1.2  2003/05/02 16:26:03  peter
 * added detail for handling users
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.7  2003/02/11 09:19:17  peter
 * *** empty log message ***
 *
 * Revision 1.6  2003/02/11 09:11:49  peter
 * added detail constants for settings
 *
 * Revision 1.5  2003/02/04 17:43:45  colin
 * copyright notice
 *
 * Revision 1.4  2002/07/12 09:42:04  colin
 * added comment right constant
 *
 * Revision 1.3  2002/06/28 13:15:23  colin
 * first addressbook release
 *
 * Revision 1.2  2002/06/17 10:45:45  colin
 * added constants for topic_item...
 *
 * Revision 1.1  2002/06/13 11:21:24  colin
 * first version with rose model integration.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.person.group.right;


/**
 * <p>Store all the constants for person group rights. You need to use these
 * constants to identify the 'detail' of right you are applying, or the access
 * value.</p>
 *
 * @since   2002-05-19
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public class RightConstants {

    /**
     * <p>This right allows a group to add, view or remove group memberships.</p>
     */
    public final static Integer DETAIL_PERSON_GROUP_MEMBER = new Integer(1);

    /**
     * <p>This right allows a group to add, view or remove library items, based
     * on their topic.</p>
     */
    public final static Integer DETAIL_LIBRARY_ITEM_TOPIC = new Integer(2);

    /**
     * <p>This right allows a group to add, amend or remove topics.</p>
     */
    public final static Integer DETAIL_LIBRARY_TOPIC = new Integer(3);

    /**
     * <p>This right allows a group to add, amend or remove comment, based on
     * the topics of their items.</p>
     */
    public final static Integer DETAIL_LIBRARY_COMMENT_TOPIC = new Integer(4);

    /**
     * <p>This right allows a group to amend a setting on user level.</p>
     */
    public final static Integer DETAIL_SETTING_USER = new Integer(5);

    /**
     * <p>This right allows a group to amend settings on system level.</p>
     */
    public final static Integer DETAIL_SETTING_SYSTEM = new Integer(6);

    /**
     * <p>This right allows a group to add to, amend and remove a directory.</p>
     */
    public final static Integer DETAIL_DIRECTORY = new Integer(8);

    /**
     * <p>Gives the group the right to view/select an element.</p>
     */
    public final static Integer ACCESS_VIEW = new Integer(0);

    /**
     * <p>Gives the group the right to add/insert an element.</p>
     */
    public final static Integer ACCESS_ADD = new Integer(1);

    /**
     * <p>Gives the group the right to amend/update an element.</p>
     */
    public final static Integer ACCESS_AMEND = new Integer(2);

    /**
     * <p>Gives the group the right to remove/delete an element.</p>
     */
    public final static Integer ACCESS_REMOVE = new Integer(3);
}

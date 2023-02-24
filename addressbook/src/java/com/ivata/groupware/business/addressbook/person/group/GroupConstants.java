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
 * $Log: GroupConstants.java,v $
 * Revision 1.5  2005/04/29 02:48:13  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.4  2005/04/28 18:47:10  colinmacleod
 * Fixed XHMTL, styles and resin compatibility.
 * Added support for URL rewriting.
 *
 * Revision 1.3  2005/04/10 20:09:36  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:07  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:31  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/07/13 19:41:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
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
 * Revision 1.3  2003/08/05 14:57:35  jano
 * addressBook extension
 *
 * Revision 1.2  2003/07/25 11:42:11  jano
 * adding functionality for addressBook extension
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.3  2003/02/04 17:43:45  colin
 * copyright notice
 *
 * Revision 1.2  2002/12/03 15:25:36  jano
 * we have new group - "USER" it's mandatory group
 *
 * Revision 1.1  2002/09/27 12:53:07  jano
 * constants of GROUP
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.person.group;

/**
 * <p>Store all the constants for groups.</p>
 *
 * @since   2002-09-26
 * @author  jano
 * @version $Revision: 1.5 $
 */
public class GroupConstants {

    /**
     * <p>Administrator group, this group can not be deleted.</p>
     */
    public final static Integer GROUP_ADMINISTRATOR = new Integer(1);
    /**
     * <p>User group. Each USER is int this group, this group can not be deleted.</p>
     */
    public final static Integer USER_GROUP = new Integer(2);

    /**
     * <p>each user has privete group, so those user groups are under this group</p>
     */
    public final static Integer USER_GROUP_PRIVATE = new Integer(3);

    /**
     * <p>This group contain addressBooks groups - private and public.</p>
     */
    public final static Integer ADDRESS_BOOK = new Integer(4);
    /**
     * <p>This group contain private addressBooks - one for each user.</p>
     */
    public final static Integer ADDRESS_BOOK_PRIVATE = new Integer(5);
    /**
     * <p>This group contain public addressBooks</p>
     */
    public final static Integer ADDRESS_BOOK_PUBLIC = new Integer(6);
    /**
     * <p>This is a default address book, present when the system is initially
     * created. Everyone can see this address book.</p>
     */
    public final static Integer ADDRESS_BOOK_DEFAULT = new Integer(7);
    public static boolean equals(
            final Integer compareID,
            final Integer constantGroupID) {
        // none of the constants are null!
        assert (constantGroupID != null);
        if (compareID == null) {
            return false;
        }
        return constantGroupID.equals(compareID);
    }
}

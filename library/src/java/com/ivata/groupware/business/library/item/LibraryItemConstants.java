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
 * $Log: LibraryItemConstants.java,v $
 * Revision 1.3  2005/04/10 20:31:59  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:45  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:59  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:58:39  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/02/24 19:09:22  colin
 * moved to business
 *
 * Revision 1.3  2003/02/04 17:43:47  colin
 * copyright notice
 *
 * Revision 1.2  2002/07/01 08:12:09  colin
 * made constants Integers rather than ints
 *
 * Revision 1.1  2002/06/18 11:40:13  colin
 * first version of library
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.item;


/**
 * <p>Stores constant values for the {@link LibraryItemBean library item}.</p>
 *
 * @since   2002-06-14
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 * @see     LibraryItemBean
 */
public class LibraryItemConstants {
    /**
 * <p>Represents a general document or report type.</p>
 */
    public final static Integer ITEM_DOCUMENT = new Integer(0);

    /**
 * <p>Represents a memo from one person to a group.</p>
 */
    public final static Integer ITEM_MEMO = new Integer(1);

    /**
 * <p>Represents frequently asked questions.</p>
 */
    public final static Integer ITEM_FAQ = new Integer(2);

    /**
 * <p>Represents minutes/agenda of a meeting.</p>
 */
    public final static Integer ITEM_MEETING = new Integer(3);

    /**
 * <p>Represents a short note, with just a summary.</p>
 */
    public final static Integer ITEM_NOTE = new Integer(4);

    /**
 * <p>Represents a news item, usually linking externally.</p>
 */
    public final static Integer ITEM_NEWS = new Integer(5);
}

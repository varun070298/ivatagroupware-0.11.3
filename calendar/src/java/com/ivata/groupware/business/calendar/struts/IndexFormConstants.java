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
 * $Log: IndexFormConstants.java,v $
 * Revision 1.2  2005/04/09 17:19:18  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:47:42  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/03/21 21:16:22  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:07:29  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:22  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 13:57:23  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/02/24 19:09:22  colin
 * moved to business
 *
 * Revision 1.2  2003/02/13 08:45:42  colin
 * conversion to Struts/popups
 *
 * Revision 1.1  2003/02/04 17:50:17  colin
 * first version in CVS
 *
 * Revision 1.1  2003/02/04 17:41:16  colin
 * first version in CVS
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar.struts;


/**
 * <p>Constants used to identify view styles for calendar.</p>
 *
 * @since 2003-02-02
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 *
 */
public class IndexFormConstants {
    /**
     * <p>Represents day view.</p>
     */
    public static final Integer VIEW_DAY = new Integer(0);
    /**
     * <p>Represents working week, or 5 day view.</p>
     */
    public static final Integer VIEW_WORK_WEEK = new Integer(1);
    /**
     * <p>Represents week, or 7 day view.</p>
     */
    public static final Integer VIEW_WEEK = new Integer(2);
    /**
     * <p>Represents month view.</p>
     */
    public static final Integer VIEW_MONTH = new Integer(3);
    /**
     * <p>Represents year view.</p>
     */
    public static final Integer VIEW_YEAR = new Integer(4);
    /**
     * <p>Represents a list view displaying recent and future events as a list.</p>
     */
    public static final Integer VIEW_LIST = new Integer(15);

}

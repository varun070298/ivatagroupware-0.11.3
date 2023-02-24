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
 * $Log: MenuConstants.java,v $
 * Revision 1.3  2005/04/10 20:10:05  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:58  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:32  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/09/30 15:16:04  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.3  2004/03/21 21:16:19  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:34  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:59  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 13:49:57  colin
 * fixing fo Xdoclet
 *
 * Revision 1.3  2003/02/24 19:27:17  colin
 * restructured file paths
 *
 * Revision 1.2  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.1  2002/07/02 14:57:54  colin
 * tried to fix jbuilder EJB designer
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.navigation.menu;




/**
 * <p>Constants applicable to the menu navigations tools in the system.</p>
 *
 * @since 2002-07-01
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 * @see MenuBean
 */
public class MenuConstants {
    /**
     * <p>Defines dynamic short-cuts which move when you are over them.</p>
     */
    public final static Integer SHORTCUT_STYLE_DYNAMIC = new Integer(0);
    /**
     * <p>Defines short-cuts when you have to click on them to change menu.</p>
     */
    public final static Integer SHORTCUT_STYLE_CLICK = new Integer(1);
    /**
     * <p>Defines short-cuts where all the menues are always open.</p>
     */
    public final static Integer SHORTCUT_STYLE_OPEN = new Integer(2);
    /**
     * Id of the main menu.
     */
    public final static String ID_MAIN = "-1";
    /**
     * Id of the favorites menu.
     */
    public final static String ID_FAVORITES = "0";
}

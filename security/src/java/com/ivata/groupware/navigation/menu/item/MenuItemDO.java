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
 * $Log: MenuItemDO.java,v $
 * Revision 1.3  2005/04/10 20:10:06  colinmacleod
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
 * Revision 1.1  2004/07/13 19:41:16  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
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
 * Revision 1.5  2003/11/03 11:28:25  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/17 12:36:13  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.3  2003/10/15 13:56:20  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:49:57  colin
 * fixing fo Xdoclet
 *
 * Revision 1.10  2003/05/01 12:15:43  jano
 * tidy up names of sequeneces
 *
 * Revision 1.9  2003/02/25 14:38:14  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.8  2003/02/24 19:27:17  colin
 * restructured file paths
 *
 * Revision 1.7  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.6  2002/09/11 15:00:29  colin
 * update for adding new menu items
 *
 * Revision 1.5  2002/08/19 13:35:02  colin
 * added image
 *
 * Revision 1.4  2002/07/04 12:29:28  jano
 * i put readonly script to CVS and i will commit all SRC directory
 *
 * Revision 1.3  2002/06/28 13:31:20  colin
 * changed create method.
 *
 * Revision 1.2  2002/06/17 07:29:08  colin
 * improved and extended javadoc documentation
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.navigation.menu.item;


import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.container.persistence.BaseDO;
import com.ivata.groupware.navigation.menu.MenuDO;


/**
 * <p><code>EntityBean</code> to store items in the users menu system.</p>
 *
 * @since 2002-05-07
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 * @see com.ivata.groupware.menu.MenuBean
 *
 * @hibernate.class
 *      table="navigation_menu_item"
 * @hibernate.cache
 *      usage="read-write"
 */
public class MenuItemDO extends BaseDO {

    /**
     * <p>Image associated with the item.</p>
     */
    private String image;

    /**
     * <p>
     * Menu which contains this item.
     * </p>
     */
    private MenuDO menu;
    /**
     * <p>Order in which this menu item should appear, in relation to the other
     * menu items.<p>
     */
    private Integer priority;

    /**
     * <p>Get the text or caption which should appear in this menu.</p>
     */
    private String text;


    /**
     * <p>Get the <code>URL</code> to link this menu item to.</p>
     */
    private String URL;

    /**
     * <p>User associated with this menu item.</p>
     */
    private UserDO user;

    /**
     * <p>Get the image associated with the item.</p>
     *
     * @return the filename (without path) of an image associated with this
     * item, or <code>null</code> if no image is currently associated with this
     * menu item.
     * @hibernate.property
     */
    public final String getImage() {
        return image;
    }

    /**
     * <p>
     * Menu which contains this item.
     * </p>
     *
     * @return current value of menu.
     * @hibernate.many-to-one
     */
    public final MenuDO getMenu() {
        return menu;
    }

    /**
     * <p>Get the order in which this menu item should appear, in relation to the other
     * menu items.<p>
     *
     * @return the order in which this menu item should appear, in relation
     * to the other menu items. Lower values of this number will appear lower in the
     * list of menu items (and are therefore more significant).
     * @hibernate.property
     */
    public final Integer getPriority() {
        return priority;
    }
    /**
     * <p>Get the text or caption which should appear in this menu.</p>
     *
     * @return the text or caption which should appear in this menu
     * @hibernate.property
     */
    public final String getText() {
        return text;
    }

    /**
     * <p>Get the <code>URL</code> to link this menu item to.</p>
     *
     * @return the <code>URL</code> to link this menu item to
     * @hibernate.property
     */
    public final String getURL() {
        return URL;
    }
    /**
     * <p>Get the user associated with this menu item.</p>
     *
     * @return the user associated with this item, or <code>null</code> if
     * the item should appear in all users' menues.
     *
     * @hibernate.many-to-one
     *      column="person_user"
     */
    public UserDO getUser() {
        return user;
    }

    /**
     * <p>Set the image associated with the item.</p>
     *
     * @param image the filename (without path) of an image associated with this
     * item, or <code>null</code> if no image is currently associated with this
     * menu item.
     */
    public final void setImage(final String image) {
        this.image = image;
    }
    /**
     * <p>
     * Menu which contains this item.
     * </p>
     *
     * @param menu new value of menu.
     */
    public final void setMenu(final MenuDO menu) {
        this.menu = menu;
    }

    /**
     * <p>Set the order in which this menu item should appear, in relation to the other
     * menu items.<p>
     *
     * @param priority the order in which this menu item should appear, in relation
     * to the other menu items. Lower values of this number will appear lower in the
     * list of menu items (and are therefore more significant).
     */
    public final void setPriority(final Integer priority) {
        this.priority = priority;
    }

    /**
     * <p>Set the text or caption which should appear in this menu.</p>
     *
     * @param text the text or caption which should appear in this menu.
     */
    public final void setText(final String text) {
        this.text = text;
    }

    /**
     * <p>Set the <code>URL</code> to link this menu item to.</p>
     *
     * @param URL the <code>URL</code> to link this menu item to.
     */
    public final void setURL(final String URL) {
        this.URL = URL;
    }

    /**
     * <p>Set the user associated with this menu item.</p>
     *
     * @param user the user associated with this item, or <code>null</code> if
     * the item should appear in all users' menues.
     */
    public final void setUser(final UserDO user) {
        this.user = user;
    }
}

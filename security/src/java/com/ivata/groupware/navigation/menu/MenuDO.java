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
 * $Log: MenuDO.java,v $
 * Revision 1.4  2005/04/29 02:48:20  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
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
 * Revision 1.4  2004/07/13 19:41:16  colinmacleod
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
 * Revision 1.2  2003/10/15 13:49:57  colin
 * fixing fo Xdoclet
 *
 * Revision 1.7  2003/02/24 19:27:17  colin
 * restructured file paths
 *
 * Revision 1.6  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.5  2002/09/27 18:15:32  peter
 * vector of menuItem ids added to menuDO and the appropriate changes to Menubean
 *
 * Revision 1.4  2002/09/11 15:00:05  colin
 * improved documentation
 *
 * Revision 1.3  2002/08/19 13:48:58  colin
 * added image to menu items
 *
 * Revision 1.2  2002/06/28 13:29:54  colin
 * added comparison method.
 *
 * Revision 1.1  2002/06/13 11:21:57  colin
 * first version with rose model integration.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.navigation.menu;


import java.util.Set;

import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p><code>EntityBean</code> to store the users menu system.</p>
 *
 * <p>This is a dependent value class, used to pass data back from the.</p>
 * {@link MenuBean MenuBean} to a client application.</p>
 *
 * <p><strong>Note:</strong> This class provides data from {@link MenuBean MenuBean}.
 * This is no local copy of the bean class, however, and changes here
 * will not be automatically reflected in {@link MenuBean MenuBean}.</p>
 *
 * @since 2002-05-07
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 * @see MenuBean
 *
 * @hibernate.class
 *      table="navigation_menu"
 * @hibernate.cache
 *      usage="read-write"
 */
public class MenuDO extends BaseDO {

    /**
     * <p>All the menu items under this menu.</p>
     */
    private Set items;
    /**
     * <p>Order in which this menu should appear, in relation to the other
     * menus.<p>
     */
    private Integer priority;
    /**
     * <p>Text or caption which should appear on this menu.</p>
     */
    private String text;

    /**
     * <p>Comparison method. See if the object supplied is a menu dependent
     * object and, if so, whether or not its contents are the same as this one.
     * Only the <code>id</code> fields are compared.</p>
     *
     * @param compare the object to compare with this one.
     * @return <code>true</code> if the object supplied in <code>compare</code>
     *     is effectively the same as this one, otherwise false.
     */
    public boolean equals(final Object compare) {
        // first check it is non-null and the class is right
        if ((compare == null) ||
            !(this.getClass().isInstance(compare))) {
            return false;
        }
        MenuDO menuDO = (MenuDO) compare;
        Integer id = getId();
        Integer menuDOId = menuDO.getId();

        // check that the ids are the same
        return (((id == null) ?
                    (menuDOId == null) :
                    id.equals(menuDOId)));
    }
    /**
     * <p>Get all the menu items under this menu.</p>
     *
     * @return all items in this menu as a <code>Collection</code> of
     * <code>MenuItemDO</code> instances.
     *
     * @hibernate.set
     *      sort="com.ivata.groupware.navigation.menu.item.MenuItemComparator"
     * @hibernate.collection-key
     *      column="menu"
     * @hibernate.collection-one-to-many
     *      class="com.ivata.groupware.navigation.menu.item.MenuItemDO"
     */
    public final Set getItems() {
        return items;
    }
    /**
     * <p>Get the order in which this menu should appear, in relation to the other
     * menus.<p>
     *
     * @return the order in which this menu should appear, in relation
     * to the other menus. Lower values of this number will appear lower in the
     * Set of menus (and are therefore more significant).
     * @hibernate.property
     */
    public final Integer getPriority() {
        return priority;
    }

    /**
     * <p>Get the text or caption which should appear on this menu.</p>
     *
     * @return the text or caption which should appear on this menu
     * @hibernate.property
     */
    public final String getText() {
        return text;
    }

    /**
     * <p>Set all the menu items under this menu.</p>
     *
     * @param items all items in this menu as a <code>Collection</code> of
     * <code>MenuItemDO</code> instances.
     */
    public final void setItems(final Set items) {
        this.items = items;
    }
    /**
     * <p>Set the order in which this menu should appear, in relation to the other
     * menus.<p>
     *
     * @param priority the order in which this menu should appear, in relation
     * to the other menus. Lower values of this number will appear lower in the
     * list of menus (and are therefore more significant).
     */
    public final void setPriority(final Integer priority) {
        this.priority = priority;
    }

    /**
     * <p>Set the text or caption which should appear on this menu.</p>
     *
     * @param text the text or caption which should appear on this menu
     */
    public final void setText(final String text) {
        this.text = text;
    }

}

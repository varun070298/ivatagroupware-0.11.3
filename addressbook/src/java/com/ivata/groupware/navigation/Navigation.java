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
 * $Log: Navigation.java,v $
 * Revision 1.3  2005/04/10 20:09:39  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:09  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:35  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/11/12 18:17:25  colinmacleod
 * Ordered imports.
 *
 * Revision 1.2  2004/11/12 15:57:07  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/07/13 19:41:16  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.navigation;

import java.util.Collection;

import javax.ejb.EJBException;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.navigation.menu.item.MenuItemDO;
import com.ivata.mask.util.SystemException;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Apr 14, 2004
 * @version $Revision: 1.3 $
 */
public interface Navigation {
    /**
     * <p>Add a new menu item, with no image associated with it initially.</p>
     *
     * @param userName the user for whom to insert the new menu item, or
     * <code>null</code> if everyone should see it.
     * @param menuId the unique identifier of the menu into which the new item
     * will be inserted.
     * @param text human-readable english language text for the menu item.
     * Should be unique within the menu it is in though this is not enforced
     * server-side.
     * @param URL the <code>URL</code> the new menu item links to.
     *
     * @ejb.interface-method
     *      view-type="remote"
     */
    public abstract void addMenuItem(
        SecuritySession securitySession,
        MenuItemDO menuItem)
        throws SystemException;
    /**
     * <p>changes a menu item, if it belongs to the given user</p>
     *
     * @param menuItemId the unique identifier of the menu item to change.
     * @param text human-readable english language text for the menu item.
     * Should be unique within the menu it is in though this is not enforced
     * server-side.
     * @param URL the <code>URL</code> the new menu item links to.
     * @param userName the user for whom the menu item should belong
     *
     * @ejb.interface-method
     *      view-type="remote"
     */
    public abstract void amendMenuItem(
        SecuritySession securitySession,
        MenuItemDO menuItem)
        throws SystemException;
    /**
     * <p>Find all the menues for a given user.</p>
     *
     * @param userName the user to search for
     * @return a <code>Collection</code>Containing all the user's menues, as
     * instances of {@link com.ivata.groupware.menu.MenuDO MenuDO}
     * @see com.ivata.groupware.menu.MenuDOHome#findByUserName( String sUserName )
     * @throws EJBException if there is a <code>FinderException</code> calling
     * <code>MenuDOHome.findByUserName</code>
     * @throws EJBException if there is a <code>NamingException</code> setting
     * looking up the MenuHome
     *
     * @ejb.interface-method
     *      view-type="remote"
     */
    public abstract Collection findMenues(SecuritySession securitySession)
        throws SystemException;
    /**
     * <p>removes a menu item, if it belongs to the given user</p>
     *
     * @param menuItemId the unique identifier of the menu item to remove.
     * @param userName the user for whom the menu item should belong
     *
     * @ejb.interface-method
     *      view-type="remote"
     */
    public abstract void removeMenuItem(
        SecuritySession securitySession,
        String menuItemId)
        throws SystemException;
}
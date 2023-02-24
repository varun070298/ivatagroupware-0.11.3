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
 * $Log: NavigationImpl.java,v $
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
 * Revision 1.4  2004/11/12 18:17:11  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 15:57:07  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/07/19 18:17:34  colinmacleod
 * Made synchronized collection.
 *
 * Revision 1.1  2004/07/13 19:41:16  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.navigation;


import java.util.Collection;
import java.util.Collections;

import javax.ejb.EJBException;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.BusinessLogic;
import com.ivata.groupware.container.persistence.QueryPersistenceManager;
import com.ivata.groupware.navigation.menu.item.MenuItemDO;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.util.SystemException;


/**
 * <p>Lets you access the menues and folders in the system and generally get
 * around.</p>
 *
 * @since 2002-05-07
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class NavigationImpl extends BusinessLogic implements Navigation {
    /**
     * Persistence manger used to store/retrieve data objects, or retrieve a
     * new persistence session.
     */
    private QueryPersistenceManager persistenceManager;

    /**
     * Construct and initialize the navigation implementation.
     *
     * @param persistenceManager persistence manager used to store/retrieve data
     *     objects.
     */
    public NavigationImpl(QueryPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

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
    public void addMenuItem(final SecuritySession securitySession,
            final MenuItemDO menuItem)
            throws SystemException {
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            persistenceManager.add(persistenceSession, menuItem);
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }

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
    public void amendMenuItem(final SecuritySession securitySession,
            final MenuItemDO menuItem)
            throws SystemException {
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            persistenceManager.amend(persistenceSession, menuItem);
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }

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
    public Collection findMenues(final SecuritySession securitySession)
            throws SystemException {
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            return Collections.synchronizedCollection(persistenceManager.find(
                    persistenceSession,
                    "navigationMenuByUserNameOrderByPriority",
                    new Object[] {securitySession.getUser().getName()}));
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }

    /**
     * <p>removes a menu item, if it belongs to the given user</p>
     *
     * @param menuItemId the unique identifier of the menu item to remove.
     * @param userName the user for whom the menu item should belong
     *
     * @ejb.interface-method
     *      view-type="remote"
     */
    public void removeMenuItem(final SecuritySession securitySession,
            final String menuItemId)
            throws SystemException {
        PersistenceSession persistenceSession =
            persistenceManager.openSession(securitySession);
        try {
            persistenceManager.remove(persistenceSession,
                    MenuItemDO.class, menuItemId);
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }
}

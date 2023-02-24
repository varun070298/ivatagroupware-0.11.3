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
 * $Log: NavigationBean.java,v $
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
 * Revision 1.6  2004/11/12 18:17:10  colinmacleod
 * Ordered imports.
 *
 * Revision 1.5  2004/11/12 15:57:07  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.4  2004/07/13 19:41:16  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:18  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:34  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:58  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.5  2003/11/03 11:28:24  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/17 12:36:13  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.3  2003/10/15 13:55:15  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:49:57  colin
 * fixing fo Xdoclet
 *
 * Revision 1.12  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.11  2003/02/24 19:27:17  colin
 * restructured file paths
 *
 * Revision 1.10  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.9  2002/09/30 08:26:32  peter
 * fixed 2 errors when testing for user equality
 *
 * Revision 1.8  2002/09/30 07:53:52  peter
 * added amendMenuItem and removeMenuItem methods
 *
 * Revision 1.7  2002/09/11 14:59:14  colin
 * added code to add a menu item
 *
 * Revision 1.6  2002/08/11 11:46:24  colin
 * Changed isFolderOpen return from boolean to Boolean.
 * This allows us to check if it has been set yet or not.
 *
 * Revision 1.5  2002/06/28 13:29:01  colin
 * improved menu sorting routine so that texts are sorted if the priorities are the same.
 *
 * Revision 1.4  2002/06/21 13:10:48  colin
 * restructured com.ivata.groupware.web
 *
 * Revision 1.3  2002/06/17 07:28:57  colin
 * improved and extended javadoc documentation
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.navigation;


import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.groupware.navigation.menu.item.MenuItemDO;
import com.ivata.mask.util.SystemException;


/**
 * <p><code>SessionBean</code> to let you access the menues and folders in
 * the system and generally get around.</p>
 *
 * @since 2002-05-07
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @ejb.bean
 *      name="Navigation"
 *      display-name="Navigation"
 *      type="Stateless"
 *      view-type="remote"
 *      jndi-name="NavigationRemote"
 *
 * @ejb.transaction
 *      type = "Required"
 *
 *  @ejb.home
 *      generate="false"
 *      remote-class="com.ivata.groupware.navigation.NavigationRemoteHome"
 *
 *  @ejb.interface
 *      remote-class="com.ivata.groupware.navigation.NavigationRemote"
 */
public class NavigationBean implements SessionBean, Navigation {


    /**
     * <p>Provides the session bean with container-specific information.</p>
     */
    SessionContext sessionContext;

    /**
     * <p>Add a new menu item, with no image associated with it initially.</p>
     *
     * @param menuId the unique identifier of the menu into which the new item
     * will be inserted.
     * @param text human-readable english language text for the menu item.
     * Should be unique within the menu it is in though this is not enforced
     * server-side.
     * @param URL the <code>URL</code> the new menu item links to.
     * @param userName the user for whom to insert the new menu item, or
     * <code>null</code> if everyone should see it.
     *
     * @ejb.interface-method
     *      view-type="remote"
     */
    public void addMenuItem(final SecuritySession securitySession,
            final MenuItemDO menuItem)
            throws SystemException {
        getNavigation().addMenuItem(securitySession, menuItem);
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
        getNavigation().addMenuItem(securitySession, menuItem);
    }

    /**
     * <p>Called by the container to notify a stateful session object it has been
     * activated.</p>
     */
    public void ejbActivate() {}

    /**
     * <p>Provides the session bean with container-specific information.</p>
     *
     * @ejb.create-method
     */
    public void ejbCreate() throws CreateException {}

    /**
     * <p>Called by the container to notify a stateful session object it will be
     * deactivated. Called just before deactivation.</p>
     */
    public void ejbPassivate() {}

    /**
     * <p>This method is called by the container when the session bean is about
     * to be removed.</p>
     *
     * <p>This method will be called after a client calls the <code>remove</code>
     * method of the remote/local home interface.</p>
     */
    public void ejbRemove() {}

    /**
     * <p>Find all the menues for a given user.</p>
     *
     * @param userName the user to search for
     * @return a <code>Collection</code>Containing all the user's menues, as
     * instances of {@link com.ivata.groupware.menu.MenuDO MenuDO}
     * @see com.ivata.groupware.menu.MenuLocalHome#findByUserName( String sUserName )
     * @throws EJBException if there is a <code>FinderException</code> calling
     * <code>MenuLocalHome.findByUserName</code>
     * @throws EJBException if there is a <code>NamingException</code> setting
     * looking up the MenuHome
     *
     * @ejb.interface-method
     *      view-type="remote"
     */
    public Collection findMenues(final SecuritySession securitySession)
            throws SystemException {
        return getNavigation().findMenues(securitySession);
    }
    /**
     * Get the navigation implementation.
     *
     * @return valid security implementation.
     */
    private Navigation getNavigation() throws SystemException {
        PicoContainer container = PicoContainerFactory.getInstance()
            .getGlobalContainer();
        return (Navigation) container.getComponentInstance(Navigation.class);
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
            final String id)
            throws SystemException {
        getNavigation().removeMenuItem(securitySession, id);
    }

    /**
     * <p>Provides access to the runtime properties of the context in which this
     * session bean is running.</p>
     *
     * <p>Is usually stored by the bean internally.</p>
     *
     * @param sessionContext new value for the session context. Is usually stored internally
     */
    public final void setSessionContext(final SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}

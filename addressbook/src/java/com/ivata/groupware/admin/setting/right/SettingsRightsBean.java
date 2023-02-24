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
 * $Log: SettingsRightsBean.java,v $
 * Revision 1.3  2005/04/10 20:09:33  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:05  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:38  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.7  2004/11/12 18:17:09  colinmacleod
 * Ordered imports.
 *
 * Revision 1.6  2004/11/12 15:56:46  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.5  2004/07/13 19:41:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/02/10 19:57:20  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:46  colinmacleod
 * Moved open portal to sourceforge.
 *
 * Revision 1.5  2003/11/03 11:28:24  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.3  2003/10/15 13:08:06  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:04:21  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.setting.right;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.SystemException;


/**
 * <p>This session bean manages rigths for changing settings.</p>
 *
 * @since 2003-02-11
 * @author Peter Illes
 * @version $Revision: 1.3 $
 *
 * @ejb.bean
 *      name="SettingsRights"
 *      display-name="SettingRights"
 *      type="Stateless"
 *      view-type="both"
 *      local-jndi-name="SettingsRightsLocal"
 *      jndi-name="SettingsRightsRemote"
 *
 *
 *  @ejb.home
 *      generate="false"
 *      remote-class="com.ivata.groupware.admin.setting.right.SettingsRightsRemoteHome"
 *
 *  @ejb.interface
 *      remote-class="com.ivata.groupware.admin.setting.right.SettingsRightsRemote"
 */
public class SettingsRightsBean implements SessionBean {


    /**
     * <p>Provides the session bean with container-specific information.</p>
     */
    SessionContext sessionContext;

    /**
     * <p>this method sets a setting to be allowed for users to override the
     * system value</p>, {see canAmendSetting(name)}
     * @param name the name of the setting
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public void addAmendRightForSetting(final SecuritySession securitySession,
            final String settingName)
            throws SystemException {
        getSettingsRights().addAmendRightForSetting(securitySession, settingName);
    }

    /**
     * <p>the method finds out whether a setting can be changed (overriden) by
     * a user</p>
     * @param name the name of the setting
     * @return <code>true</code> when this setting can be overridden by user,
     * <code>false</code> otherwise
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public boolean canAmendSetting(final SecuritySession securitySession,
            final String settingName)
            throws SystemException {
        return getSettingsRights().canAmendSetting(securitySession, settingName);
    }

    /**
     * <p>the method tells whether a user can amend system settings</p>
     * @param userName the name of the user
     * @return <code>true</code> when this user can amend system settings or
     * <code>false</code> when he can't
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public boolean canAmendSystemSettings(final SecuritySession securitySession)
            throws SystemException {
        return getSettingsRights().canAmendSystemSettings(securitySession);
    }

    /**
     * <p>Called by the container to notify an entity object it has been
     * activated.</p>
     */
    public void ejbActivate() {}

    /**
     * <p>Called by the container just after the bean has been created.</p>
     *
     * @throws CreateException if any error occurs. Never thrown by this class.
     *
     * @ejb.create-method
     */
    public void ejbCreate() throws CreateException {}

    /**
     * <p>Called by the container to notify the entity object it will be
     * deactivated. Called just before deactivation.</p>
     */
    public void ejbPassivate() {}

    /**
     * <p>This method is called by the container when the bean is about
     * to be removed.</p>
     *
     * <p>This method will be called after a client calls the <code>remove</code>
     * method of the remote/local home interface.</p>
     *
     * @throws RemoveException if any error occurs. Currently never thrown by
     *     this class.
     */
    public void ejbRemove() {}
    /**
     * Get the settings rights implementation.
     *
     * @return valid settings rights implementation.
     */
    private SettingsRights getSettingsRights() throws SystemException {
        PicoContainer container = PicoContainerFactory.getInstance()
            .getGlobalContainer();
        return (SettingsRights) container.getComponentInstance(SettingsRights.class);
    }

    /**
     * <p>this method disables overriding the system value of one setting,
     * {see canAmendSetting(name)}
     * @param name the name of the setting
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public void removeAmendRightForSetting(final SecuritySession securitySession,
            final String settingName)
            throws SystemException {
        getSettingsRights().removeAmendRightForSetting(securitySession, settingName);
    }

    /**
     * <p>Set up the context for this entity object. The session bean stores the
     * context for later use.</p>
     *
     * @param sessionContext the new context which the session object should store.
     */
    public final void setSessionContext(final SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}

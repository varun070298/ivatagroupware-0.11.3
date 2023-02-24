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
 * $Log: SettingsBean.java,v $
 * Revision 1.3  2005/04/10 20:10:04  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:57  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:36  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/11/12 18:16:07  colinmacleod
 * Ordered imports.
 *
 * Revision 1.2  2004/11/12 15:57:19  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/09/30 15:15:59  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.4  2004/07/13 19:54:31  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:05  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:46  colinmacleod
 * Moved ivata openportal to SourceForge..
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
 * Revision 1.3  2003/10/15 13:15:08  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:04:20  colin
 * fixing for XDoclet
 *
 * Revision 1.6  2003/03/27 16:06:25  peter
 * getSetting methods are user-override aware
 *
 * Revision 1.5  2003/03/05 17:34:13  peter
 * fixed wrong message name in validation
 *
 * Revision 1.4  2003/03/05 11:53:53  peter
 * fixed fieldName messages in validation
 *
 * Revision 1.3  2003/02/25 16:58:31  peter
 * fixed the logic in setSetting method
 *
 * Revision 1.2  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.1  2003/02/24 18:56:14  colin
 * added to admin
 *
 * Revision 1.18  2003/02/24 09:50:39  peter
 * the enable field is boolean now
 *
 * Revision 1.17  2003/02/19 18:21:35  peter
 * validation now covers all settings show to user worth validation
 *
 * Revision 1.16  2003/02/11 10:28:44  peter
 * added isSettingEnabled method
 *
 * Revision 1.15  2003/02/07 14:40:45  peter
 * added new constants and parameter to validate
 *
 * Revision 1.14  2003/02/05 18:14:12  peter
 * added validate method to SettingsBean
 *
 * Revision 1.13  2003/02/05 11:58:06  peter
 * added getSettingType to SettingsBean
 *
 * Revision 1.12  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.11  2003/02/04 13:43:37  peter
 * newly created settings had no names assigned, fixed
 *
 * Revision 1.10  2003/02/04 10:25:44  peter
 * only user settings can be removed via setSetting
 *
 * Revision 1.9  2003/02/04 08:01:02  peter
 * modified to handle null userDO booth in getSetting and setSetting
 *
 * Revision 1.8  2002/11/12 09:37:08  colin
 * added SettingNullException when a setting doesn't exist
 *
 * Revision 1.7  2002/09/25 07:08:12  peter
 * fixed a bug in setSetting (type of the setting)
 *
 * Revision 1.6  2002/08/15 14:29:52  peter
 * removed initial context instances
 *
 * Revision 1.5  2002/08/11 11:49:04  colin
 * Improved error handling and output messages for non-existant settings
 *
 * Revision 1.4  2002/06/17 07:29:02  colin
 * improved and extended javadoc documentation
 *
 * moved setting constants into separate class
 *
 * Revision 1.1  2002/04/27 17:36:28  colin
 * first compliling version in EJB/JBuilder project
 *
 * Revision 1.1.1.1  2002/04/22 13:51:47  colin
 * EJB version of the intranet project
 *
 * Revision 1.5  2002/02/03 19:54:25  colin
 * linked settings to the database, rather than hard-coding in
 * com.ivata.groupware.admin.settings
 *
 * Revision 1.4  2002/02/03 16:49:45  colin
 * updated javadoc
 *
 * Revision 1.3  2002/02/02 21:23:01  colin
 * major restructuring to make the Settings class more generic
 * all default settings are now taken from the database rather than being
 * hard coded in the settings class
 * settings are stored in a HashMap in settings
 *
 * Revision 1.2  2002/01/20 19:28:25  colin
 * added tab and tree tags
 * implemented address book functionality
 *
 * Revision 1.1.1.1  2001/10/06 15:40:20  colin
 * initial import into cvs
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.setting;

import java.util.Locale;
import java.util.Map;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationErrors;


/**
 * <p>This class controls/sets the global preferences. It's might make sense to
 * make it a stateful bean so it can contais a reference to the current
 * personUser</p>
 *
 * <p>This Session Bean has been extended from a class,
 * <code>com.ivata.groupware.admin.settings</code>, in the original JSP intranet
 * prototype.</p>
 *
 * @since 2001-09-06
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @ejb.bean
 *      name="Settings"
 *      display-name="Settings"
 *      type="Stateless"
 *      view-type="both"
 *      local-jndi-name="SettingsLocal"
 *      jndi-name="SettingsRemote"
 *
 * @ejb.transaction
 *      type = "Required"
 *
 *  @ejb.home
 *      generate="false"
 *      remote-class="com.ivata.groupware.admin.setting.SettingsRemoteHome"
 *
 *  @ejb.interface
 *      remote-class="com.ivata.groupware.admin.setting.SettingsRemote"
 */
public class SettingsBean implements SessionBean, Settings {


    /**
     * <p>Provides the session bean with container-specific information.</p>
     */
    SessionContext sessionContext;

    /**
     * <p>
     * Change the value of an existing setting.
     * </p>
     *
     * @param name the name of the setting to set
     * @param value new value to be set.
     * @param user if not <code>null</code>, then the setting for this user
     * is set, otherwise the general setting is changed.
     */
    public void amendSetting(final SecuritySession securitySession,
            final String name,
            final Object value,
            final UserDO user) throws SystemException {
        getSettings().amendSetting(securitySession, name, value, user);
    }

    /**
     * <p>Called by the container to notify a stateful session object it has been
     * activated.</p>
     */
    public void ejbActivate() {}

    /**
     * <p>Mandatory, parameter-less <code>ejbCreate</code> is required by the
     * stateless bean interface.</p>
     *
     * <p>This method will be called after a client calls the <code>create</code>
     * of the remote/local home interface.</p>
     *
     * @ejb.create-method
     */
    public void ejbCreate() {}

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
     * <p>Get a setting of class <code>Boolean</code>.</p>
     *
     * @return the setting if set, or null if not
     * @param name the name of the setting to return the value for
     * @param userDO the user to search for. If null is specified, the
     *     default setting is searched for and returned if found.
     * @exception SettingsDataTypeException if the setting has any class other
     *     than Boolean
     * @return a setting of class <code>Boolean</code> for the setting
     *      name provided.
     * @see #getSetting
     * @see #getIntegerSetting
     * @see #getStringSetting
     * @throws SettingNullException if this setting does not exist.
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public final Boolean getBooleanSetting(final SecuritySession securitySession,
            final String name,
            final UserDO userDO) throws SystemException {
        return getSettings().getBooleanSetting(securitySession, name, userDO);
    }

    /**
     * <p>Get a setting of class <code>Integer</code>.</p>
     *
     * @exception SettingsDataTypeException if the setting has any class other
     *     than Integer.
     * @return the setting if set, or null if not
     * @param name the name of the setting to return the value for.
     * @param userDO the user to search for. If null is specified, the
     *     default setting is searched for and returned if found.
     * @return a setting of class <code>Integer</code> for the setting
     *      name provided.
     * @see #getSetting
     * @see #getStringSetting
     * @see #getBooleanSetting
     * @throws SettingNullException if this setting does not exist.
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public final Integer getIntegerSetting(final SecuritySession securitySession,
            final String name,
            final UserDO userDO) throws SystemException {
        return getSettings().getIntegerSetting(securitySession, name, userDO);
    }

    /**
     * <p>Get a setting for a given user. The class of the returned object will
     * depend on the <code>type</code> field of the EJB with this name and can
     * be one of:</br/>
     * <ul>
     * <li><code>Integer</code></li>
     * <li><code>String</code></li>
     * <li><code>Boolean</code></li></p>
     *
     * @return the setting if set, or null if not
     * @param name the name of the setting to return the value for
     * @param userDO the user to search for. If null is specified, the
     *     default setting is searched for and returned if found.
     * @return a setting with the setting name provided. The type of the
     *     returned object depends on the <code>type</code> field of the
     *     setting.
     * @see #getIntegerSetting
     * @see #getStringSetting
     * @see #getBooleanSetting
     * @throws SettingNullException if this setting does not exist.
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public final Object getSetting(final SecuritySession securitySession,
            final String name,
            final UserDO userDO) throws SystemException {
        return getSettings().getSetting(securitySession, name, userDO);
    }
    /**
     * Get the implementation which does all the hard work.
     */
    private Settings getSettings() throws SystemException {
        PicoContainer container = PicoContainerFactory.getInstance()
            .getGlobalContainer();
        return (Settings) container.getComponentInstance(Settings.class);
    }

    /**
     * <p>Get the type of a setting</p>
     * @param name the name of the setting
     * @return one of the static fields of <code>SettingConstants</code>
     * @throws SettingNullException if this setting does not exist.
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public final int getSettingType(final SecuritySession securitySession,
            final String name) throws SystemException {
        return getSettings().getSettingType(securitySession, name);
    }

    /**
     * <p>Get a setting of class String.</p>
     *
     * @return the setting if set, or null if not
     * @param name the name of the setting to return the value for
     * @param userDO the user to search for. If null is specified, the
     *     default setting is searched for and returned if found.
     * @exception SettingsDataTypeException if the setting has any class other
     *     than String
     * @return a setting of class <code>String</code> for the setting
     *      name provided.
     * @see #getSetting
     * @see #getIntegerSetting
     * @see #getBooleanSetting
     * @throws SettingNullException if this setting does not exist.
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public final String getStringSetting(final SecuritySession securitySession,
            final String name,
            final UserDO userDO) throws SystemException {
        return getSettings().getStringSetting(securitySession, name, userDO);
    }
    /**
     * <p>
     * Find out whether or not a setting is enabled.
     * </p>
     *
     * @param securitySession valid security session.
     * @param name name of the setting to check.
     * @return <code>true</code> if the setting exists and is enabled.
     * @throws SystemException if it don't work out in some way :-)
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public boolean isSettingEnabled(final SecuritySession securitySession,
            final String
            name)
            throws SystemException {
        return getSettings().isSettingEnabled(securitySession, name);
    }

    /**
     * <p>
     * Revert a user setting back to the general value.
     * </p>
     *
     * @param name the name of the setting to revert
     * @param user the setting for this user is reverted
     */
    public void revertSetting(final SecuritySession securitySession,
            final String name,
            final UserDO user) throws SystemException {
        getSettings().revertSetting(securitySession, name, user);
    }

    /**
     * <p>Provides access to the runtime properties of the context in which this
     * session bean is running.</p>
     *
     * <p>Is usually stored by the bean internally.</p>
     *
     * @param sessionContext new value for the session context. Is usually
     *     stored internally
     */
    public final void setSessionContext(final SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     * <p>Confirm all of the settings passed are correct.</p>
     *
     * @param settings a <code>Map</code> with setting names as keys and setting
     *  values as values
     * @param the <code>Locale</code> to get localised error messages
     * @param settingType one of the constants in <code>SettingConstants</code>:
     *  <code>SETTING_USER</code> or  <code>SETTING_SYSTEM</code>
     * @return a collection of validation errors if any of the settings contains
     *  invalid value.
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public ValidationErrors validate(final SecuritySession securitySession,
            final Map settings,
            final Locale locale,
            final int settingType) throws SystemException {
        return getSettings().validate(securitySession, settings, locale, settingType);
    }

}

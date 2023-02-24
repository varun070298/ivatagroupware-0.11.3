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
 * $Log: Settings.java,v $
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
 * Revision 1.2  2004/07/13 19:54:31  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.setting;

import java.util.Locale;
import java.util.Map;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationErrors;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Apr 3, 2004
 * @version $Revision: 1.2 $
 */
public interface Settings {
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
    void amendSetting(final SecuritySession securitySession,
            final String name,
            final Object value,
            final UserDO user)
            throws SystemException;

    /**
     * <p>Get a setting of class <code>Boolean</code>.</p>
     *
     * @return the setting if set, or null if not
     * @param name the name of the setting to return the value for
     * @param userName the user to search for. If null is specified, the
     *     default setting is searched for and returned if found.
     * @exception SettingsDataTypeException if the setting has any class other
     *     than Boolean
     * @return a setting of class <code>Boolean</code> for the setting
     *      name provided.
     * @see #getSetting
     * @see #getIntegerSetting
     * @see #getStringSetting
     * @throws SettingNullException if this setting does not exist.
     */
    Boolean getBooleanSetting(final SecuritySession securitySession,
            final String name,
            final UserDO userDO)
        throws SystemException;

    /**
     * <p>Get a setting of class <code>Integer</code>.</p>
     *
     * @exception SettingsDataTypeException if the setting has any class other
     *     than Integer.
     * @return the setting if set, or null if not
     * @param name the name of the setting to return the value for.
     * @param userName the user to search for. If null is specified, the
     *     default setting is searched for and returned if found.
     * @return a setting of class <code>Integer</code> for the setting
     *      name provided.
     * @see #getSetting
     * @see #getStringSetting
     * @see #getBooleanSetting
     * @throws SettingNullException if this setting does not exist.
     */
    Integer getIntegerSetting(final SecuritySession securitySession,
            final String name,
            final UserDO userDO)
        throws SystemException;

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
     * @param userName the user to search for. If null is specified, the
     *     default setting is searched for and returned if found.
     * @return a setting with the setting name provided. The type of the
     *     returned object depends on the <code>type</code> field of the
     *     setting.
     * @see #getIntegerSetting
     * @see #getStringSetting
     * @see #getBooleanSetting
     * @throws SettingNullException if this setting does not exist.
     */
    Object getSetting(final SecuritySession securitySession,
            final String name,
            final UserDO userDO)
        throws SystemException;

    /**
     * <p>Get the type of a setting</p>
     * @param name the name of the setting
     * @return one of the static fields of <code>SettingConstants</code>
     * @throws SettingNullException if this setting does not exist.
     */
    int getSettingType(final SecuritySession securitySession,
            final String name)
        throws SystemException;

    /**
     * <p>Get a setting of class String.</p>
     *
     * @return the setting if set, or null if not
     * @param name the name of the setting to return the value for
     * @param userName the user to search for. If null is specified, the
     *     default setting is searched for and returned if found.
     * @exception SettingsDataTypeException if the setting has any class other
     *     than String
     * @return a setting of class <code>String</code> for the setting
     *      name provided.
     * @see #getSetting
     * @see #getIntegerSetting
     * @see #getBooleanSetting
     * @throws SettingNullException if this setting does not exist.
     */
    String getStringSetting(final SecuritySession securitySession,
            final String name,
            final UserDO userDO)
        throws SystemException;

    /**
     * <p>
     * Find out whether or not a setting is enabled.
     * </p>
     *
     * @param securitySession valid security session.
     * @param name name of the setting to check.
     * @return <code>true</code> if the setting exists and is enabled.
     * @throws SystemException if it don't work out in some way :-)
     */
    boolean isSettingEnabled(final SecuritySession securitySession,
            final String
        name)
        throws SystemException;

    /**
     * <p>
     * Revert a user setting back to the general value.
     * </p>
     *
     * @param name the name of the setting to revert
     * @param user the setting for this user is reverted
     */
    void revertSetting(final SecuritySession securitySession,
            final String name,
            final UserDO user)
            throws SystemException;

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
     */
    ValidationErrors validate(final SecuritySession securitySession,
            final Map settings,
            final Locale locale,
            final int settingType)
        throws SystemException;
}
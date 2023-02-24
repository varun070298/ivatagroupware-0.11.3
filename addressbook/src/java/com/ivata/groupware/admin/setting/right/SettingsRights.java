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
 * $Log: SettingsRights.java,v $
 * Revision 1.2  2005/04/09 17:19:05  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:38  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/12 15:56:46  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/07/13 19:41:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.setting.right;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.mask.util.SystemException;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Apr 16, 2004
 * @version $Revision: 1.2 $
 */
public interface SettingsRights {
    /**
     * <p>this method sets a setting to be allowed for users to override the
     * system value</p>, {see canAmendSetting(name)}
     * @param name the name of the setting
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public void addAmendRightForSetting(final
        SecuritySession securitySession,
            final String settingName)
        throws SystemException;
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
    public boolean canAmendSetting(final
        SecuritySession securitySession,
            final String settingName)
        throws SystemException;
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
        throws SystemException;
    /**
     * <p>this method disables overriding the system value of one setting,
     * {see canAmendSetting(name)}
     * @param name the name of the setting
     *
     * @ejb.interface-method
     *      view-type="both"
     */
    public void removeAmendRightForSetting(final
        SecuritySession securitySession,
            final String settingName)
        throws SystemException;
}
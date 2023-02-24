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
 * $Log: SettingsRightsImpl.java,v $
 * Revision 1.4  2005/04/29 02:47:52  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
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
 * Revision 1.4  2004/11/12 18:17:09  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 15:56:46  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/11/03 15:29:30  colinmacleod
 * Added missing persistenceSession.close().
 *
 * Revision 1.1  2004/07/13 19:41:12  colinmacleod
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

import java.util.Collection;
import java.util.Iterator;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.setting.SettingDO;
import com.ivata.groupware.business.BusinessLogic;
import com.ivata.groupware.business.addressbook.person.group.GroupConstants;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.groupware.business.addressbook.person.group.right.RightConstants;
import com.ivata.groupware.business.addressbook.person.group.right.RightDO;
import com.ivata.groupware.business.addressbook.person.group.right.detail.RightDetailDO;
import com.ivata.groupware.container.persistence.QueryPersistenceManager;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.util.SystemException;


/**
 * <p>This session bean manages rigths for changing settings.</p>
 *
 * @since 2003-02-11
 * @author Peter Illes
 * @version $Revision: 1.4 $
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
public class SettingsRightsImpl extends BusinessLogic implements SettingsRights {
    private QueryPersistenceManager persistenceManager;
    public SettingsRightsImpl(QueryPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

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
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            // get the system setting - we always refer to system setting, as
            // they can't be removed
            SettingDO setting = (SettingDO)
                persistenceManager.findInstance(persistenceSession,
                "adminSettingByName", new Object [] {settingName});
            Integer settingId = setting.getId();

            // detail with meaning amend setting - user level
            RightDetailDO amendDetail = (RightDetailDO)
                    persistenceManager.findByPrimaryKey(persistenceSession,
                    RightDetailDO.class, RightConstants.DETAIL_SETTING_USER);

            Collection amendRight =
                persistenceManager.find(persistenceSession,
                            "rightByAccessDetailTargetId",
                            new Object [] {RightConstants.ACCESS_AMEND,
                                RightConstants.DETAIL_SETTING_USER,
                                settingId});

            // if there were no such rights set for this setting, do it now
            if (amendRight.isEmpty()) {
                GroupDO userGroup = (GroupDO)
                    persistenceManager.findByPrimaryKey(persistenceSession,
                    GroupDO.class, GroupConstants.USER_GROUP);

                RightDO right = new RightDO();
                right.setAccess(RightConstants.ACCESS_AMEND);
                right.setDetail(amendDetail);

                right.setGroup(userGroup);
                right.setTargetId(settingId);

                persistenceManager.add(persistenceSession, right);
            }
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
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
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            // get the system setting - we always refer to system setting, as
            // they can't be removed
            SettingDO setting = (SettingDO)
                persistenceManager.findInstance(persistenceSession,
                "adminSettingByName", new Object [] {settingName});
            Integer settingId = setting.getId();


            Collection amendRight =
                persistenceManager.find(persistenceSession,
                            "rightByAccessDetailTargetId",
                            new Object [] {RightConstants.ACCESS_AMEND,
                                RightConstants.DETAIL_SETTING_USER,
                                settingId});

            // if there were rights, they must be ours
            return !amendRight.isEmpty();
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
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
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            Collection targetIds = persistenceManager.find(persistenceSession,
                "rightByUserNameAccessDetail",
                new Object []
                {
                    securitySession.getUser().getName(),
                    RightConstants.ACCESS_AMEND,
                    RightConstants.DETAIL_SETTING_SYSTEM
                });

            // if the collection is nonEmpty, the user has right to change
            // system settings
            return !targetIds.isEmpty();
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
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
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            // get the system setting - we always refer to system setting, as
            // they can't be removed
            SettingDO setting = (SettingDO)
                persistenceManager.findInstance(persistenceSession,
                "adminSettingByName", new Object [] {settingName});
            Integer settingId = setting.getId();

            // detail with meaning amend setting - user level
            RightDetailDO amendDetail = (RightDetailDO)
                    persistenceManager.findByPrimaryKey(persistenceSession,
                    RightDetailDO.class, RightConstants.DETAIL_SETTING_USER);
            Collection amendRight =
                persistenceManager.find(persistenceSession,
                            "rightByAccessDetailTargetId",
                            new Object [] {RightConstants.ACCESS_AMEND,
                                RightConstants.DETAIL_SETTING_USER,
                                settingId});

            // remove all rights found, there should be only one though...
            for (Iterator i = amendRight.iterator(); i.hasNext();) {
                RightDO currentRight = (RightDO) i.next();
                persistenceManager.remove(persistenceSession, currentRight);
            }
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }
}

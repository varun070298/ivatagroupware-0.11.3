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
 * $Log: SettingsImplTest.java,v $
 * Revision 1.2  2005/04/09 17:19:11  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:14  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/11/12 18:17:25  colinmacleod
 * Ordered imports.
 *
 * Revision 1.2  2004/11/12 15:57:08  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/07/13 19:41:17  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.setting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.HibernateException;

import com.ivata.groupware.admin.AdminTestCase;
import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.container.persistence.hibernate.HibernateManager;
import com.ivata.groupware.container.persistence.hibernate.HibernateSession;
import com.ivata.mask.util.SystemException;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Jun 7, 2004
 * @version $Revision: 1.2 $
 */
public class SettingsImplTest extends AdminTestCase {
    /**
     * <p>
     * This is the implementation instance which will be tested.
     * </p>
     */
    private SettingsImpl settings;

    private List testSettings = new ArrayList();

    /**
     * Constructor for SettingsImplTest.
     * @param arg0
     */
    public SettingsImplTest(String arg0) throws HibernateException {
        super(arg0);
    }

    private SettingDO createStringSetting(final String name) throws SystemException {
        HibernateManager hibernateManager = getHibernateManager();
        HibernateSession hibernateSession = getHibernateSession();

        SettingDO setting = new SettingDO();
        setting.setDescription("any description");
        setting.setEnabled(true);
        setting.setName("name");
        setting.setType(SettingConstants.DATA_TYPE_STRING);
        setting.setUser(null);
        setting = (SettingDO) hibernateManager.add(hibernateSession, setting);
        assertNotNull(setting.getId());
        testSettings.add(setting);
        return setting;
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        SecuritySession securitySession = getSecuritySession();
        settings = new SettingsImpl(getHibernateManager());
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        HibernateManager hibernateManager = getHibernateManager();
        HibernateSession hibernateSession = getHibernateSession();

        Iterator testSettingIterator = testSettings.iterator();
        while (testSettingIterator.hasNext()) {
            SettingDO setting = (SettingDO) testSettingIterator.next();
            hibernateManager.remove(hibernateSession, setting);
        }
    }

    public void testAmendSetting() throws SystemException {
        SecuritySession securitySession = getSecuritySession();
        HibernateManager hibernateManager = getHibernateManager();
        HibernateSession hibernateSession = getHibernateSession();

        SettingDO setting = createStringSetting("amend_test");
        settings.amendSetting(securitySession, "amend_test", "new_value", null);
        setting = (SettingDO)
            hibernateManager.findInstance(hibernateSession, "adminSettingName",
            new Object[] {setting.getName()});
        assertEquals("new_value", setting.getValue());
        UserDO user = securitySession.getUser();
        settings.amendSetting(securitySession, "amend_test", "user_value", user);
        SettingDO userSetting = (SettingDO)
            hibernateManager.findInstance(hibernateSession, "adminSettingNameUserName",
            new Object[] {setting.getName(), user.getName()});
        setting = (SettingDO)
            hibernateManager.findInstance(hibernateSession, "adminSettingName",
            new Object[] {setting.getName()});
        // check the general setting hasn't changed!
        assertEquals("user_value", userSetting.getValue());
        // check the general setting hasn't changed!
        assertEquals("new_value", setting.getValue());
    }

    public void testGetBooleanSetting() throws SystemException {
        SecuritySession securitySession = getSecuritySession();
        HibernateManager hibernateManager = getHibernateManager();
        HibernateSession hibernateSession = getHibernateSession();

        SettingDO testSetting = createStringSetting("boolean_test");
        testSetting.setType(SettingConstants.DATA_TYPE_BOOLEAN);
        testSetting.setValue("false");
        hibernateManager.amend(hibernateSession, testSetting);

        Boolean test = settings.getBooleanSetting(securitySession, "boolean_test", null);
        assertFalse(test.booleanValue());
    }

    public void testGetIntegerSetting() throws SystemException {
        SecuritySession securitySession = getSecuritySession();
        HibernateManager hibernateManager = getHibernateManager();
        HibernateSession hibernateSession = getHibernateSession();

        SettingDO testSetting = createStringSetting("integer_test");
        testSetting.setType(SettingConstants.DATA_TYPE_BOOLEAN);
        testSetting.setValue("202");
        hibernateManager.amend(hibernateSession, testSetting);

        Integer test = settings.getIntegerSetting(securitySession, "integer_test", null);
        assertEquals(new Integer(202), test);
    }

    public void testGetSetting() throws SystemException {
        SecuritySession securitySession = getSecuritySession();
        HibernateManager hibernateManager = getHibernateManager();
        HibernateSession hibernateSession = getHibernateSession();

        SettingDO testSetting = createStringSetting("get_test");
        testSetting.setValue("get_test_value");
        hibernateManager.amend(hibernateSession, testSetting);

        Object test = settings.getSetting(securitySession, "get_test", null);
        assertEquals("get_test_value", test);
    }

    public void testGetSettingType() {
    }

    public void testGetStringSetting() {
    }

    public void testIsSettingEnabled() {
    }

    public void testRemoveSetting() {
    }

    public void testValidate() {
    }

}

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
 * $Log: AdminTestCase.java,v $
 * Revision 1.2  2005/04/09 17:19:11  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:14  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/07/13 19:41:17  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin;

import java.io.File;
import java.util.Properties;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.cfg.Configuration;

import com.ivata.groupware.GroupwareTestCase;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Jun 7, 2004
 * @version $Revision: 1.2 $
 */
public abstract class AdminTestCase extends GroupwareTestCase {

    /**
     * @param hibernateConfiguration
     * @param arg0
     */
    public AdminTestCase(String arg0) throws HibernateException {
        super(getHibernateConfiguration(), arg0);
    }

    /**
     * <p>
     * Helper called by the constructor to create a valid hibernate
     * configuration for all the address book's functionality.
     * </p>
     *
     * @return valid hibernate configuration.
     * @throws HibernateException
     */
    private static Configuration getHibernateConfiguration() throws HibernateException {
        Configuration hibernateConfiguration = new Configuration();
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/admin/setting/SettingDO.hbm.xml"));
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/admin/security/user/UserDO.hbm.xml"));
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/business/addressbook/address/AddressDO.hbm.xml"));
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/business/addressbook/address/country/CountryDO.hbm.xml"));
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/business/addressbook/person/PersonDO.hbm.xml"));
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/business/addressbook/person/group/right/RightDO.hbm.xml"));
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/business/addressbook/person/group/right/detail/RightDetailDO.hbm.xml"));
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/business/addressbook/person/group/GroupDO.hbm.xml"));
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/business/addressbook/person/employee/EmployeeDO.hbm.xml"));
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/business/addressbook/telecomaddress/TelecomAddressDO.hbm.xml"));
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/navigation/menu/MenuDO.hbm.xml"));
        hibernateConfiguration.addFile(new File("../hibernate/target/xdoclet/hibernatedoclet/com/ivata/groupware/navigation/menu/item/MenuItemDO.hbm.xml"));

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        hibernateProperties.setProperty("hibernate.connection.URL", "jdbc:postgresql://localhost:5432/portal");
        hibernateProperties.setProperty("hibernate.connection.username", "postgres");
        hibernateProperties.setProperty("hibernate.connection.password", "");
        hibernateProperties.setProperty("hibernate.dialect", "net.sf.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateConfiguration.setProperties(hibernateProperties);

        return hibernateConfiguration;
    }


}

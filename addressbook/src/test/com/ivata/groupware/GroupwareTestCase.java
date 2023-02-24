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
 * $Log: GroupwareTestCase.java,v $
 * Revision 1.3  2005/04/10 20:09:39  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:11  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:13  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/07/13 19:41:17  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import net.sf.hibernate.Interceptor;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.server.TestSecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.container.persistence.hibernate.HibernateInterceptor;
import com.ivata.groupware.container.persistence.hibernate.HibernateManager;
import com.ivata.groupware.container.persistence.hibernate.HibernateQueryFactory;
import com.ivata.groupware.container.persistence.hibernate.HibernateSession;
import com.ivata.mask.persistence.right.DefaultPersistenceRights;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Jun 2, 2004
 * @version $Revision: 1.3 $
 */
public class GroupwareTestCase extends TestCase {

    /**
     * <p>
     * Hibernate persistence manager.
     * </p>
     */
    private static HibernateManager hibernateManager;
    /**
     * <p>
     * Hibernate session - used to access hibernate.
     * </p>
     */
    private static HibernateSession hibernateSession;

    /**
     * <p>
     * Fake security session - gives you admin rights.
     * </p>
     */
    private static SecuritySession securitySession;
    /**
     * @return
     */
    protected static HibernateManager getHibernateManager() {
        return hibernateManager;
    }
    /**
     * @return
     */
    protected static HibernateSession getHibernateSession() {
        return hibernateSession;
    }

    /**
     * @return
     */
    protected static SecuritySession getSecuritySession() {
        return securitySession;
    }

    /**
     * <p>
     * Hibernate configuration to use to configure the hibernate session.
     * </p>
     */
    Configuration hibernateConfiguration;

    /**
     * Constructor for AddressBookImplTest.
     * @param arg0
     */
    public GroupwareTestCase(Configuration hibernateConfiguration, String arg0) {
        super(arg0);
        this.hibernateConfiguration = hibernateConfiguration;
    }

    /*
     * @see TestCase#setUp()
     */
    protected synchronized void setUp() throws Exception {
        super.setUp();

        if (hibernateSession == null) {
            // set up the hibernate session
            SessionFactory sessionFactory = hibernateConfiguration.buildSessionFactory();

            Map queryMap = new HashMap();
            Map queryArgumentsMap = new HashMap();

            hibernateManager = new HibernateManager(sessionFactory,
                        new HibernateQueryFactory(queryMap, queryArgumentsMap),
                        new DefaultPersistenceRights());
            Interceptor interceptor = new HibernateInterceptor(hibernateManager,
                    sessionFactory);
            Session wrappedSession = sessionFactory.openSession(interceptor);
            hibernateSession = new HibernateSession(wrappedSession,
                    wrappedSession.beginTransaction(), null);

            // create a fake security session for admin
            UserDO adminUser = (UserDO)hibernateManager.findByPrimaryKey(hibernateSession,
                UserDO.class, new Integer(1));
            securitySession = new TestSecuritySession(adminUser);
        }
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}

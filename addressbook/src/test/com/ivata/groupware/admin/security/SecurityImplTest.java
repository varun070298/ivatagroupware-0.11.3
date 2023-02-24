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
 * $Log: SecurityImplTest.java,v $
 * Revision 1.4  2005/04/10 20:32:01  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.3  2005/04/09 17:19:11  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.2  2005/03/16 12:47:28  colinmacleod
 * Fixed SecurityImpl constructor.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:14  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/11/12 18:17:25  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 15:57:07  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/09/30 15:15:53  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.1  2004/07/13 19:41:17  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security;
import net.sf.hibernate.HibernateException;

import com.ivata.groupware.admin.AdminTestCase;
import com.ivata.groupware.admin.security.server.PlainTextSecurityServer;
import com.ivata.groupware.admin.security.server.SecurityServer;
import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.container.persistence.QueryPersistenceManager;
import com.ivata.mask.DefaultMaskFactory;
import com.ivata.mask.field.DefaultFieldValueConvertorFactory;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.util.SystemException;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Jun 3, 2004
 * @version $Revision: 1.4 $
 */
public class SecurityImplTest extends AdminTestCase {

    QueryPersistenceManager persistenceManager;
    PersistenceSession persistenceSession;

    PersonDO person = null;

    /**
     * <p>
     * This is the implementation instance which will be tested.
     * </p>
     */
    private SecurityImpl security;

    /**
     * Constructor for SecurityImplTest.
     * @param arg0
     */
    public SecurityImplTest(String arg0)  throws HibernateException {
        super(arg0);
    }
    SecuritySession securitySession;

    /*
     * @see GroupwareTestCase#setUp()
     */
    protected synchronized void setUp() throws Exception {
        super.setUp();
        securitySession = getSecuritySession();
        persistenceManager = getHibernateManager();
        persistenceSession = getHibernateSession();
        SecurityServer server = new PlainTextSecurityServer(persistenceManager);
        //security = new SecurityImpl(persistenceManager, server, new DefaultMaskFactory(), Boolean.FALSE);
        /*
         * QualitaCorpus.class: we removed the line above
         * due to a missing parameter. We replace it with the following
         * statement:
         */
        security = new SecurityImpl(persistenceManager, server, new DefaultMaskFactory(null), Boolean.FALSE);
    }

    /*
     * @see GroupwareTestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        if (person != null) {
            persistenceManager.remove(persistenceSession, person);
        }
    }

    private PersonDO createTestPerson() throws SystemException {
        person = new PersonDO();
        person.setCompany("test company");
        person.setFileAs("file as");
        person.setFirstNames("first names");
        person.setLastName("security test");
        persistenceManager.add(persistenceSession, person);
        return person;
    }


    public void testAddUserToPerson() throws SystemException {
/* TODO
        PersonDO person = createTestPerson();

        security.addUserToPerson(securitySession, person, "security test user", true);

        String id = person.getId();
        person = (PersonDO)
            persistenceManager.findByPrimaryKey(persistenceSession, PersonDO.class,
                    id);
        assertEquals(id, person.getId());
        UserDO user = (UserDO)
            persistenceManager.findInstance(persistenceSession, "adminUserByName",
                    new Object [] {"security test user"});
        assertNotNull(user.getId());
        assertEquals(person.getUser(), user);
        assertEquals("security test user", user.getName());
        assertTrue(user.isEnabled());
        assertFalse(user.isDeleted());
*/
    }

    public void testAmendUserName() throws SystemException {
    }

    public void testCheckPassword() {
    }

    public void testCreatePrivateGroups() {
    }

    public void testEnableUser() {
    }

    public void testFindUserByName() {
    }

    public void testGetSystemUserName() {
    }

    public void testGetUserNameFromSystemUserName() {
    }

    public void testIsUserEnabled() {
    }

    public void testLogin() {
    }

    public void testLoginAgain() {
    }

    public void testLoginGuest() {
    }

    public void testRemoveUser() {
    }

    public void testRestoreUser() {
    }

    public void testSetPassword() {
    }

}
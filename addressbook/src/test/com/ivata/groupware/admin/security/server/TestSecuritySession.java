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
 * $Log: TestSecuritySession.java,v $
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
package com.ivata.groupware.admin.security.server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.user.UserDO;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Jun 2, 2004
 * @version $Revision: 1.2 $
 */
public class TestSecuritySession implements SecuritySession {
    int access;
    Map attributes = new HashMap();
    UserDO user;

    /**
     */
    public TestSecuritySession(UserDO user) {
        super();
        this.user = user;
    }

    /**
     * @see com.ivata.groupware.admin.security.server.SecuritySession#getAttribute(String)
     */
    public final Serializable getAttribute(final String name) {
        return (Serializable) attributes.get(name);
    }

    /**
     * @see com.ivata.groupware.admin.security.server.SecuritySession#setAttribute(String, java.io.Serializable)
     */
    public final void setAttribute(final String name,
            final Serializable value) {
        attributes.put(name, value);
    }

    /**
     * @see com.ivata.groupware.admin.security.server.SecuritySession#getUser()
     */
    public UserDO getUser() {
        return user;
    }

    /**
     * @see com.ivata.groupware.admin.security.server.SecuritySession#getAccess()
     */
    public final int getAccess() {
        return access;
    }

    /**
     * @see com.ivata.groupware.admin.security.server.SecuritySession#setAccess(int)
     */
    public final void setAccess(final int access) {
        this.access = access;
    }

    /**
     * @see com.ivata.groupware.admin.security.server.SecuritySession#getContainer()
     */
    public final PicoContainer getContainer() {
        return null;
    }

    /**
     * Refer to {@link }.
     * @see com.ivata.groupware.admin.security.server.SecuritySession#isGuest()
     * @return
     */
    public boolean isGuest() {
        return false;
    }

    /**
     * Refer to {@link SecuritySession#getPassword}.
     * @return Refer to {@link SecuritySession#getPassword}.
     */
    public String getPassword() {
        return null;
    }

}

/*
 * Created on Oct 9, 2003
 * $Id: SecuritySession.java,v 1.2 2005/04/09 17:19:57 colinmacleod Exp $
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
 * $Log: SecuritySession.java,v $
 * Revision 1.2  2005/04/09 17:19:57  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:40  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/09/30 15:15:58  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.3  2004/07/13 19:41:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/03/21 21:16:05  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:57:45  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.1.1.1  2003/10/13 20:50:07  colin
 * Restructured portal into subprojects
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security.server;

import java.io.Serializable;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.user.UserDO;

/**
 * <p>Represents a user's session, and is used to authenticate her actions
 * throughout the system. You must define a class which implements
 * this interface and which will be returned by <code>SessionServer.login</code>.</p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 */
public interface SecuritySession extends Serializable {
    /**
     * <p>
     * Get the access level for this next task to be performed.
     * </p>
     *
     * @return access level
     */
    int getAccess();
    /**
     * <p>
     * The security session can also be used as a container for items which
     * should persist as long as the user is logged in.
     * </p>
     *
     * @param name name of the attribute to retrieve.
     * @return value for this attribute.
     */
    Serializable getAttribute(final String name);

    /**
     * <p>
     * Get the pico container used to access objects for this session.
     * </p>
     *
     * @return valid pico container.
     */
    PicoContainer getContainer();
    /**
     * <p>Get the password associated with this session.</p>
     * <p>
     * (I'd rather not store
     * the password in the sesssion, but <strong>Cyrus IMAP</strong> needs
     * it.)
     * </p>
     *
     * @return password.
     */
    String getPassword();
    /**
     * <p>
     * Get the user who logged in to this security session.
     * </p>
     *
     * @return user who logged in to this security session.
     */
    UserDO getUser();
    /**
     * <p>
     * Find out whether or not this is the guest user.
     * </p>
     * @return <code>true</code> if this is a guest, otherwise
     * <code>false</code>.
     */
    boolean isGuest();
    /**
     * <p>
     * Set the access level for subsequent tasks to be performed.
     * </p>
     *
     * @param accesss access level
     */
    void setAccess(final int access);
    /**
     * <p>
     * The security session can also be used as a container for items which
     * should persist as long as the user is logged in.
     * </p>
     *
     * @param name name of the attribute to set.
     * @param value value for this attribute.
     */
    void setAttribute(final String name,
            final Serializable value);
}

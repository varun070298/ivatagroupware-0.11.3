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
 * $Log: AbstractSecuritySession.java,v $
 * Revision 1.3.2.1  2005/10/08 17:31:03  colinmacleod
 * Fixed initialization of SecuritySession.
 *
 * Revision 1.3  2005/04/10 20:09:48  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:57  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:41  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/11/12 18:16:07  colinmacleod
 * Ordered imports.
 *
 * Revision 1.2  2004/11/12 15:57:18  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/09/30 15:15:58  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.1  2004/07/13 19:41:11  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.defaults.DefaultPicoContainer;

import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.SystemException;

/**
 * This absrtact class is a wrapper for <code>Map</code>, used to store items
 * in the session.
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Mar 29, 2004
 * @version $Revision: 1.3.2.1 $
 */
public abstract class AbstractSecuritySession implements SecuritySession {

    /**
     * Access level.
     */
    private int access;
    /**
     * Stores all attributes which are persisted in this session.
     */
    private Map attributes = new HashMap();
    /**
     * <p>
     * Container used throughout this session.
     * </p>
     */
    private PicoContainer container;
    private String password;

    /**
     * User who is logged in to this session.
     */
    private UserDO user;

    /**
     * Construct a security session for the given user.
     *
     * @param container container used throughout this session.
     * @param user user who is logged in to this session.
     */
    public AbstractSecuritySession(PicoContainer container, UserDO user)
            throws SystemException {
        MutablePicoContainer mutableParent =
            new DefaultPicoContainer(container);
        mutableParent.registerComponentInstance(
            SecuritySession.class, this);
        MutablePicoContainer childContainer = PicoContainerFactory
            .getInstance().override(mutableParent);
        childContainer.registerComponentInstance(SecuritySession.class,
            this);

        this.container = childContainer;
        this.user = user;
    }

    /**
     * <p>
     * Get the access level for the next commands.
     * </p>
     *
     * @return access level for the next commands.
     */
    public final int getAccess() {
        return access;
    }

    /**
     * The security session can also be used as a container for items which
     * should persist as long as the user is logged in.
     *
     * @param name name of the attribute to retrieve.
     * @return value for this attribute.
     * @see com.ivata.groupware.admin.security.server.SecuritySession#getAttribute(String)
     */
    public final Serializable getAttribute(final String name) {
        return (Serializable) attributes.get(name);
    }

    /**
     * <p>
     * Container used throughout this session.
     * </p>
     * @return container used throughout this session.
     */
    public final PicoContainer getContainer() {
        return container;
    }


    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @see com.ivata.groupware.admin.security.server.SecuritySession#getUser()
     */
    public UserDO getUser() {
        return user;
    }

    /**
     * Refer to {@link SecuritySession#isGuest}.
     * @return Refer to {@link SecuritySession#isGuest}.
     */
    public boolean isGuest() {
        return ((user == null)
                || "guest".equals(user.getName()));
    }

    /**
     * <p>Serialize the object from the input stream provided.</p>
     * @exception ClassNotFoundException thrown by
     * <code>ObjectInputStream.defaultReadObject(  )</code>.
     * @exception IOException thrown by
     * <code>ObjectInputStream.defaultReadObject(  )</code>.
     * @param ois the input stream to serialize the object from
     *
     */
    private void readObject(final ObjectInputStream ois) throws ClassNotFoundException,IOException {
        ois.defaultReadObject();
    }

    /**
     * <p>
     * Set the access level for the next commands.
     * </p>
     *
     * @param access access level for the next commands.
     */
    public final void setAccess(final int access) {
        this.access = access;
    }

    /**
     * The security session can also be used as a container for items which
     * should persist as long as the user is logged in.
     *
     * @param name name of the attribute to set.
     * @param value value for this attribute.
     * @see com.ivata.groupware.admin.security.server.SecuritySession#setAttribute(String, java.io.Serializable)
     */
    public final void setAttribute(final String name,
            final Serializable value) {
        attributes.put(name, value);
    }
    /**
     * Refer to {@link #getPassword}.
     * @param passwordParam Refer to {@link #getPassword}.
     */
    public void setPassword(String passwordParam) {
        password = passwordParam;
    }
    /**
     * <p>Serialize the object to the output stream provided.</p>
     * @exception IOException thrown by
     * <code>ObjectOutputStream.defaultWriteObject(  )</code>
     * @param oos the output stream to serialize the object to
     *
     */
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }
}

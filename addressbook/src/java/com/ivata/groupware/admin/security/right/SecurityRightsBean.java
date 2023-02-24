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
 * $Log: SecurityRightsBean.java,v $
 * Revision 1.3  2005/04/10 19:55:43  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:04  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:44  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.5  2004/11/12 15:56:45  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.4  2004/07/13 19:41:11  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/02/01 22:00:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:45  colinmacleod
 * Moved open portal to sourceforge.
 *
 * Revision 1.8  2003/11/03 11:28:24  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.7  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.6  2003/10/16 07:53:32  jano
 * fixing XDoclet
 *
 * Revision 1.5  2003/10/15 12:51:43  colin
 * fixing with XDoclet
 *
 * Revision 1.4  2003/10/15 12:48:32  jano
 * goning to 1.1
 *
 * Revision 1.1.1.1  2003/10/13 20:50:06  colin
 * Restructured portal into subprojects
 *
 * Revision 1.4  2003/08/21 09:48:59  jano
 * user has rights to add/amend/remove another user if he can
 * add/amend/remove in "everyone" user group
 *
 * Revision 1.3  2003/08/19 15:02:42  jano
 * fixing users & rights popup window
 *
 * Revision 1.2  2003/05/02 16:25:11  peter
 * only administrators can handle users now
 *
 * Revision 1.1  2003/02/24 18:53:57  colin
 * added to admin
 *
 * Revision 1.5  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.4  2003/01/03 17:17:21  jano
 * fixing findr problem in canUser
 *
 * Revision 1.3  2003/01/02 16:40:29  jano
 * taking VIEW of
 *
 * Revision 1.2  2002/12/16 13:42:18  jano
 * finder and collection problem
 *
 * Revision 1.1  2002/09/09 08:35:24  colin
 * new security EJB takes over role of
 *  logging into the system
 *  maintaining user names and passwords
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security.right;


import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.SystemException;


/**
 * <p>Security rights determine what each user can and cannot do within the
 * security subsystem. If you need to know where a user has sufficient rights
 * to add, change or remove another user, then  this is the class to tell you.</p>
 *
 *
 * @since 2002-09-08
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @ejb.bean
 *      name="SecurityRights"
 *      display-name="SecurityRights"
 *      type="Stateless"
 *      view-type="both"
 *      local-jndi-name = "SecurityRightsLocal"
 *      jndi-name="SecurityRightsRemote"
 *
 * @ejb.transaction
 *      type = "Required"
 *
 *  @ejb.home
 *      generate="false"
 *      remote-class="com.ivata.groupware.admin.security.right.SecurityRightsRemoteHome"
 *
 *  @ejb.interface
 *      remote-class="com.ivata.groupware.admin.security.right.SecurityRightsRemote"
 */
public class SecurityRightsBean implements SessionBean {

    /**
     * <p>Provides the session bean with container-specific information.</p>
     */
    SessionContext sessionContext;

    /**
     * <p>See if a user has sufficient rights to add user to the system - it's meen to everyOne group.</p>
     *
     * @param userName the user who wants to add another user.
     * @param personId the unique identifier of the person who will be added.
     * @return <code>true</code> if this action is authorized by the system,
     * otherwise <code>false</code>.
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canAddUser(final SecuritySession securitySession)
            throws SystemException {
        return getSecurityRights().canAddUser(securitySession);
    }

    /**
     * <p>See if a user has sufficient rights to amend user in the
     * system - it's meen in everyone group.</p>
     *
     * @param userName the user who wants to add another user.
     * @param userNameAmend the user who should be amended.
     * @return <code>true</code> if this action is authorized by the system,
     * otherwise <code>false</code>.
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canAmendUser(final SecuritySession securitySession)
            throws SystemException {
        return getSecurityRights().canAmendUser(securitySession);
    }

    /**
     * <p>See if a user has sufficient rights to remove user from the
     * system - it's meen from everone group.</p>
     *
     * @param userName the user who wants to add another user.
     * @param userNameRemove the user who should be removed.
     * @return <code>true</code> if this action is authorized by the system,
     * otherwise <code>false</code>.
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public boolean canRemoveUser(final SecuritySession securitySession)
            throws SystemException {
        return getSecurityRights().canRemoveUser(securitySession);
    }

    /**
     * <p>Internal helper method. Find out if a user is allowed to access
     * entries in a given group.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param groupId the unique identifier of the group to check.
     * @param access the access level as defined in {@link
     *      com.ivata.groupware.security.person.group.right.RightConstants
     *      RightConstants}.
     * @return <code>true</code> if the user is entitled to access entries in the
     *      group, otherwise <code>false</code>.
     */
    public boolean canUser(final SecuritySession securitySession,
            final Integer access)
            throws SystemException {
        return getSecurityRights().canUser(securitySession, access);
    }

    /**
     * <p>Called by the container to notify an entity object it has been
     * activated.</p>
     */
    public void ejbActivate() {}

    /**
     * <p>Called by the container just after the bean has been created.</p>
     *
     * @exception CreateException if any error occurs. Never thrown by this
     *     class.
     *
     * @ejb.create-method
     */
    public void ejbCreate() throws CreateException {}

    /**
     * <p>Called by the container to notify the entity object it will be
     * deactivated. Called just before deactivation.</p>
     */
    public void ejbPassivate() {}

    /**
     * <p>This method is called by the container when the bean is about
     * to be removed.</p>
     *
     * <p>This method will be called after a client calls the <code>remove</code>
     * method of the remote/local home interface.</p>
     *
     * @throws RemoveException if any error occurs. Currently never thrown by
     *     this class.
     */
    public void ejbRemove() {}
    /**
     * Get the addressbook implementation from the <code>PicoContainer</code>.
     */
    private SecurityRights getSecurityRights() throws SystemException {
        PicoContainer container = PicoContainerFactory.getInstance()
            .getGlobalContainer();
        return (SecurityRights) container.getComponentInstance(SecurityRights.class);
    }

    /**
     * <p>Set up the context for this entity object. The session bean stores the
     * context for later use.</p>
     *
     * @param sessionContext the new context which the session object should
     *     store.
     */
    public final void setSessionContext(final SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}

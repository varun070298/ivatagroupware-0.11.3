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
 * $Log: PlainTextSecuritySession.java,v $
 * Revision 1.3  2005/04/10 19:39:23  colinmacleod
 * Cosmetic changes.
 *
 * Revision 1.2  2005/04/09 17:19:57  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:41  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/12 15:57:18  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/09/30 15:15:58  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.2  2004/07/13 19:41:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.1  2004/03/21 20:16:24  colinmacleod
 * First version. Plain text security server allows for open portal to function without a mail server.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security.server;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.mask.util.SystemException;

/**
 * Very simple implementation of security session, for use with plain text
 * password verification.
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since 2004-05-11
 * @version $Revision: 1.3 $
 */
public class PlainTextSecuritySession extends AbstractSecuritySession {
    /**
     * Construct a security session for the user with the name given.
     *
     * @param user currently logged in user.
     */
    public PlainTextSecuritySession(PicoContainer container, UserDO user)
            throws SystemException {
        super(container, user);
    }
}

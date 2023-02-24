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
 * $Log: SecurityMaskAuthenticator.java,v $
 * Revision 1.2  2005/04/09 17:19:50  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:49:54  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/12/29 14:09:32  colinmacleod
 * Changed subproject name from masks to mask
 *
 * Revision 1.1  2004/11/12 18:28:27  colinmacleod
 * Added security session mask authenticator.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.mask.struts;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.mask.web.struts.MaskAuthenticator;

/**
 * <p>
 * Authenticates the current user against the <strong>ivata groupware</strong> security
 * session.
 * </p>
 *
 * @since Nov 12, 2004
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */

public class SecurityMaskAuthenticator implements MaskAuthenticator, Serializable {

    /**
     * <p>
     * Authenticates the current user against the <strong>ivata groupware</strong> security
     * session.
     * </p>
     *
     * @return <code>null</code>, if this is a valid, logged-in user, otherwise
     * &quot;<code>login</code>&quot;.
     * @see com.ivata.mask.web.struts.MaskAuthenticator#authenticate(javax.servlet.http.HttpSession, boolean)
     */
    public String authenticate(final HttpSession session,
            final boolean login) {
        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");

        // if we're logging in, or there is a valid security session, that means
        // carry on as normal...
        if (login
                || ((securitySession != null)
                        && !securitySession.isGuest())) {
            return null;
        }
        // otherwise login...
        return "login";
    }

}

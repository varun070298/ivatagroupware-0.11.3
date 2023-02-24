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
 * $Log: TestSecurityServer.java,v $
 * Revision 1.2  2005/04/09 17:19:11  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:14  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/12 15:57:08  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/11/03 15:38:00  colinmacleod
 * First version in CVS.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security.server;

import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.mask.util.SystemException;

/**
 * <p>
 * TODO
 * </p>
 *
 * @since Nov 2, 2004
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */

public class TestSecurityServer implements SecurityServer {

    /* (non-Javadoc)
     * @see com.ivata.groupware.admin.security.server.SecurityServer#addUser(String, String)
     */
    public void addUser(final SecuritySession securitySession,
            final String userName,
            final String fullName)
            throws SystemException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.admin.security.server.SecurityServer#checkPassword(String, String)
     */
    public void checkPassword(final SecuritySession securitySession,
            final String userName,
            final String password)
            throws SystemException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.admin.security.server.SecurityServer#getSystemUserName(String)
     */
    public final String getSystemUserName(final SecuritySession securitySession,
            final String userName) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.admin.security.server.SecurityServer#getUserNameFromSystemUserName(String)
     */
    public final String getUserNameFromSystemUserName(final SecuritySession securitySession,
            final String systemUserName) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Refer to {@link }.
     *
     * @param userNameParam
     * @return
     * @see com.ivata.groupware.admin.security.server.SecurityServer#isUser(java.lang.String)
     */
    public boolean isUser(final SecuritySession securitySession,
            final String userNameParam) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.admin.security.server.SecurityServer#login(com.ivata.groupware.admin.security.user.UserDO, String)
     */
    public SecuritySession login(final UserDO user,
            final String password)
            throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.admin.security.server.SecurityServer#loginGuest()
     */
    public SecuritySession loginGuest() throws SystemException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.admin.security.server.SecurityServer#removeUser(String)
     */
    public void removeUser(final SecuritySession securitySession,
            final String userName) throws SystemException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.admin.security.server.SecurityServer#setPassword(String, String)
     */
    public final void setPassword(final SecuritySession securitySession,
            final String userName,
            final String password)
            throws SystemException {
        // TODO Auto-generated method stub

    }

}

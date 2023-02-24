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
 * $Log: TimestampDOHandling.java,v $
 * Revision 1.2  2005/04/09 17:19:58  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:32  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/09/30 15:16:00  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.1  2004/07/13 19:41:15  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.container.persistence;

import java.sql.Timestamp;
import java.util.Date;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;

/**
 * <p>
 * This class helps you assign users and time stamps for created by and modified
 * by times
 * </p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since ivata groupware 0.9 (2004-06-03)
 * @version $Revision: 1.2 $
 */
public final class TimestampDOHandling {
    public static void add(SecuritySession securitySession, TimestampDO timestampDO) {
        assert (securitySession != null);
        assert (timestampDO != null);

        Timestamp now = new Timestamp(new Date().getTime());
        UserDO user = securitySession.getUser();
        timestampDO.setCreated(now);
        timestampDO.setCreatedBy(user);
        timestampDO.setModified(now);
        timestampDO.setModifiedBy(user);
    }
    public static void amend(SecuritySession securitySession, TimestampDO timestampDO) {
        Timestamp now = new Timestamp(new Date().getTime());
        UserDO user = securitySession.getUser();
        // modified is automagically updated timestampDO.setModified(now);
        timestampDO.setModifiedBy(user);
    }
}

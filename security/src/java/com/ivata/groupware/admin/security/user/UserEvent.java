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
 * $Log: UserEvent.java,v $
 * Revision 1.3  2005/04/10 18:47:41  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:57  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:42  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/09/30 15:15:59  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.1  2004/07/13 19:15:26  colinmacleod
 * Moved from business.addressbook to admin.security.
 *
 * Revision 1.2  2004/03/21 21:16:07  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:57:52  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security.user;

import com.ivata.groupware.business.event.Event;

/**
 * <p>To implement a specific event with detail specific to your system, you
 * need to extend this class.</p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 */
public class UserEvent extends Event {
    /**
     * <p>Stores the user name for the user this event relates to.</p>
     */
    private String userName = null;

    /**
     * <p>Construct an event and set the name of the <em>JMS</em> topic associated
     * with this event.</p>
     *
     * @param userName the name of the user with whom this event is associated.
     * @param topic set to one of the constants in <code>UserEventConstants</code>.
     * @see com.ivata.groupware.business.addressbook.person.group.right.RightConstants
     */
    public UserEvent(String userNameParam, String topic) {
        super(topic);
        userName = userNameParam;
    }

    /**
     * <p>Get  the user name for the user this event relates to.</p>
     *
     * @return  the user name for the user this event relates to.</p>
     */
    public final String getUserName() {
        return userName;
    }

}

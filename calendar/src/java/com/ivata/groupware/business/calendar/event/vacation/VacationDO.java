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
 * $Log: VacationDO.java,v $
 * Revision 1.2  2005/04/09 17:19:17  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:47:46  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/07/13 19:42:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar.event.vacation;

import com.ivata.groupware.business.calendar.event.EventDO;

/**
 * <p>
 * Represents a vacation or vacation request.
 * </p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Apr 4, 2004
 * @version $Revision: 1.2 $
 *
 * @hibernate.joined-subclass
 *      table="vacation"
 * @hibernate.joined-subclass-key
 *      column="calendar_event"
 */
public class VacationDO extends EventDO {
    /**
     * <p>
     * Indicates whether or not this vacation has been approved.
     * </p>
     */
    private boolean approved = false;
    /**
     * <p>
     * Indicates whether or not this vacation has been approved.
     * </p>
     *
     * @return <code>true</code> if this vacation has been approved.
     * @hibernate.property
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * <p>
     * Indicates whether or not this vacation has been approved.
     * </p>
     *
     * @param approved <code>true</code> if this vacation has been approved.
     */
    public final void setApproved(final boolean approved) {
        this.approved = approved;
    }
}

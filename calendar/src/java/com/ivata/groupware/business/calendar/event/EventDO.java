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
 * $Log: EventDO.java,v $
 * Revision 1.3  2005/04/10 20:09:41  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:16  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:47:45  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/11/03 15:49:50  colinmacleod
 * Changed todo comments to TODO: all caps.
 *
 * Revision 1.3  2004/07/13 19:42:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/03/21 21:16:21  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:58:20  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 13:57:23  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/03/03 16:42:37  colin
 * added created, modifiedBy and userName (applied it) to event
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.8  2003/02/04 17:41:45  colin
 * renamed dayevent to allDayEvent
 *
 * Revision 1.7  2002/09/06 07:20:00  colin
 * changed country implementation to new AddressCountry bean
 *
 * Revision 1.6  2002/08/02 16:21:13  jano
 * added Public Holiday
 *
 * Revision 1.5  2002/07/30 08:06:58  jano
 * added field TYPE to Event
 *
 * Revision 1.4  2002/07/22 12:59:30  jano
 * change Calendar to Calendar in EventDO
 *
 * Revision 1.3  2002/07/15 11:59:25  jano
 * added property DAYEVENT to EventBean
 *
 * Revision 1.2  2002/07/11 13:10:09  jano
 * commiting all calendar files for sure
 *
 * Revision 1.1  2002/07/05 09:34:28  jano
 * first version of EventDO
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar.event;

import java.util.Calendar;

import com.ivata.groupware.container.persistence.TimestampDO;

/**
 * <p>Event for Calendar</p>
 *
 * @since 2002-07-05
 * @author Jan Boros <janboros@sourceforge.net>
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table="calendar_event"
 * @hibernate.cache
 *      usage="read-write"
 */
public class EventDO extends TimestampDO {
    /**
     * <p>Store whether or not this is an 'all-day' event.</p>
     */
    private boolean allDayEvent;

    /**
     * <p>Store the description with additional information about the event.</p>
     */
    private String description;

    /**
     * <p>Store the finish Calendar/time of the event.</p>
     */
    private Calendar finish;

    /**
     * <p>Store the start Calendar/time of the event.</p>
     */
    private Calendar start;

    /**
     * <p>Store the subject of this event.</p>
     */
    private String subject;

    /**
     * <p>Get the description with additional information about the event.</p>
     *
     * @return current value of description.
     * @hibernate.property
     */
    public final String getDescription() {
        return description;
    }

    /**
     * <p>Get the time the event will finish.</p>
     *
     * @return time the event will finish.
     * @hibernate.property
     */
    public final Calendar getFinish() {
        return finish;
    }

    /**
     * <p>Get the time the event will start.</p>
     *
     * @return time the event will start.
     * @hibernate.property
     */
    public final Calendar getStart() {
        return start;
    }

    /**
     * <p>Get the brief subject describing the purpose of the event.</p>
     *
     * @return current value of subject.
     * @hibernate.property
     */
    public final String getSubject() {
        return subject;
    }

    /**
     * <p>Get whether or not this is an 'all-day' event.</p>
     *
     * @return <code>true</code> if this is an 'all-day' event,
     *     otherwise <code>false</code>.
     *
     * TODO: change the column in the table
     * @hibernate.property
     *      column="dayevent"
     */
    public boolean isAllDayEvent() {
        return allDayEvent;
    }

    /**
     * <p>Set whether or not this is an 'all-day' event.</p>
     *
     * @param allDayEvent <code>true</code> if this is an 'all-day' event,
     *     otherwise <code>false</code>.
     */
    public final void setAllDayEvent(final boolean allDayEvent) {
        this.allDayEvent = allDayEvent;
    }

    /**
     * <p>Set the description with additional information about the event.</p>
     *
     * @param description new value of description.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * <p>Set the time the event will finish.</p>
     */
    public final void setFinish(final Calendar finish) {
        this.finish = finish;
    }

    /**
     * <p>Set the time the event will start.</p>
     *
     * @param start new time the event will start.
     */
    public final void setStart(final Calendar start) {
        this.start = start;
    }
    /**
     * <p>Set the brief subject describing the purpose of the event.</p>
     *
     * @param subject new value of subject.
     */
    public final void setSubject(final String subject) {
        this.subject = subject;
    }

}

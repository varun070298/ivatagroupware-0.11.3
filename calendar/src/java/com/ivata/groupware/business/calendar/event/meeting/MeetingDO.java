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
 * $Log: MeetingDO.java,v $
 * Revision 1.2  2005/04/09 17:19:16  colinmacleod
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
 *
 * Revision 1.3  2004/03/21 21:16:22  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:07:29  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:20  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2004/01/19 21:13:14  colin
 * Removed minutes for ivata groupware v0.9
 *
 * Revision 1.2  2003/10/15 13:57:23  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.11  2003/02/13 08:45:42  colin
 * conversion to Struts/popups
 *
 * Revision 1.10  2003/02/04 17:41:57  colin
 * copyright notice
 *
 * Revision 1.9  2002/10/24 10:00:28  peter
 * added libraryItemTopic field
 *
 * Revision 1.8  2002/10/22 08:24:19  peter
 * added libraryItemId field
 *
 * Revision 1.7  2002/10/14 16:00:48  colin
 * changed checkLibraryItemExist to checkLibraryItemExists
 *
 * Revision 1.6  2002/09/10 08:21:12  jano
 * field libraryItemExist was add, its information if we allready have minutes for that meeting, it's mean library item
 *
 * Revision 1.5  2002/09/09 14:28:57  jano
 * libraryItemId did add to meetingDO|
 *
 * Revision 1.4  2002/09/03 15:05:59  jano
 * i don't know
 *
 * Revision 1.3  2002/08/16 13:38:57  jano
 * *** empty log message ***
 *
 * Revision 1.2  2002/08/07 16:18:55  jano
 * *** empty log message ***
 *
 * Revision 1.1  2002/06/21 12:45:01  colin
 * restructured com.ivata.groupware.web
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar.event.meeting;


import java.util.Set;

import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.calendar.event.EventDO;


/**
 * <p>Represents a meeting which takes place in the organization. This is
 * responsible for storing the details of the meeting, and interacting with the
 * library to create the minutes and agenda.</p>
 *
 * @since 2002-06-18
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 * @see MeetingBean
 *
 * @hibernate.joined-subclass
 *      table="meeting"
 * @hibernate.joined-subclass-key
 *      column="calendar_event"
 */
public class MeetingDO extends EventDO {
    /**
     * <p>Stores all people who will attend the meeting.</p>
     */
    private Set attendees;

    /**
     * <p>Stores categories of meeting minutes.</p>
     */
    private Set categories;

    /**
     * <p>Stores person in charge of the meeting.</p>
     */
    private PersonDO chairPerson;

    /**
     * <p>Stores the client-specific text describing the location of the
     * meeting.</p>
     */
    private String location;

    /**
     * <p>Get all people who will attend the meeting.</p>
     *
     * @return people who will attend the meeting.
     *
     * @nosuch.hibernate.set
     * @nosuch.hibernate.collection-key
     *      column="meeting"
     * @nosuch.hibernate.collection-one-to-many
     *      class="com.ivata.groupware.business.calendar.event.meeting.attendee.AttendeeDO"
     */
    public final Set getAttendees() {
        return attendees;
    }

    /**
     * <p>Get all meeting categories.</p>
     *
     * @return all meeting categories.
     *
     * @nosuch.hibernate.set
     * @nosuch.hibernate.collection-key
     *      column="meeting"
     * @nosuch.hibernate.collection-one-to-many
     *      class="com.ivata.groupware.business.calendar.event.meeting.category.MeetingCategoryDO"
     */
    public final Set getCategories() {
        return categories;
    }

    /**
     * <p>Get the person who is in charge of the meeting.</p>
     *
     * @return current value of the person in charge of the meeting.
     * @hibernate.many-to-one
     *      column="chair_person"
     */
    public PersonDO getChairPerson() {
        return chairPerson;
    }

    /**
     * <p>Get the location of the meeting.</p>
     *
     * @return current value of client-specific location text.
     * @hibernate.property
     */
    public final String getLocation() {
        return location;
    }

    /**
     * <p>Set all people who will attend the meeting.</p>
     *
     * @param people who will attend the meeting.
     */
    public final void setAttendees(final Set attendees) {
        this.attendees = attendees;
    }

    /**
     * <p>Set all of meeting categories.</p>
     *
     * @param categories new value of category names.
     */
    public final void setCategories(final Set categories) {
        this.categories = categories;
    }

    /**
     * <p>Set the person who is in charge of the meeting.</p>
     *
     * @param chairPerson new value of the person in
     *     charge of the meeting.
     */
    public final void setChairPerson(final PersonDO chairPerson) {
        this.chairPerson = chairPerson;
    }

    /**
     * <p>Set the location of the meeting.</p>
     *
     * @param location new value of client-specific location text.
     */
    public final void setLocation(final String location) {
        this.location = location;
    }
}

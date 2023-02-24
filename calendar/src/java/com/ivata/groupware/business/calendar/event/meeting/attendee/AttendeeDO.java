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
 * $Log: AttendeeDO.java,v $
 * Revision 1.3  2005/04/10 20:09:41  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
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
 * Revision 1.5  2003/11/03 11:28:48  jano
 * commiting calendar,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/28 13:10:23  jano
 * commiting calendar,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.3  2003/10/15 14:03:06  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:57:23  colin
 * fixing for XDoclet
 *
 * Revision 1.3  2003/05/01 12:13:22  jano
 * tidy up names of sequeneces
 *
 * Revision 1.2  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.9  2003/02/04 17:41:57  colin
 * copyright notice
 *
 * Revision 1.8  2002/09/10 09:05:44  jano
 * fixing ejbCreate method
 *
 * Revision 1.6  2002/08/14 09:25:00  jano
 * *** empty log message ***
 *
 * Revision 1.5  2002/07/15 11:59:25  jano
 * added property DAYEVENT to EventBean
 *
 * Revision 1.4  2002/07/05 10:29:10  colin
 * changes trying to move project to jano
 *
 * Revision 1.3  2002/07/04 12:28:04  colin
 * resolved conficts
 *
 * Revision 1.2  2002/07/04 12:11:34  jano
 * *** empty log message ***
 *
 * Revision 1.1  2002/06/21 12:45:26  colin
 * restructured com.ivata.groupware.web
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar.event.meeting.attendee;

import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.calendar.event.meeting.MeetingDO;
import com.ivata.groupware.container.persistence.BaseDO;

/**
 * <p>Every meeting in ivata groupware may have many attendees, each of whom may
 * or may not be intranet users themselves. This class records which of the
 * invited attendees have confirmed they will attend.</p>
 *
 * @since 2002-06-18
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table="meeting_attendee"
 * @hibernate.cache
 *      usage="read-write"
 */
public class AttendeeDO  extends BaseDO {
    /**
     * <p>Find out whether or not this attendess has confirmed s/he will attend.</p>
     */
    private boolean confirmed;

    /**
     * <p>The meeting this attendee refers to.</p>
     */
    private MeetingDO meeting;

    /**
     * <p>Person who should attend.</p>
     */
    private PersonDO person;
    /**
     * <p>Find out whether or not this attendess has confirmed s/he will attend.</p>
     *
     * @return <code>true</code> if the attendee will attend, otherwise
     * <code>false</code>.
     *
     * @hibernate.property
     */
    public final boolean getConfirmed() {
        return confirmed;
    }
    /**
     * <p>Get the meeting this attendee refers to.</p>
     *
     * @return the meeting this attendee refers to.
     *
     * @hibernate.many-to-one
     *      class = com.ivata.groupware.business.calendar.event.EventDO
     */
    public final MeetingDO getMeeting() {
        return meeting;
    }
    /**
     * <p>Get person who should attend.</p>
     *
     * @return person who should attend.
     *
     * @hibernate.many-to-one
     */
    public PersonDO getPerson() {
        return person;
    }

    /**
     * <p>Set whether or not this attendess has confirmed s/he will attend.</p>
     *
     * @param confirmed <code>true</code> if the attendee will attend, otherwise
     * <code>false</code>.
     */
    public final void setConfirmed(final boolean confirmed) {
        this.confirmed = confirmed;
    }

    /**
     * <p>Set the meeting this attendee refers to.</p>
     *
     * @param meeting meeting this attendee refers to.
     */
    public final void setMeeting(final MeetingDO meeting) {
        this.meeting = meeting;
    }

    /**
     * <p>Set person who should attend.</p>
     *
     * @param person person who should attend.
     */
    public final void setPerson(final PersonDO person) {
        this.person = person;
    }
}

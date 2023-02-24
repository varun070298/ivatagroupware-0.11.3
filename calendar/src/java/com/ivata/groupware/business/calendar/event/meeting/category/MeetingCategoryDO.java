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
 * $Log: MeetingCategoryDO.java,v $
 * Revision 1.3  2005/04/10 20:09:42  colinmacleod
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
 * Revision 1.3  2003/10/15 14:03:35  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:57:23  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.8  2003/02/04 17:41:57  colin
 * copyright notice
 *
 * Revision 1.7  2002/09/10 09:12:31  jano
 * fixing ejbCreate method
 *
 * Revision 1.5  2002/07/15 11:59:25  jano
 * added property DAYEVENT to EventBean
 *
 * Revision 1.4  2002/07/05 10:29:10  colin
 * changes trying to move project to jano
 *
 * Revision 1.3  2002/07/04 12:42:04  colin
 * resolved conflicts in cvs
 *
 * Revision 1.2  2002/07/04 12:11:34  jano
 * *** empty log message ***
 *
 * Revision 1.1  2002/06/21 12:45:37  colin
 * restructured com.ivata.groupware.web
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar.event.meeting.category;

import java.util.Set;

import com.ivata.groupware.business.calendar.event.meeting.MeetingDO;
import com.ivata.groupware.container.persistence.BaseDO;

/**
 * <p>The many agenda points which make up the meeting are grouped into
 * categories by this class.</p>
 *
 * @since 2002-06-18
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table="meeting_category"
 * @hibernate.cache
 *      usage="read-write"
 */
public class MeetingCategoryDO  extends BaseDO {

    /**
     * <p>The agenda points contained int his category.</p>
     */
    private Set agendaPoints;

    /**
     * <p>The meeting this category refers to.</p>
     */
    private MeetingDO meeting;
    /**
     * <p>Name of this meeting category.</p>
     */
    private String name;
    /**
     * <p>Get the agenda points contained int his category.</p>
     *
     * @return the agenda points contained int his category.
     *
     * @hibernate.set
     * @hibernate.collection-key
     *      column="category"
     * @hibernate.collection-one-to-many
     *      class="com.ivata.groupware.business.calendar.event.meeting.agendapoint.AgendaPointDO"
     */
    public final Set getAgendaPoints() {
        return agendaPoints;
    }
    /**
     * <p>Get the meeting this category refers to.</p>
     *
     * @return the meeting this category refers to.
     *
     * @hibernate.many-to-one
     */
    public final MeetingDO getMeeting() {
        return meeting;
    }
    /**
     * <p>Get the name of this meeting category.</p>
     *
     * @return name of this meeting category
     *
     * @hibernate.property
     */
    public final String getName() {
        return name;
    }

    /**
     * <p>Aet the agenda points contained int his category.</p>
     *
     * @param agendaPoints the agenda points contained int his category.
     */
    public final void setAgendaPoints(final Set agendaPoints) {
        this.agendaPoints = agendaPoints;
    }

    /**
     * <p>Set the meeting this category refers to.</p>
     *
     * @param meeting the meeting this category refers to.
     */
    public final void setMeeting(final MeetingDO meeting) {
        this.meeting = meeting;
    }

    /**
     * <p>Set the name of this meeting category.</p>
     *
     * @param name name of this meeting category
     */
    public final void setName(final String name) {
        this.name = name;
    }
}

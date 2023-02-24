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
 * $Log: AgendaPointDO.java,v $
 * Revision 1.3  2005/04/10 20:09:41  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
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
 * Revision 1.5  2003/11/03 11:28:48  jano
 * commiting calendar,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/28 13:10:23  jano
 * commiting calendar,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.3  2003/10/15 14:02:20  jano
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
 * Revision 1.7  2003/02/04 17:41:57  colin
 * copyright notice
 *
 * Revision 1.6  2002/09/10 09:14:27  jano
 * fixing ejbCreate method
 *
 * Revision 1.5  2002/07/15 11:59:25  jano
 * added property DAYEVENT to EventBean
 *
 * Revision 1.4  2002/07/05 10:29:10  colin
 * changes trying to move project to jano
 *
 * Revision 1.3  2002/07/04 12:24:05  colin
 * *** empty log message ***
 *
 * Revision 1.2  2002/07/04 12:11:34  jano
 * *** empty log message ***
 *
 * Revision 1.1  2002/06/21 12:45:15  colin
 * restructured com.ivata.groupware.web
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar.event.meeting.agendapoint;


import com.ivata.groupware.business.calendar.event.meeting.category.MeetingCategoryDO;
import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>Each meeting may have many agenda points. These will appear as points on
 * the agenda before the meeting, and as headings in the library item of the
 * minutes after the meeting.</p>
 *
 * @since 2002-06-18
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table="meeting_agenda_point"
 * @hibernate.cache
 *      usage="read-write"
 */
public class AgendaPointDO  extends BaseDO {

    /**
     * <p>Get category which contains this agenda point.</p>
     */
    private MeetingCategoryDO category;

    /**
     * <p>The text of the minutes describing the meeting.</p>
     */
    private String minutesText;
    /**
     * <p>The name of this agenda point.</p>
     */
    private String name;
    /**
     * <p>Get the category which contains this agenda point.</p>
     *
     * @return category which contains this agenda point.
     *
     * @hibernate.many-to-one
     */
    public final MeetingCategoryDO getCategory() {
        return category;
    }
    /**
     * <p>Get the text of the minutes describing the meeting.</p>
     *
     * @return text of the minutes describing the meeting.
     * @hibernate.property
     */
    public final String getMinutesText() {
        return minutesText;
    }
    /**
     * <p>Get the name of this agenda point.</p>
     *
     * @return the agenda point name.
     *
     * @hibernate.property
     */
    public final String getName() {
        return name;
    }

    /**
     * <p>Set the category which contains this agenda point.</p>
     *
     * @param category new value of category which contains this agenda point.
     */
    public final void setCategory(final MeetingCategoryDO category) {
        this.category = category;
    }

    /**
     * <p>Set the text of the minutes describing the meeting.</p>
     *
     * @param minutesText new text of the minutes describing the meeting.
     */
    public final void setMinutesText(final String minutesText) {
        this.minutesText = minutesText;
    }

    /**
     * <p>Set the name of this agenda point.</p>
     *
     * @param name the new agenda point name.
     */
    public final void setName(final String name) {
        this.name = name;
    }
}

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
 * $Log: Calendar.java,v $
 * Revision 1.3  2005/04/10 20:09:40  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:16  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:47:40  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/11/12 18:17:26  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 16:08:08  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/07/13 19:42:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.1  2004/03/27 10:31:25  colinmacleod
 * Split off business logic from remote facades to POJOs.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar;

import java.util.Collection;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.calendar.event.EventDO;
import com.ivata.groupware.business.calendar.event.meeting.MeetingDO;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationErrors;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since 24-Mar-2004
 * @version $Revision: 1.3 $
 */
public interface Calendar {
    public final static String BUNDLE_PATH = "calendar";

    /** <p>method using to add NEW event</p>
     *
     * @param eventDO event to ADD
     * @return added event
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public EventDO addEvent(final SecuritySession securitySession,
            final EventDO eventDO)
        throws SystemException;

    /**
     * <p>method using to modifi event</p>
     *
     * @param eventDO event to AMEND
     * @return amend event
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public EventDO amendEvent(final SecuritySession securitySession,
            final EventDO eventDO)
        throws SystemException;

    /**
     * <p>find eventDO for that id</p>
     *
     *
     * @param day evens for this DAY
     * @return Vector of events
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public EventDO findEventByPrimaryKey(final SecuritySession securitySession,
            final String id)
            throws SystemException;

    /**
     * <p>this method giving Events for DAY. DAY has to be initial at clients site
     * with DAY, MOUTH, YEAR</p>
     *
     * @param day evens for this DAY
     * @return Vector of events
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public Collection findEventsForDay(final SecuritySession securitySession,
            final java.util.Calendar day)
            throws SystemException;

    /**
     * <p>method usig to remove event</p>
     *
     * @param eventDO event to REMOVE
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public void removeEvent(final SecuritySession securitySession,
            final EventDO event)
            throws SystemException;

    /**
     * <p>Confirm all of the elements of the event are present and valid.</p>
     *
     * @param eventDO data object to check for consistency and
     *     completeness.
     * @return a collection of validation errors if any of the
     *     mandatory fields are missing, or if fields contain invalid values.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public ValidationErrors validate(final SecuritySession securitySession,
            final EventDO eventDO)
            throws SystemException;

    /**
     * <p>Confirm all of the elements of the meeting and associated event are
     * present and valid.</p>
     *
     * @param meetingDO data object to check for consistency and
     *     completeness.
     * @param locale locale to show field errors for.
     * @return a collection of validation errors if any of the
     *     mandatory fields are missing, or if fields contain invalid values.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public ValidationErrors validate(final SecuritySession securitySession,
            final MeetingDO meetingDO)
            throws SystemException;
}
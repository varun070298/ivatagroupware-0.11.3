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
 * $Log: CalendarBean.java,v $
 * Revision 1.3  2005/04/10 20:09:40  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:16  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:47:42  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.8  2004/11/12 18:17:26  colinmacleod
 * Ordered imports.
 *
 * Revision 1.7  2004/11/12 16:08:08  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.6  2004/07/13 19:42:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.5  2004/03/27 10:31:25  colinmacleod
 * Split off business logic from remote facades to POJOs.
 *
 * Revision 1.4  2004/03/21 21:16:21  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/10 19:57:22  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:07:29  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:19  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.7  2004/01/19 21:13:14  colin
 * Removed minutes for ivata groupware v0.9
 *
 * Revision 1.6  2003/12/08 15:52:45  jano
 * fixing calendar functionaality
 *
 * Revision 1.5  2003/11/03 11:28:48  jano
 * commiting calendar,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/28 13:10:23  jano
 * commiting calendar,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.3  2003/10/15 13:58:58  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:57:23  colin
 * fixing for XDoclet
 *
 * Revision 1.5  2003/05/02 10:28:48  peter
 * added locale to validation
 *
 * Revision 1.4  2003/03/13 17:09:14  peter
 * fixed: day events finder is timeZone aware
 *
 * Revision 1.3  2003/03/03 16:42:07  colin
 * now sets user name and modified by as appropriate
 *
 * Revision 1.2  2003/02/26 17:11:45  peter
 * fixed the paths for resourcBundles in validation methods
 *
 * Revision 1.1  2003/02/25 08:09:10  colin
 * moved to business
 *
 * Revision 1.25  2003/02/20 20:24:29  colin
 * improved validation by adding ValidationField and ValidationException
 *
 * Revision 1.24  2003/02/13 08:45:42  colin
 * conversion to Struts/popups
 *
 * Revision 1.23  2003/02/05 14:50:42  colin
 * added validate method
 *
 * Revision 1.22  2003/02/04 17:43:46  colin
 * copyright notice
 *
 * Revision 1.21  2002/09/25 15:33:10  jano
 * it's not nesesery to remove event when you are removing meeting
 *
 * Revision 1.20  2002/09/25 08:25:36  jano
 * fixing when event finishing before starting
 *
 * Revision 1.19  2002/09/11 11:05:58  jano
 * new method findMeetingByPrimaryKey
 *
 * Revision 1.18  2002/09/10 15:00:48  jano
 * add new method findMetingsWithoutMinutes
 *
 * Revision 1.17  2002/09/10 08:19:45  jano
 * amendMeeting was fixed
 *
 * Revision 1.16  2002/08/29 15:54:00  jano
 * removeMeeting working
 *
 * Revision 1.15  2002/08/29 14:53:23  jano
 * *** empty log message ***
 *
 * Revision 1.14  2002/08/29 14:46:40  jano
 * new methods amendMeeting removeMeeting
 *
 * Revision 1.13  2002/08/16 14:58:08  jano
 * add mehod findMeetingByEventId
 *
 * Revision 1.12  2002/08/14 09:24:38  jano
 * *** empty log message ***
 *
 * Revision 1.11  2002/08/07 16:18:40  jano
 * *** empty log message ***
 *
 * Revision 1.10  2002/08/01 06:36:58  jano
 * add method findEventByPrimaryKey to calendarBean
 *
 * Revision 1.9  2002/07/26 06:53:48  jano
 * problem with GregorianCalendra.roll method so I change to SET method
 *
 * Revision 1.8  2002/07/22 12:59:30  jano
 * change DATE to GregorianCalendar in EventDO
 *
 * Revision 1.7  2002/07/11 13:10:09  jano
 * commiting all calendar files for sure
 *
 * Revision 1.6  2002/07/04 14:57:21  jano
 * testing watch on/cvs edit
 *
 * Revision 1.5  2002/07/04 14:56:21  jano
 * testing watch on
 *
 * Revision 1.4  2002/07/04 14:49:19  jano
 * test with cvs edit
 *
 * Revision 1.3  2002/07/04 12:07:31  jano
 * findEventForDay was added to calendar Bean
 *
 * Revision 1.2  2002/07/03 15:26:53  jano
 * doing on calendar and calendar.event bean
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar;


import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.calendar.event.EventDO;
import com.ivata.groupware.business.calendar.event.meeting.MeetingDO;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationErrors;


/**
 * @author Jan Boros <janboros@sourceforge.net>
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/**
 * <p>Calendar facade to enter, retrieve and delete events and meetings.</p>
 *
 * @since 2002-07-03
 * @author Jan Boros <janboros@sourceforge.net>
 * @version $Revision: 1.3 $
 *
 * @ejb.bean
 *      name="Calendar"
 *      display-name="Calendar"
 *      type="Stateless"
 *      view-type="remote"
 *      jndi-name="CalendarRemote"
 *
 * @ejb.transaction
 *      type = "Required"
 *
 *  @ejb.home
 *      generate="false"
 *      remote-class="com.ivata.groupware.business.calendar.CalendarRemoteHome"
 *
 *  @ejb.interface
 *      remote-class="com.ivata.groupware.business.calendar.CalendarRemote"
 */
public class CalendarBean implements SessionBean, Calendar {
    /**
     * <p>Provides the session bean with container-specific information.</p>
     */
    SessionContext sessionContext;

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
            throws SystemException {
        return getCalendar().addEvent(securitySession, eventDO);
    }

    /** <p>method using to modifi event</p>
     *
     * @param eventDO event to AMEND
     * @return amend event
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public EventDO amendEvent(final SecuritySession securitySession,
            final EventDO eventDO)
            throws SystemException {
        return getCalendar().amendEvent(securitySession, eventDO);
    }

    /**
     * <p>Called by the container to notify an entity object it has been
     * activated.</p>
     */
    public void ejbActivate() {}

    /**
     * <p>Called by the container just after the bean has been created.</p>
     *
     * @exception CreateException if any error occurs. Never thrown by this
     *     class.
     *
     * @ejb.create-method
     */
    public void ejbCreate() throws CreateException {}

    /**
     * <p>Called by the container to notify the entity object it will be
     * deactivated. Called just before deactivation.</p>
     */
    public void ejbPassivate() {}

    /**
     * <p>This method is called by the container when the bean is about
     * to be removed.</p>
     *
     * <p>This method will be called after a client calls the <code>remove</code>
     * method of the remote/local home interface.</p>
     *
     * @throws RemoveException if any error occurs. Currently never thrown by
     *     this class.
     */
    public void ejbRemove() {}

    /**
     * <p>find eventDO for that Id</p>
     *
     *
     * @param
     * @return Vector of events
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public EventDO findEventByPrimaryKey(final SecuritySession securitySession,
            final String id)
            throws SystemException {
        return getCalendar().findEventByPrimaryKey(securitySession, id);
    }

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
            throws SystemException {
        return getCalendar().findEventsForDay(securitySession, day);
    }

    /**
     * Get the Calendar implementation.
     */
    private Calendar getCalendar()
            throws SystemException {
        PicoContainer container = PicoContainerFactory.getInstance()
            .getGlobalContainer();
        return (Calendar) container.getComponentInstance(Calendar.class);
    }

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
            throws SystemException {
        getCalendar().removeEvent(securitySession, event);
    }

    /**
     * <p>Set up the context for this entity object. The session bean stores the
     * context for later use.</p>
     *
     * @param sessionContext the new context which the session object should
     *     store.
     */
    public final void setSessionContext(final SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

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
            throws SystemException {
        return getCalendar().validate(securitySession, eventDO);
    }

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
            throws SystemException {
        return getCalendar().validate(securitySession, meetingDO);
    }
}

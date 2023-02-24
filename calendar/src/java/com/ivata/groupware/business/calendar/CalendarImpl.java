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
 * $Log: CalendarImpl.java,v $
 * Revision 1.3  2005/04/10 20:43:45  colinmacleod
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
 * Revision 1.5  2004/11/12 18:17:26  colinmacleod
 * Ordered imports.
 *
 * Revision 1.4  2004/11/12 16:08:08  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.3  2004/11/03 15:49:49  colinmacleod
 * Changed todo comments to TODO: all caps.
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.TreeSet;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.BusinessLogic;
import com.ivata.groupware.business.calendar.event.EventDO;
import com.ivata.groupware.business.calendar.event.meeting.MeetingDO;
import com.ivata.groupware.business.calendar.event.publicholiday.PublicHolidayDO;
import com.ivata.groupware.container.persistence.QueryPersistenceManager;
import com.ivata.groupware.container.persistence.TimestampDOHandling;
import com.ivata.mask.Mask;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationError;
import com.ivata.mask.validation.ValidationErrors;
import com.ivata.mask.validation.ValidationException;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since 24-Mar-2004
 * @version $Revision: 1.3 $
 */
public class CalendarImpl extends BusinessLogic implements Calendar {

    /**
     * <p>This class is used to sort the Events.</p>
     */
    private class EventComparator implements java.util.Comparator {

        /**
         * <p>Compare two objects (in this case, both are instances of
         * {@link com.ivata.groupware.business.calendar.event.EventDO
         * EventDO}) and return which is the higher priority.</p>
         *
         * @param o1 first instance of <code>Event</code> to be compared.
         * @param o2 second instance of <code>Event</code> to be compared.
         * @return a negative integer, or a positive integer as the first argument
         *     comes after, same as, or before the second.
         */
        public int compare(final Object o1,
            final Object o2) {
            EventDO event1 = (EventDO) o1;
            EventDO event2 = (EventDO) o2;
            long test1 = event1.getStart().getTime().getTime();
            long test2 = event2.getStart().getTime().getTime();

            // note: purposely don't allow an equals case here!! If two are
            //       equal, then only one will appear in the final set
            return (test1 > test2) ? 1 : -1;
        }
    }

    /**
     * Persistence manger used to store/retrieve data objects.
     */
    private QueryPersistenceManager persistenceManager;

    /**
     * Construct a new address book.
     *
     * @param persistenceManager used to store objects in db.
     */
    public CalendarImpl(QueryPersistenceManager persistenceManager,
            MaskFactory maskFactory) {
        this.persistenceManager = persistenceManager;
        this.maskFactory = maskFactory;
    }
    private MaskFactory maskFactory;


    /* (non-Javadoc)
     * @see com.ivata.groupware.business.calendar.Calendar#addEvent(com.ivata.groupware.business.calendar.event.EventDO)
     */
    public EventDO addEvent(final SecuritySession securitySession,
            final EventDO event)
            throws SystemException {
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            // before creating the event, check we have reasonable data
            ValidationErrors errors = validate(securitySession, event);
            if (!errors.isEmpty()) {
                throw new ValidationException(errors);
            }
            TimestampDOHandling.add(securitySession, event);
            return (EventDO) persistenceManager.add(persistenceSession, event);
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }

    /**
     * <p>method finding meeting without minutes, it's mean that meeting hasn't
     * library item.
     * meeting has to be before input day.</p>
     *
     * @param day we are finding meetings before this day
     * @return Vector of meetingDOs
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
/*    public java.util.Vector findMeetingsWithoutMinutes(java.util.Calendar day) {
        Vector returnItems = new Vector();

        try {
            MeetingLocalHome homeMeeting = (MeetingLocalHome)
                ApplicationServer.findLocalHome("MeetingLocal",
                    MeetingLocalHome.class);

            Calendar tmpday = new java.util.Calendar();
            tmpday.setTime(day.getTime());

            Collection meetings = homeMeeting.findMeetingWithoutMinutes(new Timestamp(tmpday.getTime().getTime()));

            for (Iterator i = meetings.iterator();
                i.hasNext();) {
                MeetingLocal meeting = (MeetingLocal) i.next();
                returnItems.add(meeting.getDO());
            }

            return returnItems;

        } catch (NamingException e) {
            throw new EJBException(e);
        } catch (FinderException e) {
            throw new EJBException(e);
        }
    }*/


    /* (non-Javadoc)
     * @see com.ivata.groupware.business.calendar.Calendar#amendEvent(com.ivata.groupware.business.calendar.event.EventDO)
     */
    public EventDO amendEvent(final SecuritySession securitySession,
            final EventDO event)
            throws SystemException {
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            // before changing the event, check we have reasonable data
            ValidationErrors errors = validate(securitySession, event);
            if (!errors.isEmpty()) {
                throw new ValidationException(errors);
            }
            TimestampDOHandling.amend(securitySession, event);
            persistenceManager.amend(persistenceSession, event);
            return event;
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.business.calendar.Calendar#findEventByPrimaryKey(Integer)
     */
    public EventDO findEventByPrimaryKey(final SecuritySession securitySession,
            final String id)
            throws SystemException {
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            return (EventDO) persistenceManager.findByPrimaryKey(persistenceSession,
                    EventDO.class, id);
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.business.calendar.Calendar#findEventsForDay(java.util.Calendar)
     */
    public Collection findEventsForDay(final SecuritySession securitySession,
            final java.util.Calendar day)
            throws SystemException {
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            GregorianCalendar tmpday = new GregorianCalendar();
            TimeZone timeZone = day.getTimeZone();

            tmpday.setTime(day.getTime());
            tmpday.setTimeZone(timeZone);
            Timestamp start = new Timestamp(tmpday.getTime().getTime());

            tmpday.set(java.util.Calendar.DAY_OF_YEAR, tmpday.get(java.util.Calendar.DAY_OF_YEAR) + 1); // this move day field to next
            Timestamp finish = new Timestamp(tmpday.getTime().getTime());
            Collection events = persistenceManager.find(persistenceSession, "calendarEventByStartFinish",
                new Object[] {start, finish});
            // sort the events TODO: I would rather use the database to sort
            // but EJB QL still has no 'order by'
            TreeSet sorted = new TreeSet(new EventComparator());

            sorted.addAll(events);
            Collection returnItems = new ArrayList();
            // either return all items, or the count requested, whatever comes
            // first
            // TODO: I would rather use the database to limit the return but
            // EJB QL still has no 'limit'
            for (Iterator i = sorted.iterator();
                i.hasNext();) {
                EventDO eventDO = (EventDO) i.next();
                // changing the timeZone for start and end, for correct sorting
                // outside this method
                java.util.Calendar tmpStart = eventDO.getStart();
                if (tmpStart!=null) {
                    tmpStart.setTimeZone(timeZone);
                    eventDO.setStart(tmpStart);
                }
                java.util.Calendar tmpFinish = eventDO.getFinish();
                if (tmpFinish!=null) {
                    tmpFinish.setTimeZone(timeZone);
                    eventDO.setFinish(tmpFinish);
                }

                returnItems.add(eventDO);
            }
            return returnItems;
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.business.calendar.Calendar#removeEvent(com.ivata.groupware.business.calendar.event.EventDO)
     */
    public void removeEvent(final SecuritySession securitySession,
            final EventDO event)
            throws SystemException {
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            persistenceManager.remove(persistenceSession,
                    event.getClass(), event.getId());
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.business.calendar.Calendar#validateEvent(com.ivata.groupware.business.calendar.event.EventDO, java.util.Locale)
     */
    public ValidationErrors validate(final SecuritySession securitySession,
            final EventDO eventDO) {
        ValidationErrors errors = new ValidationErrors();
        Mask mask = maskFactory.getMask(EventDO.class);
        String fieldKey;

        String startFieldKey = "";
        String finishFieldKey = "";

        // the field names depend on the type
        if (eventDO instanceof PublicHolidayDO) {
            startFieldKey = "event.field.startDate.publicHoliday";
            finishFieldKey = "event.field.finishDate.publicHoliday";
        } else {
            startFieldKey = "event.field.startDate";
            finishFieldKey = "event.field.finishDate";
        }

        // start date is always mandatory
        if (eventDO.getStart() == null) {
            errors.add(new ValidationError(
                    "event",
                    Calendar.BUNDLE_PATH,
                    mask.getField("start"),
                    "errors.required"));
        }

        //start date/time must be before end date/time
        if (eventDO.getStart() != null && eventDO.getFinish()!= null &&
            eventDO.getStart().after(eventDO.getFinish())) {

            errors.add(new ValidationError(
                    "event",
                    Calendar.BUNDLE_PATH,
                    mask.getField("finish"),
                    "errors.calendar.startAfterFinish",
                    Arrays.asList(new String[] {
                        startFieldKey,
                        finishFieldKey})));
        }

        // subject is always mandatory
        if (StringHandling.isNullOrEmpty(eventDO.getSubject())) {
            // the field name depends on the type
            if (eventDO instanceof PublicHolidayDO) {
                fieldKey = "event.field.subject.publicHoliday";
            } else {
                fieldKey = "event.field.subject";
            }
            errors.add(new ValidationError(
                    "event",
                    Calendar.BUNDLE_PATH,
                    mask.getField("subject"),
                    "errors.required"));
        }
        return errors;
    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.business.calendar.Calendar#validateMeeting(com.ivata.groupware.business.calendar.event.meeting.MeetingDO, java.util.Locale)
     */
    public ValidationErrors validate(final
            SecuritySession securitySession,
            final MeetingDO meetingDO) {
        ValidationErrors errors = validate(securitySession, (EventDO) meetingDO);
        Mask mask = maskFactory.getMask(MeetingDO.class);

        // location is always mandatory
        if (StringHandling.isNullOrEmpty(meetingDO.getLocation())) {
            errors.add(new ValidationError(
                    "event",
                    Calendar.BUNDLE_PATH,
                    mask.getField("location"),
                    "errors.required"));
        }
        return errors;
    }

}

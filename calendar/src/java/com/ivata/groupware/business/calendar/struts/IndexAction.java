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
 * $Log: IndexAction.java,v $
 * Revision 1.3  2005/04/10 18:47:37  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:18  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:47:42  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.9  2004/12/31 18:27:43  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.8  2004/12/23 21:01:27  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.7  2004/11/12 18:19:14  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.6  2004/11/12 16:08:11  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.5  2004/11/03 15:31:50  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.4  2004/07/13 19:42:14  colinmacleod
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
 * Revision 1.1.1.1  2004/01/27 20:58:22  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.4  2003/11/13 16:07:26  jano
 * commitng everything to CVS
 * can deploy and application is ruuning, can login into
 *
 * Revision 1.3  2003/10/28 13:10:23  jano
 * commiting calendar,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 13:57:23  colin
 * fixing for XDoclet
 *
 * Revision 1.4  2003/04/03 13:26:35  jano
 * cosmetic change
 *
 * Revision 1.3  2003/03/13 17:08:04  peter
 * fixed: the sorting is now timeZone aware
 *
 * Revision 1.2  2003/02/28 09:36:51  jano
 * RuntimeException(e) -> IntrnetRuntimeException
 *
 * Revision 1.1  2003/02/24 19:09:22  colin
 * moved to business
 *
 * Revision 1.3  2003/02/20 13:04:33  peter
 * 24H time formats changed to simple ones
 *
 * Revision 1.2  2003/02/13 08:45:42  colin
 * conversion to Struts/popups
 *
 * Revision 1.1  2003/02/04 17:50:17  colin
 * first version in CVS
 *
 * Revision 1.1  2003/02/04 17:41:16  colin
 * first version in CVS
 *
 * Revision 1.6  2003/01/30 09:02:19  colin
 * updates for struts conversion
 *
 * Revision 1.5  2003/01/18 20:12:36  colin
 * fixes and changes to override new MaskAction method
 *
 * Revision 1.4  2003/01/10 10:29:46  jano
 * we need information about user who created group
 *
 * Revision 1.3  2003/01/09 10:50:54  jano
 * I need only one method for finding right for group
 *
 * Revision 1.2  2003/01/08 17:16:21  jano
 * We will use new methods for finding and changing rights for
 * GROUP of AddressBookRightsBean
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar.struts;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.admin.setting.SettingsDataTypeException;
import com.ivata.groupware.business.calendar.Calendar;
import com.ivata.groupware.business.calendar.event.EventDO;
import com.ivata.groupware.util.SettingDateFormatter;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.format.DateFormatterConstants;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>This action is  invoked from the main calendar index, to create
 * the appropriate view.</p>
 *
 * @since 2003-02-02
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 */
public class IndexAction extends MaskAction {
    private Calendar calendar;
    private SettingDateFormatter dateFormatter;
    private Settings settings;
    /**
     * TODO
     *
     * @param calendar
     * @param settings
     * @param dateFormatter
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public IndexAction(Calendar calendar, Settings settings, SettingDateFormatter
            dateFormatter, MaskFactory maskFactory,
            MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.calendar = calendar;
        this.settings = settings;
        this.dateFormatter = dateFormatter;
    }

    /**
     * <p>Invoked when the user views a calendar list.</p>
     *
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName current user name from session. .
     * @param settings valid, non-null settings from session.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     *
     *
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        UserDO user = securitySession.getUser();
        IndexForm indexForm = (IndexForm) form;
        TimeZone timeZone;
        timeZone =
            TimeZone.getTimeZone(
                settings.getStringSetting(
                    securitySession,
                    "i18nTimeZone",
                    user));

        GregorianCalendar day = indexForm.getCurrentDay();
        day.setTimeZone(timeZone);

        // see if there is a view from before
        Integer view = indexForm.getView();
        // request overrides the form
        Integer requestView = StringHandling.integerValue(request.getParameter("view"));
        if (requestView != null) {
            indexForm.setView(view = requestView);
        }

        // if this is the first trip to the calendar, or if you asked for it
        // make the current day today
        if ((view == null)
                || (request.getParameter("today") != null)) {
            day.setTime(new java.util.Date());
            day.set(GregorianCalendar.HOUR_OF_DAY, 0);
            day.set(GregorianCalendar.MINUTE, 0);
            day.set(GregorianCalendar.SECOND, 0);
            day.set(GregorianCalendar.MILLISECOND, 0);

            // if there is no view parameter, take the current view from the
            // settings
            if (view == null) {
                try {
                    indexForm.setView(view = settings.getIntegerSetting(securitySession,
                        "calendarDefaultView", user));
                } catch (SettingsDataTypeException e2) {
                    throw new SystemException(e2);
                }
            }
        }


        // if someone clicked to day link in week view
        if (request.getParameter("day") != null) {
            day.set( GregorianCalendar.DATE,
                new Integer(request.getParameter("day")).intValue());
            day.set( GregorianCalendar.MONTH,
                new Integer(request.getParameter("month")).intValue());
            day.set( GregorianCalendar.YEAR,
                new Integer(request.getParameter("year")).intValue());
            day.set(GregorianCalendar.HOUR_OF_DAY, 0);
            day.set(GregorianCalendar.MINUTE, 0);
            day.set(GregorianCalendar.SECOND, 0);
            day.set(GregorianCalendar.MILLISECOND, 0);
        }

        // sort out the help key, include page, window size and events from
        // the view

        // ------- DAY -------
        if (IndexFormConstants.VIEW_DAY.equals(view)) {
            indexForm.setViewPage("/calendar/dayview.jsp");
            indexForm.setHelpKey("calendar.day");

            // look for next, previous day
            if (request.getParameter("next") != null) {
                day.set(GregorianCalendar.DAY_OF_YEAR,
                    day.get(GregorianCalendar.DAY_OF_YEAR) + 1);
            } else if (request.getParameter("previous") != null) {
                day.set(GregorianCalendar.DAY_OF_YEAR,
                    day.get(GregorianCalendar.DAY_OF_YEAR)-1);
            }
            Map eventsDay = new HashMap();
            Map [] events = {eventsDay};
            indexForm.setEvents(events);
            Vector allDayEventsDay = new Vector();
            Vector [] allDayEvents = {allDayEventsDay};
            indexForm.setAllDayEvents(allDayEvents);
            findDayEvents(securitySession, indexForm, day, allDayEventsDay, eventsDay);

        } else {
            // in the settings table, 1=MONDAY  0=SUNDAY  --%>
            int firstDay;
            firstDay =
                (settings
                    .getIntegerSetting(
                        securitySession,
                        "calendarFirstWeekDay",
                        user))
                    .intValue();

            // if this is a 5 day view, monday is always the first
            int numberOfDays = 7;
            if (IndexFormConstants.VIEW_WORK_WEEK.equals(view)) {
                firstDay = 1;
                numberOfDays = 5;
            }

            // now work out the first day of the week to show - note for
            // month/year views it is only the day of the week for this
            // day which is significant (not its actual date)
            GregorianCalendar firstWeekDay = new GregorianCalendar(timeZone);
            GregorianCalendar firstMonthDay = null;

            // if this is month view, go to the start of the month
            if (IndexFormConstants.VIEW_MONTH.equals(view)) {
                // look for next, previous month
                if (request.getParameter("next") != null) {
                    day.set(GregorianCalendar.MONTH,
                        day.get(GregorianCalendar.MONTH) + 1);
                } else if (request.getParameter("previous") != null) {
                    day.set(GregorianCalendar.MONTH,
                        day.get(GregorianCalendar.MONTH) - 1);
                }

                firstMonthDay = new GregorianCalendar(timeZone);
                firstMonthDay.setTime(day.getTime());
                firstMonthDay.set(GregorianCalendar.DAY_OF_MONTH, 1);
                firstWeekDay.setTime(firstMonthDay.getTime());
            } else {
                // look for next, previous week
                if (request.getParameter("next") != null) {
                    day.set(GregorianCalendar.DAY_OF_YEAR,
                        day.get(GregorianCalendar.DAY_OF_YEAR) + 7);
                } else if (request.getParameter("previous") != null) {
                    day.set(GregorianCalendar.DAY_OF_YEAR,
                        day.get(GregorianCalendar.DAY_OF_YEAR)-7);
                }

                // if this is a week view, just go to the current day
                firstWeekDay.setTime(day.getTime());
            }

            firstWeekDay.set(GregorianCalendar.DAY_OF_YEAR,
                    firstWeekDay.get(GregorianCalendar.DAY_OF_YEAR)
                    - ((firstWeekDay.get(GregorianCalendar.DAY_OF_WEEK) + 6 - firstDay) % 7) );
            firstWeekDay.set(GregorianCalendar.HOUR_OF_DAY, 0);
            firstWeekDay.set(GregorianCalendar.MINUTE, 0);
            firstWeekDay.set(GregorianCalendar.SECOND, 0);
            firstWeekDay.set(GregorianCalendar.MILLISECOND, 0);
            indexForm.setFirstWeekDay(firstWeekDay);

            // temporary variable used for counting forward
            GregorianCalendar thisDay = new GregorianCalendar(timeZone);

            // basically now it breaks down into month or week
            // ------- MONTH -------
            if (IndexFormConstants.VIEW_MONTH.equals(view)) {
                // month view doesn't separate all day events from normal
                // events - just go thro' all events each day and add them
                // to a tree set sorted by hour

                // first work out how many weeks there are
                int dayOfWeek = firstWeekDay.get(GregorianCalendar.DAY_OF_WEEK);
                int dayBeforeOfWeek = dayOfWeek - 1;
                if (dayBeforeOfWeek < 0) {
                    dayBeforeOfWeek += 7;
                }
                int twoDaysBeforeOfWeek = dayBeforeOfWeek - 1;
                if (twoDaysBeforeOfWeek < 0) {
                    twoDaysBeforeOfWeek += 7;
                }
                int numberOfWeeks = 0;

                // if the first day is first day of week and 28 days in
                // month then we need 4 weeks
                if ((firstMonthDay.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == 28)
                        && (firstMonthDay.get(GregorianCalendar.DAY_OF_WEEK) == dayOfWeek)) {
                    numberOfWeeks = 4;
                } else if (((firstMonthDay.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)==31)
                            && (firstMonthDay.get(GregorianCalendar.DAY_OF_WEEK) == twoDaysBeforeOfWeek))
                        || ((firstMonthDay.get(GregorianCalendar.DAY_OF_WEEK) == dayBeforeOfWeek)
                            && (firstMonthDay.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) >=30))) {
                    // e.g. if you have a sunday as the first day of the week,
                    // friday as the first day with 31 days or saturday as
                    // the first day with 30/31 will mean 6 weeks
                    // if you have a monday as the first day of the week,
                    // saturday as the first day with 31 days or sunday as
                    // the first day with 30/31 will mean 6 weeks
                    numberOfWeeks = 6;
                // default: we need 5 weeks
                } else {
                    numberOfWeeks = 5;
                }

                Map [] events = new Map[numberOfWeeks];
                indexForm.setEvents(events);
                // go thro' all the weeks
                int thisMonth = firstMonthDay.get(GregorianCalendar.MONTH);
                thisDay.setTime(firstWeekDay.getTime());
                for (int week = 0; week < numberOfWeeks; ++week) {
                    events[week] = new HashMap();
                    // go thro' all the days
                    for (int dayNumber = 0; dayNumber < 7; ++dayNumber) {
                        // if this day is in the month, set the events
                        // (it might be in the month before or after -
                        // the value for their keys are null to set them
                        // apart)
                        if (thisMonth==thisDay.get(GregorianCalendar.MONTH)) {
                            // set the events for each day
                            EventHourComparator comparator = new EventHourComparator();
                            comparator.setDay(thisDay);
                            TreeSet eventsThisDay = new TreeSet(comparator);
                            eventsThisDay.addAll(calendar.findEventsForDay(securitySession, thisDay));
                            events[week].put(new Integer(dayNumber), eventsThisDay);
                        }
                        thisDay.set(GregorianCalendar.DAY_OF_YEAR,
                          thisDay.get(GregorianCalendar.DAY_OF_YEAR) + 1);
                    }
                }

                indexForm.setViewPage("/calendar/monthview.jsp");
                indexForm.setHelpKey("calendar.month");

            // ------- WEEK -------
            } else {
                // if chosen day is saturday or sunday make 7 days view
                if ((day.get(GregorianCalendar.DAY_OF_WEEK)==GregorianCalendar.SATURDAY)
                        || (day.get(GregorianCalendar.DAY_OF_WEEK)==GregorianCalendar.SUNDAY)) {
                    indexForm.setView(view = IndexFormConstants.VIEW_WEEK);
                }

                // go thro' all of the days and create the maps of tree sets
                // and the vectors
                Map [] events = new Map[numberOfDays];
                indexForm.setEvents(events);
                Vector [] allDayEvents = new Vector[numberOfDays];
                indexForm.setAllDayEvents(allDayEvents);
                thisDay.setTime(firstWeekDay.getTime());
                for (int index = 0; index < numberOfDays; ++index) {
                    events[index] = new HashMap();
                    allDayEvents[index] = new Vector();
                    findDayEvents(securitySession, indexForm, thisDay,
                            allDayEvents[index], events[index]);
                    thisDay.set(GregorianCalendar.DAY_OF_YEAR,
                      thisDay.get(GregorianCalendar.DAY_OF_YEAR) + 1);
                }

                indexForm.setViewPage("/calendar/weekview.jsp");
                indexForm.setHelpKey("calendar.week");
            }

        }

        // this form always returns to the input screen
        // I think this is not necesary because day is not new instance !!
        //indexForm.setCurrentDay(day);
        return null;
    }

    /**
     * <p>This helper method finds the events for the day specified and
     * splits them into all day events, and normal events. Normal events
     * are
     * sorted in a hash map, indexed by the hour of day they either start
     * or
     * (if they start on a day before the current day) end.</p>
     *
     * @param calendar remote interface instance to the server-side
     * calendar facade.
     * @param indexForm the current index form with details to be listed.
     * @param day defines the day to retrieve events for.
     * @param allDayEvents a <code>Vector</code> into which all of  the
     * events marked as all day events are loaded.
     * @param events <code>Map</code> into which normal events
     * are stored indexed by the time they happen.
     * @param userName current user name from session. .
     * @param settings valid, non-null settings from session.
     * @exception SystemException if there is any remote
     * excepotion accessing server-side methods.
     */
    private void findDayEvents(final SecuritySession securitySession,
            final IndexForm indexForm,
            final GregorianCalendar day,
            final Vector allDayEvents,
            final Map events) throws SystemException {
        // this comparator will be used to construct the tree maps
        EventHourComparator comparator = new EventHourComparator();
        comparator.setDay(day);

        Collection eventsForDay;
        eventsForDay = calendar.findEventsForDay(securitySession, day);
        String fromKey, toKey;

        // TODO:
        dateFormatter.setDateFormat(DateFormatterConstants.DATE_LONG_DAY);
        dateFormatter.setTimeFormat(DateFormatterConstants.TIME_SHORT);

        // sort all events into all day events and ordinary ones
        // all day events go into the special vector, ordinary ones
        // go into the map to sort them
        for (Iterator i = eventsForDay.iterator(); i.hasNext(); ) {
            EventDO event = (EventDO) i.next();

            Integer key = null;
            // does the event start before today and finish after today?
            // OR it is marked as an all day event?
            if (event.isAllDayEvent()
                    || (comparator.isAfterDay(event) && (event.getStart().before(day)))) {
                allDayEvents.add(event);

            // if ordinary events start today, it's the start time which is
            // important
            } else if (!event.getStart().before(day)) {
                // the start hour is our key
                key = new Integer(event.getStart().get(GregorianCalendar.HOUR_OF_DAY));

                // set up starting hour of the first event this day
                if (event.getStart().get(GregorianCalendar.HOUR_OF_DAY) < indexForm.getDayStartHour()) {
                    indexForm.setDayStartHour(event.getStart().get(GregorianCalendar.HOUR_OF_DAY));
                }
                // set up starting hour of the last event this day
                if (event.getStart().get(GregorianCalendar.HOUR_OF_DAY) > indexForm.getDayFinishHour()) {
                    indexForm.setDayFinishHour(event.getStart().get(GregorianCalendar.HOUR_OF_DAY));

                }

            // if the event finishes today but started before today, it is the
            // to time which is important
            } else if (!comparator.isAfterDay(event)) {
                // the finish hour is our key
                key = new Integer(event.getFinish().get(GregorianCalendar.HOUR_OF_DAY));

                // set up finishing hour of the last event at the day
                if (event.getFinish().get(GregorianCalendar.HOUR_OF_DAY) > indexForm.getDayFinishHour()) {
                    indexForm.setDayFinishHour(event.getFinish().get(GregorianCalendar.HOUR_OF_DAY));
                }
                if (event.getFinish().get(GregorianCalendar.HOUR_OF_DAY) < indexForm.getDayStartHour()) {
                    indexForm.setDayStartHour(event.getFinish().get(GregorianCalendar.HOUR_OF_DAY));
                }
            }
            // if this was a normal event, set it in the tree set
            if (key != null) {
                // see if there is already a tree set at this hour
                TreeSet treeSet = (TreeSet) events.get(key);
                if (treeSet == null) {
                    // if there is no tree set at this hour, make an empty one
                    events.put(key, treeSet = new TreeSet(comparator));
                }
                treeSet.add(event);
            }
        }
    }
}

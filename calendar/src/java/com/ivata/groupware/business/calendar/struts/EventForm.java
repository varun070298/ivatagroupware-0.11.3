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
 * $Log: EventForm.java,v $
 * Revision 1.5  2005/04/30 13:04:13  colinmacleod
 * Fixes reverting id type from String to Integer.
 *
 * Revision 1.4  2005/04/10 20:43:44  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.3  2005/04/09 17:19:18  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.2  2005/03/16 18:36:15  colinmacleod
 * Fixed clear button and pop-up closing.
 *
 * Revision 1.1.1.1  2005/03/10 17:47:43  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
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
 * Revision 1.5  2004/07/13 19:42:14  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:22  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/10 15:14:51  janboros
 * fixing bug
 *
 * Revision 1.2  2004/02/01 22:07:29  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:21  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/11/13 16:07:26  jano
 * commitng everything to CVS
 * can deploy and application is ruuning, can login into
 *
 * Revision 1.2  2003/10/15 13:57:23  colin
 * fixing for XDoclet
 *
 * Revision 1.8  2003/05/02 10:30:03  peter
 * fixed validation of times when dates the equal
 *
 * Revision 1.7  2003/04/14 07:10:15  peter
 * fixed helpKey logic
 *
 * Revision 1.6  2003/03/21 09:35:38  peter
 * fixed check for event type before validation
 *
 * Revision 1.5  2003/02/28 09:36:51  jano
 * RuntimeException(e) -> IntrnetRuntimeException
 *
 * Revision 1.4  2003/02/27 12:33:46  peter
 * fixed reset - points and minutes are vectors
 *
 * Revision 1.3  2003/02/26 17:12:37  peter
 * fixed the countryCode validation, when set to empty string, must be null
 *
 * Revision 1.2  2003/02/25 17:27:37  peter
 * fixed validation
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.5  2003/02/20 20:25:37  colin
 * improved validation by adding ValidationField and ValidationException
 *
 * Revision 1.4  2003/02/20 16:20:45  peter
 * date/time formats changed to input ones
 *
 * Revision 1.3  2003/02/20 13:00:26  peter
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
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar.struts;

import java.text.ParseException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.calendar.Calendar;
import com.ivata.groupware.business.calendar.event.EventDO;
import com.ivata.groupware.business.calendar.event.meeting.MeetingDO;
import com.ivata.groupware.business.calendar.event.publicholiday.PublicHolidayDO;
import com.ivata.groupware.util.SettingDateFormatter;
import com.ivata.mask.Mask;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationError;
import com.ivata.mask.validation.ValidationErrors;
import com.ivata.mask.web.format.DateFormatterConstants;
import com.ivata.mask.web.format.DateFormatterException;
import com.ivata.mask.web.struts.DialogForm;


/**
 * <p>This form is wrapper for <code>EventDO</code>. It is used in
 * <code>event.jsp</code>.</p>
 *
 * @since 2003-01-31
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.5 $
 *
 */
public class EventForm extends DialogForm {
    /**
     * <p>If not <code>null</code> and not empty, specifies a new, empty
     * category should be added.</p>
     */
    private String addCategory = "";
    /**
     * <p>If this event is a meeting, this is a semicolon (;) deliminated
     * string of all attendee identifers.</p>
     */
    private String attendees = "";
    private Calendar calendar;
    private SettingDateFormatter dateFormatter;
    /**
     * <p>Instance of event data object, containing all values
     * of event being submitted.</p>
     */
    private EventDO event;
    /**
     * <p>Stores the number of the active tab page.</p>
     */
    private Integer eventTab_activeTab = new Integer(0);
    /**
     * <p>Specifies just the date part of the end date/time.</p>
     */
    private String finishDate = "";
    /**
     * <p>Specifies just the time part of the end date/time.</p>
     */
    private String finishTime = "";
    /**
     * <p>Specifies just the date part of the start date/time.</p>
     */
    private String startDate = "";
    /**
     * <p>Specifies just the time part of the start date/time.</p>
     */
    private String startTime = "";
    /**
     * <p>Page to currently display in the tab control.</p>
     */
    private String tabPage = "/calendar/eventDetails.jsp";
    /**
     * <p>Key used to access localized title string.</p>
     */
    private String titleKey = "event.title.new";
    /**
     * <p>
     * Defines the base class of all objects in the value object list.
     * </p>
     */
    private Class baseClass;
    /**
     * <p>
     * Mask containing all the field definitions for this list.
     * </p>
     */
    private Mask mask;
    /**
     * @param maskParam Refer to {@link DialogForm#DialogForm}.
     * @param baseClassParam Refer to {@link DialogForm#DialogForm}.
     * @param settings ivata settings implementation - used in validation.
     */
    public EventForm(final Calendar calendar,
            final SettingDateFormatter dateFormatter,
            MaskFactory maskFactory) {
        this.calendar = calendar;
        this.dateFormatter = dateFormatter;
        mask = maskFactory.getMask(EventDO.class);
    }

    /**
     * <p>
     * Return all form state to initial values.
     * </p>
     *
     * @see com.ivata.mask.web.struts.MaskForm#clear()
     */
    protected void clear() throws OperationNotSupportedException {
        super.clear();
        addCategory = "";
        attendees = "";
        event = new EventDO();
        eventTab_activeTab = new Integer(0);
        finishDate = "";
        finishTime = "";
        startDate = "";
        startTime = "";
        tabPage = "/calendar/eventDetails.jsp";
        titleKey = "event.title.new";
    }

    /**
     * <p>If not <code>null</code> and not empty, specifies a new, empty
     * category should be added.</p>
     *
     * @return the current value of addCategory.
     */
    public final String getAddCategory() {
        return addCategory;
    }

    /**
     * <p>If this event is a meeting, this is a semicolon (;) deliminated
     * string of all attendee identifers.</p>
     *
     * @return the current value of attendees.
     */
    public final String getAttendees() {
        return attendees;
    }

    /**
     * <p>Get the values of the event this form refers to as a dependent
     * value object.</p>
     *
     * @return event values this form refers to.
     *
     *
     */
    public final EventDO getEvent() {
        return event;
    }

    /**
     * <p>Stores the number of the active tab page.</p>
     *
     * @return the current value of eventTab_activeTab.
     */
    public final Integer getEventTab_activeTab() {
        return eventTab_activeTab;
    }

    /**
     * <p>Specifies just the date part of the end date/time.</p>
     *
     * @return the current value of finishDate.
     */
    public final String getFinishDate() {
        return finishDate;
    }

    /**
     * <p>Specifies just the time part of the end date/time.</p>
     *
     * @return the current value of finishTime.
     */
    public final String getFinishTime() {
        return finishTime;
    }

    /**
     * <p>Specifies just the date part of the start date/time.</p>
     *
     * @return the current value of startDate.
     */
    public final String getStartDate() {
        return startDate;
    }

    /**
     * <p>Specifies just the time part of the start date/time.</p>
     *
     * @return the current value of startTime.
     */
    public final String getStartTime() {
        return startTime;
    }

    /**
     * <p>Page to currently display in the tab control.</p>
     *
     * @return the current value of tabPage.
     */
    public final String getTabPage() {
        return tabPage;
    }

    /**
     * <p>Key used to access localized title string.</p>
     *
     * @return the current value of titleKey.
     */
    public final String getTitleKey() {
        return titleKey;
    }

    /**
     * <p>Find out if this is a meeting.</p>
     *
     * @return <code>true</code> if it is a meeting, otherwise
     * <code>false</code>.
     */
    public boolean isMeeting() {
        return event instanceof MeetingDO;
    }

    /**
     * <p>Find out if this is an event rather than a public holiday.</p>
     *
     * @return <code>true</code> if it is an event, otherwise
     * <code>false</code> if it is a public holiday or meeting.
     */
    public boolean isPlainEvent() {
        return !((event instanceof MeetingDO) || (event instanceof PublicHolidayDO));
    }

    /**
     * <p>Find out if this is a public holiday.</p>
     *
     * @return <code>true</code> if it is a public holiday, otherwise
     * <code>false</code>.
     */
    public boolean isPublicHoliday() {
        return event instanceof PublicHolidayDO;
    }


    /**
     * <p>Reset all bean properties to their default state.  This method
     * is called before the properties are repopulated by the controller
     * servlet.</p>
     *
     * @param calendar valid calendar remote instance.
     * @param request The servlet request we are processing.
     * @param session The servlet session we are processing.
     *
     *
     */
    public void reset(final ActionMapping mapping,
            final HttpServletRequest request) {
        // if there is no event yet, nothing to reset
        if (event == null) {
            return;
        }

        MeetingDO meeting = null;
        if (event instanceof MeetingDO) {
            meeting = (MeetingDO) event;
        }

        // what we reset depends on the tab page
        // meeting attendees affects the chairperson and the list of attendees
        if (tabPage.equals("/calendar/meetingPeople.jsp")) {
            attendees = "";
            meeting.setChairPerson(null);

        // meeting agenda affects the list of agenda points
        } else if (tabPage.equals("/calendar/meetingAgenda.jsp")) {
            // TODO: clear agenda points and categories here

        // standard event
        } else {
            event.setSubject("");
            startDate = "";
            startTime = "";
            finishDate = "";
            finishTime = "";
            event.setAllDayEvent(false);
            event.setDescription("");
            if (meeting != null) {
                meeting.setLocation("");
            }
        }

    }

    /**
     * <p>If not <code>null</code> and not empty, specifies a new, empty
     * category should be added.</p>
     *
     * @param addCategory the new value of addCategory.
     */
    public final void setAddCategory(final String addCategory) {
        this.addCategory = addCategory;
    }

    /**
     * <p>If this event is a meeting, this is a semicolon (;) deliminated
     * string of all attendee identifers.</p>
     *
     * @param attendees the new value of attendees.
     */
    public final void setAttendees(final String attendees) {
        this.attendees = attendees;
    }

    /**
     * <p>Private helper method to parse the date and time from strings,
     * and set the results in a <code>GregorianCalendar</code>.</p>
     *
     * @param dateFormatter used to parse the strings
     * @param dateParam string representation of the calendar date (without
     * time).
     * @param timeParam string representation of the time of day.
     * @param calendar target for results (if there are no errors).
     * @return <code>ValidationError</code> instance if there is a
     * problem, otherwise <code>null</code>.
     */
    private ValidationError setDateTime(
            final SettingDateFormatter dateFormatter,
            final String dateParam,
            final String timeParam,
            final GregorianCalendar calendar) {
        ValidationError error = null;

        try {
            if (!StringHandling.isNullOrEmpty(dateParam)) {
                String date = StringHandling.getNotNull(dateParam, "");
                String time = StringHandling.getNotNull(timeParam, "");
                try {
                    calendar.setTime(dateFormatter.parse(date
                            + " "
                            + time));
                } catch (ParseException e) {
                    // find out which went wrong - date or time
                    try {
                        dateFormatter.setDateTimeText("{0}");
                        dateFormatter.parse(dateParam);
                        String[] parameters = {time};

                        error = new ValidationError(
                                "event",
                                Calendar.BUNDLE_PATH,
                                mask.getField("time"),
                                "errors.time",
                                Arrays.asList(parameters));
                    } catch (ParseException e2) {
                        String[] parameters = {date};

                        error = new ValidationError(
                                "event",
                                Calendar.BUNDLE_PATH,
                                mask.getField("date"),
                                "errors.date",
                                Arrays.asList(parameters));
                    }
                }
            }
        } catch (DateFormatterException e) {
            throw new RuntimeException(e);
        }
        return error;
    }

    /**
     * <p>Set the values of the event this form refers to as a dependent
     * value object.</p>
     *
     * @param event new event values this form refers to.
     *
     *
     */
    public final void setEvent(final EventDO event) {
        this.event = event;
    }

    /**
     * <p>Stores the number of the active tab page.</p>
     *
     * @param eventTab_activeTab the new value of eventTab_activeTab.
     */
    public final void setEventTab_activeTab(final Integer eventTab_activeTab) {
        this.eventTab_activeTab = eventTab_activeTab;
    }

    /**
     * <p>Specifies just the date part of the end date/time.</p>
     *
     * @param finishDate the new value of finishDate.
     */
    public final void setFinishDate(final String finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * <p>Specifies just the time part of the end date/time.</p>
     *
     * @param finishTime the new value of finishTime.
     */
    public final void setFinishTime(final String finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * <p>Specifies just the date part of the start date/time.</p>
     *
     * @param startDate the new value of startDate.
     */
    public final void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    /**
     * <p>Specifies just the time part of the start date/time.</p>
     *
     * @param startTime the new value of startTime.
     */
    public final void setStartTime(final String startTime) {
        this.startTime = startTime;
    }

    /**
     * <p>Page to currently display in the tab control.</p>
     *
     * @param tabPage the new value of tabPage.
     */
    public final void setTabPage(final String tabPage) {
        this.tabPage = tabPage;
    }

    /**
     * <p>Key used to access localized title string.</p>
     *
     * @param titleKey the new value of titleKey.
     */
    public final void setTitleKey(final String titleKey) {
        this.titleKey = titleKey;
    }

    /**
     * <p>Validates the properties that have been set for this HTTP
     * request, and return an <code>ActionMessages</code> object that
     * encapsulates any validation errors that have been found.  If no
     * errors are found,</p>
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     * @return <code>null</code> or an <code>ActionMessages</code> object
     * with no recorded error messages.
     *
     *
     */
    public ValidationErrors validate(final HttpServletRequest request,
            final HttpSession session) {
        ValidationError startDateError = null;
        ValidationError finishDateError = null;
        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");
        Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);

        try {
            dateFormatter.setDateFormat(DateFormatterConstants.DATE_INPUT);
            dateFormatter.setTimeFormat(DateFormatterConstants.TIME_INPUT);
        } catch (DateFormatterException e) {
            throw new RuntimeException(e);
        }

        GregorianCalendar start = new GregorianCalendar();

        if (StringHandling.isNullOrEmpty(startDate)) {
           event.setStart(null);
        } else {
            if (StringHandling.isNullOrEmpty(startTime)) {
                try {
                    dateFormatter.setDateTimeText("{0}");
                } catch (DateFormatterException e1) {
                    throw new RuntimeException(e1);
                }
            } else {
                try {
                    dateFormatter.setDateTimeText("{0} {1}");
                } catch (DateFormatterException e1) {
                    throw new RuntimeException(e1);
                }
            }
            startDateError = setDateTime(dateFormatter, startDate, startTime, start);
            if (startDateError == null) {
                event.setStart(start);
                // when start time was not set, mark it as an all day event
                if (StringHandling.isNullOrEmpty(startTime)) {
                    event.setAllDayEvent(true);
                }
            }
        }

        GregorianCalendar finish = new GregorianCalendar();
        if (StringHandling.isNullOrEmpty(finishDate)) {
           event.setFinish(null);
        } else {
            if (StringHandling.isNullOrEmpty(finishTime)) {
                try {
                    dateFormatter.setDateTimeText("{0}");
                } catch (DateFormatterException e1) {
                    throw new RuntimeException(e1);
                }
            } else {
                try {
                    dateFormatter.setDateTimeText("{0} {1}");
                } catch (DateFormatterException e1) {
                    throw new RuntimeException(e1);
                }
            }
            finishDateError = setDateTime(dateFormatter, finishDate, finishTime, finish);
            // if the finish is before start, that has too mean the finish date
            // was the same as start date, but the time was unspecified
            if (finishDateError == null && finish.after(start)) {
                if (StringHandling.isNullOrEmpty(finishTime)) {
                  finish.add(GregorianCalendar.MINUTE, 24*60 - 1);
                }
                event.setFinish(finish);
            }
        }

        // now perform server-side validation
        ValidationErrors validationErrors = null;
        try {
            validationErrors = calendar.validate(securitySession, event);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }

        // if validation errors is null and we have a start date or an end date
        // error, create a new colllection object so we can add that to the errors
        if (((startDateError != null)
                    || (finishDateError != null))
                && (validationErrors == null)) {
            validationErrors = new ValidationErrors();
        }
        if (startDateError != null) {
            validationErrors.add(startDateError);
        }
        if (finishDateError != null) {
            validationErrors.add(finishDateError);
        }
        return validationErrors;
    }

    /**
     * <p>
     * Defines the base class of all objects in the value object list.
     * </p>
     *
     * @return base class of all objects in the value object list.
     */
    public final Class getBaseClass() {
        return baseClass;
    }

    /**
     * <p>
     * Mask containing all the field definitions for this list.
     * </p>
     *
     * @return mask containing all the field definitions for this list.
     */
    public final Mask getMask() {
        return mask;
    }
}

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
 * $Log: EventAction.java,v $
 * Revision 1.6  2005/04/30 13:04:13  colinmacleod
 * Fixes reverting id type from String to Integer.
 *
 * Revision 1.5  2005/04/29 02:48:15  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.4  2005/04/10 20:09:42  colinmacleod
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
 * Revision 1.1.1.1  2005/03/10 17:47:45  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.10  2004/12/31 18:27:43  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.9  2004/12/23 21:01:26  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.8  2004/11/12 18:19:14  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.7  2004/11/12 16:08:10  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.6  2004/11/03 15:31:50  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.5  2004/07/18 21:59:14  colinmacleod
 * Removed Person from User - now you need to use addressbook/persistence manager to find the person (makes the app run faster.)
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
 * Revision 1.1.1.1  2004/01/27 20:58:21  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/10/28 13:10:23  jano
 * commiting calendar,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 13:57:23  colin
 * fixing for XDoclet
 *
 * Revision 1.11  2003/04/14 07:10:15  peter
 * fixed helpKey logic
 *
 * Revision 1.10  2003/04/09 08:57:28  jano
 * handling data of removing user
 *
 * Revision 1.9  2003/03/21 09:35:05  peter
 * null meeting when not meeting type event
 *
 * Revision 1.8  2003/03/12 10:43:01  peter
 * chairPerson defaults to current user
 *
 * Revision 1.7  2003/03/04 00:25:41  colin
 * added type after clear to save for new form after clear button
 *
 * Revision 1.6  2003/03/03 16:40:43  colin
 * now sets user name and modified by as appropriate
 *
 * Revision 1.5  2003/02/27 12:31:51  peter
 * fixed Conversion of Ids String to Vector of Integers
 *
 * Revision 1.4  2003/02/26 17:12:37  peter
 * fixed the countryCode validation, when set to empty string, must be null
 *
 * Revision 1.3  2003/02/26 13:43:07  peter
 * fixed countryCode and multiplying new events in onConfirm
 *
 * Revision 1.2  2003/02/25 17:26:09  peter
 * fixed return value of onConfirm
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.2  2003/02/13 13:53:06  colin
 * conversion to Struts/pop-ups
 *
 * Revision 1.2  2003/02/13 08:45:42  colin
 * conversion to Struts/popups
 *
 * Revision 1.1  2003/02/04 17:50:17  colin
 * first version in CVS
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar.struts;

import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.business.addressbook.AddressBook;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.calendar.Calendar;
import com.ivata.groupware.business.calendar.event.EventDO;
import com.ivata.groupware.business.calendar.event.meeting.MeetingDO;
import com.ivata.groupware.business.calendar.event.meeting.category.MeetingCategoryDO;
import com.ivata.groupware.business.calendar.event.publicholiday.PublicHolidayDO;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.CollectionHandling;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>Invoked when the user edits, displays or enters a new event or
 * public holiday.</p>
 *
 * @since 2003-02-02
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.6 $
 */
public class EventAction extends MaskAction {
    /**
     * Logger for this class.
     */
    private static final Logger logger = Logger.getLogger(EventAction.class);

    /**
     * <p>Private helper method to remove empty categories from the
     * meeting. Empty in this case means the name/heading is empty and
     * there
     * are neither minute texts nor agenda points.</p>
     *
     * <p>If there <em>are</em> agenda points or minute texts but the
     * name/heading is empty, it is replaced with a default localized text
     * from the application resources.</p>
     *
     * <p>Note this method is static as it is also used from the library.</p>
     *
     * @param meeting the meeting to remove categories from.
     * @param messages used to calculate default localized category
     * name.
     * @param locale used to calculate default localized category
     * name.
     */
    public static void removeEmptyMeetingCategories(MeetingDO meeting, MessageResources messages, Locale locale) {
        int count = 1;
        Set categories = meeting.getCategories();
        Iterator categoryIterator = categories.iterator();
        List toRemove = new Vector();
        while (categoryIterator.hasNext()) {
            MeetingCategoryDO category = (MeetingCategoryDO) categoryIterator.next();

            // note: the default name does not count as empty
            String defaultName = messages.getMessage(locale, "default.category", new Integer(count++));

            if (StringHandling.isNullOrEmpty(category.getName())) {
                // see if there are points or minutes
                Set agendaPoints = category.getAgendaPoints();
                if ((agendaPoints == null)
                        || (agendaPoints.size() == 0)) {
                    toRemove.add(category);
                } else {
                    // default the text to heading + number
                    category.setName(defaultName);
                }
            }
        }
        // now go thro' and remove all the ones which are empty
        categoryIterator = toRemove.iterator();
        while(categoryIterator.hasNext()) {
            categories.remove(categoryIterator.next());
        }
    }
    private AddressBook addressBook;
    private Calendar calendar;
    /**
     * TODO
     *
     * @param calendar
     * @param addressBook
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public EventAction(Calendar calendar, AddressBook addressBook,
            MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.calendar = calendar;
        this.addressBook = addressBook;
    }

    /**
     * <p>Called when the clear button is pressed, or after an ok or
     * delete button, where the session should be restored to its default
     * state.</p>
     *
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     *
     */
    public void clear(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        // and remove the current tab from the session
        session.removeAttribute("eventTab_activeTab");
        EventForm eventForm = (EventForm)form;
        try {
            eventForm.clear();
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>Overridden to see which type of event to create when creating a
     * new event.</p>
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
        PicoContainer container = securitySession.getContainer();
        MessageResources messages = getResources(request, "calendar");
        Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);

        // look for a request parameter identifying the type - that means new
        String type = request.getParameter("type");
        EventForm eventForm = (EventForm) form;
        if (type != null) {
            if ("meeting".equals(type)) {
                MeetingDO meeting = new MeetingDO();
                eventForm.setEvent(meeting);
                eventForm.setTitleKey("event.title.new.meeting");
                eventForm.setHelpKey("calendar.meeting.event");

                // initially make one agenda category
                Set meetingCategories = meeting.getCategories();
                if (meetingCategories == null) {
                    meetingCategories = new HashSet();
                    meeting.setCategories(meetingCategories);
                }
                if (meetingCategories.size() == 0) {
                    MeetingCategoryDO newCategory = new MeetingCategoryDO();
                    newCategory.setMeeting(meeting);
                    newCategory.setName(messages.getMessage(locale, "default.category", "1"));
                    meetingCategories.add(newCategory);
                }
                PersonDO person;
                person = addressBook.findPersonByUserName(securitySession,
                        user.getName());
                meeting.setChairPerson(person);

            } else if ("publicHoliday".equals(type)) {
                eventForm.setEvent(new PublicHolidayDO());
                eventForm.setTitleKey("event.title.new.publicHoliday");
                eventForm.setHelpKey("calendar.publicHoliday");
            } else {
                eventForm.setEvent(new EventDO());
                eventForm.setTitleKey("event.title.new");
                eventForm.setHelpKey("calendar.event");
            }
            return null;
        }

        // --tabs-- currently only meetings have tabs
        // the request overrides the current setting in the form
        Integer tab = eventForm.getEventTab_activeTab();
        Integer requestTab = StringHandling.integerValue(request.getParameter("eventTab_activeTab"));
        if (requestTab != null) {
            eventForm.setEventTab_activeTab(tab = requestTab);
        }
        EventDO event = eventForm.getEvent();

        // now choose the right page to show based on the tab
        if (tab.equals(new Integer(1))) {
            eventForm.setTabPage("/calendar/meetingPeople.jsp");
            eventForm.setHelpKey("calendar.meeting.people");
        } else if (tab.equals(new Integer(2))) {
            eventForm.setTabPage("/calendar/meetingAgenda.jsp");
            eventForm.setHelpKey("calendar.meeting.agenda");
        } else {
            eventForm.setTabPage("/calendar/eventDetails.jsp");
            if (event instanceof PublicHolidayDO) {
                eventForm.setHelpKey("calendar.publicHoliday");
            } else if (event instanceof MeetingDO) {
                eventForm.setHelpKey("calendar.meeting.event");
            } else {
                eventForm.setHelpKey("calendar.event");
            }
        }

        // meeting handling
        if (event instanceof MeetingDO) {
            MeetingDO meeting = (MeetingDO) event;
            // go thro' all the categories and discard empty ones
            removeEmptyMeetingCategories(meeting, messages, locale);
            // check there is a collection for categories
            Set categories = meeting.getCategories();
            if (categories == null) {
                meeting.setCategories(categories = new HashSet());
            }

            // if the add button was pressed, add an empty category
            // if the new arrays are empty, add a solitary one
            int size = categories.size();
            if (!StringHandling.isNullOrEmpty(eventForm.getAddCategory())
                    || (size == 0)) {
                MeetingCategoryDO category = new MeetingCategoryDO();
                category.setName(messages.getMessage(locale, "default.category",
                    new Integer(size + 1)));
                category.setMeeting(meeting);
                categories.add(category);
            }
        }

        return null;
    }

    /**
     * <p>This method is called if the ok or apply buttons are pressed.</p>
     *
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     * @param ok <code>true</code> if the ok button was pressed, otherwise
     * <code>false</code> if the apply button was pressed.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     *
     */
    public String onConfirm(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session,
            final String defaultForward) throws SystemException {
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        UserDO user = securitySession.getUser();
        EventForm eventForm = (EventForm) form;
        EventDO event = eventForm.getEvent();

        // meeting ?
        if (event instanceof MeetingDO) {
            MeetingDO meeting = (MeetingDO) event;

            // set the attendee ids
            List attendeeIdStrings = new Vector(CollectionHandling.convertFromLines(eventForm.getAttendees(), ";"));
            // convert the id strings to Interers
            Set attendees = new HashSet();
            for (Iterator i = attendeeIdStrings.iterator(); i.hasNext(); ) {
                String attendeeId = (String) i.next();
                PersonDO attendee = addressBook.findPersonByPrimaryKey(
                        securitySession, attendeeId);
                attendees.add(attendee);
            }
            meeting.setAttendees(attendees);

            // go thro' all the categories and discard empty ones
            removeEmptyMeetingCategories(meeting,
                getResources(request, "calendar"), getLocale(request));

            // if there is no chair person default it to this user's person
            PersonDO chairPerson = meeting.getChairPerson();
            if (chairPerson == null) {
                chairPerson = addressBook.findPersonByUserName(securitySession,
                        user.getName());
            }
        }
        // if it doesn't have an id, it is a new event
        if (event.getId() == null) {
            event = calendar.addEvent(securitySession, event);
        } else {
            // amend an existing event
            event = calendar.amendEvent(securitySession, event);
        }
        eventForm.setEvent(event);

        request.setAttribute("openerPage", "/calendar/index.action");

        return defaultForward;
    }

    /**
     * <p>This method is called if the delete (confirm, not warn) button
     * is pressed.</p>
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param log valid logging object to write messages to.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     *
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     *
     */
    public String onDelete(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session, final String defaultForward) throws SystemException {
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        EventForm eventForm = (EventForm) form;
        EventDO event = eventForm.getEvent();
        calendar.removeEvent(securitySession, event);
        request.setAttribute("openerPage", "/calendar/index.action");
        return defaultForward;
    }
}

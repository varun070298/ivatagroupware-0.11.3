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
 * $Log: FindEventAction.java,v $
 * Revision 1.3  2005/04/10 20:09:42  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:18  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:47:45  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.8  2004/12/31 18:27:43  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
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
 * Revision 1.1.1.1  2004/01/27 20:58:21  colinmacleod
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
 * Revision 1.5  2003/04/14 07:10:15  peter
 * fixed helpKey logic
 *
 * Revision 1.4  2003/02/28 09:36:51  jano
 * RuntimeException(e) -> IntrnetRuntimeException
 *
 * Revision 1.3  2003/02/27 12:30:42  peter
 * fixed converting of attendeeIds to String in execute
 *
 * Revision 1.2  2003/02/25 17:26:46  peter
 * fixed SettingDateFormatter initialisation settings
 *
 * Revision 1.1  2003/02/24 19:09:22  colin
 * moved to business
 *
 * Revision 1.3  2003/02/20 13:00:58  peter
 * 24H time formats changed to simple ones
 *
 * Revision 1.2  2003/02/13 08:45:42  colin
 * conversion to Struts/popups
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

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.calendar.Calendar;
import com.ivata.groupware.business.calendar.event.EventDO;
import com.ivata.groupware.business.calendar.event.meeting.MeetingDO;
import com.ivata.groupware.business.calendar.event.publicholiday.PublicHolidayDO;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.groupware.util.SettingDateFormatter;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.CollectionHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.format.DateFormatterConstants;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>This action is  invoked from the main calendar viewpage. This
 * action locates the event and prepares the form for
 * <code>event.jsp</code>.</p>
 *
 * @since 2003-02-02
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class FindEventAction extends MaskAction {
    private Calendar calendar;
    private SettingDateFormatter dateFormatter;
    /**
     * TODO
     *
     * @param calendar
     * @param dateFormatter
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public FindEventAction(Calendar calendar, SettingDateFormatter dateFormatter,
            MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.calendar = calendar;
        this.dateFormatter = dateFormatter;
    }

    /**
     * <p>Invoked when the user clicks on an event in the calendar.</p>
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
        // create a new event form then call reset to make sure the calendar is there
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        EventForm eventForm = (EventForm)
            PicoContainerFactory.getInstance().instantiateOrOverride(
                    securitySession.getContainer(),
                    EventForm.class);

        eventForm.reset(mapping, request);
        String id = request.getParameter("id");

        if (id == null) {
            throw new SystemException("ERROR in FindEventAction: id is null");
        }
        EventDO event = calendar.findEventByPrimaryKey(securitySession, id);
        // ok, this is not very efficient. it kinda grew this way out of
        // hysteric reasons :-)
        // the only alternative I can see is to add a 'type' request parameter
        // and I don't want to do this as it will make all the views more
        // complicated
        if (event instanceof MeetingDO) {
            MeetingDO meeting = (MeetingDO) event;
            Set attendees = meeting.getAttendees();
            if (attendees == null) {
                meeting.setAttendees(attendees = new HashSet());
            }
            List attendeeIds =  new Vector(attendees.size());
            // make strings of Integers
            for (Iterator i = attendees.iterator(); i.hasNext(); ) {
                PersonDO attendee = (PersonDO) i.next();
                attendeeIds.add(attendee.getId().toString());
            }
            eventForm.setAttendees(CollectionHandling.convertToLines(attendeeIds, ';'));
        }
        dateFormatter.setDateTimeText("{0}");
        // TODO: replace this with settings
        dateFormatter.setDateFormat(DateFormatterConstants.DATE_INPUT_DISPLAY);
        dateFormatter.setTimeFormat(DateFormatterConstants.TIME_INPUT_DISPLAY);
        eventForm.setStartDate(dateFormatter.format(event.getStart().getTime()));
        if (event.getFinish() == null) {
            eventForm.setFinishDate("");
        } else {
            eventForm.setFinishDate(dateFormatter.format(event.getFinish().getTime()));
        }
        dateFormatter.setDateTimeText("{1}");
        eventForm.setStartTime(dateFormatter.format(event.getStart().getTime()));
        if (event.getFinish() == null) {
            eventForm.setFinishTime("");
        } else {
            eventForm.setFinishTime(dateFormatter.format(event.getFinish().getTime()));
        }

        // go thro' all the types and set the form up for the right one
        // note if this request parameter is set, it indicates a new event
        // existing events are retrieved in FindEventAction.java
        if (event instanceof MeetingDO) {
            eventForm.setTitleKey("event.title.amend.meeting");
            eventForm.setHelpKey("calendar.meeting.event");
        } else if (event instanceof PublicHolidayDO) {
            eventForm.setTitleKey("event.title.amend.publicHoliday");
            eventForm.setHelpKey("calendar.publicHoliday");
        } else {
            // default is standard event
            eventForm.setHelpKey("calendar.event");
            eventForm.setTitleKey("event.title.amend");
        }
        eventForm.setEvent(event);
        session.setAttribute("calendarEventForm", eventForm);
        return "calendarEvent";
    }
}

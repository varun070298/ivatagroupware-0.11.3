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
 * $Log: IndexForm.java,v $
 * Revision 1.3  2005/04/10 20:43:44  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:18  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:47:44  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.6  2004/11/12 18:19:14  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.5  2004/11/03 15:49:51  colinmacleod
 * Changed todo comments to TODO: all caps.
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
 * Revision 1.2  2003/10/15 13:57:23  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/04/14 12:31:04  peter
 * removed helpKey field - it is inherited
 *
 * Revision 1.1  2003/02/24 19:09:22  colin
 * moved to business
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

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.setting.SettingsDataTypeException;
import com.ivata.groupware.admin.setting.SettingsInitializationException;
import com.ivata.groupware.util.SettingDateFormatter;
import com.ivata.mask.Mask;
import com.ivata.mask.validation.ValidationErrors;
import com.ivata.mask.web.format.DateFormatterConstants;
import com.ivata.mask.web.format.DateFormatterException;
import com.ivata.mask.web.struts.DialogForm;


/**
 * <p>This form contains parameters for the main calendar list.</p>
 *
 * @since 2003-02-01
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 */
public class IndexForm extends DialogForm {
    /**
     * <p>For week and day view, stores a <code>Vector</code> of all day
     * events. This is not used in month and year views.</p>
     */
    private Vector[] allDayEvents;
    /**
     * <p>Specifies the current day being viewed.</p>
     */
    private GregorianCalendar currentDay = new GregorianCalendar();
    /**
     * <p>
     * Used to format and display only the date part of an event.
     * </p>
     */
    private SettingDateFormatter dateFormatter;
    /**
     * <p>
     * Used to format and display date and time parts of an event.
     * </p>
     */
    private SettingDateFormatter dateTimeFormatter;
    /**
     * <p>Last hour of the day to show in the day view.</p>
     */
    private int dayFinishHour = 17;
    /**
     * <p>First hour of the day to show in the day view.</p>
     */
    private int dayStartHour = 8;
    /**
     * <p>For week and day views, contains events for day view which are
     * not all day events, indexed by hour.</p>
     *
     * <p>For month views, contains all events for each day, indexed by
     * day. There will be as many elements of the array as there are weeks
     * in the month.</p>
     *
     * <p>Each element of each <code>Map</code> is a sorted
     * <code>TreeSet</code> which uses <code>EventHourComparator</code> to
     * sort.</p>
     */
    private Map[] events = null;
    /**
     * <p>This attribute is used in week views to determine the date of
     * the first day to be shown.</p>
     */
    private GregorianCalendar firstWeekDay = null;
    /**
     * <p>
     * Used to format and display only the time part of an event.
     * </p>
     */
    private SettingDateFormatter timeFormatter;

    /**
     * <p>Represents the current view type, set to one of the constants in
     * {@link IndexFormConstants IndexFormConstants}. If unset, defaults
     * to
     * the settings <code>calendarDefaultView</code>.</p>
     */
    private Integer view = null;
    /**
     * <p>Path of the page to include which will show the current view.</p>
     */
    private String viewPage = null;
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
     * Construct an index form with the date formatters provided.
     *
     * @param dateFormatter used to format and display only the date part of an
     *      event.
     * @param timeFormatter used to format and display only the time part of an
     *      event.
     * @param dateTimeFormatter used to format and display date and time parts
     *      of an event.
     * @param maskParam Refer to {@link DialogForm#DialogForm}.
     * @param baseClassParam Refer to {@link DialogForm#DialogForm}.
     */
    public IndexForm(final SettingDateFormatter dateFormatter,
            final SettingDateFormatter timeFormatter,
            final SettingDateFormatter dateTimeFormatter)
            throws SettingsDataTypeException, SettingsInitializationException {
        this.dateFormatter = dateFormatter;
        try {
            dateFormatter.setDateTimeText("{0}");
            dateFormatter.setDateFormat(DateFormatterConstants.DATE_LONG_DAY);
            this.timeFormatter = timeFormatter;
            timeFormatter.setDateTimeText("{1}");
            timeFormatter.setTimeFormat(DateFormatterConstants.TIME_SHORT);
            this.dateTimeFormatter = dateTimeFormatter;
            dateFormatter.setDateFormat(DateFormatterConstants.DATE_SHORT);
            timeFormatter.setTimeFormat(DateFormatterConstants.TIME_SHORT);
        } catch (DateFormatterException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>
     * Return all form state to initial values.
     * </p>
     *
     * @see com.ivata.mask.web.struts.MaskForm#clear()
     */
    protected void clear() {
        allDayEvents = null;
        currentDay = new GregorianCalendar();
        dayFinishHour = 17;
        dayStartHour = 8;
        events = null;
        firstWeekDay = null;
        view = null;
        viewPage = null;
    }

    /**
     * <p>Get the all day events to be shown for each day. In the day
     * view, the array only contains one element - for the week views, it
     * contains as many elements as there are days</p>
     *
     * @return all day events to be shown in the day view as a
     * <code>Vector</code> of <code>EventDO</code> instances.
     */
    public final Vector[] getAllDayEvents() {
        return allDayEvents;
    }

    /**
     * <p>Specifies the current day being viewed.</p>
     *
     * @return the current value of currentDay.
     */
    public final GregorianCalendar getCurrentDay() {
        return currentDay;
    }
    /**
     * <p>
     * Used to format and display only the date part of an event.
     * </p>
     *
     * @return format and display only the date part of an event.
     */
    public final SettingDateFormatter getDateFormatter() {
        return dateFormatter;
    }

    /**
     * <p>
     * Used to format and display date and time parts of an event.
     * </p>
     *
     * @return format and display date and time parts of an event.
     */
    public final SettingDateFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    /**
     * <p>Last hour of the day to show in the day view.</p>
     *
     * @return the current value of dayFinishHour.
     */
    public final int getDayFinishHour() {
        return dayFinishHour;
    }

    /**
     * <p>First hour of the day to show in the day view.</p>
     *
     * @return the current value of dayStartHour.
     */
    public final int getDayStartHour() {
        return dayStartHour;
    }

    /**
     * <p>For week and day views, contains events for day view which are
     * not all day events, indexed by hour.</p>
     *
     * <p>For month views, contains all events for each day, indexed by
     * day. There will be as many elements of the array as there are weeks
     * in the month.</p>
     *
     * <p>Each element of each <code>Map</code> is a sorted
     * <code>TreeSet</code> which uses <code>EventHourComparator</code> to
     * sort.</p>
     *
     * @return current value of events.
     */
    public final Map[] getEvents() {
        return events;
    }

    /**
     * <p>This attribute is used in week views to determine the date of
     * the first day to be shown.</p>
     *
     * @return the current value of firstWeekDay.
     */
    public final GregorianCalendar getFirstWeekDay() {
        return firstWeekDay;
    }

    /**
     * <p>
     * Used to format and display only the time part of an event.
     * </p>
     *
     * @return format and display only the time part of an event.
     */
    public final SettingDateFormatter getTimeFormatter() {
        return timeFormatter;
    }

    /**
     * <p>Represents the current view type, set to one of the constants in
     * {@link IndexFormConstants IndexFormConstants}. If unset, defaults
     * to
     * the settings <code>calendarDefaultView</code>.</p>
     *
     * @return the current value of view.
     */
    public final Integer getView() {
        return view;
    }

    /**
     * <p>Path of the page to include which will show the current
     * view.</p>
     *
     * @return the current value of viewPage.
     */
    public final String getViewPage() {
        return viewPage;
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
     */
    public void reset(final ActionMapping mapping,
            final HttpServletRequest request) {
        // TODO: make this a setting
        dayStartHour = 8;
        dayFinishHour = 17;
        allDayEvents = null;
        events = null;
    }

    /**
     * <p>Set the all day events to be shown for each day. In the day
     * view, the array only contains one element - for the week views, it
     * contains as many elements as there are days</p>
     *
     * @param allDayEvents all day events to be shown for each day as a
     * <code>Vector</code> of <code>EventDO</code> instances.
     */
    public final void setAllDayEvents(final Vector[] allDayEvents) {
        this.allDayEvents = allDayEvents;
    }

    /**
     * <p>Specifies the current day being viewed.</p>
     *
     * @param currentDay the new value of currentDay.
     */
    public final void setCurrentDay(final GregorianCalendar currentDay) {
        this.currentDay = currentDay;
    }

    /**
     * <p>
     * Used to format and display only the date part of an event.
     * </p>
     *
     * @param formatter format and display only the date part of an event.
     */
    public final void setDateFormatter(final SettingDateFormatter formatter) {
        dateFormatter = formatter;
    }

    /**
     * <p>
     * Used to format and display date and time parts of an event.
     * </p>
     *
     * @param formatter format and display date and time parts of an event.
     */
    public final void setDateTimeFormatter(final SettingDateFormatter formatter) {
        dateTimeFormatter = formatter;
    }

    /**
     * <p>Last hour of the day to show in the day view.</p>
     *
     * @param dayFinishHour the new value of dayFinishHour.
     */
    public final void setDayFinishHour(final int dayFinishHour) {
        this.dayFinishHour = dayFinishHour;
    }

    /**
     * <p>First hour of the day to show in the day view.</p>
     *
     * @param dayStartHour the new value of dayStartHour.
     */
    public final void setDayStartHour(final int dayStartHour) {
        this.dayStartHour = dayStartHour;
    }

    /**
     * <p>For week and day views, contains events for day view which are
     * not all day events, indexed by hour.</p>
     *
     * <p>For month views, contains all events for each day, indexed by
     * day. There will be as many elements of the array as there are weeks
     * in the month.</p>
     *
     * <p>Each element of each <code>Map</code> is a sorted
     * <code>TreeSet</code> which uses <code>EventHourComparator</code> to
     * sort.</p>
     *
     * @param events new value of events.
     */
    public final void setEvents(final Map[] events) {
        this.events = events;
    }

    /**
     * <p>This attribute is used in week views to determine the date of
     * the first day to be shown.</p>
     *
     * @param firstWeekDay the new value of firstWeekDay.
     */
    public final void setFirstWeekDay(final GregorianCalendar firstWeekDay) {
        this.firstWeekDay = firstWeekDay;
    }

    /**
     * <p>
     * Used to format and display only the time part of an event.
     * </p>
     *
     * @param formatter format and display only the time part of an event.
     */
    public final void setTimeFormatter(final SettingDateFormatter formatter) {
        timeFormatter = formatter;
    }

    /**
     * <p>Represents the current view type, set to one of the constants in
     * {@link IndexFormConstants IndexFormConstants}. If unset, defaults
     * to
     * the settings <code>calendarDefaultView</code>.</p>
     *
     * @param view the new value of view.
     */
    public final void setView(final Integer view) {
        this.view = view;
    }

    /**
     * <p>Path of the page to include which will show the current
     * view.</p>
     *
     * @param viewPage the new value of viewPage.
     */
    public final void setViewPage(final String viewPage) {
        this.viewPage = viewPage;
    }

    /**
     * TODO
     *
     * @see com.ivata.mask.web.struts.MaskForm#validate(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession)
     */
    public ValidationErrors validate(final HttpServletRequest request,
            final HttpSession session) {
        // TODO Auto-generated method stub
        return null;
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

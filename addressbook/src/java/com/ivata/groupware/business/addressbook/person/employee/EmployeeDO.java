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
 * $Log: EmployeeDO.java,v $
 * Revision 1.3  2005/04/10 20:09:35  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:07  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:31  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/07/13 19:41:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/03/21 21:16:06  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:57:50  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.2  2003/02/04 17:43:45  colin
 * copyright notice
 *
 * Revision 1.1  2002/08/30 09:52:02  colin
 * added new employee EJB
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.person.employee;


import com.ivata.groupware.business.addressbook.address.country.CountryDO;
import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>Tracks which people are employees and the number of vacation days they
 * have remaining for the current calendar year.</p>
 *
 * <p>This is a dependent value class, used to pass data back from the.</p>
 * {@link EmployeeBean EmployeeBean} to a client application.</p>
 *
 * <p><strong>Note:</strong> This class provides data from {@link EmployeeBean EmployeeBean}.
 * This is no local copy of the bean class, however, and changes here
 * will not be automatically reflected in {@link EmployeeBean EmployeeBean}.</p>
 *
 * @since   2002-08-30
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table="person_employee"
 * @hibernate.cache
 *      usage="read-write"
 */
public class EmployeeDO extends BaseDO {
    /**
     * Static definitiaion of the display value prefix.
     */
    public static final String DISPLAY_VALUE_PREFIX = "[employee] ";
    /**
     * <p>Store the country for this employee.</p>
     */
    private CountryDO country;

    /**
     * <p>Store the internal company employee number. This number is used
     * by the client company for reference/internal use and is not required by
     * the ivata groupware system.</p>
     */
    private String number;

    /**
     * <p>Store the region for this employee. The region code together with the
     * country code is used to filter the employee's public holidays in the
     * calendar.</p>
     */
    private String regionCode;

    /**
     * <p>Store the total number of days vacation this employee has for the
     * current calendar year. <strong>Note:</strong> this is the total number of days
     * for the employee, not the number of days remaining. The system calculates
     * the days remaining by adding all previous vacation days within the
     * current calendar year.</p>
     */
    private Integer vacationDays;

    /**
     * <p>Get the country for this employee.</p>
     *
     * @return the country for the employee.
     * @hibernate.many-to-one
     *      column="address_country"
     */
    public final CountryDO getCountry() {
        return country;
    }
    /**
     * Just returns the text "[employee] " followed by the employee number.
     * Refer to {@link #getName}.
     * @return Refer to {@link #getName}.
     */
    public String getDisplayValue() {
        return DISPLAY_VALUE_PREFIX + getNumber();
    }
    /**
     * <p>Get the internal company employee number. This number is used
     * by the client company for reference/internal use and is not required by
     * the ivata groupware system.</p>
     *
     * @return employee number, for company records. Need not be numeric only.
     * @hibernate.property
     *      column="number"
     */
    public final String getNumber() {
        return number;
    }

    /**
     * <p>Get the region for this employee. The region code together with the
     * country code is used to filter the employee's public holidays in the
     * calendar.</p>
     *
     * @return text value for the region code.
     * @hibernate.property
     *      column="region_code"
     */
    public final String getRegionCode() {
        return regionCode;
    }

    /**
     * <p>Get the total number of days vacation this employee has for the
     * current calendar year. <strong>Note:</strong> this is the total number of days
     * for the employee, not the number of days remaining. The system calculates
     * the days remaining by adding all previous vacation days within the
     * current calendar year.</p>
     *
     * @return total number of days vacation for this employee for the current
     * calendar year.
     * @hibernate.property
     *      column="vacation_days"
     */
    public final Integer getVacationDays() {
        return vacationDays;
    }
    /**
     * <p>Set the country for this employee.</p>
     *
     * @param country new value of the country for the employee.
     */
    public final void setCountry(final CountryDO country) {
        this.country = country;
    }

    /**
     * <p>Set the internal company employee number. This number is used
     * by the client company for reference/internal use and is not required by
     * the ivata groupware system.</p>
     *
     * @param number employee number, for company records. Need not be numeric
     * only.
     */
    public final void setNumber(final String number) {
        this.number = number;
    }

    /**
     * <p>Set the region for this employee. The region code together with the
     * country code is used to filter the employee's public holidays in the
     * calendar.</p>
     *
     * @param regionCode new text value for the region code. Can be a maximum of
     * ten characters.
     */
    public final void setRegionCode(final String regionCode) {
        this.regionCode = regionCode;
    }

    /**
     * <p>Set the total number of days vacation this employee has for the
     * current calendar year. <strong>Note:</strong> this is the total number of days
     * for the employee, not the number of days remaining. The system calculates
     * the days remaining by adding all previous vacation days within the
     * current calendar year.</p>
     *
     * @param vacationDays total number of days vacation for this employee for
     * the current calendar year.
     */
    public final void setVacationDays(final Integer vacationDays) {
        this.vacationDays = vacationDays;
    }
}

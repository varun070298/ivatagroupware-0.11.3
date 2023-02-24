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
 * $Log: PersonDO.java,v $
 * Revision 1.3  2005/04/10 18:47:34  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:07  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:31  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.7  2004/11/12 15:57:05  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.6  2004/11/03 15:34:06  colinmacleod
 * Changed relationship between person and address:
 * each person for now has exactly one address.
 *
 * Revision 1.5  2004/07/18 21:59:10  colinmacleod
 * Removed Person from User - now you need to use addressbook/persistence manager to find the person (makes the app run faster.)
 *
 * Revision 1.4  2004/07/13 19:41:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:06  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
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
 * Revision 1.7  2003/08/21 09:50:03  jano
 * we have new field in personDO : memberOfUserGroups
 *
 * Revision 1.6  2003/08/20 16:24:27  jano
 * fixing addressBook extension
 *
 * Revision 1.5  2003/08/05 14:57:35  jano
 * addressBook extension
 *
 * Revision 1.4  2003/07/25 11:41:22  jano
 * adding functionality for addressBook extension
 *
 * Revision 1.3  2003/06/02 22:04:29  colin
 * added comparison method
 *
 * Revision 1.2  2003/04/09 08:52:53  jano
 * handling data of removing user
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.12  2003/02/21 16:18:36  peter
 * dateFormatter and birthDate Strings access taken off
 *
 * Revision 1.11  2003/02/18 11:08:54  colin
 * fixes in setTelecomAddress...(int...
 *
 * Revision 1.10  2003/02/04 17:43:44  colin
 * copyright notice
 *
 * Revision 1.9  2003/01/09 13:32:19  jano
 * we have fiield in Person called : createdBy - its name of user who created that contact
 *
 * Revision 1.8  2002/12/19 12:56:02  jano
 * when contact is read only then showonly name of main group
 *
 * Revision 1.7  2002/09/17 08:40:11  colin
 * addeed checkDO which can be used to check before a new person is added
 * improved methods for adding telecom addresses to the DO
 *
 * Revision 1.6  2002/09/09 08:37:38  colin
 * improved documentation
 * finished employee implementation via PersonBean.setDO
 *
 * Revision 1.5  2002/08/30 09:49:12  colin
 * added employee EJB
 * made country a separate EJB for address
 *
 * Revision 1.4  2002/08/29 08:49:21  jano
 * renamed field department to mainGroup
 *
 * Revision 1.3  2002/06/28 13:15:23  colin
 * first addressbook release
 *
 * Revision 1.2  2002/06/17 07:28:49  colin
 * improved and extended javadoc documentation
 *
 * Revision 1.1  2002/06/13 11:21:24  colin
 * first version with rose model integration.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.person;


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.business.addressbook.address.AddressDO;
import com.ivata.groupware.business.addressbook.person.employee.EmployeeDO;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.groupware.business.addressbook.telecomaddress.TelecomAddressConstants;
import com.ivata.groupware.business.addressbook.telecomaddress.TelecomAddressDO;
import com.ivata.groupware.container.persistence.NamedDO;
import com.ivata.mask.util.StringHandling;


/**
 * <p>Represents a single person within the intranet system. This person can
 * be simply within the address book, or can be a user by having a
 * {@link UserBean user} associated with it.</p>
 *
 * <p>This is a dependent value class, used to pass data back from the.</p>
 * {@link PersonBean PersonBean} to a client application.</p>
 *
 * <p><strong>Note:</strong> This class provides data from {@link PersonBean PersonBean}.
 * This is no local copy of the bean class, however, and changes here
 * will not be automatically reflected in {@link PersonBean PersonBean}.</p>
 *
 * @since 2002-05-12
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table="person"
 * @hibernate.cache
 *      usage="read-write"
 */
public class PersonDO extends NamedDO implements Comparable {
    /**
     * <p>Store this person's address.</p>
     */
    private AddressDO address;

    /**
     * <p>Store the company the person works for.</p>
     */
    private String company;

    /**
     * <p>Store the person's date of birth.</p>
     */
    private Date dateOfBirth;

    /**
     * <p>
     * Stores whether or not this person is logically deleted. If a person
     * is a user and is deleted, then (s)he will only be logically deleted.
     * </p>
     */
    private boolean deleted;

    /**
     * <p>Stores the employee record for this person as a data object.</p>
     */
    private EmployeeDO employee = null;

    /**
     * <p>Store the string to file the person under in the address book.</p>
     */
    private String fileAs;

    /**
     * <p>Store the person's first names.</p>
     */
    private String firstNames;

    /**
     * <p>Stores the group of this person.</p>
     */
    private GroupDO group;

    /**
     * <p>Store the job title for this person.</p>
     */
    private String jobTitle;

    /**
     * <p>Store the person's last name.</p>
     */
    private String lastName;

    /**
     * <p>Store the salutation with which this person likes to be greeted by
     * post or email.</p>
     */
    private String salutation;

    /**
     * <p>Store the communication addresses themeselves.</p>
     */
    private Set telecomAddresses = new HashSet();

    /**
     * <p>Stores the user for this person.</p>
     */
    private UserDO user;

    /**
     * Get all the addresses associated with this person.
     *
     * @return all addresses associated with this person.
     *
     * @hibernate.one-to-one
     *      cascade="all"
     */
    public AddressDO getAddress() {
        return address;
    }

    /**
     * <p>Get the company the person works for.</p>
     *
     * @return the company the person works for.
     * @hibernate.property
     *      column="company"
     */
    public final String getCompany() {
        return company;
    }

    /**
     * <p>Get the person's date of birth.</p>
     *
     * @return the person's date of birth.
     * @hibernate.property
     *      column="date_of_birth"
     */
    public final Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * <p>
     * Return the first name(s), followed by a space, followed by the last name.
     * </p>
     *
     * @see com.ivata.mask.valueobject.ValueObject#getDisplayValue()
     */
    public final String getDisplayValue() {
        StringBuffer displayValue = new StringBuffer();
        if (!StringHandling.isNullOrEmpty(firstNames)) {
            displayValue.append(firstNames);
        }
        if (!StringHandling.isNullOrEmpty(firstNames)) {
            if (displayValue.length() > 0) {
                displayValue.append(" ");
            }
            displayValue.append(lastName);
        }
        return displayValue.toString();
    }

    /**
     * <p>Get the person's email address, formatted according to <a
     * href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.</p>
     *
     *
     * <p>This gives you the form:<br>
     * <blockquote>&quot;<em>{firstName} {lastName}</em>&quot;&nbsp;&lt;<em>{email}</em>&gt;
     * </blockquote></p>
     *
     * <p>If the person has multiple email addresses, the returned one is the
     * first one found, as ordered by the address number.</p>
     *
     * @return formatted email address, or <code>null</code> if this person does
     *    not have an email address in the system.
     */
    public final String getEmailAddress() {
        String firstEmailAddress = null;
        for (Iterator telecomAddressIterator = telecomAddresses.iterator(); telecomAddressIterator.hasNext();) {
            TelecomAddressDO address = (TelecomAddressDO) telecomAddressIterator.next();
            if (address.getType() == TelecomAddressConstants.TYPE_EMAIL) {
                firstEmailAddress = address.getAddress();
                break;
            }
        }
        // if no addresses found, return null
        if (firstEmailAddress == null) {
            return null;
        }

        // first prepend the name if there is one for this person
        StringBuffer formattedEmailAddress = new StringBuffer();
        if (!StringHandling.isNullOrEmpty(firstNames)) {
            formattedEmailAddress.append(firstNames);
        }
        if (!StringHandling.isNullOrEmpty(lastName)) {
            if (formattedEmailAddress.length() > 0) {
                formattedEmailAddress.append(" ");
            }
            formattedEmailAddress.append(lastName);
        }
        if (formattedEmailAddress.length() > 0) {
            formattedEmailAddress.append(" ");
        }
        formattedEmailAddress.append("<");
        formattedEmailAddress.append(firstEmailAddress);
        formattedEmailAddress.append(">");

        return formattedEmailAddress.toString();
    }

    /**
     * <p>Get the employee record for this person as a data object.</p>
     *
     * @return employee record, or <code>null</code> if this person is not an
     * employee.
     * @hibernate.one-to-one
     *      cascade="all"
     *      property-ref="person"
     */
    public final EmployeeDO getEmployee() {
        return employee;
    }

    /**
     * <p>Get the string to file the person under in the address book.</p>
     *
     * @return the string to file the person under in the address book.
     * @hibernate.property
     *      column="file_as"
     */
    public final String getFileAs() {
        return fileAs;
    }

    /**
     * <p>Get the person's first names.</p>
     *
     * @return the person's first names, separated by a comma.
     * @hibernate.property
     *      column="first_names"
     */
    public final String getFirstNames() {
        return firstNames;
    }

    /**
     * <p>Get the group of this person.</p>
     *
     * @return the group this person is in.
     *
     * @hibernate.many-to-one
     *      column="person_group"
     */
    public final GroupDO getGroup() {
        return group;
    }
    /**
     * <p>Get the job title for this person.</p>
     *
     * @return the job title for this person.
     * @hibernate.property
     *      column="job_title"
     */
    public final String getJobTitle() {
        return  jobTitle;
    }

    /**
     * <p>Get the person's last name.</p>
     *
     * @return the person's last name.
     * @hibernate.property
     *      column="last_name"
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * <p>
     * Get the full name of this person. This is the same as the
     * <code>fileAs</code> value.
     * </p>
     *
     * @return fileAs value for this person
     */
    public final String getName() {
        return fileAs;
    }

    /**
     * <p>Get the saluation with which this person likes to be greeted by mail
     * or email.</p>
     *
     * @return the saluation with which this person likes to be greeted by mail
     *     or email.
     * @hibernate.property
     *      column="salutation"
     */
    public final String getSalutation() {
        return salutation;
    }

    /**
     * <p>Get the communication addresses themeselves.</p>
     *
     * @return a set containing the communication addresses themeselves.
     *
     * @hibernate.set
     *      cascade="all"
     *      role="telecom-addresses"
     * @hibernate.collection-key
     *      column="person"
     * @hibernate.collection-one-to-many
     *      class="com.ivata.groupware.business.addressbook.telecomaddress.TelecomAddressDO"
     */
    public final Set getTelecomAddresses() {
        return telecomAddresses;
    }

    /**
     * <p>Get the user for this person.</p>
     *
     * @return user name for this person, or <code>null</code> if this person is
     * not a user.
     * @hibernate.one-to-one
     *      cascade="all"
     */
    public UserDO getUser() {
        return user;
    }

    /**
     * @return Returns the deleted.
     * @hibernate.property
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param address The address to set.
     */
    public final void setAddress(final AddressDO address) {
        this.address = address;
    }
    /**
     * <p>Set the company the person works for.</p>
     *
     * @param company the company the person works for.
     */
    public final void setCompany(final String company) {
        this.company = company;
    }

    /**
     * <p>Set the person's date of birth.</p>
     *
     * @param dateOfBirth the new value supplied to person's date of birth.
     */
    public final void setDateOfBirth(final java.util.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    /**
     * @param deletedParam The deleted to set.
     */
    public void setDeleted(boolean deletedParam) {
        deleted = deletedParam;
    }

    /**
     * <p>Get the employee record for this person as a data object.</p>
     *
     * @param employee employee record, or <code>null</code> if this person is
     * not an employee.
     */
    public final void setEmployee(final com.ivata.groupware.business.addressbook.person.employee.EmployeeDO employee) {
        this.employee = employee;
    }

    /**
     * <p>Set the string to file the person under in the address book.</p>
     *
     * @param fileAs the string to file the person under in the address book.
     */
    public final void setFileAs(final String fileAs) {
        this.fileAs = fileAs;
    }

    /**
     * <p>Set the person's first names.</p>
     *
     * @param firstNames the person's first names, separated by a comma.
     */
    public final void setFirstNames(final String firstNames) {
        this.firstNames = firstNames;
    }

    /**
     * <p>Set the group of this person.</p>
     *
     * @param group new value of the group.
     */
    public final void setGroup(final GroupDO group) {
        this.group = group;
    }

    /**
     * <p>Set the job title for this person.</p>
     *
     * @param jobTitle the job title for this person.
     */
    public final void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * <p>Set the person's last name.</p>
     *
     * @param lastName the person's last name.
     */
    public final void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * <p>Set the saluation with which this person likes to be greeted by mail or
     * email.</p>
     *
     * @param salutation the saluation with which this person likes to be
     *     greeted by mail or email.
     */
    public final void setSalutation(final String salutation) {
        this.salutation = salutation;
    }

    /**
     * @param set
     */
    public final void setTelecomAddresses(final Set set) {
        telecomAddresses = set;
    }

    /**
     * <p>Set the user for this person.</p>
     *
     * @param user new user for this person, or <code>null</code> if
     * this person is not a user.
     */
    public final void setUser(final UserDO user) {
        this.user = user;
    }
}

/*
 * Copyright (c) 2001 - 2005 ivata limited.
 * All rights reserved.
 * -----------------------------------------------------------------------------
 * ivata groupware may be redistributed under the GNU General Public
 * License as published by the Free Software Foundation;
 * version 2 of the License.
 *
 * These programs are free software; you can redistribute them and/or
 * modify them under the terms of the GNU General License
 * as published by the Free Software Foundation; version 2 of the License.
 *
 * These programs are distributed in the hope that they will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General License in the file LICENSE.txt for more
 * details.
 *
 * If you would like a copy of the GNU General License write to
 *
 * Free Software Foundation, Inc.
 * 59 Temple Place - Suite 330
 * Boston, MA 02111-1307, USA.
 *
 *
 * To arrange commercial support and licensing, contact ivata at
 *                  http://www.ivata.com/contact.jsp
 * -----------------------------------------------------------------------------
 * $Log: AddressBook.java,v $
 * Revision 1.4  2005/04/29 02:48:14  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:09:33  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:06  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:18  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.5  2004/11/12 18:17:10  colinmacleod
 * Ordered imports.
 *
 * Revision 1.4  2004/11/12 15:56:47  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.3  2004/07/18 21:59:12  colinmacleod
 * Removed Person from User - now you need to use addressbook/persistence
 * manager to find the person (makes the app run faster.)
 *
 * Revision 1.2  2004/07/13 19:41:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.1  2004/03/26 21:34:35  colinmacleod
 * Split off functionality into POJO.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook;

import java.util.List;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.addressbook.address.AddressDO;
import com.ivata.groupware.business.addressbook.address.country.CountryDO;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationErrors;


/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since Mar 22, 2004
 * @version $Revision: 1.4 $
 */
public interface AddressBook {
    public final static String BUNDLE_PATH = "addressBook";
    /**
     * <p>Add bew AddressBook.</p>
     * @param userName
     * @param groupDO
     * @return
     */
    GroupDO addAddressBook(final SecuritySession securitySession,
            final GroupDO groupDO)
        throws SystemException;

    /**
     * <p>Add a new group to the address book.</p>
     *
     * @param userName the name of the user who wants to add the group. This is
     *     used to check user rights.
     * @param groupDO a data object containing all the details
     *     of the group to add.
     * @exception com.ivata.groupware.ejb.entity.UserRightException if the user
     *     provided is not entitled to add this group
     * @exception com.ivata.groupware.ejb.entity.InvalidFieldValueException
     *     if a field value in the new group contains an invalid value
     * @return the new group data object, with the details as they
     *     now are in the adressbook.
     */
    GroupDO addGroup(final SecuritySession securitySession,
            final GroupDO groupDO)
            throws SystemException;

    /**
     * <p>Add a new person to the address book.</p>
     *
     * @param personDO data object containing the details of the
     *     person you want to add.
     * @return the new person data object, with the details as they
     *     now are in the adressbook.
     */
    PersonDO addPerson(final SecuritySession securitySession,
            final PersonDO personDO)
            throws SystemException;
    /**
     * <p>add new userGroup.</p>
     * @param userName
     * @param groupDO
     * @return
     */
    GroupDO addUserGroup(final SecuritySession securitySession,
            final GroupDO groupDO)
        throws SystemException;

    /**
     * <p>Amend the details of a group in the address book.</p>
     *
     * @param userName the name of the user who wants to amend the group. This
     *     is used to check user rights.
     * @param groupDO a data object containing all the details
     *     of the group to amend.
     * @exception com.ivata.groupware.ejb.entity.UserRightException if the user
     *     provided is not entitled to amend this group.
     * @exception com.ivata.groupware.ejb.entity.InvalidFieldValueException
     *     if a field value in the new group contains an invalid value.
     * @return the new group data object, with the details as they
     *     now are in the adressbook.
     */
    GroupDO amendGroup(final SecuritySession securitySession,
            final GroupDO groupDO)
        throws SystemException;

    /**
     * <p>Change/update a person's details in the addressbook.</p>
     *
     * @param personDO data object containing the details of the
     *     person you want to amend.
     * @return the new person data object, with the details as they
     *     now are in the adressbook.
     */
    PersonDO amendPerson(final SecuritySession securitySession,
            final PersonDO personDO)
        throws SystemException;

    /**
     * <p>get Map of all address book names which are allowed with specific access.</p>
     * @param userName
     * @param access
     * @return
     */
    List findAddressBooks(final SecuritySession securitySession,
            final boolean includePersonal)
        throws SystemException;

    /**
     * <p>Find all of the countries in the system.</p>
     *
     * @return all of the coutries in the system as a <code>List</code>
     * of {@link com.ivata.groupware.business.addressbook.address.country.CountryDO CountryDO}
     * instances.
     */
    List findAllCountries(final SecuritySession securitySession)
        throws SystemException;

    /**
     * <p>Find All People in a specific address book.</p
     *
     * @see com.ivata.groupware.business.addressbook.AddressBook#findAllPeople(com.ivata.groupware.admin.security.server.SecuritySession, Integer, Integer, String)
     */
    List findAllPeopleInGroup(final SecuritySession securitySession,
            final GroupDO group,
            final String initialLetter) throws SystemException;

    /**
     * <p>Find a single county identified by its two-letter country code.</p>
     *
     * @param countryCode two-letter internet country code.
     * @return details of the country as an instance of
     * {@link com.ivata.groupware.business.addressbook.address.country.CountryDO CountryDO),
     * or <code>null</code> if no such country exists.
     */
    CountryDO findCountryByCode(final SecuritySession securitySession,
            final String countryCode)
        throws SystemException;

    /**
     * <p>Find a group in the address book by its unique identifier.</p>
     *
     * @param id the unique identifier of the group to find.
     * @return the data object of the group which matches this unique
     *      identifier.
     */
    GroupDO findGroupByPrimaryKey(final SecuritySession securitySession,
            final Integer id)
        throws SystemException;

    /**
     * <p>
     * Find all groups which are siblings, identified by the parent group.
     * </p>
     */
    List findGroupsByParent(final SecuritySession securitySession,
            final Integer parentId)
            throws SystemException;

    /**
     * <p>Find Id of personal AddressBook.</p>
     * @param userName
     * @return
     */
    GroupDO findPersonalAddressBook(final SecuritySession securitySession)
        throws SystemException;

    /**
     * <p>Find a person in the address book by their unique identifier.</p>
     *
     * @param id the unique identifier of the person to find.
     * @return the person data object which matches this id, with the
     *      details as they now are in the adressbook.
     */
    PersonDO findPersonByPrimaryKey(final SecuritySession securitySession,
            final String id)
        throws SystemException;

    /**
     * <p>Find a person in the address book by their user name.</p>
     *
     * @param userName Name of the user to find.
     * @return the person data object which matches this user name.
     */
    PersonDO findPersonByUserName(final SecuritySession securitySession,
            final String userName)
        throws SystemException;

    /**
     * <p>get Map of all usergroup names which are allowed with specific access.</p>
     * @param userName
     * @param access
     * @return
     */
    List findUserGroups(final SecuritySession securitySession,
            final boolean includeAdministrator)
        throws SystemException;

    /**
     * <p>Remove a group from the address book.</p>
     *
     * @param userName the name of the user who wants to remove the group. This
     *     is used to check user rights.
     * @param groupDO a data object containing all the details
     *     of the group to remove. The id of the group is used to identify which
     *      group to remove.
     * @exception com.ivata.groupware.ejb.entity.UserRightException if the user
     *     provided is not entitled to remvoe this group.
     * @exception com.ivata.groupware.ejb.entity.InvalidFieldValueException
     *     if the id of the group contains an invalid value.
     */
    void removeGroup(final SecuritySession securitySession,
            final Integer id)
        throws SystemException;

    /**
     * <p>Remove a person from the address book.</p>
     *
     * @param personDO data object containing the details of the
     *     person you want to remove. The id is used to locate and remove the
     *     person.
     */
    void removePerson(final SecuritySession securitySession,
            final Integer id)
            throws SystemException;

    /**
     * <p>Confirm all of the elements of the group are present and valid,
     * before the message is sent.</p>
     *
     * @param groupDO data object to check for consistency and
     *     completeness.
     * @return a collection of validation errors if any of the
     *     mandatory fields are missing, or if fields contain invalid values.
     */
    ValidationErrors validate(final SecuritySession securitySession,
            final AddressDO addressDO)
        throws SystemException;

     /**
      * <p>Confirm all of the elements of the group are present and valid,
      * before the message is sent.</p>
      *
      * @param groupDO data object to check for consistency and
      *     completeness.
      * @return a collection of validation errors if any of the
      *     mandatory fields are missing, or if fields contain invalid values.
      */
     ValidationErrors validate(final SecuritySession securitySession,
            final GroupDO groupDO)
         throws SystemException;

    /**
     * <p>Confirm all of the elements of the person are present and valid,
     * before the message is sent.</p>
     *
     * @param personDO data object to check for consistency and
     *     completeness.
     * @return a collection of validation errors if any of the
     *     mandatory fields are missing, or if fields contain invalid values.
     */
    ValidationErrors validate(final SecuritySession securitySession,
            final PersonDO personDO)
        throws SystemException;
}

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
 * $Log: AddressBookBean.java,v $
 * Revision 1.4  2005/04/29 02:48:14  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:09:34  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:06  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:19  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.10  2004/11/12 18:17:10  colinmacleod
 * Ordered imports.
 *
 * Revision 1.9  2004/11/12 15:56:47  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.8  2004/11/03 15:36:48  colinmacleod
 * Changed relationship between person and address:
 * each person for now has exactly one address.
 *
 * Revision 1.7  2004/07/18 21:59:12  colinmacleod
 * Removed Person from User - now you need to use addressbook/persistence manager to find the person (makes the app run faster.)
 *
 * Revision 1.6  2004/07/13 19:41:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.5  2004/03/27 10:29:41  colinmacleod
 * Added getAddressBook implementation.
 *
 * Revision 1.4  2004/03/26 21:34:35  colinmacleod
 * Split off functionality into POJO.
 *
 * Revision 1.3  2004/03/21 21:16:05  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:49  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.6  2003/12/12 11:08:58  jano
 * fixing aaddressbook functionaality
 *
 * Revision 1.5  2003/11/03 11:28:24  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.3  2003/10/15 13:36:47  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.20  2003/08/21 09:49:22  jano
 * removed unusing methods
 *
 * Revision 1.19  2003/08/20 16:23:59  jano
 * fixing addressBook extension
 *
 * Revision 1.18  2003/08/19 14:53:30  jano
 * *** empty log message ***
 *
 * Revision 1.17  2003/08/14 15:54:19  jano
 * fixing bugs
 *
 * Revision 1.16  2003/08/14 08:19:59  jano
 * fixing bug
 *
 * Revision 1.15  2003/08/13 13:32:16  jano
 * addressBook exttension: next level
 *
 * Revision 1.14  2003/08/05 14:51:03  jano
 * addressBook extension
 *
 * Revision 1.13  2003/08/05 05:54:06  jano
 * new methods in addressbook
 *
 * Revision 1.12  2003/07/31 08:45:57  jano
 * new method findAllPeople
 * removing all similar methods
 *
 * Revision 1.10  2003/07/25 11:40:17  jano
 * adding functionality for addressBook extension
 *
 * Revision 1.9  2003/06/18 14:20:43  jano
 * fixing problem with removing user
 *
 * Revision 1.8  2003/05/23 13:04:46  peter
 * uncommented email address validation
 *
 * Revision 1.7  2003/05/12 13:45:52  colin
 * added new methods for finding rights
 *
 * Revision 1.6  2003/05/02 15:14:57  peter
 * added email address validation
 *
 * Revision 1.5  2003/04/09 08:51:48  jano
 * handling data of removing user
 *
 * Revision 1.4  2003/04/02 09:04:05  jano
 * again, fixing bug with wrong keys in validate method
 *
 * Revision 1.3  2003/03/31 15:44:04  jano
 * mistake in validate method, using wrong key of applicationResources
 *
 * Revision 1.2  2003/02/27 12:57:40  peter
 * fixed the resouceBundle paths in validation methods
 *
 * Revision 1.1  2003/02/24 19:09:20  colin
 * moved to business
 *
 * Revision 1.28  2003/02/21 09:23:17  colin
 * fixed bug when fileAs values are the same (only 1 was shown)
 *
 * Revision 1.27  2003/02/20 20:49:53  colin
 * changed checking of unique fileAs field to ValidaitonException
 *
 * Revision 1.26  2003/02/20 20:22:54  colin
 * improved validation error handling
 *
 * Revision 1.25  2003/02/20 14:34:02  colin
 * addGroupsToPerson no longer adds child groups to the person (just the parent)
 *
 * Revision 1.24  2003/02/18 11:11:27  colin
 * first release of address book with Struts
 *
 * Revision 1.23  2003/02/14 07:57:07  colin
 * changed finder in AddressBookBean so it finds all groups (rather than just parent groups)
 *
 * Revision 1.22  2003/02/04 17:43:44  colin
 * copyright notice
 *
 * Revision 1.21  2003/01/30 15:29:38  colin
 * added group validation
 *
 * Revision 1.20  2003/01/10 10:30:06  jano
 * we need information about user who created group
 *
 * Revision 1.19  2003/01/09 13:31:49  jano
 * we have fiield in Person called : createdBy - its name of user who created that contact
 *
 * Revision 1.18  2003/01/09 10:02:51  jano
 * we are not storing group's rights in geoupDO now
 * we are using methods in addressBookRightsBean
 *
 * Revision 1.17  2003/01/03 15:43:50  jano
 * we don't need tempFindTarge...... method, we are using getTargetIdsByAUD
 *
 * Revision 1.16  2002/12/16 13:41:34  jano
 * in there is not groupId we have to find only those contacts which we can see
 *
 * Revision 1.15  2002/12/12 15:03:28  jano
 * rights for groups
 *
 * Revision 1.14  2002/12/03 15:42:19  jano
 * if the person is user so put him to USER group if he is not there
 *
 * Revision 1.13  2002/10/11 12:51:10  colin
 * added check for duplicate values of fileAs
 *
 * Revision 1.12  2002/09/30 12:51:34  jano
 * trow exception if the group i main group for any people
 *
 * Revision 1.11  2002/09/27 14:18:19  jano
 * when you want delete group contain sub groups throw exception
 *
 * Revision 1.10  2002/09/17 08:36:59  colin
 * added checkDO to check the person before adding
 *
 * Revision 1.9  2002/09/09 08:38:10  colin
 * moved user handling to new security bean
 *
 * Revision 1.8  2002/09/02 08:47:48  colin
 * added country handling and sorting (comparator)
 *
 * Revision 1.7  2002/08/30 09:48:45  colin
 * added employee EJB
 *
 * Revision 1.6  2002/08/29 14:33:43  colin
 * changed AddressBook: added isUserEnabled method
 *
 * Revision 1.5  2002/07/26 13:07:21  colin
 * split user rights off into separate class
 * added mail server methods
 *
 * Revision 1.4  2002/06/28 13:15:22  colin
 * first addressbook release
 *
 * Revision 1.3  2002/06/17 10:45:47  colin
 * Tidied up the canUser... code
 *
 * Revision 1.2  2002/06/17 07:28:51  colin
 * improved and extended javadoc documentation
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.addressbook.address.AddressDO;
import com.ivata.groupware.business.addressbook.address.country.CountryDO;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationErrors;


/**
 * <p>This address book provides clients with access to the register of people
 * within the ivata groupware system.</p>
 *
 * <p>Actions are here to retrieve people, change their details and enter new
 * people into the system. This bean manages people's street and
 * telecommunications addresses, together with which people are users and what
 * group(s) each person is a member of.</p>
 *
 * <p>Furthermore, this bean is responsible for assigning and maintaining
 * employee information.</p>
 *
 * <p><strong>Note</strong>, however that whilst the addressbook may react on which
 * people are users (it checks those people always have an email address), this
 * <em>EJB</em> is not responsible for adding, removing or amending users or their
 * passwords. For that functionality, see the {@link com.ivata.groupware.admin.security.SecurityBean
 * SecurityBean}.</p>
 *
 * @since 2002-05-12
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 * TODO: the employee and user methods need to check the user rights
 *
 * @ejb.bean
 *      name="AddressBook"
 *      display-name="AddressBook"
 *      type="Stateless"
 *      view-type="remote"
 *      jndi-name="AddressBookRemote"
 *
 * @ejb.transaction
 *      type = "Required"
 *
 *  @ejb.home
 *      generate="false"
 *      remote-class="com.ivata.groupware.business.addressbook.AddressBookRemoteHome"
 *
 *  @ejb.interface
 *      remote-class="com.ivata.groupware.business.addressbook.AddressBookRemote"
 */
public class AddressBookBean implements SessionBean, AddressBook {
    /**
     * <p>Provides the session bean with container-specific information.</p>
     */
    SessionContext sessionContext;


    /**
     * <p>Add bew AddressBook.</p>
     * @param userName
     * @param groupDO
     * @return
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public GroupDO addAddressBook(final SecuritySession securitySession,
            final GroupDO groupDO)
            throws SystemException {
        return getAddressBook().addAddressBook(securitySession, groupDO);
    }

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
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public GroupDO addGroup(final SecuritySession securitySession,
            final GroupDO groupDO)
            throws SystemException {
        return getAddressBook().addGroup(securitySession, groupDO);
    }

    /**
     * <p>Add a new person to the address book.</p>
     *
     * @param personDO data object containing the details of the
     *     person you want to add.
     * @return the new person data object, with the details as they
     *     now are in the adressbook.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public PersonDO addPerson(final SecuritySession securitySession,
            final PersonDO personDO)
            throws SystemException {
        return getAddressBook().addPerson(securitySession, personDO);
    }

    /**
     * <p>add new userGroup.</p>
     * @param userName
     * @param groupDO
     * @return
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public GroupDO addUserGroup(final SecuritySession securitySession,
            final GroupDO groupDO)
            throws SystemException {
        return getAddressBook().addUserGroup(securitySession, groupDO);
    }

    /**
     * <p>Amend the details of a group in the public address book.</p>
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
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public GroupDO amendGroup(final SecuritySession securitySession,
            final GroupDO groupDO)
            throws SystemException {
        return getAddressBook().amendGroup(securitySession, groupDO);
    }

    /**
     * <p>Change/update a person's details in the addressbook.</p>
     *
     * @param personDO data object containing the details of the
     *     person you want to amend.
     * @return the new person data object, with the details as they
     *     now are in the adressbook.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public PersonDO amendPerson(final SecuritySession securitySession,
            final PersonDO personDO)
            throws SystemException {
        return getAddressBook().amendPerson(securitySession, personDO);
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
    public final void ejbRemove() {}

    /**
     * <p>get Map of all address book names which are allowed with specific access.</p>
     * @param userName
     * @param access
     * @return
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public List findAddressBooks(final SecuritySession securitySession,
            final boolean includePersonal)
            throws SystemException {
        return getAddressBook().findAddressBooks(securitySession, includePersonal);
    }

    /**
     * <p>Find all of the countries in the system.</p>
     *
     * @return all of the coutries in the system as a <code>List</code>
     * of {@link com.ivata.groupware.business.addressbook.address.country.CountryDO CountryDO}
     * instances.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public List findAllCountries(final SecuritySession securitySession)
            throws SystemException {
        return getAddressBook().findAllCountries(securitySession);
    }


    /**
     * <p>Find All People in a specific address book.</p
     *
     * @see com.ivata.groupware.business.addressbook.AddressBook#findAllPeople(com.ivata.groupware.admin.security.server.SecuritySession, Integer, Integer, String)
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public List findAllPeopleInGroup(final SecuritySession securitySession,
            final GroupDO group,
            final String initialLetter) throws SystemException {
        return getAddressBook().findAllPeopleInGroup(securitySession, group,
            initialLetter);
    }

    /**
     * <p>Find a single county identified by its two-letter country code.</p>
     *
     * @param countryCode two-letter internet country code.
     * @return details of the country as an instance of
     * {@link com.ivata.groupware.business.addressbook.address.country.CountryDO CountryDO),
     * or <code>null</code> if no such country exists.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public CountryDO findCountryByCode(final SecuritySession securitySession,
            final String countryCode)
            throws SystemException {
        return getAddressBook().findCountryByCode(securitySession, countryCode);
    }

    /**
     * <p>Find a group in the address book by its unique identifier.</p>
     *
     * @param id the unique identifier of the group to find.
     * @return the data object of the group which matches this unique
     *      identifier.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public GroupDO findGroupByPrimaryKey(final SecuritySession securitySession,
            final Integer id)
            throws SystemException {
        return getAddressBook().findGroupByPrimaryKey(securitySession, id);
    }

    /**
     * <p>
     * Find all groups which are siblings, identified by the parent group.
     * </p>
     * @see com.ivata.groupware.business.addressbook.AddressBook#findGroupsByParent(com.ivata.groupware.admin.security.server.SecuritySession, Integer)
     */
    public List findGroupsByParent(final SecuritySession securitySession,
            final Integer parentId) throws SystemException {
        return getAddressBook().findGroupsByParent(securitySession, parentId);
    }

    /**
     * <p>Find Id of personal AddressBook.</p>
     * @param userName
     * @return
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public GroupDO findPersonalAddressBook(final SecuritySession securitySession)
            throws SystemException {
        return getAddressBook().findPersonalAddressBook(securitySession);
    }

    /**
     * <p>Find a person in the address book by their unique identifier.</p>
     *
     * @param id the unique identifier of the person to find.
     * @return the person data object which matches this id, with the
     *      details as they now are in the adressbook.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public PersonDO findPersonByPrimaryKey(final SecuritySession securitySession,
            final String id)
            throws SystemException {
        return getAddressBook().findPersonByPrimaryKey(securitySession, id);
    }

    /**
     * <p>Find a person in the address book by their user name.</p>
     *
     * @param userName Name of the user to find.
     * @return the person data object which matches this user name.
     * @see com.ivata.groupware.business.addressbook.AddressBook#findPersonByUserName(com.ivata.groupware.admin.security.server.SecuritySession, String)
     */
    public PersonDO findPersonByUserName(final SecuritySession securitySession,
            final String userName) throws SystemException {
        return getAddressBook().findPersonByUserName(securitySession, userName);
    }

    /**
     * <p>get Map of all usergroup names which are allowed with specific access.</p>
     * @param userName
     * @param access
     * @return
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public List findUserGroups(final SecuritySession securitySession,
            final boolean includeAdministrator)
            throws SystemException {
        return getAddressBook().findUserGroups(securitySession, includeAdministrator);
    }

    /**
     * Get the addressbook implementation from the <code>PicoContainer</code>.
     */
    private AddressBook getAddressBook()
            throws SystemException {
        PicoContainer container = PicoContainerFactory.getInstance()
            .getGlobalContainer();
        return (AddressBook) container.getComponentInstance(AddressBook.class);
    }

    /**
     * <p>Remove a group from the public address book.</p>
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
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public void removeGroup(final SecuritySession securitySession,
            final Integer id)
            throws SystemException {
        getAddressBook().removeGroup(securitySession, id);
    }

    /**
     * <p>Remove a person from the address book.</p>
     *
     * @param personDO data object containing the details of the
     *     person you want to remove. The id is used to locate and remove the
     *     person.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public void removePerson(final SecuritySession securitySession,
            final Integer id)
            throws SystemException {
        getAddressBook().removePerson(securitySession, id);
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
     * <p>Confirm all of the elements of the person are address and valid,
     * before the message is sent.</p>
     *
     * @param addressDO data object to check for consistency and
     *     completeness.
     * @return a collection of validation errors if any of the
     *     mandatory fields are missing, or if fields contain invalid values.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public ValidationErrors validate(final SecuritySession securitySession,
            final AddressDO addressDO)
            throws SystemException {
        return getAddressBook().validate(securitySession, addressDO);
    }

    /**
     * <p>Confirm all of the elements of the group are present and valid,
     * before the message is sent.</p>
     *
     * @param groupDO data object to check for consistency and
     *     completeness.
     * @return a collection of validation errors if any of the
     *     mandatory fields are missing, or if fields contain invalid values.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public ValidationErrors validate(final SecuritySession securitySession,
            final GroupDO groupDO)
            throws SystemException {
        return getAddressBook().validate(securitySession, groupDO);
    }

    /**
     * <p>Confirm all of the elements of the person are present and valid,
     * before the message is sent.</p>
     *
     * @param personDO data object to check for consistency and
     *     completeness.
     * @return a collection of validation errors if any of the
     *     mandatory fields are missing, or if fields contain invalid values.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public ValidationErrors validate(final SecuritySession securitySession,
            final PersonDO personDO)
            throws SystemException {
        return getAddressBook().validate(securitySession, personDO);
    }

}

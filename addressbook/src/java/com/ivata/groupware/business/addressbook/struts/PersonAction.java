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
 * $Log: PersonAction.java,v $
 * Revision 1.5  2005/05/10 20:03:34  colinmacleod
 * Added checking for demo version
 * so the demo no longer removes users.
 *
 * Revision 1.4  2005/05/01 08:43:06  colinmacleod
 * In clear, no longer removes the session
 * attributes.
 *
 * Revision 1.3  2005/04/10 18:47:36  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:08  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:25  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.9  2004/12/31 18:27:43  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.8  2004/12/23 21:01:25  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.7  2004/11/12 18:19:14  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.6  2004/11/12 15:57:07  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.5  2004/11/03 15:31:50  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.4  2004/07/13 19:41:14  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:08  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:33  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:54  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.4  2003/12/12 11:08:58  jano
 * fixing addressbook functionality
 *
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.18  2003/09/11 13:10:30  jano
 * fixing bugs with renaming userName and with removing user from system
 *
 * Revision 1.17  2003/08/25 08:36:38  jano
 * fixing bug with new contact and TABS
 *
 * Revision 1.16  2003/08/22 15:23:51  jano
 * fixing bug
 *
 * Revision 1.15  2003/08/21 09:49:32  jano
 * fixing for new addressBook extension
 *
 * Revision 1.14  2003/08/20 16:24:15  jano
 * fixing addressBook extension
 *
 * Revision 1.13  2003/07/25 11:41:04  jano
 * adding functionality for addressBook extension
 *
 * Revision 1.12  2003/06/18 14:22:12  jano
 * fixing problem with removing user
 * and spaces in userName
 * and capitals in userName
 *
 * Revision 1.11  2003/06/10 10:42:15  peter
 * changed keys when flushing tree JSP caches
 *
 * Revision 1.10  2003/06/09 08:14:49  jano
 * fixing user aliases and his emails
 *
 * Revision 1.9  2003/06/04 09:57:54  jano
 * fixing addUser to Person
 * and email if user has not it
 *
 * Revision 1.8  2003/06/04 07:17:23  peter
 * jndiPrefix test fixed
 *
 * Revision 1.7  2003/04/09 13:29:17  jano
 * fixing bug
 *
 * Revision 1.6  2003/04/09 12:10:22  jano
 * *** empty log message ***
 *
 * Revision 1.5  2003/04/09 09:43:55  jano
 * *** empty log message ***
 *
 * Revision 1.4  2003/04/09 08:53:20  jano
 * handling data of removing user
 *
 * Revision 1.3  2003/02/25 17:45:57  colin
 * no longer refreshes the opener on cancel
 *
 * Revision 1.2  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean
 * superclass
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.14  2003/02/21 10:06:41  colin
 * added missing import for EmployeeDO
 *
 * Revision 1.13  2003/02/21 09:50:56  colin
 * fixed bug - null employee on apply
 *
 * Revision 1.12  2003/02/20 20:23:17  colin
 * improved validation error handling
 *
 * Revision 1.11  2003/02/20 16:18:31  colin
 * fixed algorithm for adding email address to people
 *
 * Revision 1.10  2003/02/20 13:57:07  colin
 * moved amendPerson to end
 *
 * Revision 1.9  2003/02/20 13:46:37  colin
 * changed jndiPrefix handling to application server
 *
 * Revision 1.8  2003/02/20 13:33:46  colin
 * update person when extra email address is added
 *
 * Revision 1.7  2003/02/20 13:31:10  colin
 * added checking for an email address with users
 *
 * Revision 1.6  2003/02/20 08:32:07  colin
 * made apply also go thro execute to catch changes to main group id
 *
 * Revision 1.5  2003/02/20 07:45:24  colin
 * fixed bug where name of main group doesn't show in personSummary.jsp
 *
 * Revision 1.4  2003/02/18 11:10:27  colin
 * first release of address book with Struts
 *
 * Revision 1.3  2003/02/14 08:59:48  colin
 * changed findParentGroups... to findGroups...
 *
 * Revision 1.2  2003/02/04 17:39:47  colin
 * updated for new struts interface
 *
 * Revision 1.1  2003/01/30 15:27:28  colin
 * first version
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.struts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.addressbook.AddressBookSecurity;
import com.ivata.groupware.admin.security.right.SecurityRights;
import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.admin.security.user.UserConstants;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.business.addressbook.AddressBook;
import com.ivata.groupware.business.addressbook.address.AddressDO;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.addressbook.person.employee.EmployeeDO;
import com.ivata.groupware.business.addressbook.person.group.GroupConstants;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.groupware.business.addressbook.right.AddressBookRights;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>Invoked when the user edits, displays or enters a new person -
 * it's all the same to ivata groupware.</p>
 *
 * @since 2003-01-26
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.5 $
 *
 * @TODO dependency AddressbookAction on WebMail
 */
public class PersonAction extends MaskAction {
    /**
     * <p>This log provides tracing and debugging information.</p>
     */
    private static Logger log = Logger.getLogger(PersonAction.class);
    private AddressBook addressBook;
    private AddressBookRights addressBookRights;
    private AddressBookSecurity security;
    private SecurityRights securityRights;
    private Settings settings;

    /**
     * <p>
     * TODO
     * </p>
     *
     * @param addressBook
     * @param addressBookRights
     * @param security
     * @param securityRights
     * @param settings
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public PersonAction(AddressBook addressBook,
            AddressBookRights addressBookRights,
            AddressBookSecurity security, SecurityRights securityRights,
            Settings settings,
            MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.addressBook = addressBook;
        this.addressBookRights = addressBookRights;
        this.security = security;
        this.securityRights = securityRights;
        this.settings = settings;
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
     */
    public void clear(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        if (log.isDebugEnabled()) {
            log.debug("In PersonAction.clear");
        }

        PersonForm personForm = (PersonForm) form;
        personForm.clear();
        if (personForm.getRefreshOnExit()
                && StringHandling.isNullOrEmpty(personForm.getClear())) {
            // set the opener page to the address book
            request.setAttribute("openerPage", "/addressBook/index.action");
        }

        if (log.isDebugEnabled()) {
            log.debug("Out PersonAction.clear");
        }
    }

    /**
     * <p>Overridden from the default intranet implementation to
     * process tab changes.</p>
     *
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there
     * are any errors, the action will return to the input.
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
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        if (log.isDebugEnabled()) {
            log.debug("In PersonAction.execute");
        }

        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");
        PersonForm personForm = (PersonForm) form;

        // Shouldn't struts do this? *confused*
        session.setAttribute("addressBookPersonForm", personForm);

        // see if the address book groups are there - if not, initialize them
        // from the address book service layer
        Map addressBookGroupNames = personForm.getAddressBookGroupNames();
        if (addressBookGroupNames == null) {
            personForm.setAddressBookGroupNames(addressBookGroupNames =
                new HashMap());
            List allAddressBookGroups = addressBook.findAddressBooks(
                    securitySession, true);
            Iterator groupIterator = allAddressBookGroups.iterator();
            while(groupIterator.hasNext()) {
                GroupDO addressBookGroup = (GroupDO) groupIterator.next();
                addressBookGroupNames.put(addressBookGroup.getId(),
                        addressBookGroup.getName());
            }
        }
        // users are always in the default public address book
        PersonDO person = personForm.getPerson();
        if (!StringHandling.isNullOrEmpty(personForm.getUserName())
                || (personForm.getAddressBookGroupId() == null)) {
            GroupDO defaultAddressBook =
                addressBook.findGroupByPrimaryKey(securitySession,
                        GroupConstants.ADDRESS_BOOK_DEFAULT);
            personForm.setAddressBookGroupId(defaultAddressBook.getId());
            personForm.setAddressBookGroupName(defaultAddressBook.getName());
            person.setGroup(defaultAddressBook);
        } else {
            // update the address book name to match the id
            personForm.setAddressBookGroupName((String)
                    addressBookGroupNames.get(
                            personForm.getAddressBookGroupId()));
        }

        // get/set a list of all possible user groups
        if (personForm.getUserGroups() == null) {
            personForm.setUserGroups(addressBook.findUserGroups(securitySession,
                    true));
        }

        // ensure there is an address object we can use
        if (person.getAddress() == null) {
            person.setAddress(new AddressDO());
        }
        // and ensure there is an employee
        if (person.getEmployee() == null) {
            person.setEmployee(new EmployeeDO());
        }

        // likewise, we need a country
        personForm.findAndSetCountry(securitySession);

        GroupDO group = person.getGroup();
        if (group == null) {
            person.setGroup(group = new GroupDO());
        }
        UserDO user = person.getUser();
        if (user == null) {
            person.setUser(user = new UserDO());
        }
        if (user.getGroups() == null) {
            user.setGroups(new HashSet());
        }

        // update the telecom addresses in the person
        personForm.setPersonFromTelecomAddresses();


        String activeTabKey =
            (personForm.getPersonTab_activeTabKey() == null)
            ? ""
            : personForm.getPersonTab_activeTabKey();

        // if we want to restore user
        // but really restore her only on Apply/OK button
        if (!StringHandling.isNullOrEmpty(personForm.getRestoreUser())) {
            user.setDeleted(false);
            personForm.setUserName(person.getUser().getName());
            // TODO personForm.getUserAliases().add(personForm.getUserName());

            // add the first one as an email address
            int size = personForm.getTelecomAddresses().size();
            String emailSubHost =
                settings.getStringSetting(
                    securitySession,
                    "emailSubHost",
                    user);


/*TODO            TelecomAddressDO newAddress = new TelecomAddressDO();
            newAddress.setAddress(personForm.getUserName()
             + "@" + emailSubHost);
            newAddress.setNumber(telecomAddresses.size());
            newAddress.setType(TelecomAddressConstants.TYPE_EMAIL);
            telecomAddresses.add(newAddress);
            personForm.setRestoreUser("");*/
        }

        // create the keys of all the tabs we want to show
        List tabKeys = personForm.getTabKeys();
        if (tabKeys == null) {
            personForm.setTabKeys(tabKeys = new Vector());
        } else {
            tabKeys.clear();
        }

        // first the standard ones - these are always shown
        tabKeys.add("person.tab.contact");
        tabKeys.add("person.tab.address");
        tabKeys.add("person.tab.telecomAddress");

        // only show the extra tabs if this is not a new contact - you must
        // add a contact first before you can make him/her a user or employee
        if (person.getId() != null) {
            // if this is a user, show a different delete warning message
            if (user.getId() != null) {
                personForm.setDeleteKey("person.alert.delete.user");
            } else {
                personForm.setDeleteKey("person.alert.delete");
            }

            // user tab is only shown if the person already has a user, or the
            // current user has the rights to add one
            if ((user.getId() != null)
                    || securityRights.canAddUser(securitySession)) {
                tabKeys.add("person.tab.user");
            }
            // groups tab is only shown if the person has a user - you need to
            // add one first, then apply to change the groups.
            // TODO: for now this is never shown: remove the 'false &&' when
            // user rights are enabled again
            if (false
                    && (user.getId() != null)) {
                tabKeys.add("person.tab.groups");
            }
            // employee tab is only shown if the user has the rights
            if (addressBookRights.canAddEmployeeToPerson(securitySession,
                    person)) {
                tabKeys.add("person.tab.employee");
            }
        }
        // set Up READ ONLY flag
        personForm.setReadOnly(!addressBookRights.canAmendInGroup(
                securitySession, person.getGroup().getAddressBook()));
        if (personForm.getReadOnly()
                && person.getCreatedBy().equals(securitySession.getUser())) {
            personForm.setReadOnly(false);
        }
        // set Up CAN REMOVE flag - the only person you definitely can't
        // remove is the root administrator user
        boolean canRemove = addressBookRights.canRemoveFromGroup(
                securitySession, person.getGroup().getAddressBook())
                && !UserConstants.ADMINISTRATOR.equals(
                                personForm.getPerson().getId());
        // users cannot be removed if this is a demo
        Boolean demoVersion = settings.getBooleanSetting(securitySession,
            "demoVersion", null);
        if (demoVersion.booleanValue()
                && (person.getUser() != null)
                && (person.getUser().getId() != null)) {
            canRemove = false;
        }
        personForm.setCanRemove(canRemove);

        if ("person.tab.address".equals(activeTabKey)) {
            personForm.setTabPage("/addressBook/address.jsp");
            personForm.setHelpKey("addressbook.address");
        } else if ("person.tab.telecomAddress".equals(activeTabKey)) {
            personForm.setTabPage("/addressBook/telecomAddress.jsp");
            personForm.setHelpKey("addressbook.telecomAddress");
            // and update the telecom addresses back to the form again, to make
            // sure we have the extra blank ones.
            personForm.setTelecomAddressesFromPerson();

        // TODO: who added this one? check it out!
        } else if ("person.tab.restoreUser".equals(activeTabKey)) {
            personForm.setTabPage("/addressBook/restoreUser.jsp");
            personForm.setHelpKey("addressbook.restoreUser");

        } else if ("person.tab.user".equals(activeTabKey)) {
            personForm.setTabPage("/addressBook/user.jsp");
            personForm.setHelpKey("addressbook.user");
        } else if ("person.tab.employee".equals(activeTabKey)) {
            // this could be 4 if the user tab is not showing
            personForm.setTabPage("/addressBook/employee.jsp");
            personForm.setHelpKey("addressbook.employee");
        } else {
            personForm.setTabPage("/addressBook/personDetails.jsp");
            personForm.setHelpKey("addressbook.person");
        }

        if (log.isDebugEnabled()) {
            log.debug("Out PersonAction.execute");
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
     * @param defaultForward &quot;ok&quot; if the <code>Ok</code> button
     * was pressed, otherwise &quot;apply&quot; if the <code>Apply</code> button
     * was pressed.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     */
    public String onConfirm(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session,
            final String defaultForward) throws SystemException {
        if (log.isDebugEnabled()) {
            log.debug("In PersonAction.onConfirm");
        }

        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");
        PersonForm personForm = (PersonForm) form;
        PersonDO person = personForm.getPerson();
        UserDO user = person.getUser();
        if (user == null) {
            person.setUser(user = new UserDO());
        }

        // set the user group from the id
        GroupDO group = addressBook.findGroupByPrimaryKey(securitySession,
                person.getGroup().getId());
        person.setGroup(group);

        // update the telecom addresses in the person
        personForm.setPersonFromTelecomAddresses();

        AddressDO address = person.getAddress();
        if (StringHandling.isNullOrEmpty(address.getPostCode())
                && StringHandling.isNullOrEmpty(address.getRegion())
                && StringHandling.isNullOrEmpty(address.getStreetAddress())
                && StringHandling.isNullOrEmpty(address.getTown())) {
            // if there is no address, ignore the address elements for
            // validation purposes (we'll set it back again at the end of this
            // method
            person.setAddress(null);
        }

        // add a new person
        if (person.getId() == null) {
            // if it's a new person, add them to the address book
            // initially the person is only a member of the group
            person.setEmployee(null);
            // you need to apply person changes before you can create a new
            // user
            person.setUser(null);
            person = addressBook.addPerson(securitySession, person);

            // amend an existing person
        } else {
            // if this is not an employee, remove the employee from the
            // person
            if (!personForm.isEmployee()) {
                person.setEmployee(null);
            }

            // if there is to be no user name, clear the user
            if (StringHandling.isNullOrEmpty(personForm.getUserName())) {
                if (user.getId() == null) {
                    person.setUser(null);
                } else {
                    user.setDeleted(true);
                    security.amendUser(securitySession, user);
                }
            } else {
                user.setName(personForm.getUserName());
                user.setDeleted(false);
                user.setEnabled(personForm.getEnableUser());
                if (user.getId() == null) {
                    user.setId(person.getId());
                    security.addUserToPerson(securitySession, person);
                } else {
                    security.amendUser(securitySession, user);
                }
            }

            person = addressBook.amendPerson(securitySession, person);
        }

        //flush the cached group and person trees
        //flushCache("ContactTree",
        //   javax.servlet.jsp.PageContext.APPLICATION_SCOPE, request);

        // if this is the okay button, just get out now
        if ("ok".equals(defaultForward)) {
            if (log.isDebugEnabled()) {
                log.debug("Out PersonAction.onConfirm (ok)");
            }
            return "utilClosePopUp";
        }
        // if we are applying changes, save the (possibly) new person back
        // in the form again
        // make an empty employee, in case this changes in form
        if (person.getEmployee() == null) {
            person.setEmployee(new EmployeeDO());
        }
        personForm.setPerson(person);

        if (log.isDebugEnabled()) {
            log.debug("Out PersonAction.onConfirm");
        }

        // in case we deleted the address, set it back again
        person.setAddress(address);
        // go back thro' execute - we might have changed the group
        execute(mapping, errors, form, request, response, session);
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
     */
    public String onDelete(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session, final String defaultForward) throws SystemException {
        if (log.isDebugEnabled()) {
            log.debug("Out PersonAction.onDelete");
        }

        PersonForm personForm = (PersonForm) form;
        PersonDO person = personForm.getPerson();
        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");

        UserDO user = person.getUser();
        if (user.getId() != null) {
            user.setDeleted(true);
            security.amendUser(securitySession, user);
        }

        // do you want to delete this poor person?
        addressBook.removePerson(securitySession, person.getId());
        if (log.isDebugEnabled()) {
            log.debug("Out PersonAction.onDelete");
        }
        return "utilClosePopUp";
    }
}

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
 * $Log: PersonListAction.java,v $
 * Revision 1.4  2005/04/29 02:48:14  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:09:38  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:08  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:24  colinmacleod
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
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.3  2003/08/05 14:57:35  jano
 * addressBook extension
 *
 * Revision 1.2  2003/07/31 08:56:21  jano
 * we are using new method for finding all people
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.3  2003/02/14 08:59:48  colin
 * changed findParentGroups... to findGroups...
 *
 * Revision 1.2  2003/02/04 17:39:47  colin
 * updated for new struts interface
 *
 * Revision 1.1  2003/01/30 09:02:20  colin
 * updates for struts conversion
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.struts;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.addressbook.AddressBook;
import com.ivata.groupware.business.addressbook.person.group.GroupConstants;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>Retrieves a list of people for the main index page of the
 * address book.</p>
 *
 * @since 2003-01-26
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 */
public class PersonListAction extends MaskAction {
    private AddressBook addressBook;
    /**
     * <p>TODO</p>
     *
     * @param addressBook
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public PersonListAction(AddressBook addressBook,
            MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.addressBook = addressBook;
    }


    /**
     * <p>Retrieve a list of people to show in the main index view of the
     * address book.</p>
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
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        // group is from the tree combo selector (frmPersonList), index from tabs
        Integer comboAddressBook = null;
        Integer comboGroup = null;
        String index = null;
        try {
            comboAddressBook = (Integer) PropertyUtils.getSimpleProperty(form, "comboAddressBook");
            comboGroup = (Integer) PropertyUtils.getSimpleProperty(form, "comboGroup");
            index = (String) PropertyUtils.getSimpleProperty(form, "index");
            // request parameter overrides the form
            String requestIndex = request.getParameter("index");
            if (!StringHandling.isNullOrEmpty(requestIndex)) {
                PropertyUtils.setSimpleProperty(form, "index", index = requestIndex);
            }
        } catch (NoSuchMethodException e) {
            throw new SystemException(e);
        } catch (InvocationTargetException e) {
            throw new SystemException(e);
        } catch (IllegalAccessException e) {
            throw new SystemException(e);
        }
        // request overrides form state
        if (request.getParameter("index") != null) {
            index = request.getParameter("index");
        }
        if (request.getParameter("comboAddressBook") != null) {
            comboAddressBook = StringHandling.integerValue(
                    request.getParameter("comboAddressBook"));
        }
        if (request.getParameter("comboGroup") != null) {
            comboGroup = StringHandling.integerValue(
                    request.getParameter("comboGroup"));
        }

        // have to assign the results to something
        List results;

        // need to do this conversion, because 4 - the address_book group doesn't contain
        // people, and the search by group isn't recursive... all the people
        // will be found this way - with no group membership lookup
        if ((comboAddressBook == null)
                || comboAddressBook.equals("4")) {
            comboAddressBook = new Integer(0);
            comboGroup = new Integer(0);
        }

        // default the index to everyone
        if (StringHandling.isNullOrEmpty(index)) {
            index = "all";
        }
        String initialLetter = "all".equals(index) ? null : index;
        try {
            Integer groupId;

            // if there is a specific group, go for that
            if (!"".equals(comboGroup)) {
                groupId = comboGroup;

            // otherwise see if a general address book was specified
            } else if (!"".equals(comboAddressBook)) {
                groupId = comboAddressBook;
            } else {
                groupId = GroupConstants.ADDRESS_BOOK;
            }
            GroupDO parentGroup = addressBook.findGroupByPrimaryKey(securitySession,
                groupId);
            results = addressBook.findAllPeopleInGroup(securitySession,
                parentGroup, initialLetter);
        } catch(SystemException e) {
            throw new SystemException(e);
        }

        List addressBooks = addressBook.findAddressBooks(securitySession, true);
        List addressBookNames = new Vector();
        Iterator addressBookIterator = addressBooks.iterator();
        while(addressBookIterator.hasNext()) {
            GroupDO addressBook = (GroupDO) addressBookIterator.next();
            addressBookNames.add(addressBook.getName());
        }

        // now store the results we want to show
        try {
            PropertyUtils.setSimpleProperty(form, "comboAddressBook", comboAddressBook);
            PropertyUtils.setSimpleProperty(form, "comboGroup", comboGroup);
            PropertyUtils.setSimpleProperty(form, "index", index);
            PropertyUtils.setSimpleProperty(form, "addressBooks", addressBookNames);
            PropertyUtils.setSimpleProperty(form, "results", results);
        } catch (NoSuchMethodException e) {
            throw new SystemException(e);
        } catch (InvocationTargetException e) {
            throw new SystemException(e);
        } catch (IllegalAccessException e) {
            throw new SystemException(e);
        }

        // this list always goes to the same page
        return null;
    }
}

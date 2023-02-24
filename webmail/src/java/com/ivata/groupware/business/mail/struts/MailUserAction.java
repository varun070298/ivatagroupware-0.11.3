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
 * $Log: MailUserAction.java,v $
 * Revision 1.2  2005/04/09 17:20:01  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:19  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.8  2004/12/31 18:27:44  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.7  2004/12/23 21:01:34  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.6  2004/11/12 18:19:16  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.5  2004/11/12 15:57:25  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.4  2004/11/03 15:31:52  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.3  2004/07/13 19:48:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/03/21 21:16:39  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 21:00:00  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/10/28 13:27:51  jano
 * commiting webmail,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:11:33  colin
 * fixing for XDoclet
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.mail.struts;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.addressbook.AddressBookSecurity;
import com.ivata.groupware.admin.security.right.SecurityRights;
import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.business.addressbook.AddressBook;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.addressbook.right.AddressBookRights;
import com.ivata.groupware.business.addressbook.struts.PersonAction;
import com.ivata.groupware.business.mail.Mail;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>Extends the person action from the standard address book class to add
 * in mail user settings.</p>
 *
 * <p>To use this class rather than the standard AddressBook class, you need to
 * change the struts config file to specify this class instead of
 * <code>PersonAction</code>.</p>
 *
 * TODO: this class needs to be substituted for the PersonAction in the
 *     struts file.
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 */
public class MailUserAction extends PersonAction {
    Mail mail;
    /**
     * TODO
     * @param mail
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
    public MailUserAction(Mail mail, AddressBook addressBook,
            AddressBookRights addressBookRights,
            AddressBookSecurity security, SecurityRights securityRights,
            Settings settings,
            MaskFactory maskFactory,
            MaskAuthenticator authenticator) {
        super(addressBook, addressBookRights, security, securityRights, settings,
                maskFactory, authenticator);
        this.mail = mail;
    }

    /* (non-Javadoc)
     * @see com.ivata.groupware.business.struts.MaskAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionMessages, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session)
            throws SystemException {
        MailUserForm mailUserForm = (MailUserForm) form;
        if (mailUserForm.getUserAliases() == null) {
            UserDO user = null;
            if (mailUserForm.getPerson() != null) {
                user = mailUserForm.getPerson().getUser();
            }
            // if there is no user, just create an empty user aliases list
            if ((user == null)
                    || StringHandling.isNullOrEmpty(user.getName())) {
                mailUserForm.setUserAliases(new Vector());
            }
            // otherwise get the user aliases for this user
            else {
                SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
                mailUserForm.setUserAliases(new Vector(mail.getUserAliases(
                        securitySession, user.getName())));
            }
        }

        return super.execute(mapping, errors, form, request, response, session);
    }
    public String onConfirm(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session,
            final String defaultForward) throws SystemException {
        // first process the default implementation of person action
        String returnValue = super.onConfirm(mapping, errors, form, request, response,
                session, defaultForward);
        MailUserForm mailUserForm = (MailUserForm) form;
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        PersonDO person = mailUserForm.getPerson();
        UserDO user = person.getUser();

        if ((user != null)
                && !StringHandling.isNullOrEmpty(user.getName())) {
            // TODO: - vacation message not currently implemented
            // mail.setVacationMessage(personForm.getUserName(), personForm.getVacationMessage());
            // NOTE: This MUST come after updating the user
            mail.setUserAliases(securitySession, user.getName(),
                mailUserForm.getUserAliases());
        }

        return returnValue;
    }
}

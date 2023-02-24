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
 * $Log: FindMailUserAction.java,v $
 * Revision 1.3  2005/04/10 20:10:08  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:20:01  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:18  colinmacleod
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
 * Revision 1.1.1.1  2004/01/27 20:59:59  colinmacleod
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

import com.ivata.groupware.admin.security.Security;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.business.addressbook.AddressBook;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.addressbook.right.AddressBookRights;
import com.ivata.groupware.business.addressbook.struts.FindPersonAction;
import com.ivata.groupware.business.mail.Mail;
import com.ivata.groupware.business.mail.session.MailSession;
import com.ivata.groupware.util.SettingDateFormatter;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>Extends the find person action from the standard address book class to add
 * in user alias and other mail user settings.</p>
 *
 * <p>To use this class rather than the standard AddressBook class, you need to
 * change the struts config file to specify this class instead of
 * <code>FindPersonAction</code>.</p>
 *
 * TODO: this class needs to be substituted for the FindPersonAction in the
 *     struts file.
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 */
public class FindMailUserAction extends FindPersonAction {
    AddressBook addressBook;
    Mail mail;
    Security security;
    /**
     * TODO
     * @param addressBook
     * @param addressBookRights
     * @param mail
     * @param dateFormatter
     * @param security
     * @param settings
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public FindMailUserAction(AddressBook addressBook, AddressBookRights
            addressBookRights, Mail mail, SettingDateFormatter dateFormatter,
            Security security, Settings settings,
            MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(addressBook, addressBookRights, dateFormatter, security, settings,
                maskFactory, authenticator);
        this.addressBook = addressBook;
        this.mail = mail;
        this.security = security;
    }

    /**
     * <p>Overridden to set the vacation message and user aliases.</p>
     *
     * @see com.ivata.mask.web.struts.MaskAction#execute
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        String returnValue = super.execute(mapping, errors, form, request,
                response, session);

        MailUserForm mailUserForm = (MailUserForm) session.getAttribute(
                "addressBookPersonForm");

        // set up the user
        String id = request.getParameter("id");

        if (id == null) {
            throw new SystemException("ERROR in FindPersonAction: id is null",
                null);
        }

        PersonDO person;
        MailSession mailSession = (MailSession) session.getAttribute("securitySession");
        person = addressBook.findPersonByPrimaryKey(mailSession, id);

        String userNameSet = person.getUser().getName();

        // only get the email aliases and vacation message if the user name is
        // set to something
        if (!StringHandling.isNullOrEmpty(userNameSet)) {
            mailUserForm.setUserAliases(new Vector(mail.getUserAliases(
                mailSession, userNameSet)));
            mailUserForm.setVacationMessage(mail.getVacationMessage(
                mailSession, userNameSet));
        }

        mailUserForm.setUserName(userNameSet);
        mailUserForm.setEnableUser((userNameSet != null) &&
            security.isUserEnabled(mailSession, userNameSet));

        return returnValue;
    }
}

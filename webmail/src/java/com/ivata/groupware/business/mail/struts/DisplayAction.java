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
 * $Log: DisplayAction.java,v $
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
 * Revision 1.10  2004/12/31 18:27:44  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.9  2004/12/23 21:01:34  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.8  2004/11/12 18:19:16  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.7  2004/11/12 15:57:25  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.6  2004/11/03 15:31:52  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.5  2004/07/13 19:48:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:39  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/03/21 20:51:33  colinmacleod
 * Change SecurityServer into interface.
 * Added checking of mail server.
 *
 * Revision 1.2  2004/02/01 22:07:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:59:59  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.4  2004/01/12 14:02:23  jano
 * fixing bugs
 *
 * Revision 1.3  2003/10/28 13:27:51  jano
 * commiting webmail,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:11:33  colin
 * fixing for XDoclet
 *
 * Revision 1.6  2003/06/03 08:09:13  peter
 * compose for is in the session nowadays
 *
 * Revision 1.5  2003/03/26 16:28:09  peter
 * after trashing a mail, opener refreshes
 *
 * Revision 1.4  2003/03/26 15:19:09  peter
 * after thrashing a mail, opener goes to index
 *
 * Revision 1.3  2003/03/25 08:25:33  jano
 * if we didn't find a message forward to noMessage.jsp
 *
 * Revision 1.2  2003/02/25 11:53:33  colin
 * bugfixes and minor restructuring
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.3  2003/02/04 17:39:10  colin
 * updated for new MaskAction interface
 *
 * Revision 1.2  2003/01/27 07:27:46  colin
 * added request override for form parameters
 *
 * Revision 1.1  2003/01/18 20:24:26  colin
 * changed design from thread to display based
 *
 * Revision 1.1  2002/11/17 20:05:47  colin
 * first version of struts threading
 * supports forward/reply but HTML not yet supported
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
import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.business.mail.Mail;
import com.ivata.groupware.business.mail.message.MessageDO;
import com.ivata.groupware.business.mail.server.NoMailServerException;
import com.ivata.groupware.business.mail.session.MailSession;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>This <code>Action</code> is invoked when displaying a message.
 * This action also handles replying to, forwarding or editing a mail
 * for resending.</p>
 *
 * @since 2002-11-11
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class DisplayAction extends MaskAction {
    Mail mail;
    Settings settings;
    /**
     * TODO
     * @param mail
     * @param settings
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public DisplayAction(Mail mail, Settings settings,
            MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.mail = mail;
        this.settings = settings;
    }

    /**
     * <p>Build the form for <code>/mail/display.jsp</code> from an
     * existing mail.</p>
     *
     * @param mapping The ActionMapping used to select this instance.
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
        // Was this transaction cancelled? For us, that means the close button
        // was pressed
        if (isCancelled(request)) {
            return "mailIndex";
        }

        DisplayForm displayForm = (DisplayForm) form;
        MessageDO message = displayForm.getMessage();

        // request parameters override the form ones
        if (request.getParameter("folderName") != null) {
            displayForm.setFolderName(request.getParameter("folderName"));
        }

        if (request.getParameter("id") != null) {
            displayForm.setId(request.getParameter("id"));
        }

        // used if we forward or delete the message
        Vector idVector = new Vector();
        idVector.add(message.getMessageID());

        // is there a new thread (forward/reply) from this message?
        MailSession mailSession;
        try {
            mailSession = (MailSession) session.getAttribute("securitySession");
        } catch (ClassCastException e) {
            session.setAttribute("mailServerException",
                new NoMailServerException(e));
            return "serverError";
        }
        UserDO user = mailSession.getUser();

        if (displayForm.getThread() != null) {
            SecuritySession securitySession = (SecuritySession)
                    session.getAttribute("securitySession");
            PicoContainer picoContainer = securitySession.getContainer();
            ComposeForm composeForm = (ComposeForm)
                    PicoContainerFactory.getInstance()
                        .instantiateOrOverride(picoContainer,
                            ComposeForm.class);

            MessageDO newMessage = mail.createThreadMessage(mailSession,
                    displayForm.getFolderName(), idVector,
                    displayForm.getThread());
            composeForm.setMessage(newMessage);
            session.setAttribute("mailComposeForm", composeForm);

            return "mailCompose";
        }

        // move the current message to trash?
        if (displayForm.getDelete()) {
            String trashFolderName = settings.getStringSetting(mailSession,
                    "emailFolderTrash",
                    user);
            mail.moveMessages(mailSession, displayForm.getFolderName(),
                idVector, trashFolderName);
            request.setAttribute("refreshOpener", "1");

            return "success";
        }

        // delete the current message from trash
        if (displayForm.getDeleteTrash()) {
            mail.deleteMessagesFromTrash(mailSession, idVector);
        }

        // ok, so get the message we actually want to show
        message = mail.findMessageByFolderMessageId(mailSession,
                displayForm.getFolderName(), displayForm.getId());

        // if we didn't find message forward to page with info text
        if (message == null) {
            return "noMessage";
        }

        displayForm.setMessage(message);

        // down here means we've found our message and want to display it
        return "mailDisplay";
    }
}

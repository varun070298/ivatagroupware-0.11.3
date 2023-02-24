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
 * $Log: IndexAction.java,v $
 * Revision 1.3.2.1  2005/10/08 17:35:02  colinmacleod
 * Backported HEAD version to add logging.
 *
 * Revision 1.6  2005/10/03 10:21:16  colinmacleod
 * Fixed some style and javadoc issues.
 *
 * Revision 1.5  2005/10/02 14:08:59  colinmacleod
 * Added/improved log4j logging.
 *
 * Revision 1.4  2005/09/14 16:16:52  colinmacleod
 * Removed unused local and class variables.
 * Added serialVersionUID.
 *
 * Revision 1.3  2005/04/10 19:26:05  colinmacleod
 * Cosmetic changes.
 *
 * Revision 1.2  2005/04/09 17:20:01  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:17  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.10  2004/12/31 18:27:44  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.9  2004/11/12 18:19:16  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.8  2004/11/12 15:57:25  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.7  2004/11/03 15:31:52  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.6  2004/07/13 19:48:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.5  2004/03/21 21:16:39  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.4  2004/03/21 20:51:33  colinmacleod
 * Change SecurityServer into interface.
 * Added checking of mail server.
 *
 * Revision 1.3  2004/03/10 22:38:16  colinmacleod
 * Added security server exception handling.
 *
 * Revision 1.2  2004/02/01 22:07:33  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:59:59  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.5  2004/01/12 14:02:23  jano
 * fixing bugs
 *
 * Revision 1.4  2003/12/12 13:24:34  jano
 * fixing webmail functionality
 *
 * Revision 1.3  2003/10/28 13:27:51  jano
 * commiting webmail,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:11:33  colin
 * fixing for XDoclet
 *
 * Revision 1.7  2003/06/04 11:22:03  peter
 * fixed always setting the most recent sortDirection a messageList to session
 *
 * Revision 1.6  2003/06/03 13:13:10  peter
 * fixed sorting
 *
 * Revision 1.5  2003/03/14 12:27:37  peter
 * added logic for wrong folder name - defaults to inbox
 *
 * Revision 1.4  2003/03/03 16:57:12  colin
 * converted localization to automatic paths
 * added labels
 * added mandatory fieldName attribute
 *
 * Revision 1.3  2003/02/28 13:32:41  jano
 * clearing field movoTo after moveing messages to specifik folder
 * and
 * set false forwardMessages
 *
 * Revision 1.2  2003/02/25 11:53:33  colin
 * bugfixes and minor restructuring
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.4  2003/02/04 17:39:10  colin
 * updated for new MaskAction interface
 *
 * Revision 1.3  2003/01/27 07:28:17  colin
 * cosmetic changes
 *
 * Revision 1.2  2003/01/18 20:27:25  colin
 * added new mail action superclass
 * many fixes
 *
 * Revision 1.1  2002/11/17 20:05:08  colin
 * first version of struts mail index
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.mail.struts;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecurityServerException;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.business.mail.Mail;
import com.ivata.groupware.business.mail.MailConstants;
import com.ivata.groupware.business.mail.message.MessageDO;
import com.ivata.groupware.business.mail.server.NoMailServerException;
import com.ivata.groupware.business.mail.session.MailSession;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.util.ThrowableHandling;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>This <code>Action</code> is invoked when displaying the main
 * listing of a mail folder.</p>
 *
 * @since 2002-11-14
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3.2.1 $
 */
public class IndexAction extends MaskAction {
    /**
     * <copyDoc>Refer to {@link Logger}.</copyDoc>
     */
    private static final Logger logger = Logger.getLogger(IndexAction.class);
    /**
     * <copyDoc>Refer to {@link Index()}.</copyDoc>
     */
    private Mail mail;
    /**
     * <copyDoc>Refer to {@link Index()}.</copyDoc>
     */
    private Settings settings;
    /**
     * Construct the action.
     *
     * @param mailParam This object is used to retrieve all the messages.
     * @param settingsParam Used to retrieve the names of common mail folders.
     * @param maskFactory {@inheritDoc}
     * @param authenticator {@inheritDoc}
     */
    public IndexAction(
            final Mail mailParam,
            final Settings settingsParam,
            final MaskFactory maskFactory,
            final MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.mail = mailParam;
        this.settings = settingsParam;
    }

    /**
     * <p>Build the form for <code>/mail/index.jsp</code> to either list a
     * new folder, go to the next page (remembering the selections) or
     * react to user selections.</p>
     *
     * @param mapping {@inheritDoc}
     * @param form {@inheritDoc}
     * @param request {@inheritDoc}
     * @param response {@inheritDoc}
     * @param session {@inheritDoc}
     * @return &quot;mailServerError&quot; if the mail server has not been
     * set up correctly, &quot;mailCompose&quot; if some messages should
     * be forwarded, otherwise &quot;mailIndex&quot;.
     * @throws SystemException If the messages cannot be retrieved for any
     * reason.
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        if (logger.isDebugEnabled()) {
            logger.debug("execute(ActionMapping mapping = " + mapping
                    + ", ActionForm form = " + form
                    + ", HttpServletRequest request = " + request
                    + ", HttpServletResponse response = " + response
                    + ", HttpSession session = " + session
                    + ") - start");
        }

        IndexForm indexForm = (IndexForm) form;

        MailSession mailSession;
        try {
            mailSession = (MailSession) session.getAttribute("securitySession");
        } catch (ClassCastException e) {
            logger.error("Class cast exception getting the mail security "
                    + "session. This means we don't have a mail session.",
                    e);

            session.setAttribute("mailServerException",
                new NoMailServerException(e));

            if (logger.isDebugEnabled()) {
                logger.debug("execute - end - return value = mailServerError");
            }
            return "mailServerError";
        }
        final String folderName = getFolderName(mailSession, indexForm);

        // all the selected ids are stored in the session
        HashSet selectedMessageIds = (HashSet) session.getAttribute(
                "selectedMessageIds");

        // if there is no selected message ids in the session yet, make one now
        if (selectedMessageIds == null) {
            selectedMessageIds = new HashSet();
            session.setAttribute("selectedMessageIds",
                selectedMessageIds);

            // this flag is stored in index.jsp to say we should retain the
            // selected messages
        } else if (!indexForm.getRetainPrevious()) {
            selectedMessageIds.clear();
        }

        // first add all message numbers in this page, subtract the selected
        // ones. this gives us the unselected ones to subtract from our main
        // list
        HashSet removeIds = new HashSet();
        removeIds.addAll(Arrays.asList(indexForm.getMessageIds()));

        List selectedList = Arrays.asList(indexForm.getSelectedMessageIds());
        removeIds.removeAll(selectedList);

        // now set up all of the messages in this folder in the request
        try {
            // get the messages and save them for display
            getIndexMessages(request, mailSession, folderName, indexForm);
        } catch (SystemException e) {
            String returnValue = handleMailIndexException(e, request,
                    folderName);
            if (logger.isDebugEnabled()) {
                logger.debug("execute() - end - returning "
                        + returnValue);
            }
            return returnValue;
        }

        // must remove all unchecked ones, so we don't get them from before
        selectedMessageIds.removeAll(removeIds);

        // set the selected Ids in the form
        indexForm.setSelectedMessageIds((String[]) selectedMessageIds.toArray(
                indexForm.getSelectedMessageIds()));

        String forwardName = null;
        if (!selectedMessageIds.isEmpty()) {
            forwardName = processSelectedMessages(request, mailSession,
                    folderName, indexForm, selectedMessageIds);
        }

        // if it gets down here, then it has all gone well :-)
        if (forwardName == null) {
            forwardName = "mailIndex";
        }
        if (logger.isDebugEnabled()) {
            logger.debug("execute - end - return value = "
                    + forwardName);
        }
        return forwardName;
    }
    /**
     * Get the name of the folder we are currently processing.
     *
     * @param mailSession Current mail session for the user who is displaying
     * the page.
     * @param indexForm Form returned from the index page.
     * @return The name of the folder.
     * @throws SystemException If one of the system settings for standard
     * folder names cannot be read.
     */
    private String getFolderName(
            final MailSession mailSession,
            final IndexForm indexForm)
            throws SystemException {
        if (logger.isDebugEnabled()) {
            logger.debug("getFolderName(MailSession mailSession = "
                    + mailSession + ", IndexForm indexForm = " + indexForm
                    + ") - start");
        }

        // default to the inbox
        String folderName = null;

        if (indexForm.getFolderName() != null) {
            folderName = indexForm.getFolderName().toLowerCase();
        }

        UserDO user = mailSession.getUser();
        if (StringHandling.isNullOrEmpty(folderName)) {
            folderName = settings.getStringSetting(
                    mailSession,
                    "emailFolderInbox",
                    user);
            indexForm.setFolderName(folderName);

            // translate 'special' folder names
            // done this way to keep the menu item names independent of IMAP
            // implementation
        } else if (folderName.equals("sent")) {
            folderName = settings.getStringSetting(
                    mailSession,
                    "emailFolderSent",
                    user);
            indexForm.setFolderName(folderName);
        } else if (folderName.equals("drafts")) {
            folderName = settings.getStringSetting(
                    mailSession,
                    "emailFolderDrafts",
                    user);
            indexForm.setFolderName(folderName);
        } else if (folderName.equals("trash")) {
            folderName = settings.getStringSetting(
                    mailSession,
                    "emailFolderTrash",
                    user);
            indexForm.setFolderName(folderName);
        } else {
            folderName = settings.getStringSetting(
                    mailSession,
                    "emailFolderInbox",
                    user);
            indexForm.setFolderName(folderName);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("getFolderName() - end - return value = "
                            + folderName);
        }
        return folderName;
    }
    /**
     * This method actually gets the messages we will show, and saves them
     * in the session.
     *
     * @param request Current request we are processing.
     * @param mailSession Current mail session for the user who is displaying
     * the page.
     * @param folderName Name of the folder being displayed.
     * @param indexForm Form returned from the index page.
     * @throws SystemException Thrown by the mail subsystem if we cannot
     * retrieve the messages.
     */
    private void getIndexMessages(
            final HttpServletRequest request,
            final MailSession mailSession,
            final String folderName,
            final IndexForm indexForm)
            throws SystemException {
        if (logger.isDebugEnabled()) {
            logger.debug("getIndexMessages(HttpServletRequest request = "
                    + request + ", MailSession mailSession = " + mailSession
                    + ", String folderName = " + folderName
                    + ", IndexForm indexForm = " + indexForm + ") - start");
        }

        HttpSession session = request.getSession();
        List sessionMessages = null;

        // if the mail folder is the same as the one in the session, and
        // it has not been modified, retrieve it from the session
        String sessionFolderName = (String) session.getAttribute(
                "mailIndexFolderName");
        Integer sessionSortBy = (Integer) session.getAttribute(
                "mailIndexFolderSortBy");
        Boolean sessionSortAscending = (Boolean) session.getAttribute(
                "mailIndexFolderSortAscending");
        sessionMessages = (List) session.getAttribute("mailIndexMessages");

        Integer sortBy = indexForm.getSortBy();
        Boolean sortAscending = new Boolean(indexForm.getSortAscending());

        boolean modified = true;

        // this folder might not exist yet
        // see if it is the same as before, as only then do we need to
        // compare modified times
        if (folderName.equals(sessionFolderName)
                || (sessionFolderName == null)) {
            modified = mail.hasNewMessages(
                    mailSession,
                    mailSession.getUser().getName(),
                    folderName);
        }
        // see if this is the same folder we indexed last time. If so, don't
        // try to index it again.
        if (modified
                || (sessionMessages == null)
                || !folderName.equals(sessionFolderName)
                || !sortBy.equals(sessionSortBy)) {
            // if the folder doesn't exist, create it
            if (!(folderName.equals(sessionFolderName)
                    || mail.doesFolderExist(mailSession, folderName))) {
                mail.createFolder(mailSession, folderName);
            }

            // we'll have to get the messages from JavaMail
            // set the current values to the session to retrieve again later
            session.setAttribute("mailIndexFolderName", folderName);
            session.setAttribute("mailIndexFolderSortBy", sortBy);
            sessionMessages = mail.findMessagesInFolder(mailSession,
                    folderName, sortBy, sortAscending.booleanValue());

            // if it's just the direction has reversed, just do that here to
            // save another server-side call
        } else if (!sortAscending.equals(sessionSortAscending)) {
            int end = sessionMessages.size();
            int halfSize = end / 2;
            Object tmp;

            for (int start = 0; start < halfSize; ++start) {
                tmp = sessionMessages.get(--end);
                sessionMessages.set(end, sessionMessages.get(start));
                sessionMessages.set(start, tmp);
            }
        }

        // the sorting direction must be always updated for flip-flop checks
        session.setAttribute("mailIndexFolderSortAscending", sortAscending);

        // setting the list back to the session (display)
        session.setAttribute("mailIndexMessages", sessionMessages);

        if (logger.isDebugEnabled()) {
            logger.debug("getIndexMessages() - end");
        }
    }
    /**
     * Check any system exception we received to see if this means the mail
     * server has not been installed properly.
     *
     * @param systemExceptionParam System Exception we received.
     * @param request Current request we are processing.
     * @param folderName Name of the folder being displayed.
     * @return &quot;mailServerError&quot; if the server has not been installed
     * properly.
     * @throws SystemException Thrown if this exception doesn't match a server
     * installation problem.
     */
    private String handleMailIndexException(
            final SystemException systemExceptionParam,
            final HttpServletRequest request,
            final String folderName)
            throws SystemException {
        logger.warn("handleMailIndexException - Caught "
                + systemExceptionParam.getClass().getName()
                + " in IndexAction. Analysing cause.", systemExceptionParam);
        Throwable eCause = ThrowableHandling.getCause(systemExceptionParam);
        assert (eCause != null);
        logger.warn("Exception cause: "
                + eCause.getClass().getName()
                + ".", eCause);

        if (eCause instanceof NoMailServerException) {
            NoMailServerException noMailServerException =
                (NoMailServerException) eCause;

            // catch this exception - warn the user that the server is not
            // set up correctly
            logger.error("handleMailIndexException ("
                + noMailServerException.getClass().getName()
                + ") retrieving index for folder '"
                + folderName
                + "': "
                + noMailServerException.getCause().getMessage());
            noMailServerException.printStackTrace();
            request.setAttribute("mailServerException",
                noMailServerException);

            if (logger.isDebugEnabled()) {
                logger.debug("handleMailIndexException - end - return value = "
                        + "mailServerError");
            }
            return "mailServerError";
        } else if (eCause instanceof SecurityServerException) {
            SecurityServerException serverException =
                (SecurityServerException) eCause;

            // catch this exception - warn the user that the server is not
            // set up correctly
            logger.error("handleMailIndexException ("
                + serverException.getClass().getName()
                + ") retrieving index for folder '"
                + folderName
                + "': "
                + serverException.getMessage());
            serverException.printStackTrace();
            request.setAttribute("mailServerException",
                serverException.getCause());

            if (logger.isDebugEnabled()) {
                logger.debug("handleMailIndexException - end - return value = "
                        + "mailServerError");
            }
            return "mailServerError";
        }

        // if it gets here, it is not an exception type we can handle
        if (logger.isDebugEnabled()) {
            logger.debug("handleMailIndexException - throwing system exception "
                    + "again.", systemExceptionParam);
        }
        throw systemExceptionParam;
    }
    /**
     * If some of the messages are selected, this method decides what to do
     * with them.
     *
     * @param request Current request we are processing.
     * @param mailSession Current mail session for the user who is displaying
     * the page.
     * @param folderName Name of the folder being displayed.
     * @param indexForm Form returned from the index page.
     * @param selectedMessageIds List of the unique identifiers of the messages
     * with a checkbox beside them.
     * @return &quot;mailCompose&quot; if control should be passed to the
     * compose page, otherwise <code>null</code>
     * @throws SystemException If the messages cannot be deleted, forwarded
     * or moved as requested.
     */
    private String processSelectedMessages(
            final HttpServletRequest request,
            final MailSession mailSession,
            final String folderName,
            final IndexForm indexForm,
            final HashSet selectedMessageIds)
            throws SystemException {
        if (logger.isDebugEnabled()) {
            logger.debug("processSelectedMessages("
                    + "HttpServletRequest request = "
                    + request
                    + ", MailSession mailSession = "
                    + mailSession
                    + ", String folderName = "
                    + folderName
                    + ", IndexForm indexForm = "
                    + indexForm
                    + ", List selectedMessageIds = "
                    + selectedMessageIds
                    + ") - start");
        }

        HttpSession session = request.getSession();
        Integer sortBy = indexForm.getSortBy();
        List sessionMessages;

        // if we are told to delete the trash, do that
        if (indexForm.getDeleteTrash()) {
            mail.deleteMessagesFromTrash(mailSession,
                new Vector(selectedMessageIds));
            selectedMessageIds.clear();
            sessionMessages = mail.findMessagesInFolder(
                    mailSession, folderName, sortBy,
                    indexForm.getSortAscending());
            session.setAttribute("mailIndexMessages",
                sessionMessages);

            // if we are told to move selected messages to another folder,
            // do that
        } else if (!StringHandling.isNullOrEmpty(indexForm.getMoveTo())) {
            mail.moveMessages(mailSession, indexForm.getFolderName(),
                new Vector(selectedMessageIds), indexForm.getMoveTo());
            selectedMessageIds.clear();
            sessionMessages = mail.findMessagesInFolder(
                    mailSession, folderName, sortBy,
                    indexForm.getSortAscending());
            session.setAttribute("mailIndexMessages",
                sessionMessages);

            //clear the hidden field
            // javascript will set up
            indexForm.setMoveTo("");
        } else if (indexForm.getForwardMessages()) {
            // we should forward the messages to someone else
            PicoContainer picoContainer = PicoContainerFactory
                .getInstance().getGlobalContainer();
            ComposeForm composeForm = (ComposeForm)
                    picoContainer.getComponentInstance(ComposeForm.class);

            MessageDO newMessage =
                mail.createThreadMessage(
                    mailSession,
                    indexForm.getFolderName(),
                    new Vector(selectedMessageIds),
                    MailConstants.THREAD_FORWARD);
            composeForm.setMessage(newMessage);

            request.setAttribute("mailComposeForm", composeForm);

            indexForm.setForwardMessages(false);

            if (logger.isDebugEnabled()) {
                logger.debug("execute - end - return value = mailCompose");
            }
            return "mailCompose";
        } else {
            sessionMessages = (List) session.getAttribute("mailIndexMessages");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("processSelectedMessages() - end - return value = "
                    + null);
        }
        // if we're told to select all, do that
        if (indexForm.getSelectAll()) {
            for (Iterator i = sessionMessages.iterator(); i.hasNext();) {
                selectedMessageIds.add(((MessageDO) i.next()).getId());
            }

            // otherwise just select those checked on the new page
        } else {
            List selectedList =
                Arrays.asList(indexForm.getSelectedMessageIds());
            selectedMessageIds.addAll(selectedList);
        }

        return null;
    }
}

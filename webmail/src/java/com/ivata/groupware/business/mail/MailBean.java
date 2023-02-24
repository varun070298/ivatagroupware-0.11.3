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
 * $Log: MailBean.java,v $
 * Revision 1.3  2005/04/10 20:10:08  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:20:00  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:13  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.10  2004/11/12 18:16:08  colinmacleod
 * Ordered imports.
 *
 * Revision 1.9  2004/11/12 15:57:23  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.8  2004/07/13 19:48:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.7  2004/03/27 10:31:26  colinmacleod
 * Split off business logic from remote facades to POJOs.
 *
 * Revision 1.6  2004/03/21 21:16:39  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.5  2004/03/21 20:51:51  colinmacleod
 * Change SecurityServer into interface.
 * Added checking of mail server.
 *
 * Revision 1.4  2004/03/10 22:43:13  colinmacleod
 * Added security server exception handling.
 *
 * Revision 1.3  2004/02/10 19:57:26  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:07:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:59:56  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.6  2003/12/12 13:24:34  jano
 * fixing webmail functionality
 *
 * Revision 1.5  2003/11/03 11:31:06  jano
 * commiting webmail,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/28 13:27:51  jano
 * commiting webmail,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.3  2003/10/15 14:13:00  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 14:11:33  colin
 * fixing for XDoclet
 *
 * Revision 1.25  2003/07/15 06:43:40  peter
 * fixed the last fix
 *
 * Revision 1.24  2003/07/15 06:01:59  peter
 * fixed message text bugs and composed attachments size bug
 *
 * Revision 1.23  2003/07/14 15:04:22  jano
 * peter: fixed invisible attachments problem
 *
 * Revision 1.22  2003/07/14 14:52:24  jano
 * fixing bug in mailBean
 *
 * Revision 1.21  2003/07/11 06:31:06  peter
 * fixed text logic in alternative multiparts
 *
 * Revision 1.20  2003/07/07 13:43:32  peter
 * fixed getAttachment for cases with fileName
 *
 * Revision 1.19  2003/06/22 21:28:10  peter
 * re-fixed attachment handling for multipart cases
 *
 * Revision 1.18  2003/06/20 18:31:03  peter
 * added incorrectly composed mail forwards  and self contained attachment like email handling
 *
 * Revision 1.17  2003/06/19 10:06:08  jano
 * add check boxies in registration proces of customer
 *
 * Revision 1.16  2003/06/02 06:30:19  peter
 * create reply and forward message fixed
 *
 * Revision 1.15  2003/05/28 05:41:21  peter
 * added fileName as secondary attachments identifier, when contentId not present
 *
 * Revision 1.14  2003/05/27 17:15:12  peter
 * getAttachment fixed, private getAttachment methnod added
 *
 * Revision 1.13  2003/05/15 08:21:12  peter
 * fixed addMultipart logic - some multipart types weren't included
 *
 * Revision 1.12  2003/05/14 11:22:07  peter
 * fixed bug: getDOFromJavamailMessage was called after folder closed in appendAttachmnets
 *
 * Revision 1.11  2003/05/13 15:24:18  peter
 * attachment compose changes
 *
 * Revision 1.10  2003/05/12 16:31:13  peter
 * attachment compose changes
 *
 * Revision 1.9  2003/04/01 17:58:52  colin
 * removed boolean from InternetAddress constructor (marked as private in my JVM)
 *
 * Revision 1.8  2003/03/25 16:18:30  peter
 * fixed email address validation
 *
 * Revision 1.7  2003/03/25 08:23:29  jano
 * if there is no message in folder -> return null
 * and validate the email addresses
 *
 * Revision 1.6  2003/03/14 10:26:46  jano
 * adding backdoor man functionality
 * backdoor man = briezky
 *
 * Revision 1.5  2003/03/03 16:57:12  colin
 * converted localization to automatic paths
 * added labels
 * added mandatory fieldName attribute
 *
 * Revision 1.4  2003/02/28 10:23:27  peter
 * fixed handling of plain - one part messages in getDOFromJavaMailMessage
 *
 * Revision 1.3  2003/02/27 17:23:09  peter
 * Changed the return type of getAttachment to FileContentDO
 *
 * Revision 1.2  2003/02/25 11:53:33  colin
 * bugfixes and minor restructuring
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.38  2003/02/20 20:26:15  colin
 * improved validation by adding ValidationField and ValidationException
 *
 * Revision 1.37  2003/02/04 17:39:21  colin
 * copyright notice
 *
 * Revision 1.36  2003/01/15 15:43:56  colin
 * re-implemented:
 * forwarding/replying (also to multiple messages)
 * moving messages
 *
 * Revision 1.35  2002/11/20 09:21:23  peter
 * removed duplicated function contents getDOFrom... (Jbuilder bug)
 *
 * Revision 1.34  2002/11/17 20:01:24  colin
 * speed improvements in findMessagesInFolder...
 *
 * Revision 1.33  2002/11/12 09:12:38  colin
 * structural changes. currently mail bean composes and reads messages but
 * attachment & thread handling not active
 *
 * Revision 1.32  2002/10/25 08:31:44  peter
 * mailFolderSent setting name changed to emailFolderSent
 *
 * Revision 1.31  2002/10/23 12:44:37  jano
 * using new method for get System userName
 *
 * Revision 1.30  2002/10/23 09:18:59  jano
 * there is a new method for generating SystemUserName
 *
 * Revision 1.29  2002/10/18 09:18:48  colin
 * check users to make sure they are enabled before sending them mail
 *
 * Revision 1.28  2002/10/14 11:15:46  peter
 * fixed a bug in (precomposed) send method, the cc fields work now
 *
 * Revision 1.27  2002/10/11 10:05:38  jano
 * add PREFIX to user name for difren site
 *
 * Revision 1.26  2002/10/10 14:03:57  peter
 * changes due to demo version
 *
 * Revision 1.25  2002/10/01 05:59:47  peter
 * modifications in (precomposed) send method
 *
 * Revision 1.24  2002/09/17 07:26:24  peter
 * working version
 *
 * Revision 1.23  2002/09/16 16:26:40  peter
 * the attachments stuff works....
 *
 * Revision 1.22  2002/09/13 13:59:17  peter
 * appendMessages and setDO methods tuned...
 * it still doesn't work properly
 *
 * Revision 1.21  2002/09/12 15:55:25  peter
 * tuned createMessage and setDO
 *
 * Revision 1.20  2002/09/12 07:26:19  colin
 * added vacation message and user alias methods
 *
 * Revision 1.19  2002/09/11 15:57:48  peter
 * finished createMessage and setDO, debugging needed yet
 *
 * Revision 1.18  2002/09/11 11:33:12  peter
 * moveMessage works, works on createMessage and setDO
 *
 * Revision 1.17  2002/09/10 15:38:51  peter
 * MailBean: works on methods
 *
 * Revision 1.16  2002/09/10 14:18:51  peter
 * MailBean: works on methods
 *
 * Revision 1.15  2002/09/10 08:20:16  peter
 * MailBean: added moveMessage method
 *
 * Revision 1.14  2002/09/09 16:07:37  peter
 * added and modified methods in mail/MailBean
 *
 * Revision 1.13  2002/09/09 08:27:24  colin
 * changed mail bean from stateful to stateless
 * added new MailSession class
 *
 * Revision 1.12  2002/08/30 09:50:31  colin
 * changed canUser... methods to just can...
 *
 * Revision 1.11  2002/08/29 12:23:06  peter
 * mail display works...
 *
 * Revision 1.10  2002/08/27 15:26:25  peter
 * worked on getDO, should be finished
 *
 * Revision 1.9  2002/08/26 15:30:14  peter
 * MessageDO integration, not finished yet
 *
 * Revision 1.8  2002/08/26 11:15:47  peter
 * added getDo and the basic methods work
 *
 * Revision 1.7  2002/08/23 08:09:37  peter
 * design for MailBean methods, display so far
 *
 * Revision 1.6  2002/08/16 12:35:22  peter
 * fiixed a minor bug in getMessage method
 *
 * Revision 1.5  2002/08/16 11:59:00  peter
 * new mail accessing methods
 *
 * Revision 1.4  2002/08/11 11:37:50  colin
 * added routines to handle server activation and passivisation
 *
 * Revision 1.3  2002/07/26 13:08:06  colin
 * first version with mail server support
 *
 * Revision 1.2  2002/07/15 13:29:27  jano
 * added CreateException
 *
 * Revision 1.1  2002/07/15 07:51:04  colin
 * added new Mail EJB and local interface to settings
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.mail;

import java.util.Collection;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.RemoveException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.business.drive.file.FileContentDO;
import com.ivata.groupware.business.mail.message.MessageDO;
import com.ivata.groupware.business.mail.message.MessageNotFoundException;
import com.ivata.groupware.business.mail.session.MailSession;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationErrors;


/**
 * <p>This session bean provides an interface to the mail system. Every mail
 * operation for retrieving deleting and sending messages takes place in this
 * class.</p>
 *
 * @since 2002-07-12
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @author Peter Illes
 * @version $Revision: 1.3 $
 *
 * @ejb.bean
 *      name="Mail"
 *      display-name="Mail"
 *      type="Stateless"
 *      view-type="both"
 *      local-jndi-name = "MailLocal"
 *      jndi-name="MailRemote"
 *
 * @ejb.transaction
 *      type = "Required"
 *
 *  @ejb.home
 *      generate="false"
 *      remote-class="com.ivata.groupware.business.mail.MailRemoteHome"
 *
 *  @ejb.interface
 *      remote-class="com.ivata.groupware.business.mail.MailRemote"
 */
public class MailBean implements SessionBean {
    /**
     * <p>Provides the session bean with container-specific information.</p>
     */
    SessionContext sessionContext;

    /**
     * <p>Add a composed message to the drafts folder for later sending.</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param messageDO data object containing full details of the
     *     message to be added to the drafts.
     * @return new <code>MessageDO</code> with the <code>id</code> set to the
     *     current value in the mail system.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public MessageDO addMessageToDraftsFolder(final MailSession mailSession,
            final MessageDO messageDO) throws SystemException {
        return getMail().addMessageToDraftsFolder(mailSession, messageDO);
    }

    /**
     * <p>Append attachments to a message located in the drafts folder.</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param id the unique identifier of the message to which we want to append
     *     attachments.
     * @param attachments <code>List</code> of <code>String</code>s -
     *     filenames of files waiting in upload directory.
     * @return <code>null</code> when the operation failed, otherwise the new
     *     message id.
     * @exception MessageNotFoundException if the folder doesn't exist, or there
     *     is no matching mail in this folder.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public MessageDO appendAttachments(final MailSession mailSession,
            final String id,
            final List attachments) throws SystemException {
        return getMail().appendAttachments(mailSession, id, attachments);
    }

    /**
     * <p>Create a new mail folder.</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param folderName the full path name of the folder to create.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public void createFolder(final MailSession mailSession,
            final String folderName)
            throws SystemException{
        getMail().createFolder(mailSession, folderName);
    }

    /**
     * <p>Create a new message in the drafts folder from an existing one,
     * based on a 'thread'. The thread indicates that the message is a:<br/>
     * <ul>
     *   <li>reply to all recipients of a previous message</li>
     *   <li>reply to one recipient of a previous message</li>
     *   <li>previous message(s) forwarded to new recipients</li>
     *   <li>an existing (draft) message being altered for resending</li>
     * </ul></p>
     *
     * <p>This new message in the drafts folder can then be used to store
     * attachments or for reviewing before sending.</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param folderName the name of the folder to copy existing messages from.
     * @param messageIds the unique identifier of the messages to be extended.
     *     Can be <code>null</code> if a new message is requeested. When
     *     forwarding, multiple address identifiers may be specified otherwise
     *     (if editing a draft message or replying) only one message identifier
     *     should be set in the list.
     * @param thread set to one of the constants in {@link MailConstants
     *     MailConstants}.
     * @return populated message data object matching the required
     *     message, and with the <code>id</code> set to the message in the
     *     drafts folder.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public MessageDO createThreadMessage(final MailSession mailSession,
            final String folderName,
            final List messageIds,
            final Integer thread)
            throws SystemException{
        return getMail().createThreadMessage(mailSession, folderName,
            messageIds, thread);
    }

    /**
     * <p>Delete a list of messages from the trash folder.</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param ids the unique identifiers (<code>String</code> instances) of the
     *     messages to be removed.
     * @exception MessageNotFoundException if the folder doesn't exist, or there
     *     is no matching mail in this folder.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public void deleteMessagesFromTrash(final MailSession mailSession,
            final List ids)
            throws SystemException {
        getMail().deleteMessagesFromTrash(mailSession, ids);
    }

    /**
     * <p>Check whether or not a given folder pathname exists.</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param folderName the full path name of the folder to check.
     * @return <code>true</code> if the folder exists, otherwise <code>false</code>.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public boolean doesFolderExist(final MailSession mailSession,
            final String folderName)
            throws SystemException {
        return getMail().doesFolderExist(mailSession, folderName);
    }

    /**
     * <p>Called by the container to notify an entity object it has been
     * activated.</p>
     */
    public void ejbActivate() {
    }

    /**
     * <p>Called by the container just after the bean has been created.</p>
     *
     * @throws CreateException if any error occurs. Never thrown by this class.
     *
     * @ejb.create-method
     */
    public void ejbCreate() throws CreateException {
    }

    /**
     * <p>Called by the container to notify the entity object it will be
     * deactivated. Called just before deactivation.</p>
     */
    public void ejbPassivate() {
    }

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
    public void ejbRemove() {
    }

    /**
     * <p>This method retrieves the requested message and sets all the
     * attributes of a MessageDO object for use on client side.</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param folderName the name of the folder the message is located in.
     * @param id the unique identifier of the message.
     * @return a MessageDO instance filled up with the messages attributes,
     *     except the contents of the attachments.
     * @exception MessageNotFoundException if the folder doesn't exist, or there
     *     is no matching mail in this folder.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public MessageDO findMessageByFolderMessageId(final MailSession mailSession,
            final String folderName,
            final String id)
            throws SystemException {
        return getMail().findMessageByFolderMessageId(mailSession, folderName,
            id);
    }

    /**
     * <p>Used in the main folder index page, this method returns the contents
     * of a folder as a <code>List</code> of
     * <code>MessageDO</code> instances.</p>
     *
     * <p><strong>Note:</strong> for efficiency reasons, this method does not fill the
     * text, format or attachment values of the returned <code>MessageDO</code>
     * instance. For that, you must call
     * {@link #findMessageByFolderMessageId findMessageByFolderMessageId}.</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param folderName the name of the folder to list.
     * @param sortBy the field to sort the returned list by. Set to one of the
     *     <code>SORT_...</code> constants in {@link MailConstants MailConstants}.
     * @param sortAscending if <code>true</code> then the messages are sorted in
     *     ascending order, otherwise (<code>false</code>) they are descending.
     * @return <code>List</code> of
     *     <code>MessageDO</code> instances.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public List findMessagesInFolder(final MailSession mailSession,
            final String folderName,
            final Integer sortBy,
            final boolean sortAscending)
            throws SystemException {
        return getMail().findMessagesInFolder(mailSession, folderName, sortBy,
            sortAscending);
    }

    /**
     * <p>Retrieve an attachment's content and it's MimeType. This method is
     * used to by the download servlet.</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param folderName the name of the folder the message is located in.
     * @param messageId the unique identifier of the message.
     * @param attachmentId the unique identifier of the attachment.
     * @return attachment data object containing attachment content
     *     and mime type.
     * @exception MessageNotFoundException if the folder doesn't exist, or there
     *     is no matching mail in this folder.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public final FileContentDO getAttachment(final MailSession mailSession,
            final String folderName,
            final String messageId,
            final String attachmentId)
            throws SystemException {
        return getMail().getAttachment(mailSession, folderName, messageId,
            attachmentId);
    }

    /**
     * <p>Get the time the specified mail folder was last modified as a
     * <code>long</code>. This can then be saved and compared to subsequent
     * calls of this method to see if the folder has changed.</p>
     *
     * @param userName the name of the user for whom to locate the folder.
     * @param folderName the name of the folder to locate.
     * @return operating system specific timestamp indicating when the
     * folder was last changed.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public final long getFolderModified(final String userName,
            final String folderName)
            throws SystemException {
        // TODO: return getMail().getFolderModified(userName, folderName);
        return 0;
    }

    /**
     * Get the mail implementation from the <code>PicoContainer</code>.
     *
     * @return valid mail implementation POJO.
     */
    private Mail getMail() throws SystemException {
        PicoContainer container = PicoContainerFactory.getInstance()
            .getGlobalContainer();

        return (Mail) container.getComponentInstance(Mail.class);
    }

    /**
     * <p>Retrieve all of the email aliases for the user provided, on the curent
     * email server. The aliases returned each one containing just the 'user' (or
     * 'local') part of the email address, before the '@' sign.</p>
     *
     * @param userName the name of the user for whom to retrieve the email aliases.
     * @return a <code>Collection</code> of <code>String</code> instances containing
     *     the local part of the different email aliases this user has. If the user
     *     has no aliaes, an empty collection is returned.
     * @throws MandatoryFieldException if the user name is <code>null</code>.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public final Collection getUserAliases(final SecuritySession securitySession,
            final String userName)
            throws SystemException {
        return getMail().getUserAliases(securitySession, userName);
    }

    /**
     * <p>Get the vacation message for the user provided.</p>
     *
     * <p>The vacation message is a text the user can set, which will be sent
     * to all emails received at this address while the user is not present.</p>
     *
     * @param userName the name of the user for whom to get the vacation message.
     * @return the vacation message text for this user. If the user has no
     *     vacation message set, <code>null</code> is returned.
     * @throws MandatoryFieldException if the user name is <code>null</code>.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public final String getVacationMessage(final SecuritySession securitySession,
            final String userName)
            throws SystemException {
        return getMail().getVacationMessage(securitySession, userName);
    }

    /**
     * <p>Login to the mail system. This method should be called before any other,
     * to establish the mail session and store.</p>
     *
     * @param userName this user name is used to log into the remote system.
     * @param password the clear-text password to log into the remote system.
     * @throws EJBException if the person cannot log in.
     * @return the mail session (class <code>javax.mail.Session</code>) in a
     *    <code>SessionSerializer</code>.
     *
     * @ejb.interface-method
     *      view-type = "local"
     */
    public MailSession login(final UserDO user,
            final String password)
            throws SystemException {
        return getMail().login(user, password);
    }

    /**
     * <p>Move a list of messages from one folder to another.</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param folderName the name of the folder the messages are currently located in.
     * @param ids the unique identifiers (<code>String</code> instances) of the
     *     messages to be moved.
     * @param targetFolderName the name of the the folder the message will be moved
     *     to.
     * @exception MessageNotFoundException if the folder doesn't exist, or there
     *     is no matching mail in this folder.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public void moveMessages(final MailSession mailSession,
            final String folderName,
            final List ids,
            final String targetFolderName) throws SystemException {
        getMail().moveMessages(mailSession, folderName, ids, targetFolderName);
    }

    /**
     * <p>Send a mime email message that is already composed. If <code>id</code>
     * has been set in <code>messageDO</code> it is assumed to point to a
     * message in the drafts folder. Attachments are copied from the message
     * who match the contents of <code>getAttachmentIds</code>. (All other
     * attachments are discarded.)</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param messageDO data object containing full details of the
     *     message to be sent.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public void send(final MailSession mailSession,
            final MessageDO messageDO)
            throws SystemException {
        getMail().send(mailSession, messageDO);
    }

    /**
     * <p>Send an mime email message without using a data object.</p>
     *
     * @param mailSession valid mail session to which the user should already be
     *     logged in.
     * @param fromAddress the address of the person sending the mail. This must
     *    be formatted according to <a
     *    href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     * @param to recipients, a <code>Collection</code> containing instances of
     *    <code>String</code> or <code>UserLocal</code> or
     *    <code>PersonLocal</code>. A mixture of these types is allowed. If the
     *    type of an instance is <code>String</code>, then it must be formatted
     *    according to <a href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     *    Otherwise, if the type is <code>PersonLocal</code>, the method
     *    <code>getEmailAddress</code> must return a valid address string for
     *    this person.
     * @param cc cc recipients. For format, see <code>to</code> parameter.
     * @param bcc bcc recipients. For format, see <code>to</code> parameter.
     * @param subject clear-text email subject field.
     * @param content any valid email content type, as defined by
     *    <code>javax.mail.internet.MimeMessage</code>.
     * @param contentType mime type for the <code>content</code> field, as
     *    defined by <code>javax.mail.internet.MimeMessage</code>.
     * @param addToSentFolder if set to <code>true</code> then the mail is added
     *    to the sent folder of the current email session.
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public void send(final MailSession mailSession,
            final String fromAddress,
            final Collection to,
            final Collection cc,
            final Collection bcc,
            final String subject,
            Object content, String contentType, boolean addToSentFolder)
            throws SystemException {
        getMail().send(mailSession, fromAddress, to, cc, bcc, subject, content,
            contentType, addToSentFolder);
    }

    /**
     * <p>Set up the context for this entity object. The session bean stores the
     * context for later use.</p>
     *
     * @param sessionContext the new context which the session object should store.
     */
    public final void setSessionContext(final SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     * <p>Set all of the email aliases for the user provided, on the curent
     * email server. Each alias in the collection should contain just the 'user'
     * (or 'local') part of the email address, before the '@' sign.</p>
     *
     * @param userName the name of the user for whom to retrieve the email aliases.
     * @param userAliases a <code>Collection</code> of <code>String</code>
     *     instances containing the local part of the different email aliases
     *     this user has. If the user has no aliaes, an empty collection should
     *     be provided.
     * @throws MandatoryFieldException if either input is <code>null</code>.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public final void setUserAliases(final SecuritySession securitySession,
            final String userName,
            final Collection userAliases)
            throws SystemException {
        getMail().setUserAliases(securitySession, userName, userAliases);
    }

    /**
     * <p>Set the vacation message for the user provided.</p>
     *
     * <p>The vacation message is a text the user can set, which will be sent
     * to all emails received at this address while the user is not present.</p>
     *
     * @param userName the name of the user for whom to get the vacation message.
     * @param vacationMessage vacation message text for this user. If the user
     *     has no vacation message set, set to <code>null</code>.
     * @throws MandatoryFieldException if the user name is <code>null</code>.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public final void setVacationMessage(final SecuritySession securitySession,
            final String userName,
            final String vacationMessage)
            throws SystemException {
        getMail().setVacationMessage(securitySession, userName, vacationMessage);
    }

    /**
     * <p>Confirm all of the elements of the message are present and valid,
     * before the message is sent.</p>
     *
     * @param messageDO data object to check for consistency and
     *     completeness.
     * @return a collection of validation errors if any of the
     *     mandatory fields are missing, or if fields contain invalid values.
     *
     * @ejb.interface-method
     *      view-type = "remote"
     */
    public ValidationErrors validate(final SecuritySession securitySession,
            final MessageDO messageDO)
            throws SystemException {
        return getMail().validate(securitySession, messageDO);
    }
}

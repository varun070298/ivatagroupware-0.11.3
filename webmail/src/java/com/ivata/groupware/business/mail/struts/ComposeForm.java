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
 * $Log: ComposeForm.java,v $
 * Revision 1.3  2005/04/10 18:47:43  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:20:01  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:19  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
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
 * Revision 1.4  2004/07/13 19:48:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:39  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:07:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:59:59  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 14:11:33  colin
 * fixing for XDoclet
 *
 * Revision 1.5  2003/05/14 09:44:53  peter
 * removing unwanted attachments
 *
 * Revision 1.4  2003/05/12 16:32:02  peter
 * attachment compose changes
 *
 * Revision 1.3  2003/03/05 18:10:12  colin
 * now gets mail from MailAction (rather than session directly)
 *
 * Revision 1.2  2003/02/25 11:53:33  colin
 * bugfixes and minor restructuring
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.4  2003/02/20 20:26:32  colin
 * improved validation by adding ValidationField and ValidationException
 *
 * Revision 1.3  2003/02/04 17:39:10  colin
 * updated for new MaskAction interface
 *
 * Revision 1.2  2002/11/17 20:04:36  colin
 * changed folder to folderName
 *
 * Revision 1.1  2002/11/12 11:46:32  colin
 * first version in CVS. applied Struts to mail subsystem.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.mail.struts;

import java.util.Iterator;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.drive.file.FileDO;
import com.ivata.groupware.business.mail.Mail;
import com.ivata.groupware.business.mail.message.MessageDO;
import com.ivata.mask.Mask;
import com.ivata.mask.util.CollectionHandling;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationErrors;
import com.ivata.mask.web.struts.DialogForm;


/**
 * <p>Main form used to input/display data in
 * <code>/mail/compose.jsp</code>.</p>
 *
 * @since 2002-11-09
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class ComposeForm extends DialogForm {
    /**
     * <p>Set when attachments were uploaded to the temporary upload
     * directory, contains the fileNames separated by
     * <code>File.SeparatorChar</code>.</p>
     */
    private String attach;
    /**
     * <p>
     * Defines the base class of all objects in the value object list.
     * </p>
     */
    private Class baseClass;
    /**
     * Refer to {@link Logger}.
     */
    private Logger log = Logger.getLogger(ComposeForm.class);
    Mail mail;
    /**
     * <p>
     * Mask containing all the field definitions for this list.
     * </p>
     */
    private Mask mask;

    /**
     * <p>Message data object which contains all of the
     * information in this form.</p>
     */
    private MessageDO message = new MessageDO();
    /**
     * Stores any exception encountered when sending the mail.
     */
    private MessagingException messagingException = null;
    /**
     * @param maskParam Refer to {@link DialogForm#DialogForm}.
     * @param baseClassParam Refer to {@link DialogForm#DialogForm}.
     */
    public ComposeForm(final Mail mail) {
        this.mail = mail;
    }

    /**
     * <p>
     * Return all form state to initial values.
     * </p>
     *
     * @see com.ivata.mask.web.struts.MaskForm#clear()
     */
    protected void clear() {
        attach = null;
    }

    /**
     * <p>Set when attachments were uploaded to the temporary upload
     * directory, contains the fileNames separated by
     * <code>File.SeparatorChar</code>.</p>
     *
     * @return the current value of attach.
     */
    public final String getAttach() {
        return attach;
    }

    /**
     * <p>This map contains names of files to remove from the attachment
     * list</p>
     * @param attachmentId the fileName or attachmentId of the attachment
     * to remove
     * @return the remove status for the specified attachment.
     */
    public final boolean getAttachmentRemove(final String attachmentId) {
        // always return false, a file in the list wants to stay there...
        return false;
    }

    /**
     * <p>
     * Defines the base class of all objects in the value object list.
     * </p>
     *
     * @return base class of all objects in the value object list.
     */
    public final Class getBaseClass() {
        return baseClass;
    }

    /**
     * <p>
     * Mask containing all the field definitions for this list.
     * </p>
     *
     * @return mask containing all the field definitions for this list.
     */
    public final Mask getMask() {
        return mask;
    }

    /**
     * <p>Get the message data object which contains all of the
     * information in this form.</p>
     *
     * @return message data object which contains all of the
     * information in this form.
     */
    public MessageDO getMessage() {
        return message;
    }

    /**
     * @return Returns the messagingException.
     */
    public MessagingException getMessagingException() {
        return messagingException;
    }

    /**
     * <p>Get recipients of the message.</p>
     *
     * @return recipients of the message, as a <code>String</code>
     * containing all of the instances separated by semicolons (;),  each
     * one formatted according to <a
     * href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     */
    public final String getRecipients() {
        return CollectionHandling.convertToLines(message.getRecipients(), ';');
    }

    /**
     * <p>Get "Blind carbon copy" recipients as List of strings. These are
     * additional recipients whose identity is <em>not</em> made known to
     * any
     * other recipients.</p>
     *
     * @return recipients of the message, as a <code>String</code>
     * containing all of the instances separated by semicolons (;),  each
     * one formatted according to <a
     * href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     *
     */
    public final String getRecipientsBCC() {
        return CollectionHandling.convertToLines(message.getRecipientsBCC(), ';');
    }

    /**
     * <p>Get "Carbon copy" recipients of the message. These are
     * additional
     * recipients whose identity is made known to all other
     * recipients.</p>
     *
     * @return recipients of the message, as a <code>String</code>
     * containing all of the instances separated by semicolons (;),  each
     * one formatted according to <a
     * href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     *
     */
    public final String getRecipientsCC() {
        return CollectionHandling.convertToLines(message.getRecipientsCC(), ';');
    }

    /**
     * <p>Get senders of the message.</p>
     *
     * @return senders of the message, as a <code>String</code>
     * containing all of the instances separated by semicolons (;),  each
     * one formatted according to <a
     * href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     */
    public final String getSenders() {
        return CollectionHandling.convertToLines(message.getSenders(), ';');
    }

    /**
     * <p>Reset all bean properties to their default state.  This method
     * is called before the properties are repopulated by the controller
     * servlet.<p>
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(final ActionMapping mapping,
            final HttpServletRequest request) {
        // ok, it's not very memory-efficient, but it's easier to maintain :-)
        super.reset(mapping, request);
        attach = null;
    }

    /**
     * <p>Set when attachments were uploaded to the temporary upload
     * directory, contains the fileNames separated by
     * <code>File.SeparatorChar</code>.</p>
     *
     * @param attach the new value of attach.
     */
    public final void setAttach(final String attach) {
        this.attach = attach;
    }

    /**
     * <p>This map contains names of attachments to remove from the message</p>
     *
     * @param attachmentId the attachment to remove from the message
     * @param remove when <code>true</code>, the attachment should be removed.
     */
    public final void setAttachmentRemove(final String attachmentId,
            final boolean remove) {
        // if there's an attachment marked to remove, remove the corresponding
        // attachment from the list
        if (remove && (message.getAttachments() != null)) {
            int attachmentIdHashCode = Integer.parseInt(attachmentId);

            for (Iterator i = message.getAttachments().iterator(); i.hasNext();) {
                FileDO currentDO = (FileDO) i.next();
                String currentId = currentDO.getName();

                if (currentId.hashCode() == attachmentIdHashCode) {
                    message.getAttachments().remove(currentDO);

                    break;
                }
            }
        }
    }

    /**
     * <p>Set the message data object which contains all of the
     * information in this form. The contents of the form are entirely
     * replaced by the contents of the messge provided.</p>
     *
     * @param message message data object which contains all of
     * the new information in this form.
     */
    public final void setMessage(final MessageDO message) {
        this.message = message;
    }
    /**
     * @param messagingExceptionParam The messagingException to set.
     */
    public void setMessagingException(MessagingException messagingExceptionParam) {
        if (log.isDebugEnabled()) {
            log.debug("setMessagingException before: '" + messagingException
                    + "', after: '" + messagingExceptionParam + "'");
        }

        messagingException = messagingExceptionParam;
    }

    /**
     * <p>Set recipients of the message.</p>
     *
     * @param recipients recipients of the message, as a
     * <code>String</code> containing all of the instances separated by
     * semicolons (;),  each one formatted according to <a
     * href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     */
    public final void setRecipients(final String recipients) {
        message.setRecipients(CollectionHandling.convertFromLines(recipients,
                ";"));
    }

    /**
     * <p>Set "Blind carbon copy" recipients as List of strings. These are
     * additional recipients whose identity is <em>not</em> made known to
     * any
     * other recipients.</p>
     *
     * @param recipientsBCC recipients of the message, as a
     * <code>String</code> containing all of the instances separated by
     * semicolons (;),  each one formatted according to <a
     * href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     */
    public final void setRecipientsBCC(final String recipientsBCC) {
        message.setRecipientsBCC(CollectionHandling.convertFromLines(
                recipientsBCC, ";"));
    }

    /**
     * <p>Set "Carbon copy" recipients of the message. These are
     * additional
     * recipients whose identity is made known to all other
     * recipients.</p>
     *
     * @param recipientsCC recipients of the message, as a
     * <code>String</code> containing all of the instances separated by
     * semicolons (;),  each one formatted according to <a
     * href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     */
    public final void setRecipientsCC(final String recipientsCC) {
        message.setRecipientsCC(CollectionHandling.convertFromLines(
                recipientsCC, ";"));
    }

    /**
     * <p>Set senders of the message.</p>
     *
     * @param senders senders of the message, as a <code>String</code>
     * containing all of the instances separated by semicolons (;),  each
     * one formatted according to <a
     * href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     */
    public final void setSenders(final String senders) {
        message.setSenders(CollectionHandling.convertFromLines(senders, ";"));
    }

    /**
     * <p>Call the corresponding server-side validation, handle possible
     * exceptions and return any errors generated.</p>
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     * @return <code>ActionMessages</code> collection containing all
     * validation errors, or <code>null</code> if there were no errors.
     */
    public ValidationErrors validate(final HttpServletRequest request,
            final HttpSession session) {
        // only validate if ok was pressed
        if (StringHandling.isNullOrEmpty(getOk()) &&
                StringHandling.isNullOrEmpty(getApply())) {
            return null;
        }

        if (mail == null) {
            throw new NullPointerException(
                "ERROR in ComposeForm: mail remote instance is null.");
        }

        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");
        ValidationErrors validationErrors;
        try {
            validationErrors = mail.validate(securitySession,
                message);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
        return validationErrors;
    }
}

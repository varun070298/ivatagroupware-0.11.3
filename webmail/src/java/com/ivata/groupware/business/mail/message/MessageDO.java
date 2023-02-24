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
 * $Log: MessageDO.java,v $
 * Revision 1.3  2005/04/10 18:47:42  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:20:01  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:16  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.6  2004/09/30 15:09:33  colinmacleod
 * Bug fixes
 *
 * Revision 1.5  2004/07/13 19:48:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:39  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/10 19:57:26  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:07:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:59:57  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 14:11:33  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/05/12 16:31:52  peter
 * the attachments are a Vector of FileDOs now
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.mail.message;

import java.util.GregorianCalendar;
import java.util.List;

import com.ivata.groupware.business.mail.MailBean;
import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>This is a dependent value class, used to pass data back from
 * the</p>
 * {@link com.ivata.groupware.business.mail.MailBean MailBean} to a
 * client application.</p>
 *
 * @since 2002-08-26
 * @author Peter Illes
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 * @see MailBean
 */
public class MessageDO extends BaseDO {

    /**
     * <p><code>List</code> of attachments as <code>FileDO</code>s. These can be used
     * to identify the attachment for later downloading.</p>
     */
    private List attachments;
    /**
     * <p>The mail folder where this message is located.</p>
     */
    private String folderName;

    /**
     * <p>The format of the text, must be set to one of the formats in
     * {@link
     * com.ivata.mask.web.format.FormatConstants
     * FormatConstants}.</p>
     */
    private int format;

    /**
     * <p>
     * This is the <strong>JavaMail</strong> message id.
     * </p>
     */
    private String messageID;

    /**
     * <p>The date the message was received.</p>
     */
    private GregorianCalendar received;

    /**
     * <p>Recipients of the message as a <code>List</code> of strings.</p>
     */
    private List recipients;

    /**
     * <p>"Blind carbon copy" recipients as List of strings. These are
     * additional
     * recipients whose identity is <em>not</em> made known to all other
     * recipients.</p>
     */
    private List recipientsBCC;

    /**
     * <p>"Carbon copy" recipients as List of strings. These are
     * additional
     * recipients whose identity is made known to all other
     * recipients.</p>
     */
    private List recipientsCC;

    /**
     * <p>Senders of the message as a <code>List</code> of strings.</p>
     */
    private List senders;

    /**
     * <p>The date the message was sent.</p>
     */
    private GregorianCalendar sent;

    /**
     * <p>The size of the message, including attachments, in bytes.</p>
     */
    private Integer size;

    /**
     * <p>The subject of the message.</p>
     */
    private String subject;

    /**
     * <p>The textual content of the message. This can either be
     * <code>HTML</code> formatted or plain-text, depending on the value
     * of the
     * <code>format</code> field.</p>
     */
    private String text;

    /**
     * <p>Get all of the identifiers for message attachments. This method
     * retrieves the ids, content types, sizes so they can be used to identify and download the
     * message attachments.</p>
     *
     * @return <p><code>Vector</code> of <code>FileDO</code>s. These can be used to identify the
     * attachment
     * for later downloading.</p>
     *
     */
    public List getAttachments() {
        return attachments;
    }

    /**
     * <p>Get the mail folder where this message is located.</p>
     *
     * @return name of the mail folder where this message is located.
     *
     */
    public final String getFolderName() {
        return folderName;
    }

    /**
     * <p>Get the format of the text.</p>
     *
     * @return one of the formats in
     * {@link com.ivata.mask.web.format.FormatConstants
     * FormatConstants}.</p>
     *
     */
    public final int getFormat() {
        return format;
    }
    /**
     * <p>
     * This is the <strong>JavaMail</strong> message id.
     * </p>
     *
     * @return Returns the messageID.
     */
    public final String getMessageID() {
        return messageID;
    }

    /**
     * <p>Get the time the message was received.</p>
     *
     * @return time the message was received, or <code>null</code> if this
     * has not
     * been set for this message.
     *
     */
    public final GregorianCalendar getReceived() {
        return this.received;
    }

    /**
     * <p>Get recipients of the message.</p>
     *
     * @return recipients of the message, as a <code>List</code>
     * of <code>String</code> instances, each one formatted according to
     * <a href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     */
    public List getRecipients() {
        return recipients;
    }

    /**
     * <p>Get "Blind carbon copy" recipients as List of strings. These are
     * additional recipients whose identity is <em>not</em> made known to
     * any
     * other recipients.</p>
     *
     * @return recipients of the message, as a <code>List</code>
     * of <code>String</code> instances, each one formatted according to
     * <a href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     *
     */
    public List getRecipientsBCC() {
        return recipientsBCC;
    }

    /**
     * <p>Get "Carbon copy" recipients of the message. These are
     * additional
     * recipients whose identity is made known to all other
     * recipients.</p>
     *
     * @return recipients of the message, as a <code>List</code>
     * of <code>String</code> instances, each one formatted according to
     * <a href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     */
    public List getRecipientsCC() {
        return recipientsCC;
    }

    /**
     * <p>Get senders of the message.</p>
     *
     * @return senders of the message, as a <code>List</code>
     * of <code>String</code> instances, each one formatted according to
     * <a href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     *
     */
    public List getSenders() {
        return senders;
    }

    /**
     * <p>Get the time the message was sent.</p>
     *
     * @return time the message was sent, or <code>null</code> if this has
     * not
     * been set for this message.
     *
     */
    public final GregorianCalendar getSent() {
        return sent;
    }

    /**
     * <p>Get the size of the message, including attachments.</p>
     *
     * @return total size of the message and attachments, in bytes.
     *
     */
    public final Integer getSize() {
        return size;
    }

    /**
     * <p>Get the subject of this message.</p>
     *
     * @return the subject of the mail, in plain text without
     * <code>HTML</code> code or character entities.
     *
     */
    public final String getSubject() {
        return subject;
    }

    /**
     * <p>Get the textual content of the message.</p>
     *
     * @return either be <code>HTML</code> formatted or plain-text,
     * depending on
     * the value of the <code>format</code> field.
     * @see #setFormat
     *
     */
    public final String getText() {
        return text;
    }

    /**
     * <p>Set all of the identifiers for message attachments. This method
     * will only every be called server-side to set up the atachment list  the client can later locate the correct attachments.</p>
     *
     * @param attachments <code>List</code> of <code>FileDO</code>s.
     */
    public final void setAttachments(final List attachments) {
        this.attachments = attachments;
    }

    /**
     * <p>Set the mail folder where this message is located. <strong>Note:</strong>
     * this
     * method is not enough to actually <em>move</em> the folder. The
     * <code>messageDO</code> merely records information about the
     * message.</p>
     *
     * @param folderName new value of folder where this message is located.
     *
     */
    public final void setFolderName(final String folderName) {
        this.folderName = folderName;
    }

    /**
     * <p>Set the format of the text.</p>
     *
     * @param format one of the formats in
     * {@link com.ivata.mask.web.format.FormatConstants
     * FormatConstants}.</p>
     *
     */
    public final void setFormat(final int format) {
        this.format = format;
    }
    /**
     * <p>
     * This is the <strong>JavaMail</strong> message id.
     * </p>
     *
     * @param messageID The messageID to set.
     * @hibernate.property
     */
    public final void setMessageID(final String messageID) {
        this.messageID = messageID;
    }

    /**
     * <p>Set the time the message was received.</p>
     *
     * @param received valid date and time the message was received.
     *
     */
    public final void setReceived(final GregorianCalendar received) {
        this.received = received;
    }

    /**
     * <p>Set recipients of the message.</p>
     *
     * @param recipients recipients of the message, as a <code>List</code>
     * of <code>String</code> instances, each one formatted according to
     * <a href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     *
     */
    public final void setRecipients(final List recipients) {
        this.recipients = recipients;
    }

    /**
     * <p>Set "Blind carbon copy" recipients as List of strings. These are
     * additional recipients whose identity is <em>not</em> made known to
     * any
     * other recipients.</p>
     *
     * @param recipientsBCC recipients of the message, as a
     * <code>List</code>
     * of <code>String</code> instances, each one formatted according to
     * <a href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     *
     */
    public final void setRecipientsBCC(final List recipientsBCC) {
        this.recipientsBCC = recipientsBCC;
    }

    /**
     * <p>Set "Carbon copy" recipients of the message. These are
     * additional
     * recipients whose identity is made known to all other
     * recipients.</p>
     *
     * @param recipientsCC recipients of the message, as a
     * <code>List</code>
     * of <code>String</code> instances, each one formatted according to
     * <a href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     *
     */
    public final void setRecipientsCC(final List recipientsCC) {
        this.recipientsCC = recipientsCC;
    }

    /**
     * <p>Set senders of the message.</p>
     *
     * @param senders senders of the message, as a <code>List</code>
     * of <code>String</code> instances, each one formatted according to
     * <a href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.
     *
     */
    public final void setSenders(final List senders) {
        this.senders = senders;
    }

    /**
     * <p>Set the time the message was sent.</p>
     *
     * @param sent valid date and time the message was sent.
     *
     */
    public final void setSent(final GregorianCalendar sent) {
        this.sent = sent;
    }

    /**
     * <p>Set the size of the message, including attachments.</p>
     *
     * @param size total size of the message and attachments, in bytes.
     *
     */
    public final void setSize(final Integer size) {
        this.size = size;
    }

    /**
     * <p>Set the subject of this message.</p>
     *
     * @param subject the subject of the mail, in plain text without
     * <code>HTML</code> code or character entities.
     *
     */
    public final void setSubject(final String subject) {
        this.subject = subject;
    }

    /**
     * <p>Set the textual content of the message.</p>
     *
     * @param text this can either be <code>HTML</code> formatted or
     * plain-text,
     * depending on the value of the <code>format</code> field.
     * @see #setFormat
     *
     */
    public final void setText(final String text) {
        this.text = text;
    }
}

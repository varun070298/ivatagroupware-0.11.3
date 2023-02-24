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
 * $Log: DisplayForm.java,v $
 * Revision 1.3  2005/04/10 18:47:43  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:20:01  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:17  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.6  2004/11/12 18:19:16  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.5  2004/09/30 15:09:34  colinmacleod
 * Bug fixes
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
 * Revision 1.2  2003/02/25 11:53:33  colin
 * bugfixes and minor restructuring
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.2  2003/02/04 17:39:10  colin
 * updated for new MaskAction interface
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ivata.groupware.business.mail.MailConstants;
import com.ivata.groupware.business.mail.message.MessageDO;
import com.ivata.mask.Mask;
import com.ivata.mask.validation.ValidationErrors;
import com.ivata.mask.web.struts.DialogForm;


/**
 * <p>This form is used to identify an existing message to display, or
 * from which we want to create a new
 * one. Depending on the value of the <code>thread</code> attribute,
 * the newmessage can:<br/>
 * <ul>
 * <li>Reply to the main recipient of the previous message</li>
 * <li>Reply to all visible (i.e. not <em>BCC</em>) recipients of the
 * previous message</li>
 * <li>Forward existing message, or messages</li>
 * <li>Edit an existing message as a temple for a new message.</li>
 * </ul></p>
 *
 * @since 2002-11-11
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class DisplayForm extends DialogForm {

    /**
     * <p>Indicates this message should be moved to the trash folder.
     * After the message has been moved, the next message in the folder
     * will
     * be displayed or (if there are no more messages) the folder
     * index.</p>
     */
    private boolean delete;

    /**
     * <p>Indicates this message should be permanently erased from the
     * trash folder. After the message has been deleted, the next message
     * in
     * the trash folder will be displayed or (if there are no more
     * messages)
     * the trash folder index.</p>
     */
    private boolean deleteTrash;

    /**
     * <p>Used to store the result from a submit button, if this is
     * non-<code>null</code>, then the next message in the current folder
     * will be displayed.</p>
     */
    private String displayNext;

    /**
     * <p>Used to store the result from a submit button, if this is
     * non-<code>null</code>, then the previous message in the current
     * folder will be displayed.</p>
     */
    private String displayPrevious;

    /**
     * <p>Set by the action to indicate whether there are more messages in
     * the current folder after this one.</p>
     */
    private boolean hasNext;

    /**
     * <p>Set by the action to indicate whether there are more messages in
     * the current folder before this one.</p>
     */
    private boolean hasPrevious;

    /**
     * <p>Stores the data of the currently displayed message.</p>
     */
    private MessageDO message = new MessageDO();
    private boolean sortAscending;
    private Integer sortBy;
    /**
     * <p>Set to one of the constants in {@link
     * com.ivata.groupware.business.mail.MailConstants MailConstants}, this
     * indicator
     * tells us that the message is a:<br/>
     * <ul>
     * <li>reply to all recipients of a previous message</li>
     * <li>reply to one recipient of a previous message</li>
     * <li>previous message(s) forwarded to new recipients</li>
     * <li>an existing (draft) message being altered for resending</li>
     * </ul></p>
     */
    private Integer thread;

    /**
     * <p>
     * Defines the base class of all objects in the value object list.
     * </p>
     */
    private Class baseClass;

    /**
     * <p>
     * Mask containing all the field definitions for this list.
     * </p>
     */
    private Mask mask;
    /**
     * <p>
     * Return all form state to initial values.
     * </p>
     *
     * @see com.ivata.mask.web.struts.MaskForm#clear()
     */
    protected void clear() {
        delete = false;
        deleteTrash = false;
        displayNext = null;
        displayPrevious = null;
        hasNext = false;
        hasPrevious = false;
        message = new MessageDO();
        sortAscending = true;
        sortBy = MailConstants.SORT_SENT;
        thread = null;
    }

    /**
     * <p>Indicates this message should be moved to the trash folder.
     * After the message has been moved, the next message in the folder
     * will
     * be displayed or (if there are no more messages) the folder
     * index.</p>
     *
     * @return the current value of delete.
     */
    public final boolean getDelete() {
        return delete;
    }

    /**
     * <p>Indicates this message should be permanently erased from the
     * trash folder. After the message has been deleted, the next message
     * in
     * the trash folder will be displayed or (if there are no more
     * messages)
     * the trash folder index.</p>
     *
     * @return the current value of deleteTrash.
     */
    public final boolean getDeleteTrash() {
        return deleteTrash;
    }

    /**
     * <p>Used to store the result from a submit button, if this is
     * non-<code>null</code>, then the next message in the current folder
     * will be displayed.</p>
     *
     * @return the current value of displayNext.
     */
    public final String getDisplayNext() {
        return displayNext;
    }

    /**
     * <p>Used to store the result from a submit button, if this is
     * non-<code>null</code>, then the previous message in the current
     * folder will be displayed.</p>
     *
     * @return the current value of displayPrevious.
     */
    public final String getDisplayPrevious() {
        return displayPrevious;
    }

    /**
     * <p>Get the name of the folder to locate messages which will be
     * forwarded, replied to or used as a draft template.</p>
     *
     * @return the current name of the folder where the messages are
     * located.
     */
    public final String getFolderName() {
        return message.getFolderName();
    }

    /**
     * <p>Set by the action to indicate whether there are more messages in
     * the current folder after this one.</p>
     *
     * @return the current value of hasNext.
     */
    public final boolean getHasNext() {
        return hasNext;
    }

    /**
     * <p>Set by the action to indicate whether there are more messages in
     * the current folder before this one.</p>
     *
     * @return the current value of hasPrevious.
     */
    public final boolean getHasPrevious() {
        return hasPrevious;
    }

    /**
     * <p>Get the unique identifier of the message to be displayed.</p>
     *
     * @return the current value of the message identifier.
     */
    public final String getId() {
        return message.getMessageID();
    }

    /**
     * <p>Get the message data object which contains the main
     * information in this form.</p>
     *
     * @return message data object which contains all of the
     * information in this form.
     */
    public MessageDO getMessage() {
        return message;
    }

    /**
     * <p>Get whether the messages are sorted in ascending or descending
     * order.</p>
     *
     * @return <code>true</code> if the messages are sorted in ascending
     * order, otherwise (<code>false</code>) indicates they are
     * descending.
     */
    public final boolean getSortAscending() {
        return sortAscending;
    }

    /**
     * <p>Get the field to sort the returned list by, one of the
     * <code>SORT_...</code> constants in {@link
     * com.ivata.groupware.business.mail.MailConstants MailConstants}.
     *
     * @return the current value indicating which column to sort by.
     */
    public final Integer getSortBy() {
        return sortBy;
    }

    /**
     * <p>Get the setting of <code>thread</code>. This indicator tells us
     * that the message is a:<br/>
     * <ul>
     * <li>reply to all recipients of a previous message</li>
     * <li>reply to one recipient of a previous message</li>
     * <li>previous message(s) forwarded to new recipients</li>
     * <li>an existing (draft) message being altered for resending</li>
     * </ul></p>
     *
     * @return the current value of thread, one of the constants in {@link
     * com.ivata.groupware.business.mail.MailConstants MailConstants},
     */
    public final Integer getThread() {
        return thread;
    }

    /**
     * <p>Indicates this message should be moved to the trash folder.
     * After the message has been moved, the next message in the folder
     * will
     * be displayed or (if there are no more messages) the folder
     * index.</p>
     *
     * @param delete the new value of delete.
     */
    public final void setDelete(final boolean delete) {
        this.delete = delete;
    }

    /**
     * <p>Indicates this message should be permanently erased from the
     * trash folder. After the message has been deleted, the next message
     * in
     * the trash folder will be displayed or (if there are no more
     * messages)
     * the trash folder index.</p>
     *
     * @param deleteTrash the new value of deleteTrash.
     */
    public final void setDeleteTrash(final boolean deleteTrash) {
        this.deleteTrash = deleteTrash;
    }

    /**
     * <p>Used to store the result from a submit button, if this is
     * non-<code>null</code>, then the next message in the current folder
     * will be displayed.</p>
     *
     * @param displayNext the new value of displayNext.
     */
    public final void setDisplayNext(final String displayNext) {
        this.displayNext = displayNext;
    }

    /**
     * <p>Used to store the result from a submit button, if this is
     * non-<code>null</code>, then the previous message in the current
     * folder will be displayed.</p>
     *
     * @param displayPrevious the new value of displayPrevious.
     */
    public final void setDisplayPrevious(final String displayPrevious) {
        this.displayPrevious = displayPrevious;
    }

    /**
     * <p>Set the name of the folder to locate messages which will be
     * forwarded, replied to or used as a draft template.</p>
     *
     * @param folderName the current name of the folder where the messages
     * are located.
     */
    public final void setFolderName(final String folderName) {
        message.setFolderName(folderName);
    }

    /**
     * <p>Set by the action to indicate whether there are more messages in
     * the current folder after this one.</p>
     *
     * @param hasNext the new value of hasNext.
     */
    public final void setHasNext(final boolean hasNext) {
        this.hasNext = hasNext;
    }

    /**
     * <p>Set by the action to indicate whether there are more messages in
     * the current folder before this one.</p>
     *
     * @param hasPrevious the new value of hasPrevious.
     */
    public final void setHasPrevious(final boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    /**
     * <p>Set the unique identifier of the message to be displayed.</p>
     *
     * @param messageId new value of the message identifier.
     */
    public final void setId(final String messageId) {
        this.message.setMessageID(messageId);
    }

    /**
     * <p>Set the message data object which contains the main
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
     * <p>Set whether the messages are sorted in ascending or descending
     * order.</p>
     *
     * @param sortAscending set to <code>true</code> if the messages are
     * sorted in ascending order, otherwise (<code>false</code>) indicates
     * they are descending.
     */
    public final void setSortAscending(final boolean sortAscending) {
        this.sortAscending = sortAscending;
    }

    /**
     * <p>Set the field to sort the returned list by, one of the
     * <code>SORT_...</code> constants in {@link
     * com.ivata.groupware.business.mail.MailConstants MailConstants}.
     *
     * @param sortBy the new value indicating which column to sort by.
     */
    public final void setSortBy(final Integer sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * <p>Set the setting of <code>thread</code>. This indicator tells us
     * that the message is a:<br/>
     * <ul>
     * <li>reply to all recipients of a previous message</li>
     * <li>reply to one recipient of a previous message</li>
     * <li>previous message(s) forwarded to new recipients</li>
     * <li>an existing (draft) message being altered for resending</li>
     * </ul></p>
     *
     * @param thread the new value of thread, one of the constants in
     * {@link com.ivata.groupware.business.mail.MailConstants MailConstants},
     */
    public final void setThread(final Integer thread) {
        this.thread = thread;
    }
    /**
     * TODO
     *
     * @see com.ivata.mask.web.struts.MaskForm#validate(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession)
     */
    public ValidationErrors validate(final HttpServletRequest request,
            final HttpSession session) {
        // TODO Auto-generated method stub
        return null;
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
}

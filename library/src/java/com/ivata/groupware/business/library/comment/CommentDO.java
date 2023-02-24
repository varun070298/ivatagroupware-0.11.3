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
 * $Log: CommentDO.java,v $
 * Revision 1.3  2005/04/10 20:09:44  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:44  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:05  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/07/13 19:47:28  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:28  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:38  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/07/18 14:43:47  jano
 * we need itemTitle
 *
 * Revision 1.1  2003/02/24 19:09:22  colin
 * moved to business
 *
 * Revision 1.3  2003/02/04 17:43:47  colin
 * copyright notice
 *
 * Revision 1.2  2002/07/12 09:39:34  colin
 * added format field to choose between plain text and HTML comments
 *
 * Revision 1.1  2002/07/08 10:16:13  colin
 * first version of comment/comment tree model design
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.comment;

import java.io.Serializable;

import com.ivata.groupware.business.library.item.LibraryItemDO;
import com.ivata.groupware.container.persistence.TimestampDO;
import com.ivata.mask.web.format.FormatConstants;


/**
 * <p>Represents a single comment by a user, either in reply to a library item
 * or to another comment. The comments are usually displayed using a tree
 * generated from the {@link CommentTreeModelBean tree model}.</p>
 *
 * @since 2002-07-05
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table = "library_comment"
 */
public class CommentDO extends TimestampDO
        implements com.ivata.groupware.web.tree.TreeNode,
        Serializable {

    /**
     * <p>Store the format of this comment. This should correspond to one of the
     * <code>FORMAT_...</code> constants in {@link
     * com.ivata.mask.web.format.FormatConstants FormatConstants}.</p>
     */
    private int format = FormatConstants.FORMAT_TEXT;

    /**
     * <p>Store the item the comment relates to.</p>
     */
    private LibraryItemDO item;

    /**
     * <p>Parent comment which contains this one.</p>
     */
    private CommentDO parent;

    /**
     * <p>Store the subject for this comment. This is usually a clear-text
     * describing the comment, and can begin with "Re: " to show that it is
     * related to other comments in a thread.</p>
     */
    private String subject;

    /**
     * <p>Store the actual comment body, describing the user's thoughts.</p>
     */
    private String text;

    /**
     * <p>
     * If a comment should be ignored and needs no reply, set this to
     * <code>true</code>.
     * </p>
     */
    private boolean unacknowledged = false;

    /**
     * <p>Get the format of this comment. This should correspond to one of the
     * <code>FORMAT_...</code> constants in {@link
     * com.ivata.mask.web.format.FormatConstants FormatConstants}.</p>
     *
     * @return one of the <code>FORMAT_...</code> constants in {@link
     * com.ivata.mask.web.format.FormatConstants FormatConstants} which
     * identifies the formatting of this comment.
     *
     * @hibernate.property
     */
    public final int getFormat() {
        return format;
    }

    /**
     * <p>Get the library item this comment relates
     * to. Every comment in the system must relate to a library item.</p>
     *
     * @return unique identifier of the library item this comment
     * relates to.
     * @hibernate.many-to-one
     */
    public final LibraryItemDO getItem() {
        return item;
    }

    /**
     * <p>Get the 'name' for the subject as it appears in the tree. This returns
     * the same as <code>getSubject</code>.</p>
     *
     * @return see {@link #getSubject getSubject}.
     */
    public final String getName() {
        return getSubject();
    }
    /**
     * <p>Parent comment which contains this one.</p>
     *
     * @return comment which contains this one
     * @hibernate.many-to-one
     *      column="id_reply_to"
     */
    public final CommentDO getParent() {
        return parent;
    }

    /**
     * <p>Get the subject for this comment. This is usually a clear-text
     * describing the comment, and can begin with "Re: " to show that it is
     * related to other comments in a thread.</p>
     *
     * @return clear-text describing the comment content.
     * @hibernate.property
     */
    public final String getSubject() {
        return subject;
    }

    /**
     * <p>Get the actual comment body, describing the user's thoughts.</p>
     *
     * @return new value for the comment body describing the user's
     * thoughts.
     * @hibernate.property
     */
    public final String getText() {
        return text;
    }
    /**
     * <p>
     * If a comment should be ignored and needs no reply, set this to
     * <code>true</code>.
     * </p>
     *
     * @return current value of ignored.
     * @hibernate.property
     *      column="no_reply"
     */
    public boolean isUnacknowledged() {
        return unacknowledged;
    }

    /**
     * <p>Set the format of this comment. This should correspond to one of the
     * <code>FORMAT_...</code> constants in {@link
     * com.ivata.mask.web.format.FormatConstants FormatConstants}.</p>
     *
     * @param format one of the <code>FORMAT_...</code> constants in {@link
     * com.ivata.mask.web.format.FormatConstants FormatConstants} to
     * identify the formatting of this comment.
     */
    public final void setFormat(final int format) {
        this.format = format;
    }

    /**
     * <p>Set the unique identifier of the library item this comment relates
     * to. Every comment in the system must relate to a library item.</p>
     *
     * @param itemId new unique identifier of the library item this comment
     * relates to.
     */
    public final void setItem(final LibraryItemDO item) {
        this.item = item;
    }

    /**
     * <p>Parent comment which contains this one.</p>
     *
     * @param parent comment which contains this one
     */
    public final void setParent(final CommentDO parent) {
        this.parent = parent;
    }

    /**
     * <p>Set the subject for this comment. This is usually a clear-text
     * describing the comment, and can begin with "Re: " to show that it is
     * related to other comments in a thread.</p>
     *
     * @param subject clear-text describing the comment content.
     */
    public final void setSubject(final String subject) {
        this.subject = subject;
    }

    /**
     * <p>Set the actual comment body, describing the user's thoughts.</p>
     *
     * @param text new value for the comment body describing the user's
     * thoughts.
     */
    public final void setText(final String text) {
        this.text = text;
    }
    /**
     * <p>
     * If a comment should be ignored and needs no reply, set this to
     * <code>true</code>.
     * </p>
     *
     * @param ignored new value of ignored.
     */
    public final void setUnacknowledged(final boolean ignored) {
        this.unacknowledged = ignored;
    }
}

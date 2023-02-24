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
 * $Log: CommentTreeModel.java,v $
 * Revision 1.3  2005/04/10 20:31:59  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:44  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:06  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/11/12 18:16:05  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 15:57:15  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/07/31 10:26:38  colinmacleod
 * Fixed comment tree.
 *
 * Revision 1.1  2004/07/13 19:47:28  colinmacleod
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
 * Revision 1.3  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/02/24 19:09:22  colin
 * moved to business
 *
 * Revision 1.2  2003/02/04 17:43:47  colin
 * copyright notice
 *
 * Revision 1.1  2002/07/08 10:16:13  colin
 * first version of comment/comment tree model design
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.comment.tree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.library.Library;
import com.ivata.groupware.business.library.comment.CommentDO;
import com.ivata.groupware.business.library.item.LibraryItemDO;
import com.ivata.mask.util.SystemException;


/**
 * <p>Provides a tree model of the heirarchy of comments, for a given {@link
 * com.ivata.groupware.business.library.item.LibraryItemBean library item}.<p>
 *
 * <p>In ivata groupware, this tree control is displayed using {@link
 * com.ivata.groupware.web.tag.webgui.TreeTag TreeTag}.</p>
 *
 * <p>This is a wrapper class which wraps the remote class
 * {@link CommentTreeModelRemote CommentTreeModelRemote}, handles any
 * <code>RemoteException</code> and implements interfaces required
 * by the client application.</p>
 *
 * <p><strong>Note:</strong> This class provides data from {@link CommentTreeModelBean CommentTreeModelBean}.
 * This is no local copy of the bean class, however, and changes here
 * will not be automatically reflected in {@link CommentTreeModelBean CommentTreeModelBean}.</p>
 *
 * @since 2002-07-05
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 * @see CommentTreeModelBean
 * @see CommentTreeModelRemote
 */
public class CommentTreeModel implements TreeModel, Serializable {
    /**
     * <p>Stores the library item this tree applies to.</p>
     */
    private LibraryItemDO item;
    private Library library;
    private SecuritySession securitySession;

    /**
     * <p>Initialize the comment tree model.</p>
     */
    public CommentTreeModel(Library library,
            SecuritySession securitySession) {
        this.library = library;
        this.securitySession = securitySession;
    }

    /**
     * <p>This method is not implemented for this class</p>
     *
     * @param l the listener to add. Not used in this class.
     */
    public void addTreeModelListener(final TreeModelListener l) {
        throw new UnsupportedOperationException(
            "Method addTreeModelListener(  ) not implemented.");
    }

    /**
     * <p>Get the reply to the parent comment provided, with the specified
     * index.</p>
     *
     * @param parent the parent for whom to return the reply comment.
     * @param index the index of the child within the internal parent array.
     * @return the reply to the parent comment provided, with the specified
     *      index, or <code>null</code> if the specified comment has no
     *      replies.
     */
    public final Object getChild(final Object parent,
            final int index) {
        CommentDO parentComment = (CommentDO) parent;
        return getReplies(parentComment).get(index);
    }


    /**
     * <p>Get the number of replies to the given parent comment.</p>
     *
     * @param parentDO the parent for whom to return the count of the number of
     *     replies.
     * @return the number of replies to the given parent comment.
     */
    public final int getChildCount(final Object parent) {
        CommentDO parentComment = (CommentDO) parent;
        return getReplies(parentComment).size();
    }

    /**
     * <p>Get the index of a particular reply in the parent's array of
     * reply comments.</p>
     *
     * @param parentDO the parent node for whom to find the reply index.
     * @param childDO the reply node to find the index for.
     * @return the index of a reply (child) node for the given parent comment.
     */
    public final int getIndexOfChild(final Object parent,
            final Object child) {
        CommentDO parentComment = (CommentDO) parent;
        return getReplies(parentComment).indexOf(child);
    }

    /**
     * <p>Get the library item for the tree. Each tree is unique to
     * one library item.</p>
     *
     * @return library item referenced.
     */
    public final LibraryItemDO getItem() {
        return item;
    }

    /**
     * <p>
     * Private Helper. Get all the comments with the parent provided.
     * </p>
     */
    private List getReplies(final CommentDO parent) {
        // is this the 'fake' root comment
        if (parent.getId() == null) {
            try {
                return library.findCommentByItem(securitySession, parent.getItem().getId());
            } catch (SystemException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                return library.findCommentByParent(securitySession, parent.getId());
            } catch (SystemException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * <p>Get the root node of the tree. The data structure for comments does
     * not actually have a root comment - the effective root of each comment
     * tree is the library item itself. This method returns a new
     * <code>CommentDO</code> with a <code>null</code> identifier and the
     * correct library item identifier.</p>
     *
     * @return the root node of the tree, as an instance of {@link
     *     com.ivata.groupware.business.library.comment.CommentDO CommentDO}.
     */
    public final Object getRoot() {
        CommentDO root = new CommentDO();
        root.setId(null);
        root.setItem(item);
        return root;
    }

    /**
     * <p>Always returns <code>false</code> since there are no leaf nodes in the
     * comment tree.</p>
     *
     * @param node this parameter is not used.
     * @return always returns <code>false</code>.
     */
    public boolean isLeaf(final Object node) {
        return false;
    }

    /**
     * <p>Serialize the object from the input stream provided.</p>
     *
     * @param ois the input stream to serialize the object from
     * @throws IOException thrown by <code>ObjectInputStream.defaultReadObject(  )</code>.
     * @throws ClassNotFoundException thrown by <code>ObjectInputStream.defaultReadObject(  )</code>.
     */
    private void readObject(final ObjectInputStream ois)
        throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }

    /**
     * <p>This method is not implemented for this class</p>
     *
     * @param l the listener to remove. Not usd in this class.
     */
    public void removeTreeModelListener(final TreeModelListener l) {
        throw new UnsupportedOperationException(
            "Method removeTreeModelListener(  ) not implemented.");
    }
    /**
     * <p>Get the library item for the tree. Each tree is unique to
     * one library item.</p>
     *
     * @param item library item referenced.
     */
    public final void setItem(final LibraryItemDO item) {
        this.item = item;
    }

    /**
     * <p>This method is not implemented for this class</p>
     *
     * @param path path to the node that the user has altered.
     * @param newValue the new value from the TreeCellEditor.
     */
    public void valueForPathChanged(final TreePath path,
            final Object newValue) {
        throw new UnsupportedOperationException(
            "Method valueForPathChanged(  ) not implemented.");
    }

    /**
     * <p>Serialize the object to the output stream provided.</p>
     *
     * @param oos the output stream to serialize the object to
     * @throws IOException thrown by <code>ObjectOutputStream.defaultWriteObject(  )</code>
     */
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

}

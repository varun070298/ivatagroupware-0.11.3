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
 * $Log: TreeNodeRenderer.java,v $
 * Revision 1.2  2005/04/09 17:19:10  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:36  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/09/30 15:16:03  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.3  2004/07/13 19:41:16  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/03/21 21:16:18  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:57:58  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.1.1.1  2003/10/13 20:50:09  colin
 * Restructured portal into subprojects
 *
 * Revision 1.1  2003/02/24 19:33:33  colin
 * moved to jsp
 *
 * Revision 1.4  2003/02/04 17:43:46  colin
 * copyright notice
 *
 * Revision 1.3  2003/01/24 19:30:27  peter
 * added pageContext to initialize parameters, URL rewriting
 *
 * Revision 1.2  2002/08/10 21:17:48  colin
 * first version of HTML sanitizer/parser to clean up HTML code
 *
 * Revision 1.1  2002/06/21 11:58:37  colin
 * restructured com.ivata.groupware.web into seperate
 * subcategories:
 * fornat, javascript, theme and tree.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.tree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.ivata.groupware.web.tag.webgui.tree.TreeTag;
import com.ivata.mask.web.theme.Theme;


/**
 * <p>This interface defines the methods of an HTML tree renderer as
 * used in
 * the ivata groupware {@link com.ivata.groupware.web.tag.webgui.TreeTag
 * tree
 * tag}.</p>
 *
 * @since   2002-05-16
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public abstract class TreeNodeRenderer {
    /**
     * <p>This tree tag reference is used to open and close folders.</p>
     */
    private TreeTag treeTag;

    /**
     * <p>Get a string which will be used in the tree representation of
     * the tree node. This can contain caption information and image
     * paths, for
     * example, as properties which are evaluated by {@link
     * com.ivata.groupware.web.theme.Theme#parseSection(String
     * name,
     * java.util.Properties properties)
     * Theme.parseSection(String name, java.util.Properties
     * properties)}.</p>
     * @param model <code>TreeModel</code> representing the data of the
     * tree.
     * @param node <code>Object</code> representing a node in the tree.
     * The
     * <code>toString(  )</code> of this node will be used as the
     * 'caption'
     * properties when parsing.
     * @param expanded <code>true</code> if this node is 'open', otherwise
     * <code>false</code>.
     * @param leaf <code>true</code> if this node is a leaf node,
     * otherwise
     * <code>false</code>.
     * A leaf node is one which can have no children, like a file in a
     * filesystem
     * tree.
     * @param level the depth of this node within the tree, with 0 being
     * root.
     * @param last <code>true</code> if this node is the last in the
     * current branch,
     * otherwise <code>false</code>.
     * @param theme this theme does the parsing.
     * @param properties existing properties to parse.
     * @return the parsed string.
     * @throws JspException if the theme sections required by the
     * renderer have not been defined.
     */
    public abstract String render(javax.swing.tree.TreeModel model, Object node, boolean expanded, boolean leaf, int level, boolean last, Theme theme, java.util.Properties properties) throws JspException;

    /**
     * <p>This method initializes the internal reference to the tree
     * tag.</p>
     *
     * @param treeTag internal reference to the treetag which owns this
     * renderer.
     */
    public void setTreeTag(TreeTag treeTag) {
        this.treeTag = treeTag;
    }

    /**
     * <p>This method is called by the tree tag during
     * <code>doStartTag</code> to
     * allow the renderer to open or close folders as apropriate.</p>
     *
     * @param session the current session which can be used to retrieve
     * settings.
     * @param request the current servlet request which can be used to
     * retrieve
     * settings.
     * @param out jsp writer which can be used to output HTML.
     * @param pageContext the current PageContext
     * @throws JspException not thrown by this class but can be thrown by
     * subclasses
     * who experience an error on initialization.
     */
    public abstract void initialize(HttpSession session, HttpServletRequest request, JspWriter out, PageContext pageContext) throws JspException;

    /**
     * <p>Is called by the tree tag after the tree has been drawn. Can be
     * overridden
     * by subclasses to provide finalization.</p>
     *
     * <p>This default implementation does nothing.</p>
     *
     * @param session the current session which can be used to retrieve
     * settings.
     * @param request the current servlet request which can be used to
     * retrieve
     * settings.
     * @param out jsp writer which can be used to output HTML.
     * @throws JspException not thrown by this class but can be thrown by
     * subclasses
     * who experience an error on finalization.
     */
    public void finalize(HttpSession session, HttpServletRequest request, JspWriter out) throws JspException {
    }

    /**
     * <p>Get the internal reference to the tree tag.</p>
     *
     * @return internal reference to the treetag which owns this renderer.
     */
    public TreeTag getTreeTag() {
        return treeTag;
    }
}

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
 * $Log: DefaultTreeNodeRenderer.java,v $
 * Revision 1.3  2005/04/10 20:32:01  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:10  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:36  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/12/23 21:01:31  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.2  2004/11/12 15:57:20  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/09/30 15:16:03  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.3  2004/07/13 19:41:15  colinmacleod
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
 * Revision 1.2  2004/01/20 15:11:46  jano
 * fixing problems with new sslext
 *
 * Revision 1.1.1.1  2003/10/13 20:50:07  colin
 * Restructured portal into subprojects
 *
 * Revision 1.2  2003/08/19 06:09:53  peter
 * committed because the version on lucenec1 doesn't compile
 *
 * Revision 1.1  2003/02/24 19:33:33  colin
 * moved to jsp
 *
 * Revision 1.6  2003/02/04 17:43:46  colin
 * copyright notice
 *
 * Revision 1.5  2003/01/24 19:30:27  peter
 * added pageContext to initialize parameters, URL rewriting
 *
 * Revision 1.4  2002/09/16 15:53:13  jano
 * if you set up fomName it will submit form when you open or
 * close folder, otherwise it will make a link
 *
 * Revision 1.3  2002/08/10 21:17:48  colin
 * first version of HTML sanitizer/parser to clean up HTML code
 *
 * Revision 1.2  2002/07/01 08:09:44  colin
 * added new antlr parser; trapped parse exception
 *
 * Revision 1.1  2002/06/21 11:58:37  colin
 * restructured com.ivata.groupware.web into seperate
 * subcategories:
 * fornat, javascript, theme and tree.
 */
// Source file: h:/cvslocal/ivata groupware/src/com.ivata.groupware/jsp/tree/DefaultTreeNodeRenderer.java
package com.ivata.groupware.web.tree;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.struts.taglib.TagUtils;

import com.ivata.groupware.web.tag.webgui.tree.TreeTag;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.web.theme.Theme;
import com.ivata.mask.web.theme.ThemeParseException;


/**
 * <p>Create a default tree node renderer to draw your tree. This
 * renderer
 * calls {@link com.ivata.groupware.web.theme.Theme#parseSection
 * Theme.parseSection}
 * to parse the standard tree theme sections 'treeOpen', 'treeClosed',
 * 'treeNoChildren' and 'treeLeaf'.</p>
 *
 * <p>The object you provide must implement {@link
 * com.ivata.groupware.web.DefaultTreeNode
 * TreeNode}.</p>
 *
 * <p>In each of these sections, the property 'caption' is parsed out
 * to the
 * {@link com.ivata.groupware.web.DefaultTreeNode#getName(  ) getName}
 * value
 * of the node
 * provided, and the property 'id' is parsed out to the value of
 * {@link com.ivata.groupware.web.DefaultTreeNode#getName(  )
 * getId}.</p>
 *
 * @since   2002-05-16
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 * @see     TreeNodeRenderer
 * @see     com.ivata.groupware.web.theme.Theme#parseSection
 */
public class DefaultTreeNodeRenderer extends TreeNodeRenderer {

    /**
     * <p>The URL of the current page, used to create open folder/ close
     * folder
     * links.</p>
     */
    private String URL;

    /**
     * <p>valueholder foor the current <code>PageContext</code></p>
     */
    private PageContext pageContext;

    /**
     * <p>Render a standard tree using the theme sections 'treeOpen',
     * 'treeClosed',
     * 'treeNoChildren' and 'treeLeaf'.</p>
     * <p>The following properties will be defined:<br/>
     * <table cellpadding='2' cellspacing='5' border='0' align='center'
     * width='85%'>
     *   <tr class='TableHeadingColor'>
     *     <th>property</th>
     *     <th>description</th>
     *   </tr>
     *   <tr class='TableRowColor'>
     *     <td>caption</td>
     *     <td>The value returned by <code>node.toString(  )</code>.</td>
     *   </tr>
     *   <tr class='TableRowColor'>
     *     <td>id</td>
     *     <td>The id of the node.</td>
     *   </tr>
     *   <tr class='TableRowColor'>
     *     <td>last</td>
     *     <td>'Last' if this is the last node in the current branch,
     * otherwise
     * this property is not set.</td>
     *   </tr>
     * </table>
     * </p>
     * <p> These properties are evaluated by calling {@link
     * com.ivata.groupware.web.theme.Theme#parseSection(String
     * sName,
     * java.util.Properties properties)
     * Theme.parseSection(String sName, java.util.Properties
     * properties)}
     * for each of the relevant theme sections.</p>
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
     * @throws JspException if the theme sections 'treeOpen',
     * 'treeClosed'
     * 'treeNoChildren' or 'treeLeaf' have not been defined.
     *
     *
     */
    public String render(javax.swing.tree.TreeModel model, Object node, boolean expanded, boolean leaf, int level, boolean last, Theme theme, java.util.Properties properties) throws JspException {
        try {
            TreeNode treeNode = (TreeNode) node;
            HashMap URLProperties = new HashMap();

            // set the caption property
            properties.setProperty("caption", treeNode.getName());
            // set the id property
            if (treeNode.getId() != null) {
                properties.setProperty("id", treeNode.getId().toString());
            }
            // is this the last node? if so, set the last property
            if (last) {
                properties.setProperty("last", "Last");
            }
            // is this a leaf = no children?
            if (leaf) {
                return theme.parseSection("treeLeaf",
                        setAdditionalProperties(treeNode, level, properties));
            }
            // see if this node has no children
            if (model.getChildCount(node) == 0) {
                return theme.parseSection("treeNoChildren",
                        setAdditionalProperties(treeNode, level, properties));
            }
            // options: it is either open or closed and if we will submit the form or we will make a link
            TreeTag treeTag = getTreeTag();
            if (expanded) {
                if (treeTag.getFormName() != null) {
                    properties.setProperty("folderURL", "javascript:" + treeTag.getFormName() +
                        ".closeFolder.value=" + treeNode.getId() + ";" +
                        treeTag.getFormName() + ".submit();");
                    return theme.parseSection("treeOpen",
                            setAdditionalProperties(treeNode, level, properties));
                } else {
                    URLProperties.put("closeFolder", treeNode.getId().toString());
                    try {
                        properties.setProperty("folderURL",
                            TagUtils.getInstance().computeURL(pageContext, null, URL,
                                null, null, null, URLProperties, null, true));
                    } catch (java.net.MalformedURLException e) {
                        throw new JspException(e);
                    }
                    return theme.parseSection("treeOpen",
                            setAdditionalProperties(treeNode, level, properties));
                }
            } else {
                if (treeTag.getFormName() != null) {
                    properties.setProperty("folderURL", "javascript:" + treeTag.getFormName() +
                        ".openFolder.value=" + treeNode.getId() + ";" +
                        treeTag.getFormName() + ".submit();");
                    return theme.parseSection("treeClosed",
                            setAdditionalProperties(treeNode, level, properties));
                } else {
                    URLProperties.put("openFolder", treeNode.getId().toString());
                    try {
                        properties.setProperty("folderURL",
                                TagUtils.getInstance().computeURL(pageContext, null,
                                        URL, null,  null, null, URLProperties,
                                        null, true));
                    } catch (java.net.MalformedURLException e) {
                        throw new JspException(e);
                    }
                    return theme.parseSection("treeClosed",
                            setAdditionalProperties(treeNode, level, properties));
                }
            }
        } catch (ThemeParseException e) {
            throw new RuntimeException(e);
        }
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
     * @param pageContext the current <code>PageContext</code>
     * @throws JspException not thrown by this class but can be thrown by
     * subclasses
     * who experience an error on initialization.
     */
    public void initialize(HttpSession session, HttpServletRequest request, JspWriter out, PageContext pageContext) throws JspException {
        String openFolder = request.getParameter("openFolder");
        TreeTag treeTag = getTreeTag();

        if (!StringHandling.isNullOrEmpty(openFolder)) {
            treeTag.setOpenFolder(StringHandling.integerValue(openFolder));
        }
        String closeFolder = request.getParameter("closeFolder");

        if (!StringHandling.isNullOrEmpty(closeFolder)) {
            treeTag.setCloseFolder(StringHandling.integerValue(closeFolder));
        }
        URL = request.getRequestURL().toString();
        this.pageContext = pageContext;
    }

    /**
     * <p>Can be overridden by super-class to provide addtional property
     * information
     * for a node.</p>
     *
     * @param treeNode the current node in the tree being drawn.
     * @param level the depth of this node within the tree, with 0 being
     * root.
     * @param properties all the properties are already defined. New
     * properties
     * should be added to this instance and returned.
     * @return all of the properties which should be evaluated in the
     * client theme
     * section.
     * @throws JspException thrown by subclasses if there is a formatting
     * error.
     */
    public java.util.Properties setAdditionalProperties(TreeNode treeNode, int level, java.util.Properties properties) throws JspException {
        // this default implementation does nothing...
        return properties;
    }

    /**
     * <p>valueholder foor the current <code>PageContext</code></p>
     *
     * @return the current value of pageContext.
     */
    public PageContext getPageContext() {
        return pageContext;
    }

    /**
     * <p>valueholder foor the current <code>PageContext</code></p>
     *
     * @param pageContext the new value of pageContext.
     */
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }
}

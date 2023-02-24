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
 * $Log: CommentTreeNodeRenderer.java,v $
 * Revision 1.2  2005/04/09 17:19:48  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:07  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.5  2004/11/12 18:16:06  colinmacleod
 * Ordered imports.
 *
 * Revision 1.4  2004/11/12 15:57:16  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.3  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:58:45  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/11/18 09:57:52  jano
 * commiting all forgoten staff
 *
 * Revision 1.2  2003/10/15 14:16:54  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/02/27 07:53:01  colin
 * added missing setUserName on dateFormatter
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
 * Revision 1.2  2002/09/05 13:23:24  colin
 * changed library user rights from canUser... to can...
 *
 * Revision 1.1  2002/08/15 09:48:49  colin
 * first version
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.tree.comment;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.admin.setting.SettingsDataTypeException;
import com.ivata.groupware.business.library.comment.CommentDO;
import com.ivata.groupware.business.library.right.LibraryRights;
import com.ivata.groupware.util.SettingDateFormatter;
import com.ivata.groupware.web.tree.DefaultTreeNodeRenderer;
import com.ivata.groupware.web.tree.TreeNode;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.format.CharacterEntityFormat;
import com.ivata.mask.web.format.DateFormatterException;
import com.ivata.mask.web.format.FormatConstants;
import com.ivata.mask.web.format.HTMLFormatter;
import com.ivata.mask.web.format.LineBreakFormat;


/**
 * <p>Create at tree node renderer for library item comments. This
 * renderer uses
 * the same theme sections as <code>DefaultTreeNodeRenderer</code>
 * though it
 * sets
 * new properties to be evaluated in a separate theme.</p>
 *
 * @since   2002-07-07
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 * @see     DefaultTreeNodeRenderer
 */
public class CommentTreeNodeRenderer extends DefaultTreeNodeRenderer {

    /**
     * <p>Applied to the content of the tree tag</p>
     */
    private CharacterEntityFormat characterEntityFormat = new CharacterEntityFormat();

    /**
     * <p>Used to format the modified by field.</p>
     */
    private SettingDateFormatter dateFormatter;

    /**
     * <p>Used to check whether or not the current user can edit the
     * current
     * node.</p>
     */
    private LibraryRights libraryRights;

    /**
     * <p>Used to convert line breaks in plain text comments.</p>
     */
    private LineBreakFormat lineBreakFormat = new LineBreakFormat();

    /**
     * <p>Used to initialize get the spacer width.</p>
     */
    private Settings settings;
    /**
     * <p>Stores the number of the top level comment (top level meaning a
     * comment
     * which is not a reply to another comment). This is used to set the
     * css
     * class.</p>
     */
    int threadNumber = 0;
    SecuritySession securitySession;

    /**
     * Constructor.
     */
    public CommentTreeNodeRenderer(SecuritySession securitySession,
            SettingDateFormatter dateFormatter, LibraryRights
            libraryRights, Settings settings) {
        this.securitySession = securitySession;
        this.dateFormatter = dateFormatter;
        this.libraryRights = libraryRights;
        this.settings = settings;
    }

    /**
     * <p>Overridden to close the table which surrounds comment trees.</p>
     *
     * @param session the current session which can be used to retrieve
     * settings.
     * @param request the current servlet request which can be used to
     * retrieve
     * settings.
     * @param out jsp writer which can be used to output HTML.
     * @throws JspException if there is an error parsing the theme or
     * writing the
     * output.
     * who experience an error on finalization.
     */
    public void finalize(final HttpSession session,
            final HttpServletRequest request,
            final JspWriter out) throws JspException {
        try {
            out.print(getTreeTag().getTheme().parseSection("finalizeCommentTree",
                    new Properties()));
        } catch (java.io.IOException e) {
            throw new JspException(e);
        }

        super.finalize(session, request, out);
    }

    /**
     * <p>Access the internal date formatter to change the date or time
     * format
     * applied.</p>
     *
     * @return the formatter which is used internally to convert the
     * modified field
     * to a string.
     */
    public final SettingDateFormatter getDateFormatter() {
        return dateFormatter;
    }

    /**
     * <p>This method has been overriden to initialize the settings in the
     * internal
     * date formatter, and to parse the comment surrounding table.</p>
     *
     * @param session the current session which can be used to retrieve
     * settings.
     * @param request the current servlet request which can be used to
     * retrieve
     * settings.
     * @param out jsp writer which can be used to output HTML.
     * @param pageContext the current PageContext
     * @throws JspException if there is an error parsing the theme or
     * writing the
     * output.
     * who experience an error on initialization.
     */
    public void initialize(final HttpSession session,
            final HttpServletRequest request,
            final JspWriter out,
            final PageContext pageContext) throws JspException {
        try {
            out.print(getTreeTag().getTheme().parseSection("initializeCommentTree",
                    new Properties()));
        } catch (java.io.IOException e) {
            throw new JspException(e);
        }

        super.initialize(session, request, out, pageContext);

        // if we apply it, we'll want the line break format to do its job ;-)
        lineBreakFormat.setConvertLineBreaks(true);
    }

    /**
     * <p>Overridden to add the comment text property.</p>
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
     * @throws JspException if there is a format error with the date.
     */
    public Properties setAdditionalProperties(final TreeNode treeNode,
            final int level,
            final Properties properties) throws JspException {
        // make the right formatter for the occasion
        HTMLFormatter formatter = new HTMLFormatter();
        CommentDO commentDO = (CommentDO) treeNode;

        // see if the user is allowed to edit this comment
        try {
            if (libraryRights.canAmendComment(securitySession, commentDO)) {
                properties.setProperty("canUserEdit", "true");
            }
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }

        // if this is a plain text message, convert character entities and line
        // breaks
        if (commentDO.getFormat() == FormatConstants.FORMAT_TEXT) {
            formatter.add(characterEntityFormat);
            formatter.add(lineBreakFormat);
        }

        properties.setProperty("commentSubject",
            formatter.format(commentDO.getSubject()));
        properties.setProperty("commentText",
            formatter.format(commentDO.getText()));
        properties.setProperty("commentUserName", commentDO.getCreatedBy().getName());

        try {
            properties.setProperty("commentModified",
                dateFormatter.format(commentDO.getModified()));
        } catch (DateFormatterException e1) {
            throw new RuntimeException(e1);
        }

        // how large the spacer is depends on the level
        try {
            properties.setProperty("spacer",
                new Integer(
                    level * settings.getIntegerSetting(securitySession,
                            "libraryCommentSpacer",
                            securitySession.getUser()).intValue()).toString());
        } catch (SettingsDataTypeException e) {
            throw new JspException(e);
        } catch (SystemException e) {
            throw new JspException(e);
        }

        // the css style for this row depends on the whether the thread number
        // is odd or even, and whether the reply number within that thread is
        // odd or even too.
        String threadClass;

        if ((threadNumber % 2) == 0) {
            if ((level % 2) == 0) {
                threadClass = "commentEvenThreadEvenReply";
            } else {
                threadClass = "commentEvenThreadOddReply";
            }
        } else if ((level % 2) == 0) {
            threadClass = "commentOddThreadEvenReply";
        } else {
            threadClass = "commentOddThreadOddReply";
        }

        properties.setProperty("threadClass", threadClass);

        // if this is a top level comment, then increase the thread counter
        if (level == 0) {
            ++threadNumber;
        }

        return properties;
    }
}

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
 * $Log: PersonTreeNodeRenderer.java,v $
 * Revision 1.2  2005/04/09 17:19:10  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:36  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.5  2004/12/23 21:01:26  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.4  2004/11/12 15:57:07  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
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
 * Revision 1.2  2004/01/20 15:11:46  jano
 * fixing problems with new sslext
 *
 * Revision 1.1.1.1  2003/10/13 20:50:09  colin
 * Restructured portal into subprojects
 *
 * Revision 1.1  2003/02/24 19:33:33  colin
 * moved to jsp
 *
 * Revision 1.8  2003/02/04 17:43:47  colin
 * copyright notice
 *
 * Revision 1.7  2003/01/31 16:20:01  colin
 * bugfixes for /addressBook/group.jsp
 *
 * Revision 1.6  2003/01/24 19:30:27  peter
 * added pageContext to initialize parameters, URL rewriting
 *
 * Revision 1.5  2002/09/16 15:57:56  colin
 * resolved
 *
 * Revision 1.4  2002/09/16 15:54:31  jano
 * changed opening and closing folder and added
 * initializePersonTree section in theme
 *
 * Revision 1.3  2002/09/03 08:05:47  jano
 * corecting of seting property value
 *
 * Revision 1.2  2002/08/28 15:13:49  jano
 * new code cenerated by rose, I was missing imports
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.tree.person;

import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.struts.taglib.TagUtils;

import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.addressbook.person.group.tree.PersonTreeNode;
import com.ivata.groupware.web.tree.DefaultTreeNodeRenderer;
import com.ivata.groupware.web.tree.TreeNode;
import com.ivata.mask.util.StringHandling;


/**
 * <p>Overrides methods <code>setAdditionalProperties</code> and
 * <code>initialize</code>. We need to know which check box is checked
 * and which is not.</p>
 *
 * @since   2002-05-27
 * @author  jano
 * @version $Revision: 1.2 $
 * @see     DefaultTreeNodeRenderer
 */
public class PersonTreeNodeRenderer extends DefaultTreeNodeRenderer {
    private java.util.Vector checked = null;
    PageContext pageContext = null;

    /**
     * <p>Overridden method to set up <code>check</code> property of the
     * check box in the leaf node.</p>
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
    public java.util.Properties setAdditionalProperties(final TreeNode treeNode,
            final int level,
            final java.util.Properties properties) throws JspException {
        PersonDO person = (PersonDO) ((PersonTreeNode) treeNode).getPerson();

        if (person != null) {
            // see if person Id is in checked vector
            if ((checked !=null) && (person != null) &&
                (checked.indexOf(person.getId()) != -1)) {
                properties.setProperty("checked", "checked");
            }
            properties.setProperty("value", person.getId().toString());
            properties.setProperty("email",
                StringHandling.getNotNull(person.getEmailAddress(),
                                        "[none]"));
            properties.setProperty("fileAs",
                StringHandling.getNotNull(person.getFileAs()));
        } else {
            try {
                HashMap map = new HashMap();
                map.put("page",
                        "/mask/find.action?deleteKey=group.alert.delete"
                    + "&menuFrameURI=%2FaddressBook%2FgroupTree.action"
                    + "%3FgroupTreeRefresh%3Dtrue%26mode%3Dgroup&"
                    + "idString=" + treeNode.getId().toString()
                    + "&inputMask=imGroupInputMaskAction"
                    + "&baseClass=com.ivata.groupware.business.addressbook"
                    + ".person.group.GroupDO&bundle=addressBook"
                    + "&resourceFieldPath=group&menuFrameName=ivataGroupList");
                String URL = TagUtils.getInstance().computeURL(pageContext, "utilLoading",
                        null, null, null, null, map, null, true);
                properties.setProperty("groupFindAction", URL);
            } catch (java.net.MalformedURLException e) {
                throw new JspException(e);
            }
        }

        return properties;
    }

    /**
     * <p>This method is called by the tree tag during
     * <code>doStartTag</code> to allow the renderer to open or close
     * folders as appropriate.</p>
     *
     * <p>It gets <code>Vector checkedAttendee</code> from the
     * session.</p>
     *
     * @param session the current session which can be used to retrieve
     * settings.
     * @param request the current servlet request which can be used to
     * retrieve settings.
     * @param out jsp writer which can be used to output HTML.
     * @param pageContext the current <code>PageContext</code>
     * @throws JspException not thrown by this class but can be thrown by
     * subclasses
     * who experience an error on initialization.
     */
    public void initialize(final HttpSession session,
            final HttpServletRequest request,
            final JspWriter out,
            final PageContext pageContext) throws JspException {
        this.pageContext = pageContext;
        try {
            out.print(getTreeTag().getTheme().parseSection("initializePersonTree", new Properties()));
        } catch (java.io.IOException e) {
            throw new JspException(e);
        }

        super.initialize(session,request,out, pageContext);
        this.checked = (java.util.Vector) session.getAttribute("checkedAttendees");

    }

    /**
     * @return the current value of checked.

     */
    public final java.util.Vector getChecked() {
        return checked;
    }

    /**
     * @param checked the new value of checked.

     */
    public final void setChecked(final java.util.Vector checked) {
        this.checked=checked;
    }
}

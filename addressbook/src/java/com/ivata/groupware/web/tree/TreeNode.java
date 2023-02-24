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
 * $Log: TreeNode.java,v $
 * Revision 1.4  2005/04/29 02:48:14  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:09:39  colinmacleod
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
 * Revision 1.2  2004/11/03 16:10:12  colinmacleod
 * Changed todo comments to TODO: all caps.
 *
 * Revision 1.1  2004/09/30 15:16:03  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.2  2004/03/21 21:16:18  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:57:58  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.1.1.1  2003/10/13 20:50:07  colin
 * Restructured portal into subprojects
 *
 * Revision 1.1  2003/02/24 19:33:33  colin
 * moved to jsp
 *
 * Revision 1.2  2003/02/04 17:43:46  colin
 * copyright notice
 *
 * Revision 1.1  2002/06/21 11:58:37  colin
 * restructured com.ivata.groupware.web into seperate subcategories: fornat, javascript, theme and tree.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.tree;

/**
 * <p>Create a tree node as used in {@link
 * com.ivata.groupware.web.DefaultTreeNodeRenderer
 * DefaultTreeNodeRenderer}. All objects which are used as nodes in {@link
 * com.ivata.groupware.web.tag.webgui.TreeTag TreeTag} must implement this
 * interface.</p>
 *
 * @since   TODO:
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 * @see     com.ivata.groupware.web.DefaultTreeNodeRenderer
 */
public interface TreeNode {

    /**
     * <p>Get a unique identifier for this object.</p>
     *
     * @return a unique identifier for this object.
     *
     */
    public Integer getId();

    /**
     * <p>Get a clear-text name for this object. This should usually be unique
     * for this object, although this is not necessarily enforced.</p>
     *
     * @return a clear-text name for this object.
     *
     */
    public String getName();
}

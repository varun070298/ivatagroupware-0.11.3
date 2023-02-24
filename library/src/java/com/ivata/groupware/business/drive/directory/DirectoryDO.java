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
 * $Log: DirectoryDO.java,v $
 * Revision 1.3  2005/04/10 20:09:43  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:43  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:52  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/07/13 20:00:00  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/03/21 21:16:25  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:58:26  colinmacleod
 * Moved ivata op to sourceforge.
 *
 * Revision 1.2  2003/10/15 14:05:21  colin
 * fixing for XDoclet
 *
 * Revision 1.4  2003/08/14 08:11:00  peter
 * move to no path in db
 *
 * Revision 1.3  2003/08/05 12:21:48  peter
 * fixed fields
 *
 * Revision 1.2  2003/07/29 10:45:34  peter
 * Directory tree works (without rights)
 *
 * Revision 1.1  2003/07/18 17:43:58  peter
 * initial version
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.drive.directory;

import com.ivata.groupware.container.persistence.TimestampDO;
import com.ivata.groupware.web.tree.TreeNode;


/**
 * <p>Stores a directory of the virtual drive</p>
 *
 * @author  Peter Illes
 * @since   2003-07-18
 * @version $Revision: 1.3 $
 */
public class DirectoryDO extends TimestampDO implements TreeNode {
    /**
     * <p>Name of the directory</p>
     */
    private String name;

    /**
     * <p>Parent directory</p>
     */
    private DirectoryDO parent;

    /**
     * <p>Get the name of the directory</p>
     *
     * @return the directory name
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Get the parent directory</p>
     *
     * @return the parent directory
     */
    public DirectoryDO getParent() {
        return parent;
    }

    /**
     * <p>Set the name of the directory</p>
     *
     * @param name the directory name
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * <p>Set the parent directory</p>
     *
     * @param parent the id of parent directory
     */
    public final void setParent(final DirectoryDO parent) {
        this.parent = parent;
    }
}

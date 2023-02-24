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
 * $Log: DirectoryConstants.java,v $
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
 * Revision 1.2  2003/08/18 10:47:55  peter
 * new directory methods
 *
 * Revision 1.1  2003/08/14 15:17:11  peter
 * added to cvs
 * -----------------------------------------------------------------------------
 */

// Source file: h:/cvslocal/ivata op/src/com.ivata.portal/business/drive/directory/DirectoryConstants.java
package com.ivata.groupware.business.drive.directory;


/**
 * <p>This class holds the ids of special folders in virtual drive</p>
 */
public class DirectoryConstants {
    /**
     * <p>The root directory, this directory has no parent</p>
     */
    public static final Integer ROOT_DIRECTORY = new Integer(0);

    /**
     * <p>The attached files of library items are kept inside
     * subdirectories of this directory</p>
     */
    public static final Integer LIBRARY_DIRECTORY = new Integer(1);

    /**
     <p>No navigation available (no history)</p>
     */
    public static final Integer NAVIGATION_NONE = new Integer(0);

    /**
     <p>Forward navigation available</p>
     */
    public static final Integer NAVIGATION_FORWARD = new Integer(1);

    /**
     <p>Backward navigation available</p>
     */
    public static final Integer NAVIGATION_BACKWARD = new Integer(2);

    /**
     <p>Forward and backward navigation both available</p>
     */
    public static final Integer NAVIGATION_BOTH = new Integer(3);
}

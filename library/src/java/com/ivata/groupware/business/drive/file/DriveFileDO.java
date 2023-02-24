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
 * $Log: DriveFileDO.java,v $
 * Revision 1.2  2005/04/09 17:19:43  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:52  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.5  2004/07/13 20:00:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:26  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/10 19:57:23  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:07:30  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:27  colinmacleod
 * Moved ivata op to sourceforge.
 *
 * Revision 1.2  2003/10/15 14:05:21  colin
 * fixing for XDoclet
 *
 * Revision 1.3  2003/08/14 08:11:17  peter
 * move to no path in db
 *
 * Revision 1.2  2003/05/13 15:39:50  jano
 * fixing *DOs
 *
 * Revision 1.1  2003/05/13 08:05:55  peter
 * added to cvs
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.drive.file;

import com.ivata.groupware.business.drive.directory.DirectoryDO;


/**
 * <p>Encapsulates a single file of the drive, but doesn't
 * contain its
 * content. For content @link FileContentDO is used</p>
 *
 * @since 2003-04-10
 * @author Peter Illes
 * @version $Revision: 1.2 $
 */
public class DriveFileDO extends FileDO {
    /**
     * <p>The parent directory</p>
     */
    private DirectoryDO directory;

    /**
     * <p>The most recent revision of this file.</p>
     */
    private FileRevisionDO headRevision;

    /**
     * </p>the path (directory) of the file</p>
     */
    private String path;

    /**
     * <p>The parent directory</p>
     *
     * @return the parent directory
     * @hibernate.many-to-one
     */
    public final DirectoryDO getDirectory() {
        return directory;
    }

    /**
     * <p>Get the head revision  - the most recent CVS revision info
     * for this file</p>
     *
     * @return the last CVS revision info for this file
     * @hibernate.many-to-one
     */
    public FileRevisionDO getHeadRevision() {
        return this.headRevision;
    }

    /**
     * <p>Get the path of the file</p>
     *
     * @return the path of the file
     * @hibernate.property
     */
    public final String getPath() {
        return this.path;
    }

    /**
     * <p>The parent directory</p>
     *
     * @param directory the parent directory
     */
    public final void setDirectory(final DirectoryDO directory) {
        this.directory = directory;
    }

    /**
     * <p>Get the head revision  - the most recent CVS revision info
     * for this file</p>
     *
     * @param headRevision the last CVS revision info for this file
     */
    public final void setHeadRevision(final FileRevisionDO headRevision) {
        this.headRevision = headRevision;
    }

    /**
     * <p>Set the path of the file.</p>
     *
     * @param path the file path (directory)
     */
    public final void setPath(final String path) {
        this.path = path;
    }
}

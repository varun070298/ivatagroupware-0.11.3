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
 * $Log: FileDO.java,v $
 * Revision 1.3  2005/04/10 20:10:07  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:20:00  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:13  colinmacleod
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
 * Revision 1.8  2003/05/13 08:07:27  peter
 * restructured hierarchy
 *
 * Revision 1.7  2003/02/24 19:03:09  colin
 * restructured file paths
 *
 * Revision 1.6  2003/02/04 17:43:45  colin
 * copyright notice
 *
 * Revision 1.5  2003/01/20 13:46:51  peter
 * comment added
 *
 * Revision 1.2  2003/01/13 16:48:16  peter
 * beans and DOs added to the model
 *
 * Revision 1.4  2003/01/09 17:14:12  peter
 * added path field
 *
 * Revision 1.3  2002/12/30 16:03:52  peter
 * added id field
 *
 * Revision 1.2  2002/12/23 13:36:16  peter
 * completed and commented version
 *
 * Revision 1.1  2002/12/20 17:49:25  peter
 * initial  versions added to CVS
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.drive.file;

import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>Encapsulates a single file, but doesn't
 * contain its
 * content. For content @link FileContentDO is used</p>
 *
 * @since 2002-12-20
 * @author Peter Illes
 * @version $Revision: 1.3 $
 */
public class FileDO extends BaseDO {

    /**
     * <p>Comment used when working with new library attachments.</p>
     */
    private String comment;

    /**
     * <p>Stores the mime-type text identifier of the content.</p>
     */
    private String mimeType;

    /**
     * </p>The name of the file.</p>
     */
    private String name;

    /**
     * <p>The size of this file in bytes</p>
     */
    private Integer size;

    /**
     * <p>comment used when working with new library attachments</p>
     * @return comment
     * @roseuid 3EBFBE2A0122
     */
    public final String getComment() {
        return comment;
    }

    /**
     * <p>Get the mime type of the file content.</p>
     * @return clear-text mime type of the file content.
     *
     * @roseuid 3E22C256019C
     */
    public final java.lang.String getMimeType() {
        return mimeType;
    }

    /**
     * <p>gets the fileName field</p>
     * @return fileName the filename
     *
     * @roseuid 3E22C2560160
     */
    public final String getName() {
        return name;
    }

    /**
     * <p>getter for size field - the size of the file in bytes</p>
     * @return the size of the file in bytes
     *
     * @roseuid 3E22C256019B
     */
    public final Integer getSize() {
        return size;
    }

    /**
     * <p>comment used when working with new library attachments</p>
     * @param comment
     * @roseuid 3EBFBE390118
     */
    public final void setComment(final String comment) {
        this.comment = comment;
    }

    /**
     * <p>sets the mime type of the file content.</p>
     * @param mimeType clear-text mime type identifier of the file
     * content.
     *
     * @roseuid 3E22C256019D
     */
    public final void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * <p>Sets the file name field.</p>
     * @param name new file name.
     *
     * @roseuid 3E22C256015E
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * <p>setter for size field - the size of the file in bytes</p>
     * @param size the size of the file
     *
     * @roseuid 3E22C2560167
     */
    public final void setSize(final Integer size) {
        this.size = size;
    }
}

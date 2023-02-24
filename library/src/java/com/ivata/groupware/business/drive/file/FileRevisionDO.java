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
 * $Log: FileRevisionDO.java,v $
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
 * Revision 1.6  2003/08/14 08:11:17  peter
 * move to no path in db
 *
 * Revision 1.4  2003/02/24 19:03:09  colin
 * restructured file paths
 *
 * Revision 1.3  2003/02/04 17:43:46  colin
 * copyright notice
 *
 * Revision 1.2  2003/01/07 08:51:08  peter
 * getFileRevision method brought to life
 *
 * Revision 1.1  2002/12/30 16:03:33  peter
 * added to cvs
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.drive.file;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * <p>Encapsulates a revision of a file in the virtual drive</p>
 * @since 2002-12-30
 * @author Peter Illes
 * @version $Revision: 1.2 $
 */
public class FileRevisionDO implements Serializable {
    /**
     * <p>Stores the description (comment) of the revision.</p>
     *
     */
    private String comment;

    /**
     * <p>the revision 'code'</p>
     *
     */
    private String revision;

    /**
     * <p>Stores the name of the user who submitted this revision.
     * </p>
     *
     */
    private String userName;

    /**
     * <p>Stores the date and time when this  revision was made</p>
     */
    private java.util.Date revised;

    /**
     * <p>Serialize the object from the input stream provided.</p>
     *
     * @param ois the input stream to serialize the object from
     * @throws IOException thrown by
     * <code>ObjectInputStream.defaultReadObject()</code>.
     * @throws ClassNotFoundException thrown by
     * <code>ObjectInputStream.defaultReadObject()</code>.
     *
     * @roseuid 3E22C2570157
     */
    private void readObject(final ObjectInputStream ois)
        throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }

    /**
     * <p>Serialize the object to the output stream provided.</p>
     *
     * @param oos the output stream to serialize the object to
     * @throws IOException thrown by
     * <code>ObjectOutputStream.defaultWriteObject()</code>
     *
     * @roseuid 3E22C2570187
     */
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    /**
     * <p>sets the comment field</p>
     * @param comment the descriptive text of this revision
     *
     * @roseuid 3E22C2570189
     */
    public final void setComment(final String comment) {
        this.comment = comment;
    }

    /**
     * <p>gets the comment field</p>
     * @return the descriptive text of this revision
     *
     * @roseuid 3E22C257018B
     */
    public final String getComment() {
        return comment;
    }

    /**
     * <p>sets the revision number (code) of this revision</p>
     * @param headRevision the number of the last revision for this file
     *
     * @roseuid 3E22C257018C
     */
    public final void setRevision(final String revision) {
        this.revision = revision;
    }

    /**
     * <p>sets the head revision number - the most recent CVS revision
     * number
     * for this file</p>
     * @return the code of the last CVS revision for this file
     *
     * @roseuid 3E22C257018E
     */
    public final String getRevision() {
        return revision;
    }

    /**
     * <p>getter for the date and time the file was modified to this
     * revision</p>
     * @return the date of this revision
     *
     * @roseuid 3E22C257018F
     */
    public final java.util.Date getRevised() {
        return revised;
    }

    /**
     * <p>setter for the date and time the file was revised</p>
     * @param modified the date of the revision
     *
     * @roseuid 3E22C2570190
     */
    public final void setRevised(final java.util.Date revised) {
        this.revised = revised;
    }

    /**
     * <p>getter of the username of the user who submitted this
     * revision.<p>
     * @return the name of the user who submitted this revision.
     *
     * @roseuid 3E22C2570192
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * <p>sets the username of the user who submitted this revision.<p>
     * @param userName the userName of the user who submitted this
     * revision
     *
     * @roseuid 3E22C25701C2
     */
    public final void setUserName(final String userName) {
        this.userName = userName;
    }
}

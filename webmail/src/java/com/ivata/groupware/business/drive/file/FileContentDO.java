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
 * $Log: FileContentDO.java,v $
 * Revision 1.3  2005/04/10 18:47:42  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:20:00  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:13  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/07/13 20:00:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:26  colinmacleod
 * Shortened name to ivata op.
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
 * Revision 1.4  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.drive.file;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.ivata.mask.util.SerializedByteArray;


/**
 * <p>Encapsulates a file (content & mime type) for
 * download purposes.</p>
 *
 * @since 2002-11-10
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class FileContentDO implements Serializable {
    /**
     * <p>The mime-type of the content.</p>
     */
    private String mimeType;

    /**
     * <p>Stores the content of the file, in a byte array which
     * can be passed from server-side to client-side.</p>
     */
    private SerializedByteArray content;

    /**
     * <p>Intitialize the content and mime type for this DO.
     * These class attributes are <em>immutable<em> - once they have been
     * set by
     * this constrctor, you can't change them.</p>
     *
     * @param content see {@link #getContent}.
     * @param mimeType see {@link #getMimeType}.
     *
     * @roseuid 3E228C3701FD
     */
    public FileContentDO(SerializedByteArray content, String mimeType) {
        this.content = content;
        this.mimeType = mimeType;
    }

    /**
     * <p>Serialize the object from the input stream provided.</p>
     *
     * @param ois the input stream to serialize the object from
     * @throws IOException thrown by
     * <code>ObjectInputStream.defaultReadObject()</code>.
     * @throws ClassNotFoundException thrown by
     * <code>ObjectInputStream.defaultReadObject()</code>.
     *
     * @roseuid 3E228C3701F7
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
     * @roseuid 3E228C3701F9
     */
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    /**
     * <p>Get the content of the file.</p>
     *
     * @return content of the in a byte array object which can be passed
     * from
     * server-side to client-side (is serialized).
     *
     * @roseuid 3E228C3701FB
     */
    public final SerializedByteArray getContent() {
        return content;
    }

    /**
     * <p>Get the mime type of the file content.</p>
     *
     * @return clear-text mime type of the file content.
     *
     * @roseuid 3E228C3701FC
     */
    public final java.lang.String getMimeType() {
        return mimeType;
    }
}

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
 * $Log: BaseDO.java,v $
 * Revision 1.4  2005/04/29 02:48:15  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:09:42  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:37  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1  2005/03/10 19:23:03  colinmacleod
 * Moved to ivata groupware.
 *
 * Revision 1.2  2004/11/12 15:57:10  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/07/13 19:42:44  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.container.persistence;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.ivata.mask.util.StringHandling;
import com.ivata.mask.valueobject.ValueObject;

/**
 * <p>
 * This data object class is inherited by all others.
 * </p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since Mar 27, 2004
 * @version $Revision: 1.4 $
 */
public abstract class BaseDO implements Serializable, ValueObject {
    /**
     * <p>
     * Unique identifier of this data object.
     * </p>
     */
    private Integer id;

    /**
     * @see Object
     */
    public boolean equals(final Object compare) {
        return hashCode() == compare.hashCode();
    }

    /**
     * <p>
     * This default implementation simply throws an exception.
     * </p>
     *
     * @see com.ivata.mask.valueobject.ValueObject#getDisplayValue()
     * @throws UnsupportedOperationException always, with a description of the
     *     subclass for which <code>getDisplayValue</code> was not overridden.
     */
    public String getDisplayValue() {
        throw new UnsupportedOperationException(
                "ERROR: getDisplayValue not implemented for "
                + this.getClass());
    }

    /**
     * <p>
     * Override this method to return <code>getIdImpl</code> and set the
     * sequence name for hibernate.
     * </p>
     *
     * @hibernate.id
     *      generator-class = "native"
     * @return current value of the unique identifier of this dependent value
     * object.
     */
    public Integer getId() {
        return id;
    }

    /**
     * <p>
     * Implementation for ivata masks interface <code>ValueObject</code>.
     * </p>
     *
     * <p>
     * Identifies this value object uniquely. This value may only be
     * <code>null</code> for a new value object.
     * </p>
     *
     * @return unique identifier for this value object.
     */
    public final String getIdString() {
        return id == null ? null : id.toString();
    }

    /**
     * @see Object
     */
    public int hashCode() {
        String modifier = getClass().getName();
        int result = 17;
        if (getId() == null) {
            result = 37 * result;
        } else {
            result = 37 * result + (int) getId().hashCode();
        }
        result = 37 * result + (int) modifier.hashCode();
        return result;
    }

    /**
     * <p>Serialize the object from the input stream provided.</p>
     *
     * @param ois the input stream to serialize the object from
     * @throws IOException thrown by
     *  <code>ObjectInputStream.defaultReadObject()</code>.
     * @throws ClassNotFoundException thrown by
     *  <code>ObjectInputStream.defaultReadObject()</code>.
     */
    private void readObject(final ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }

    /**
     * <p>
     * Unique identifier of this data object.
     * </p>
     * @param id current value of the unique identifier of this dependent value
     * object.
     */
    public final void setId(final Integer id) {
        this.id = id;
    }
    /**
     * Set the value as a string. The string must represent a number or a
     * <code>RuntimeException</code> will be thrown.
     *
     * @param idString string representing the id of this object.
     */
    public final void setIdString(final String idString) {
        id = StringHandling.integerValue(idString);
    }


    /**
     * <p>
     * Provide helpful debugging info about this data object.
     * </p>
     *
     * @return clear text describing this object.
     */
    public String toString() {
        String className = getClass().getName();
        // don't output package - just the class name
        if (className.lastIndexOf('.') >= 0) {
            className = className.substring(className.lastIndexOf('.') + 1);
        }
        return  className
            + "(id "
            + getId()
            + ")";
    }

    /**
     * <p>Serialize the object to the output stream provided.</p>
     *
     * @param oos the output stream to serialize the object to
     * @throws IOException thrown by <code>ObjectOutputStream.defaultWriteObject(  )</code>
     */
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }
}
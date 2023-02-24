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
 * $Log: NamedDO.java,v $
 * Revision 1.3  2005/04/10 20:10:04  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:58  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:32  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/09/30 15:16:00  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.1  2004/07/13 19:41:15  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.container.persistence;

/**
 * <p>
 * Any data object which has a unique name for display in lists or choices
 * should extend this class.
 * </p>
 *
 * @since 2004-06-06
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public abstract class NamedDO extends TimestampDO implements Comparable {

    /**
     * <p>Comparison method. See if the object supplied is a person data
     * object and, if so, whether or not its contents are greater than, the same
     * or less than this one.</p>
     *
     * <p>This method sorts by the name first then the id.</p>
     *
     * @param compare the object to compare with this one.
     * @return a positive number if the object supplied in <code>compare</code>
     *     is greater than this one, <code>0</code> if they are the same,
     *     otherwise a negative number.
     */
    public int compareTo(final Object compare) {
        // first check it is non-null and the class is right
        if ((compare == null) ||
            !(this.getClass().isInstance(compare))) {
            return 1;
        }
        NamedDO namedDO = (NamedDO) compare;

        // see if the ids are the same
        if ((getId() != null)
                && (namedDO.getId() != null)) {
            return getId().compareTo(namedDO.getId());
        }

        // watch for null names
        String thisName = getName();
        String otherName = namedDO.getName();
        if (thisName == null) {
            return otherName == null ? 0 : 1;
        }

        // otherwise, compare the names
        return thisName.compareTo(otherName);
    }

    /**
     * <p>Comparison method. See if the two objects are equivalent.</p>
     *
     * @param compare the object to compare with this one.
     * @return <code>true</code> if the objects are the same.
     */
    public boolean equals(final Object compare) {
        return compareTo(compare) == 0;
    }
    /**
     * Just returns the name.
     * Refer to {@link #getName}.
     * @return Refer to {@link #getName}.
     */
    public String getDisplayValue() {
        return getName();
    }

    /**
     * <p>
     * You must override this method to output the usual name for this data
     * object.
     * </p>
     *
     * @return name of this data object, used to clearly identifiy it if no id
     * has been set yet.
     */
    public abstract String getName();

    /**
     * <p>
     * Provide helpful debugging info about this data object.
     * </p>
     *
     * @return clear text describing this object.
     */
    public String toString() {
        return super.toString()
            + " "
            + getName();
    }
}

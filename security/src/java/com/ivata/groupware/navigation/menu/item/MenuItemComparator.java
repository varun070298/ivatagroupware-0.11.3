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
 * $Log: MenuItemComparator.java,v $
 * Revision 1.2  2005/04/09 17:19:58  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:32  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.navigation.menu.item;

import java.util.Comparator;

/**
 * Sorts menu items by their <code>priority</code> properties.
 *
 * @since ivata groupware 0.10 (Feb 14, 2005)
 * @author Colin MacLeod
 * <a href="mailto:colin.macleod@ivata.com">colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */

public class MenuItemComparator implements Comparator {
    /**
     * Sorts menu items by their <code>priority</code> properties.
     * Refer to {@link Comparator}.
     *
     * @param item0Object first menu item to compare.
     * @param item1Object first menu item to compare.
     * @return Refer to {@link Comparator}.
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object item0Object, Object item1Object) {
        MenuItemDO menuItem0 = (MenuItemDO)item0Object;
        MenuItemDO menuItem1 = (MenuItemDO)item1Object;

        // first rule out whichever is null (if one is or both are)
        Integer compareNull = compareNull(menuItem0, menuItem1);
        if (compareNull != null) {
            return compareNull.intValue();
        }

        // try to use priority - if they are the same, compare id values
        compareNull = compareNull(menuItem0.getPriority(),
                menuItem1.getPriority());
        if (compareNull != null) {
            return compareNull.intValue();
        }
        int returnValue = menuItem0.getPriority().compareTo(
                menuItem1.getPriority());
        if (returnValue == 0) {
            compareNull = compareNull(menuItem0.getId(),
                    menuItem1.getId());
            if (compareNull != null) {
                return compareNull.intValue();
            }
            return menuItem0.getId().compareTo(menuItem1.getId());
        }
        return returnValue;
    }
    /**
     * Private helper. Compare two objects to see if either is null and return
     * as appropriate, otherwise return <code>null</code>.
     *
     * @param object0 first object to compare.
     * @param object1 second object to compare.
     * @return <code>0</code> if both are <code>null</code>, <code>1</code> if
     * only <code>object0</code> is <code>null</code>,   <code>-1</code> if only
     * <code>object1</code> is <code>null</code>, otherwise <code>null</code>.
     */
    private Integer compareNull(final Object object0, final Object object1) {
        if (object0 == null) {
            if ((object1 == null))  {
                return new Integer(0);
            }
            return new Integer(1);
        } else if (object1 == null) {
            if ((object0 == null))  {
                return new Integer(0);
            }
            return new Integer(-1);
        }
        return null;
    }
}

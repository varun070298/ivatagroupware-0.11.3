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
 * $Log: SearchResult.java,v $
 * Revision 1.2  2005/04/09 17:19:56  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:38  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/08/01 11:40:29  colinmacleod
 * Moved search classes to separate subproject.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.search.result;

import com.ivata.groupware.business.search.item.SearchItemDO;

/**
 * <p>
 * Represents a single result as returned by the search engine.
 * </p>
 *
 * @since Jul 31, 2004
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */

public class SearchResult implements Comparable {
    /**
     * <p>
     * The search item which matched a query.
     * </p>
     */
    private SearchItemDO item;

    /**
     * <p>
     * Indicates how well the item matched the query.
     * </p>
     */
    private float weight;
    /**
     * <p>
     * <code>Comparable</code> method used to sort items. This compares the
     * weight of the two results.
     * </p>
     *
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(final Object compare) {
        SearchResult otherResult = (SearchResult) compare;
        if (otherResult.weight == weight) {
            return 0;
        }
        if (otherResult.weight > weight) {
            return 1;
        }
        return -1;
    }

    /**
     * <p>
     * The search item which matched a query.
     * </p>
     *
     * @return Returns the item.
     */
    public final SearchItemDO getItem() {
        return item;
    }
    /**
     * <p>
     * Indicates how well the item matched the query.
     * </p>
     *
     * @return Returns the weight.
     */
    public final float getWeight() {
        return weight;
    }
    /**
     * <p>
     * The search item which matched a query.
     * </p>
     *
     * @param item The item to set.
     */
    public final void setItem(final SearchItemDO item) {
        this.item = item;
    }
    /**
     * <p>
     * Indicates how well the item matched the query.
     * </p>
     *
     * @param weight The weight to set.
     */
    public final void setWeight(final float weight) {
        this.weight = weight;
    }

}

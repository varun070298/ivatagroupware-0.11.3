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
 * $Log: SearchItemContentDO.java,v $
 * Revision 1.4  2005/04/29 02:48:20  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:09:48  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:55  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:38  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/09/30 15:01:44  colinmacleod
 * Fixed hibernate tags.
 *
 * Revision 1.1  2004/08/01 11:40:28  colinmacleod
 * Moved search classes to separate subproject.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.search.item.content;

import com.ivata.groupware.business.search.item.SearchItemDO;
import com.ivata.groupware.container.persistence.BaseDO;

/**
 * <p>
 * Each item searched for in <strong>ivata groupware</strong> can have different
 * content associated with it. Whether it is a page in a library item, or a
 * comment about a library item, for example, it is more efficient to index
 * these separately so they can be updated separately when they change.
 * </p>
 *
 * @since Aug 1, 2004
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 *
 * @hibernate.class
 *      table="search_item_content"
 * @hibernate.cache
 *      usage="read-write"
 */
public class SearchItemContentDO extends BaseDO {

    /**
     * <p>
     * This refers to the item which contains this content.
     * </p>
     */
    private SearchItemDO item;
    /**
     * <p> Unique identifier of the object being indexed. </p>
     */
    private Integer targetId;

    /**
     * <p> This is a text to describe the type of the object being indexed. In
     * <strong>ivata groupware</strong>, this always refers to the class of the
     * indexed object. </p>
     */
    private String type;

    /**
     * <p>
     * This refers to the item which contains this content.
     * </p>
     *
     * @return Returns the item.
     * @hibernate.many-to-one
     *      column="search_item"
     */
    public final SearchItemDO getItem() {
        return item;
    }

    /**
     * <p>
     * Unique identifier of the object being indexed.
     * </p>
     *
     * @return Returns the targetId.
     * @hibernate.property
     *      column="target_id"
     */
    public final Integer getTargetId() {
        return targetId;
    }

    /**
     * <p>
     * This is a text to describe the type of the object being indexed. In
     * <strong>ivata groupware</strong>, this always refers to the class of the
     * indexed object.
     * </p>
     *
     * @return Returns the type.
     * @hibernate.property
     */
    public final String getType() {
        return type;
    }
    /**
     * <p>
     * This refers to the item which contains this content.
     * </p>
     *
     * @param item The item to set.
     */
    public final void setItem(final SearchItemDO item) {
        this.item = item;
    }

    /**
     * <p>
     * Unique identifier of the object being indexed.
     * </p>
     *
     * @param targetId The targetId to set.
     */
    public final void setTargetId(final Integer targetId) {
        this.targetId = targetId;
    }

    /**
     * <p>
     * This is a text to describe the type of the object being indexed. In
     * <strong>ivata groupware</strong>, this always refers to the class of the
     * indexed object.
     * </p>
     *
     * @param type The type to set.
     */
    public final void setType(final String type) {
        this.type = type;
    }
}

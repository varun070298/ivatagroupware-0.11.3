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
 * $Log: SearchItemDO.java,v $
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
 * Revision 1.2  2004/09/30 15:02:08  colinmacleod
 * Fixed hibernate tags.
 *
 * Revision 1.1  2004/08/01 11:40:30  colinmacleod
 * Moved search classes to separate subproject.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.search.item;


import java.util.Set;

import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>
 * Each item which can be queried as a result of a search is abstracted by an
 * instance of this class.
 * </p>
 *
 * @since 2004-07-31
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 *
 * @hibernate.class
 *      table="search_item"
 * @hibernate.cache
 *      usage="read-write"
 */
public class SearchItemDO  extends BaseDO {
    /**
     * <p>
     * Extra string to describe the category or topic associated with the indexed
     * object. Used in conjunction with <code>type</code> and
     * <code>targetId</code> to uniquely identify the target.
     * </p>
     */
    private String category;

    /**
     * <p>
     * Refers to all the indexed contents of this item.
     * </p>
     */
    private Set contents;

    /**
     * <p>
     * Unique identifier of the object being indexed.
     * </p>
     */
    private Integer targetId;
    /**
     * <p>
     * This is a text to describe the type of the object being indexed. In
     * <strong>ivata groupware</strong>, this always refers to the class of the
     * indexed object.
     * </p>
     */
    private String type;

    /**
     * <p>
     * Extra string to describe the category or topic associated with the indexed
     * object. Used in conjunction with <code>type</code> and
     * <code>targetId</code> to uniquely identify the target.
     * </p>
     *
     * @return Returns the category.
     * @hibernate.property
     */
    public final String getCategory() {
        return category;
    }
    /**
     * <p>
     * Refers to all the indexed contents of this item.
     * </p>
     *
     * @hibernate.set
     *      cascade="all"
     * @hibernate.collection-key
     *      column="search_item"
     * @hibernate.collection-one-to-many
     *      class="com.ivata.groupware.business.library.page.PageDO"
     * @return Returns the contents.
     */
    public final Set getContents() {
        return contents;
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
     * Extra string to describe the category or topic associated with the indexed
     * object. Used in conjunction with <code>type</code> and
     * <code>targetId</code> to uniquely identify the target.
     * </p>
     *
     * @param category The category to set.
     */
    public final void setCategory(final String category) {
        this.category = category;
    }
    /**
     * <p>
     * Refers to all the indexed contents of this item.
     * </p>
     *
     * @param contents The contents to set.
     */
    public final void setContents(final Set contents) {
        this.contents = contents;
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

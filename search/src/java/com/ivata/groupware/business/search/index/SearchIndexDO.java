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
 * $Log: SearchIndexDO.java,v $
 * Revision 1.3  2005/04/10 20:09:47  colinmacleod
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
 * Revision 1.1  2004/08/01 11:40:29  colinmacleod
 * Moved search classes to separate subproject.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.search.index;


import com.ivata.groupware.business.search.item.content.SearchItemContentDO;
import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>
 * Each word within an item has an index associated with it. This data object
 * stores that index.
 * </p>
 *
 * @since 2004-07-31
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table="search_index"
 * @hibernate.cache
 *      usage="read-write"
 */
public class SearchIndexDO  extends BaseDO {
    /**
     * <p>
     * This is the content in the item which this index matches.
     * </p>
     */
    private SearchItemContentDO content;
    /**
     * <p>
     * Indicates how well the query applies to this result.
     * </p>
     */
    private float weight;
    /**
     * <p>
     * Word within the object which is indexed.
     * </p>
     */
    private String word;
    /**
     * <p>
     * This is the content in the item which this index matches.
     * </p>
     *
     * @return Returns the item.
     * @hibernate.many-to-one
     */
    public final SearchItemContentDO getContent() {
        return content;
    }

    /**
     * <p>
     * Indicates how well the query applies to this result.
     * </p>
     *
     * @return Returns the weight.
     * @hibernate.property
     */
    public final float getWeight() {
        return weight;
    }

    /**
     * <p>
     * Word within the object which is indexed.
     * </p>
     *
     * @return Returns the word.
     * @hibernate.property
     */
    public final String getWord() {
        return word;
    }
    /**
     * <p>
     * This is the content in the item which this index matches.
     * </p>
     *
     * @param content Content of the item which this index matches.
     */
    public final void setContent(final SearchItemContentDO content) {
        this.content = content;
    }

    /**
     * <p>
     * Indicates how often the word occurs within this object.
     * </p>
     *
     * @param weight The weight to set.
     */
    public final void setWeight(final float weight) {
        this.weight = weight;
    }
    /**
     * <p>
     * Word within the object which is indexed.
     * </p>
     *
     * @param word The word to set.
     */
    public final void setWord(final String word) {
        this.word = word;
    }
}

/*
 * Copyright (c) 2001 - 2005 ivata limited.
 * All rights reserved.
 * ---------------------------------------------------------
 * ivata groupware may be redistributed under the GNU General Public
 * License as published by the Free Software Foundation;
 * version 2 of the License.
 *
 * These programs are free software; you can redistribute them and/or
 * modify them under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
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
 * ---------------------------------------------------------
 * $Log: SearchStopwordDO.java,v $
 * Revision 1.1  2005/04/30 13:06:00  colinmacleod
 * The DO is not yet used, but it ensures Hibernate
 * creates the associated table.
 *
 * ---------------------------------------------------------
 */
package com.ivata.groupware.business.search.stopword;

import org.apache.log4j.Logger;

import com.ivata.groupware.container.persistence.BaseDO;

/**
 * This table stores 'stopwords' which are treated as punctuation when
 * indexing the search engine.
 * 
 * @since ivata groupware 0.11 (29-Apr-2005)
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.1 $
 * 
 * @hibernate.class
 *      table="search_stopword"
 */
public class SearchStopwordDO extends BaseDO {
    /**
     * Logger for this class.
     */
    private static final Logger logger = Logger
            .getLogger(SearchStopwordDO.class);

    /**
     * Refer to {@link #getWord}.
     */
    private String word;
    /**
     * The stopword.
     *
     * @return Returns the stopword.
     * @hibernate.property
     */
    public String getWord() {
        return word;
    }
    /**
     * Refer to {@link #getWord}.
     * @param wordParam Refer to {@link #getWord}.
     */
    public void setWord(String wordParam) {
        if (logger.isDebugEnabled()) {
            logger.debug("Setting word. Before '" + word + "', after '"
                    + wordParam + "'");
        }
        word = wordParam;
    }
}

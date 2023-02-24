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
 * $Log: SearchEngine.java,v $
 * Revision 1.4  2005/04/29 02:48:20  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
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
 * Revision 1.2  2004/11/12 15:57:17  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/08/01 11:40:30  colinmacleod
 * Moved search classes to separate subproject.
 *
 * Revision 1.1  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.search;

import java.util.SortedSet;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.mask.util.SystemException;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Apr 9, 2004
 * @version $Revision: 1.4 $
 */
public interface SearchEngine {
    /**
     * <p>removes an item or part of an item from index</p>
     * @param type one of {@link: SearchConstant SearchContant}s values
     * @param id the id of the document, when null, all entries for {@see:item
     * item } will be removed
     * @param item the item id of the library item which this document relates to
     */
    public void removeFromIndex(SecuritySession securitySession,
            final String type,
            final String id,
            final String category)
        throws SystemException;
    /**
     * <p>searches the index for the given query, returns appropriate DOs as a
     * <code>Vector</code>, most relevant documents first</p>
     * @param query the query of one or more space-separated words
     * @param topic the id of the topic of the wanted documents, <code>null</code>
     * when the all topics should be searched
     * @param filter <code>Collection</code> of {@link: SearchConstant SearchContant}s,
     * describing what parts of the system to search, when <code>null</code>,
     * search is performed on all kinds of documents
     * @return <code>Vector</code> of appropriate DOs,
     * most relevant documents first
     *
     * @ejb.interface-method
     *      view-type = "both"
     */
    public SortedSet search(final SecuritySession securitySession,
            final String query)
        throws SystemException;
    /**
     * <p>stems the text content and indexes the document with the provided type
     * {@see SearchConstants} and id</p>
     * @param integerParam the id of the document - subpart of a library item,
     * used as reference when searching
     * @param type one of {@link: SearchConstant SearchContant}s values
     * @param integer2Param TODO
     * @param contentType TODO
     * @param text the content (HTML is converted to plain text) of the document
     * @param topic the id of the topic of this document, <code>null</code> when
     * this document has no topic asscociated
     * @param item the item id of the library item which this document relates to
     * @param textType format of the text, see
     * {@link com.ivata.intranet.jsp.format.FormatConstants}
     */
    public void updateIndex(SecuritySession securitySession,
            Integer integerParam,
            final String type,
            final String category,
            Integer integer2Param,
            final String contentType,
            final String text, int format)
            throws SystemException;
}
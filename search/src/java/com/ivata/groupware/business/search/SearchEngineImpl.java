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
 *                  http:// www.ivata.com/contact.jsp
 * -----------------------------------------------------------------------------
 * $Log: SearchEngineImpl.java,v $
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
 * Revision 1.4  2004/11/12 18:16:06  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 15:57:17  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/09/30 15:03:02  colinmacleod
 * Removed id dispenser requirement.
 * Added log4j,
 *
 * Revision 1.1  2004/08/01 11:40:30  colinmacleod
 * Moved search classes to separate subproject.
 *
 * Revision 1.1  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/10 19:57:24  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:45  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.6  2004/01/16 14:48:02  jano
 * changing name of DataSource
 *
 * Revision 1.5  2004/01/12 14:01:03  jano
 * fixing bugs
 *
 * Revision 1.4  2003/10/28 13:16:15  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.3  2003/10/15 14:24:59  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 14:16:54  colin
 * fixing for XDoclet
 *
 * Revision 1.6  2003/05/29 07:51:35  peter
 * removed quotes from sql - tablename, fieldname
 *
 * Revision 1.5  2003/05/28 14:50:09  jano
 * fixing SQL for hsqlDB
 *
 * Revision 1.4  2003/05/28 11:23:04  peter
 * removed sequence from search
 *
 * Revision 1.3  2003/05/01 12:13:22  jano
 * tidy up names of sequeneces
 *
 * Revision 1.2  2003/03/13 09:54:08  peter
 * added apostrophy as separator character
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.16  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.15  2002/10/31 16:05:17  peter
 * the list of separator characters extended
 *
 * Revision 1.14  2002/10/31 10:25:39  peter
 * fixed bug in search (lowercase the query)
 *
 * Revision 1.13  2002/10/31 08:26:33  peter
 * fixed the bug with jndi prefixes
 *
 * Revision 1.12  2002/10/28 16:27:44  peter
 * fixed the HTML text  extracting problem
 *
 * Revision 1.11  2002/10/25 14:52:40  peter
 * fixed SearchBean search method parameters
 *
 * Revision 1.10  2002/10/24 15:52:03  peter
 * indexing works, search results need to be put through right yet
 *
 * Revision 1.9  2002/10/24 09:36:21  peter
 * minor changes
 *
 * Revision 1.8  2002/10/22 08:25:37  peter
 * fixed topic null handling in addToIndex
 *
 * Revision 1.7  2002/10/17 16:25:42  peter
 * the input type parameters in removeFromIndex of SearchBean fixed
 *
 * Revision 1.6  2002/10/16 15:39:00  peter
 * fixed addToIndex input parameter types in SearchBean
 *
 * Revision 1.5  2002/09/19 15:51:58  peter
 * added removeFromIndex, works in theory
 *
 * Revision 1.4  2002/09/19 15:12:25  peter
 * changes in search.SearchBean
 *
 * Revision 1.3  2002/09/19 10:48:51  peter
 * *** empty log message ***
 *
 * Revision 1.1  2002/09/17 15:20:10  peter
 * added to repository
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.search.index.SearchIndexDO;
import com.ivata.groupware.business.search.item.SearchItemDO;
import com.ivata.groupware.business.search.item.content.SearchItemContentDO;
import com.ivata.groupware.business.search.result.SearchResult;
import com.ivata.groupware.container.persistence.QueryPersistenceManager;
import com.ivata.groupware.web.format.SanitizerFormat;
import com.ivata.mask.persistence.FinderException;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.format.FormatConstants;
import com.ivata.mask.web.format.HTMLFormatter;



/**
 * <p>Performs indexing of documents and search</p>
 *
 * @since 2002-09-17
 * @author Peter Illes
 * @version $Revision: 1.4 $
 */
public class SearchEngineImpl implements SearchEngine {
    /**
     * <p>
     * <strong>Log4J</strong> logger.
     * </p>
     */
    private static Logger log = Logger.getLogger(SearchEngineImpl.class);


    /**
     * <p>the separator characters used in <code>StringTokenizer</code>s.</p>
     */
    private final static String SEPARATORS = " '`%*+={}[])&#-_,;/<>|:.!?\t\n\r\f";

    /**
     * This datasource will be used to execute queries to search for library
     * items.
     */
    private QueryPersistenceManager persistenceManager;

    SanitizerFormat sanitizer = new SanitizerFormat();

    /**
     * Construct a search engine implementation.
     *
     * @param library used to retrieve items and topics.
     */
    public SearchEngineImpl(QueryPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    /**
     * <p>checks whether a word is a frequently used english word, not worth to
     * index and therefore not worth to search for it too
     * @param connection the db connection passed
     * @param word the word to check
     * @return <code>true</code> when the word appears in the list of
     * frequently used english words (stopwords)
     */
    private boolean isStopWord(final Connection connection,
            final String word)
            throws SystemException {
        // quotes and slashes not allowed in stop word!
        if ((word.indexOf('\'') != -1)
                || (word.indexOf('\\') != -1)) {
            return false;
        }
        try {
            Statement statementReturn = connection.createStatement();

            ResultSet results = statementReturn.executeQuery("SELECT word FROM search_stopword WHERE word='" + word + "'");
            if (results.next()) {
                statementReturn.close();
                return true;
            } else {
                statementReturn.close();
                return false;
            }
        } catch (SQLException e) {
            throw new SystemException(e);
        }
    }

    /**
     * <p>removes an item or part of an item from index</p>
     * @param type one of {@link: SearchConstant SearchContant}s values
     * @param id the id of the document, when null, all entries for {@see:item
     * item } will be removed
     * @param item the item id of the library item which this document relates to
     *
     * @ejb.interface-method
     *      view-type = "local"
     */
    public void removeFromIndex(final SecuritySession securitySession,
            final String type,
            final String id,
            final String category)
            throws SystemException {
        PersistenceSession persistenceSession
                = persistenceManager.openSession(securitySession);
        SearchItemDO item;
        try {
            item = (SearchItemDO) persistenceManager.findInstance(persistenceSession,
                    "searchItemByTargetIdTypeCategory",
                    new Object[]{id, category, type});
            // clear out all previous index entries for this item
            persistenceManager.removeAll(persistenceSession,
                    "searchIndexByItemId",
                    new Object [] {item.getId()});
            persistenceManager.remove(persistenceSession, item);
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }

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
     */
    public SortedSet search(final SecuritySession securitySession,
            final String query)
            throws SystemException {
        if (query == null || query.equals("")) {
            return null;
        }

        SortedSet results = Collections.synchronizedSortedSet(new TreeSet());
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            Map resultsMap = new HashMap();
            // process words in the query one by one
            for(StringTokenizer tokenizer = new StringTokenizer(query, SEPARATORS);tokenizer.hasMoreTokens();) {

                String currentWord = tokenizer.nextToken();
                currentWord = currentWord.toLowerCase();

                // if the current word from the query is a stopword, skip it
                Connection connection = persistenceSession.getConnection();
                if (isStopWord(connection, currentWord)) {
                    continue;
                }

                currentWord = PorterStemmer.stem(currentWord);

                // if the stemmer returned "", skip the current word of the query
                if (currentWord=="") {
                    continue;
                }

                List indexes = persistenceManager.find(persistenceSession,
                        "searchIndexByWord",
                        new Object[] {currentWord});
                Iterator iterator = indexes.iterator();
                while (iterator.hasNext()) {
                    SearchIndexDO index = (SearchIndexDO) iterator.next();
                    SearchItemDO item = index.getContent().getItem();
                    Integer id = item.getId();

                    // if there is none already, this is a new result
                    SearchResult result = (SearchResult) resultsMap.get(id);
                    if (result == null) {
                        result = new SearchResult();
                        result.setItem(item);
                        result.setWeight(index.getWeight());
                        results.add(result);
                        resultsMap.put(id, result);
                    } else {
                        // increase the weight of this result
                        result.setWeight(result.getWeight() + index.getWeight());
                    }
                }
            }

        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
        return results;
    }

    /**
     * <p>stems the text content and indexes the document with the provided type
     * {@see SearchConstants} and id</p>
     * @param id the id of the document - subpart of a library item,
     * used as reference when searching
     * @param type in <strong>ivata groupware</strong>, always indicates the class of
     *     the object to be stored.
     * @param textParam the content (HTML is converted to plain text) of the
     * document
     * @param format format of the text, see
     * {@link com.ivata.mask.web.format.FormatConstants}
     * @ejb.interface-method
     *      view-type = "local"
     */
    public void updateIndex(SecuritySession securitySession,
            final Integer id,
            final String type,
            final String category,
            final Integer contentId,
            final String contentType,
            final String textParam,
            final int format)
            throws SystemException {
        String text = textParam;
        // this Hashtable will contain the stem-occurences pairs
        Hashtable stems = new Hashtable();

        // if the text is HTML, we need to extract plain text from it
        if (format == FormatConstants.FORMAT_HTML) {
            if (log.isDebugEnabled()) {
                log.debug("Converting HTML into plain text.");
            }
            HTMLFormatter formatter = new HTMLFormatter();

            sanitizer.setTextOnly(true);
            formatter.add(sanitizer);
            String oldText = text;
            text = formatter.format(text);
            if (log.isDebugEnabled()) {
                log.debug("Converted "
                        + oldText.getBytes().length
                        + " bytes HTML into "
                        + text.getBytes().length
                        + " bytes plain text.");
            }
            oldText = null;
        }

        // split up the text to fragments (hopefully words)
        StringTokenizer tokenizer = new StringTokenizer(text, SEPARATORS);

        String newWord;
        int counter = 0;

        PersistenceSession persistenceSession = persistenceManager
                .openSession(securitySession);
        try {
            // looping through all tokens (words)...
            if (log.isDebugEnabled()) {
                log.debug("Locating stemmed words in text ("
                        + text.getBytes().length
                        + " bytes).");
            }
            while (tokenizer.hasMoreTokens()) {
                counter++;

                newWord = tokenizer.nextToken();

                newWord = newWord.toLowerCase();

                // checking whether it's a stopword, when not, stem it
                if (!isStopWord(persistenceSession.getConnection(), newWord)) {
                    newWord = PorterStemmer.stem(newWord);
                    // if there's an output from the stemmer, add it to the collection
                    // or increment the occurences if there was such word in the text
                    if (!(newWord.equals(""))) {
                        // if the word is already here, increment the occurences
                        if (stems.containsKey(newWord)) {
                            Integer occurences = (Integer) stems.get(newWord);
                            stems.put(newWord, new Integer(occurences.intValue() + 1));
                        // if it's a new word for this text, put it to the Hashtable
                        } else {
                            stems.put(newWord, new Integer(1));
                        }
                    }
                }
            }

            // a small security check :>)
            if (counter == 0) {
                if (log.isDebugEnabled()) {
                    log.debug("Found no valid stems (text empty or only contains stop words.)");
                }
                return;
            }

            // Now we have the stems with their occurences, let's put it to index,
            // each occurence divided by the total word count in the text
            // first try to see if this search item already exists
            SearchItemDO item = null;
            SearchItemContentDO content;
            try {
                item = (SearchItemDO) persistenceManager.findInstance(persistenceSession,
                        "searchItemByTargetIdTypeCategory",
                        new Object[]{id, category, type});
                content = (SearchItemContentDO) persistenceManager.findInstance(persistenceSession,
                        "searchItemContentByTargetIdType",
                        new Object[]{contentId, contentType});
                if (log.isDebugEnabled()) {
                    log.debug("Removing previous index contents for this search item content (target id "
                            + contentId
                            + ").");
                }
                // clear out all previous index entries for this item content
                persistenceManager.removeAll(persistenceSession,
                        "searchIndexByContentId",
                        new Object [] {content.getId()});
            } catch (FinderException finderException) {
                // this could be finder exception on either the item or the
                // content
                if (item == null) {
                    item = new SearchItemDO();
                    item.setCategory(category);
                    item.setTargetId(id);
                    item.setType(type);
                    if (log.isDebugEnabled()) {
                        log.debug("Creating new search item (target id "
                                + id
                                + ").");
                    }
                    persistenceManager.add(persistenceSession, item);
                }
                if (log.isDebugEnabled()) {
                    log.debug("Creating new search item content (target id "
                            + contentId
                            + ").");
                }
                content = new SearchItemContentDO();
                content.setItem(item);
                content.setTargetId(contentId);
                content.setType(contentType);
                persistenceManager.add(persistenceSession, content);
            }



            Set keySet = stems.keySet();
            if (log.isDebugEnabled()) {
                log.debug("Adding index for "
                        + keySet.size()
                        + " stemmed words.");
            }
            Iterator iterator = keySet.iterator();
            while(iterator.hasNext()) {
                SearchIndexDO index = new SearchIndexDO();
                index.setContent(content);

                // the current stem, it's also the key in stems Hashlist
                String currentStem = (String) iterator.next();

                // the stemmed word
                index.setWord(currentStem);


                // the stemmed word is the key to find its occurences, then divided
                // by the total count of words in the doc
                index.setWeight((((float)((Integer) stems.get(currentStem)).intValue()))/counter);

                if (log.isDebugEnabled()) {
                    log.debug(currentStem
                            + " --> "
                            + index.getWeight());
                }

                persistenceManager.add(persistenceSession, index);
            }
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
    }
}

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
 * $Log: LibraryItemDO.java,v $
 * Revision 1.4  2005/04/26 15:18:45  colinmacleod
 * Renamed Faq to FAQ.
 *
 * Revision 1.3  2005/04/10 20:31:59  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:45  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:59  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/07/13 19:47:28  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:58:40  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.4  2004/01/19 21:13:14  colin
 * Removed minutes for ivata groupware v0.9
 *
 * Revision 1.3  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/02/24 19:09:22  colin
 * moved to business
 *
 * Revision 1.3  2003/02/04 17:43:47  colin
 * copyright notice
 *
 * Revision 1.2  2002/07/02 14:56:31  colin
 * tried to fix jbuilder EJB designer
 *
 * Revision 1.1  2002/06/18 11:40:13  colin
 * first version of library
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.item;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.ivata.groupware.business.drive.file.FileContentDO;
import com.ivata.groupware.business.library.topic.TopicDO;
import com.ivata.groupware.container.persistence.TimestampDO;

/**
 * <p>Represents a single item within the library. The item can be one of six
 * types:<br/>
 * <ul>
 *   <li>document</li>
 *   <li>Faq (Frequently Asked Question)</li>
 *   <li>memo</li>
 *   <li>note</li>
 *   <li>news item</li>
 *   <li>meeting agenda/minutes</li>
 * </ul></p>
 *
 * <p>This is a dependent value class, used to pass data back from the.</p>
 * {@link LibraryItemBean LibraryItemBean} to a client application.</p>
 *
 * <p><strong>Note:</strong> This class provides data from {@link LibraryItemBean LibraryItemBean}.
 * This is no local copy of the bean class, however, and changes here
 * will not be automatically reflected in {@link LibraryItemBean LibraryItemBean}.</p>
 *
 * @since   2002-06-14
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 * @see LibraryItemBean
 *
 * @hibernate.class
 *      table="library_item"
 */
public class LibraryItemDO extends TimestampDO {

    /**
     * <p>This method converts an XML file to a LibraryItemDO.</p>
     *
     * @param fileContent contains the XML to be converted
     * @return valid library item.
     */
    public static LibraryItemDO convertFromFile(FileContentDO fileContent) {
        XMLDecoder decoder = new XMLDecoder(fileContent.getContent().getInputStream());
        LibraryItemDO itemDO = (LibraryItemDO) decoder.readObject();

        decoder.close();
        return itemDO;
    }

    /**
     * <p>If the item is frequently asked questions, store all the categoreies
     * of questions here.</p>
     */
    private List fAQCategories;

    /**
     * <p>Store the directory to store images in for this library item.</p>
     */
    private String imageDirectory;

    /**
     * <p>Store all the pages of this item, if it has pages.</p>
     */
    private List pages;

    /**
     * <p>Store the summary text. This summary will appear on the noticeboard page,
     * giving an overview of the content of the item.</p>
     */
    private String summary;


    /**
     * <p>Store the title for this item. Is usually used in the titlebar of the
     * window which displays the item.</p>
     */
    private String title;

    /**
     * <p>Topics are used to group items together and apply rights.</p>
     */
    private TopicDO topic;
    /**
     * <p>Store the type of the item, to one of the values found in {@link LibraryItemConstants
     * LibraryItemConstants}.</p>
     */
    private Integer type;

    /**
     * <p>If the item is frequently asked questions, set all the categoreies
     * of questions here.</p>
     *
     * @return fAQCategories <code>List</code> of <code>FAQCategoryDO</code>
     * instances.
     *
     * @hibernate.bag
     *      cascade="all"
     * @hibernate.collection-key
     *      column="library_item"
     * @hibernate.collection-one-to-many
     *      class="com.ivata.groupware.business.library.faq.category.FAQCategoryDO"
     */
    public List getFAQCategories() {
        return fAQCategories;
    }
    /**
     * <p>Get the directory to store images in for this library item.</p>
     *
     * @return the directory to store images in for this library item.
     * @hibernate.property
     *      column="image_directory"
     */
    public final String getImageDirectory() {
        return imageDirectory;
    }

    /**
     * <p>Set all the pages of this item, as a <code>List</code> of
     * <code>PageDO</code> instances.</p>
     *
     * <p>
     * <strong>Note:</strong> not all types have pages. Use this method only if this
     * library item represents a document, news item or meeting minutes.</p>
     *
     * @return page texts as a set of DO instances.
     *
     * @hibernate.bag
     *      cascade="all"
     * @hibernate.collection-key
     *      column="library_item"
     * @hibernate.collection-one-to-many
     *      class="com.ivata.groupware.business.library.page.PageDO"
     */
    public List getPages() {
        return pages;
    }

    /**
     * <p>Set the summary text. This summary will appear on the noticeboard page,
     * giving an overview of the content of the item.</p>
     *
     * @return the summary text.
     * @hibernate.property
     */
    public final String getSummary() {
        return summary;
    }

    /**
     * <p>Set the title for this item. Is usually used in the titlebar of the
     * window which displays the item.</p>
     *
     * @return the title for this item. Is usually used in the titlebar of the
     * window which displays the item.
     * @hibernate.property
     */
    public final String getTitle() {
        return title;
    }

    /**
     * <p>Topics are used to group items together and apply rights.</p>
     *
     * @return current value of topic.
     * @hibernate.many-to-one
     *      column="library_topic"
     */
    public final TopicDO getTopic() {
        return topic;
    }

    /**
     * <p>Get the type of the item, as one of the values found in {@link LibraryItemConstants
     * LibraryItemConstants}.</p>
     * @hibernate.property
     */
    public final Integer getType() {
        return type;
    }

    /**
     * <p>This method will convert a library item to a to a file.
     * In this case it will be an XML file.</p>
     *
     * @return name of the file this library item was saved to.
     */
    public String saveToFile()
            throws IOException {
        String returnFileName = "";

        // create temporary file
        File tmpFile = File.createTempFile("drive", "xml");

        returnFileName = tmpFile.getPath();
        // write itemDO to file
        FileOutputStream file = new FileOutputStream(returnFileName);
        XMLEncoder encoder = new XMLEncoder(file);

        encoder.writeObject(this);
        encoder.close();
        file.close();

        return returnFileName;
    }

    /**
     * <p>If the item is frequently asked questions, set all the categoreies
     * of questions here.</p>
     *
     * @param fAQCategories <code>List</code> of <code>FAQCategoryDO</code>
     * instances.
     */
    public final void setFAQCategories(final List fAQCategories) {
        this.fAQCategories = fAQCategories;
    }

    /**
     * <p>Set the directory to store images in for this library item.</p>
     *
     * @param imageDirectory new value of image directory.
     */
    public final void setImageDirectory(final String imageDirectory) {
        this.imageDirectory = imageDirectory;
    }

    /**
     * <p>Set all the pages of this item, as a <code>List</code> of
     * <code>PageDO</code> instances.</p>
     *
     * <p>
     * <strong>Note:</strong> not all types have pages. Use this method only if this
     * library item represents a document, news item or meeting minutes.</p>
     *
     * @param pages new value of page texts as a set of DO instances.
     */
    public final void setPages(final List pages) {
        this.pages = pages;
    }

    /**
     * <p>Set the summary text. This summary will appear on the noticeboard page,
     * giving an overview of the content of the item.</p>
     *
     * @param summary summary of the item's contents.
     */
    public final void setSummary(final String summary) {
        this.summary = summary;
    }


    /**
     * <p>Set the title for this item. Is usually used in the titlebar of the
     * window which displays the item.</p>
     *
     * @param title new value of title.
     */
    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * <p>Topics are used to group items together and apply rights.</p>
     *
     * @param topic new value of topic.
     */
    public final void setTopic(final TopicDO topic) {
        this.topic = topic;
    }

    /**
     * <p>Set the type of the item, to one of the values found in {@link LibraryItemConstants
     * LibraryItemConstants}.</p>
     */
    public final void setType(final Integer type) {
        this.type = type;
    }

}

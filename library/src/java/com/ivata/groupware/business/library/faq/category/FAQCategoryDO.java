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
 * $Log: FAQCategoryDO.java,v $
 * Revision 1.2  2005/04/29 02:48:16  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.1  2005/04/26 15:21:54  colinmacleod
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
 * Revision 1.1.1.1  2004/01/27 20:58:39  colinmacleod
 * Moved ivata openportal to SourceForge..
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
 * Revision 1.2  2002/10/24 14:35:39  peter
 * search indexing implemented, 2 new fields added to DO
 *
 * Revision 1.1  2002/06/28 13:19:54  colin
 * first release of the library
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.faq.category;

import java.util.List;

import com.ivata.groupware.business.library.item.LibraryItemDO;
import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>Contains one category of questions and answers in a frequently asked
 * questions library item.</p>
 *
 * @since   2002-06-28
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 *
 * @hibernate.class
 *      table="library_faq_category"
 */
public class FAQCategoryDO extends BaseDO {

    /**
     * <p>Text describing the function of this category. This is
     * optional.</p>
     */
    private String description;

    /**
     * <p>All of the questions/answers in this category.</p>
     */
    private List fAQs;

    /**
     * <p>Store the library Item, the owner of this category.</p>
     */
    private LibraryItemDO libraryItem;
    /**
     * <p>The name for this category. This should be a clear-text name
     * which can contain punctuation characters and spaces. This name should
     * uniquely identify the category within the library item it is in.</p>
     */
    private String name;

    /**
     * <p>Comparison method. See if the object supplied is a faq category
     * dependent object and, if so, whether or not its contents are the same as
     * this one. Only the <code>id</code> fields are compared.</p>
     *
     * @param compare the object to compare with this one.
     * @return <code>true</code> if the object supplied in <code>compare</code>
     *     is effectively the same as this one, otherwise false.
     */
    public boolean equals(final Object compare) {
        // first check it is non-null and the class is right
        if ((compare == null) || !(this.getClass().isInstance(compare))) {
            return false;
        }

        FAQCategoryDO categoryDO = (FAQCategoryDO) compare;
        Integer id = getId();
        Integer categoryId = categoryDO.getId();

        // check that the ids are the same
        return (((id == null)
                ? (categoryId == null)
                : id.equals(categoryId)));
    }

    /**
     * <p>Get the text describing the function of this category. This is
     * optional and may be set to null.</p>
     *
     * @return optional text describing the function and purpose of the
     *     category.
     * @hibernate.property
     */
    public final String getDescription() {
        return description;
    }

    /**
     * <p>Get all of the questions and answers in this category as a
     * <code>List</code> of <code>FAQDO</code> instances.</p>
     *
     * @return all of the questions and answers in this category as a
     * <code>List</code> of <code>FAQDO</code> instances.
     *
     * @hibernate.bag
     *      cascade="all"
     * @hibernate.collection-key
     *      column="library_faq_category"
     * @hibernate.collection-one-to-many
     *      class="com.ivata.groupware.business.library.faq.FAQDO"
     */
    public List getFAQs() {
        return fAQs;
    }
    /**
     * <p>Get the library item.</p>
     *
     * @return the library item.
     * @hibernate.many-to-one
     *      column="library_item"
     */
    public LibraryItemDO getLibraryItem() {
        return libraryItem;
    }

    /**
     * <p>Get this name for this category. This should be a clear-text name
     * which can contain punctuation characters and spaces. This name should
     * uniquely identify the category within the library item it is in.</p>
     *
     * @return clear-text name which identifies this category within the
     *     library item.
     * @hibernate.property
     */
    public final String getName() {
        return name;
    }

    /**
     * <p>Set the text describing the function of this category. This is
     * optional.</p>
     *
     * @param description optional text describing the function and purpose of
     *     the category.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * <p>Set all of the questions and answers in this category as a
     * <code>List</code> of <code>FAQDO</code> instances.</p>
     *
     * @param faqs all of the questions and answers in this category as a
     * <code>List</code> of <code>FAQDO</code> instances.
     */
    public final void setFAQs(final List faqs) {
        this.fAQs = faqs;
    }

    /**
     * <p>Set the library item for this category.</p>
     *
     * @param libraryItem new value of library item.
     */
    public final void setLibraryItem(final LibraryItemDO libraryItem) {
        this.libraryItem = libraryItem;
    }

    /**
     * <p>Set this name for this category. This should be a clear-text name
     * which can contain punctuation characters and spaces. This name should
     * uniquely identify the category within the library item it is in.</p>
     *
     * @param name clear-text name which identifies this category within the
     *     library item.
     */
    public final void setName(final String name) {
        this.name = name;
    }
}

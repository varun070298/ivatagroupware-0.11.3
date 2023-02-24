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
 * $Log: TopicDO.java,v $
 * Revision 1.4  2005/04/29 02:48:16  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:31:57  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:46  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:06  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:58:43  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/06/04 09:28:36  colin
 * made comparable
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.4  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.3  2003/01/08 10:40:14  jano
 * we changed interface of libraryBeans, we are using libraryRightBean for amending rights and for finding out
 * and we are not storing rights in TopicDO
 *
 * Revision 1.2  2002/12/03 09:44:54  jano
 * I added rights for TOPIC
 *
 * Revision 1.1  2002/11/28 14:12:15  jano
 * we need because it's better pass one object as many Strings
 *
 * Revision 1.2  2002/11/26 14:56:31  jano
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.topic;

import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>Represents a topic which can be used for a library item. Each library item
 * in ivata groupware must have a topic associated with it, and this is important
 * as access to each item is granted or denied on the basis of the topic
 * associated with it.</p>
 *
 * @since   2002-11-26
 * @author  jano
 * @version $Revision: 1.4 $
 *
 * @hibernate.class
 *      table="library_topic"
 */
public class TopicDO extends BaseDO implements Comparable {

    /**
     * <p>Briefly describes the topic in one line.</p>
     */
    private String caption;

    /**
     * <p>Name of image file.</p>
     */
    private String image;
    /**
     * <p>Comparison method. See if the object supplied is a topic dependent
     * object and, if so, whether or not its contents are greater than, the same
     * or less than this one.</p>
     *
     * <p>This method sorts by the caption first then the id.</p>
     *
     * @param compare the object to compare with this one.
     * @return a positive number if the object supplied in <code>compare</code>
     *     is greater than this one, <code>0</code> if they are the same,
     *     otherwise a negative number.
     */
    public int compareTo(final Object compare) {
        // first check it is non-null and the class is right
        if ((compare == null) || !(this.getClass().isInstance(compare))) {
            return 1;
        }

        TopicDO topicDO = (TopicDO) compare;
        Integer id = getId();
        Integer topicId = topicDO.getId();

        // see the ids are the same
        if (((id == null) ? (topicId == null) : id.equals(topicId))) {
            return 0;
        }

        // see if the captions are equal - if so, use the id
        if (((caption == null) ? (topicDO.caption == null)
                                   : caption.equals(topicDO.caption))) {
            // if the id is null and the other id is not null, return +ve
            if (id == null) {
                return 1;
            }

            return 0;
        }

        // if the name is null and the other name is not null, return +ve
        if (caption == null) {
            return 1;
        }

        // otherwise, compare the names
        return caption.compareTo(topicDO.caption);
    }

    /**
     * <p>Briefly describes the topic in one line.</p>
     *
     * @return current value of caption.
     * @hibernate.property
     */
    public final String getCaption() {
        return caption;
    }
    /**
     * <p>Name of image file.</p>
     *
     * @return image file name.
     * @hibernate.property
     */
    public String getImage() {
        return image;
    }

    /**
     * <p>Briefly describes the topic in one line.</p>
     *
     * @param caption new value of caption.
     */
    public final void setCaption(final String caption) {
        this.caption = caption;
    }

    /**
     * <p>Name of image file.</p>
     *
     * @param image new image file name.
     */
    public final void setImage(final String image) {
        this.image = image;
    }
}

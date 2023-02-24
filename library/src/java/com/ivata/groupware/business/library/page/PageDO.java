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
 * $Log: PageDO.java,v $
 * Revision 1.3  2005/04/10 20:09:45  colinmacleod
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
 * Revision 1.1  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:40  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.5  2003/11/03 11:29:44  jano
 * commiting library,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.3  2003/10/15 14:21:49  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.3  2003/05/01 12:13:22  jano
 * tidy up names of sequeneces
 *
 * Revision 1.2  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.1  2003/02/24 19:09:22  colin
 * moved to business
 *
 * Revision 1.5  2003/02/04 17:43:47  colin
 * copyright notice
 *
 * Revision 1.4  2002/08/11 11:35:07  colin
 * cosmetic changes
 *
 * Revision 1.3  2002/07/04 12:29:28  jano
 * i put readonly script to CVS and i will commit all SRC directory
 *
 * Revision 1.2  2002/07/02 14:56:55  colin
 * tried to fix jbuilder EJB designer
 *
 * Revision 1.1  2002/06/18 11:40:13  colin
 * first version of library
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.page;

import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>Represents a page of text within the library. Items with the following
 * types can have pages associated with them:<br/>
 * <ul>
 *   <li>document</li>
 *   <li>memo</li>
 *   <li>news item</li>
 *   <li>meeting agenda/minutes</li>
 * </ul></p>
 *
 * @since 2002-06-14
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table="library_page"
 */
public class PageDO  extends BaseDO {

    /**
     * <p>Get the page number of this page within the library item. The first
     * page in the item will have the number <code>0</code>.</p>
     *
     * @return an <code>Integer</code> beginning with 0 describing the
     *   page's significance within it's library item. Lower numbers have higher
     *   significance.
     */
    private Integer number;

    /**
     * <p>
     * Contains the contents of the page.
     * </p>
     */
    private String text;

    /**
     * <p>Get the page number of this page within the library item. The first
     * page in the item will have the number <code>0</code>.</p>
     *
     * @return an <code>Integer</code> beginning with 0 describing the
     *   page's significance within it's library item. Lower numbers have higher
     *   significance.
     * @hibernate.property
     */
    public final Integer getNumber() {
        return number;
    }
    /**
     * <p>
     * Contains the contents of the page.
     * </p>
     *
     * @return Human-readable, clear text contents of the page.
     * @hibernate.property
     */
    public final String getText() {
        return text;
    }
    /**
     * <p>Set the page number of this page within the library item. The first
     * page in the item will have the number <code>0</code>.</p>
     *
     * @param number An <code>Integer</code> beginning with 0 describing the
     *   page's significance within it's library item. Lower numbers have higher
     *   significance.
     */
    public final void setNumber(final Integer number) {
        this.number = number;
    }
    /**
     * <p>
     * Contains the contents of the page.
     * </p>
     *
     * @param text Human-readable, clear text contents of the page.
     */
    public final void setText(final String text) {
        this.text = text;
    }
}

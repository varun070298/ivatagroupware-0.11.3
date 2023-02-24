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
 * $Log: CountryDO.java,v $
 * Revision 1.3  2005/04/10 20:09:34  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:07  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:22  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.5  2004/11/12 15:57:05  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.4  2004/07/13 19:41:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:06  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:49  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/02/24 19:09:20  colin
 * moved to business
 *
 * Revision 1.2  2003/02/04 17:43:44  colin
 * copyright notice
 *
 * Revision 1.1  2002/09/02 08:51:47  colin
 * first version of CountryDO for use in selects
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.address.country;


import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>Stores all valid countries in the system. These countries are used in
 * public holidays, employee records as well as addresses.</p>
 *
 * @since 2002-09-01
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table="address_country"
 * @hibernate.cache
 *      usage="read-write"
 */
public class CountryDO extends BaseDO {

    /**
     * <p>Store the two-letter country code here.</p>
     */
    private String code;

    /**
     * <p>Store the name of the country.</p>
     */
    private String name;

    /**
     * <p>Priority of this country in the system. Lower = better.</p>
     */
    private int priority;

    /**
     * <p>Get the country code for this country.</p>
     *
     * @return two letter country abbreviation following the standards of
     * internet TLDs.
     *
     * @hibernate.property
     */
    public final String getCode() {
        return code;
    }

    /**
     * Just returns the country name.
     * Refer to {@link #getName}.
     * @return Refer to {@link #getName}.
     */
    public String getDisplayValue() {
        return getName();
    }

    /**
     * <p>Get the name of the country.</p>
     *
     * @return US English clear-text country name.
     *
     * @hibernate.property
     */
    public final String getName() {
        return name;
    }

    /**
     * <p>Priority of this country in the system for lists. Lower = better.</p>
     *
     * @return priority of this country in the system. Lower = better.
     * @hibernate.property
     */
    public final int getPriority() {
        return priority;
    }
    /**
     * <p>Set the country code for this country.</p>
     *
     * @param code two letter country abbreviation following the standards of
     * internet TLDs.
     */
    public final void setCode(final String code) {
        this.code = code;
    }

    /**
     * <p>Set the name of the country.</p>
     *
     * @param name US English clear-text country name.
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * <p>Priority of this country in the system for lists. Lower = better.</p>
     * @param i priority of this country in the system. Lower = better.
     */
    public final void setPriority(final int i) {
        priority = i;
    }
}

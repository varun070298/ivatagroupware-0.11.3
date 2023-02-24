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
 * $Log: PublicHolidayDO.java,v $
 * Revision 1.2  2005/04/09 17:19:17  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:47:45  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/03 15:49:51  colinmacleod
 * Changed todo comments to TODO: all caps.
 *
 * Revision 1.1  2004/07/13 19:42:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:21  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/10 19:57:22  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:07:29  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:20  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.6  2003/11/04 09:27:15  jano
 * fixing mistaakes in XDoclet tags
 *
 * Revision 1.5  2003/11/03 11:28:48  jano
 * commiting calendar,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/28 13:10:23  jano
 * commiting calendar,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.3  2003/10/15 13:59:52  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:57:23  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.calendar.event.publicholiday;

import com.ivata.groupware.business.addressbook.address.country.CountryDO;
import com.ivata.groupware.business.calendar.event.EventDO;


/**
 * <p>Public holiday is event with country and region code. TODO:</p>
 *
 * @since 2002-08-02
 * @author Jan Boros <janboros@sourceforge.net>
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 *
 * @hibernate.joined-subclass
 *      table="public_holiday"
 * @hibernate.joined-subclass-key
 *      column="calendar_event"
 */
public class PublicHolidayDO  extends EventDO {
    /**
     * <p>The country to which this public holiday applies.</p>
     */
    private CountryDO country;

    /**
     * <p>Get the region code to which this public holiday applies.</p>
     */
    private String regionCode;
    /**
     * <p>Get the country to which this public holiday applies.</p>
     *
     * @return the country to which this public holiday applies.
     *
     * @hibernate.many-to-one
     */
    public final CountryDO getCountry() {
        return country;
    }
    /**
     * <p>Get the region code to which this public holiday applies.</p>
     *
     * @return region code to which this public holiday applies.
     *
     * @hibernate.property
     *      column="region_code"
     */
    public final String getRegionCode() {
        return regionCode;
    }

    /**
     * <p>Set the country to which this public holiday applies.</p>
     *
     * @param country to which this public holiday applies.
     */
    public final void setCountry(final CountryDO country) {
        this.country = country;
    }

    /**
     * <p>Set the region code to which this public holiday applies.</p>
     *
     * @param region code to which this public holiday applies.
     */
    public final void setRegionCode(final String regionCode) {
        this.regionCode = regionCode;
    }
}

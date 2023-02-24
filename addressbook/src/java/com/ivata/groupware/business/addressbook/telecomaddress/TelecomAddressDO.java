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
 * $Log: TelecomAddressDO.java,v $
 * Revision 1.3  2005/04/10 20:09:38  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:09  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:21  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/07/13 19:41:14  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:08  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:33  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:54  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.5  2003/11/03 11:28:24  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.3  2003/10/15 13:48:01  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.4  2003/02/04 17:43:45  colin
 * copyright notice
 *
 * Revision 1.3  2002/07/04 12:29:28  jano
 * i put readonly script to CVS and i will commit all SRC directory
 *
 * Revision 1.2  2002/06/17 07:28:55  colin
 * improved and extended javadoc documentation
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.telecomaddress;


import com.ivata.groupware.container.persistence.NamedDO;


/**
 * <p>Represents an email, web site URL, telephone number or fax number. The
 * actual type of the number depends on the setting of the type fields,
 * which should be set to one of the constants in {@link TelecomAddressConstants
 * TelecomAddressConstants}.</p>
 *
 * @since 2002-05-14
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table="telecom_address"
 * @hibernate.cache
 *      usage="read-write"
 */
public class TelecomAddressDO  extends NamedDO {
    /**
     * <p>The actual address value. Depending on the value of the type
     * field, this will be a telephone/fax number, URL or email address.</p>
     */
    private String address;

    /**
     * <p>Get the order in which this address should appear on the screen. Lower
     * numbers mean that the address will appear higher in any listing.</p>
     */
    private int number;

    /**
     * <p>The type of the address to a numeric value representing on of the
     * possible telecommunication types.</p>
     */
    private int type;

    /**
    /**
     * <p>Get the actual address value. Depending on the value of the type
     * field, this will be a telephone/fax number, URL or email address.</p>
     *
     * @return the telephone number, email address or URL string this
     *     telecommunications address represents.
     *
     * @hibernate.property
     */
    public final String getAddress() {
        return address;
    }
    /**
     * <p>
     * Get the 'name' of this telecom address - just returns the address value.
     * </p>
     *
     * @return see {@link #getAdddress}.
     * @see com.ivata.groupware.container.persistence.NamedDO#getName()
     */
    public final String getName() {
        return getAddress();
    }

    /**
     * <p>Get the order in which this address should appear on the screen. Lower
     * numbers mean that the address will appear higher in any listing.</p>
     *
     * @return number used for sorting the address to display. Lower numbers
     *     mean higher significance.
     *
     * @hibernate.property
     */
    public final int getNumber() {
        return number;
    }
    /**
     * <p>Get the type of the address to a numeric value representing on of the
     * possible telecommunication types.</p>
     *
     * @return the type of the address, defined in {@link
     *     TelecomAddressConstants TelecomAddressConstants}.
     *
     * @hibernate.property
     *      column="address_type"
     */
    public final int getType() {
        return type;
    }

    /**
     * <p>Set the actual address value. Depending on the value of the type
     * field, this will be a telephone/fax number, URL or email address.</p>
     *
     * @param address the telephone number, email address or URL string this
     *     telecommunications address represents.
     */
    public final void setAddress(final String address) {
        this.address = address;
    }

    /**
     * <p>Set the order in which this address should appear on the screen. Lower
     * numbers mean that the address will appear higher in any listing.</p>
     *
     * @param number number used for sorting the address to display. Lower
     *     numbers mean higher significance.
     */
    public final void setNumber(final int number) {
        this.number = number;
    }

    /**
     * <p>Set the type of the address to a numeric value representing on of the
     * possible telecommunication types.</p>
     *
     * @param type the type of the address, defined in {@link
     *     TelecomAddressConstants TelecomAddressConstants}.
     * @see TelecomAddressConstants
     */
    public final void setType(final int type) {
        this.type = type;
    }
}

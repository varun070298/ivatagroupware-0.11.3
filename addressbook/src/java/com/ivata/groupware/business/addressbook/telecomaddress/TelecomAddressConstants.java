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
 * $Log: TelecomAddressConstants.java,v $
 * Revision 1.2  2005/04/09 17:19:09  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:19  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/07/13 19:41:14  colinmacleod
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
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.3  2003/02/04 17:43:45  colin
 * copyright notice
 *
 * Revision 1.2  2002/06/28 13:15:23  colin
 * first addressbook release
 *
 * Revision 1.1  2002/06/17 07:28:30  colin
 * improved and extended javadoc documentation
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.telecomaddress;


/**
 * <p>Contains all constants and constant conversion in use for telecom addresses.
 * Use these constats to get the clear text name of the telecom address type, or
 * to find the correct type to store in the EJB.</p>
 *
 * @since 2002-05-14
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public class TelecomAddressConstants {

    /**
     * <p>Represents a home telephone number.</p>
     */
    public final static int TYPE_HOME = 0;

    /**
     * <p>Represents a work telephone number.</p>
     */
    public final static int TYPE_WORK = 1;

    /**
     * <p>Represents a mobile telephone number.</p>
     */
    public final static int TYPE_MOBILE = 2;

    /**
     * <p>Represents a fax telephone number.</p>
     */
    public final static int TYPE_FAX = 3;

    /**
     * <p>Represents an email address.</p>
     */
    public final static int TYPE_EMAIL = 4;

    /**
     * <p>Represents a web site address.</p>
     */
    public final static int TYPE_URL = 5;

    /**
     * <p>This is used to translate the different address types.</p>
     *
     * @see #getTelecomAddressTypeName( int telecomAddressType )
     */
    private final static String[  ] typeNames = {
        "person.label.telecomaddress.home",
        "person.label.telecomaddress.work",
        "person.label.telecomaddress.mobile",
        "person.label.telecomaddress.fax",
        "person.label.telecomaddress.email",
        "person.label.telecomaddress.uRL"};

    /**
     * <p>Tell client routines how many telecom address types there are.</p>
     *
     * @return the number of telecom address types.
     */
    public static int countTypes() {
        return typeNames.length;
    }

    /**
     * <p>Translates the code assigned to a a telecom address type into a text
     * representing it.<p>
     *
     * @param type the numeric type to be converted into a text.
     * @return the code assigned to a a telecom address type translated into a
     *     text representing it.
     */
    public final static String getTypeName(int type) {
        return typeNames[type];
    }
}

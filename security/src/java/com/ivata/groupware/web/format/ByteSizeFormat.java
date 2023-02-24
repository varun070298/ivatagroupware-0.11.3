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
 * $Log: ByteSizeFormat.java,v $
 * Revision 1.3  2005/04/11 16:54:15  colinmacleod
 * Changed HTMLFormat to an interface (rather
 * than an abstract class.)
 *
 * Revision 1.2  2005/04/09 17:19:59  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:35  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/09/30 15:16:01  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.3  2004/03/21 21:16:08  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:33  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:55  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.1.1.1  2003/10/13 20:50:09  colin
 * Restructured portal into subprojects
 *
 * Revision 1.2  2003/06/19 07:46:41  jano
 * fixing bug with int size
 *
 * Revision 1.1  2003/02/24 19:33:32  colin
 * moved to jsp
 *
 * Revision 1.2  2003/02/04 17:43:46  colin
 * copyright notice
 *
 * Revision 1.1  2002/09/18 16:34:46  colin
 * first version used in email list
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.format;

import com.ivata.mask.util.StringHandling;
import com.ivata.mask.web.format.HTMLFormat;


/**
 * <p>Format the size of a file or other object as bytes, kb, or Mb
 * depending if the number is less than a kilobyte, or less than a
 * megabyte in size..</p>
 *
 * @since 2002-09-18
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class ByteSizeFormat implements HTMLFormat {
    /**
     * <p>The size under which the number appears just as it is, a raw
     * number. If the numer is greater than this size (but less than
     * <code>megaSize</code>) it will be displayed as a multiple of this
     * number.</p>
     */
    static final int kiloSize = 1024;
    /**
     * <p>The size above which the number appears as a multiple of this
     * number, a multiple of <i>megabytes</i>.</p>
     */
    static final int megaSize = 1048576;
    /**
     * <p>The number of decimal places to show for <i>kilobyte</i> or
     * <i>megabyte</i> sizes.</p>
     */
    private int decimals = 1;
    /**
     * <p>String to append to the number if it is expressed in
     * <i>bytes</i>.</p>
     */
    private String byteUnits = "";
    /**
     * <p>String to append to the number if it is expressed in
     * <i>kilobytes</i>.</p>
     */
    private String kiloUnits = "k";
    /**
     * <p>String to append to the number if it is expressed in
     * <i>Megabytes</i>.</p>
     */
    private String megaUnits = "M";
    /**
     * <p>The character to use as a decimal point.</p>
     */
    private char decimalCharacter = '.';

    /**
     * <p>Convert  the number the given string represents according to
     * whether or not the number represents just <i>bytes</i>,
     * <i>kilobytes</i> or <i>megabytes<i>.</p>
     *
     * @param byteTextParam string number to convert.
     * @return a string representing the size, expressed in the
     * appropriate units.
     */
    public String format(final String byteTextParam) {
        // first convert the string to an integer
        Integer bytesInteger = StringHandling.integerValue(byteTextParam);
        int bytes;
        String byteText = byteTextParam;
        if(bytesInteger == null) {
            bytes = 0;
        } else {
            bytes = bytesInteger.intValue();
        }
        // is this less than the kilo size?
        if(bytes < kiloSize) {
            byteText = bytes + byteUnits;
        } else {
            // both the kilo and mega ranges need to do rounding
            int rounding = 10;
            for(int exp = 0; exp < decimals; ++exp) {
                rounding *= 10;
            }
            long newValue = (long) bytes * rounding;
            String units;
            // if this is in the kilo range, divide by that amount
            if(bytes < megaSize) {
                newValue /= kiloSize;
                units = kiloUnits;
            } else {
                newValue /= megaSize;
                units = megaUnits;
            }
            // the clever bit - this does the rounding up or down whichever is
            // nearest
            newValue += 5;
            newValue /= 10;

            // now to add the decimal point
            String noDecimal = Long.toString(newValue);
            int length = noDecimal.length();
            byteText = noDecimal.substring(0, length - decimals)
                + decimalCharacter
                + noDecimal.substring(length - decimals)
                + units;
        }
        return byteText;
    }

    /**
     * <p>The number of decimal places to show for <i>kilobyte</i> or
     * <i>megabyte</i> sizes.</p>
     *
     * @return the current value of decimal places.
     */
    public final int getDecimals() {
        return decimals;
    }

    /**
     * <p>The number of decimal places to show for <i>kilobyte</i> or
     * <i>megabyte</i> sizes.</p>
     *
     * @param decimals the new value of decimal places to display.
     */
    public final void setDecimals(final int decimals) {
        this.decimals = decimals;
    }

    /**
     * <p>String to append to the number if it is expressed in
     * <i>bytes</i>.</p>
     *
     * @return the current value of byte units.
     */
    public final String getByteUnits() {
        return byteUnits;
    }

    /**
     * <p>String to append to the number if it is expressed in
     * <i>bytes</i>.</p>
     *
     * @param byteUnits the new value of units to append to numbers
     * expressed in bytes.
     */
    public final void setByteUnits(final String byteUnits) {
        this.byteUnits = byteUnits;
    }

    /**
     * <p>String to append to the number if it is expressed in
     * <i>kilobytes</i>.</p>
     *
     * @return the current value of kilo units.
     */
    public final String getKiloUnits() {
        return kiloUnits;
    }

    /**
     * <p>String to append to the number if it is expressed in
     * <i>kilobytes</i>.</p>
     *
     * @param kiloUnits the new value of units to append to numbers
     * expressed in kilobytes.
     */
    public final void setKiloUnits(final String kiloUnits) {
        this.kiloUnits = kiloUnits;
    }

    /**
     * <p>String to append to the number if it is expressed in
     * <i>Megabytes</i>.</p>
     *
     * @return the current value of mega units.
     */
    public final String getMegaUnits() {
        return megaUnits;
    }

    /**
     * <p>String to append to the number if it is expressed in
     * <i>Megabytes</i>.</p>
     *
     * @param megaUnits the new value of units to append to numbers
     * expressed in megabytes.
     */
    public final void setMegaUnits(final String megaUnits) {
        this.megaUnits = megaUnits;
    }

    /**
     * <p>The character to use as a decimal point.</p>
     *
     * @return the current value of decimal character.
     */
    public final char getDecimalCharacter() {
        return decimalCharacter;
    }

    /**
     * <p>The character to use as a decimal point.</p>
     *
     * @param decimalCharacter the new value of decimal character.
     */
    public final void setDecimalCharacter(final char decimalCharacter) {
        this.decimalCharacter = decimalCharacter;
    }
}

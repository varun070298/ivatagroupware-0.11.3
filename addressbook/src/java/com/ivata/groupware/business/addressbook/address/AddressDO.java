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
 * $Log: AddressDO.java,v $
 * Revision 1.4  2005/04/30 13:00:25  colinmacleod
 * Added getId override for one-to-one relationship.
 *
 * Revision 1.3  2005/04/10 20:09:34  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:06  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:22  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/11/12 15:57:04  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/11/03 15:34:05  colinmacleod
 * Changed relationship between person and address:
 * each person for now has exactly one address.
 *
 * Revision 1.1  2004/07/13 19:41:13  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.address;

import com.ivata.groupware.business.addressbook.address.country.CountryDO;
import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.container.persistence.BaseDO;

/**
 * <p>Represents a street address within the system. The current model will allow
 * each person to have multiple street addresses, though the JSP front-end is
 * currently restricted to just one.</p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   2002-05-15
 * @version $Revision: 1.4 $
 *
 * @hibernate.class
 *      table="address"
 * @hibernate.cache
 *      usage="read-write"
 */
public class AddressDO extends BaseDO {
    /**
     * <p>
     * Used to separate all the values in the display value.
     * </p>
     */
    private final static String DISPLAY_VALUE_SEPERATOR = ", ";
    /**
     * Country this address is in.
     */
    private CountryDO country;
    /**
     * <p>
     * In this version of ivata groupware, each address is associated with a single
     * person.
     * </p>
     */
    private PersonDO person;
    /**
     * Zipcode or postcode. Can be aphanumeric in some countries (UK/CA).
     */
    private String postCode;
    /**
     * <p>
     * State or county/region within country.
     * </p>
     */
    private String region;
    /**
     * <p>
     * House name/house number, street and district.
     * </p>
     */
    private String streetAddress;
    /**
     * <p>
     * Town or city.
     * </p>
     */
    private String town;

    /**
     * <p>
     * Get the country of an address.
     * </p>
     *
     * @return country for this address bean.
     * @hibernate.many-to-one
     *      column="address_country"
     */
    public final  CountryDO getCountry() {
        return country;
    }

    /**
     * <p>
     * For this class, shows the whole address.
     * </p>
     *
     * @see com.ivata.mask.valueobject.ValueObject#getDisplayValue()
     */
    public final String getDisplayValue() {
        StringBuffer displayValue = new StringBuffer();
        if (streetAddress != null) {
            displayValue.append(streetAddress);
        }
        if (town != null) {
            if (displayValue.length() > 0) {
                displayValue.append(DISPLAY_VALUE_SEPERATOR);
            }
            displayValue.append(town);
        }
        if (postCode != null) {
            if (displayValue.length() > 0) {
                displayValue.append(DISPLAY_VALUE_SEPERATOR);
            }
            displayValue.append(postCode);
        }
        if (region != null) {
            if (displayValue.length() > 0) {
                displayValue.append(DISPLAY_VALUE_SEPERATOR);
            }
            displayValue.append(region);
        }
        if (country != null) {
            if (displayValue.length() > 0) {
                displayValue.append(DISPLAY_VALUE_SEPERATOR);
            }
            displayValue.append(country.getDisplayValue());
        }
        return displayValue.toString();
    }
    /**
     * @return Returns the person.
     * @hibernate.one-to-one
     */
    public PersonDO getPerson() {
        return person;
    }

    /**
     * <p>
     * Get the zipcode or postcode. Can be aphanumeric in some countries (UK/CA).
     * </p>
     *
     * @return zipcode or postcode. Can be aphanumeric in some countries
     *     (UK/CA).
     * @hibernate.property
     *      column="post_code"
     */
    public final  String getPostCode() {
        return postCode;
    }

    /**
     * <p>
     * Get the state or county/region within country.
     * </p>
     *
     * @return state or county/region within country.
     * @hibernate.property
     */
    public final  String getRegion() {
        return region;
    }
    /**
     * <p>Get the house name/house number, street and district.</p>
     *
     * @return the house name/house number, street and district.
     * @hibernate.property
     *      column="street_address"
     */
    public final  String getStreetAddress() {
        return streetAddress;
    }
    /**
     * <p>Get the town or city.</p>
     *
     * @return the town or city.
     * @hibernate.property
     */
    public final  String getTown() {
        return town;
    }

    /**
     * <p>Set the country of an address.</p>
     *
     * @param country the new country for this address bean.
     */
    public final  void setCountry(final CountryDO country) {
        this.country = country;
    }
    /**
     * @param person The person to set.
     */
    public final void setPerson(final PersonDO person) {
        this.person = person;
    }

    /**
     * <p>Set the zipcode or postcode. Can be aphanumeric in some countries
     * (UK/CA).</p>
     *
     * @param postCode zipcode or postcode. Can be aphanumeric in some countries
     *      (UK/CA).
     */
    public final  void setPostCode(final String postCode) {
        this.postCode = postCode;
    }

    /**
     * <p>Set the state or county/region within country.</p>
     *
     * @param region state or county/region within country.
     */
    public final  void setRegion(final String region) {
        this.region = region;
    }

    /**
     * <p>Set the house name/house number, street and district.</p>
     *
     * @param streetAddress house name/house number, street and district.
     */
    public final  void setStreetAddress(final String streetAddress) {
        this.streetAddress = streetAddress;
    }
    /**
     * <p>Set the town or city.</p>
     *
     * @param town or city.
     */
    public final  void setTown(final String town) {
        this.town = town;
    }
    /**
     * Overridden to set the id type.
     * @see com.ivata.groupware.container.persistence.BaseDO#getId()
     * @return current identifier.
     * @hibernate.id
     *      generator-class="foreign"
     * @hibernate.generator-param
     *      name="property"
     *      value="person"
     */
    public Integer getId() {
        return super.getId();
    }
}

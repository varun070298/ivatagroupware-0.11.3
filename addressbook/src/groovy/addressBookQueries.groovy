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
 * $Log: addressBookQueries.groovy,v $
 * Revision 1.1  2005/04/11 09:59:28  colinmacleod
 * Added scripts to initialize container.
 *
 * -----------------------------------------------------------------------------
 * This script defines all of the Hibernate queries used in the AddressBook
 * subproject.
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since ivata groupware v0.11 (2005-03-29)
 * @version $Revision: 1.1 $
 * -----------------------------------------------------------------------------
 */
// find a country by its two letter code
queryMap["addressBookCountryByCode"] =
    "FROM " +
    "com.ivata.groupware.business.addressbook.address.country." +
    "CountryDO country " +
    "WHERE " +
    "country.code=:code "
queryArgumentsMap["addressBookCountryByCode"] = ["code"]

// find all child groups in a parent group
queryMap["addressBookGroupsInGroup"] =
    "FROM " +
    "com.ivata.groupware.business.addressbook.person.group.GroupDO " +
    "person_group " +
    "WHERE " +
    "person_group.parent.id=:parentId "
queryArgumentsMap["addressBookGroupsInGroup"] = ["parentId"]

// find one group in a parent group by its name
queryMap["addressBookGroupsInGroupByName"] =
    "FROM " +
    "com.ivata.groupware.business.addressbook.person.group.GroupDO " +
    "person_group " +
    "WHERE " +
    "person_group.name=:name " +
    "AND " +
    "person_group.parent.id=:parentId "
queryArgumentsMap["addressBookGroupsInGroupByName"] = ["parentId", "name"]
queryMap["addressBookPersonByAddressBookId"] =
    "FROM " +
    "com.ivata.groupware.business.addressbook.person.PersonDO " +
    "person " +
    "WHERE " +
    "person.group.addressBook.id=:addressBookId"
queryArgumentsMap["addressBookPersonByAddressBookId"] = ["addressBookId"]
queryMap["addressBookPersonByUserName"] =
    "FROM " +
    "com.ivata.groupware.business.addressbook.person.PersonDO " +
    "person " +
    "WHERE " +
    "person.user.name=:userName"
queryArgumentsMap["addressBookPersonByUserName"] = ["userName"]
////////////////////////////////////////////////////////////////////////////////

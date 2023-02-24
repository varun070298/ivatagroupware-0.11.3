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
 * $Log: securityQueries.groovy,v $
 * Revision 1.1  2005/04/11 09:59:28  colinmacleod
 * Added scripts to initialize container.
 *
 * -----------------------------------------------------------------------------
 * This script defines all of the Hibernate queries used in the Security
 * subproject.
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since ivata groupware v0.11 (2005-03-29)
 * @version $Revision: 1.1 $
 * -----------------------------------------------------------------------------
 */
// find a user by user name
queryMap["securityUserByName"] =
    "FROM " +
    "com.ivata.groupware.admin.security.user.UserDO person_user " +
    "WHERE " +
    "person_user.name=:name "
queryArgumentsMap["securityUserByName"] = ["name"]

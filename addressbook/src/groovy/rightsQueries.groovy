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
 * $Log: rightsQueries.groovy,v $
 * Revision 1.1  2005/04/11 09:59:28  colinmacleod
 * Added scripts to initialize container.
 *
 * -----------------------------------------------------------------------------
 * This script defines all of the Hibernate queries used in user rights.
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since ivata groupware v0.11 (2005-03-29)
 * @version $Revision: 1.1 $
 * -----------------------------------------------------------------------------
 */
queryMap["rightByAccessDetailTargetId"] =
    "FROM " +
    "com.ivata.groupware.business.addressbook.person.group.right." +
    "RightDO userRight " +
    "WHERE " +
    "userRight.access=:access " +
    "AND " +
    "userRight.detail=:detail " +
    "AND " +
    "userRight.targetId=:targetId "
queryArgumentsMap["rightByAccessDetailTargetId"] = ["access", "detail",
    "targetId"]
queryMap["rightByGroupIdAccessDetail"] =
    "FROM " +
    "com.ivata.groupware.business.addressbook.person.group.right." +
    "RightDO userRight " +
    "WHERE " +
    "userRight.group.id=:groupId " +
    "AND " +
    "userRight.access=:access " +
    "AND " +
    "userRight.detail=:detail"
queryArgumentsMap["rightByGroupIdAccessDetail"] = ["groupId", "access",
    "detail"]
queryMap["rightByUserNameAccessDetail"] =
    "FROM " +
    "com.ivata.groupware.business.addressbook.person.group.right." +
    "RightDO AS userRight " +
    "INNER JOIN " +
    "com.ivata.groupware.admin.security.user.UserDO AS person_user " +
    "WHERE " +
    "person_user.name=:userName " +
    "AND " +
    "person_user in elements(userRight.group.users) " +
    "AND " +
    "userRight.access=:access " +
    "AND " +
    "userRight.detail=:detail"
queryArgumentsMap["rightByUserNameAccessDetail"] = ["userName", "access",
    "detail"]
queryMap["rightByUserNameAccessDetailTargetId"] =
    "SELECT userRight " +
    "FROM " +
    "com.ivata.groupware.business.addressbook.person.group.right." +
    "RightDO AS userRight, " +
    "com.ivata.groupware.admin.security.user.UserDO AS person_user " +
    "WHERE " +
    "person_user.name=:userName " +
    "AND " +
    "person_user in elements(userRight.group.users) " +
    "AND " +
    "userRight.access=:access " +
    "AND " +
    "userRight.detail=:detail " +
    "AND " +
    "userRight.targetId=:targetId "
queryArgumentsMap["rightByUserNameAccessDetailTargetId"] = ["userName",
    "access", "detail", "targetId"]
queryMap["rightGroupIdByAccessDetailTargetId"] =
    "SELECT group.id " +
    "FROM " +
    "com.ivata.groupware.business.addressbook.person.group.right." +
    "RightDO AS userRight " +
    "WHERE " +
    "userRight.access=:access " +
    "AND " +
    "userRight.detail=:detail" +
    "AND " +
    "userRight.targetId=:targetId "
queryArgumentsMap["rightGroupIdByAccessDetailTargetId"] = ["access",
    "detail", "targetId"]
queryMap["rightTargetIdByGroupIdAccessDetail"] =
    "SELECT targetId " +
    "FROM " +
    "com.ivata.groupware.business.addressbook.person.group.right" +
    ".RightDO AS userRight " +
    "WHERE " +
    "userRight.group.id = groupId " +
    "AND " +
    "userRight.access=:access " +
    "AND " +
    "userRight.detail=:detail"
queryArgumentsMap["rightTargetIdByGroupIdAccessDetail"] = ["groupId",
    "access", "detail"]
queryMap["rightTargetIdByUserNameAccessDetail"] =
    "SELECT userRight.targetId " +
    "FROM " +
    "com.ivata.groupware.business.addressbook.person.group.right" +
    ".RightDO AS userRight, " +
    "com.ivata.groupware.admin.security.user.UserDO AS " +
    "person_user " +
    "WHERE " +
    "person_user.name=:userName " +
    "AND " +
    "person_user in elements(userRight.group.users) " +
    "AND " +
    "userRight.access=:access " +
    "AND " +
    "userRight.detail=:detail"
queryArgumentsMap["rightTargetIdByUserNameAccessDetail"] = ["userName",
    "access", "detail"]

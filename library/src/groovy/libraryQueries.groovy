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
 * $Log: libraryQueries.groovy,v $
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
queryMap["libraryCountCommentByItemId"] =
    "SELECT count(*) " +
    "FROM " +
    "com.ivata.groupware.business.library.comment.CommentDO " +
    "comment " +
    "WHERE " +
    "comment.item.id=:itemId "
queryArgumentsMap["libraryCountCommentByItemId"] = ["itemId"]
queryMap["libraryCommentByItemId"] =
    "FROM " +
    "com.ivata.groupware.business.library.comment.CommentDO " +
    "comment " +
    "WHERE " +
    "comment.parent.id is NULL " +
    "AND " +
    "comment.item.id=:itemId"
queryArgumentsMap["libraryCommentByItemId"] = ["itemId"]
queryMap["libraryCommentByParentId"] =
    "FROM " +
    "com.ivata.groupware.business.library.comment.CommentDO " +
    "comment " +
    "WHERE " +
    "comment.parent.id=:parentId "
queryArgumentsMap["libraryCommentByParentId"] = ["parentId"]
queryMap["libraryCommentByUserNameUnacknowledged"] =
    "SELECT DISTINCT comment FROM " +
    "com.ivata.groupware.business.library.comment.CommentDO " +
    "comment " +
    "WHERE " +
    "(" +
    "  (" +
    "    (comment.createdBy.name<>:userName) " +
    "  AND " +
    "    (comment.parent IS NULL) " +
    "  AND " +
    "    (comment.item.createdBy.name=:userName)" +
    "  ) " +
    "OR " +
    "  (" +
    "    (comment.createdBy.name<>:userName) " +
    "  AND " +
    "    (comment.parent.createdBy.name=:userName)" +
    "  )" +
    ") " +
    "AND " +
    "comment.unacknowledged=:unacknowledged " +
    "ORDER BY " +
    "comment.modified DESC"
queryArgumentsMap["libraryCommentByUserNameUnacknowledged"] = ["userName",
    "unacknowledged"]
queryMap["libraryItemRecent"] =
    "FROM " +
    "com.ivata.groupware.business.library.item.LibraryItemDO " +
    "item " +
    "ORDER BY " +
    "item.modified DESC"
queryArgumentsMap["libraryItemRecent"] = []
queryMap["libraryItemRecentByTopic"] =
    "FROM " +
    "com.ivata.groupware.business.library.item.LibraryItemDO " +
    "item " +
    "WHERE " +
    "item.topic.id=:topicId " +
    "ORDER BY " +
    "item.modified DESC"
queryArgumentsMap["libraryItemRecentByTopic"] = ["topicId"]
////////////////////////////////////////////////////////////////////////////////

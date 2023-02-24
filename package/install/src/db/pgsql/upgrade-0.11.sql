--==============================================================================
--
-- PostgreSQL file to upgrade from ivata op 0.10 to 0.11. This script is
-- compatible with PostgreSQL 7.2 or greater.
--
--==============================================================================
--
-- $Id: upgrade-0.11.sql,v 1.3 2005/05/01 08:48:07 colinmacleod Exp $
-- Copyright (c) 2001 - 2005 ivata limited.
-- All rights reserved.
-- =========================================================
-- ivata groupware may be redistributed under the GNU General Public
-- License as published by the Free Software Foundation;
-- version 2 of the License.
--
-- These programs are free software; you can redistribute them and-or
-- modify them under the terms of the GNU General Public License
-- as published by the Free Software Foundation; either version 2
-- of the License, or (at your option) any later version.
--
-- These programs are distributed in the hope that they will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
--
-- See the GNU General Public License in the file LICENSE.txt for more
-- details.
--
-- If you would like a copy of the GNU General Public License write to
--
-- Free Software Foundation, Inc.
-- 59 Temple Place - Suite 330
-- Boston, MA 02111-1307, USA.
--
--
-- To arrange commercial support and licensing, contact ivata at
--                  http:--www.ivata.com-contact.jsp
--
--==============================================================================
--
-- Use this script to upgrade from 0.10 to 0.11.
-- You probably need postgres 7.4 or higher (might work with 7.3).
-- BACK EVERYTHING UP BEFORE ATTEMPTING THE FOLLOWING STEPS
--
-- 1) run this script against your old data
-- 2) backup your db with "pg_dump -aD {dbname} > file.dump"
-- 3) then drop the database
-- 4) create a new db and load the new schema using db/pgsql/schema-current.sql
-- 5) load in the data from file.dump in stage 2
--
-- $Log: upgrade-0.11.sql,v $
-- Revision 1.3  2005/05/01 08:48:07  colinmacleod
-- Added siteName line.
--
-- Revision 1.2  2005/04/29 02:55:38  colinmacleod
-- Added navigation/addressbook changes and
-- a brief summary of how to use this script.
--
-- Revision 1.1  2005/04/11 10:26:58  colinmacleod
-- Adds new siteTheme setting for v0.11.
--
--==============================================================================

--==============================================================================
-- == SETTINGS ==
--==============================================================================
insert into setting (name, value, type, description, enable, created, created_by, modified, modified_by) values ('siteTheme', 'shadow', 1, 'Theme to use by default.', true, now(), 1, now(), 1);
update setting set value='/images/logo.gif' where name='siteLogo';
update setting set value='ivatagroupware v0.11' where name='siteName';
--==============================================================================
-- == ADDRESS BOOK ==
--==============================================================================
update address_country set priority=99 where priority is null;
alter table person_user drop column person cascade;
--==============================================================================
-- == NAVIGATION ==
--==============================================================================
update navigation_menu_item set text='new doc' where text='new document';
--==============================================================================


#!/bin/sh
################################################################################
# $Id: createPortalDB.sh,v 1.2 2005/04/09 17:19:52 colinmacleod Exp $
#
# This script creates the portal database in a running Hypersonic DB
#
# Before using this script, change the settings in the file settings.sh
#
# Since: ivata groupware 0.9 (2004-03-06)
# Author: Colin MacLeod <colin.macleod@ivata.com>
# $Revision: 1.2 $
#
################################################################################
# Copyright (c) 2001 - 2005 ivata limited.
# All rights reserved.
# ---------------------------------------------------------
# ivata groupware may be redistributed under the GNU General Public
# License as published by the Free Software Foundation;
# version 2 of the License.
#
# These programs are free software; you can redistribute them and/or
# modify them under the terms of the GNU General Public License
# as published by the Free Software Foundation; version 2 of the License.
#
# These programs are distributed in the hope that they will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
#
# See the GNU General Public License in the file LICENSE.txt for more
# details.
#
# If you would like a copy of the GNU General Public License write to
#
# Free Software Foundation, Inc.
# 59 Temple Place - Suite 330
# Boston, MA 02111-1307, USA.
#
#
# To arrange commercial support and licensing, contact ivata at
#                  http://www.ivata.com/contact.jsp
#
################################################################################
#
# $Log: createPortalDB.sh,v $
# Revision 1.2  2005/04/09 17:19:52  colinmacleod
# Changed copyright text to GPL v2 explicitly.
#
# Revision 1.1.1.1  2005/03/10 17:49:17  colinmacleod
# Restructured ivata op around Hibernate/PicoContainer.
# Renamed ivata groupware.
#
################################################################################

thisdir=`dirname $0`
. "$thisdir/settings.sh"

exec "$JAVA_HOME/bin/java" $JAVA_OPTS "org.hsqldb.util.ScriptTool" -url "$DB_URL" -database "$DB_BASE" -script "$OPHSQL_HOME/portal-schema.sql"


#!/bin/sh
################################################################################
# $Id: createCurrent.sh,v 1.2 2005/04/09 17:19:53 colinmacleod Exp $
#
# This script will create a current version of the ivata groupware database,
# called 'ivatagroupware'. It's useful for development purposes, and should be
# run as the 'postgres' user.
#
# NOTE: THIS WILL DELETE ANY PREVIOUS ivatagroupware DATABASE!!!!!!!!
#
# Since: ivata groupware 0.9 (2004-05-23)
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
################################################################################
#
# $Log: createCurrent.sh,v $
# Revision 1.2  2005/04/09 17:19:53  colinmacleod
# Changed copyright text to GPL v2 explicitly.
#
# Revision 1.1.1.1  2005/03/10 17:49:20  colinmacleod
# Restructured ivata op around Hibernate/PicoContainer.
# Renamed ivata groupware.
#
# Revision 1.3  2004/12/31 18:18:49  colinmacleod
# Changed current version number from 0.9.1 to 0.10.
#
# Revision 1.2  2004/11/12 17:05:37  colinmacleod
# Added optional local initial state SQL file.
#
# Revision 1.1  2004/07/13 19:48:09  colinmacleod
# Moved project to POJOs from EJBs.
# Applied PicoContainer to services layer (replacing session EJBs).
# Applied Hibernate to persistence layer (replacing entity EJBs).
#
################################################################################

# location of useful commands
CREATEDB="/usr/bin/createdb"
DROPDB="/usr/bin/dropdb"
PSQL="/usr/bin/psql -e"
SLEEP="/bin/sleep"
DB_NAME=ivatagroupware

# first drop and recreate the portal db
$DROPDB $DB_NAME
$SLEEP 1
$CREATEDB $DB_NAME || exit -1

echo "################################################################################"
echo "FIRST INITIALIZE TO 0.9 STATE"
echo "################################################################################"
if test -e ../../../db/psql/upgrade/local.sql; then
    $PSQL $DB_NAME < ../../../db/psql/upgrade/local.sql || exit -1
else
    $PSQL $DB_NAME < ../../../db/psql/upgrade/state-0.9.sql || exit -1
fi

echo "################################################################################"
echo "UPDATES SINCE 0.9 BEING APPLIED NEXT"
echo "################################################################################"
$PSQL $DB_NAME < ../../../db/psql/upgrade/upgrade-0.10.sql || exit -1
echo "################################################################################"



#!/bin/sh
################################################################################
# $Id: settings.sh,v 1.2 2005/04/09 17:19:53 colinmacleod Exp $
#
# Sets various settings to their default values.
#
# JAVA_HOME to point to your java directory
#   - defaults to /usr/share/java
# JBOSS_HOME to point to your JBOSS installation
#   - defaults to /usr/local/jboss
# OP_HOME to point to your open portal scripts directory
#   - defaults to /usr/local/openportal
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
################################################################################
#
# $Log: settings.sh,v $
# Revision 1.2  2005/04/09 17:19:53  colinmacleod
# Changed copyright text to GPL v2 explicitly.
#
# Revision 1.1.1.1  2005/03/10 17:49:17  colinmacleod
# Restructured ivata op around Hibernate/PicoContainer.
# Renamed ivata groupware.
#
# Revision 1.2  2004/03/11 21:13:49  colinmacleod
# Fixed bugs in the default settings.
#
# Revision 1.1  2004/03/07 16:52:54  colinmacleod
# Added to centralize HSQL script settings.
#
################################################################################

                 ########### CHANGE THESE ONES!!!! ###########
# Uncomment these lines here and change the values to match your environment.
# JAVA_HOME=/usr/share/java
# JAVA_OPTS=""
# JBOSS_HOME=/usr/local/jboss
# OP_HOME=/usr/local/openportal
                 ########### CHANGE THESE ONES!!!! ###########

################################################################################
# settings which don't normally change below this point
CLASSPATH="$JBOSS_HOME/server/default/lib/hsqldb.jar"
DB_BASE="//localhost:1701:portal"
DB_URL="jdbc:hsqldb:hsql:"
################################################################################
# default values below this point: you really shouldn't need to change this
if [ "x$JAVA_HOME" = "x" ]; then
    JAVA_HOME=/usr/share/java
fi
if [ "x$JBOSS_HOME" = "x" ]; then
    JBOSS_HOME=/usr/local/jboss
fi
if [ "x$OP_HOME" = "x" ]; then
    OP_HOME=/usr/local/openportal
fi
if [ "x$OPHSQL_HOME" = "x" ]; then
    OPHSQL_HOME="$OP_HOME/db/hsql"
fi
################################################################################
# put out the settings so you can see what's happening when you run the scripts
echo
echo "HSQLDB is using the following settings:"
echo " ... CLASSPATH:   "$CLASSPATH
echo " ... JAVA_HOME:   "$JAVA_HOME
echo " ... JAVA_OPTS:   "$JAVA_OPTS
echo " ... JBOSS_HOME:  "$JBOSS_HOME
echo " ... OP_HOME:     "$OP_HOME
echo " ... OPHSQL_HOME: "$OPHSQL_HOME
echo
################################################################################


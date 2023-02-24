#!/bin/bash
################################################################################
# $Id: exec.sh,v 1.2 2005/04/09 17:19:53 colinmacleod Exp $
#
# Execute another script via sudo.
#
# NOTE: you must set the environment variable SUDO_USER to an sudo user
# and SUDO_PATH to a destination path for the scripts, excluding the
# final slash,
#
# This script will look for a script with the same name as that used to call
# this script, only at the target location. If you make a symlink to this file
# and call it 'addUser', then addUser will be executed thru sudo
#
# Since: ivata groupware 0.9 (2002-09-11)
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
# $Log: exec.sh,v $
# Revision 1.2  2005/04/09 17:19:53  colinmacleod
# Changed copyright text to GPL v2 explicitly.
#
# Revision 1.1.1.1  2005/03/10 17:49:20  colinmacleod
# Restructured ivata op around Hibernate/PicoContainer.
# Renamed ivata groupware.
#
# Revision 1.1  2004/02/05 21:54:57  colinmacleod
# Moved from webmail subproject.
#
# Revision 1.1.1.1  2004/01/27 20:59:58  colinmacleod
# Moved ivata op to sourceforge.
#
# Revision 1.2  2003/10/15 14:11:33  colin
# fixing for XDoclet
#
# Revision 1.1  2003/02/24 19:09:24  colin
# moved to business
#
# Revision 1.1  2002/10/28 10:33:16  jano
# *** empty log message ***
#
################################################################################

SUDO="/usr/bin/sudo"
SUDO_FLAGS=

#echo "$SUDO -u $SUDO_USER $SUDO_PATH/`basename $0` $*"  > /tmp/w
cd $SUDO_PATH && $SUDO -u $SUDO_USER $SUDO_PATH/`basename $0` $*

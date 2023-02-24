#!/bin/sh
###############################################################################
# $Id: exec.sh,v 1.2 2005/04/09 17:19:53 colinmacleod Exp $
#
# Execute another script via ssh. This is used where the mail server is not
# at the same location as the running application server.
#
# NOTE: you must set the environment variable SSH_USER to an ssh user
# and host, and SSH_PATH to a destination path for the scripts, excluding the
# final slash,
# e.g. SSH_USER=root@mailserver, SSH_PATH=/usr/local/scripts.
#
# This script will look for a script with the same name as that used to call
# this script, only at the target location. If you make a symlink to this file
# and call it 'addUser', then addUser will be executed at the remoteaddress
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
# Revision 1.1  2004/02/05 21:54:42  colinmacleod
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
# Revision 1.3  2003/02/20 16:01:37  colin
# overwriting mistake
#
# Revision 1.1  2002/10/28 10:36:09  jano
# general script
#
###############################################################################

SSH="/usr/bin/ssh -C"
SSH_FLAGS=
#echo "USER:$SSH_USER; PATH:$SSH_PATH" 1>&2
$SSH $SSH_FLAGS $SSH_USER "cd $SSH_PATH && ./`basename $0`" $*
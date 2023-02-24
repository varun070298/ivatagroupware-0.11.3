#!/bin/bash
################################################################################
# $Id: clearLinks.sh,v 1.2 2005/04/09 17:19:53 colinmacleod Exp $
#
# Delete symlinks from each of the directories apart from exim
#
# Since: ivata groupware 0.9 (2004-02-05)
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
# $Log: clearLinks.sh,v $
# Revision 1.2  2005/04/09 17:19:53  colinmacleod
# Changed copyright text to GPL v2 explicitly.
#
# Revision 1.1.1.1  2005/03/10 17:49:20  colinmacleod
# Restructured ivata op around Hibernate/PicoContainer.
# Renamed ivata groupware.
#
# Revision 1.1  2004/02/05 21:53:13  colinmacleod
# Added clearLinks.sh/makeLinks.sh
#
################################################################################

cd `dirname $0`

if test -d exim; then
    echo "This program clears links created by makeLinks.sh."
else
    echo "No directory called exim! Cannot clear script links.";
    exit -1;
fi

for script in exim/*;
do
    script=$(echo $script | sed s/^exim.//)
    if [ "$script" != CVS ]; then
        for directory in *;
        do
            if [[ "$directory" != CVS && "$directory" != exim && "$directory" != makeLinks.sh && "$directory" != clearLinks.sh ]]; then
                if [[ -h "$directory/$script" ]]; then
                    echo "Removing $directory/$script"
                    rm "$directory/$script"
                fi
            fi
        done
    fi
done


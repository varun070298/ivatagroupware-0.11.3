#!/bin/sh
################################################################################
#
# Deploy the whole site documentation. This is more efficient than
# maven site:sshdeploy on my shared broadband connection :-)
#
# Since: ivata groupware 0.10
# Author: Colin MacLeod <colin.macleod@ivata.com>
# $Revision: 1.1.1.1 $
#
################################################################################
# Copyright (c) 2001 - 2005 ivata limited.
# All rights reserved.
################################################################################

# fix the changes page - it removes spaces from after tags
perl -p -i -e "s/([<][\\/][^>]*[>])([^.,:;\\s])/\$1 \$2/g" target/docs/changes-report.html

rsync -e /usr/local/bin/ssh-C -av target/docs/* root@ivata.com:/home/sites/groupware.ivata.org/war
ssh -C root@ivata.com /bin/chmod -R a+rX /home/sites/groupware.ivata.org/war
################################################################################


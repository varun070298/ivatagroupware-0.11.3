#!/bin/bash
################################################################################
#
# Deploy the whole site
#
# Since: ivata groupware 0.10
# Author: Colin MacLeod <colin.macleod@ivata.com>
# $Revision: 1.4 $
#
################################################################################
# Copyright (c) 2001 - 2005 ivata limited.
# All rights reserved.
################################################################################

#rsync -e /usr/local/bin/ssh-C -av --exclude=WEB-INF/web.xml --exclude=WEB-INF/tag --exclude=WEB-INF/logs --exclude=WEB-INF/classes --exclude=WEB-INF/work --exclude=WEB-INF/tmp target/ivatagroupware-war/* root@ivata.com:/home/sites/groupware.ivata.org/demo
#ssh -C root@ivata.com /bin/chmod -R a+rX /home/sites/groupware.ivata.org/demo
rsync -e /usr/local/bin/ssh-C -av --delete --exclude=WEB-INF/classes/hibernate.cfg.xml --exclude=WEB-INF/web.xml --exclude=WEB-INF/logs --exclude=WEB-INF/work --exclude=WEB-INF/tmp target/ivatagroupware-war/* root@ivata.com:/usr/local/resin3/webapps/demo
ssh -C root@ivata.com /bin/chmod -R a+rX /usr/local/resin3/webapps/demo
################################################################################


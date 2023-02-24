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
 * $Log: HibernateQuery.java,v $
 * Revision 1.2  2005/04/09 17:19:37  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1  2005/03/10 19:23:04  colinmacleod
 * Moved to ivata groupware.
 *
 * Revision 1.1  2004/07/13 19:42:44  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.container.persistence.hibernate;

import java.util.Map;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since Mar 27, 2004
 * @version $Revision: 1.2 $
 */
public class HibernateQuery {
    String queryString;
    Map arguments;

    public HibernateQuery(String queryString, Map arguments) {
        super();
        this.queryString = queryString;
        this.arguments = arguments;
    }
    String getQueryString() {
        return queryString;
    }
    Map getArguments() {
        return arguments;
    }

}

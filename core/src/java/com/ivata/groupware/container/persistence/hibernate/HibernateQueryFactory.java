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
 * $Log: HibernateQueryFactory.java,v $
 * Revision 1.3  2005/04/10 20:09:43  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
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

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since Mar 28, 2004
 * @version $Revision: 1.3 $
 */
public class HibernateQueryFactory implements Serializable {
    /**
     * Map of query names to the string queries which will be passed to
     * hibernate.
     */
    private Map queries;
    /**
     * Stores the order of arguments when passed as an object array.
     */
    private Map queryArguments;

    /**
     * Construct a new hibernate query factory with the given queries and
     * default argument order.
     */
    public HibernateQueryFactory(Map queries, Map queryArguments) {
        super();
        this.queries = queries;
        this.queryArguments = queryArguments;
    }

    /**
     * @see com.ivata.groupware.container.persistence.PersistenceQueryFactory#generateQuery(String, java.util.List)
     */
    public HibernateQuery generateQuery(final String name,
            final Map arguments) {
        String query = (String) queries.get(name);
        if (query == null) {
            throw new NullPointerException("Error in HibernateQueryFactory: no query called '"
                + name
                +"'");
        }
        return new HibernateQuery(query, arguments);
    }

    /**
     * @see com.ivata.groupware.container.persistence.PersistenceQueryFactory#generateQuery(String, Object[])
     */
    public HibernateQuery generateQuery(final String name,
            final Object[] arguments) {
        Object argumentNamesObject = queryArguments.get(name);
        String[] argumentNames;
        if (argumentNamesObject instanceof List) {
            argumentNames = (String[]) ((List) argumentNamesObject)
                .toArray(new String[] {});
        } else {
            assert (argumentNamesObject instanceof String[]);
            argumentNames = (String[]) argumentNamesObject;
        }
        if (argumentNames == null) {
            throw new NullPointerException("Error in HibernateQueryFactory: no query called '"
                + name
                +"'");
        }
        if(arguments.length > argumentNames.length) {
            throw new RuntimeException("Error in HibernateQueryFactory: too many arguments ("
                + arguments.length
                + ") provided - expected "
                + argumentNames.length);
        }
        Map argumentMap = new HashMap();
        for (int i = 0; i < arguments.length; i++) {
            String argumentName = argumentNames[i];
            Object argument = arguments[i];
            argumentMap.put(argumentName, argument);
        }
        return generateQuery(name, argumentMap);
    }
}

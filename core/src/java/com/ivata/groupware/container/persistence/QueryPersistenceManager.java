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
 * $Log: QueryPersistenceManager.java,v $
 * Revision 1.2  2005/04/09 17:19:37  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1  2005/03/10 19:23:04  colinmacleod
 * Moved to ivata groupware.
 *
 * Revision 1.1  2004/11/12 16:08:12  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/08/01 11:55:03  colinmacleod
 * Added removeAll.
 *
 * Revision 1.1  2004/07/13 19:42:44  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.container.persistence;

import java.util.List;

import com.ivata.groupware.container.persistence.listener.AddPersistenceListener;
import com.ivata.groupware.container.persistence.listener.AmendPersistenceListener;
import com.ivata.groupware.container.persistence.listener.RemovePersistenceListener;
import com.ivata.mask.persistence.PersistenceException;
import com.ivata.mask.persistence.PersistenceManager;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.valueobject.ValueObject;

/**
 * Extends the <strong>ivata masks</strong> persistence manager to include
 * facilities to execute queries against the persistence store, and adds
 * listeners.
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since Mar 27, 2004
 * @version $Revision: 1.2 $
 */
public interface QueryPersistenceManager extends PersistenceManager {
    void addAddListener(Class dOClass, AddPersistenceListener listener);
    void addAmendListener(Class dOClass, AmendPersistenceListener listener);
    void addRemoveListener(Class dOClass, RemovePersistenceListener listener);
    List find(final PersistenceSession session,
            final String queryName,
            final Object [] queryArguments) throws PersistenceException;
    List find(final PersistenceSession session,
            final String queryName,
            final Object [] queryArguments,
            final Integer pageSize,
            final Integer pageNumber) throws PersistenceException;
    ValueObject findInstance(final PersistenceSession session,
            final String queryName,
            final Object [] queryArguments) throws PersistenceException;
    Integer findInteger(final PersistenceSession session,
            final String queryName,
            final Object [] queryArguments) throws PersistenceException;
    String findString(final PersistenceSession session,
            final String queryName,
            final Object [] queryArguments) throws PersistenceException;
    void remove(final PersistenceSession session,
            final ValueObject valueObject) throws PersistenceException;
    void removeAll(final PersistenceSession session,
            final String queryName,
            final Object [] queryArguments) throws PersistenceException;
}

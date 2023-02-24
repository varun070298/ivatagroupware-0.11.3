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
 * $Log: HibernateManager.java,v $
 * Revision 1.5  2005/04/29 02:48:15  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.4  2005/04/10 20:43:09  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.3  2005/04/09 17:19:37  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.2  2005/03/16 15:46:44  colinmacleod
 * Added flush after save.
 *
 * Revision 1.1  2005/03/10 19:23:04  colinmacleod
 * Moved to ivata groupware.
 *
 * Revision 1.5  2004/12/31 18:43:17  colinmacleod
 * Added class checking of key (id) type for ivata masks.
 *
 * Revision 1.4  2004/11/12 15:57:10  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.3  2004/09/30 15:15:55  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.2  2004/08/01 11:55:02  colinmacleod
 * Added removeAll.
 *
 * Revision 1.1  2004/07/13 19:42:44  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.container.persistence.hibernate;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.ObjectNotFoundException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;

import org.apache.log4j.Logger;

import com.ivata.groupware.container.persistence.QueryPersistenceManager;
import com.ivata.groupware.container.persistence.listener.AddPersistenceListener;
import com.ivata.groupware.container.persistence.listener.AmendPersistenceListener;
import com.ivata.groupware.container.persistence.listener.RemovePersistenceListener;
import com.ivata.mask.persistence.FinderException;
import com.ivata.mask.persistence.PersistenceException;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.persistence.right.PersistenceRights;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.valueobject.ValueObject;

/**
 * <p>
 * This class provides a persistence manager for the whole
 * <strong>ivata groupware</strong> project. Every time a data object should be stored
 * to or retrieved from the data store, it happens here.
 * </p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since Mar 27, 2004
 * @version $Revision: 1.5 $
 */
public class HibernateManager implements QueryPersistenceManager, Serializable {
    /**
     * Logger for this class.
     */
    private static final Logger logger = Logger
            .getLogger(HibernateManager.class);

    /**
     * Tells us what we can and can't do!
     */
    private PersistenceRights persistenceRights;
    /**
     * <p>
     * This object is used to create the queries from strings and arguments.
     * </p>
     */
    private HibernateQueryFactory queryFactory;
    /**
     * <p>
     * Hibernate session factory.
     * </p>
     */
    private SessionFactory sessionFactory;

    /**
     * <p>
     * One and only constructor - called by Pico. Uses constructor arguments
     * to initialize the whole object.
     * </p>
     *
     * @param sessionFactoryParam Used to create <strong>Hibernate</strong> sessions!
     * @param queryFactoryParam This object is used to create the queries from
     * strings and arguments.
     * @param interceptorParam
     */
    public HibernateManager(final SessionFactory sessionFactoryParam,
            final HibernateQueryFactory queryFactoryParam,
            final PersistenceRights persistenceRightsParam) {
        this.sessionFactory = sessionFactoryParam;
        this.queryFactory = queryFactoryParam;
        this.persistenceRights = persistenceRightsParam;
    }

    /**
     * <p>
     * Add a new data object to the store.
     * </p>
     *
     * @param session Open, valid persistence session used to store the object.
     * @param valueObject Data object to be stored.
     */
    public ValueObject add(final PersistenceSession session,
            final ValueObject valueObject) throws PersistenceException {
        HibernateSession hibernateSession = (HibernateSession) session;
        Session wrappedSession = hibernateSession.getSession();
        Transaction wrappedTransaction = hibernateSession.getTransaction();

        try {
            wrappedSession.save(valueObject);
            wrappedSession.flush();
        } catch (Exception e) {
            logger.error("Exception in add.", e);
            try {
                wrappedTransaction.rollback();
            } catch (HibernateException e2) {
                throw new PersistenceException("Error rolling back a transaction",
                    e2);
            }
            throw new PersistenceException("Error saving a new instance of class '"
                + valueObject.getClass().getName()
                +"'",
                e);
        }
        return valueObject;
    }
    /**
     * Refer to {@link }.
     *
     * @param dOClass
     * @param listener
     * @see com.ivata.groupware.container.persistence.QueryPersistenceManager#addAddListener(java.lang.Class, com.ivata.groupware.container.persistence.listener.AddPersistenceListener)
     */
    public void addAddListener(Class dOClass, AddPersistenceListener listener) {
        HibernateInterceptor.addAddListener(dOClass, listener);
    }


    /**
     * Refer to {@link }.
     *
     * @param dOClass
     * @param listener
     * @see com.ivata.groupware.container.persistence.QueryPersistenceManager#addAmendListener(java.lang.Class, com.ivata.groupware.container.persistence.listener.AddPersistenceListener)
     */
    public void addAmendListener(Class dOClass, AmendPersistenceListener listener) {
        HibernateInterceptor.addAmendListener(dOClass, listener);
    }


    /**
     * Refer to {@link }.
     *
     * @param dOClass
     * @param listener
     * @see com.ivata.groupware.container.persistence.QueryPersistenceManager#addRemoveListener(java.lang.Class, com.ivata.groupware.container.persistence.listener.AddPersistenceListener)
     */
    public void addRemoveListener(Class dOClass, RemovePersistenceListener listener) {
        HibernateInterceptor.addRemoveListener(dOClass, listener);
    }

    /**
     * <p>
     * Change the values of a data object which already exists in the data
     * store.
     * </p>
     *
     * @param session Open, valid persistence session used to update the object.
     * @param baseDO Data object to be modified.
     */
    public void amend(final PersistenceSession session,
            final ValueObject baseDO) throws PersistenceException {
        String className = baseDO.getClass().getName();
        HibernateSession hibernateSession = (HibernateSession) session;
        Session wrappedSession = hibernateSession.getSession();
        Transaction wrappedTransaction = hibernateSession.getTransaction();

        try {
            wrappedSession.update(baseDO);
        } catch (Exception e) {
            logger.error("Exception in amend.", e);
            try {
                wrappedTransaction.rollback();
            } catch (HibernateException e2) {
                throw new PersistenceException("Error rolling back a transaction",
                    e2);
            }
            throw new PersistenceException("Error updating an instance of class '"
                + baseDO.getClass().getName()
                +"' with id '"
                + baseDO.getIdString()
                + "'",
                e);
        }
    }

    /**
     * <p>
     * Internal helper method. This one does all the hard work for retrieving
     * objects or deleting multiple objects at once.
     * </p>
     *
     * @param session Open, valid persistence session.
     * @param queryName Name of the query within the query factory.
     * @param queryArguments Arguments that apply to this query.
     * @param pageSize If multiple rows should be returned, this indicates the
     * maximum number to return. Set to <code>null</code> if a single instance
     * should be returned, or if all instances are needed.
     * @param pageNumber It is possible to select a subset of all matching rows
     * by setting this parameter to a number greater than zero. This is used
     * together with the <code>pageSize</code> argument. Set to
     * <code>null</code> for a single result, or <code>0</code> to return the
     * first page of results, or all results (with <code>pageNumber</code>
     * <code>null</code>.).
     * @param delete If <code>true</code>, all entries returned by this query
     * will be deleted.
     * @return
     * @throws PersistenceException
     */
    private Object execute(final PersistenceSession session,
            final String queryName,
            final Object[] queryArguments,
            final Integer pageSize,
            final Integer pageNumber,
            final boolean delete) throws PersistenceException {
        HibernateSession hibernateSession = (HibernateSession) session;
        Session wrappedSession = hibernateSession.getSession();

        Object object = null;
        HibernateQuery hibernateQuery = (HibernateQuery) queryFactory.generateQuery(queryName,
            queryArguments);
        Query q;
        try {
            q = wrappedSession.createQuery(hibernateQuery.getQueryString());
            Map arguments = hibernateQuery.getArguments();
            Set keys = arguments.keySet();
            for (Iterator keyIterator = keys.iterator(); keyIterator.hasNext();) {
                String key = (String) keyIterator.next();
                Object value = arguments.get(key);
                q.setParameter(key, value);
            }
        } catch (HibernateException e) {
            logger.error("Exception in execute.", e);
            throw new PersistenceException("Error retrieving object "
                + "with query '"
                + hibernateQuery.getQueryString()
                + "', "
                + hibernateQuery.getArguments(),
                e);
        }

        // if delete was specified, or the page number was set, expect multiple
        // results
        if (delete
                || (pageNumber != null)) {
            List results;
            try {
                // if you specified a page size, limit the results
                if ((pageSize != null)
                        && (pageNumber != null)) {
                    q.setMaxResults(pageSize.intValue());
                    q.setFirstResult(pageSize.intValue() * pageNumber.intValue());
                }
                results = q.list();
            } catch (HibernateException e) {
                throw new PersistenceException(queryName, e);
            }
            // if delete was specified, just discard the results
            if (delete) {
                results.clear();
                results = null;
            } else {
                object = results;
            }

        // otherwise we're trying to retrieve a single instance
        } else {
            try {
                object = q.uniqueResult();
            } catch (HibernateException e) {
                throw new PersistenceException(queryName, e);
            }
            if (object == null) {
                throw new FinderException(queryName, queryArguments);
            }
        }
        return object;
    }

    /**
     * <p>
     * Find all results which match the given query.
     * </p>
     *
     * @param session Open, valid persistence session.
     * @param queryName Name of the query within the query factory.
     * @param queryArguments Arguments that apply to this query.
     */
    public List find(final PersistenceSession session,
            final String queryName,
            final Object[] queryArguments) throws PersistenceException {
        return find(session, queryName, queryArguments, null, new Integer(0));
    }


    /**
     * <p>
     * Find all results which match the given query.
     * </p>
     *
     * @param session Open, valid persistence session.
     * @param queryName Name of the query within the query factory.
     * @param queryArguments Arguments that apply to this query.
     * @param pageSize This indicates the maximum number of results to return.
     * Set to <code>null</code> if all instances are needed.
     * @param pageNumber It is possible to select a subset of all matching rows
     * by setting this parameter to a number greater than zero. This is used
     * together with the <code>pageSize</code> argument. Set to
     * <code>0</code> to return the
     * first page of results, or all results (with <code>pageNumber</code>
     * <code>null</code>.).
     */
    public List find(final PersistenceSession session,
            final String queryName,
            final Object[] queryArguments,
            final Integer pageSize,
            final Integer pageNumber) throws PersistenceException {
        return (List) execute(session, queryName, queryArguments, pageSize,
                pageNumber, false);
    }


    /**
     * <p>
     * Find all results which have the given class.
     * </p>
     *
     * @param session Open, valid persistence session.
     * @param dOClass Class of the data objects to return.
     */
    public List findAll(final PersistenceSession session,
            final Class dOClass) throws PersistenceException {
        HibernateSession hibernateSession = (HibernateSession) session;
        Session wrappedSession = hibernateSession.getSession();

        List results = null;
        try {
            results = wrappedSession.find("from "
                + dOClass.getName());
        } catch (HibernateException e) {
            logger.error("Exception in findAll.", e);
            throw new PersistenceException("Error retrieving all objects from "
                + dOClass.getName(),
                e);
        }
        return results;
    }

    /**
     * <p>
     * Find a single result given the class and unique identifier.
     * </p>
     *
     * @param session Open, valid persistence session.
     * @param dOClass Class of the data object to return.
     * @param key Unique identifier of the data object to return.
     */
    public ValueObject findByPrimaryKey(final PersistenceSession session,
            final Class dOClass,
            final Serializable key) throws PersistenceException {
        HibernateSession hibernateSession = (HibernateSession) session;
        Session wrappedSession = hibernateSession.getSession();

        if (key == null) {
            throw new PersistenceException(dOClass.getName()
                + ": key is null");
        }

        ValueObject baseDO = null;
        Transaction transaction;
        try {
            // TODO: at the moment, everything is indexed as an string - this
            // probably needs to be abstracted somehow
            Integer id = StringHandling.integerValue(key.toString());
            baseDO = (ValueObject) wrappedSession.load(dOClass, id);
        } catch (ObjectNotFoundException e) {
            throw new FinderException(dOClass, key, e);
        } catch (HibernateException e) {
            logger.error("Exception in findByPrimaryKey.", e);
            throw new PersistenceException(e);
        }
        if (baseDO == null) {
            throw new FinderException(dOClass, key, null);
        }
        return baseDO;
    }


    /**
     * <p>
     * Find a single result which matches the given query.
     * </p>
     *
     * @param session Open, valid persistence session.
     * @param queryName Name of the query within the query factory.
     * @param queryArguments Arguments that apply to this query.
     */
    public ValueObject findInstance(final PersistenceSession session,
            final String queryName,
            final Object[] queryArguments) throws PersistenceException {
        return (ValueObject) execute(session, queryName, queryArguments, null, null, false);
    }

    /**
     * <p>
     * Find a single integer result which matches the given query.
     * </p>
     *
     * @param session Open, valid persistence session.
     * @param queryName Name of the query within the query factory.
     * @param queryArguments Arguments that apply to this query.
     */
    public Integer findInteger(final PersistenceSession session,
            final String queryName,
            final Object[] queryArguments) throws PersistenceException {
        return (Integer) execute(session, queryName, queryArguments, null, null, false);
    }


    /**
     * <p>
     * Find a single string result which matches the given query.
     * </p>
     *
     * @param session Open, valid persistence session.
     * @param queryName Name of the query within the query factory.
     * @param queryArguments Arguments that apply to this query.
     */
    public String findString(final PersistenceSession session,
            final String queryName,
            final Object[] queryArguments) throws PersistenceException {
        return (String) execute(session, queryName, queryArguments, null, null, false);
    }
    /**
     * Tells us what we can and can't do!
     *
     * @return Returns the persistence rights.
     */
    public PersistenceRights getPersistenceRights() {
        return persistenceRights;
    }


    /**
     * Refer to {@link }.
     *
     * @return
     * @throws PersistenceException
     * @see com.ivata.mask.persistence.PersistenceManager#openSession()
     */
    public PersistenceSession openSession() throws PersistenceException {
        return openSession(null);
    }


    /**
     * <p>
     * Open a new persistence session.
     * </p>
     *
     * @return New session which can be used in future queries.
     */
    public PersistenceSession openSession(Object systemSession)
            throws PersistenceException {
        try {
            HibernateInterceptor interceptor = new HibernateInterceptor(this,
                    sessionFactory);
            Session session = sessionFactory.openSession(interceptor);
            HibernateSession hibernateSession = new HibernateSession(session,
                    session.beginTransaction(),
                    systemSession);
            interceptor.setHibernateSession(hibernateSession);
            return hibernateSession;
        } catch (HibernateException e) {
            logger.error("Exception in openSession.", e);
            throw new PersistenceException("Error creating a new hibernate session.",
                e);
        }
    }

    /**
     * <p>
     * Remove a single object from the data store.
     * </p>
     *
     * @param session Open, valid persistence session.
     * @param dOClass Class of the data object to remove.
     * @param key Unique identifier of the data object to remove.
     */
    public void remove(final PersistenceSession session,
            final Class dOClass,
            final Serializable key) throws PersistenceException {
        HibernateSession hibernateSession = (HibernateSession) session;
        Session wrappedSession = hibernateSession.getSession();
        Transaction wrappedTransaction = hibernateSession.getTransaction();
        try {
            wrappedSession.delete("from "
                + dOClass.getName()
                + " dO where dO.id = "
                + key);
        } catch (Exception e) {
            logger.error("Exception in remove.", e);
            try {
                wrappedTransaction.rollback();
            } catch (HibernateException e2) {
                throw new PersistenceException("Error rolling back a transaction",
                    e2);
            }
            throw new PersistenceException("Error removing an instance of class '"
                + dOClass.getName()
                +"' with id '"
                + key
                + "'",
                e);
        }
    }

    /**
     * <p>
     * Remove a single object from the data store.
     * </p>
     *
     * @param session Open, valid persistence session.
     * @param baseDO Data object to be removed.
     */
    public void remove(final PersistenceSession session,
            final ValueObject valueObject) throws PersistenceException {
        remove(session, valueObject.getClass(), valueObject.getIdString());
    }

    /**
     * Refer to {@link QueryPersistenceManager#removeAll}.
     *
     * @param session Refer to {@link QueryPersistenceManager#removeAll}.
     * @param queryName Refer to {@link QueryPersistenceManager#removeAll}.
     * @param queryArguments Refer to {@link QueryPersistenceManager#removeAll}.
     * @throws PersistenceException
     * Refer to {@link QueryPersistenceManager#removeAll}.
     * TODO: this doesn't notify listeners!!
     */
    public void removeAll(final PersistenceSession session,
            final String queryName,
            final Object[] queryArguments) throws PersistenceException {
        execute(session, queryName, queryArguments, null, null, true);
    }

}

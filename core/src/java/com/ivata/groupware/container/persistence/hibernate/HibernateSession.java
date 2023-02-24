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
 * $Log: HibernateSession.java,v $
 * Revision 1.2  2005/04/09 17:19:37  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1  2005/03/10 19:23:04  colinmacleod
 * Moved to ivata groupware.
 *
 * Revision 1.3  2004/11/12 15:57:11  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/08/01 11:54:50  colinmacleod
 * Improved transaction handling.
 *
 * Revision 1.1  2004/07/13 19:42:44  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.container.persistence.hibernate;

import java.sql.Connection;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import org.apache.log4j.Logger;

import com.ivata.mask.persistence.PersistenceException;
import com.ivata.mask.persistence.PersistenceSession;

/**
 * <p>
 * Adaptor to wrap the <strong>Hibernate</strong> session.
 * </p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since Mar 27, 2004
 * @version $Revision: 1.2 $
 */
public class HibernateSession implements PersistenceSession {
    /**
     * Refer to {@link Logger}.
     */
    private static Logger log = Logger.getLogger(HibernateSession.class);
    /**
     * If <code>true</code> the transaction will be rolled back.
     */
    private boolean cancel = false;
    /**
     * <p>
     * The Hibernate session we're adapting.
     * </p>
     */
    private Session session;

    private Object systemSession;

    /**
     * Hibernate transaction being wrapped.
     */
    private Transaction transaction;

    /**
     * Create a new instance wrapping the given hibernate session, and
     * transaction,
     */
    public HibernateSession(Session sessionParam, Transaction transactionParam,
            Object systemSessionParam) {
        this.session = sessionParam;
        this.transaction = transactionParam;
        this.systemSession = systemSessionParam;
    }

    /**
     * Refer to {@link com.ivata.mask.persistence.PersistenceSession#cancel}.
     *
     * @throws PersistenceException
     * Refer to {@link com.ivata.mask.persistence.PersistenceSession#cancel}.
     */
    public void cancel() throws PersistenceException {
        cancel = true;
    }

    /**
     * @see com.ivata.mask.persistence.PersistenceSession#commit()
     */
    public void close() throws PersistenceException {
        HibernateException hibernateException = null;
        if (cancel) {
            try {
                transaction.rollback();
            } catch (HibernateException e) {
                log.error("("
                        + e.getClass().getName()
                        + ") ROLLING BACK TRANSACTION: "
                        + e.getMessage(), e);
            } finally {
                try {
                    session.close();
                } catch (HibernateException e) {
                    if (hibernateException != null) {
                        hibernateException = e;
                    }
                }
            }

        } else {
            try {
                if (!transaction.wasRolledBack()) {
                    transaction.commit();
                }
            } catch (HibernateException e) {
                hibernateException = e;
                try {
                    transaction.rollback();
                } catch (Exception eRollback) {
                    log.error("("
                            + e.getClass().getName()
                            + ") ROLLING BACK TRANSACTION: "
                            + e.getMessage(), e);
                }
            } finally {
                try {
                    session.close();
                } catch (HibernateException e) {
                    if (hibernateException != null) {
                        hibernateException = e;
                    }
                }
            }
        }
        if (hibernateException != null) {
            throw new PersistenceException("Error closing hibernate persistence session: ",
                hibernateException);
        }
    }

    /**
     * TODO
     *
     * @see com.ivata.mask.persistence.PersistenceSession#getConnection()
     */
    public final Connection getConnection() throws PersistenceException {
        try {
            return session.connection();
        } catch (HibernateException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Get the hibernate session this session adapts.
     *
     * @param session valid hibernate session.
     */
    Session getSession() {
        return session;
    }

    /**
     * @return Returns the systemSession.
     */
    public Object getSystemSession() {
        return systemSession;
    }
    /**
     * @return transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }
}

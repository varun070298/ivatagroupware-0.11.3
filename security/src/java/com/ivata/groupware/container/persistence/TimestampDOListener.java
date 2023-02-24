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
 * $Log: TimestampDOListener.java,v $
 * Revision 1.2  2005/04/09 17:19:58  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:32  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.container.persistence;

import org.apache.log4j.Logger;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.container.persistence.listener.AddPersistenceListener;
import com.ivata.groupware.container.persistence.listener.AmendPersistenceListener;
import com.ivata.mask.persistence.PersistenceException;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.valueobject.ValueObject;

/**
 * This class handles the timestamp properties when objects are added to or
 * amended in the store.
 * @since ivata groupware 0.10 (2005-01-17)
 * @author Colin MacLeod
 * <a href="mailto:colin.macleod@ivata.com">colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public class TimestampDOListener implements AddPersistenceListener,
        AmendPersistenceListener {
    /**
     * Refer to {@link Logger}.
     */
    private Logger log = Logger.getLogger(TimestampDOListener.class);
    /**
     * Constructor. Registers this listener with the persistence manager.
     * @param persistenceManager used to register this listener against
     * subclasses of <code>TimestampDO</code>.
     */
    public TimestampDOListener(QueryPersistenceManager persistenceManager) {
        persistenceManager.addAddListener(TimestampDO.class,
                this);
        persistenceManager.addAmendListener(TimestampDO.class,
                this);
    }

    /**
     * Refer to {@link AddPersistenceListener#onAdd}.
     *
     * @param session Refer to {@link AddPersistenceListener#onAdd}.
     * @param valueObject Refer to {@link AddPersistenceListener#onAdd}.
     * @throws PersistenceException Refer to {@link
     * AddPersistenceListener#onAdd}.
     */
    public void onAdd(PersistenceSession session, ValueObject valueObject)
            throws PersistenceException {
        if (log.isDebugEnabled()) {
            log.debug("onAdd: before: "
                    + valueObject);
        }
        TimestampDO timestampDO = (TimestampDO)valueObject;
        SecuritySession securitySession = (SecuritySession)
            session.getSystemSession();
        assert (securitySession != null);
        TimestampDOHandling.add(securitySession, timestampDO);
        if (log.isDebugEnabled()) {
            log.debug("onAdd: after: "
                    + valueObject);
        }
    }

    /**
     * Refer to {@link AmendPersistenceListener#onAmend}.
     *
     * @param session Refer to {@link AmendPersistenceListener#onAmend}.
     * @param valueObject Refer to {@link AmendPersistenceListener#onAmend}.
     * @throws PersistenceException Refer to {@link
     * AmendPersistenceListener#onAmend}.
     */
    public void onAmend(PersistenceSession session, ValueObject valueObject)
            throws PersistenceException {
        if (log.isDebugEnabled()) {
            log.debug("onAmend: before: "
                    + valueObject);
        }
        TimestampDO timestampDO = (TimestampDO)valueObject;
        SecuritySession securitySession = (SecuritySession)
            session.getSystemSession();
        assert (securitySession != null);
        TimestampDOHandling.amend(securitySession, timestampDO);
        if (log.isDebugEnabled()) {
            log.debug("onAmend: after: "
                    + valueObject);
        }
    }
}

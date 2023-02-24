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
 * $Log: AmendPersistenceListener.java,v $
 * Revision 1.2  2005/04/09 17:19:37  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1  2005/03/10 19:23:05  colinmacleod
 * Moved to ivata groupware.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.container.persistence.listener;

import com.ivata.mask.persistence.PersistenceException;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.valueobject.ValueObject;

/**
 * Called when a value object is about to be amended.
 * @since ivata groupware 0.10 (2005-01-17)
 * @author Colin MacLeod
 * <a href="mailto:colin.macleod@ivata.com">colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public interface AmendPersistenceListener {
    /**
     * Called just before a previously stored value object is amended, after
     * changes have been made.
     *
     * @param session
     *            current persistence session. Should have been previously
     *            opened with {@link #openSession() openSession()}.
     * @param valueObject
     *            value object for which changes should be saved.
     * @throws PersistenceException
     *             if the object cannot be persisted.
     */
    void onAmend(PersistenceSession session, ValueObject valueObject)
            throws PersistenceException;
}

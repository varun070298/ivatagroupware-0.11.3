/*
 * $Id: SecurityServerException.java,v 1.2 2005/04/09 17:19:57 colinmacleod Exp $
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
 * $Log: SecurityServerException.java,v $
 * Revision 1.2  2005/04/09 17:19:57  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:41  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/12 15:57:19  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/09/30 15:15:58  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.5  2004/07/13 19:41:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:05  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/03/21 20:19:46  colinmacleod
 * Added constructor with just a string message.
 *
 * Revision 1.2  2004/03/10 22:40:22  colinmacleod
 * Changed to EJBException.
 *
 * Revision 1.1.1.1  2004/01/27 20:57:45  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.1.1.1  2003/10/13 20:50:07  colin
 * Restructured portal into subprojects
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security.server;

import com.ivata.mask.util.SystemException;


/**
 * <p>Thrown by subclasses of <code>Session</code> if a user cannot be
 * authenticated.</p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 */
public class SecurityServerException extends SystemException {

    /**
     * <p>Create a new exception which wraps the one supplied.</p>
     *
     * @param cause specific exception which caused this one.
     */
    public SecurityServerException(Exception cause) {
        super(cause);
    }
    /**
     * Create a new exception with the causual text as a string message.
     *
     * @param message information about when the error happened.
     */
    public SecurityServerException(String message) {
        super(message);
    }

    /**
     * Create a new exception, giving additional information to the supplied
     * exception which caused this to happen.
     *
     * @param message additional information about when the error happened.
     * @param cause specific exception which caused this one.
     */
    public SecurityServerException(String message, Exception cause) {
        super(message, cause);
    }
}

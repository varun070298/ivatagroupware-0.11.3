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
 * $Log: ScriptExecutor.java,v $
 * Revision 1.2  2005/04/09 17:19:56  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:37  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/11/12 18:16:07  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 15:57:18  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/11/03 16:04:14  colinmacleod
 * Cosmetic changes.
 *
 * Revision 1.1  2004/09/30 15:15:57  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.1  2004/07/13 19:12:33  colinmacleod
 * New classes as part of conversion to PicoContainer.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.script;

import com.ivata.groupware.admin.security.server.SecurityServerException;
import com.ivata.mask.util.SystemException;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Apr 9, 2004
 * @version $Revision: 1.2 $
 */
public interface ScriptExecutor {
    /**
     * <p>Execute a command and handle any error that occurs.</p>
     *
     * @param scriptName name of the script to be executed.
     * @param argv command name and all arguments of to be executed. The first
     *     argument should always be the script name
     * @throws SecurityServerException if the command returns non-zero, or if
     * there is
     * an input/output exception.
     * @return all lines of the program output as a <code>String</code>.
     */
    public String exec(final String scriptName,
            final String[] argv)
        throws SystemException;
}
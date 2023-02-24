// Source file: h:/cvslocal/ivata groupware/src/com/ivata/intranet/admin/setting/SettingsInitializationException.java

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
 * $Log: SettingsInitializationException.java,v $
 * Revision 1.2  2005/04/09 17:19:57  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:37  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/12 15:57:19  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/09/30 15:15:59  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.4  2004/07/13 19:54:31  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:05  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:46  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:04:21  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/02/25 07:39:43  colin
 * version in rose model still extends JspTagException
 * fixed again in code to RuntimeException
 *
 * Revision 1.1  2003/02/24 18:56:15  colin
 * added to admin
 *
 * Revision 1.3  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.2  2002/11/12 09:37:49  colin
 * now extends RuntimeException instead of JspTagException
 *
 * Revision 1.1  2002/06/13 11:22:10  colin
 * first version with rose model integration.
 *
 * Revision 1.1  2002/02/03 18:19:23  colin
 * added to handle exceptions in HeadTag
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.setting;

import com.ivata.mask.util.SystemException;


/**
 * <p>An instance of this class gets thrown if an attempt is made to use the
 * settings before they have been set up.</p>
 *
 * @since 2002-02-03
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @see com.ivata.groupware.admin.setting.SettingsBean
 * @version $Revision: 1.2 $
 */
public class SettingsInitializationException extends SystemException {

    /**
     * <p>Creates a new instance of SettingsInitializationException.</p>
     *
     * @param message a clear error Message indicating what should be
     * done to resolve this exception.
     *
     */
    public SettingsInitializationException(String message) {
        super(message);
    }
}

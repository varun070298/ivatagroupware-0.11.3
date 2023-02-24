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
 * $Log: SanitizeLibrary.java,v $
 * Revision 1.3  2005/04/10 20:09:45  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:47  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:07  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/11/12 18:16:06  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 15:57:16  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/11/03 15:55:46  colinmacleod
 * Changed todo comments to TODO: all caps.
 *
 * Revision 1.1  2004/09/30 14:59:06  colinmacleod
 * Added methods to sanitize the entire library and update the search index.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Category;
import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.Security;
import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.library.Library;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.SystemException;

/**
 * <p>
 * TODO
 * </p>
 *
 * @since Aug 9, 2004
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */

public class SanitizeLibrary extends HttpServlet {
    /**
     * <p>
     * <strong>Log4J</strong> logger.
     * </p>
     */
    private static Category log = Category.getInstance(SanitizeLibrary.class);

    /**
     * <p>Clean up resources.</p>
     */
    public void destroy() {}

    /**
     * <p>TODO: add a comment here.</p>
     */
    public void doGet(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {
        log.info("START Sanitize library.");

        PicoContainer container;
        try {
            container = PicoContainerFactory.getInstance()
                .getGlobalContainer();
        } catch (SystemException e) {
            throw new ServletException(e);
        }
        Security security = (Security) container.getComponentInstance(Security.class);
        try {
            // get a session container - we need a session for the date formatter
            SecuritySession session = security.loginGuest();
            container = session.getContainer();
            Library library = (Library) container.getComponentInstance(Library.class);
            library.sanitize(session);
        } catch (SystemException e) {
            e.printStackTrace();
            log.error(e);
        }

        log.info("END Sanitize library.");
    }

    /**
     * <p>Initialize global variables.</p>
     */
    public void init() throws ServletException {}

}

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
 * $Log: CheckSiteTag.java,v $
 * Revision 1.2  2005/04/09 17:19:59  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:32  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/03 16:10:11  colinmacleod
 * Changed todo comments to TODO: all caps.
 *
 * Revision 1.1  2004/09/30 15:16:02  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.4  2004/07/13 19:41:15  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:08  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:34  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:56  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.1.1.1  2003/10/13 20:50:09  colin
 * Restructured portal into subprojects
 *
 * Revision 1.3  2003/06/04 12:24:45  peter
 * fixed maintenance redirect
 *
 * Revision 1.2  2003/03/11 18:25:45  colin
 * changes to allow just one webapp
 *
 * Revision 1.1  2003/02/24 19:33:33  colin
 * moved to jsp
 *
 * Revision 1.10  2003/02/20 14:18:01  colin
 * removed s if https
 *
 * Revision 1.9  2003/02/20 13:19:47  colin
 * fixed bug in lastURL
 *
 * Revision 1.8  2003/02/20 12:34:47  colin
 * finally got maintenance page working again
 *
 * Revision 1.7  2003/02/20 10:46:07  colin
 * more bugfixes in URL code
 *
 * Revision 1.6  2003/02/20 10:36:03  colin
 * bugfixes in URL code
 *
 * Revision 1.5  2003/02/20 10:33:28  colin
 * prepended protocol
 *
 * Revision 1.4  2003/02/20 10:20:13  colin
 * added lastURL to session
 *
 * Revision 1.3  2003/02/20 10:04:25  colin
 * removed secure protocol and made it a comment
 *
 * Revision 1.2  2003/02/20 09:56:15  colin
 * bugfix in URL
 *
 * Revision 1.1  2003/02/20 09:44:12  colin
 * split off from checkLogin
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.tag;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>Check that the site is okay and is not being worked on.
 *  The presence of a file in the location
 * <code>/var/lock/ivata groupware/site_down{prefix}_down
 * indicates the site should not be displayed.<p>
 *
 * <p>{prefix} = context path of the site</p>
 *
 * <p>If the site should not be displayed, the contents of
 * <code>/maintenance.jsp</code> are shown.</p>
 *
 * @since 2003-02-20
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public class CheckSiteTag extends TagSupport {

    /**
     * <p>This method is called after the JSP engine finished processing
     * the tag.</p>
     *
     * @return <code>SKIP_PAGE</code> if the site is being
     * worked on, otherwise <code>EVAL_PAGE</code>.
     * @throws JspException if there is an error accessing the
     * maintenance page for sites which are not active.
     */
    public int doEndTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String jndiPrefix = "";
        // TODO:
        //jndiPrefix = applicationServer.getJndiPrefix();
        File siteDownFile = new File("/var/lock/ivata groupware/site_down/" +
            jndiPrefix + "_down");

        // store the last visited URL in the session - good for the error page
        String requestURL = request.getRequestURL().toString();
        if (requestURL.indexOf("error.jsp") == -1) {
            request.getSession().setAttribute("lastURL", requestURL);
        }

        // if there is no file, evaluate the rest of the page as normal
        if (!siteDownFile.exists()) {
            return EVAL_PAGE;
        }

        try {
            // if it gets here, just show the contents of the maintenance page
            // and get out
            RequestDispatcher dispatcher = pageContext.getServletContext().getRequestDispatcher("/maintenance.jsp?jndiPrefix=" + jndiPrefix);
                dispatcher.forward(request, pageContext.getResponse());
        } catch (IOException e) {
            throw new JspException(e);
        } catch (ServletException e) {
            throw new JspException(e);
        }
        return SKIP_PAGE;
    }
}

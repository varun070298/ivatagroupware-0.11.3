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
 * $Log: CheckLoginTag.java,v $
 * Revision 1.3  2005/04/10 20:10:06  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:59  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:32  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/12/31 19:29:10  colinmacleod
 * Added checking that the field writer factory is in application scope.
 *
 * Revision 1.2  2004/11/12 15:57:20  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
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
 * Revision 1.2  2004/02/01 22:00:33  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:56  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.1.1.1  2003/10/13 20:50:09  colin
 * Restructured portal into subprojects
 *
 * Revision 1.4  2003/05/27 11:37:43  peter
 * fixed: return added after dispatcher forward
 *
 * Revision 1.3  2003/05/27 10:31:03  peter
 * fixed conditions for going back to login.jsp
 *
 * Revision 1.2  2003/03/04 19:07:14  colin
 * moved charset stuff from head to checkLogin
 *
 * Revision 1.1  2003/02/24 19:33:33  colin
 * moved to jsp
 *
 * Revision 1.10  2003/02/20 09:44:24  colin
 * split off site checking code to checkSite
 *
 * Revision 1.9  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.8  2003/01/27 07:29:03  colin
 * much simplified login checking (because of keepAlive.jsp)
 *
 * Revision 1.6  2003/01/23 13:58:06  colin
 * modifications to allow URL rewriting sessions (instead of
 * cookie)
 *
 * Revision 1.5  2002/10/21 13:51:24  jano
 * name of file has changed
 *
 * Revision 1.4  2002/10/21 12:41:08  jano
 * i forgot, we have diffrent name of site
 *
 * Revision 1.3  2002/10/21 11:24:21  jano
 * check for file existing,
 * if exist - display deploing message
 *
 * Revision 1.2  2002/09/30 15:32:46  colin
 * bugfixes
 *
 * Revision 1.1  2002/09/26 08:16:19  colin
 * first version of check login tag
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.tag;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.apache.struts.taglib.TagUtils;
import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.mask.web.field.FieldWriterFactory;


/**
 *
 * <p>Check that the user is logged into the system and that the
 * session has not timed. If the session <em>has</em> timed out, then
 * this tag forwards to the login page, returning to the page where
 * this tag was upon successful login</p>
 *
 * <p>This tag should appear as the first tag in each page where we
 * want to confirm that the user is logged in. In particular, this tag
 * should appear before any object is read from the session
 * (user/settings).</p>
 * <p><strong>Tag attributes:</strong><br/>
 * <table cellpadding='2' cellspacing='5' border='0' align='center'
 * width='85%'>
 *   <tr class='TableHeadingColor'>
 *     <th>attribute</th>
 *     <th>reqd.</th>
 *     <th>param. class</th>
 *     <th width='100%'>description</th>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>forward</td>
 *     <td>true</td>
 *     <td><code>String</code></td>
 *     <td>Stores the action forward we should pass control when the
 * user logs back in after a timeout.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>forwardFormName</td>
 *     <td>true</td>
 *     <td><code>String</code></td>
 *     <td>Specifies a form name to store while the user logs back in.
 * The form is looked for in request and session scopes, in that
 * order.</td>
 *   </tr>
 * </table>
 * </p>
 *
 * @since 2002-09-25
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class CheckLoginTag extends TagSupport {
    /**
     * Refer to {@link Logger}.
     */
    private static Logger log = Logger.getLogger(CheckLoginTag.class);
    /**
     * <p>Stores the action forward we should pass control when the user
     * logs back in after a timeout.</p>
     */
    private String forward = null;
    /**
     * <p>Value to be returned by <code>doEndTag()</code>.</p>
     */
    int endTagReturn = EVAL_PAGE;

    /**
     * <p>Checks the user is logged into the system and forwards to the
     * login page if the user is not..</p>
     *
     * <p>This method is called when the JSP engine encounters the start
     * tag, after the attributes are processed.<p>
     *
     * <p>Scripting variables (if any) have their values set here.</p>
     *
     * @return <code>SKIP_BODY</code> since there is no tag body to
     * evaluate.
     * @exception JspException if there is problem forwarding to the login
     * page.
     */
    public int doStartTag() throws JspException {
        // get the http session & request first of all
        HttpSession session = pageContext.getSession();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

        // see if we have a user name and a settings object in the session
        // if not, we will have to forward to the login page
        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");
        FieldWriterFactory fieldWriterFactory = (FieldWriterFactory)
            TagUtils.getInstance().lookup(pageContext,
                FieldWriterFactory.APPLICATION_ATTRIBUTE,
                "application");
        if ((securitySession == null)
                || (fieldWriterFactory == null)) {
            RequestDispatcher dispatcher = pageContext.getServletContext()
                .getRequestDispatcher("/loginGuest.action");

            try {
                dispatcher.forward(request, pageContext.getResponse());
                return SKIP_BODY;
            } catch (Exception e) {
                log.error("CheckLoginTag: could not forward to /loginGuest.action: "
                    + e.getMessage(),
                    e);
            }
        }
        PicoContainer container = securitySession.getContainer();
        Settings settings = (Settings) container.getComponentInstance(Settings.class);
        // indicates that the body should not be evaluated - this tag has no body
        return SKIP_BODY;
    }

    /**
     * <p>Get the action forward we should pass control when the user logs
     * back in after a timeout.</p>
     *
     * @return the current value of forward to pass control to after a
     * login.
     */
    public final String getForward() {
        return forward;
    }

    /**
     * <p>Set the action forward we should pass control when the user logs
     * back in after a timeout.</p>
     *
     * @param forward new value of forward to pass control to after a
     * login.
     */
    public final void setForward(final String forward) {
        this.forward = forward;
    }

    /**
     * <p>We decide here if we should display the rest of the page or not.
     * If  the session times out, we display the contents of the login
     * page
     * instead. This method is called after the JSP engine finished
     * processing
     * the tag.</p>
     *
     * @exception JspException encapsulates any exception when calling
     * <code>out.println</code>
     * @return <code>EVAL_PAGE</code> if we want to evaluate
     * the page after this tag, otherwise <code>SKIP_PAGE</code>.
     */
    public int doEndTag() throws JspException {
        // return value set in doStartTag
        return endTagReturn;
    }
}

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
 * $Log: UnacknowledgedCommentsAction.java,v $
 * Revision 1.4  2005/04/29 02:48:16  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:31:58  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:46  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:02  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.10  2004/12/31 18:27:44  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.9  2004/12/23 21:01:29  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.8  2004/11/12 18:19:15  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.7  2004/11/12 15:57:16  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.6  2004/11/03 15:31:51  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.5  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/10 19:57:24  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:42  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.4  2004/01/12 14:01:03  jano
 * fixing bugs
 *
 * Revision 1.3  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/07/21 14:16:06  jano
 * return to library index or list of openComments
 * Revision 1.1  2003/06/26 09:17:46  jano
 * first version can only hide unacknowledged comment
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.library.Library;
import com.ivata.groupware.business.library.comment.CommentDO;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 *<!--**********************************************************************-->
 *
 * <p><code>Action</code> invoked when you clicking on cross of unacknowledgedComment to remove  that comment  from UC list.</p>
 * <!--**********************************************************************-->
 * <br/>
 * <br/><strong>Copyright &copy; 2002 ivata limited.  All rights
 * reserved.</strong>
 * <br/>
 * <!--**********************************************************************-->
 * <em>
 * <br/> $Log: UnacknowledgedCommentsAction.java,v $
 * <br/> Revision 1.4  2005/04/29 02:48:16  colinmacleod
 * <br/> Data bugfixes.
 * <br/> Changed primary key back to Integer.
 * <br/>
 * <br/> Revision 1.3  2005/04/10 20:31:58  colinmacleod
 * <br/> Added new themes.
 * <br/> Changed id type to String.
 * <br/> Changed i tag to em and b tag to strong.
 * <br/> Improved PicoContainerFactory with NanoContainer scripts.
 * <br/>
 * <br/> Revision 1.1.1.1  2005/03/10 17:52:02  colinmacleod
 * <br/> Restructured ivata op around Hibernate/PicoContainer.
 * <br/> Renamed ivata groupware.
 * <br/>
 * <br/> Revision 1.10  2004/12/31 18:27:44  colinmacleod
 * <br/> Added MaskFactory to constructor of MaskAction.
 * <br/>
 * <br/> Revision 1.9  2004/12/23 21:01:29  colinmacleod
 * <br/> Updated Struts to v1.2.4.
 * <br/> Changed base classes to use ivata masks.
 * <br/>
 * <br/> Revision 1.8  2004/11/12 18:19:15  colinmacleod
 * <br/> Change action and form classes to extend MaskAction, MaskForm respectively.
 * <br/>
 * <br/> Revision 1.7  2004/11/12 15:57:16  colinmacleod
 * <br/> Removed dependencies on SSLEXT.
 * <br/> Moved Persistence classes to ivata masks.
 * <br/>
 * <br/> Revision 1.6  2004/11/03 15:31:51  colinmacleod
 * <br/> Change method interfaces to remove log.
 * <br/>
 * <br/> Revision 1.5  2004/07/13 19:47:29  colinmacleod
 * <br/> Moved project to POJOs from EJBs.
 * <br/> Applied PicoContainer to services layer (replacing session EJBs).
 * <br/> Applied Hibernate to persistence layer (replacing entity EJBs).
 * <br/>
 * <br/> Revision 1.4  2004/03/21 21:16:29  colinmacleod
 * <br/> Shortened name to ivata op.
 * <br/>
 * <br/> Revision 1.3  2004/02/10 19:57:24  colinmacleod
 * <br/> Changed email address.
 * <br/>
 * <br/> Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * <br/> Added full names to author tags
 * <br/>
 * <br/> Revision 1.1.1.1  2004/01/27 20:58:42  colinmacleod
 * <br/> Moved ivata openportal to SourceForge..
 * <br/>
 * <br/> Revision 1.4  2004/01/12 14:01:03  jano
 * <br/> fixing bugs
 * <br/>
 * <br/> Revision 1.3  2003/10/28 13:16:14  jano
 * <br/> commiting library,
 * <br/> still fixing compile and building openGroupware project
 * <br/>
 * <br/> Revision 1.2  2003/10/15 14:16:53  colin
 * <br/> fixing for XDoclet
 * <br/>
 * <br/> Revision 1.2  2003/07/21 14:16:06  jano
 * <br/> return to library index or list of openComments
 * <br/>
 * <br/> Revision 1.1  2003/06/26 09:17:46  jano
 * <br/> first version can only hide unacknowledged comment
 * <br/>
 * </em><br/><br/><hr>
 * <!--**********************************************************************-->
 *
 * @since 2003-6-25
 * @author Jan Boros <janboros@sourceforge.net>
 * @version $Revision: 1.4 $
 */
public class UnacknowledgedCommentsAction extends MaskAction {
    Library library;
    /**
     * TODO
     * @param library
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public UnacknowledgedCommentsAction(Library library,
            MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.library = library;
    }
    /**
     * <p>Called from the other <code>execute</code> method, this can
     * be overridden by each subclass to provide the <em>ivata</em>-specific
     * processing required.</p>
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session)
            throws SystemException {
        Integer commentId = StringHandling.integerValue(
                request.getParameter("hide"));
        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");
        CommentDO comment = library.findCommentByPrimaryKey(securitySession,
                commentId);
        comment.setUnacknowledged(true);
        library.amendComment(securitySession, comment);

        if (request.getParameter("list") != null) {
            return "listOfComments";
        } else {
            return "libraryIndex";
        }
    }
}

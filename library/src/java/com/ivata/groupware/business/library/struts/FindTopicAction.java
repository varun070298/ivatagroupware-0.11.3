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
 * $Log: FindTopicAction.java,v $
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
 * Revision 1.1.1.1  2005/03/10 17:52:05  colinmacleod
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
 * Revision 1.3  2004/02/10 19:57:23  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:41  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/03/03 16:57:12  colin
 * converted localization to automatic paths
 * added labels
 * added mandatory fieldName attribute
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.9  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.8  2003/01/18 20:21:18  colin
 * conversion to new MaskAction methods
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.struts;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.addressbook.person.group.right.RightConstants;
import com.ivata.groupware.business.library.Library;
import com.ivata.groupware.business.library.right.LibraryRights;
import com.ivata.groupware.business.library.topic.TopicDO;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p><code>Action</code> invoked when you calling topicModify.jsp
 * from topic.jsp.</p>
 * <!--**********************************************************************-->
 *
 * @since 2002-11-22
 * @author Jan Boros <janboros@sourceforge.net>
 * @version $Revision: 1.4 $
 */
public class FindTopicAction extends MaskAction {
    Library library;
    LibraryRights libraryRights;

    /**
     * TODO
     * @param library
     * @param libraryRights
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public FindTopicAction(Library library, LibraryRights libraryRights,
            MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.library = library;
        this.libraryRights = libraryRights;
    }

    /**
     * <p>Submit or cancel the form in
     * <code>/library/topicModify.jsp</code>.</p>
     *
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName current user name from session. .
     * @param settings valid, non-null settings from session.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     *
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        try {
            TopicForm libraryTopicForm = new TopicForm(library);

            // request parameter overrides
            String requestId = request.getParameter(
                        "id");
            Integer id;
            if (requestId != null) {
                id = StringHandling.integerValue(requestId);
            } else {
                id = (Integer) PropertyUtils.getSimpleProperty(form, "id");
            }

            TopicDO topic = library.findTopicByPrimaryKey(securitySession, id);

            libraryTopicForm.setTopic(topic);

            // set view rights to ITEM in the form
            java.util.Collection rights = libraryRights.findRightsForItemsInTopic(
                    securitySession,
                    id,
                    RightConstants.ACCESS_VIEW);
            Integer[] tmp = new Integer[rights.size()];
            int a = 0;

            for (java.util.Iterator i = rights.iterator(); i.hasNext();) {
                tmp[a] = (Integer) i.next();
                a++;
            }

            libraryTopicForm.setRightsViewItem(tmp);

            // set add rights to ITEM in the form
            rights = libraryRights.findRightsForItemsInTopic(securitySession,
                    id,
                    RightConstants.ACCESS_ADD);
            tmp = new Integer[rights.size()];
            a = 0;

            for (java.util.Iterator i = rights.iterator(); i.hasNext();) {
                tmp[a] = (Integer) i.next();
                a++;
            }

            libraryTopicForm.setRightsAddItem(tmp);

            // set amend rights to ITEM in the form
            rights = libraryRights.findRightsForItemsInTopic(securitySession,
                    id,
                    RightConstants.ACCESS_AMEND);
            tmp = new Integer[rights.size()];
            a = 0;

            for (java.util.Iterator i = rights.iterator(); i.hasNext();) {
                tmp[a] = (Integer) i.next();
                a++;
            }

            libraryTopicForm.setRightsAmendItem(tmp);

            // set delete rights to ITEM in the form
            rights = libraryRights.findRightsForItemsInTopic(securitySession,
                    id,
                    RightConstants.ACCESS_REMOVE);
            tmp = new Integer[rights.size()];
            a = 0;

            for (java.util.Iterator i = rights.iterator(); i.hasNext();) {
                tmp[a] = (Integer) i.next();
                a++;
            }

            libraryTopicForm.setRightsRemoveItem(tmp);

            // set view rights to TOPIC in the form
            rights = libraryRights.findRightsForTopic(securitySession,
                    id,
                    RightConstants.ACCESS_VIEW);
            tmp = new Integer[rights.size()];
            a = 0;

            for (java.util.Iterator i = rights.iterator(); i.hasNext();) {
                tmp[a] = (Integer) i.next();
                a++;
            }

            libraryTopicForm.setRightsView(tmp);

            // set amend rights to TOPIC in the form
            rights = libraryRights.findRightsForTopic(securitySession,
                    id,
                    RightConstants.ACCESS_AMEND);
            tmp = new Integer[rights.size()];
            a = 0;

            for (java.util.Iterator i = rights.iterator(); i.hasNext();) {
                tmp[a] = (Integer) i.next();
                a++;
            }

            libraryTopicForm.setRightsAmend(tmp);

            // set delete rights to TOPIC in the form
            rights = libraryRights.findRightsForTopic(securitySession,
                    id,
                    RightConstants.ACCESS_REMOVE);
            tmp = new Integer[rights.size()];
            a = 0;

            for (java.util.Iterator i = rights.iterator(); i.hasNext();) {
                tmp[a] = (Integer) i.next();
                a++;
            }

            libraryTopicForm.setRightsRemove(tmp);
            libraryTopicForm.setTopicTab_activeTab(new Integer(0));
            session.setAttribute("libraryTopicForm", libraryTopicForm);
            session.removeAttribute("topicTab_activeTab");

            // Forward control to the topicModify.jsp - FORM for add new or modify existing TOPIC
            return "libraryTopic";
        } catch (NoSuchMethodException e) {
            throw new SystemException(e);
        } catch (InvocationTargetException e) {
            throw new SystemException(e);
        } catch (IllegalAccessException e) {
            throw new SystemException(e);
        }
    }
}

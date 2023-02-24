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
 * $Log: GroupFrameAction.java,v $
 * Revision 1.3  2005/04/10 18:47:36  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:08  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:24  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/12/31 18:27:42  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.3  2004/12/23 21:01:25  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.2  2004/11/12 18:19:13  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.1  2004/11/12 15:40:25  colinmacleod
 * First version in CVS.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.struts;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;

/**
 * <p>
 * This action tells the JSP page <code>groupFrame.jsp</code> what to display,
 * and how to display it.
 * </p>
 *
 * @since ivata groupware 0.10 (2004-11-03)
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class GroupFrameAction extends MaskAction {
    /**
     * <p>
     * Constructor. Called by <strong>PicoContainer.</strong>.
     * </p>
     *
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public GroupFrameAction(MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
    }

    /**
     * <p>
     * This method does all the hard work in preparing data for
     * <code>frameIndex.jsp</code>.
     * </p>
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.

     * @see com.ivata.mask.web.struts.MaskAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionMessages, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession)
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session)
            throws SystemException {
        String mode = request.getParameter("mode");
        if (StringHandling.isNullOrEmpty(mode)) {
            throw new SystemException("No mode specified in GroupFrameAction");
        }

        // this frame can handle 3 different types of content:
        // groups, address books and user groups
        String menuPage, contentFrameName;
        // content will be set via JavaScript on the menu pane
        String contentPage = "/util/loading.jsp";

        if ("group".equals(mode)) {
            menuPage = "/addressBook/groupTree.action?mode=group";
            contentFrameName = "ivataGroup";
        } else if ("addressBook".equals(mode)) {
            menuPage = "/addressBook/groupList.action?mode=addressBook";
            contentFrameName = "ivataAddressBook";
        } else if ("userGroup".equals(mode)) {
            menuPage = "/addressBook/groupList.action?mode=userGroup";
            contentFrameName = "ivataUserGroup";
        } else {
            throw new SystemException("ERROR in GroupFrameAction: unidentified mode '"
                    + mode
                    + "'");
        }

        try {
            PropertyUtils.setProperty(form, "contentFrameName", contentFrameName);
            PropertyUtils.setProperty(form, "contentPage", contentPage);
            PropertyUtils.setProperty(form, "menuFrameName",
                    contentFrameName + "List");
            PropertyUtils.setProperty(form, "menuPage", menuPage);
        } catch (IllegalAccessException e) {
            throw new SystemException(e);
        } catch (InvocationTargetException e) {
            throw new SystemException(e);
        } catch (NoSuchMethodException e) {
            throw new SystemException(e);
        }
        return null;
    }
}

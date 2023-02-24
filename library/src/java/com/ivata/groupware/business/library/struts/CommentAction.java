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
 * $Log: CommentAction.java,v $
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
 * Revision 1.10  2004/12/31 18:27:43  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.9  2004/12/23 21:01:28  colinmacleod
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
 * Revision 1.3  2004/03/21 20:29:56  colinmacleod
 * Changed session variable called mailSession to securityServerSession.
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:40  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.6  2004/01/12 14:01:03  jano
 * fixing bugs
 *
 * Revision 1.5  2003/12/16 15:08:46  jano
 * fixing library functionality
 *
 * Revision 1.4  2003/12/12 13:23:36  jano
 * fixing library functionality
 *
 * Revision 1.3  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.9  2003/07/21 14:16:48  jano
 * we have new field - list
 * somethime we are commenting without itemForm in session
 * Revision 1.8  2003/06/30 07:12:29  peter
 * fixed flushing of commentTrees - all users want to see the news
 *
 * Revision 1.7  2003/06/30 05:23:07  peter
 * userName at the end of cache key, to match other cache namings
 *
 * Revision 1.6  2003/06/30 04:53:10  peter
 * comment tree is cached for each user separately now
 *
 * Revision 1.5  2003/06/09 12:06:13  peter
 * Implemented comment tree cache flushing on new/amended comment
 *
 * Revision 1.4  2003/03/04 17:26:32  colin
 * fixed bug in defaulting of subject (if parent is null)
 *
 * Revision 1.3  2003/02/28 13:44:12  colin
 * made comments work with Struts & popups
 *
 * Revision 1.2  2003/02/28 07:30:22  colin
 * implemented editing/displaying of faqs & notes
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
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
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.admin.setting.SettingsDataTypeException;
import com.ivata.groupware.business.library.Library;
import com.ivata.groupware.business.library.comment.CommentDO;
import com.ivata.groupware.business.library.item.LibraryItemDO;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>Invoked when the user edits, displays or enters a new library
 * comment.</p>
 *
 * @since 2003-02-18
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 *
 */
public class CommentAction extends MaskAction {
    private Library library;
    private Settings settings;
    /**
     *  TODO
     * @param library
     * @param settings
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public CommentAction(Library library, Settings settings,
            MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.library = library;
        this.settings = settings;
    }

    /**
     * <p>Called when the clear button is pressed, or after an ok or
     * delete button, where the session should be restored to its default
     * state.</p>
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
     */
    public void clear(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        // clear the comment form
        session.removeAttribute("libraryCommentForm");
    }

    /**
     * <p>Overridden to TODO:.</p>
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
     *
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        CommentForm commentForm = (CommentForm) form;

        ItemForm itemForm = (ItemForm) session.getAttribute("libraryItemForm");

        if (StringHandling.isNullOrEmpty(commentForm.getList())) {
            commentForm.setList("false");
        }

        // if there is no item form, just get out
        // but only if we are not in list of openComments
        if (itemForm == null && commentForm.getList().equals("false")) {
            return "utilClosePopUp";
        }

        LibraryItemDO item = null;

        if (commentForm.getList().equals("false")) {
            item = itemForm.getItem();
        }

        Integer requestParentId = StringHandling.integerValue(
                request.getParameter("parentId"));
        Integer requestId = StringHandling.integerValue(
                request.getParameter("id"));
        CommentDO comment = commentForm.getComment();
        CommentDO parent = null;
        if (comment != null) {
            parent = comment.getParent();
        }
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        comment.setCreatedBy(securitySession.getUser());

        // reply to an existing comment
        if (requestParentId != null) {
            commentForm.setComment(comment = new CommentDO());
            comment.setParent(parent = library.findCommentByPrimaryKey(
                        securitySession, requestParentId));


            // edit an existing comment
        } else if (requestId != null) {
            commentForm.setComment(comment = library.findCommentByPrimaryKey(
                    securitySession, requestId));
        }

        if (comment.getItem() == null) {
            if (item != null) {
                comment.setItem(item);
            } else {
                comment.setItem(parent.getItem());
            }
        }

        if (comment.getCreatedBy() == null) {
            comment.setCreatedBy(securitySession.getUser());
        }

        commentForm.setBundle("library");
        commentForm.setDeleteKey("submitComment.alert.delete");

        // set default values here
        if (StringHandling.isNullOrEmpty(comment.getSubject())) {
            try {
                comment.setSubject(settings.getStringSetting(securitySession,
                        "emailSubjectReplyPrefix",
                        securitySession.getUser()) +
                    (((parent == null) ||
                    StringHandling.isNullOrEmpty(parent.getSubject()))
                    ? ((item != null) ? item.getTitle() : comment.getItem().getTitle())
                    : parent.getSubject()));
            } catch (SettingsDataTypeException e) {
                throw new SystemException(e);
            }
        }
        return null;
    }

    /**
     * <p>This method is called if the ok or apply buttons are
     * pressed.</p>
     *
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     * @param ok <code>true</code> if the ok button was pressed, otherwise
     * <code>false</code> if the apply button was pressed.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     *
     */
    public String onConfirm(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session,
            final String defaultForward) throws SystemException {

        // new comment ?
        CommentDO comment = ((CommentForm) form).getComment();
        CommentDO parent = comment.getParent();
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        StringBuffer openerPage = new StringBuffer();

        if (((CommentForm) form).getList().equals("true")) {
            openerPage.append("/library/openComments.jsp");
        } else {
            openerPage.append("/library/display.action?id=");
            openerPage.append(comment.getItem().getId());
        }

        if (comment.getId() == null) {
            comment.setCreatedBy(securitySession.getUser());

            Integer parentId = (parent == null) ? null : parent.getId();

            comment = library.addComment(securitySession, comment);
        } else {
            // amend an existing comment
            library.amendComment(securitySession, comment);
        }

        // flush the comment tree for this item from jsp cache
        //flushCache("itemCommentTree_" + comment.getItemId().toString(),PageContext.APPLICATION_SCOPE, request);

        // if it was 'ok' we won't know the id till now
        if (!((CommentForm) form).getList().equals("true")) {
            openerPage.append("#comment");
            openerPage.append(comment.getId());
        }

        request.setAttribute("openerPage", openerPage.toString());

        return defaultForward;
    }

    /**
     * <p>This method is called if the delete (confirm, not warn) button
     * is pressed.</p>
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param log valid logging object to write messages to.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     *
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     *
     */
    public String onDelete(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session, final String defaultForward) throws SystemException {
        return null;
    }
}

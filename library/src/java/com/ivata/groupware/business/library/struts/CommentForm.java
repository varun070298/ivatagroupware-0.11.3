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
 * $Log: CommentForm.java,v $
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
 * Revision 1.6  2004/12/23 21:01:28  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.5  2004/11/12 18:19:15  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.4  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:40  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/11/13 16:11:08  jano
 * commitng everything to CVS
 * can deploy and application is ruuning, can login into
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.4  2003/07/21 14:16:48  jano
 * we have new field - list
 * somethime we are commenting without itemForm in session
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
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.library.Library;
import com.ivata.groupware.business.library.comment.CommentDO;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.validation.ValidationErrors;


/**
 * <p>Contains details of a comment which is being changed.</p>
 *
 * @since 2003-02-18
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class CommentForm extends LibraryForm {
    /**
     * <p>Contains the comment currently being edited.</p>
     */
    private CommentDO comment;
    /**
     * <p>
     * Library implementation.
     * </p>
     */
    private Library library;
    /**
     *<p>it <code>true</code> that refresh list of openComments, otherwise refres display item page.</p>
     */
    private String list;
    /**
     *
     * @param library
     * @param maskParam
     *            Refer to {@link #getMask}.
     * @param baseClassParam
     *            Refer to {@link #getBaseClass}.
     */
    public CommentForm(final Library library) {
        this.library = library;
        clear();
    }

    /**
     * TODO
     *
     * @see com.ivata.mask.web.struts.MaskForm#clear()
     */
    protected void clear() {
        comment = new CommentDO();
        list = null;
    }

    /**
     * <p>Contains the comment currently being edited.</p>
     *
     * @return the current value of comment.
     */
    public final CommentDO getComment() {
        return comment;
    }

    /**
     *<p>it <code>true</code> that refresh list of openComments, otherwise refres display item page.</p>
     *
     * @return the current value of list.
     */
    public final String getList() {
        return this.list;
    }

    /**/
    public void reset(final HttpServletRequest request,
            final HttpSession session) {
        comment = new CommentDO();
    }

    /**
     * <p>Contains the comment currently being edited.</p>
     *
     * @param comment the new value of comment.
     */
    public final void setComment(final CommentDO comment) {
        this.comment = comment;
    }

    /**
     *<p>it <code>true</code> that refresh list of openComments, otherwise refres display item page.</p>
     *
     * @param list the new value of list.
     */
    public final void setList(final String list) {
        this.list = list;
    }

    /**
     * Validate the properties that have been set for this HTTP request,
     * and return an <code>ActionMessages</code> object that encapsulates any
     * validation errors that have been found.  If no errors are found,
     * return <code>null</code> or an <code>ActionMessages</code> object with
     * no recorded error messages.
     * <p>
     * The default ipmlementation performs no validation and returns
     * <code>null</code>.  Subclasses must override this method to provide
     * any validation they wish to perform.
     *
     * @param request The servlet request we are processing.
     * @param session The HTTP session we are processing.
     * @see com.ivata.mask.web.struts.MaskForm#validate(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession)
     */
    public ValidationErrors validate(final HttpServletRequest request,
            final HttpSession session) {
        // if there is no ok, just get out
        if (StringHandling.isNullOrEmpty(getOk())) {
            return null;
        }

        // if it gets here - ok was pressed. validate on the server side
        ActionErrors errors;
        ValidationErrors validationErrors;

        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        validationErrors = library.validate(securitySession, comment);

        return validationErrors;
    }
}

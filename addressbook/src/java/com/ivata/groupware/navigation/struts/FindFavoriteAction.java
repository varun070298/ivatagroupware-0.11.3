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
 * $Log: FindFavoriteAction.java,v $
 * Revision 1.2  2005/04/09 17:19:10  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:36  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.navigation.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.navigation.menu.item.MenuItemDO;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.persistence.PersistenceManager;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.FindAction;
import com.ivata.mask.web.struts.InputMaskForm;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;

/**
 * Find a favorite, given it's id.
 *
 * @since ivata groupware 0.10 (2005-02-14)
 * @author Colin MacLeod
 * <a href="mailto:colin.macleod@ivata.com">colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public class FindFavoriteAction extends FindAction {
    public FindFavoriteAction(final PersistenceManager persistenceManagerParam,
            final MaskFactory maskFactoryParam,
            final MaskAuthenticator authenticatorParam) {
        super (persistenceManagerParam, maskFactoryParam, authenticatorParam);
    }

    /**
     * This does all the hard work of locating the favorite.
     * Refer to {@link MaskAction#execute}.
     *
     * @param mappingParam Refer to {@link MaskAction#execute}.
     * @param errorsParam Refer to {@link MaskAction#execute}.
     * @param formParam Refer to {@link MaskAction#execute}.
     * @param requestParam Refer to {@link MaskAction#execute}.
     * @param responseParam Refer to {@link MaskAction#execute}.
     * @param sessionParam Refer to {@link MaskAction#execute}.
     * @return Refer to {@link MaskAction#execute}.
     * @throws SystemException Refer to {@link MaskAction#execute}.
     */
    public String execute(final ActionMapping mappingParam,
            final ActionErrors errorsParam,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session)
            throws SystemException {
        setBaseClassName(MenuItemDO.class.getName());
        String forward =
            super.execute(mappingParam, errorsParam, form, request,
                response, session);

        // we don't want to warn on delete, and we don't want the new or apply
        // buttons to show, and we want to close on ok.
        InputMaskForm inputMaskForm = (InputMaskForm)
            request.getAttribute(InputMaskForm.REQUEST_ATTRIBUTE);
        assert (inputMaskForm != null);
        // we don't want a warning for deleting favorites!
        inputMaskForm.setDeleteWithoutWarn(true);
        // only delete/ok/cancel buttons needed
        inputMaskForm.setApplyButtonHidden(true);
        inputMaskForm.setClearButtonHidden(true);
        // close the pop-up on ok
        inputMaskForm.setDefaultForwardDelete("utilClosePopUp");
        inputMaskForm.setDefaultForwardOk("utilClosePopUp");
        // path to the field labels in the application resources
        inputMaskForm.setResourceFieldPath("navigationFavorite");
        // refresh the pop-up opener
        inputMaskForm.setRefreshOpener(true);
        return forward;
    }
}

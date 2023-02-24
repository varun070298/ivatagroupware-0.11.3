/*
 * Copyright (c) 2001 - 2005 ivata limited.
 * All rights reserved.
 * ---------------------------------------------------------
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
 * ---------------------------------------------------------
 * $Log: ResetContainerAction.java,v $
 * Revision 1.1  2005/04/11 10:54:43  colinmacleod
 * Added simple struts action to reset the
 * pico container factory.
 *
 * ---------------------------------------------------------
 */
package com.ivata.groupware.container.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;

/**
 * Call this action to reset the main container and reload the configuration.
 *
 * @since ivata groupware 0.11 (2005-03-31)
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.1 $
 */

public class ResetContainerAction extends MaskAction {
    public ResetContainerAction(MaskFactory maskFactoryParam,
            MaskAuthenticator authenticatorParam) {
        super(maskFactoryParam, authenticatorParam);
    }

    /**
     * This method simply calls {@link PicoContainerFactory#reset
     * PicoContainerFactory.reset()}.
     *
     * @param mappingParam
     * Refer to {@link MaskAction#execute}.
     * @param errorsParam
     * Refer to {@link MaskAction#execute}.
     * @param formParam
     * Refer to {@link MaskAction#execute}.
     * @param requestParam
     * Refer to {@link MaskAction#execute}.
     * @param responseParam
     * Refer to {@link MaskAction#execute}.
     * @param sessionParam
     * Refer to {@link MaskAction#execute}.
     * @return
     * Refer to {@link MaskAction#execute}.
     * @throws SystemException
     * Refer to {@link MaskAction#execute}.
     */
    public String execute(ActionMapping mappingParam, ActionErrors errorsParam,
            ActionForm formParam, HttpServletRequest requestParam,
            HttpServletResponse responseParam, HttpSession sessionParam)
            throws SystemException {
        // just reset the container!
        PicoContainerFactory.getInstance().initialize();
        return "success";
    }
}

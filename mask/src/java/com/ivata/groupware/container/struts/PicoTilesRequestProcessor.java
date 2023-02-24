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
 * $Log: PicoTilesRequestProcessor.java,v $
 * Revision 1.2  2005/04/22 09:35:23  colinmacleod
 * Added setup action interface so that
 * the hibernate action can reset/delete all
 * actions when the container is reloaded.
 *
 * Revision 1.1  2005/04/11 10:18:56  colinmacleod
 * Added tiles support to request processor.
 * Updated for new PicoContianerFactory singleton.
 *
 * ---------------------------------------------------------
 */
package com.ivata.groupware.container.struts;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.groupware.struts.SetupAction;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskTilesRequestProcessor;

/**
 * <p>
 * This Struts request processor extends the standard tiles class to instantiate
 * actions and action forms in a <strong>PicoContainer</strong> friendly way.
 * </p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   ivata groupware 0.11 (2005-03-11)
 * @version $Revision: 1.2 $
 */
public class PicoTilesRequestProcessor extends MaskTilesRequestProcessor {
    /**
     * Constructor.
     */
    public PicoTilesRequestProcessor() throws SystemException {
        super(PicoContainerFactory.getInstance().getMaskFactory(),
                PicoRequestProcessorImplementation.getPersistenceManager());
        setImplementation(PicoRequestProcessorImplementation
                .getRequestProcessorImplementation());
    }
    /**
     * <p>
     * Overridden to watch out for the setup action and pass the actions to it
     * so they can be cleared when the setup is finished.
     * </p>
     * <p>
     * (This is drastic but I could not see another way to reset the actions in
     * <strong>Struts</strong>.)
     * </p>
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param mapping The mapping we are using
     *
     * @exception IOException if an input/output error occurs
     */
    protected Action processActionCreate(final HttpServletRequest request,
            final HttpServletResponse response,
            final ActionMapping mapping) throws IOException {
        Action action = super.processActionCreate(request,
                response,
                mapping);
        if (action instanceof SetupAction) {
            SetupAction setupAction = (SetupAction) action;
            setupAction.setActions(actions);
        }
        return action;
    }
}

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
 * $Log: PicoRequestProcessor.java,v $
 * Revision 1.4  2005/04/22 09:35:23  colinmacleod
 * Added setup action interface so that
 * the hibernate action can reset/delete all
 * actions when the container is reloaded.
 *
 * Revision 1.3  2005/04/10 20:09:46  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:50  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:49:54  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/12/31 18:51:27  colinmacleod
 * Added ivata masks form initialization.
 *
 * Revision 1.1  2004/12/29 14:09:32  colinmacleod
 * Changed subproject name from masks to mask
 *
 * Revision 1.3  2004/12/23 21:01:29  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.2  2004/11/12 15:46:37  colinmacleod
 * Moved from security subproject to masks.
 *
 * Revision 1.1  2004/09/30 15:16:00  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.1  2004/07/13 19:41:15  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 * -----------------------------------------------------------------------------
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
import com.ivata.mask.web.struts.MaskRequestProcessor;

/**
 * <p>
 * This Struts request processor extends the standard class to instantiate
 * actions and action forms in a <strong>PicoContainer</strong> friendly way.
 * </p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Apr 14, 2004
 * @version $Revision: 1.4 $
 */
public class PicoRequestProcessor extends MaskRequestProcessor {
    public PicoRequestProcessor() throws SystemException {
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

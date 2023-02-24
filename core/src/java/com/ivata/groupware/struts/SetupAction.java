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
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
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
 * $Log: SetupAction.java,v $
 * Revision 1.1  2005/04/22 09:35:10  colinmacleod
 * Added setup action interface so that
 * the hibernate action can reset/delete all
 * actions when the container is reloaded.
 *
 * ---------------------------------------------------------
 */
package com.ivata.groupware.struts;

import java.util.Map;

/**
 * Implemented by all actions which are used to setup the site (and reset all
 * existing <strong>Struts</strong> actions.
 *
 * @since ivata groupware 0.11 (2005-04-13)
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.1 $
 */
public interface SetupAction {
    /**
     * This method is called by the request processor when the action is
     * created, to set the map of actions so they can be later cleared.
     */
    void setActions(Map actions);
}

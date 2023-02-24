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
 * $Log: Event.java,v $
 * Revision 1.3  2005/04/10 18:47:38  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:36  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1  2005/03/10 19:23:03  colinmacleod
 * Moved to ivata groupware.
 *
 * Revision 1.2  2004/03/21 21:16:23  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1  2004/01/29 13:48:41  janboros
 * Moved ivata openportal to SourceForge.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.event;

import java.io.Serializable;

/**
 * <p>To implement a specific event with detail specific to your system, you
 * need to extend this class.</p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 */
public abstract class Event implements Serializable {
    /**
     * <p>The name of the <em>JMS</em> topic associated with this event.</p>
     */
    private String topic;
    /**
     * <p>Get the name of the <em>JMS</em> topic associated with this event.</p>
     *
     * @return name of the <em>JMS</em> topic associated with this event.
     */
    public final String getTopic() {
        return topic;
    }

    /**
     * <p>Construct an event and set the name of the <em>JMS</em> topic associated
     * with this event.</p>
     *
     * @param theTopic name of the <em>JMS</em> topic associated with this event.
     */
    public Event(String theTopic) {
        topic = theTopic;
    }

}

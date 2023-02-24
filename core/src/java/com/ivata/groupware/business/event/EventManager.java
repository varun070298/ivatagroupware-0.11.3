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
 * $Log: EventManager.java,v $
 * Revision 1.3  2005/04/10 18:47:38  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:36  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1  2005/03/10 19:23:03  colinmacleod
 * Moved to ivata groupware.
 *
 * Revision 1.4  2004/07/13 19:42:43  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:23  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:07:29  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1  2004/01/29 13:48:41  janboros
 * Moved ivata openportal to SourceForge.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.event;


/**
 * <p>This class manages system events and notifications.</p>
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 */
public class EventManager {
    /**
     * <p>The one and only instance of this class.</p>
     */
    private static EventManager eventManager = null;

    /**
     * <p>Private constructor enforces singleton.</p>
     */
    private EventManager() {
    }

    /**
     * <p>Retrieve the only instance of this class.</p>
     */
    public synchronized static EventManager getInstance() {
        if (eventManager == null) {
            eventManager = new EventManager();
        }
        return eventManager;
    }

    /**
     * <p>Send a <em>JMS</em> message to notify other systems that an important
     * event has taken place.</p>
     */
    public void fireEvent(final Event event) {

/* TODO
        JBoss applicationServer = (JBoss) ApplicationServer.getInstance();
        QueueConnectionFactory queueConnectionFactory =
            applicationServer.getConnectionFactory();
        // Create the connection
        try {
            QueueConnection queueConnection =
                queueConnectionFactory.createQueueConnection();

            // Create the session for sending
            QueueSession senderSession = queueConnection.createQueueSession(
                // use transactions
                true,
                // Auto ack
                0);

            ObjectMessage message = senderSession.createObjectMessage(event);

            // Send the message
            // Look up the destination
            Queue queue =  null;
            try {
                queue = (Queue) ApplicationServer.getInitialContext().lookup(event.getTopic());
            } catch (NamingException e1) {
                throw new RuntimeException(e1);
            }
            // Create a sender
            QueueSender queueSender = senderSession.createSender(queue);
            queueSender.send(queue, message);
            senderSession.commit();
            senderSession.close();
            queueConnection.close();
        } catch(JMSException e) {
            throw new RuntimeException(e);
        }
*/
    }
}

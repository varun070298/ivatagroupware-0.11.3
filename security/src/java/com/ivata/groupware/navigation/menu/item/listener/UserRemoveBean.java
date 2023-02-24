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
 * $Log: UserRemoveBean.java,v $
 * Revision 1.2  2005/04/09 17:19:58  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:32  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/09/30 15:16:04  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.5  2004/07/13 19:41:17  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:19  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/01 22:00:34  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.2  2004/01/29 14:31:24  janboros
 * fixing package declaration
 * and imports
 *
 * Revision 1.1.1.1  2004/01/27 20:57:59  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.5  2003/11/07 14:54:15  jano
 * commitng after fixing some bugs
 *
 * Revision 1.4  2003/11/03 11:28:25  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.3  2003/10/17 12:36:13  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:49:57  colin
 * fixing fo Xdoclet
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.navigation.menu.item.listener;

 import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;

 /**
 * <p>This bean listens for events when a user is removed.</p>
 *
 * @since 2003-10-09
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 *
 * @ejb.bean
 *      name = "UserRemoveMenuItem"
 *      display-name = "UserRemoveMenuItem"
 *      acknowledge-mode = "Auto-acknowledge"
 *      destination-type = "javax.jms.Topic"
 *
 * @jboss.destination-jndi-name
 *      name = "topic/topicUserRemove"
 */
public class UserRemoveBean implements MessageDrivenBean, MessageListener {

  MessageDrivenContext messageDrivenContext;

  public void ejbCreate() {
  }

  public void ejbRemove() {
  }

  public void onMessage(final Message message) {
/** TODO
      // check this is an object message
      if (!ObjectMessage.class.isInstance(message)) {
          throw new EJBException("ERROR in folder.UserRemoveBean: unknown messaage class ("
            + message.getClass().getName()
            + ")");
      }
      ObjectMessage objectMessage = (ObjectMessage) message;
      UserEvent userEvent = null;
      try {
          userEvent = (UserEvent) objectMessage.getObject();
      } catch (JMSException e) {
          throw new EJBException(e);
      }
      String userName = userEvent.getUserName();
      try {
          // remove all menuItems which belong to this user
          java.util.Collection menuItems = menuItemHome.findByUserName(userName);
          for (Iterator i = menuItems.iterator(); i.hasNext(); ) {
              MenuItemLocal item = (MenuItemLocal) i.next();
              item.remove();
          }
      } catch (FinderException e) {
          throw new EJBException(e);
      } catch (NamingException e) {
          throw new EJBException(e);
      } catch (RemoveException e) {
          throw new EJBException(e);
      }
*/
  }

  public final void setMessageDrivenContext(final MessageDrivenContext messageDrivenContext) {
    this.messageDrivenContext = messageDrivenContext;
  }
}
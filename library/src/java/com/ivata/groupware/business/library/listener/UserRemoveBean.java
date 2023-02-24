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
 * Revision 1.3  2005/04/10 20:31:59  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:45  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:59  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
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
 * Revision 1.5  2003/11/13 16:11:08  jano
 * commitng everything to CVS
 * can deploy and application is ruuning, can login into
 *
 * Revision 1.4  2003/11/03 11:29:44  jano
 * commiting library,
 * tryinjg to fix deploying problem
 *
 * Revision 1.3  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.listener;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.ivata.groupware.admin.security.user.UserEvent;


/**
 * <p>This bean listens for events when a user is removed.</p>
 *
 * @since 2003-10-09
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @ejb.bean
 *      name = "UserRemoveLibrary"
 *      display-name = "UserRemoveLibrary"
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
        // check this is an object message
        if (!ObjectMessage.class.isInstance(message)) {
            throw new EJBException(
                "ERROR in folder.UserRemoveBean: unknown messaage class (" +
                message.getClass().getName() + ")");
        }

        ObjectMessage objectMessage = (ObjectMessage) message;
        UserEvent userEvent = null;

        try {
            userEvent = (UserEvent) objectMessage.getObject();
        } catch (JMSException e) {
            throw new EJBException(e);
        }

        String userName = userEvent.getUserName();

        /**
try {
    boolean deleteUser = true;
    // check if we can removeUser
    // find library items
    LibraryItemLocalHome libraryItemHome = (LibraryItemLocalHome)
        ApplicationServer.findLocalHome("LibraryItemLocal",
            LibraryItemLocalHome.class);
    java.util.Collection items = libraryItemHome.findByCreatedBy(this.getName());


    // if he has not LIN -> find comments
    if (items.isEmpty()) {
        CommentLocalHome commentHome = (CommentLocalHome)
            ApplicationServer.findLocalHome("CommentLocal",
                CommentLocalHome.class);
        java.util.Collection comments = commentHome.findByUserName(this.getName());

        // if he has not comments -> find files
        if (comments.isEmpty()) {
            FileLocalHome fileHome = (FileLocalHome)
                ApplicationServer.findLocalHome("FileLocal",
                    FileLocalHome.class);
            java.util.Collection files = fileHome.findByUserName(this.getName());

            // if he has not files -> find events
            // if he has events in futer he can have privet events
            if (files.isEmpty()) {
                EventLocalHome eventHome = (EventLocalHome)
                    ApplicationServer.findLocalHome("EventLocal",
                        EventLocalHome.class);
                java.util.Collection events = eventHome.findByUserName(this.getName());

                //if he has not events -> find groups
                if (events.isEmpty()) {

                    java.util.Collection groups = groupHome.findByCreatedBy(this.getName());

                    // if he has not groups -> find contacts
                    if (groups.isEmpty()) {
                        PersonLocalHome personHome = (PersonLocalHome)
                            ApplicationServer.findLocalHome("PersonLocal",
                                PersonLocalHome.class);
                        java.util.Collection persons = personHome.findByCreatedBy(this.getName());

                        if (!persons.isEmpty()) {
                            deleteUser = false;
                        }

                    } else {
                        deleteUser = false;
                    }

                } else {
                    deleteUser = false;
                }
            } else {
                deleteUser = false;
            }
        } else {
            deleteUser = false;
        }
    } else {
        deleteUser = false;
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

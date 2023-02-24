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
 * $Log: LibraryRightsImpl.java,v $
 * Revision 1.4  2005/04/29 02:48:16  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:09:45  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:46  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:59  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/11/12 18:16:05  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 15:57:16  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/07/18 21:57:35  colinmacleod
 * Removed unneccesary import.
 *
 * Revision 1.1  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:40  colinmacleod
 * Moved open portal to sourceforge.
 *
 * Revision 1.5  2003/11/03 11:29:44  jano
 * commiting library,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.3  2003/10/15 14:21:00  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.4  2003/06/02 22:18:43  colin
 * changes for new user rights interface
 *
 * Revision 1.3  2003/05/12 13:47:40  colin
 * added new methods for finding rights
 *
 * Revision 1.2  2003/04/09 09:02:44  jano
 * handling data of removing user
 *
 * Revision 1.1  2003/02/24 19:09:22  colin
 * moved to business
 *
 * Revision 1.10  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.9  2003/01/09 10:50:11  jano
 * I need only two method for finding right for TOPIC and ITEM
 *
 * Revision 1.8  2003/01/08 10:40:43  jano
 * we changed interface of libraryBeans, we are using libraryRightBean for amending rights and for finding out
 * and we are not storing rights in TopicDO
 *
 * Revision 1.7  2003/01/03 17:17:11  jano
 * fixing findr problem in canUser
 *
 * Revision 1.6  2003/01/02 16:39:40  jano
 * taking VIEW of
 *
 * Revision 1.5  2002/12/05 16:09:38  jano
 * fixing bug
 *
 * Revision 1.4  2002/11/29 13:12:36  jano
 * if finder didn't find anytnik he didn't throe exeption so we have to check if collection is empty
 *
 * Revision 1.3  2002/10/09 07:39:02  jano
 * to many imports :-)
 *
 * Revision 1.2  2002/08/30 09:50:19  colin
 * changed canUser... methods to just can...
 *
 * Revision 1.1  2002/07/12 09:31:39  colin
 * split library bean into library and library rights
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.right;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.mail.MethodNotSupportedException;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.groupware.business.addressbook.person.group.right.RightConstants;
import com.ivata.groupware.business.addressbook.person.group.right.RightDO;
import com.ivata.groupware.business.library.comment.CommentDO;
import com.ivata.groupware.business.library.topic.TopicDO;
import com.ivata.groupware.container.persistence.QueryPersistenceManager;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.util.SystemException;


/**
 * <p>
 * Facade to the intranet library access rights. This POJO can be
 * used both locally and remotely to establish what users are entitled to
 * within the library subsystem.
 * </p>
 *
 * @since 2002-07-10
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 */
public class LibraryRightsImpl implements LibraryRights, Serializable {
    /**
     * Persistence manger used to store/retrieve data objects.
     */
    private QueryPersistenceManager persistenceManager;

    /**
     * Construct a new library rights instance.
     *
     * @param persistenceManager used to store objects in db.
     */
    public LibraryRightsImpl(QueryPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    /**
     * <p>
     * This method changes ADD rights. Users in the specified groups will be
     * able to add ITEMS with this TOPIC.
     * </p>
     *
     * <p>
     * It's works only with those groups which can be seen by the user.
     * if I am adding ADD right for a group and the user has not VIEW right
     * -> also create a VIEW right for that group.
     * </p>
     *
     * @param id of TOPIC
     * @param userName user vhich is goin to change rights
     * @param rights collection of groups for which we will set up ADD right
     */
    public void amendAddRightsForItem(final SecuritySession securitySession,
            final Integer id,
            final Collection rights)
            throws SystemException {
        throw new SystemException(new MethodNotSupportedException("ERROR: LibraryRightsImpl.amendAddRightsForItem is not implemented yet!"));
    }

    /**
     * <p>This method changing AMEND rights. Users in those groups will be able to amend ITEMS with this TOPIC.
     * It's working only with those groups which can be see by user.
     * if I am adding AMEND right for group and there is not VIEW right -> so create VIEW right for that group.</p>
     *
     * @param id of TOPIC
     * @param userName user vhich is goin to change rights
     * @param rights collection of groups for which we will set up AMEND right
     */
    public void amendAmendRightsForItem(final SecuritySession securitySession,
            final Integer id,
            final Collection rights)
            throws SystemException {
        throw new SystemException(new MethodNotSupportedException("ERROR: LibraryRightsImpl.amendAmendRightsForItem is not implemented yet!"));
    }

    /**
     * <p>This method changing AMEND rights of TOPIC. It's working only with those groups which can be see by user.
     * if I am adding AMEND right for group and there is not VIEW right -> so create VIEW right for that group.</p>
     *
     * @param id of TOPIC
     * @param userName user which is chaning
     * @param rights collection of groups for which we will set up AMEND right
     */
    public void amendAmendRightsForTopic(final SecuritySession securitySession,
            final Integer id,
            final Collection rights)
            throws SystemException {
        throw new SystemException(new MethodNotSupportedException("ERROR: LibraryRightsImpl.amendAmendRightsForTopic is not implemented yet!"));
    }

    /**
     * <p>This method changing REMOVE rights. Users in those groups will be able to remove ITEMS with this TOPIC.
     * It's working only with those groups which can be see by user.
     * if I am adding REMOVE right for group and there is not VIEW right -> so create VIEW right for that group.</p>
     *
     * @param id of TOPIC
     * @param userName user vhich is goin to change rights
     * @param rights collection of groups for which we will set up REMOVE right
     */
    public void amendRemoveRightsForItem(final SecuritySession securitySession,
            final Integer id,
            final Collection rights)
            throws SystemException {
        throw new SystemException(new MethodNotSupportedException("ERROR: LibraryRightsImpl.amendRemoveRightsForItem is not implemented yet!"));
    }

    /**
     * <p>This method changing REMOVE rights of TOPIC. It's working only with those groups which can be see by user.
     * if I am adding REMOVE right for group and there is not VIEW right -> so create VIEW right for that group.</p>
     *
     * @param id of TOPIC
     * @param userName user vhich is going to change
     * @param rights collection of groups for which we will set up REMOVE right
     */
    public void amendRemoveRightsForTopic(final SecuritySession securitySession,
            final Integer id,
            final Collection rights)
            throws SystemException {
        throw new SystemException(new MethodNotSupportedException("ERROR: LibraryRightsImpl.amendRemoveRightsForTopic is not implemented yet!"));
    }

    /**
     * <p>This method changing VIEW rights of ITEMS. Users in those groups will be albe to see ITEMS with this TOPIC.
     *  It's working only with those groups which can be see by user.</p>
     *
     * @param id of TOPIC
     * @param userName user vhich is goin to change rights
     * @param rights collection of groups for which we will set up VIEW right
     */
    public void amendViewRightsForItem(final SecuritySession securitySession,
            final Integer id,
            final Collection rights)
            throws SystemException {
        throw new SystemException(new MethodNotSupportedException("ERROR: LibraryRightsImpl.amendViewRightsForItem is not implemented yet!"));
    }

    /**
     * <p>This method changing VIEW rights of TOPIC. It's working only with those groups which can be see by user.</p>
     *
     * @param id of TOPIC
     * @param userName user which is changing
     * @param rights collection of groupIds for which we will set up VIEW right
     */
    public void amendViewRightsForTopic(final SecuritySession securitySession,
            final Integer id,
            final Collection rights)
            throws SystemException {
        throw new SystemException(new MethodNotSupportedException("ERROR: LibraryRightsImpl.amendViewRightsForTopic is not implemented yet!"));
    }

    /**
     * <p>Find out if a user is allowed to add a new comment.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param comment the comment check.
     * @return <code>true</code> if the user is entitled to add this comment,
     *   otherwise <code>false</code>.
     */
    public boolean canAddComment(final SecuritySession securitySession,
            final CommentDO comment)
            throws SystemException {
        // user rights for adding comments are simple: if the user can view the
        // item this comment refers to, she can also add comments to it
        return canViewInTopic(securitySession, comment.getItem().getTopic().getId());
    }

    /**
     * <p>Find out if a user is allowed to add a new topic.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param topicId the unique identifier of the topic to check.
     * @return <code>true</code> if the user is entitled to add new topics,
     *     otherwise <code>false</code>.
     */
    public boolean canAddTopic(final SecuritySession securitySession)
            throws SystemException {
        return canUser(securitySession, (Integer)null,
                RightConstants.DETAIL_LIBRARY_TOPIC,
                RightConstants.ACCESS_ADD);
    }

    /**
     * <p>Find out if a user is allowed to add items to a given topic.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param topicId the unique identifier of the topic to check.
     * @return <code>true</code> if the user is entitled to add items to the
     *      topic, otherwise <code>false</code>.
     */
    public boolean canAddToTopic(final SecuritySession securitySession,
            final Integer topicId)
            throws SystemException {
        return canUser(securitySession, topicId, RightConstants.DETAIL_LIBRARY_ITEM_TOPIC,
                RightConstants.ACCESS_ADD);
    }

    /**
     * <p>Find out if a user is allowed to change an existing comment.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param commentParam the comment check.
     * @return <code>true</code> if the user is entitled to change this comment,
     *   otherwise <code>false</code>.
     */
    public boolean canAmendComment(final SecuritySession securitySession,
            final CommentDO commentParam)
            throws SystemException {
        // prerequisite: nobody can amend a comment with a null id
        if (commentParam.getId() == null) {
            return false;
        }
        // in order to amend a comment, the user must have sufficient rights
        // at topic level, or be the original author of the topic
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        CommentDO comment;
        try {
            // re-get the comment from the data store
            comment = (CommentDO)
                persistenceManager.findByPrimaryKey(persistenceSession,
                    CommentDO.class, commentParam.getId());
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
        // so is this the original author?
        if (comment.getCreatedBy().equals(securitySession.getUser())) {
            return true;
        }
        // if that didn't work, check the rights at topic level
        return canUser(securitySession, comment.getItem().getTopic().getId(),
                RightConstants.DETAIL_LIBRARY_COMMENT_TOPIC,
                RightConstants.ACCESS_AMEND);
    }

    /**
     * <p>Find out if a user is allowed to amend items in a given topic.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param topicId the unique identifier of the topic to check.
     * @return <code>true</code> if the user is entitled to amend items in the
     *      topic, otherwise <code>false</code>.
     */
    public boolean canAmendInTopic(final SecuritySession securitySession,
            final Integer topicId)
            throws SystemException {
        return canUser(securitySession, topicId, RightConstants.DETAIL_LIBRARY_ITEM_TOPIC,
                RightConstants.ACCESS_AMEND);
    }

    /**
     * <p>Find out if a user is allowed to amend an existing new topic.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param topicId the unique identifier of the topic to check.
     * @return <code>true</code> if the user is entitled to amend the
     *      topic, otherwise <code>false</code>.
     */
    public boolean canAmendTopic(final SecuritySession securitySession,
            final Integer id)
            throws SystemException {
        return canUser(securitySession, id, RightConstants.DETAIL_LIBRARY_TOPIC,
                RightConstants.ACCESS_AMEND);
    }

    /**
     * <p>Find out if a user is allowed to remove an existing comment.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param commentParam the comment check.
     * @return <code>true</code> if the user is entitled to remove this comment,
     *   otherwise <code>false</code>.
     */
    public boolean canRemoveComment(final SecuritySession securitySession,
            final CommentDO commentParam)
            throws SystemException {
        // prerequisite: nobody can remove a comment with a null id
        if (commentParam.getId() == null) {
            return false;
        }
        // in order to remove a comment, the user must have sufficient rights
        // at topic level, or be the original author of the topic
        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        CommentDO comment;
        try {
            // re-get the comment from the data store
            comment = (CommentDO)
                persistenceManager.findByPrimaryKey(persistenceSession,
                    CommentDO.class, commentParam.getId());
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
        // so is this the original author?
        if (comment.getCreatedBy().equals(securitySession.getUser())) {
            return true;
        }
        // if that didn't work, check the rights at topic level
        return canUser(securitySession, comment.getItem().getTopic().getId(),
                RightConstants.DETAIL_LIBRARY_COMMENT_TOPIC,
                RightConstants.ACCESS_REMOVE);
    }

    /**
     * <p>Find out if a user is allowed to remove items from a given topic.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param topicId the unique identifier of the topic to check.
     * @return <code>true</code> if the user is entitled to remove items from
     *      the topic, otherwise <code>false</code>.
     */
    public boolean canRemoveFromTopic(final SecuritySession securitySession,
            final Integer topicId)
            throws SystemException {
        return canUser(securitySession, topicId, RightConstants.DETAIL_LIBRARY_ITEM_TOPIC,
                RightConstants.ACCESS_REMOVE);
    }


    /**
     * <p>Find out if a user is allowed to remove a topic.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param topicId the unique identifier of the topic to check.
     * @return <code>true</code> if the user is entitled to remove the
     *      topic, otherwise <code>false</code>.
     */
    public boolean canRemoveTopic(final SecuritySession securitySession,
            final Integer id)
            throws SystemException {
        return canUser(securitySession, id, RightConstants.DETAIL_LIBRARY_TOPIC,
                RightConstants.ACCESS_REMOVE);
    }
    /**
     * <p>Internal helper method. Find out if a user is allowed to access
     * entries in a given group.</p>
     *
     * @param securitySession Security session to check the rights for.
     * @param integerParam Unique identifier of the topic to check.
     * @param access The access level as defined in {@link
     *      com.ivata.groupware.business.addressbook.person.group.right.RightConstants
     *      RightConstants}.
     * @return <code>true</code> if the user is entitled to access entries in the
     *      topic, otherwise <code>false</code>.
     */
    public boolean canUser(final SecuritySession securitySession,
            Integer integerParam,
            final Integer detail,
            final Integer access)
            throws SystemException {
/* TODO        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);

        // see if we're allowed to
        try {
            Collection tmp = persistenceManager.find(persistenceSession,
                "rightByUserNameAccessDetailTargetId",
                new Object [] {
                    securitySession.getUser().getName(),
                    access,
                    detail,
                    topicId
                });
            if (tmp.size() == 0) {
                return false;
            }
        } catch (FinderException e) {
            // oops
            return false;
        }
        // only return true if we get this far :- )
*/

        return true;
    }

    /**
     * <p>Find out if a user is allowed to view items to a given topic.</p>
     *
     * @param userName the name of the user to check the user rights for.
     * @param integerParam the unique identifier of the topic to check.
     * @return <code>true</code> if the user is entitled to view items in the
     *      topic, otherwise <code>false</code>.
     */
    public boolean canViewInTopic(final SecuritySession securitySession,
            Integer topicId)
            throws SystemException {
        return canUser(securitySession, topicId,
                RightConstants.DETAIL_LIBRARY_ITEM_TOPIC,
                RightConstants.ACCESS_VIEW);
    }
    /**
     * <p>Find groups which have <code>access</code> to items with topic.
     * Return only those groups which can be see by that user.</p>
     *
     * @param id of TOPIC
     * @param userName user which is trying to find rights
     * @param access find rights with this access
     * @return Collection of IDS of groups which have <code>access</code> to that items wuth that topic
     */
    public Collection findRightsForItemsInTopic(final SecuritySession securitySession,
            final Integer id,
            final Integer access)
            throws SystemException {
        Vector rights = new Vector();
        String userName = securitySession.getUser().getName();

        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            // I will send only groupIds which user can view, no more.
            List groupIdsCanViewByUser =
                persistenceManager.find(persistenceSession,
                        "rightTargetIdByUserNameAccessDetail",
                        new Object [] {
                            userName, RightConstants.ACCESS_VIEW,
                            RightConstants.DETAIL_PERSON_GROUP_MEMBER
                        });
            // find rights for TOPIC
            List tmp =
                persistenceManager.find(persistenceSession,
                        "rightByAccessDetailTargetId",
                        new Object [] {
                            access, RightConstants.DETAIL_LIBRARY_ITEM_TOPIC, id
                        });

            for (int i = 0; i < tmp.size(); i++) {
                RightDO right = (RightDO) tmp.get(i);
                Integer groupId = right.getGroup().getId();

                if (groupIdsCanViewByUser.contains(groupId)) {
                    rights.add(groupId);
                }
            }
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
        return rights;
    }

    /**
     * <p>Find groups which have <code>access</code> to topic.
     * Return only those groups which can be see by that user.</p>
     *
     * @param id of TOPIC
     * @param userName user which is trying to find rights
     * @param access find rights with this access
     * @return Collection of IDS of groups which have <code>access</code> to that topic
     */
    public Collection findRightsForTopic(final SecuritySession securitySession,
            final Integer id,
            final Integer access)
            throws SystemException {
        Vector rights = new Vector();
        String userName = securitySession.getUser().getName();

        PersistenceSession persistenceSession = persistenceManager.openSession(securitySession);
        try {
            // I will send only groupIds which user can view, no more.
            List groupIdsCanViewByUser =
                persistenceManager.find(persistenceSession,
                        "rightTargetIdByUserNameAccessDetail",
                        new Object [] {
                            userName, RightConstants.ACCESS_VIEW,
                            RightConstants.DETAIL_LIBRARY_TOPIC
                        });
            // find rights for TOPIC
            List tmp =
                persistenceManager.find(persistenceSession,
                        "rightByAccessDetailTargetId",
                        new Object [] {
                            access, RightConstants.DETAIL_LIBRARY_ITEM_TOPIC, id
                        });

            for (int i = 0; i < tmp.size(); i++) {
                RightDO right = (RightDO) tmp.get(i);
                Integer groupId = right.getGroup().getId();

                if (groupIdsCanViewByUser.contains(groupId)) {
                    rights.add(groupId);
                }
            }
        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }
        return rights;
    }

    /**
     * <p>Find the unique identifiers of all library topics for which the items
     * can be accessed by the group specified, with the access level given.</p>
     *
     * @param groupId unique identifier of the group for which to search for
     *    library topics.
     * @param access the access level as defined in {@link
     *      com.ivata.groupware.business.addressbook.person.group.right.RightConstants
     *      RightConstants}.
     * @return a <code>Collection</code> of <code>Integer</code> instances,
     *      matching all topics which can be access with this level of access
     *      by the group specified.
     */
    public Collection findTopicsByGroupAccess(final SecuritySession
            securitySession,
            final Integer groupId,
            final Integer access)
            throws SystemException {
        Vector topics = new Vector();

        PersistenceSession persistenceSession =
                persistenceManager.openSession(securitySession);
        try {
            // first find the group
            GroupDO group = (GroupDO)
                persistenceManager.findByPrimaryKey(persistenceSession,
                        GroupDO.class, groupId);

            // find all the ids
            List topicIds =
                persistenceManager.find(persistenceSession,
                        "rightTargetIdByGroupIdAccessDetail",
                        new Object [] {
                            group, access, RightConstants.DETAIL_LIBRARY_ITEM_TOPIC
                        });
            for (Iterator i = topicIds.iterator(); i.hasNext(); ) {
                String topicId = (String) i.next();
                TopicDO topic = (TopicDO)
                    persistenceManager.findByPrimaryKey(persistenceSession,
                            TopicDO.class, topicId);
                topics.add(topic);
            }

        } catch (Exception e) {
            persistenceSession.cancel();
            throw new SystemException(e);
        } finally {
            persistenceSession.close();
        }

        return topics;
    }
}

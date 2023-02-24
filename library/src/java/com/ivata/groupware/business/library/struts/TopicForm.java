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
 * $Log: TopicForm.java,v $
 * Revision 1.3  2005/04/10 20:31:58  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:46  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:01  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.7  2004/12/23 21:01:29  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.6  2004/11/12 18:19:15  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.5  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/10 19:57:24  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:42  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.3  2003/08/21 13:29:43  jano
 * we have new structure of userGroups
 * and
 * another frontend for maintan rights for item in topics
 * Revision 1.2  2003/03/03 16:57:12  colin
 * converted localization to automatic paths
 * added labels
 * added mandatory fieldName attribute
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.9  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.8  2003/01/08 10:39:39  jano
 * we changed interface of libraryBeans, we are using libraryRightBean for amending rights and for finding out
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.library.Library;
import com.ivata.groupware.business.library.topic.TopicDO;
import com.ivata.mask.Mask;
import com.ivata.mask.validation.ValidationErrors;
import com.ivata.mask.web.struts.DialogForm;


/**
 * <p>Contains details of a topic which is being changed, or where the
 * user (group) rights are being altered..</p>
 *
 * @since 2002-11-22
 * @author Jan Boros <janboros@sourceforge.net>
 * @version $Revision: 1.3 $
 */
public class TopicForm extends DialogForm {
    /**
     * <p>
     * Defines the base class of all objects in the value object list.
     * </p>
     */
    private Class baseClass;

    /**
     * Library - used in validation.
     */
    private Library library;

    /**
     * <p>
     * Mask containing all the field definitions for this list.
     * </p>
     */
    private Mask mask;
    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>add</strong> library items to this topic.</p>
     */
    private Integer[] rightsAddItem;

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>amend</strong> this topic.</p>
     */
    private Integer[] rightsAmend;

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>amend</strong> library items which have this topic.</p>
     */
    private Integer[] rightsAmendItem;

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>remove</strong>  this topic.</p>
     */
    private Integer[] rightsRemove;

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>remove</strong> library items with this topic.</p>
     */
    private Integer[] rightsRemoveItem;
    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>view</strong> this topic.</p>
     */
    private Integer[] rightsView;

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>view</strong> library items with this topic.</p>
     */
    private Integer[] rightsViewItem;

    /**
     * <p>Contains details of the topic which is currently being modified.</p>
     */
    private TopicDO topic;

    /**
     * <p>which TAB is active.</p>
     */
    private Integer topicTab_activeTab;

    public TopicForm(final Library libraryParam) {
        this.library = libraryParam;
    }
    /**
     * TODO
     *
     * @see com.ivata.mask.web.struts.MaskForm#clear()
     */
    protected void clear() {
        rightsAddItem = new Integer[] {  };
        rightsAmend = new Integer[] {  };
        rightsAmendItem = new Integer[] {  };
        rightsRemove = new Integer[] {  };
        rightsRemoveItem = new Integer[] {  };
        rightsView = new Integer[] {  };
        rightsViewItem = new Integer[] {  };
        topic = new TopicDO();
        topicTab_activeTab = null;
    }

    /**
     * <p>
     * Defines the base class of all objects in the value object list.
     * </p>
     *
     * @return base class of all objects in the value object list.
     */
    public final Class getBaseClass() {
        return baseClass;
    }

    /**
     * <p>
     * Mask containing all the field definitions for this list.
     * </p>
     *
     * @return mask containing all the field definitions for this list.
     */
    public final Mask getMask() {
        return mask;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>add</strong> library items to this topic.</p>
     *
     * @return the current value of rightsAddItem.
     */
    public final Integer[] getRightsAddItem() {
        return this.rightsAddItem;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>amend</strong> library items which have this topic.</p>
     *
     * @return the current value of rightsAmend.
     */
    public final Integer[] getRightsAmend() {
        return this.rightsAmend;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>amend</strong> library items which have this topic.</p>
     *
     * @return the current value of rightsAmendItem.
     */
    public final Integer[] getRightsAmendItem() {
        return this.rightsAmendItem;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>remove</strong> library items with this topic.</p>
     *
     * @return the current value of groups who can remove from this topic.
     */
    public final Integer[] getRightsRemove() {
        return this.rightsRemove;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>remove</strong> library items with this topic.</p>
     *
     * @return the current value of rightsRemoveItem.
     */
    public final Integer[] getRightsRemoveItem() {
        return this.rightsRemoveItem;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>view</strong> library items with this topic.</p>
     *
     * @return the current value of groups who can view this topic.
     */
    public final Integer[] getRightsView() {
        return rightsView;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>view</strong> library items with this topic.</p>
     *
     * @return the current value of rightsViewItem.
     */
    public final Integer[] getRightsViewItem() {
        return this.rightsViewItem;
    }

    /**
     * <p>Contains details of the topic which is currently being modified.</p>
     *
     * @return the current value of topic.
     */
    public final TopicDO getTopic() {
        return topic;
    }

    /**
     * <p>which TAB is active.</p>
     *
     * @return the current value of topicTab_activeTab.
     */
    public final Integer getTopicTab_activeTab() {
        return this.topicTab_activeTab;
    }

    /**
     * <p>Reset all bean properties to their default state.  This method
     * is called before the properties are repopulated by the controller
     * servlet.<p>
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(final ActionMapping mapping,
            final HttpServletRequest request) {
        // I have to clear arrays because STRUTS is not doing that
        // meybe we neew to CHAGE tree select tag
        Integer[] tmpRights = {  };
        int tab = ((this.topicTab_activeTab == null) ? 0
                                                     : this.topicTab_activeTab.intValue());

        if ((tab == 1) && (request.getParameterValues("rightsView") == null)) {
            this.setRightsView(tmpRights);
        } else if ((tab == 2) &&
                (request.getParameterValues("rightsAmend") == null)) {
            this.setRightsAmend(tmpRights);
        } else if ((tab == 3) &&
                (request.getParameterValues("rightsRemove") == null)) {
            this.setRightsRemove(tmpRights);
        }

        // if you didn't click delete butoon so don't show delete message if user want to delete topic
        if (request.getParameterValues("deleteWarn") == null) {
            this.setDeleteWarn(null);
        }
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>add</strong> library items to this topic.</p>
     *
     * @param rightsAddItem the new value of rightsAddItem.
     */
    public final void setRightsAddItem(final Integer[] rightsAddItem) {
        this.rightsAddItem = rightsAddItem;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>amend</strong> library items which have this topic.</p>
     *
     * @param rightsAmend the new value of rightsAmend.
     */
    public final void setRightsAmend(final Integer[] rightsAmend) {
        this.rightsAmend = rightsAmend;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>amend</strong> library items which have this topic.</p>
     *
     * @param rightsAmendItem the new value of rightsAmendItem.
     */
    public final void setRightsAmendItem(final Integer[] rightsAmendItem) {
        this.rightsAmendItem = rightsAmendItem;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>remove</strong> library items with this topic.</p>
     *
     * @param rightsRemove the new value of groups who can remove from
     * this topic.
     */
    public final void setRightsRemove(final Integer[] rightsRemove) {
        this.rightsRemove = rightsRemove;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>remove</strong> library items with this topic.</p>
     *
     * @param rightsRemoveItem the new value of rightsRemoveItem.
     */
    public final void setRightsRemoveItem(final Integer[] rightsRemoveItem) {
        this.rightsRemoveItem = rightsRemoveItem;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>view</strong> library items with this topic.</p>
     *
     * @param rightsView the new value of groups who can view this topic.
     */
    public final void setRightsView(final Integer[] rightsView) {
        this.rightsView = rightsView;
    }

    /**
     * <p>Contains an array of all of the group ids for groups who have
     * the right to <strong>view</strong> library items with this topic.</p>
     *
     * @param rightsViewItem the new value of rightsViewItem.
     */
    public final void setRightsViewItem(final Integer[] rightsViewItem) {
        this.rightsViewItem = rightsViewItem;
    }

    /**
     * <p>Contains details of the topic which is currently being modified.</p>
     *
     * @param topic the new value of topic.
     */
    public final void setTopic(final TopicDO topic) {
        this.topic = topic;
    }

    /**
     * <p>which TAB is active.</p>
     *
     * @param topicTab_activeTab the new value of topicTab_activeTab.
     */
    public final void setTopicTab_activeTab(final Integer topicTab_activeTab) {
        this.topicTab_activeTab = topicTab_activeTab;
    }

    /**
     * <p>Call the corresponding server-side validation, handle possible
     * exceptions and return any errors generated.</p>
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     * @return <code>ActionMessages</code> collection containing all
     * validation errors, or <code>null</code> if there were no errors.
     * @see com.ivata.mask.web.struts.MaskForm#validate(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession)
     */
    public ValidationErrors validate(final HttpServletRequest request,
            final HttpSession session) {
        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");
        return library.validate(securitySession, topic);
    }
}

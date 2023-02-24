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
 * $Log: GroupRightAction.java,v $
 * Revision 1.5  2005/04/29 02:48:14  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.4  2005/04/28 18:47:09  colinmacleod
 * Fixed XHMTL, styles and resin compatibility.
 * Added support for URL rewriting.
 *
 * Revision 1.3  2005/04/10 20:32:02  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:08  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:25  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.9  2004/12/31 18:27:42  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.8  2004/12/23 21:01:25  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.7  2004/11/12 18:19:13  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.6  2004/11/12 15:57:06  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.5  2004/11/03 15:31:50  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.4  2004/07/13 19:41:14  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:08  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:33  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:53  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.6  2003/08/19 14:53:31  jano
 * *** empty log message ***
 *
 * Revision 1.5  2003/08/14 08:20:11  jano
 * fixing bug
 *
 * Revision 1.4  2003/08/13 13:32:43  jano
 * addressBook exttension: next level
 *
 * Revision 1.3  2003/08/05 14:57:35  jano
 * addressBook extension
 *
 * Revision 1.2  2003/06/10 10:42:15  peter
 * changed keys when flushing tree jsp caches
 *
 * Revision 1.1  2003/06/02 22:17:48  colin
 * changes for new user rights interface
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.struts;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.business.addressbook.AddressBook;
import com.ivata.groupware.business.addressbook.person.group.GroupConstants;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.groupware.business.addressbook.person.group.right.RightConstants;
import com.ivata.groupware.business.addressbook.right.AddressBookRights;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>This action is  invoked whenever
 * <code>/addressBook/groupRight.jsp</code> is submitted.</p>
 *
 * @since 2003-05-09
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.5 $
 */
public class GroupRightAction extends MaskAction {
    AddressBook addressBook;
    AddressBookRights addressBookRights;

    /**
     * <p>
     * Constructor. Called by <strong>PicoContainer.</strong>.
     * </p>
     *
     * @param addressBook valid address book implementation.
     * @param addressBookRights valid address book rights implementation
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public GroupRightAction(AddressBook addressBook, AddressBookRights
            addressBookRights, MaskFactory maskFactory,
            MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.addressBook = addressBook;
        this.addressBookRights = addressBookRights;
    }
    /**
     * <p>Called when the clear button is pressed, or after an ok or
     * delete button, where the session should be restored to its default
     * state.</p>
     *
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     */
    public void clear(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session)
        throws SystemException {
        GroupRightForm groupRightForm = (GroupRightForm) form;
        groupRightForm.setGroup(new GroupDO());

        groupRightForm.setGroupRightTab_activeTab(new Integer(0));
        groupRightForm.setSelected(new String[0]);

        groupRightForm.setIncludePage("/addressBook/groupRightGeneral.jsp");

        groupRightForm.setGroupRightsAdd(new String[0]);
        groupRightForm.setGroupRightsAmend(new String[0]);
        groupRightForm.setGroupRightsRemove(new String[0]);
        groupRightForm.setGroupRightsView(new String[0]);

        groupRightForm.setGroupIds(new Vector());
        groupRightForm.setRowNames(new Vector());
        groupRightForm.setRows(new Vector());

        session.removeAttribute("groupRightTab_activeTab");
    }

    /**
     * <p>Helper method to convert a <code>Collection</code> of
     * <code>GroupDO</code> instances to a string array.</p>
     *
     * @param collection the <code>Collection</code> to convert.
     * @return a string array of the integers in the source collection.
     */
    private String[] convertGroupCollectionToStringArray(final Collection collection) {
        String[] stringArray = new String[collection.size()];
        int index = 0;

        for (Iterator i = collection.iterator(); i.hasNext(); ++index) {
            stringArray[index] = ((Integer) i.next()).toString();
        }

        return stringArray;
    }

    /**
     * <p>Overridden from the default intranet implementation to
     * process tab changes.</p>
     *
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there
     * are any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName current user name from session. .
     * @param settings valid, non-null settings from session.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session)
        throws SystemException {
        GroupRightForm groupRightForm = (GroupRightForm) form;
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");

        if ((request.getParameter("reset") != null) &&
                request.getParameter("reset").equals("true")) {
            groupRightForm.setGroup(new GroupDO());
            groupRightForm.setAddGroup(null);
            groupRightForm.setType(request.getParameter("type"));
            groupRightForm.setIncludePage("/addressBook/groupRightGeneral.jsp");
            groupRightForm.setHelpKey("addressbook.groupRightUser");
            groupRightForm.setReadOnly(false);
            groupRightForm.setCanRemove(false);
            session.removeAttribute("groupRightTab_activeTab");
        }

        if (groupRightForm == null) {
            PicoContainer container = securitySession.getContainer();
            groupRightForm = (GroupRightForm) container.getComponentInstance(GroupRightForm.class);
            session.setAttribute("addressBookGroupRightForm", groupRightForm);
        }

        // if there is a request parameter for the group id, set it from that
        Integer requestGroupId = StringHandling.integerValue(request.getParameter(
                    "requestGroupId"));

        if (requestGroupId != null) {
            GroupDO requestGroup = addressBook.findGroupByPrimaryKey(
                    securitySession,
                    requestGroupId);
            groupRightForm.setGroup(requestGroup);

            // now get and set all of the address book rights
            Set allGroups = new TreeSet();

            Collection addressBookView = addressBookRights.findRightsForGroup(securitySession,
                    requestGroup, RightConstants.ACCESS_VIEW);
            allGroups.addAll(addressBookView);
            groupRightForm.setGroupRightsView(convertGroupCollectionToStringArray(
                    addressBookView));

            Collection addressBookAdd = addressBookRights.findRightsForGroup(securitySession,
                    requestGroup, RightConstants.ACCESS_ADD);
            allGroups.addAll(addressBookAdd);
            groupRightForm.setGroupRightsAdd(convertGroupCollectionToStringArray(
                    addressBookAdd));

            Collection addressBookAmend = addressBookRights.findRightsForGroup(securitySession,
                    requestGroup, RightConstants.ACCESS_AMEND);
            allGroups.addAll(addressBookAmend);
            groupRightForm.setGroupRightsAmend(convertGroupCollectionToStringArray(
                    addressBookAmend));

            Collection addressBookRemove = addressBookRights.findRightsForGroup(securitySession,
                    requestGroup, RightConstants.ACCESS_REMOVE);
            allGroups.addAll(addressBookRemove);
            groupRightForm.setGroupRightsRemove(convertGroupCollectionToStringArray(
                    addressBookRemove));

            Vector groupIds = new Vector();
            Vector groupNames = new Vector();

            for (Iterator i = allGroups.iterator(); i.hasNext();) {
                Integer userGroupId = (Integer) i.next();
                groupIds.add(userGroupId);
                groupNames.add(addressBook.findGroupByPrimaryKey(
                        securitySession, userGroupId).getName());
            }

            groupRightForm.setGroupIds(groupIds);
            groupRightForm.setGroupNames(groupNames);

            // set Up READ ONLY flag
            groupRightForm.setReadOnly(!addressBookRights.canAmendInGroup(
                    securitySession, requestGroup));

            // set Up CAN REMOVE flag
            groupRightForm.setCanRemove(addressBookRights.canRemoveFromGroup(
                    securitySession, requestGroup));

            session.removeAttribute("groupRightTab_activeTab");
            groupRightForm.setGroupRightTab_activeTab(null);
        }

        // default put everyone group to list
        if (!groupRightForm.getGroupIds().contains(GroupConstants.USER_GROUP)) {
            groupRightForm.getGroupIds().add(GroupConstants.USER_GROUP);
            groupRightForm.getGroupNames().add("everyone");
        }

        // in case when you click on TAB, you will be here (other actions
        // are taken care of by the individual methods
        int activeTab = (groupRightForm.getGroupRightTab_activeTab() == null)
            ? 0 : groupRightForm.getGroupRightTab_activeTab().intValue();

        if (groupRightForm.getType() == null) {
            groupRightForm.setType(request.getParameter("type"));
        }

        // this flag is used later in this method to see whether the selected
        // items refers to groups or topics
        if (activeTab == 1) {
            groupRightForm.setIncludePage("/addressBook/groupRightDetail.jsp");
            groupRightForm.setHelpKey("addressbook.groupRightAddressBook");
            groupRightForm.setRows(groupRightForm.getGroupIds());
            groupRightForm.setRowNames(groupRightForm.getGroupNames());

            if (groupRightForm.getType().equals("addressBook")) {
                groupRightForm.setEmptyListMessage(
                    "groupRight.addressBook.label.list.empty");
            } else {
                groupRightForm.setEmptyListMessage(
                    "groupRight.userGroup.label.list.empty");
            }
        } else {
            groupRightForm.setIncludePage("/addressBook/groupRightGeneral.jsp");

            if (groupRightForm.getType().equals("addressBook")) {
                groupRightForm.setHelpKey("addressbook.groupRightUser");
            }
        }

        // if we should add a group to the list, get the value
        Integer addGroup = groupRightForm.getAddGroup();

        if ((addGroup != null) && !addGroup.equals(new Integer(0))) {
            GroupDO group = addressBook.findGroupByPrimaryKey(securitySession,
                    addGroup);

            // only add if the id is not already there
            if (!groupRightForm.getGroupIds().contains(group.getId())) {
                groupRightForm.getGroupIds().add(group.getId());
                groupRightForm.getGroupNames().add(group.getName());
            }
        }

        // see if the remove button was pressed
        if (!StringHandling.isNullOrEmpty(groupRightForm.getRemove())) {
            String[] selected = groupRightForm.getSelected();

            for (int i = 0; i < selected.length; i++) {
                Integer selectedId = StringHandling.integerValue(selected[i]);

                for (int index = 0; index < groupRightForm.getRows().size();
                        ++index) {
                    // is this the id to remove?
                    if (groupRightForm.getRows().get(index).equals(selectedId)) {
                        groupRightForm.getRows().remove(index);
                        groupRightForm.getRowNames().remove(index);

                        break;
                    }
                }
            }
        }

        return "addressBookGroupRight";
    }

    /**
     * <p>This method is called if the ok or apply buttons are pressed.</p>
     *
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     * @param ok <code>true</code> if the ok button was pressed, otherwise
     * <code>false</code> if the apply button was pressed.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     */
    public String onConfirm(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session,
            final String defaultForward)
        throws SystemException {
        GroupRightForm groupRightForm = (GroupRightForm) form;
        GroupDO group = groupRightForm.getGroup();
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        UserDO user = securitySession.getUser();

        // if I have not ID , so it's new group
        if (group.getId() == null) {
            group.setCreatedBy(user);

            // see what are we going add: addressBook or userGroup
            if (groupRightForm.getType().equals("addressBook")) {
                group = addressBook.addAddressBook(securitySession, group);
            } else {
                group = addressBook.addUserGroup(securitySession, group);
            }

            groupRightForm.setGroup(group);
            groupRightForm.setReadOnly(false);
            groupRightForm.setCanRemove(true);
        } else {
            try {
                // see what are we going amend: addressBook or userGroup
                if (groupRightForm.getType().equals("addressBook")) {
                    group = addressBook.amendGroup(securitySession, group);
                } else {
                    group = addressBook.amendGroup(securitySession, group);
                }

                groupRightForm.setGroup(group);
            } catch (SystemException e) {
                errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("errors.addressBook.group.amend",
                        group.getName()));
            }
        }

        // do thi only if user schoose rights
        if ((groupRightForm.getGroupRightsAdd() != null) &&
                (groupRightForm.getGroupRightsAmend() != null) &&
                (groupRightForm.getGroupRightsRemove() != null) &&
                (groupRightForm.getGroupRightsView() != null)) {
            // now go thro' all the groups and amend the rights
            updateGroupRights(securitySession, groupRightForm, groupRightForm.getGroup());

            // refresh list of rigts
            // now get and set all of the address book rights
            Set allGroups = new TreeSet();
            Collection addressBookView = addressBookRights.findRightsForGroup(securitySession,
                    group, RightConstants.ACCESS_VIEW);
            allGroups.addAll(addressBookView);
            groupRightForm.setGroupRightsView(convertGroupCollectionToStringArray(
                    addressBookView));

            Collection addressBookAdd = addressBookRights.findRightsForGroup(securitySession,
                    group, RightConstants.ACCESS_ADD);
            allGroups.addAll(addressBookAdd);
            groupRightForm.setGroupRightsAdd(convertGroupCollectionToStringArray(
                    addressBookAdd));

            Collection addressBookAmend = addressBookRights.findRightsForGroup(securitySession,
                    group, RightConstants.ACCESS_AMEND);
            allGroups.addAll(addressBookAmend);
            groupRightForm.setGroupRightsAmend(convertGroupCollectionToStringArray(
                    addressBookAmend));

            Collection addressBookRemove = addressBookRights.findRightsForGroup(securitySession,
                    group, RightConstants.ACCESS_REMOVE);
            allGroups.addAll(addressBookRemove);
            groupRightForm.setGroupRightsRemove(convertGroupCollectionToStringArray(
                    addressBookRemove));

            Vector groupIds = new Vector();
            Vector groupNames = new Vector();

            for (Iterator i = allGroups.iterator(); i.hasNext();) {
                Integer userGroupId = (Integer) i.next();
                groupIds.add(userGroupId);
                groupNames.add(addressBook.findGroupByPrimaryKey(
                        securitySession, userGroupId).getName());
            }

            groupRightForm.setGroupIds(groupIds);
            groupRightForm.setGroupNames(groupNames);

            groupRightForm.setRows(groupRightForm.getGroupIds());
            groupRightForm.setRowNames(groupRightForm.getGroupNames());

            // set Up READ ONLY flag
            groupRightForm.setReadOnly(!addressBookRights.canAmendInGroup(
                    securitySession, group));

            // set Up CAN REMOVE flag
            groupRightForm.setCanRemove(addressBookRights.canRemoveFromGroup(
                    securitySession, group));
        }

        // default put everyone group to list
        if (!groupRightForm.getGroupIds().contains(GroupConstants.USER_GROUP)) {
            groupRightForm.getGroupIds().add(GroupConstants.USER_GROUP);
            groupRightForm.getGroupNames().add("everyone");
        }

        // flush the cached contact trees
        // flushCache("ContactTree",javax.servlet.jsp.PageContext.APPLICATION_SCOPE, request);
        // close the pop-up if this is ok
        if (groupRightForm.getOk() != null) {
            // the form is finished now
            session.removeAttribute("addressBookGroupRightForm");

            return "utilClosePopUp";
        }

        return defaultForward;
    }

    /**
     * <p>This method is called if the delete (confirm, not warn) button
     * is pressed.</p>
     * @param mapping The ActionMapping used to select this instance.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param log valid logging object to write messages to.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     *
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     */
    public String onDelete(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session, final String defaultForward)
            throws SystemException {
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        GroupRightForm groupRightForm = (GroupRightForm) form;
        GroupDO group = groupRightForm.getGroup();

        try {
            // see what are we going remove: addressBook or userGroup
            if (groupRightForm.getType().equals("addressBook")) {
                addressBook.removeGroup(securitySession, group.getId());
            } else {
                addressBook.removeGroup(securitySession, group.getId());
            }
        } catch (SystemException e) {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("errors.addressBook.group.remove",
                    group.getName()));
        }

        return null;
    }

    /**
     * <p>Update all the rights concerning groups. This is called by <code>onConfirm</code>.</p>
     *
     *
     * @param groupRightForm the form which has just been submitted.
     * @param addressBookRights remote address book rights object.
     * @param userName the name of the user who is submitting the form - used to
     *     check user rights of the user to change rights.
     * @param groupId the unique identifier of the group for whom the rights are
     *     being set.
     * @throws RemoteException
     */
    private void updateGroupRights(final SecuritySession securitySession,
            final GroupRightForm groupRightForm,
            final GroupDO group)
            throws SystemException {
        // the arrays of rights and access must match up!
        String[][] rightsArray = {
            groupRightForm.getGroupRightsAdd(),
            groupRightForm.getGroupRightsAmend(),
            groupRightForm.getGroupRightsRemove(),
            groupRightForm.getGroupRightsView()
        };
        Integer[] accessArray = {
            RightConstants.ACCESS_ADD, RightConstants.ACCESS_AMEND,
            RightConstants.ACCESS_REMOVE, RightConstants.ACCESS_VIEW
        };

        for (Iterator i = groupRightForm.getGroupIds().iterator(); i.hasNext();) {
            Integer id = (Integer) i.next();

            Vector rightsForGroup = new Vector();
            boolean hasRightNow;

            for (int index = 0; index < accessArray.length; index++) {
                Integer access = accessArray[index];
                String[] rights = rightsArray[index];

                // if we are maintaining user group so group "everyone" has always "view" access to each userGroup
                if (access.equals(RightConstants.ACCESS_VIEW) &&
                        groupRightForm.getType().equals("userGroup") &&
                        GroupConstants.equals(id, GroupConstants.USER_GROUP)) {
                    hasRightNow = true;
                } else {
                    Arrays.sort(rights);
                    hasRightNow = Arrays.binarySearch(rights, id.toString()) >= 0;
                }

                rightsForGroup.addAll(addressBookRights.findRightsForGroup(
                        securitySession, group, access));

                // if it wasn't there before, and is now, then add it
                if (!rightsForGroup.contains(id) && hasRightNow) {
                    rightsForGroup.add(id);
                    addressBookRights.amendRightsForGroup(securitySession,
                        group, rightsForGroup, access);

                    // otherwise find if it was there before and isn't now
                } else if (rightsForGroup.contains(id) && !hasRightNow) {
                    rightsForGroup.remove(id);
                    addressBookRights.amendRightsForGroup(securitySession,
                        group, rightsForGroup, access);
                }

                rightsForGroup.clear();
            }
        }
    }
}

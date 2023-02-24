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
 * $Log: GroupRightForm.java,v $
 * Revision 1.4  2005/04/29 02:48:14  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
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
 * Revision 1.6  2004/12/23 21:01:25  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.5  2004/11/12 18:19:14  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
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
 * Revision 1.5  2003/11/18 09:55:46  jano
 * commiting all forgoten staff
 *
 * Revision 1.4  2003/11/13 16:03:16  jano
 * commitng everything to CVS
 * can deploy and application is ruuning, can login into
 *
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.4  2003/08/14 15:54:42  jano
 * fixing bugs
 *
 * Revision 1.3  2003/08/13 13:32:43  jano
 * addressBook exttension: next level
 *
 * Revision 1.2  2003/08/05 14:57:35  jano
 * addressBook extension
 *
 * Revision 1.1  2003/06/02 22:17:48  colin
 * changes for new user rights interface
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.struts;

import java.io.Serializable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.business.addressbook.AddressBook;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.mask.Mask;
import com.ivata.mask.validation.ValidationErrors;
import com.ivata.mask.web.struts.DialogForm;


/**
 * <p>Contains details of AddressBook or UserGroup and  rights being changed for that kindof  group. Field type is saying what we are maintaining : addressBook = 1, userGroup = 2.</p>
 *
 * @since 2003-05-10
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 */
public class GroupRightForm extends DialogForm implements Serializable {
    /**
     * <p>Button for add group to the right list.</p>
     */
    private String add = null;
    /**
     *<p> if user did click "add" this will contain id of group which is *going to be add.</p>
     */
    private Integer addGroup = null;
    private AddressBook addressBook;
    /**
     * <p> if user has right to remove the userGroup/addressBook is this true.</p>
     */
    private boolean canRemove = false;
    /**
     * <p>If list of rights is empty write this message out.</p>
     */
    private String emptyListMessage = null;
    /**
     * <p>Instance of group data object, containing all values
     * of group being submitted.</p>
     */
    private GroupDO group = new GroupDO();
    /**
     * <p><code>Vector</code> containing <code>Integer</code> instances,
     * representing ids of all the groups users the current group.</p>
     */
    private Vector groupIds = new Vector ();
    /**
     * <p><code>Vector</code> containing <code>String</code> instances,
     * representing names of all the groups users the current group.</p>
     */
    private Vector groupNames = new Vector ();
    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can add to.</p>
     */
    private String[] groupRightsAdd;
    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can amend in.</p>
     */
    private String[] groupRightsAmend;
    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can delete from.</p>
     */
    private String[] groupRightsRemove;
    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can view.</p>
     */
    private String[] groupRightsView;

    /**
     * <p>The number of the active tab being displayed, starting with 0.</p>
     */
    private Integer groupRightTab_activeTab = null;
    /**
     * <p>The URI of the page which will be included to represent the currently
     * chosen tab.</p>
     */
    private String includePage = "/addressBook/groupRightGeneral.jsp";
    /**
     * <p> if user has not right to chage addressBook / useGroup it's true.</p>
     */
    private boolean readOnly = false;
    private String remove;
    /**
     * <p>Indicates the names of 'rows' of the list. This will either be
     * <code>groupNames</code> or <code>topicCaptions</code>, depending on the
     * list currently being displayed.</p>
     */
    private Vector rowNames;
    /**
     * <p>Indicates the 'rows' of the list. This will either be <code>groupIds</code>
     * or <code>topicIds</code>, depending on the list currently being displayed.</p>
     */
    private Vector rows;
    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * topics or groups which have been selected for deletion.</p>
     */
    private String[] selected;
    /**
     * <p> We can maintain AddressBook or UserGroup.</p>
     */
    private String type = null;
    /**
     * <p>
     * Defines the base class of all objects in the value object list.
     * </p>
     */
    private Class baseClass;
    /**
     * <p>
     * Mask containing all the field definitions for this list.
     * </p>
     */
    private Mask mask;

    /**
     * <p>
     * Construct a group right form.
     * </p>
     *
     * @param addressBook address book implementation to use.
     * @param maskParam Refer to {@link DialogForm#DialogForm}.
     * @param baseClassParam Refer to {@link DialogForm#DialogForm}.
     */
    GroupRightForm(final AddressBook addressBook,
            final Mask maskParam,
            final Class baseClassParam) {
        this.addressBook = addressBook;
    }

    /**
     * <p>
     * Return all form state to initial values.
     * </p>
     *
     * @see com.ivata.mask.web.struts.MaskForm#clear()
     */
    protected void clear() {
        add = null;
        addGroup = null;
        canRemove = false;
        emptyListMessage = null;
        group = new GroupDO();
        groupIds = new Vector ();
        groupNames = new Vector ();
        groupRightTab_activeTab = null;
        setHelpKey("addressbook.groupRightUser");
        includePage = "/addressBook/groupRightGeneral.jsp";
        readOnly = false;
        remove = null;
        rowNames = null;
        rows = null;
        selected = null;
        type = null;
    }

    /**
     * <p>Get whether or not the add button was pressed.</p>
     *
     * <p>If the add button was pressed, this attribute is set to a non-null
     * string.</p>
     *
     * @return non-<code>null</code> if the add key was pressed, otherwise
     * <code>null</code>.
     */
    public final String getAdd() {
        return add;
    }

    /**
     *<p> if user did click "add" this will contain id of group which is
     * *going to be added.</p>
     *
     * @return the current value of addGroup.
     */
    public final Integer getAddGroup() {
        return this.addGroup;
    }

    /**
     * <p> if user has right to remove the userGroup/addressBook is this true.</p>
     *
     * @return the current value of canRemove.
     */
    public final boolean getCanRemove() {
        return this.canRemove;
    }

    /**
     * <p>Description of group.</p>
     *
     * @return the current value of group.description
     */
    public final String getDescription() {
        return this.group.getDescription();
    }

    /**
     * <p>If list of rights is empty write this message out.</p>
     *
     * @return the current value of emptyListMessage.
     */
    public final String getEmptyListMessage() {
        return this.emptyListMessage;
    }

    /**
     * <p>Instance of group data object, containing all values
     * of group being submitted.</p>
     *
     * @return the current value of group.
     */
    public final GroupDO getGroup() {
        return this.group;
    }

    /**
     * <p>Get a <code>Vector</code> containing <code>Integer</code> instances,
     * representing ids of all the groups users the current group.</p>
     *
     * @return current value of groupIds.
     */
    public final Vector getGroupIds() {
        return groupIds;
    }

    /**
     * <p>Get a <code>Vector</code> containing <code>Integer</code> instances,
     * representing ids of all the groups users the current group.</p>
     *
     * @return current value of groupNames.
     */
    public final Vector getGroupNames() {
        return groupNames;
    }

    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can add to.</p>
     *
     * @return the current value of groupRightsAdd[].
     */
    public final String[] getGroupRightsAdd() {
        return this.groupRightsAdd;
    }

    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can amend in.</p>
     *
     * @return the current value of groupRightsAmend[].
     */
    public final String[] getGroupRightsAmend() {
        return this.groupRightsAmend;
    }

    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can delete from.</p>
     *
     * @return the current value of groupRightsRemove[].
     */
    public final String[] getGroupRightsRemove() {
        return this.groupRightsRemove;
    }

    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can view.</p>
     *
     * @return the current value of groupRightsView[].
     */
    public final String[] getGroupRightsView() {
        return this.groupRightsView;
    }

    /**
     * <p>Get the number of the active tab being displayed, starting with 0.</p>
     *
     * @return current value of groupTab_activeTab.
     */
    public final Integer getGroupRightTab_activeTab() {
        return groupRightTab_activeTab;
    }

    /**
     * <p>Get the URI of the page which will be included to represent the currently
     * chosen tab.</p>
     *
     * @return the current value of includePage.
     */
    public final String getIncludePage() {
        return includePage;
    }

    /**
     * <p>Name of group.</p>
     *
     * @return the current value of group.name
     */
    public String getName() {
        return this.group.getName();
    }

    /**
     * <p> if user has not right to chage addressBook / useGroup it's true.</p>
     *
     * @return the current value of readOnly.
     */
    public final boolean getReadOnly() {
        return this.readOnly;
    }

    /**
     * <p>Get whether or not the remove button was pressed.</p>
     *
     * <p>If the remove button was pressed, this attribute is set to a non-null
     * string.</p>
     *
     * @return non-<code>null</code> if the remove key was pressed, otherwise
     * <code>null</code>.
     */
    public final String getRemove() {
        return remove;
    }

    /**
     * <p>Get the names of 'rows' of the list.</p>
     *
     * @return <code>groupNames</code> or <code>topicCaptions</code>, depending
     * on the list currently being displayed.</p>
     */
    public final Vector getRowNames() {
        return this.rowNames;
    }

    /**
     * <p>Get the 'rows' of the list.</p>
     *
     * @return <code>groupIds</code> or <code>topicIds</code>, depending on the
     * list currently being displayed.</p>
     */
    public final Vector getRows() {
        return this.rows;
    }

    /**
     * <p>Get a <code>String</code> array of numbers representing the id's of the
     * topics or groups which have been selected for deletion.</p>
     *
     * @return current value of selected
     */
    public final String[] getSelected() {
        return selected;
    }

    /**
     * <p> We can maintain AddressBook or UserGroup.</p>
     *
     * @return the current value of type.
     */
    public final String getType() {
        return this.type;
    }

    /**
     * <p>Reset all bean properties to their default state.  This method
     * is called before the properties are repopulated by the controller
     * servlet.</p>
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(final ActionMapping mapping,
            final HttpServletRequest request) {
        super.reset(mapping, request);
        setGroupRightTab_activeTab(new Integer(0));
        selected = new String[0];

        if (includePage.equals("/addressBook/groupRightDetail.jsp")) {
            groupRightsView = new String [0];
            groupRightsAdd = new String [0];
            groupRightsAmend = new String [0];
            groupRightsRemove = new String [0];
        }
    }

    /**
     * <p>Set whether or not the add button was pressed.</p>
     *
     * <p>If the add button was pressed, this attribute is set to a non-null
     * string.</p>
     *
     * @param add set to non-<code>null</code> if the add key was pressed, otherwise
     * <code>null</code>.
     */
    public final void setAdd(final String add) {
        this.add = add;
    }

    /**
     *<p> if user did click "add" this will contain id of group which is *going to be add.</p>
     *
     * @param addGroup the new value of addGroup.
     */
    public final void setAddGroup(final Integer addGroup) {
        this.addGroup = addGroup;
    }

    /**
     * <p> if user has right to remove the userGroup/addressBook is this true.</p>
     *
     * @param canRemove the new value of canRemove.
     */
    public final void setCanRemove(final boolean canRemove) {
        this.canRemove = canRemove;
    }

    /**
     * <p>Description of group.</p>
     *
     * @param description the new value of group.description
     */
    public final void setDescription(final String description) {
        this.group.setDescription(description);
    }

    /**
     * <p>If list of rights is empty write this message out.</p>
     *
     * @param emptyListMessage the new value of emptyListMessage.
     */
    public final void setEmptyListMessage(final String emptyListMessage) {
        this.emptyListMessage = emptyListMessage;
    }

    /**
     * <p>Instance of group data object, containing all values
     * of group being submitted.</p>
     *
     * @param group the new value of group.
     */
    public final void setGroup(final GroupDO group) {
        this.group = group;
    }

    /**
     * <p>Set a <code>Vector</code> containing <code>Integer</code> instances,
     * representing ids of all the groups users the current group.</p>
     *
     * @param groupIds new value of groupIds.
     */
    public final void setGroupIds(final Vector groupIds) {
        this.groupIds = groupIds;
    }

    /**
     * <p>Set a <code>Vector</code> containing <code>Integer</code> instances,
     * representing ids of all the groups users the current group.</p>
     *
     * @param groupNames new value of groupNames.
     */
    public final void setGroupNames(final Vector groupNames) {
        this.groupNames = groupNames;
    }

    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can add to.</p>
     *
     * @param groupRightsAdd[] the new value of groupRightsAdd[].
     */
    public final void setGroupRightsAdd(final String groupRightsAdd[]) {
        this.groupRightsAdd = groupRightsAdd;
    }

    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can amend in.</p>
     *
     * @param groupRightsAmend[] the new value of groupRightsAmend[].
     */
    public final void setGroupRightsAmend(final String groupRightsAmend[]) {
        this.groupRightsAmend = groupRightsAmend;
    }

    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can delete from.</p>
     *
     * @param groupRightsRemove[] the new value of groupRightsRemove[].
     */
    public final void setGroupRightsRemove(final String groupRightsRemove[]) {
        this.groupRightsRemove = groupRightsRemove;
    }

    /**
     * <p><code>String</code> array of numbers representing the id's of the
     * contact groups which users in the current group can view.</p>
     *
     * @param groupRightsView[] the new value of groupRightsView[].
     */
    public final void setGroupRightsView(final String groupRightsView[]) {
        this.groupRightsView = groupRightsView;
    }

    /**
     * <p>Set the number of the active tab being displayed, starting with 0.</p>
     *
     * @param groupRightTab_activeTab the new value of groupTab_activeTab.
     */
    public final void setGroupRightTab_activeTab(final Integer groupRightTab_activeTab) {
        this.groupRightTab_activeTab = groupRightTab_activeTab;
    }

    /**
     * <p>Set the URI of the page which will be included to represent the currently
     * chosen tab.</p>
     *
     * @param includePage new value of includePage.
     */
    public final void setIncludePage(final String includePage) {
        this.includePage = includePage;
    }

    /**
     * <p>Name of group.</p>
     *
     * @param name the new value of group.name
     */
    public final void setName(final String name) {
        this.group.setName(name);
    }

    /**
     * <p> if user has not right to chage addressBook / useGroup it's true.</p>
     *
     * @param readOnly the new value of readOnly.
     */
    public final void setReadOnly(final boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * <p>Set whether or not the remove button was pressed.</p>
     *
     * <p>If the remove button was pressed, this attribute is set to a non-null
     * string.</p>
     *
     * @param remove set to non-<code>null</code> if the remove key was pressed,
     * otherwise <code>null</code>.
     */
    public final void setRemove(final String remove) {
        this.remove = remove;
    }

    /**
     * <p>Set the names of 'rows' of the list.</p>
     *
     * @param rowNames <code>groupNames</code> or <code>topicCaptions</code>, depending
     * on the list currently being displayed.</p>
     */
    public final void setRowNames(final Vector rowNames) {
        this.rowNames = rowNames;
    }

    /**
     * <p>Set the 'rows' of the list.</p>
     *
     * @param rows <code>groupIds</code> or <code>topicIds</code>, depending on the
     * list currently being displayed.</p>
     */
    public final void setRows(final Vector rows) {
        this.rows = rows;
    }

    /**
     * <p>Set a <code>String</code> array of numbers representing the id's of the
     * topics which have been selected for deletion.</p>
     *
     * @param new value of libraryRightsAdd.
     */
    public final void setSelected(final String[] selected) {
        this.selected = selected;
    }

    /**
     * <p> We can maintain AddressBook or UserGroup.</p>
     *
     * @param type the new value of type.
     */
    public final void setType(final String type) {
        this.type = type;
    }

    /**
     * <p>Validates the form contents.</p>
     *
     * @param addressBook valid <code>AddressBookRemote</code> instance.
     * @param locale locale used to show error messages.
     * @param session current session to set/get attributes from.
     * @return <code>null</code> or an <code>ActionMessages</code> object
     * with recorded error messages.
     */
    public ValidationErrors validate(final HttpServletRequest request,
            final HttpSession session) {
        ValidationErrors validationErrors = new ValidationErrors();

        return validationErrors;
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
}

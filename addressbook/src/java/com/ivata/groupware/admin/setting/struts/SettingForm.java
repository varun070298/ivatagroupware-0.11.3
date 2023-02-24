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
 * $Log: SettingForm.java,v $
 * Revision 1.3  2005/04/10 19:46:46  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:05  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:43  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.8  2004/12/23 21:01:25  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.7  2004/11/12 18:19:13  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.6  2004/11/12 15:56:47  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.5  2004/07/13 19:41:12  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:05  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/10 19:57:21  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:47  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.5  2004/01/12 13:59:40  jano
 * fixing bugs
 *
 * Revision 1.4  2003/11/13 16:03:15  jano
 * commitng everything to CVS
 * can deploy and application is ruuning, can login into
 *
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:04:21  colin
 * fixing for XDoclet
 *
 * Revision 1.15  2003/03/05 13:29:11  peter
 * fixed the validation for boolean
 *
 * Revision 1.14  2003/03/05 11:02:23  peter
 * added handling for setting checkboxes - boolean settings
 *
 * Revision 1.13  2003/03/05 07:36:44  peter
 * fixed fieldNames in client-side validation
 *
 * Revision 1.12  2003/02/28 09:38:28  jano
 * RuntimeException(e) -> IntrnetRuntimeException
 *
 * Revision 1.11  2003/02/24 18:56:15  colin
 * added to admin
 *
 * Revision 1.10  2003/02/20 20:47:34  colin
 * improved validation by adding ValidationField and ValidationException
 *
 * Revision 1.9  2003/02/19 18:20:46  peter
 * fixed validation and input format doubling
 *
 * Revision 1.8  2003/02/18 17:04:42  peter
 * fixed checkBox resetting in reset()
 *
 * Revision 1.7  2003/02/14 17:02:02  peter
 * dateTime settings added
 *
 * Revision 1.6  2003/02/12 14:01:42  peter
 * reset for settingOverride checkbox Map fixed
 *
 * Revision 1.3  2003/02/05 18:15:07  peter
 * works on validation, and changed to Map backed form
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.setting.struts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.setting.SettingConstants;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.mask.Mask;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationError;
import com.ivata.mask.validation.ValidationErrors;
import com.ivata.mask.web.struts.DialogForm;


/**
 * <p>The form used to input/display data in
 * preferences (setting/index.jsp) </p>
 *
 * @since 2003-01-29
 * @author Peter Illes
 * @version $Revision: 1.3 $
 */
public class SettingForm extends DialogForm {
    /**
     * <p>when not null or non-empty string, the current user can change
     * system settings</p>
     */
    private String administrator;
    /**
     * <p>holds the values of the visible fields in tabs of
     * <code>/setting/index.jsp</code>, but it's only used because <strong>HTML
     * tags</strong> have the <code>property<code> property mandatory</p>
     */
    private Map setting;
    /**
     * <p>The area where the settings change takes place: "user" or
     * "system"</p>
     */
    private String settingArea;
    /**
     * <p>this map holds the override information for  for all settings
     * stored  in <code>settingUser</code> and <code>settingSystem</code>
     * as
     * boolean, it's only used when a user with rights to change system
     * settings is the current user. An element with the key setting name
     * has value <code>true</code> if users can override it....</p>
     */
    private Map settingOverride;
    /**
     * <p>
     * ivata settings implementation - used in validation.
     * </p>
     */
    private Settings settings;
    /**
     * <p>holds the values of the system settings</p>
     */
    private Map settingSystem;

    /**
     * <p>stores the active tab</p>
     */
    private Integer settingTab_activeTab;
    /**
     * <p>stores the names of system settings for individual tabs as
     * <code>List</code>s of <code>String</code>s.</p>
     */
    private List settingTabSystem;
    /**
     * <p>stores the names of user settings for individual tabs as
     * <code>List</code>s of <code>String</code>s.</p>
     */
    private List settingTabUser;
    /**
     * <p>this map holds the types for all settings stored  in
     * <code>settingUser</code> and  <code>settingSystem</code></p>
     */
    private Map settingType;
    /**
     * <p>holds the values of the user settings</p>
     */
    private Map settingUser;
    /**
     * <p>holds the list of tab names, the list of tabs shown varies
     * because of user rights</p>
     */
    private List tabName;
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
     * Construct a valid setting form.
     * </p>
     *
     * @param maskParam Refer to {@link DialogForm#DialogForm}.
     * @param baseClassParam Refer to {@link DialogForm#DialogForm}.
     * @param settings ivata settings implementation - used in validation.
     */
    public SettingForm(final Mask maskParam,
            final Class baseClassParam,
            final Settings settings) {
        this.settings = settings;
        clear();
    }

    /**
     * TODO
     *
     * @see com.ivata.mask.web.struts.MaskForm#clear()
     */
    protected void clear() {
        administrator = null;
        setting = new HashMap();
        settingArea = "user";
        settingOverride = new HashMap();
        settingSystem = new HashMap();
        settingTab_activeTab = null;
        settingTabSystem = null;
        settingTabUser = null;
        settingType = new HashMap();
        settingUser = new HashMap();
        tabName = null;
    }

    /**
     * <p>gets the administrator flag, that is when not null or non-empty
     * string, the current user can change system settings</p>
     * @return  when not null or non-empty string, the current user can
     * change system settings
     */
    public final String getAdministrator() {
        return administrator;
    }

    /**
     * <p>always returns empty string</p>
     * @param key the name of the setting
     * @return empty string
     */
    public final String getSetting(final String key) {
        return "";
    }

    /**
     * <p>Gets the area where the settings change takes place: "user" or
     * "system"</p>
     * @return "system" or "user"
     */
    public final String getSettingArea() {
        return settingArea;
    }

    /**
     * <p>returns the names of all  settings from the setting form as a
     * <code>Set</code>. The set may vary because of user rights and the
     * fact that some settings can be disabled (locked).</p>
     * @return names of settings
     */
    public final Set getSettingName() {
        return settingType.keySet();
    }

    /**
     * <p>returns the override status for a setting, only used when the
     * current user has rights to change system settings</p>
     * @param key the name of the setting
     * @return <code>true</code> when this setting can be overriden by
     * users,   <code>false</code> otherwise
     */
    public final boolean getSettingOverride(final String key) {
        return ((Boolean) settingOverride.get(key)).booleanValue();
    }

    /**
     * <p>returns a  value of the system settings</p>
     * @param key the name of the setting
     * @return the value of the setting
     */
    public final Object getSettingSystem(final String key) {
        return settingSystem.get(key);
    }

    /**
     * <p>getter for  the active tab field</p>
     * @return the id of the active tab as <code>Integer</code>
     */
    public final Integer getSettingTab_activeTab() {
        return settingTab_activeTab;
    }

    /**
     * <p>stores the names of system settings for individual tabs as
     * <code>List</code>s of <code>String</code>s.</p>
     *
     * @return the current value of settingTabSystem.
     */
    public List getSettingTabSystem() {
        return settingTabSystem;
    }

    /**
     * <p>returns the system setting names for the current  tab as comma
     * separated string with quotes (used for javascript array
     * definition)</p>
     * @return comma separated string with quotes (used for javascript
     * array definition)
     */
    public final String getSettingTabSystemFields() {
        String returnString = "";

        for (Iterator i =
                ((List) settingTabSystem.get(settingTab_activeTab.intValue())).iterator();
            i.hasNext();) {
            returnString += ("'" + (String) i.next() + "'");
            if (i.hasNext()) {
                returnString += ",";
            }
        }
        return returnString;
    }

    /**
     * <p>stores the names of user settings for individual tabs as
     * <code>List</code>s of <code>String</code>s.</p>
     *
     * @return the current value of settingTabUser.
     */
    public List getSettingTabUser() {
        return settingTabUser;
    }

    /**
     * <p>returns the user setting names for the current  tab as comma
     * separated string with quotes (used for javascript array
     * definition)</p>
     * @return comma separated string with quotes (used for javascript
     * array definition)
     */
    public final String getSettingTabUserFields() {
        String returnString = "";

        for (Iterator i =
                ((List) settingTabUser.get(settingTab_activeTab.intValue())).iterator();
            i.hasNext();) {
            returnString += ("'" + (String) i.next() + "'");
            if (i.hasNext()) {
                returnString += ",";
            }
        }
        return returnString;
    }

    /**
     * <p>returns the type for one of  the settings</p>
     * @param key the name of the setting
     * @return the type of the setting as <code>SettingConstants</code>
     */
    public final Integer getSettingType(final String key) {
        return (Integer) settingType.get(key);
    }

    /**
     * <p>returns a  value of the user settings</p>
     * @param key the name of the setting
     * @return the value of the setting
     */
    public final Object getSettingUser(final String key) {
        return settingUser.get(key);
    }

    /**
     * <p>returns the names of the displayed tabs</p>
     * @return  the names of displayed tabs as <code>List</code> of
     * <code>String</code>s
     */
    public List getTabName() {
        return tabName;
    }

    /**
     * <p>Reset appopriate bean properties to their default state.  This
     * method
     * is called before the properties are repopulated by the controller
     * servlet.</p>
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     *
     */
    public void reset(final ActionMapping mapping,
            final HttpServletRequest request) {

        if (settingTab_activeTab!=null) {

            // reset the override checkboxes from the last tab to <code>false</code>
            // because they're specific, only the 'succesful' ones are set
            int activeTab = settingTab_activeTab.intValue();
            for (Iterator i = ((List) getSettingTabSystem().get(activeTab)).iterator();
                    i.hasNext();) {
                String currentKey = (String) i.next();
                settingOverride.put(currentKey, new Boolean(false));
            }
            for (Iterator i = ((List) getSettingTabUser().get(activeTab)).iterator();
                    i.hasNext();) {
                String currentKey = (String) i.next();
                settingOverride.put(currentKey, new Boolean(false));
            }
            // reset the checkboxes on email tab
            if (((String) tabName.get(activeTab)).equals("email")) {
                if (settingType.containsKey("emailSignatureForward")) {
                   settingUser.put("emailSignatureForward","false");
                   settingSystem.put("emailSignatureForward","false");
                }
                if (settingType.containsKey("emailSignatureReply")) {
                   settingUser.put("emailSignatureReply","false");
                   settingSystem.put("emailSignatureReply","false");
                }
            }
        }

        // buttons
        setApply(null);
        setOk(null);

    }

    /**
     * <p>sets the administrator flag, that is when not null or non-empty
     * string, the current user can change system settings</p>
     * @param administrator  when not null or non-empty string, the
     * current user can change system settings
     */
    public final void setAdministrator(final String administrator) {
        this.administrator = administrator;
    }

    /**
     * <p>sets the last value from the visible field of a setting, but
     * this value is not used, {@see getSetting(String) </p>
     * @param key the name of the setting
     * @param the value of the visible field of a setting
     */
    public final void setSetting(final String key,
            final String value) {
    }

    /**
     * <p>Sets the area where the settings change takes place: "user" or
     * "system"</p>
     * @param settingArea
     */
    public final void setSettingArea(final String settingArea) {
        this.settingArea = settingArea;
    }

    /**
     * <p>sets the override status for a setting, only used when the
     * current user has rights to change system settings</p>
     * @param key the name of the setting
     * @param overridable  <code>true</code> when this setting can be
     * overriden by users,   <code>false</code> otherwise
     */
    public final void setSettingOverride(final String key,
            final boolean overridable) {
        settingOverride.put(key, new Boolean(overridable));
    }

    /**
     * <p>sets a  value of the system settings</p>
     * @param key the name of the setting
     * @param value the value of the setting
     */
    public final void setSettingSystem(final String key,
            final Object value) {
        if ((key.equals("emailSignatureReply") || key.equals("emailSignatureForward"))
                && !(value instanceof Boolean)
                && StringHandling.isNullOrEmpty((String) value)) {
            return;
        } else {
            settingSystem.put(key, value);
        }
    }

    /**
     * <p>setter for  the active tab field</p>
     * @param settingTab_activeTab  the id of the active tab as
     * <code>Integer</code>
     */
    public final void setSettingTab_activeTab(final Integer settingTab_activeTab) {
        this.settingTab_activeTab = settingTab_activeTab;
    }

    /**
     * <p>stores the names of system settings for individual tabs as
     * <code>List</code>s of <code>String</code>s.</p>
     *
     * @param settingTabSystem the new value of settingTabSystem.
     */
    public final void setSettingTabSystem(final List settingTabSystem) {
        this.settingTabSystem = settingTabSystem;
    }

    /**
     * <p>stores the names of user settings for individual tabs as
     * <code>List</code>s of <code>String</code>s.</p>
     *
     * @param settingTabUser the new value of settingTabUser.
     */
    public final void setSettingTabUser(final List settingTabUser) {
        this.settingTabUser = settingTabUser;
    }

    /**
     * <p>sets the type of a settings</p>
     * @param key the name of the setting
     * @param type the type of the setting
     */
    public final void setSettingType(final String key,
            final Integer type) {
        settingType.put(key, type);
    }

    /**
     * <p>sets a  value of the user settings</p>
     * @param key the name of the setting
     * @param value the value of the setting
     */
    public final void setSettingUser(final String key,
            final Object value) {
        if ((key.equals("emailSignatureReply") || key.equals("emailSignatureForward"))
                && !(value instanceof Boolean)
                && StringHandling.isNullOrEmpty((String) value)) {
            return;
        } else {
            settingUser.put(key, value);
        }
    }

    /**
     * <p>sets the names of the tabs to display</p>
     * @param tabName <code> List of tab names
     */
    public final void setTabName(final List tabName) {
        this.tabName = tabName;
    }

    /**
     * Validate the settings for correct input,
     * and return an <code>ActionMessages</code> object that encapsulates
     * any
     * validation errors that have been found.  If no errors are found,
     * return <code>null</code>
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     *
     */
    public ValidationErrors validate(final HttpServletRequest request,
            final HttpSession session) {
        ValidationErrors validationErrors = new ValidationErrors();
        SecuritySession securitySession = (SecuritySession)
            request.getSession().getAttribute("securitySession");


        // only do the validation when ok or apply was pressed
        if (!(getOk() == null && getApply() == null)) {
            // we need these for server-side validation
            // these maps will contain only the settings that were ok here
            Map settingUserValidation = new HashMap(this.settingUser);
            Map settingSystemValidation = new HashMap(this.settingSystem);

            // setting area identification (user or system) for error messages
            ResourceBundle adminBundle =
                ResourceBundle.getBundle("com.ivata.groupware.admin.ApplicationResources",
                    (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY));

            String settingSystemArea = adminBundle.getString("setting.system");
            String settingUserArea = adminBundle.getString("setting.user");

            // first go through both the user and the system map and check the types
            for (Iterator i = settingType.keySet().iterator(); i.hasNext();) {
                String currentKey = (String) i.next();
                String currentFieldName = adminBundle.getString("setting.field." + currentKey);
                Integer currentType = (Integer) settingType.get(currentKey);
                // the values are strings when they were set from request or
                // the correct types when the user haven't submitted the appropriate
                // tab yet
                String currentUserValue = settingUser.get(currentKey).toString();
                String currentSystemValue = settingSystem.get(currentKey).toString();

                switch (currentType.intValue()) {
                    // check and conversion to Boolean
                    case SettingConstants.DATA_TYPE_BOOLEAN:
                        if (!(currentUserValue.equalsIgnoreCase("true") ||
                                currentUserValue.equalsIgnoreCase("false"))) {
                            validationErrors.add(
                                    new ValidationError(
                                            "errors.setting.boolean",
                                    Arrays.asList(new String[]{
                                            settingUserArea, currentFieldName
                                            })));
                            settingUserValidation.remove(currentKey);
                        } else {
                            settingUser.put(currentKey,
                                new Boolean(currentUserValue));
                            settingUserValidation.put(currentKey,
                                new Boolean(currentUserValue));
                        }
                        if (!(currentSystemValue.equalsIgnoreCase("true") ||
                                currentSystemValue.equalsIgnoreCase("false"))) {
                            validationErrors.add(
                                    new ValidationError(
                                            "errors.setting.boolean",
                                    Arrays.asList(new String[]{
                                            settingSystemArea, currentFieldName
                                            })));
                            settingSystemValidation.remove(currentKey);
                        } else {
                            settingSystem.put(currentKey,
                                new Boolean(currentSystemValue));
                            settingSystemValidation.put(currentKey,
                                new Boolean(currentSystemValue));
                        }
                        break;

                    //check and conversion to Integer
                    case SettingConstants.DATA_TYPE_INTEGER:
                        // check whether the string holds number representation
                        try {
                            Integer.parseInt(currentUserValue);
                            // when we're here it's ok
                            settingUser.put(currentKey, new Integer(currentUserValue));
                            settingUserValidation.put(currentKey,
                                new Integer(currentUserValue));
                        } catch (NumberFormatException e) {
                            // we catched this so the string didn't contain a number
                            validationErrors.add(
                                    new ValidationError(
                                            "errors.setting.integer",
                                    Arrays.asList(new String[]{
                                            settingUserArea, currentFieldName
                                            })));
                            settingUserValidation.remove(currentKey);
                        }
                        try {
                            Integer.parseInt(currentSystemValue);
                            // when we're here it's ok
                            settingSystem.put(currentKey, new Integer(currentSystemValue));
                            settingSystemValidation.put(currentKey,
                                new Integer(currentSystemValue));
                        } catch (NumberFormatException e) {
                            // we catched this so the string didn't contain a number
                            validationErrors.add(
                                    new ValidationError(
                                            "errors.setting.integer",
                                    Arrays.asList(new String[]{
                                            settingSystemArea, currentFieldName
                                            })));
                            settingSystemValidation.remove(currentKey);
                        }
                        break;

                        // the only other type is string so far, no special validation here
                    case SettingConstants.DATA_TYPE_STRING:

                        break;
                }
            }
            // we checked the types here, the real validation takes place on the
            // server, worth to do so when there are some settings with correct
            // types left
            if (!(settingUserValidation.isEmpty() && settingSystemValidation.isEmpty())) {
                Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
                ValidationErrors userErrors;
                try {
                    userErrors = settings.validate(securitySession,
                        settingUserValidation, locale, SettingConstants.SETTING_USER);
                } catch (SystemException e) {
                    throw new RuntimeException(e);
                }
                validationErrors.addAll(userErrors);
                ValidationErrors systemErrors;
                try {
                    systemErrors = settings.validate(securitySession,
                        settingSystemValidation, locale, SettingConstants.SETTING_SYSTEM);
                } catch (SystemException e) {
                    throw new RuntimeException(e);
                }
                validationErrors.addAll(systemErrors);
            }
        }
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

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
 * $Log: SetSettingTag.java,v $
 * Revision 1.3  2005/04/10 18:47:42  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:59  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:35  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/12 15:57:20  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.1  2004/09/30 15:16:02  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.4  2004/07/13 19:41:15  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:08  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:34  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:58  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.1.1.1  2003/10/13 20:50:10  colin
 * Restructured portal into subprojects
 *
 * Revision 1.1  2003/02/24 19:33:33  colin
 * moved to jsp
 *
 * Revision 1.7  2003/02/04 17:43:51  colin
 * copyright notice
 *
 * Revision 1.6  2002/08/19 12:24:38  colin
 * tidied documentation; removed Hungarian notation
 *
 * Revision 1.5  2002/06/13 07:44:07  colin
 * first version of rose model: code tidied up/added javadoc
 *
 * Revision 1.4  2002/05/11 18:32:41  colin
 * changed the name of the remote settings instance in the
 * session from "SettingsRemote" to "settings" for easier
 * usage in JSP files
 *
 * Revision 1.3  2002/05/11 08:54:06  colin
 * updated and extended javadoc
 *
 * Revision 1.1  2002/02/03 19:41:11  colin
 * corrected misspellt getSetting to GetSetting in class names
 *
 * Revision 1.1  2002/02/03 01:00:46  colin
 * first version for shop
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.Security;
import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.mask.util.SystemException;


/**
 * <p>This tag tries to set the setting for the user specified by the
 * attribute 'userName'. If that has not been set, then the default
 * setting is
 * altered.</p>
 *
 * <p>Normally there is a settings object in the session called
 * 'SettingsRemote'
 * and this is used internally to access the ivata groupware settings
 * system.</p>
 *
 * <p><strong>Tag attributes:</strong><br/>
 * <table cellpadding='2' cellspacing='5' border='0' align='center'
 * width='85%'>
 *   <tr class='TableHeadingColor'>
 *     <th>attribute</th>
 *     <th>reqd.</th>
 *     <th>param. class</th>
 *     <th width='100%'>description</th>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>setting</td>
 *     <td>true</td>
 *     <td><code>String</code></td>
 *     <td>The name of the setting to change in the ivata groupware
 * system.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>userName</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>The name of the user for whom the setting will be make. If
 * this
 * attribute is not set, then the default setting is altered.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>value</td>
 *     <td>true</td>
 *     <td><code>Object</code></td>
 *     <td>The new value for the setting specified. This object
 * should
 * be
 * of type <code>Integer</code> or
 * <code>String</code>
 * or <code>Boolean</code> and the type will be noted in
 * the
 * ivata groupware settings.</td>
 *   </tr>
 * </table>
 * </p>
 *
 * @since 2002-02-02
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class SetSettingTag extends TagSupport {
    /**
     * <p>Property declaration for tag attribute: setting.</p>
     */
    private String setting = null;
    /**
     * <p>Property declaration for tag attribute: userName.</p>
     */
    private String userName = null;
    /**
     * <p>Property declaraion for tag attribute: value.</p>
     */
    private Object value = null;

    /**
     * <p>Default constructor.</p>

     */
    public SetSettingTag() {
        super();
    }

    /**
     * <p>This method is called after the JSP engine finished processing
     * the tag.</p>
     *
     * @return <code>EVAL_PAGE</code> since we always want to evaluate the
     * rest of the page
     */
    public int doEndTag() {
        // we want the rest of the page after this tag to be evaluated...
        return EVAL_PAGE;
    }

    /**
     * <p>This method is called when the JSP engine encounters the start
     * tag, after the attributes are processed.<p>
     *
     * <p>Scripting variables (if any) have their values set here.</p>
     *
     * @return <code>SKIP_BODY</code> since this tag has no body
     * @throws JspException if there is no attribute in the session
     * called 'SettingsRemote'
     * @throws JspException if there is a
     * <code>java.rmi.RemoteException</code> assigning the setting
     */
    public int doStartTag() throws JspException {
        // before we do anything else, get the session
        HttpSession session = pageContext.getSession();
        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");

        if(securitySession == null) {
            throw new JspException("Error in SetSettingTag: no security session object was set in the  servlet session");
        }
        PicoContainer container = securitySession.getContainer();
        Settings settings = (Settings) container.getComponentInstance(Settings.class);
        Security security = (Security) container.getComponentInstance(Security.class);

        try {
            UserDO user = userName == null ? null : security.findUserByName(securitySession, userName);
            settings.amendSetting(securitySession, setting, value, user);
        } catch(SystemException e) {
            throw new JspException(e);
        }
        // indicates that the body should not be evaluated - this tag has no body
        return SKIP_BODY;
    }

    /**
     * <p>Get the value supplied to the attribute 'setting'.</p>
     *
     * <p>This attribute represents the name of the setting to change in
     * the ivata groupware system.</p>
     *
     * @return the value supplied to the attribute 'setting'
     */
    public final String getSetting() {
        return setting;
    }

    /**
     * <p>Get the value supplied to the attribute 'userName'.</p>
     *
     * <p>This attribute represents the name of the user for whom the
     * setting will be made. If this attribute is not set, then the
     * default setting is altered.</p>
     *
     * @return the value supplied to the tag attribute 'userName'
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * <p>Get the value supplied to the attribute 'value'.</p>
     *
     * <p>This attribute represents the new value for the setting
     * specified. This object should be of type
     * <code>Integer</code>, <code>String</code> or
     * <code>Boolean</code> and the type will be noted in the
     * ivata groupware settings.</p>
     *
     * @return the value supplied to the attribute 'value'
     */
    public final Object getValue() {
        return value;
    }

    /**
     * <p>Set the value supplied to the attribute 'setting'.</p>
     *
     * <p>This attribute represents the name of the setting to change in
     * the ivata groupware system.</p>
     *
     * @param setting the value supplied to the attribute 'setting'
     */
    public final void setSetting(final String setting) {
        this.setting = setting;
    }

    /**
     * <p>Set the value supplied to the tag attribute 'userName'</p>
     *
     * <p>This attribute represents the name of the user for whom the
     * setting will be made. If this attribute is not set, then the
     * default setting is altered.</p>
     *
     * @param userName the value supplied to the tag attribute 'userName'
     */
    public final void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * <p>Set the value supplied to the attribute 'value'.</p>
     *
     * <p>This attribute represents the new value for the setting
     * specified. This object should be of type
     * <code>Integer</code>, <code>String</code> or
     * <code>Boolean</code> and the type will be noted in the
     * ivata groupware settings.</p>
     *
     * @param value the value supplied to the attribute 'value'
     */
    public final void setValue(final Object value) {
        this.value = value;
    }
}

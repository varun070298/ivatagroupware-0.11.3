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
 * $Log: GetSettingTag.java,v $
 * Revision 1.4  2005/04/27 20:19:17  colinmacleod
 * Fixed handling when value is null.
 *
 * Revision 1.3  2005/04/10 20:10:06  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:59  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:35  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/12/23 21:01:30  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
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
 * Revision 1.1.1.1  2004/01/27 20:57:56  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/17 12:36:13  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.1.1.1  2003/10/13 20:50:10  colin
 * Restructured portal into subprojects
 *
 * Revision 1.1  2003/02/24 19:33:33  colin
 * moved to jsp
 *
 * Revision 1.8  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.7  2002/09/03 11:05:50  colin
 * fixed bug in setSetting - variable still called sSetting in setter
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
 * Revision 1.3  2002/05/11 08:53:53  colin
 * updated and extended javadoc
 *
 * Revision 1.1  2002/02/03 01:00:46  colin
 * first version for shop
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.SystemException;


/**
 * <p>This tag tries first to get the setting for the user specified
 * by the session attribute 'userName'. If that has not been set (a
 * la
 * login page), or  there is no setting for this user, then the
 * default
 * setting is retrieved.</p>
 *
 * <p>Normally there is a settings object in the session called
 * 'SettingsRemote' and this is used internally to access the
 * ivata groupware settings system.</p>
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
 *     <td>The name of the setting to retrieve from the ivata groupware
 * system.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>userName</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>The name of the user for whom to retrieve the
 * ivata groupware
 * system
 * setting.</td>
 *   </tr>
 * </table>
 * </p>
 *
 *
 * @since 2002-02-02
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 */
public class GetSettingTag extends TagSupport {
    /**
     * <p>
     * Identifier for page attribute. If this attribute is not set, then
     * the setting is output to the page.
     * </p>
     */
    private String id = null;
    /**
     * <p>
     * Scope to which the page attribute is set, if <code>id</code> is set.
     * </p>
     */
    private String scope = "page";
    /**
     * <p>Property declaration for tag attribute: setting.</p>
     */
    private String setting = null;
    /**
     * <p>Class of the setting to be retrieved - this must match the setting
     * type.</p>
     */
    private String type = null;

    /**
     * <p>This method is called when the JSP engine encounters the start
     * tag, after the attributes are processed.<p>
     *
     * <p>Scripting variables (if any) have their values set here.</p>
     *
     * <p><strong>Technical implementation:</strong><br/> We locate a {@link
     * com.ivata.groupware.admin.setting.SettingsRemote SettingsRemote} object
     * in
     * the session (attribute) 'SettingsRemote'. We then use this to get
     * the setting specified. If it is not null, we send this to the
     * <code>JspWriter</code> from the <code>pageContext</code>.</p>
     *
     * @return <code>SKIP_BODY</code> since this tag has no body
     * @exception JspException if there is a <code>NamingExcpetion</code>
     * getting the <code>InitialContext</code>
     * @exception JspException if the session applicationServer is not
     * set
     * @throws JspException if there is a problem creating the
     * SettingsRemote  EJB
     * @throws JspException if there is a
     * <code>java.rmi.RemoteException</code retrieving the setting
     * @throws JspException if there is an error wrting to
     * <code>out.print()</code>
     */
    public int doStartTag() throws JspException {
        // before we do anything else, get the session
        HttpSession session = pageContext.getSession();
        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");
        PicoContainer container;
        UserDO user;
        // if there is no security session (= timeout), then we'll default to
        // the global system setting
        if (securitySession == null) {
            try {
                container = PicoContainerFactory.getInstance().getGlobalContainer();
            } catch (SystemException e) {
                throw new JspException(e);
            }
            user = null;
        } else {
            container = securitySession.getContainer();
            user = securitySession.getUser();
        }
        Settings settings = (Settings) container.getComponentInstance(
                Settings.class);

        Object value;
        try {
            value = settings.getSetting(securitySession, setting,
                    user);
        } catch(SystemException eRemote) {
            throw new JspException(eRemote);
        }

        // check the type matches, if specified
        if (type != null) {
            Class typeClass;
            try {
                typeClass = Class.forName(type);
            } catch (ClassNotFoundException e) {
                throw new JspException(e);
            }
            if ((value != null) 
                    && (!typeClass.isAssignableFrom(value.getClass()))) {
                throw new JspException("ERROR: setting '"
                    + setting
                    + "' is type '"
                    + value.getClass().getName()
                    + "', expected '"
                    + type
                    + "'");
            }
        }


        // if we got a value, try sending this to the page
        if(value != null) {
            if (id == null) {
                try {
                    JspWriter out = pageContext.getOut();
                    out.print(value);
                } catch(IOException eIo) {
                    throw new JspException(eIo);
                }
            } else {
                // if there _is_ an id, set the value to the page
                HttpServletRequest request = (HttpServletRequest)
                    pageContext.getRequest();
                int pageScope = TagUtils.getInstance().getScope(scope);
                pageContext.setAttribute(id, value, pageScope);
            }
        }
        // indicates that the body should not be evaluated - this tag has no body
        return SKIP_BODY;
    }
    /**
     * <p>
     * Identifier for page attribute. If this attribute is not set, then
     * the setting is output to the page.
     * </p>
     *
     * @return current identifier for page attribute.
     */
    public final String getId() {
        return id;
    }

    /**
     * <p>
     * Scope to which the page attribute is set, if <code>id</code> is set.
     * </p>
     *
     * @return scope to which the page attribute is set.
     */
    public final String getScope() {
        return scope;
    }

    /**
     * <p>Get the value supplied to the attribute 'setting'.</p>
     *
     * <p>This attribute represents the name of the setting to retrieve
     * from the ivata groupware system.</p>
     *
     * @return the value supplied to the attribute 'setting'
     */
    public final String getSetting() {
        return setting;
    }
    /**
     * <p>Class of the setting to be retrieved - this must match the setting
     * type.</p>
     *
     * @return class of the setting to be retrieved.
     */
    public final String getType() {
        return type;
    }

    /**
     * <p>
     * Identifier for page attribute. If this attribute is not set, then
     * the setting is output to the page.
     * </p>
     *
     * @param string new identifier for page attribute.
     */
    public final void setId(final String string) {
        id = string;
    }

    /**
     * <p>
     * Scope to which the page attribute is set, if <code>id</code> is set.
     * </p>
     *
     * @param string scope to which the page attribute is set.
     */
    public final void setScope(final String string) {
        scope = string;
    }

    /**
     * <p>Set the value supplied to the attribute 'setting'.</p>
     *
     * <p>This attribute represents the name of the setting to retrieve
     * from the ivata groupware system.</p>
     *
     * @param sSetting the value supplied to the attribute 'setting'
     */
    public final void setSetting(final String setting) {
        this.setting = setting;
    }

    /**
     * <p>Class of the setting to be retrieved - this must match the setting
     * type.</p>
     *
     * @param string class of the setting to be retrieved.
     */
    public final void setType(final String string) {
        type = string;
    }

}

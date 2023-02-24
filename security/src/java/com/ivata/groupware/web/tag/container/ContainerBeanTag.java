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
 * $Log: ContainerBeanTag.java,v $
 * Revision 1.4  2005/04/30 13:06:28  colinmacleod
 * Added default values for primitive wrappers.
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
 * Revision 1.2  2004/11/03 16:08:49  colinmacleod
 * Fixed handling of 'automatic' string beans.
 *
 * Revision 1.1  2004/09/30 15:16:02  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.7  2004/07/19 19:18:23  colinmacleod
 * Made container override itself to create fresh instances.
 *
 * Revision 1.6  2004/07/18 22:03:47  colinmacleod
 * Major bugfixes and extra scope handling.
 *
 * Revision 1.5  2004/07/17 16:09:13  colinmacleod
 * Added checking to see if the security session is null.
 *
 * Revision 1.4  2004/07/14 22:38:37  colinmacleod
 * Fixed bug in beanName.
 *
 * Revision 1.3  2004/07/14 22:30:13  colinmacleod
 * Defaulted type to String.
 *
 * Revision 1.2  2004/07/14 22:13:44  colinmacleod
 * Expanded to make a suitable replacement for bean:define.
 *
 * Revision 1.1  2004/07/13 19:41:15  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.tag.container;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts.taglib.TagUtils;
import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;


/**
 * <p>This tag retrieves an instance of the provided class from the current
 * session contianer.</p>
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
 *     <td>id</td>
 *     <td>true</td>
 *     <td><code>String</code></td>
 *     <td>Id of the bean to be created.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>type</td>
 *     <td>true</td>
 *     <td><code>String</code></td>
 *     <td>Class for which to retrieve an instance.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>scope</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>
 *      Scope into which to write the bean. Must be one of:
 *      <ul>
 *        <li>page (default)</li>
 *        <li>request</li>
 *        <li>session</li>
 *        <li>application</li>
 *      </ul>
 *     </td>
 *   </tr>
 * </table>
 * </p>
 *
 * @since 2004-06-12
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 */
public class ContainerBeanTag extends BodyTagSupport {
    /**
     * Logger for this class.
     */
    private static final Logger logger = Logger
            .getLogger(ContainerBeanTag.class);

    private final static String [] SCOPES = new String[] {
            "application",
            "session",
            "request",
            "page"
    };

    /**
     * <p>
     * Contains the contents of the tag body, or <code>null</code> if the body
     * is empty.
     * </p>
     */
    private String body;
    /**
     * <p>
     * Identifier for page attribute.
     * </p>
     */
    private String id = null;

    /**
     * <p>
     * Name of a bean to use from the scope specified. This defaults to the
     * value of <code>id</code>.
     * </p>
     */
    private String name;

    /**
     * <p>
     * Name of a property in the bean called <code>name</code>. This will act
     * like <code>name.getProperty()</code>.
     * </p>
     */
    private String property;
    /**
     * <p>
     * Scope from which the page attribute is read.
     * </p>
     */
    private String scope;

    /**
     * <p>
     * Scope to which the page attribute is set. Defaults to the value for
     * <code>scope</code>.
     * </p>
     */
    private String toScope;

    /**
     * <p>Class of the setting to be retrieved - this must match the setting
     * type. Defaults to &quot;String&quot;</p>
     */
    private String type = null;

    /**
     * <p>
     * The value to set the object to, if set as an attribute. If the value is
     * a string, you can also use the tag body.
     * </p>
     */
    private Object value;

    /**
     * <p>
     * Wrapper for <code>RequestUtils.lookup</p>. This method does not throw
     * an exception if the bean property does not exist - it merely returns
     * <code>null</code>.
     * </p>
     *
     * @param pageContext Page context being processed.
     * @param beanName Name of a bean to locate in the page context.
     * @param property Optional property name within the bean. Set to
     * <code>null</code> to return the bean itself.
     * @param beanScope one of <ul><li>application</li><li>session</li>
     * <li>request</li> or <li>page</li></ul>
     * @return value of the bean or bean property, or <code>null</code> if no
     * such bean exists in any scope.
     * @throws JspException Thrown by <code>RequestUtils.lookup</code>.
     */
    private Object beanLookup(final PageContext pageContext,
            final String beanName,
            final String property,
            final String beanScope)
            throws JspException {
        TagUtils tagUtils = TagUtils.getInstance();
        Object thisValue = tagUtils.lookup(pageContext, beanName, beanScope);
        // if there is a property, only find the value for that if there
        // is a bean in the scope requested (otherwise an exception is
        // thrown)
        if ((thisValue != null)
                && (property != null)) {
            thisValue = tagUtils.lookup(pageContext, beanName, property, beanScope);
        }
        return thisValue;
    }

    /**
     * TODO
     *
     * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
     */
    public int doAfterBody() throws JspException {
        String thisBody;
        if ((bodyContent != null)
                && ((thisBody = bodyContent.getString()) != null)
                && ((thisBody = thisBody.trim()).length() > 0)) {
            body = thisBody;
        }
        return SKIP_BODY;

    }

    public int doEndTag() throws JspException {
        // before we do anything else, get the request and session
        HttpSession session = pageContext.getSession();
        HttpServletRequest request = (HttpServletRequest)
            pageContext.getRequest();

        Object thisValue = null;
        String actualType = this.type;
        if (actualType == null) {
            // if there is no name specified, assume that the tag contents are
            // used
            if ((name == null) || (value != null)) {
                actualType = "java.lang.String";
            } else {
                actualType = "java.lang.Object";
            }
        }

        if (body != null) {
            // check both attribute and body were not supplied!
            if (value != null) {
                throw new JspException("ERROR in ContainerBeanTag(id "
                        + id
                        + "): you cannot specify both tag body ('"
                        + body
                        + "') and value attribute ('"
                        + value
                        + "'");
            }
            thisValue = body;
            actualType = "java.lang.String";
        } else {
            thisValue = value;
        }

        // finally look to see if there is a default in the chosen scope
        if (thisValue == null) {
            // if no name specified, use the id as name
            String beanName = name == null ? id : name;
            String beanScope = scope;

            if (scope == null) {
                Class searchClass;
                try {
                    searchClass = Class.forName(actualType);
                } catch (ClassNotFoundException e) {
                    throw new JspException(e);
                }


                // if there was no appropriate bean at application scope, go
                // thro' the others too
                for (int i = 0; i < SCOPES.length; i++) {
                    thisValue = beanLookup(pageContext, beanName,
                            property, beanScope = SCOPES[i]);
                    // if we found a valid bean, get out here...
                    if ((thisValue != null)
                            && searchClass.isAssignableFrom(
                                    thisValue.getClass())) {
                        break;
                    }
                }
            } else {
                thisValue = beanLookup(pageContext, beanName,
                        property, beanScope);
            }
        }

        // if we have a string, default it to a request parameter, if one is
        // available; request parameters always override the value for igw beans
        // :-)
        if ((thisValue == null)
                && "java.lang.String".equals(actualType)
                && !StringHandling.isNullOrEmpty(request.getParameter(id))) {
            thisValue = request.getParameter(id);
        }

        // if the value is _still_ null, try to create a new bean from the
        // container
        if (thisValue == null) {
            // initialize primitive type wrappers
            if ("java.lang.Boolean".equals(actualType)) {
                thisValue = Boolean.FALSE;
            } else if ("java.lang.Byte".equals(actualType)) {
                thisValue = new Byte((byte)0);
            } else if ("java.lang.Character".equals(actualType)) {
                thisValue = new Character((char)0);
            } else if ("java.lang.double".equals(actualType)) {
                thisValue = new Double(0L);
            } else if ("java.lang.Float".equals(actualType)) {
                thisValue = new Float(0L);
            } else if ("java.lang.Integer".equals(actualType)) {
                thisValue = new Integer(0);
            } else if ("java.lang.Long".equals(actualType)) {
                thisValue = new Long(0L);
            } else if ("java.lang.Short".equals(actualType)) {
                thisValue = new Short((short)0);
            } else {
                // really use the container
                SecuritySession securitySession = (SecuritySession)
                session.getAttribute("securitySession");
                PicoContainer container;

                // if there is no security session yet, use the default
                // container
                // this should only really happen in /login.jsp or /index.jsp
                PicoContainerFactory factory;
                try {
                    factory = PicoContainerFactory.getInstance();
                } catch (SystemException e) {
                    throw new JspException(e);
                }
                if (securitySession == null) {
                    container = factory.getGlobalContainer();
                } else {
                    container = securitySession.getContainer();
                }
                // override - we want fresh instances
                try {
                    container = factory.override(container);
                    thisValue =
                        factory.instantiateOrOverride(container,
                                actualType);
                } catch (SystemException e) {
                    throw new JspException(e);
                }
            }
        }

        if (thisValue == null) {
            throw new JspException("ERROR: could not instantiate id '"
                    + id
                    + "' for class '"
                    + actualType
                    + "'");
        }

        // set the value to the page
        int writeScope;
        TagUtils tagUtils = TagUtils.getInstance();
        if (toScope != null) {
            writeScope = tagUtils.getScope(toScope);
        } else if (scope != null) {
            writeScope = tagUtils.getScope(scope);
        } else {
            writeScope = PageContext.PAGE_SCOPE;
        }
        pageContext.setAttribute(id, thisValue, writeScope);

        return EVAL_PAGE;
    }

    /**
     * <p>This method is called when the JSP engine encounters the start
     * tag, after the attributes are processed.<p>
     *
     * @return <code>EVAL_BODY_BUFFERED</code> since this tag may have a body
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
        return EVAL_BODY_BUFFERED;
    }


    /**
     * <p>
     * Identifier for page attribute.
     * </p>
     *
     * @return current identifier for page attribute.
     */
    public final String getId() {
        return id;
    }

    /**
     * <p>
     * Name of a bean to use from the scope specified. This defaults to the
     * value of <code>id</code>.
     * </p>
     *
     * @return current value of name.
     */
    public final String getName() {
        return name;
    }
    /**
     * <p>
     * Name of a property in the bean called <code>name</code>. This will act
     * like <code>name.getProperty()</code>.
     * </p>
     *
     * @return current value of property.
     */
    public final String getProperty() {
        return property;
    }

    /**
     * <p>
     * Scope to which the page attribute is set.
     * </p>
     *
     * @return scope to which the page attribute is set.
     */
    public final String getScope() {
        return scope;
    }
    /**
     * <p>
     * Scope to which the page attribute is set. Defaults to the value for
     * <code>scope</code>.
     * </p>
     *
     * @return current value of toScope.
     */
    public final String getToScope() {
        return toScope;
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
     * The value to set the object to, if set as an attribute. If the value is
     * a string, you can also use the tag body.
     * </p>
     *
     * @return current value of value.
     */
    public final Object getValue() {
        return value;
    }

    /**
     * <p>
     * Identifier for page attribute.
     * </p>
     *
     * @param string new identifier for page attribute.
     */
    public final void setId(final String string) {
        id = string;
    }
    /**
     * <p>
     * Name of a bean to use from the scope specified. This defaults to the
     * value of <code>id</code>.
     * </p>
     *
     * @param name new value of name.
     */
    public final void setName(final String name) {
        this.name = name;
    }
    /**
     * <p>
     * Name of a property in the bean called <code>name</code>. This will act
     * like <code>name.getProperty()</code>.
     * </p>
     *
     * @param property new value of property.
     */
    public final void setProperty(final String property) {
        this.property = property;
    }

    /**
     * <p>
     * Scope to which the page attribute is set.
     * </p>
     *
     * @param string scope to which the page attribute is set.
     */
    public final void setScope(final String string) {
        scope = string;
    }
    /**
     * <p>
     * Scope to which the page attribute is set. Defaults to the value for
     * <code>scope</code>.
     * </p>
     *
     * @param toScope new value of toScope.
     */
    public final void setToScope(final String toScope) {
        this.toScope = toScope;
    }

    /**
     * <p>Class of the setting to be retrieved.</p>
     *
     * @param string class of the setting to be retrieved.
     */
    public final void setType(final String string) {
        type = string;
    }
    /**
     * <p>
     * The value to set the object to, if set as an attribute. If the value is
     * a string, you can also use the tag body.
     * </p>
     *
     * @param value new value of value.
     */
    public final void setValue(final Object value) {
        this.value = value;
    }
}

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
 * $Log: HeadTag.java,v $
 * Revision 1.7  2005/04/28 18:47:05  colinmacleod
 * Fixed XHMTL, styles and resin compatibility.
 * Added support for URL rewriting.
 *
 * Revision 1.6  2005/04/27 15:14:36  colinmacleod
 * Added media print stylesheets.
 *
 * Revision 1.5  2005/04/22 10:30:02  colinmacleod
 * Updated stylesheet include routine
 * to pickup stylsheets anywhere along
 * the servlet context's path, so you have
 * an override for all library pages and
 * another for all calendar pages, for
 * example.
 *
 * Revision 1.4  2005/04/10 20:10:06  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.3  2005/04/09 17:19:59  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.2  2005/03/16 18:41:45  colinmacleod
 * Added warning when title is null.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:35  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/12/23 21:01:30  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.3  2004/11/12 15:57:20  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/11/03 16:10:11  colinmacleod
 * Changed todo comments to TODO: all caps.
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
 * Revision 1.4  2004/01/20 15:11:46  jano
 * fixing problems with new sslext
 *
 * Revision 1.3  2003/11/18 09:56:25  jano
 * commiting all forgoten staff
 *
 * Revision 1.2  2003/10/17 12:36:13  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.1.1.1  2003/10/13 20:50:10  colin
 * Restructured portal into subprojects
 *
 * Revision 1.8  2003/06/16 09:09:06  peter
 * return afer sendRedirect
 *
 * Revision 1.7  2003/06/05 11:00:49  peter
 * fixed apostrophe error in favorite title
 *
 * Revision 1.6  2003/04/01 11:32:01  jano
 * if we set title use it otherwise use titleKey
 *
 * Revision 1.5  2003/03/27 20:41:12  peter
 * favorites javascript changed, works with https now
 *
 * Revision 1.4  2003/03/06 17:32:36  peter
 * added js exception catching for favorites
 *
 * Revision 1.3  2003/03/04 19:07:15  colin
 * moved charset stuff from head to checkLogin
 *
 * Revision 1.2  2003/03/04 14:21:46  colin
 * added width/height of popups & favorite stuff
 *
 * Revision 1.1  2003/02/24 19:33:33  colin
 * moved to jsp
 *
 * Revision 1.18  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.17  2003/01/31 10:09:29  colin
 * Added no cache pragma stuff
 *
 * Revision 1.16  2003/01/27 07:29:32  colin
 * added titleKey and titleArg attributes
 *
 * Revision 1.15  2003/01/23 17:37:03  colin
 * fix silly bug in response rewriting
 *
 * Revision 1.14  2003/01/23 13:58:06  colin
 * modifications to allow URL rewriting sessions (instead of
 * cookie)
 *
 * Revision 1.11  2002/09/30 15:33:08  colin
 * added keepOnTop handling
 *
 * Revision 1.10  2002/09/30 14:21:24  colin
 * added topLevel attribute
 *
 * Revision 1.9  2002/09/04 08:09:29  colin
 * fixed bug in getSetting call
 *
 * Revision 1.8  2002/08/19 12:24:38  colin
 * tidied documentation; removed Hungarian notation
 *
 * Revision 1.7  2002/06/13 07:44:07  colin
 * first version of rose model: code tidied up/added javadoc
 *
 * Revision 1.6  2002/05/11 18:32:41  colin
 * changed the name of the remote settings instance in the
 * session from "SettingsRemote" to "settings" for easier
 * usage in JSP files
 *
 * Revision 1.5  2002/05/11 18:07:29  colin
 * cosmetic improvements to javadoc
 *
 * Revision 1.4  2002/05/11 08:53:36  colin
 * updated and extended javadoc
 *
 * Revision 1.3  2002/04/30 15:22:16  colin
 * first functional version of the mail subproject in JBuilder
 *
 * Revision 1.2  2002/04/27 17:40:47  colin
 * first compliling version in EJB/JBuilder project
 *
 * Revision 1.1  2002/04/26 13:36:16  colin
 * move to EJB/JBuilder project
 *
 * Revision 1.7  2002/02/03 19:54:25  colin
 * linked settings to the database, rather than hard-coding in
 * com.ivata.groupware.admin.settings
 *
 * Revision 1.6  2002/02/03 17:09:55  colin
 * moved intialization of settings object from HeadTag to
 * LoginTag
 *
 * Revision 1.5  2002/02/03 16:28:35  colin
 * changed User to PersonUser
 *
 * Revision 1.4  2002/02/02 21:23:01  colin
 * major restructuring to make the Settings class more generic
 * all default settings are now taken from the database rather
 * than being
 * hard coded in the settings class
 * settings are stored in a HashMap in settings
 *
 * Revision 1.3  2002/01/20 19:28:25  colin
 * added tab and tree tags
 * implemented address book functionality
 *
 * Revision 1.2  2001/12/12 20:29:29  colin
 * initial version of the core intranet site
 * fixed width attribute of window tag
 *
 * Revision 1.1  2001/10/06 17:20:06  colin
 * first version after compiling in forte
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.tag;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.taglib.TagUtils;
import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.Security;
import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.container.PicoContainerFactory;
import com.ivata.mask.persistence.FinderException;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.RewriteHandling;
import com.ivata.mask.web.format.URLFormat;
import com.ivata.mask.web.servlet.GenerateCSS;
import com.ivata.mask.web.tag.theme.ThemeConstants;


/**
 * <p>Creates standard header HTML for all of our pages. It is
 * important to note that the stylesheet gets set here.</p>
 *
 * <p>This tag is also responsible for ensuring that the user is
 * logged into the system. This is done by checking the session
 * attribute 'userName' was set and, if not, the system is redirected
 * to the login page.</p>
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
 *     <td>bundle</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>Bundle used to localize the <code>titleKey</code>.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>login</td>
 *     <td>false</td>
 *     <td><code>boolean</code></td>
 *     <td>If specified, this indicates that the system is trying to
 * login.
 * In this case, the tag does not verify the user's
 * authenticity.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>title</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>Optional non-localized title information which will be appended
 * to the
 * title information from the system settings.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>titleArgs</td>
 *     <td>false</td>
 *     <td><code>Object []</code></td>
 *     <td>Optional argument to use with <code>titleKey</cocde> (see below).</td>
 *   </tr>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>titleArg1</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>Optional argument to use with <code>titleKey</cocde> (see below).</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>titleArg2</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>Optional argument to use with <code>titleKey</cocde> (see below).</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>titleArg3</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>Optional argument to use with <code>titleKey</cocde> (see below).</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>titleKey</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>Optional localized title information which will be appended
 * to the
 * title information from the system settings.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>javaScript</td>
 *     <td>false</td>
 *     <td><code>String</code></td>
 *     <td>Optional javascript to execute in the head section of the
 * page.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>topLevel</td>
 *     <td>false</td>
 *     <td><code>boolean</code></td>
 *     <td>Dictates whether or not this JSP page should always appear
 * in a top-level frame. This is only used if the attribute
 * <code>topLevel</code> is <code>true</code>.</td>
 *   </tr>
 *   <tr class='TableRowColor'>
 *     <td>topLevel</td>
 *     <td>false</td>
 *     <td><code>boolean</code></td>
 *     <td>Dictates whether or not this JSP page should appear in a
 * top-level browser window. The default is <code>false</code>,
 * meaning
 * <em>JavaScript</em> is inserted to ensure this browser window appears
 * within the main<em>ivata groupware</em> frames.</td>
 *   </tr>
 * </table>
 * </p>
 *
 * @since 2001-09-08
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.7 $
 */
public class HeadTag extends BodyTagSupport {

    /**
     * This is the default style sheet - used for all themes, if present.
     */
    private static final String DEFAULT_CSS_NAME = "default.css";
    /**
     * Logger for this class.
     */
    private static final Logger logger = Logger.getLogger(HeadTag.class);
    /**
     * This is the ivata main style sheet - for the main frame.
     */
    private static final String MAIN_CSS_NAME = "ivataMain.css";
    /**
     * Special stylesheet just for IE.
     */
    private static final String MSIE_CSS_NAME = "internetExplorer.css";
    /**
     * <p>The bundle to use when setting the attribute
     * <code>titleKey</code>.</p>
     */
    private String bundle = null;
    /**
     * Refer to {@link #isFrames}.
     */
    private boolean frames = false;
    /**
     * <p>Property decaration for tag attribute: javaScript.</p>
     */
    private String javaScript = null;
    /**
     * <p>Only used when <code>topLevel</code> is set to
     * <code>true</code>, if this is set to <code>true</code> then
     * javascript is inserted to keep the jsp page on the top level.</p>
     */
    private boolean keepOnTop = true;
    /**
     * <p>Property declaration for tag attribute: login.</p>
     */
    private boolean login = false;
    /**
     * <p>Stores the value of the tag attribute 'title'. </p>
     *
     * @see org.apache.struts.util.RequestUtils#message
     */
    private String title = null;
    /**
     * <p>Arguments used in localized title string. See <em>Struts<em>
     * documentation for details.</p>
     *
     * @see org.apache.struts.util.RequestUtils#message
     */
    private List titleArgs = new Vector();
    /**
     * <p>Use a <em>Struts</em> localize key string to set the current value
     * of <code>title</code>. </p>
     *
     * @see org.apache.struts.util.RequestUtils#message
     */
    private String titleKey = null;
    /**
     * <p>If <code>false</code> (the default), then javascript is inserted
     * to ensure this page is displayed within the main ivata groupware
     * frames.<p>
     *
     * <p>Set this to <code>true</code> for a pop-up or other top-level
     * window frame.</p>
     */
    private boolean topLevel = false;
    /**
     * <p>This method is called after the JSP engine processes the body
     * content of the tag.</p>
     * @exception JspException encapsulates any exception when calling
     * {@link #writeTagBodyContent writeTagBodyContent}.
     * @return <code>SKIP_BODY</code> since we only ever want to evaluate
     * this tag once.
     * @see #writeTagBodyContent( JspWriter out, BodyContent bodyContent
     * )
     *
     */
    public int doAfterBody() throws JspException {
        BodyContent bodyContent = getBodyContent();
        if(bodyContent != null) {
          try {
              JspWriter out = getPreviousOut();
              writeTagBodyContent(out, bodyContent);
          } catch(Exception ex) {
              throw new JspException(ex);
          }
        }

        // for this tag, we only want to evaluate the body one time
        return SKIP_BODY;
    }
    public int doEndTag() throws JspException {
        HttpSession session = pageContext.getSession();
        TagUtils tagUtils = TagUtils.getInstance();

        HttpServletRequest request =
            (HttpServletRequest) pageContext.getRequest();
        SecuritySession securitySession = (SecuritySession)
            session.getAttribute("securitySession");
        boolean print = (request.getParameter("print") != null);
        if (securitySession == null) {
            try {
                PicoContainer container = PicoContainerFactory.getInstance()
                    .getGlobalContainer();
                Security security =
                    (Security)container.getComponentInstance(Security.class);
                securitySession = security.loginGuest();
            } catch (SystemException e) {
                throw new JspException(e);
            }
            session.setAttribute("securitySession", securitySession);
        }
        PicoContainer container = securitySession.getContainer();
        Settings settings =
            (Settings) container.getComponentInstance(Settings.class);
        UserDO user = securitySession.getUser();

        // find the theme - if there is no theme, use the default from the
        // settings
        String themeName = (String) pageContext.findAttribute(
                ThemeConstants.ATTRIBUTE_THEME_NAME);
        if (StringHandling.isNullOrEmpty(themeName)) {
            try {
                // use the site default theme for this user
                themeName = settings.getStringSetting(securitySession,
                        "siteTheme", user);
            } catch (SystemException e) {
                throw new JspException(e);
            }
            session.setAttribute(ThemeConstants.ATTRIBUTE_THEME_NAME,
                    themeName);
        }

        String encoding;
        try {
            Object localeObject = session.getAttribute(Globals.LOCALE_KEY);
            if (localeObject == null) {
                String localeLanguage;
                try {
                    localeLanguage = settings.getStringSetting(securitySession,
                            "i18nLocaleLanguage", null);
                } catch (FinderException e) {
                    if (logger.isDebugEnabled()) {
                        logger.warn("Could not retrieve locale language from "
                                + "setting 'i18nLocaleLanguage'. Defaulting "
                                + "to en.", e);
                    } else {
                        logger.warn("Could not retrieve locale language from "
                                + "setting 'i18nLocaleLanguage'. Defaulting "
                                + "to en.");
                    }
                    localeLanguage = "en";
                }

                session.setAttribute(Globals.LOCALE_KEY,
                    new Locale(localeLanguage));
            }
            // set the response character encoding
            try {
                encoding = settings.getStringSetting(securitySession,
                    "i18nEncoding",
                    user);
            }  catch (FinderException e) {
                if (logger.isDebugEnabled()) {
                    logger.warn("Could not retrieve encoding from setting "
                            + "'i18nEncoding'. Defaulting to utf-8.", e);
                } else {
                    logger.warn("Could not retrieve encoding from setting "
                            + "'i18nEncoding'. Defaulting to utf-8.");
                }
                encoding = "utf-8";
            }
            pageContext.getRequest().setCharacterEncoding(encoding);
            pageContext.getResponse().setContentType("text/HTML; charset="
                    + encoding);

        } catch(UnsupportedEncodingException e) {
            throw new JspException(e);

        } catch(SystemException e) {
            throw new JspException(e);
        }

        // if we want to set up just title in jsp page we will not do titleKey
        // functionality
        String myTitle = title;
        if (myTitle==null) {
            // window title
            try {
                myTitle = "";
                if (titleKey != null) {
                    // Construct the optional arguments array we will be using
                    myTitle = tagUtils.message(pageContext, bundle,
                            Globals.LOCALE_KEY, titleKey, titleArgs.toArray());
                    if (myTitle == null) {
                        myTitle = tagUtils.message(pageContext, null,
                                Globals.LOCALE_KEY, titleKey,
                                titleArgs.toArray());
                    }
                    if (myTitle == null) {
                        StringBuffer warning =
                           new StringBuffer("Could not find a title for key '");
                        warning.append(titleKey);
                        warning.append("'. I checked ");
                        if (bundle != null) {
                            warning.append(" bundle '");
                            warning.append(bundle);
                            warning.append("' and ");
                        }
                        warning.append("the default (null) bundle.");
                        logger.warn(warning.toString());
                        myTitle = "";
                    }
                }

                if (!"".equals(myTitle)) {
                     myTitle += " - ";
                }
                try {
                    myTitle += settings.getStringSetting(securitySession,
                            "siteTitle",
                            user);
                } catch(FinderException e) {
                    if (logger.isDebugEnabled()) {
                        logger.warn("Could locate value for setting "
                                + "'siteTitle'.", e);

                    } else {
                        logger.warn("Could locate value for setting "
                                + "'siteTitle'.");
                    }
                }
            } catch(SystemException e) {
                throw new JspException(e);
            }
        }
        if (print) {
            String printLabel = StringHandling.getNotNull(tagUtils
                    .message(pageContext, null,
                            Globals.LOCALE_KEY, "print.label.title",
                            null),
                    "Printing ");
            myTitle = printLabel + myTitle;
        }


        // if this is not a top level window, make sure there is javascript to
        // return this window to a frame
        StringBuffer javaScriptBuffer = new StringBuffer();
        // append any user-requested javascript
        if(javaScript != null) {
            javaScriptBuffer.append(javaScript);
            javaScriptBuffer.append('\n');
        }
        // if this is a top level window, make sure it stays on the top
        if (topLevel) {
            // see if the width and height are set
            Integer width = StringHandling.integerValue(request.getParameter(
                    "width"));
            Integer height = StringHandling.integerValue(request.getParameter(
                    "height"));
            if ((width != null)
                    && (height != null)){
                javaScriptBuffer.append("window.resizeTo(");
                javaScriptBuffer.append(width);
                javaScriptBuffer.append(",");
                javaScriptBuffer.append(height);
                javaScriptBuffer.append(");\n");
            }

            // only keep on the top level if we were told to
            if(keepOnTop && !print) {
                javaScriptBuffer.append(
                        "if (window != top) {\n  top.location.href "
                        + "= location.href ;\n}\nwindow.focus();\n\n");
            }
        } else if (!print) {
            URLFormat URLFormat = new URLFormat();

            // make sure the index page is on the top
            // escape is JavaScript method to URL encode
            javaScriptBuffer.append(
                    "if (window == top) {\n  top.location.href = \"");
            javaScriptBuffer.append(
                    RewriteHandling.getContextPath(request));
            javaScriptBuffer.append("/index.jsp?uri=\" + escape(\"");
            
            
            String queryString = request.getQueryString();
            StringBuffer uri = new StringBuffer();
            uri.append(request.getServletPath());
            if(queryString != null) {
                uri.append(URLFormat.format("?"));
                uri.append(URLFormat.format(queryString));
            }
            javaScriptBuffer.append(RewriteHandling.rewriteURL(uri.toString(),
                    request.getContextPath()));
            javaScriptBuffer.append("\");\n}\n");
        }
        String generatedJavaScript;
        if (javaScriptBuffer.length() > 0) {
            generatedJavaScript = javaScriptBuffer.toString();
        } else {
            generatedJavaScript = null;
        }
        try {
            JspWriter out = pageContext.getOut();
            out.println("<meta http-equiv='Content-Type' content='text/HTML; "
                    + "charset="
                    + encoding
                    + "'/>");
            out.println("<meta http-equiv='Content-Style-Type' "
                    + "content='text/css' />");

            // make links for style-sheets in the /style directory which
            // match this theme
            // non-top level pages share a common style sheet
            String servletPath = request.getServletPath();
            if (!topLevel && !servletPath.startsWith("/util/iframeContent")) {
                printStyleSheetLink(request,
                        out,
                        GenerateCSS.CSS_PATH
                        + "/"
                        + MAIN_CSS_NAME,
                        null);
            }

            // go through each element of the path and see if there are matching
            // stylesheets. start with root and work up to the request path
            // and file name (minus extension) for the current path. in each
            // case see if there is matching directory in the style directory
            // with stylesheets in
            if (!StringHandling.isNullOrEmpty(servletPath)) {
                String fullPath = servletPath;
                int dotPos = fullPath.indexOf('.');
                // remove the file extension, so person.action and person.jsp
                // mean the same thing
                if (dotPos != 0) {
                    fullPath = fullPath.substring(0, dotPos);
                }
                // start with just /
                int pos = -1;
                do {
                    // go forward one directory
                    pos = fullPath.indexOf('/', pos + 1);
                    // go round once for the main stylesheet, followed by once
                    // for the print stylesheet (if such exists)
                    String[] mediaNames = new String[] {null, "print"};
                    for (int i = 0; i < mediaNames.length; ++i) {
                        String mediaName = mediaNames[i];
                        String path;
                        // if we got to the end, use the full path
                        if (pos == -1) {
                            path = fullPath + "/";
                        } else {
                            path = fullPath.substring(0, pos + 1);
                        }
                        // print stylesheet is in separate subdir
                        if (mediaName != null) {
                            path += mediaName + "/";
                        }
                        String specificCSSName = GenerateCSS.CSS_PATH
                            + path
                            + DEFAULT_CSS_NAME;
                        // see if there is a default override
                        File file = new File(pageContext.getServletContext()
                                .getRealPath(specificCSSName));
                        if (file.exists()) {
                            printStyleSheetLink(request,
                                    out,
                                    specificCSSName,
                                    mediaName);
                        }
                        // see if there is a theme-specific override
                        specificCSSName = GenerateCSS.CSS_PATH
                            + path
                            + themeName
                            + ".css";
                        file = new File(pageContext.getServletContext()
                                .getRealPath(specificCSSName));
                        if (file.exists()) {
                            printStyleSheetLink(request,
                                    out,
                                    specificCSSName,
                                    mediaName);
                        }
                        // see if there is an internet deplorer override too
                        specificCSSName = GenerateCSS.CSS_PATH
                            + path
                            + MSIE_CSS_NAME;
                        file = new File(pageContext.getServletContext()
                                .getRealPath(specificCSSName));
                        if (file.exists()) {
                            out.println("<!--[if IE]>");
                            printStyleSheetLink(request,
                                    out,
                                    specificCSSName,
                                    mediaName);
                            out.println("<![endif]-->");
                        }
                    }
                } while (pos != -1);
            }

            out.println("<title>" + myTitle + "</title>");

            // if JavaScript was specified, use it now.
            if ((generatedJavaScript != null)
                && !generatedJavaScript.trim().equals("")) {
                out.println("<script "
                        + "type='text/javascript'>");
                out.println("  <!--");
                out.println(generatedJavaScript);
                out.println("  // -->");
                out.println("</script>");
            }
            out.println("</head>");
        } catch(IOException e) {
            throw new JspException(e);
        }
        return EVAL_PAGE;
    }
    /**
     * <p>This method is called when the JSP engine encounters the start
     * tag, after the attributes are processed.<p>
     *
     * <p>Scripting variables (if any) have their values set here.</p>
     *
     * @return <code>EVAL_BODY_BUFFERED</code> since we always want to
     * evaluate the tag body once.
     * @throws JspException if there is a <code>NamingExcpetion</code>
     * getting the <code>InitialContext</code>
     * @throws com.ivata.groupware.admin.setting.SettingsInitializationException
     * if there is no {@link com.ivata.groupware.admin.setting.Settings
     * Settings} object in the session and one cannot be created
     * @throws JspException encapsulates any exception when calling
     * <code>out.println</code>
     */
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.println("<head>");
            out.println("<meta http-equiv='Content-Style-Type' "
                    + "content='text/css' />");
            // Force Refresh HTTP1.1
            out.println("<meta http-equiv='cache-control' content='no-cache'/>");
            // Force Refresh HTTP1.0
            out.println("<meta http-equiv='pragma' content='no-cache'/>");
        } catch(IOException e) {
            throw new JspException(e);
        }
        BodyContent bodyContent = getBodyContent();
        return EVAL_BODY_BUFFERED;
    }
    /**
     * <p>Get the bundle to use when setting the attribute
     * <code>titleKey</code>.</p>
     *
     * @return the current value of bundle, or <code>null</code> if there
     * is none.
     */
    public final String getBundle() {
        return bundle;
    }

    /**
     * <p>Set the value supplied to the tag attribute 'javaScript'</p>
     *
     * <p>If specified, this attribute contains javascript to execute in
     * the head section of the page.</p>
     *
     * @return the value for the javaScript tag attribute.
     */
    public final String getJavaScript() {
        return javaScript;
    }
    /**
     * <p>Only used when <code>topLevel</code> is set to
     * <code>true</code>, if this is set to <code>true</code> then
     * javascript is inserted to keep the jsp page on the top level.</p>
     *
     * @return <code>true</code> if
     * javascript will be inserted to keep the jsp page on the top level,
     * otherwise <code>false</code>.
     */
    public final boolean getKeepOnTop() {
        return keepOnTop;
    }

    /**
     * <p>Return the value supplied to the attribute 'login'.</p>
     *
     * <p>If specified, this attribute indicates that the system is trying
     * to login. In this case, the tag does not verify the user's
     * authenticity.</p>
     *
     * @return the value supplied to the tag attribute 'login'
     */
    public final boolean getLogin() {
        return login;
    }

    /**
     * <p>Get the value supplied to the attribute 'titleKey'.</p>
     *
     * <p>This attribute represents the title of this window. This sets a
     * custom
     * property 'title' to be used in
     * {@link
     * com.ivata.groupware.web.theme.Theme#parseSection(String
     * sName,
     * java.util.Properties properties)
     * Theme.parseSection(String sName, java.util.Properties
     * properties)}.
     * The actual meaning of this attribute depends on the implementation
     * of the
     * theme.</p>
     *
     * @return the value supplied to the tag attribute 'title'
     *
     */
    public final String getTitle() {
        return title;
    }

    /**
     * <p>Arguments used in localized title string. See <em>Struts<em>
     * documentation for details.</p>
     *
     * @see org.apache.struts.util.RequestUtils#message
     * @return the current value of titleArgs.
     */
    public List getTitleArgs() {
        return titleArgs;
    }

    /**
     * <p>Use a <em>Struts</em> localize key string to set the current value
     * of <code>title</code>. </p>
     *
     * @see org.apache.struts.util.RequestUtils#message
     *
     * @return the current value of titleKey.
     */
    public final String getTitleKey() {
        return titleKey;
    }

    /**
     * <p>Get whether or not this JSP page should appear in a top-level
     * browser window.</p>
     *
     * <p>If <code>false</code> (the default), then javascript is inserted
     * to ensure this page is displayed within the main ivata groupware
     * frames.<p>
     *
     * <p>This is set to <code>true</code> for a pop-up or other top-level
     * window frame.</p>
     *
     * @return <code>true</code> if this browser frame should appear in a
     * top-level window, otherwise <code>false</code>.
     */
    public final boolean getTopLevel() {
        return topLevel;
    }
    /**
     * Get whether or not this page contains frames.
     * @return Returns <code>true</code> if this page contains frames.
     */
    public boolean isFrames() {
        return frames;
    }
    /**
     * Prints out a single link tag for a stylesheet.
     *
     * @param request servlet request we are processing.
     * @param out does the writing.
     * @param filePath web-app relative path of the style-sheet, must start with
     * '/'
     * @param mediaName is this a media-specific link (e.g. 'print'). If so,
     * this is the name of the media attribute generated, otherwise
     * <code>null</code>.
     */
    private void printStyleSheetLink(final HttpServletRequest request,
            final JspWriter out,
            final String filePath,
            final String mediaName)
            throws IOException {
        out.print("<link rel='stylesheet' href='");
        out.print(RewriteHandling.rewriteURL(RewriteHandling.getContextPath(request) + filePath,
                RewriteHandling.getContextPath(request)));
        if (mediaName != null) {
            out.print("' media='");
            out.print(mediaName);
        }
        out.println("' type='text/css' />");

    }

    /**
     * <p>Set the bundle to use when setting the attribute
     * <code>titleKey</code>.</p>
     *
     * @param bundle the new value of bundle to use, or <code>null</code>
     * if there is none.
     */
    public final void setBundle(final String bundle) {
        this.bundle = bundle;
    }
    /**
     * Refer to {@link #isFrames}.
     * @param framesParam Refer to {@link #isFrames}.
     */
    public void setFrames(boolean framesParam) {
        if (logger.isDebugEnabled()) {
            logger.debug("Setting frames. Before '" + frames + "', after '"
                    + framesParam + "'");
        }
        frames = framesParam;
    }

    /**
     * <p>Set the value supplied to the tag attribute 'javaScript'</p>
     *
     * <p>If specified, this attribute contains javascript to execute in
     * the head section of the page.</p>
     *
     * @param javaScript the new value for the javaScript tag attribute.
     */
    public final void setJavaScript(final String javaScript) {
        this.javaScript = javaScript;
    }

    /**
     * <p>Only used when <code>topLevel</code> is set to
     * <code>true</code>, this attribute decides whether or not the
     * <em>JSP</em> page should only appear in a top-level frame.</p>
     *
     * @param keepOnTop if <code>true</code> then
     * javascript is inserted to keep the jsp page on the top level.
     */
    public final void setKeepOnTop(final boolean keepOnTop) {
        this.keepOnTop = keepOnTop;
    }

    /**
     * <p>Set the value supplied to the tag attribute 'login'</p>
     *
     * <p>If specified, this attribute indicates that the system is trying
     * to login. In this case, the tag does not verify the user's
     * authenticity.</p>
     *
     * @param isLogin new value for the login tag attribute.
     */
    public final void setLogin(final boolean login) {
        this.login = login;
    }

    /**
     * <p>Set the value supplied to the attribute 'title'.</p>
     *
     * <p>This attribute represents the title of this window. This sets a
     * custom property 'title' to be used in
     * {@link
     * com.ivata.groupware.web.theme.Theme#parseSection(String
     * sName, java.util.Properties properties)
     * Theme.parseSection(String sName, java.util.Properties
     * properties)}.
     * The actual meaning of this attribute depends on the implementation
     * of the
     * theme.</p>
     *
     * @param title the new value of the tag attribute 'title'
     *
     */
    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * <p>Argument used in localized title string. See <em>Struts<em>
     * documentation for details.</p>
     *
     * @see org.apache.struts.util.RequestUtils#message
     * @param titleArgs the new value of titleArgs.
     */
    public final void setTitleArgs(final List titleArgsParam) {
        this.titleArgs = titleArgsParam;
    }

    /**
     * <p>Use a <em>Struts</em> localize key string to set the current value
     * of <code>title</code>. </p>
     *
     * @see org.apache.struts.util.RequestUtils#message
     *
     * @param titleKey the new value of titleKey.
     */
    public final void setTitleKey(final String titleKey) {
        this.titleKey = titleKey;
    }

    /**
     * <p>Set whether or not this JSP page should appear in a top-level
     * browser window.</p>
     *
     * <p>If <code>false</code> (the default), then javascript is inserted
     * to ensure this page is displayed within the main ivata groupware
     * frames.<p>
     *
     * <p>Set this to <code>true</code> for a pop-up or other top-level
     * window frame.</p>
     *
     * @param topLevel set to <code>true</code> if this browser frame
     * should appear
     * in a top-level window, otherwise <code>false</code>.
     */
    public final void setTopLevel(final boolean topLevel) {
        this.topLevel = topLevel;
    }

    /**
     * <p>This method contains all the functionality to process the body
     * of the tag. Not needed for empty tags!</p>
     *
     * @exception IOException thrown by JspWriter.println( String )
     * @param out used to write the body output to
     * @param bodyContent returned by getBodyContent(  )
     */
    public void writeTagBodyContent(final JspWriter out,
            final BodyContent bodyContent) throws IOException {
        HttpSession session = pageContext.getSession();
        out.println(bodyContent.getString());
        // clear the body content for the next time through.
        bodyContent.clearBody();
    }
}

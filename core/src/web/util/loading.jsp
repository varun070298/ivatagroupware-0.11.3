<%@page contentType="text/html;charset=UTF-8"%>
<%@page import='org.apache.struts.Globals' %>
<%@page import='com.ivata.mask.web.browser.BrowserConstants' %>

<%--
////////////////////////////////////////////////////////////////////////////////
// $Id: loading.jsp,v 1.4 2005/04/28 18:47:07 colinmacleod Exp $
//
// This is a really simple script to be displayed when you are loading something
// which might take a while. Simply set the request parameter 'page' to the
// URL we should forward to and this page will do the rest!
//
// If you don't supply a page parameter, this page will go nowhere :-)
//
// Since: ivata groupware 0.9 (2002-09-16)
// Author: Colin MacLeod <colin.macleod@ivata.com>
// $Revision: 1.4 $
////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2001 - 2005 ivata limited.
// All rights reserved.
// =========================================================
// ivata groupware may be redistributed under the GNU General Public
// License as published by the Free Software Foundation;
// version 2 of the License.
//
// These programs are free software; you can redistribute them and/or
// modify them under the terms of the GNU General Public License
// as published by the Free Software Foundation; version 2 of the License.
//
// These programs are distributed in the hope that they will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//
// See the GNU General Public License in the file LICENSE.txt for more
// details.
//
// If you would like a copy of the GNU General Public License write to
//
// Free Software Foundation, Inc.
// 59 Temple Place - Suite 330
// Boston, MA 02111-1307, USA.
//
//
// To arrange commercial support and licensing, contact ivata at
//                  http://www.ivata.com/contact.jsp
//
////////////////////////////////////////////////////////////////////////////////
//
// $Log: loading.jsp,v $
// Revision 1.4  2005/04/28 18:47:07  colinmacleod
// Fixed XHMTL, styles and resin compatibility.
// Added support for URL rewriting.
//
// Revision 1.3  2005/04/10 18:47:38  colinmacleod
// Changed i tag to em and b tag to strong.
//
// Revision 1.2  2005/04/09 17:19:40  colinmacleod
// Changed copyright text to GPL v2 explicitly.
//
// Revision 1.1  2005/03/10 19:24:02  colinmacleod
// Moved to ivata groupware.
//
// Revision 1.6  2004/12/27 14:52:01  colinmacleod
// Removed unused references to RequestUtils.
// Updated other references to Struts 1.2.4 class TagUtils.
//
// Revision 1.5  2004/11/12 15:57:12  colinmacleod
// Removed dependencies on SSLEXT.
// Moved Persistence classes to ivata masks.
//
// Revision 1.4  2004/07/14 22:50:23  colinmacleod
// Replaced bean:define with extended igw:bean.
//
// Revision 1.3  2004/07/14 20:59:53  colinmacleod
// Changed all occurrences of jsp:useBean to igw:bean (better tomcat compatibility).
//
// Revision 1.2  2004/03/21 21:16:24  colinmacleod
// Shortened name to ivata op.
//
// Revision 1.1  2004/03/03 21:32:35  colinmacleod
// Moved core files to core subproject
//
// Revision 1.1.1.1  2004/01/27 20:58:19  colinmacleod
// Moved ivata op to sourceforge.
//
// Revision 1.2  2003/10/17 12:36:13  jano
// fixing problems with building
// converting intranet -> portal
// Eclipse building
//
// Revision 1.1.1.1  2003/10/13 20:50:50  colin
// Restructured portal into subprojects
//
// Revision 1.3  2003/02/25 07:31:16  colin
// restructured java file paths
//
// Revision 1.2  2003/02/11 07:18:33  colin
// browser compatibility
//
// Revision 1.1  2003/01/27 07:09:06  colin
// moved from root dir
//
// Revision 1.4  2003/01/14 10:31:29  colin
// internationalization - setting charset and changing <html...> to <imhtml:html locale='true'>
//
// Revision 1.3  2002/11/28 16:56:26  peter
// i18n finished
//
// Revision 1.2  2002/11/12 10:27:54  colin
// moved tag libraries to /WEB-INF/tag
//
// Revision 1.1  2002/09/26 08:18:30  colin
// first version, used in email/address book integration
//
////////////////////////////////////////////////////////////////////////////////
--%>
<%@include file='/include/tags.jspf'%>

<igw:bean id='browser' scope='session' type='com.ivata.mask.web.browser.Browser'/>
<%String loadingPage = request.getParameter("page");%>
<%-- check we have a request parameter --%>
<igw:bean id='URL'><c:choose>
  <c:when test='<%=loadingPage != null%>'><imhtml:rewrite page='<%=loadingPage%>'/></c:when>
  <c:otherwise>nothing</c:otherwise>
</c:choose></igw:bean>

<imhtml:html locale='true'>
  <igw:head topLevel='true' keepOnTop='false' titleKey='loading.title'>
    <imhtml:base/>
    <%--
      I couldn't get this URL reloading to work nicely with IE - it uses the javascript
    --%>
    <c:if test='<%=(loadingPage != null)
            && (!BrowserConstants.TYPE_INTERNET_EXPLORER.equals(browser.getType()))%>'>
      <meta http-equiv='Refresh' content='<%="0;" + URL%>'/>
    </c:if>
  </igw:head>

  <body class='normal'>
    <br/><br/><br/><br/>
    <div align='center'>
      <%-- most browsers that don't support blink ignore it, so it is safe --%>
      <%String animate = "blink";%>
      <c:if test='<%=browser.canDisplayMarquee()%>'>
          <%animate = "marquee";%>
      </c:if>
      <<%=animate%>><strong><font size='+2' color='black'><bean:message key='loading.label.loading' /></font></strong></<%=animate%>>
    </div>
  </body>
  <%-- other browsers use meta tag -- see above --%>
  <c:if test='<%=(loadingPage != null)
          && (BrowserConstants.TYPE_INTERNET_EXPLORER.equals(browser.getType()))%>'>
    <script>window.location.href="<%=URL%>";</script>
  </c:if>
</imhtml:html>

<%@page contentType="text/html;charset=UTF-8"%>
<%@page import='org.apache.struts.Globals' %>

<%--
////////////////////////////////////////////////////////////////////////////////
// $Id: iframe.jsp,v 1.4 2005/04/28 18:47:07 colinmacleod Exp $
//
// Decides whether or not the current browser can display iframes. If so, the
// text in the request parameter iframeText is printed out inside
// an iframe, otherwise it is just sent straight out to the
// JspWriter.
//
// You should include this page <em>dynamically</em>, using <jsp:include ../>
// rather than <%@include ...%>.
//
// Since: ivata groupware 0.9 (2003-01-25)
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
// $Log: iframe.jsp,v $
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
// Revision 1.3  2004/07/14 20:59:52  colinmacleod
// Changed all occurrences of jsp:useBean to igw:bean (better tomcat compatibility).
//
// Revision 1.2  2004/03/21 21:16:24  colinmacleod
// Shortened name to ivata op.
//
// Revision 1.1  2004/03/03 21:32:35  colinmacleod
// Moved core files to core subproject
//
// Revision 1.1.1.1  2004/01/27 20:58:18  colinmacleod
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
// Revision 1.2  2003/02/25 07:31:16  colin
// restructured java file paths
//
// Revision 1.1  2003/01/27 07:10:24  colin
// first version of iframe handling routines
//
////////////////////////////////////////////////////////////////////////////////
--%>
<%@include file='/include/tags.jspf'%>

<igw:checkLogin/>

<igw:bean id='browser' type='com.ivata.mask.web.browser.Browser' scope='session'/>
<%String iframeText = request.getParameter("iframeText");%>
<c:choose>
  <c:when test='<%=browser.canDisplayIFrames()%>'>
    <imutil:map id='params'>
      <imutil:mapEntry name='iframeText' value='<%=iframeText%>'/>
    </imutil:map>
    <igw:bean id='iframeSrc'><imhtml:rewrite page='/util/iframeContent.jsp' name='params'/></igw:bean>
    <iframe src='<%=iframeSrc%>' style='border: 0px;'>
    <%=iframeText%>
    </iframe>
  </c:when>
  <c:otherwise>
    <%=iframeText%>
  </c:otherwise>
</c:choose>

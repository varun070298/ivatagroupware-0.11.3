<%@page contentType="text/html;charset=UTF-8"%>

<%@page import='com.ivata.mask.util.StringHandling' %>

<%--
////////////////////////////////////////////////////////////////////////////////
// $Id: downloadDocument.jsp,v 1.2 2005/04/09 17:19:48 colinmacleod Exp $
//
// Display a single document item from the library.
//
// Since: ivata groupware 0.9 (2003-6-27)
// Author: Jan Boros
// $Revision: 1.2 $
//
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
// $Log: downloadDocument.jsp,v $
// Revision 1.2  2005/04/09 17:19:48  colinmacleod
// Changed copyright text to GPL v2 explicitly.
//
// Revision 1.1.1.1  2005/03/10 17:52:07  colinmacleod
// Restructured ivata op around Hibernate/PicoContainer.
// Renamed ivata groupware.
//
// Revision 1.4  2004/07/14 22:50:25  colinmacleod
// Replaced bean:define with extended igw:bean.
//
// Revision 1.3  2004/07/14 20:59:53  colinmacleod
// Changed all occurrences of jsp:useBean to igw:bean (better tomcat compatibility).
//
// Revision 1.2  2004/03/21 21:16:30  colinmacleod
// Shortened name to ivata op.
//
// Revision 1.1  2004/03/03 21:50:02  colinmacleod
// Updated webapp structure. Moved core files to core subproject.
//
// Revision 1.1.1.1  2004/01/27 20:58:45  colinmacleod
// Moved ivata op to sourceforge.
//
// Revision 1.3  2003/10/28 13:16:15  jano
// commiting library,
// still fixing compile and building openPortal project
//
// Revision 1.2  2003/10/15 14:16:54  colin
// fixing for XDoclet
//
// Revision 1.2  2003/07/03 06:26:38  peter
// embeddedImagesFormatter
//
// Revision 1.1  2003/06/30 13:10:44  jano
// caming to repository
//
//
////////////////////////////////////////////////////////////////////////////////
--%>
<%@include file='/include/tags.jspf'%>
<%@include file='/library/theme/document.jspf'%>
<%@include file='/include/theme.jspf'%>

<igw:bean id='item' scope='session' type='com.ivata.groupware.business.library.item.LibraryItemDO'/>

<igw:bean id='embeddedImagesFormatter' scope='request' type='com.ivata.mask.web.format.HTMLFormatter'/>

<%
  int size = item.getPages().size();
  int pageIndex = 0;
%>
<imtheme:frame themeName='documentTheme'>
  <imtheme:framePane styleClass='normal'>
    <c:forEach var='pageToDownload' items='<%=item.getPages()%>'>
      <igw:bean id='pageToDownload' type='java.lang.String'/>
      <%= embeddedImagesFormatter.format(pageToDownload) %>
      <c:if test='<%= pageIndex + 1 < size %>'>
        <hr />
      </c:if>
      <% pageIndex++; %>
    </c:forEach>
  </imtheme:framePane>
</imtheme:frame>


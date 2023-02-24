<%@page contentType="text/html;charset=UTF-8"%>

<%@page import="com.ivata.groupware.business.addressbook.person.group.GroupConstants" %>
<%@page import="com.ivata.groupware.business.addressbook.person.group.right.RightConstants" %>
<%--
////////////////////////////////////////////////////////////////////////////////
// $Id: groupList.jsp,v 1.5 2005/04/30 13:04:12 colinmacleod Exp $
//
// This page draws a list tree of all addressBooks or userGroups which user can
// see.
//
// Since: ivata groupware 0.9 (2003-1-8)
// Author: Jan Boros
// $Revision: 1.5 $
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
// $Log: groupList.jsp,v $
// Revision 1.5  2005/04/30 13:04:12  colinmacleod
// Fixes reverting id type from String to Integer.
//
// Revision 1.4  2005/04/28 18:47:09  colinmacleod
// Fixed XHMTL, styles and resin compatibility.
// Added support for URL rewriting.
//
// Revision 1.3  2005/04/10 20:10:09  colinmacleod
// Added new themes.
// Changed id type to String.
// Changed i tag to em and b tag to strong.
// Improved PicoContainerFactory with NanoContainer scripts.
//
// Revision 1.2  2005/04/09 17:19:12  colinmacleod
// Changed copyright text to GPL v2 explicitly.
//
// Revision 1.1.1.1  2005/03/10 17:50:45  colinmacleod
// Restructured ivata op around Hibernate/PicoContainer.
// Renamed ivata groupware.
//
// Revision 1.2  2004/12/31 18:33:04  colinmacleod
// Changes to apply ivata masks instead of hard-coded mask.
// Currently displays value objects for addressbooks.
//
// Revision 1.1  2004/11/12 15:42:32  colinmacleod
// Renamed listTree.jsp to groupList.jsp.
//
// Revision 1.4  2004/07/14 22:50:21  colinmacleod
// Replaced bean:define with extended igw:bean.
//
// Revision 1.3  2004/07/14 20:59:44  colinmacleod
// Changed all occurrences of jsp:useBean to igw:bean (better tomcat compatibility).
//
// Revision 1.2  2004/03/21 21:16:20  colinmacleod
// Shortened name to ivata op.
//
// Revision 1.1  2004/03/03 20:27:38  colinmacleod
// Updated webapp structure. Moved core items to core subproject.
//
// Revision 1.1.1.1  2004/01/27 20:58:01  colinmacleod
// Moved ivata op to sourceforge.
//
// Revision 1.2  2003/11/13 16:03:16  jano
// commitng everything to CVS
// can deploy and application is ruuning, can login into
//
// Revision 1.1  2003/11/07 14:54:15  jano
// commitng after fixing some bugs
//
// Revision 1.4  2003/08/14 15:55:25  jano
// fixing bugs
//
// Revision 1.3  2003/08/13 13:30:40  jano
// addressBook exttension: next level
//
// Revision 1.2  2003/08/05 14:54:26  jano
// addressBook extension
//
// Revision 1.1  2003/08/04 06:32:34  jano
// caming to repository
//
//
////////////////////////////////////////////////////////////////////////////////
--%>

<%@include file='/include/tags.jspf'%>
<igw:checkLogin />


<igw:bean id='addressBookGroupListForm'
    name='addressBookGroupListForm'
    scope='request'
    type='org.apache.struts.action.DynaActionForm'/>


<%@include file='/include/theme.jspf'%>
<%@include file='/addressBook/include/addressBook.jspf'%>

<imutil:map id='loadingParams'/>

<igw:bean id='groupForm' name='addressBookGroupListForm'/>
<%@ include file="/addressBook/include/groupBeans.jspf"%>

<imhtml:html locale='true'>
  <igw:head topLevel='true' keepOnTop='false' >
    <imhtml:base/>
    <script language="JavaScript" type="text/javascript">
      <!--
        function refreshContent() {
          if (document.getElementById("listTree").selectedIndex == -1) {
            window.top.<%=contentFrameName%>.location.href = "<%=newURL%>";
          } else {
            var URL = "<%=findURL%>"
                + document.getElementById("listTree").value;
            window.top.<%=contentFrameName%>.location.href = URL;
          }
        }
      // -->
    </script>

  </igw:head>
  <body class='dialog'>
    <imtheme:window>
      <imtheme:frame>
        <imtheme:framePane>
          <form style='width: 100%; height: 100%;'>
            <select onChange='refreshContent()'
                name='listTree'
                id='listTree'
                style='width: 100%; height: 100%;' multiple='multiple'>
              <logic:iterate id="element" name="groupMap">
                <igw:bean id='key' name="element" property="key" type='java.lang.String'/>
                <option value='<%=key%>' <%=selectedId.equals(key) ? "selected='selected'" : ""%>>
                  <bean:write name="element" property="value"/>
                </option>
              </logic:iterate>
            </select>
          </form>
        </imtheme:framePane>
      </imtheme:frame>
    </imtheme:window>
    <%--
      only refresh the right panel if the right panel has not just refreshed us!
      (don't want to go 'round in circles)
    --%>
    <logic:notPresent parameter="inputMaskInvoked">
      <script language="JavaScript" type="text/javascript">
        <!--
          refreshContent();
        // -->
      </script>
    </logic:notPresent>
    <%@include file='/include/script/fixPopUp.jspf'%>
  </body>
</imhtml:html>


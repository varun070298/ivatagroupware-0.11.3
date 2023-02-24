<%@page import='com.ivata.mask.util.StringHandling' %>
<%--
////////////////////////////////////////////////////////////////////////////////
// $Id: chooseParent.jsp,v 1.3 2005/04/30 13:04:11 colinmacleod Exp $
//
// These are feilds we couldn't automatically generate (easily) for the groups
// --> java script for address book and parent
//
// Since: ivata groupware 0.10 (2005-01-15)
// Author: Colin MacLeod <colin.macleod@ivata.com>
// $Revision: 1.3 $
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
// $Log: chooseParent.jsp,v $
// Revision 1.3  2005/04/30 13:04:11  colinmacleod
// Fixes reverting id type from String to Integer.
//
// Revision 1.2  2005/04/09 17:19:13  colinmacleod
// Changed copyright text to GPL v2 explicitly.
//
// Revision 1.1.1.1  2005/03/10 17:51:13  colinmacleod
// Restructured ivata op around Hibernate/PicoContainer.
// Renamed ivata groupware.
//
////////////////////////////////////////////////////////////////////////////////
--%>
<%@ include file="/include/tags.jspf"%>
<%@include file='/addressBook/include/tree.jspf'%>
<%@include file='/addressBook/include/addressBook.jspf'%>

<igw:bean id='groupMap' name="addressBookGroupTreeForm" property="groupMap" type='java.util.Map'/>
<igw:bean id='addressBookId' name="addressBookGroupTreeForm" property="selectedId" type='java.lang.Integer'/>
<logic:present parameter='addressBookId'>
  <%addressBookId = StringHandling.integerValue(request.getParameter("addressBookId"));%>
</logic:present>

<igw:bean id='group' name="InputMaskForm" property="valueObject" type='com.ivata.groupware.business.addressbook.person.group.GroupDO'/>

<%--
  if the addressbook is changed, use it to change the value of the address book
  on the left pane
--%>
<igw:bean id="groupTreeURL"><html:rewrite page="/addressBook/groupTree.action?mode=group"/></igw:bean>
<script language="JavaScript" type="text/javascript">
  <!--
  function onChangeAddressBook() {
    document.InputMaskForm.menuFrameFullURL.value = '<%=groupTreeURL%>'
                + '&resourceFieldPath=' + document.InputMaskForm.resourceFieldPath.value
                + '&deleteKey=' + document.InputMaskForm.deleteKey.value
                + '&selectedId=' + document.InputMaskForm.addressBookId.value
                + '&idIsAddressBook=true'
                + '&inputMaskInvoked=true';
    document.InputMaskForm.submit();
  }
// -->
</script>
<script language="JavaScript">
</script>

<%--
  TODO: ok, I'm keeping it simple for ivata groupware 0.10 - these hard coded
  label strings should be replaced with nice label tags :-)
--%>
<tr>
  <td class='fieldCaption'>AddressBook:</td>
  <td class='field'>
    <igw:bean id="newURLJavaScript"></igw:bean>
    <select
        onChange='onChangeAddressBook(); return true'
        name='addressBookId'
        id='addressBook'
        style='width: 100%'>
      <logic:iterate id="element" name="groupMap">
        <igw:bean id='key' name="element" property="key" type='java.lang.String'/>
        <option value='<%=key%>' <%=StringHandling.getNotNull(addressBookId, "none").equals(key) ? "selected='selected'" : ""%>>
          <bean:write name="element" property="value"/>
        </option>
      </logic:iterate>
    </select>
  </td>
</tr>
<tr>
  <td class='fieldCaption'>Parent:</td>
  <td class='field'>
    <logic:present name="group" property="id">
      <jsp:setProperty name='personTreeModel' property='excludeBranch' value='<%=group.getId()%>'/>
    </logic:present>
    <jsp:setProperty name='personTreeModel' property='filter' value='<%=null%>'/>
    <jsp:setProperty name='personTreeModel' property='rootId' value='<%=addressBookId%>'/>
    <igw:bean id="parentId">
      <logic:present name="group" property="parent.id">
        <bean:write name="group" property="parent.id"/>
      </logic:present>
    </igw:bean>
    <igw:bean id="defaultCaption">
      <bean:message
        bundle='addressBook'
        key='group.field.parent.value'/>
    </igw:bean>
    <igwtree:treeSelect model='<%=personTreeModel%>'
                                   treeName='group'
                                   defaultCaption='<%=defaultCaption%>'
                                   defaultValue='<%=StringHandling.getNotNull(addressBookId)%>'
                                   treeStyle='combo'
                                   controlName='valueObject.parent'
                                   selected='<%=parentId%>'/>
  </td>
</tr>


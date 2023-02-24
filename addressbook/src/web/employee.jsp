<%@page contentType='text/html;charset=UTF-8'%>

<%@page import='com.ivata.groupware.business.addressbook.*' %>
<%@page import='com.ivata.groupware.business.addressbook.telecomaddress.*' %>
<%@page import='com.ivata.groupware.business.addressbook.person.*' %>
<%@page import='com.ivata.mask.web.format.HTMLFormatter' %>
<%@page import='com.ivata.mask.util.StringHandling' %>

<%@page import='java.util.Collection' %>
<%--
////////////////////////////////////////////////////////////////////////////////
// $Id: employee.jsp,v 1.3 2005/04/10 20:10:09 colinmacleod Exp $
//
// Lets you enter/modify a employees's details, such as where she is located and
// how many days' vacation she has left.
//
// Since: ivata groupware 0.9 (2002-09-01)
// Author: Colin MacLeod <colin.macleod@ivata.com>
// $Revision: 1.3 $
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
// $Log: employee.jsp,v $
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
// Revision 1.6  2004/11/03 15:41:42  colinmacleod
// Updated to use pico/struts.
//
// Revision 1.5  2004/07/14 22:50:20  colinmacleod
// Replaced bean:define with extended igw:bean.
//
// Revision 1.4  2004/07/14 20:59:44  colinmacleod
// Changed all occurrences of jsp:useBean to igw:bean (better tomcat compatibility).
//
// Revision 1.3  2004/07/13 19:41:17  colinmacleod
// Moved project to POJOs from EJBs.
// Applied PicoContainer to services layer (replacing session EJBs).
// Applied Hibernate to persistence layer (replacing entity EJBs).
//
// Revision 1.2  2004/03/21 21:16:19  colinmacleod
// Shortened name to ivata op.
//
// Revision 1.1  2004/03/03 20:27:38  colinmacleod
// Updated webapp structure. Moved core items to core subproject.
//
// Revision 1.1.1.1  2004/01/27 20:58:00  colinmacleod
// Moved ivata op to sourceforge.
//
// Revision 1.2  2003/10/17 12:36:13  jano
// fixing problems with building
// converting intranet -> portal
// Eclipse building
//
// Revision 1.1.1.1  2003/10/13 20:50:20  colin
// Restructured portal into subprojects
//
// Revision 1.9  2003/04/03 12:38:14  jano
// using sorting JSP for sort Countries
//
// Revision 1.8  2003/03/04 01:27:21  colin
// fixed mistakes in text tags
//
// Revision 1.7  2003/03/03 16:57:13  colin
// converted localization to automatic paths
// added labels
// added mandatory fieldName attribute
//
// Revision 1.6  2003/02/25 07:28:54  colin
// restructured java file paths
//
// Revision 1.5  2003/02/18 10:38:26  colin
// conversion to Struts and pop-ups
//
// Revision 1.4  2003/01/14 10:38:56  colin
// internationalization - setting charset and changing <html...> to <imhtml:html locale='true'>
//
// Revision 1.3  2002/11/20 17:07:31  peter
// internationalization implemented
//
// Revision 1.2  2002/10/18 08:39:32  colin
// cosmetic changes
//
// Revision 1.1  2002/10/15 15:52:38  colin
// First fully functional version.
//
////////////////////////////////////////////////////////////////////////////////
--%>
<%@include file='/include/tags.jspf'%>
<%@include file='/include/theme.jspf'%>

<%-- get the addressBook object --%>
<%@include file='/addressBook/include/addressBook.jspf'%>

<igw:bean id='person' name='addressBookPersonForm' property='person' type='com.ivata.groupware.business.addressbook.person.PersonDO'/>
<igw:bean id='employee' name='person' property='employee' type='com.ivata.groupware.business.addressbook.person.employee.EmployeeDO'/>

<imhtml:form refersTo='personFormTag' resourceFieldPath='employee'>
  <imtheme:framePane styleClass='hilight'>
    <table cellpadding='0' cellspacing='0' border='0' width='100%'>
      <tr>
        <td class='fieldCaption' colspan='2'><br/></td>
      </tr>
      <tr>
        <td class='fieldCaption'></td>
        <td class='field'><imhtml:checkbox fieldName='employee'/></td>
      </tr>
      <tr>
        <td class='fieldCaption' colspan='2'><br/></td>
      </tr>
      <tr>
        <td class='fieldCaption'><imhtml:label fieldName='number'/></td>
        <td class='field'><imhtml:text fieldName='number' maxlength='25' property='person.employee.number'/></td>
      </tr>
      <tr>
        <td class='fieldCaption'><imhtml:label fieldName='vacationDays'/></td>
        <td class='field'><imhtml:text fieldName='vacationDays' property='person.employee.vacationDays' size='5' maxlength='4'/></td>
      </tr>
      <tr>
        <td class='fieldCaption'><imhtml:label fieldName='regionCode'/></td>
        <td class='field'><imhtml:text fieldName='regionCode' maxlength='10' property='person.employee.regionCode'/></td>
      </tr>
      <tr>
        <td class='fieldCaption'><imhtml:label fieldName='countryCode'/></td>
        <td class='field'>
          <imhtml:select fieldName='countryCode' property="person.employee.country.code">
            <imhtml:option value=''><bean:message key='employee.field.countryCode.unspecified' bundle='addressBook'/></imhtml:option>
            <%--Collection countries = addressBook.findAllCountries();--%>

            <%@include file='/addressBook/include/sortCountryKeys.jspf'%>
            <% int i=0; %>
            <c:forEach var='country' items='<%=countryCodes%>'>
              <igw:bean id='country' type='java.lang.String'/>
              <imhtml:option value='<%=country%>'><bean:message bundle='addressBook' key='<%=(String) countryKeys.get(i)%>' /></imhtml:option>
              <% i++; %>
            </c:forEach>
          </imhtml:select>
        </td>
      </tr>
    </table>
  </imtheme:framePane>
</imhtml:form>


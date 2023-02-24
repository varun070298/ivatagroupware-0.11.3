<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="com.ivata.mask.web.format.HTMLFormatter" %>
<%--
////////////////////////////////////////////////////////////////////////////////
// $Id: general.jsp,v 1.3 2005/04/10 20:10:10 colinmacleod Exp $
//
//  General preferences which control the whole system.
//
// Since: ivata groupware 0.9 (2002-06-29)
// Author: Colin MacLeod <colin.macleod@ivata.com>, peter
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
// $Log: general.jsp,v $
// Revision 1.3  2005/04/10 20:10:10  colinmacleod
// Added new themes.
// Changed id type to String.
// Changed i tag to em and b tag to strong.
// Improved PicoContainerFactory with NanoContainer scripts.
//
// Revision 1.2  2005/04/09 17:19:39  colinmacleod
// Changed copyright text to GPL v2 explicitly.
//
// Revision 1.1  2005/03/10 19:23:45  colinmacleod
// Moved to ivata groupware.
//
// Revision 1.5  2004/11/03 15:53:20  colinmacleod
// Changed todo comments to TODO: all caps.
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
// Revision 1.1  2004/03/03 21:32:34  colinmacleod
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
// Revision 1.1.1.1  2003/10/13 20:50:51  colin
// Restructured portal into subprojects
//
// Revision 1.13  2003/06/10 06:03:13  peter
// img and html:img replaced with size-aware imhtml:img
//
// Revision 1.12  2003/04/24 09:03:23  peter
// removed 100 height of inner table, was bad when less settings enabled
//
// Revision 1.11  2003/03/20 15:22:38  jano
// fixing textarea size for IE
//
// Revision 1.10  2003/03/05 15:03:51  peter
// fixed siteName: property settingType replaced by setting
//
// Revision 1.9  2003/03/05 11:24:27  peter
// resourceFieldPath in forms
//
// Revision 1.8  2003/03/03 16:57:13  colin
// converted localization to automatic paths
// added labels
// added mandatory fieldName attribute
//
// Revision 1.7  2003/02/25 07:30:55  colin
// restructured java file paths
//
// Revision 1.6  2003/02/24 15:39:22  peter
// converted to imhtml, added empty images to textareas to keep their minimal height
//
// Revision 1.5  2003/02/14 17:07:14  peter
// started to convert to imhtml
//
// Revision 1.4  2003/02/12 14:24:09  peter
// now works both in user and administrator mode
//
// Revision 1.3  2003/02/06 16:49:31  peter
// tidied up
//
// Revision 1.2  2003/01/30 16:46:39  peter
// minor changes
//
// Revision 1.1  2003/01/29 09:18:23  peter
// added to cvs
//
// Revision 1.1  2003/01/27 13:04:07  colin
// very old version of settings to use as a basis
//
////////////////////////////////////////////////////////////////////////////////
--%>

<%@include file='/include/tags.jspf'%>
<%@include file='/include/theme.jspf'%>

<igw:bean id='formatter' scope='page' type='com.ivata.mask.web.format.HTMLFormatter'/>
<igw:bean id='nonBreakingSpaces' scope='page' type='com.ivata.mask.web.format.NonBreakingSpaceFormat'/>


<igw:bean id='settingForm' scope='session' type='com.ivata.groupware.admin.setting.struts.SettingForm' />

<imformat:addFormat formatter='<%=formatter%>' format='<%=nonBreakingSpaces%>'/>

<imhtml:form refersTo='ivataSettingForm' resourceFieldPath='setting'>

  <imtheme:frame styleClass='tabContent'>
    <imtheme:framePane style='padding: 5px 5px 5px 5px; valign=top;'>
      <table border='0' cellpadding='0' cellspacing='0' width='100%'>
        <tr>
          <td colspan='2' />
          <logic:notEmpty name='settingForm' property='administrator'>
            <td class='field'><imhtml:img page='/setting/images/override.gif' /></td>
          </logic:notEmpty>
        </tr>

        <logic:notEmpty name='settingForm' property='settingType(siteDefaultForward)'>
          <tr>
            <td class='fieldCaption'><imhtml:label fieldName='siteDefaultForward'/></td>
            <td class='field'>
              <html:hidden styleId='system_siteDefaultForward' property='settingSystem(siteDefaultForward)' />
              <html:hidden styleId='user_siteDefaultForward' property='settingUser(siteDefaultForward)' />
              <select id='siteDefaultForward' name='setting(siteDefaultForward)' class='mandatory' onChange="onFieldChange('siteDefaultForward')">
                <option value='success'><bean:message bundle='admin' key='general.label.defaultForwards.success' /></option>
                <option value='addressBookIndex'><bean:message bundle='admin' key='general.label.defaultForwards.addressBookIndex' /></option>
                <option value='calendarIndex'><bean:message bundle='admin' key='general.label.defaultForwards.calendarIndex' /></option>
                <option value='mailIndex'><bean:message bundle='admin' key='general.label.defaultForwards.mailIndex' /></option>
              </select>
            <logic:notEmpty name='settingForm' property='administrator'>
              <td class='field'><imhtml:checkbox fieldName='override' bundle='admin' property='settingOverride(siteDefaultForward)' /></td>
            </logic:notEmpty>
            <td class='field'>
              <imhtml:img page='/setting/images/revert.gif' bundle='admin'
                    titleKey='index.img.revertToSystem.title'
                    onclick="revertToSystem('siteDefaultForward')" />
            </td>
            <td class='field'><html:img page='/images/empty.gif' height='21'/></td>
          </tr>
        </logic:notEmpty>

        <logic:notEmpty name='settingForm' property='settingType(siteName)'>
          <tr>
            <td class='fieldCaption'><imhtml:label fieldName='siteName'/></td>
            <td class='field'>
              <html:hidden styleId='system_siteName' property='settingSystem(siteName)' />
              <html:hidden styleId='user_siteName' property='settingUser(siteName)' />
              <imhtml:text fieldName='siteName' property='setting(siteName)' mandatory='true' onchange="onFieldChange('siteName')"/>
            </td>
          </tr>
        </logic:notEmpty>

        <logic:notEmpty name='settingForm' property='settingType(siteTitle)'>
          <tr>
            <td class='fieldCaption'><imhtml:label fieldName='siteTitle'/></td>
            <td class='field'>
              <html:hidden styleId='system_siteTitle' property='settingSystem(siteTitle)' />
              <html:hidden styleId='user_siteTitle' property='settingUser(siteTitle)' />
              <imhtml:text fieldName='siteTitle' property='setting(siteTitle)' mandatory='true' onchange="onFieldChange('siteTitle')"/>
            </td>
          </tr>
        </logic:notEmpty>

        <logic:notEmpty name='settingForm' property='settingType(siteWelcomeText)'>
          <tr>
            <td class='fieldCaption'><imhtml:label fieldName='siteWelcomeText'/></td>
          </tr>
          <tr>
            <td colspan='5' class='field' height='100%'>
              <html:hidden styleId='system_siteWelcomeText' property='settingSystem(siteWelcomeText)' />
              <html:hidden styleId='user_siteWelcomeText' property='settingUser(siteWelcomeText)' />
              <imhtml:textarea rows='6' cols='40' fieldName='siteWelcomeText' mandatory='true' property='setting(siteWelcomeText)' onchange="onFieldChange('siteWelcomeText')" />
            </td>
          </tr>
        </logic:notEmpty>

      </table>

    </imtheme:framePane>
  </imtheme:frame>

</imhtml:form>
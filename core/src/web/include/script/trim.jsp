<%--
////////////////////////////////////////////////////////////////////////////////
// $Id: trim.jsp,v 1.2 2005/04/09 17:19:39 colinmacleod Exp $
//
// Contains javascript to trim a string, remove leading and trailing spaces.
//
// Since: ivata groupware 0.9 (2002-09-25)
// Author: Colin MacLeod <colin.macleod@ivata.com>
// $Revision: 1.2 $
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
// $Log: trim.jsp,v $
// Revision 1.2  2005/04/09 17:19:39  colinmacleod
// Changed copyright text to GPL v2 explicitly.
//
// Revision 1.1  2005/03/10 19:23:44  colinmacleod
// Moved to ivata groupware.
//
// Revision 1.2  2004/03/21 21:16:24  colinmacleod
// Shortened name to ivata op.
//
// Revision 1.1  2004/03/03 21:32:34  colinmacleod
// Moved core files to core subproject
//
// Revision 1.1.1.1  2004/01/27 20:58:17  colinmacleod
// Moved ivata op to sourceforge.
//
// Revision 1.1.1.1  2003/10/13 20:50:50  colin
// Restructured portal into subprojects
//
// Revision 1.3  2003/02/21 18:31:31  peter
// tidied up, general methods moved here
//
// Revision 1.2  2003/01/27 07:21:50  colin
// changed copyright notice
//
// Revision 1.1  2002/09/30 14:29:04  colin
// javascript trim function - first version
//
////////////////////////////////////////////////////////////////////////////////
--%>


function trimElementById(elementId) {
    var textInput = document.getElementById(elementId);
    textInput.value = trim(textInput.value);
    return textInput;
}



function trim(untrimmed) {
  var trimmed = untrimmed;
  var ch = trimmed.substring(0, 1);

  <%-- check for spaces at the start --%>
  while (ch == " ") {
    trimmed = trimmed.substring(1, trimmed.length);
    ch = trimmed.substring(0, 1);
  }
  ch = trimmed.substring(trimmed.length-1, trimmed.length);

  <%-- check for spaces at the end --%>
  while (ch == " ") {
    trimmed = trimmed.substring(0, trimmed.length-1);
    ch = trimmed.substring(trimmed.length-1, trimmed.length);
   }
   return trimmed;
}

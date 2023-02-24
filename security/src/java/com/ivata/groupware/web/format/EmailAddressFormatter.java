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
 * $Log: EmailAddressFormatter.java,v $
 * Revision 1.3  2005/04/10 18:47:41  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:59  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:36  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.2  2004/11/03 16:10:11  colinmacleod
 * Changed todo comments to TODO: all caps.
 *
 * Revision 1.1  2004/09/30 15:16:01  colinmacleod
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
 * Revision 1.2  2004/02/01 22:00:33  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:56  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/11/07 14:54:15  jano
 * commitng after fixing some bugs
 *
 * Revision 1.2  2003/10/17 12:36:13  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.1.1.1  2003/10/13 20:50:09  colin
 * Restructured portal into subprojects
 *
 * Revision 1.3  2003/03/07 02:19:53  colin
 * fixed quotes, used onclick rather than javasciprt: href
 *
 * Revision 1.2  2003/03/04 14:20:58  colin
 * changed popup width to 550
 *
 * Revision 1.1  2003/02/24 19:33:32  colin
 * moved to jsp
 *
 * Revision 1.6  2003/02/18 11:12:52  colin
 * converted to using popups
 *
 * Revision 1.5  2003/02/04 17:43:46  colin
 * copyright notice
 *
 * Revision 1.4  2002/09/25 11:36:43  colin
 * changed to make the email address formatter point at the
 * webmail subsystem
 *
 * Revision 1.3  2002/09/09 13:47:11  peter
 * *** empty log message ***
 *
 * Revision 1.2  2002/06/21 16:19:16  colin
 * added constructors to allow importing of formats from another
 * formatter.
 *
 * Revision 1.1  2002/06/21 11:58:37  colin
 * restructured com.ivata.groupware.web into separate
 * subcategories:
 * fornat, javascript, theme and tree.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.web.format;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.servlet.jsp.PageContext;

import com.ivata.mask.web.format.HTMLFormatter;
import com.ivata.mask.web.format.URLFormat;
import com.ivata.mask.web.javascript.JavaScriptWindow;


/**
 * <p>Convert email addresses into links against the ivata groupware
 * email
 * system. This class can either convert single addreses or lists into
 * <code>HTML</code> anchors.</p>
 *
 * @since 2002-06-19
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 */
public class EmailAddressFormatter extends HTMLFormatter {

    /**
     * <p>Stores the string used as a separator between email
     * addresses.</p>
     *
     * @see #format
     *
     */
    private String separator = ";";

    /**
     * <p>Whether or not the email address itself should appear in the
     * link. If <code>false</code> the name of the person appears in the
     * link, otherwise the email address itself will appear.</p>
     */
    private boolean showAddress = false;

    /**
     * <p>Stores the current page context. This is needed to create the
     * <em>JavaScript</em> window.</p>
     */
    private PageContext pageContext;

    /**
     * <p>Format one email address as an anchor link. This expects the
     * address to be
     * formatted according to <a
     * href='http://www.faqs.org/rfcs/rfc822.HTML'>RFC822</a>.</p>
     *
     * @param addressString the emailAddress string to format.
     * @return a formatted email address linking to the ivata groupware
     * email system.
     */
    public String format(final String addressString) {
        // simple prerequisite: rubbish in -> rubbish out...
        if (addressString == null) {
            return null;
        }
        int startPosition, endPosition;
        InternetAddress address;

        // look for email address of the form "My Name" <my.name@provider.com>
        // TODO: change these links to links to the intranet
        try {
            if (((startPosition = addressString.indexOf('<')) != -1) &&
                ((endPosition = addressString.indexOf('>')) != -1)) {
                address = new InternetAddress(addressString.substring(startPosition + 1, endPosition), addressString.substring(0, startPosition));
            } else {
                address = new InternetAddress(addressString);
            }
            return format(address);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (javax.mail.internet.AddressException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>Format an array of addresses as anchor links.</p>
     *
     * @param addresses the array of email adddresses to format.
     * @return a separated list of email address linking to the
     * ivata groupware email
     * system. Each address is separated by the separator specified in
     * {@link
     * #setSeperator setSeperator}.
     *
     */
    public String format(final Address[] addresses) {
        // simple prerequisite: rubbish in -> rubbish out...
        if (addresses == null) {
            return null;
        }
        return format(Arrays.asList(addresses));
    }

    /**
     * <p>Format a list of email addresses as anchor links.</p>
     *
     * @param addresses the list of email addresses to format.
     * @return a separated list of email address linking to the
     * ivata groupware email
     * system. Each address is separated by the separator specified in
     * {@link
     * #setSeperator setSeperator}.
     *
     */
    public String format(final List addresses) {
        // simple prerequisite: rubbish in -> rubbish out...
        if (addresses == null) {
            return null;
        }
        ListIterator addressesIterator = addresses.listIterator();
        String returnString = "";
        URLFormat URLFormat = new URLFormat();

        // go thro' all the emails and format them
        while (addressesIterator.hasNext()) {
            Object address = addressesIterator.next();
            String addressString;

            if ((address instanceof InternetAddress) || (address instanceof String)) {
                if (address instanceof InternetAddress) {
                    InternetAddress addressInternet = (InternetAddress) address;
                    String personalString;

                    addressString = addressInternet.getAddress();
                    if ((addressInternet.getPersonal() != null) &&
                        !addressInternet.getPersonal().equals("")) {

                        /* TODO: this code can be used to strip the quotes - not sure if this is needed
                         StringBuffer personalStringBuffer = new StringBuffer(addressInternet.getPersonal());
                         // strip leading and trailing quotes and spaces
                         char ch;
                         while((personalStringBuffer.length() > 0) &&
                         ((ch = personalStringBuffer.charAt(0)) == '"' || ch==' ')) {
                         personalStringBuffer.deleteCharAt(0);
                         }
                         int length = personalStringBuffer.length();
                         while((--length > 0) &&
                         ((ch = personalStringBuffer.charAt(length)) == '"' || ch==' ')) {
                         personalStringBuffer.deleteCharAt(length);
                         }
                         personalString = personalStringBuffer.toString();*/
                        personalString = addressInternet.getPersonal();
                    } else {
                        // if no name was given, use the address itself
                        personalString = addressInternet.getAddress();
                    }
                    // we can only format the link if the pageContext has been
                    // set
                    if (pageContext == null) {
                        if (personalString.equals(addressString)) {
                            addressString = personalString + " <" + addressString + ">";
                        }
                    } else {
                        // NOTE: please keep this up to date with the code in
                        // /webapp/mail/include/composePopUp.jspf
                        JavaScriptWindow popUp = new JavaScriptWindow();
                        HashMap composeParams = new HashMap();

                        if (personalString.equals(addressString)) {
                            composeParams.put("to", addressString);
                        } else {
                            // remove quotes from the personal string
                            StringBuffer to = new StringBuffer();

                            /**
                             * WE need quotes,
                             *
                            StringTokenizer st = new StringTokenizer(personalString, "\"");

                            while (st.hasMoreElements()) {
                                to.append(st.nextElement());
                            */
                            to.append(" <");
                            to.append(addressString);
                            to.append(">");
                            composeParams.put("to", to.toString());
                        }
                        popUp.setParams(composeParams);
                        popUp.setWindowName("composeWindow");
                        popUp.setFrameName("ivataCompose");
                        popUp.setPage("/mail/compose.jsp");
                        popUp.setHeight(540);
                        popUp.setWidth(550);
                        popUp.setHasScrollBars(false);
                        popUp.setPageContext(pageContext);
                        String tagBody = showAddress ? addressString : personalString;

                        // convert spaces in the name string
                        addressString = "<a href='' onclick='"
                                + popUp.toString()
                                + "return false'>" + super.format(tagBody) + "</a>";
                    }
                } else {
                    addressString = format((String) address);
                }
                // can't convert non-internet addresses
            } else {
                addressString = super.format(address.toString());
            }
            returnString += addressString;
            // print a semicolon between all the addresses
            if (addressesIterator.hasNext()) {
                returnString += separator + " ";
            }
        }
        return returnString;
    }

    /**
     * <p>Format a single address as an anchor link.</p>
     *
     * @param address the email Address to format.
     * @return a formatted email address linking to the ivata groupware
     * email system.
     *
     */
    public String format(final Address address) {
        // simple prerequisite: rubbish in -> rubbish out...
        if (address == null) {
            return null;
        }
        Address[  ] addresses = { address };

        return format(Arrays.asList(addresses));
    }

    /**
     * <p>Get the string used as a separator between email addresses.</p>
     *
     * @see #format
     *
     * @return the current value of the separator used between email
     * addresses.
     *
     */
    public final String getSeperator() {
        return separator;
    }

    /**
     * <p>Set the string used as a separator between email addresses.</p>
     *
     * @see #format
     *
     * @param separator the new value of the separator used between email
     * addresses.
     *
     */
    public final void setSeperator(final String separator) {
        this.separator = separator;
    }

    /**
     * <p>Default constructor.</p>
     *
     */
    public EmailAddressFormatter() {
        super();
    }

    /**
     * <p>Construct a new email formatter, using all of the formats of
     * another
     * formatter.</p>
     *
     */
    public EmailAddressFormatter(HTMLFormatter formatter) {
        super();
        // just set our formats to be the same as those of the formatter provided
        setFormats(new Vector(formatter.getFormats()));
    }

    /**
     * <p>Get whether or not the email address or the person's name should
     * appear in the body of the link.</p>
     *
     * @return <code>false</code> if the name of the person will appear in
     * the link, otherwise the email address itself will appear
     */
    public final boolean getShowAddress() {
        return showAddress;
    }

    /**
     * <p>Set whether or not the email address or the person's name should
     * appear in the body of the link.</p>
     *
     * @param showAddress set to <code>false</code> if the name of the
     * person should appear in the link, otherwise the email address
     * itself
     * will appear.
     */
    public final void setShowAddress(final boolean showAddress) {
        this.showAddress = showAddress;
    }

    /**
     * <p>Stores the current page context. This is needed to create the
     * <em>JavaScript</em> window.</p>
     *
     * @return the current value of pageContext.
     */
    public final PageContext getPageContext() {
        return pageContext;
    }

    /**
     * <p>Stores the current page context. This is needed to create the
     * <em>JavaScript</em> window.</p>
     *
     * @param pageContext the new value of pageContext.
     */
    public final void setPageContext(final PageContext pageContext) {
        this.pageContext = pageContext;
    }
}

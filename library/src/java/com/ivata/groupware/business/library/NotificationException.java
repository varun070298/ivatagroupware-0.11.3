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
 * $Log: NotificationException.java,v $
 * Revision 1.2  2005/04/09 17:19:44  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:55  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library;

import java.util.List;

import com.ivata.mask.util.SystemException;

/**
 * An instance of this class is thrown whenever the notification for a library
 * item cannot be sent out.
 *
 * @since ivata groupware 0.10 (2005-01-11)
 * @author Colin MacLeod
 * <a href="mailto:colin.macleod@ivata.com">colin.macleod@ivata.com</a>
 * @version $Revision: 1.2 $
 */
public class NotificationException extends SystemException {
    private List recipientEmailAddresses;
    private String senderEmailAddress;
    /**
     * @param messageParam clear text message l
     */
    public NotificationException(final Throwable causeParam,
            final String senderEmailAddressParam,
            final List recipientEmailAddressesParam) {
        super(causeParam);
        this.recipientEmailAddresses = recipientEmailAddressesParam;
        this.senderEmailAddress = senderEmailAddressParam;
    }

    /**
     * @return Returns the recipientEmailAddresses.
     */
    public List getRecipientEmailAddresses() {
        return recipientEmailAddresses;
    }
    /**
     * @return Returns the senderEmailAddress.
     */
    public String getSenderEmailAddress() {
        return senderEmailAddress;
    }
}

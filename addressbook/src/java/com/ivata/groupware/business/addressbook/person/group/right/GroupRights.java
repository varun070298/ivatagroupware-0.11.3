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
 * $Log: GroupRights.java,v $
 * Revision 1.4.2.1  2005/10/08 16:14:31  colinmacleod
 * Added new rule that parent groups cannot be deleted.
 *
 * Revision 1.4  2005/04/28 18:47:10  colinmacleod
 * Fixed XHMTL, styles and resin compatibility.
 * Added support for URL rewriting.
 *
 * Revision 1.3  2005/04/10 18:47:35  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:07  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:35  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.person.group.right;

import java.io.Serializable;
import java.util.List;

import com.ivata.groupware.business.addressbook.AddressBook;
import com.ivata.groupware.business.addressbook.person.group.GroupConstants;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.mask.persistence.right.PersistenceRights;
import com.ivata.mask.valueobject.ValueObject;

import com.ivata.groupware.admin.security.server.SecurityServer;
import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.mask.util.SystemException;

/**
 * This class will implement ivata groupware rights, by checking against the
 * group right table/entities.
 * To keep things simple for this release, however, it returns <code>true</code>
 * for <em>almost</em> everything - it doesn't let you delete major things like
 * the system-wide address book, but that is about it.
 *
 * @since ivata groupware 0.10 (2005-01-14)
 * @author Colin MacLeod
 * <a href="mailto:colin.macleod@ivata.com">colin.macleod@ivata.com</a>
 * @version $Revision: 1.4.2.1 $
 */

public class GroupRights implements PersistenceRights, Serializable {
    private AddressBook addressBook;
    private SecurityServer securityServer;
    public GroupRights (AddressBook addressBook, SecurityServer securityServer) {
        this.addressBook = addressBook;
        this.securityServer = securityServer;
    }
    /**
     * Refer to {@link PersistenceRights#canAdd}.
     * @param valueObjectClassParam Refer to {@link PersistenceRights#canAdd}.
     *
     * @return Refer to {@link PersistenceRights#canAdd}.
     */
    public boolean canAdd(String userName,
            Class valueObjectClassParam) {
        return true;
    }

    /**
     * Refer to
     * {@link PersistenceRights#canAmend(String, ValueObject)}.
     * @param valueObjectParam Refer to
     * {@link PersistenceRights#canAmend(String, ValueObject)}.
     *
     * @return Refer to
     * {@link PersistenceRights#canAmend(String, ValueObject)}.
     */
    public boolean canAmend(String userNameParam,
            ValueObject valueObjectParam) {
        return true;
    }

    /**
     * Refer to {@link PersistenceRights#canAmend(String,
     * ValueObject, String)}.
     * @param valueObjectParam Refer to
     * {@link PersistenceRights#canAmend(String,
     * ValueObject, String)}.
     * @param fieldNameParam Refer to
     * {@link PersistenceRights#canAmend(String,
     * ValueObject, String)}.
     * @return Refer to
     * {@link PersistenceRights#canAmend(String,
     * ValueObject, String)}.
     * @see PersistenceRights#canAmend(String, com.ivata.mask.valueobject.ValueObject, java.lang.String)
     */
    public boolean canAmend(String userNameParam,
            ValueObject valueObjectParam, String fieldNameParam) {
        // you can only change the name of an address book if it is not
        // private
        if (valueObjectParam instanceof GroupDO) {
            GroupDO group = (GroupDO)valueObjectParam;
            GroupDO parent = group.getParent();
            // you can't rename a private address book
            if ((parent != null)
                    && "name".equals(fieldNameParam)
                    && (GroupConstants.equals(
                            parent.getId(),
                            GroupConstants.ADDRESS_BOOK_PRIVATE))) {
                return false;
            }
        }

        // everything else goes...
        return true;
    }

    /**
     * Refer to {@link PersistenceRights#canRemove}.
     * @param valueObjectParam Refer to {@link
     * PersistenceRights#canRemove}.
     *
     * @return Refer to {@link PersistenceRights#canRemove}.
     */
    public boolean canRemove(String userNameParam,
            ValueObject valueObjectParam) {
        // only return false if this is a major component
        if (valueObjectParam instanceof GroupDO) {
            GroupDO group = (GroupDO)valueObjectParam;
            // you can't delete the default, system-wide address book
            if (GroupConstants.equals(
                    group.getId(),
                    GroupConstants.ADDRESS_BOOK_DEFAULT)) {
                return false;
            }
            GroupDO parent = group.getParent();
            // you can't delete a private address book
            if ((parent == null)
                    || (GroupConstants.equals(
                            parent.getId(),
                            GroupConstants.ADDRESS_BOOK_PRIVATE))) {
                return false;
            }
            // you can't delete a group which has users, people
            if ((group.getPeople().size() > 0)
                    || (group.getUsers().size() > 0)) {
                return false;
            }
            // you can't delete a group which has children groups
            try {
                SecuritySession guestSession = securityServer.loginGuest();
                List children = addressBook.findGroupsByParent(guestSession,
                    group.getId());
                if ((children != null)
                        && (children.size() > 0)) {
                    return false;
                }
            } catch (SystemException e) {
                throw new RuntimeException(e);
            }
        }
        // everything else goes...
        return true;
    }
}

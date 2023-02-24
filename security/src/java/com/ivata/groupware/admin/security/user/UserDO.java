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
 * $Log: UserDO.java,v $
 * Revision 1.4  2005/04/29 02:48:20  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:09:48  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:57  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:42  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.3  2004/12/31 19:28:04  colinmacleod
 * Added override for displayValue.
 *
 * Revision 1.2  2004/11/03 16:10:10  colinmacleod
 * Changed todo comments to TODO: all caps.
 *
 * Revision 1.1  2004/09/30 15:15:59  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.2  2004/07/18 21:56:17  colinmacleod
 * Removed person. This is a one-way relationship to make it faster.
 *
 * Revision 1.1  2004/07/13 19:15:26  colinmacleod
 * Moved from business.addressbook to admin.security.
 *
 * Revision 1.3  2004/03/21 21:16:07  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:52  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.8  2003/12/12 11:08:58  jano
 * fixing aaddressbook functionaality
 *
 * Revision 1.7  2003/11/13 16:03:16  jano
 * commitng everything to CVS
 * can deploy and application is ruuning, can login into
 *
 * Revision 1.6  2003/11/03 11:28:24  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.5  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.4  2003/10/16 06:17:06  jano
 * fixing comments
 *
 * Revision 1.3  2003/10/15 13:41:12  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.6  2003/09/11 13:10:48  jano
 * fixing bugs with ranaming userName and with removing user from system
 *
 * Revision 1.5  2003/07/25 11:41:57  jano
 * adding functionality for addressBook extension
 *
 * Revision 1.4  2003/05/01 12:13:22  jano
 * tidy up names of sequeneces
 *
 * Revision 1.3  2003/04/09 08:52:14  jano
 * handling data of removing user
 *
 * Revision 1.2  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.11  2003/02/04 17:43:45  colin
 * copyright notice
 *
 * Revision 1.10  2003/01/09 13:32:51  jano
 * I remove relationShip between user and Event
 *
 * Revision 1.9  2002/10/22 10:46:13  colin
 * resurrected LibraryItemRight to fix problems when deleting library items
 *
 * Revision 1.8  2002/10/03 12:34:16  colin
 * Removed LibraryItemRightBean - replaced with CMR on User
 *
 * Revision 1.7  2002/09/02 08:56:10  colin
 * events relationship (dont know where it came from)
 *
 * Revision 1.6  2002/07/26 14:42:22  colin
 * added a person CMR to the user
 *
 * Revision 1.5  2002/07/15 08:16:07  colin
 * added person to create
 *
 * Revision 1.4  2002/07/04 12:29:28  jano
 * i put readonly script to CVS and i will commit all SRC directory
 *
 * Revision 1.3  2002/06/28 13:15:23  colin
 * first addressbook release
 *
 * Revision 1.2  2002/06/17 07:28:52  colin
 * improved and extended javadoc documentation
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.security.user;


import java.util.Set;

import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>Each user within the system has a user name, and activation information
 * such as a password. This class maintains this information.</p>
 *
 * @since 2002-05-05
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 *
 * @hibernate.class
 *      table="person_user"
 * @hibernate.cache
 *      usage="read-write"
 */
public class UserDO  extends BaseDO {
    /**
     * <p><code>true</code> if user was deleted, and created some data in system
     * which we don't want to remove.</p>
     */
    private boolean deleted;

    /**
     * <p>Find out if a user is enabled or disabled.</p>
     */
    private boolean enabled;

    /**
     * <p>Each user is a member of several groups. By default, each user is a
     * member of the group called "everyOne" </p>
     */
    private Set groups;

    /**
     * <p>The user name. This name must uniquely identify the user
     * within the system, and should only contain alphanumeric characters.</p>
     */
    private String name;

    /**
     * <p>The user's password, if the authentication is not done through the
     * <code>IMAP</code> webmail interface.</p>
     */
    private String password;

    /**
     * <p>If the user is deleted we will make new name.</p>
     * @return userName
     */
    public final String getDisplayName() {
        if (isDeleted()) {
            return this.getName() + " (deleted)";
        } else {
            return this.getName();
        }
    }
    /**
     * <p>
     * For a user, the value displayed in a choice box is just the user name,
     * </p>
     *
     * @see com.ivata.mask.valueobject.ValueObject#getDisplayValue()
     */
    public final String getDisplayValue() {
        return name;
    }
    /**
     * <p>Each user is a member of several groups. By default, each user is a
     * member of the group called "everyOne" </p>
     *
     * @hibernate.set
     *      role="person_group"
     *      table="person_group_member"
     * @hibernate.collection-key
     *      column="person_user"
     * @hibernate.collection-many-to-many
     *      class="com.ivata.groupware.business.addressbook.person.group.GroupDO"
     *      column="person_group"
     */
    public final Set getGroups() {
        return groups;
    }
    /**
     * <p>
     * Unique identifier of this data object.
     * </p>
     *
     * NOTE: this is a hibernate one-to-one relationship with person, so we
     *       need no generator here...
     * @hibernate.id
     *      generator-class = "assigned"
     */
    public final Integer getId() {
       return super.getId();
    }
    /**
     * <p>Get the user name. This name must uniquely identify the user
     * within the system, and should only contain alphanumeric characters.</p>
     *
     * @return new value for the user name. Should be unique within the intranet
     *      system.
     *
     * @hibernate.property
     */
    public final String getName() {
        return name;
    }
    /**
     * <p>Get the user's password, if the authentication is not done through the
     * <code>IMAP</code> webmail interface.</p>
     *
     * @return new value of the user's password (encrypted).
     *
     * @hibernate.property
     */
    public final String getPassword() {
        return password;
    }
    /**
     * <p>True if user was deleted, and he did created some date in system which we don't want to remove.</p>
     * @return
     *
     * @hibernate.property
     */
    public boolean isDeleted() {
        return deleted;
    }
    /**
     * <p>Find out if a user is enabled or disabled.</p>
     *
     * @return <code>true</code> if the user is currently enabled, or
     *     <code>false</code> if the user is prevented from accessing the
     *     system.
     *
     * TODO: change column to enabled
     * @hibernate.property
     *      column="enable"
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * <p>Set to <code>true</code> if user was deleted, and created some data
     * in the system which we don't want to remove.</p>
     */
    public final void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * <p>Enable/disable this user. This prevents the usr from being able to
     * login and access the system.</p>
     *
     * @param enable set to <code>true</code> to the enable the user, or
     *     <code>false</code> to prevent this user from accessing the system.
     */
    public final void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * <p>Each user is a member of several groups. By default, each user is a
     * member of the group called "everyOne" </p>
     */
    public final void setGroups(final Set groups) {
        this.groups = groups;
    }

    /**
     * <p>Sets the user name. This name must uniquely identify the user
     * within the system, and should only contain alphanumeric characters.</p>
     *
     * @param name new value for the user name. Should be unique within the
     *     intranet system.
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * <p>Set the user's password, if the authentication is not done through the
     * <code>IMAP</code> webmail interface.</p>
     *
     * @param password new value of the user's password, already encrypted.
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

}

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
 * $Log: RightDO.java,v $
 * Revision 1.4  2005/04/29 02:48:13  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:09:36  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:07  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:35  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/07/13 19:41:14  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:06  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:51  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.6  2003/11/13 16:03:15  jano
 * commitng everything to CVS
 * can deploy and application is ruuning, can login into
 *
 * Revision 1.5  2003/11/03 11:28:24  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.3  2003/10/15 13:45:32  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.3  2003/05/01 12:13:22  jano
 * tidy up names of sequeneces
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
 * Revision 1.10  2003/01/08 17:17:10  jano
 * fixing bug
 *
 * Revision 1.9  2003/01/03 15:42:30  jano
 * we don't need tempFindTarge...... method, we are using select method
 *
 * Revision 1.8  2003/01/03 15:01:46  jano
 * we have new selec method and method where we are calling that select method
 *
 * Revision 1.7  2003/01/03 10:21:45  jano
 * fixing bug
 *
 * Revision 1.6  2003/01/03 09:11:15  jano
 * taking VIEW of
 *
 * Revision 1.4  2002/09/27 12:50:57  jano
 * ejbCreate ejbPost was change
 *
 * Revision 1.3  2002/07/04 12:29:28  jano
 * i put readonly script to CVS and i will commit all SRC directory
 *
 * Revision 1.2  2002/06/17 07:28:44  colin
 * improved and extended javadoc documentation
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.person.group.right;

import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.groupware.business.addressbook.person.group.right.detail.RightDetailDO;
import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>Assigns the right to perform a specific action to a group of people. Each
 * right allows a group to add, amend, view or delete entries from the intranet.
 * The element which is allowed to be changed by this right is described by the
 * {@link com.ivata.groupware.adddressbook.person.group.right.detail.RightDetailBean
 * detail EJB} associated with it.</p>
 *
 * @since 2002-05-19
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @see
 *     com.ivata.groupware.adddressbook.person.group.right.detail.RightDetailBean
 * @see RightConstants
 * @version $Revision: 1.4 $
 *
 * @hibernate.class
 *      table="person_group_right"
 * @hibernate.cache
 *      usage="read-write"
 */
public class RightDO  extends BaseDO {
    /**
     * <p>Access level allowed. This will give the group the right to
     * add, amend, view or delete the associated data, as defined in
     * {@link RightConstants RightConstants}.</p>
     */
    private Integer access;

    /**
     * <p>The detail, which provides clear text information about this
     * right.</p>
     */
    private RightDetailDO detail;

    /**
     * <p>Group who is entitled by this right. Rights in ivata groupware
     * are allocated on a per-group basis.</p>
     */
    private GroupDO group;

    /**
     * <p>Id of the DO this right targets. Some rights allow a group
     * access to a specific instance of an EJB. This id is the id of that
     * instance.</p>
     */
    private Integer targetId;

    /**
     * <p>Get the access level allowed. This will give the group the right to
     * add, amend, view or delete the associated data, as defined in
     * {@link RightConstants RightConstants}.</p>
     *
     * @return access level allowed, as defined in {@link RightConstants
     *     RightConstants}.
     *
     * @hibernate.property
     *      column="access"
     */
    public final Integer getAccess() {
        return access;
    }

    /**
     * <p>Get the detail, which provides clear text information about this
     * right.</p>
     *
     * @return instance providing clear text information about this right
     *
     * @hibernate.many-to-one
     *      column="detail"
     */
    public final RightDetailDO getDetail() {
        return detail;
    }
    /**
     * <p>Get the group who is entitled by this right. Rights in ivata groupware
     * are allocated on a per-group basis.</p>
     *
     * @return group entitled to this right.
     *
     * @hibernate.many-to-one
     *      column="person_group"
     */
    public final GroupDO getGroup() {
        return group;
    }
    /**
     * <p>Get the id of the DO this right targets. Some rights allow a group
     * access to a specific instance of an EJB. This id is the id of that
     * instance.</p>
     *
     * @return the id of the EJB the right targets, or null if this right is not
     *      specific to a single instance.
     *
     * @hibernate.property
     *      column="target_id"
     */
    public final Integer getTargetId() {
        return targetId;
    }

    /**
     * <p>Set the access level allowed. This will give the group the right to
     * add, amend, view or delete the associated data, as defined in
     * {@link RightConstants RightConstants}.</p>
     *
     * @param access access level allowed, as defined in {@link RightConstants
     *     RightConstants}
     */
    public final void setAccess(final Integer access) {
        this.access = access;
    }

    /**
     * <p>Set the detail, which provides clear text information about this
     * right.</p>
     *
     * @param detail instance providing clear text information about this right
     */
    public final void setDetail(final RightDetailDO detail) {
        this.detail = detail;
    }

    /**
     * <p>Set the group who is entitled by this right. Rights in ivata groupware
     * are allocated on a per-group basis.</p>
     *
     * @param group group bean entitled to this right.
     */
    public final void setGroup(final GroupDO group) {
        this.group = group;
    }

    /**
     * <p>Set the id of the DO this right targets. Some rights allow a group
     * access to a specific instance of an EJB. This id is the id of that
     * instance.</p>
     *
     * @param targetId the id of the EJB the right targets, or null if this
     *     right is not specific to a single instance.
     */
    public final void setTargetId(final Integer targetId) {
        this.targetId = targetId;
    }
}

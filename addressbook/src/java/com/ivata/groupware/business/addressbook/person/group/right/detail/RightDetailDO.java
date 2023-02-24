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
 * $Log: RightDetailDO.java,v $
 * Revision 1.3  2005/04/10 20:09:36  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:08  colinmacleod
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
 * Revision 1.5  2003/11/03 11:28:24  jano
 * commiting addressbook,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.3  2003/10/15 13:46:12  jano
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
 * Revision 1.5  2003/02/04 17:43:45  colin
 * copyright notice
 *
 * Revision 1.4  2002/09/26 09:54:27  jano
 * name of sequence
 *
 * Revision 1.3  2002/07/04 12:29:28  jano
 * i put readonly script to CVS and i will commit all SRC directory
 *
 * Revision 1.2  2002/06/17 07:29:07  colin
 * improved and extended javadoc documentation
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.person.group.right.detail;

import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>Represents details a right for group of people to do something. This
 * class provides detail explaining in clear text what the right allows.</p>
 *
 * @since 2002-05-15
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 * @see com.ivata.groupware.adddressbook.person.group.right.RightBean
 *
 * @hibernate.class
 *      table="person_group_right_detail"
 * @hibernate.cache
 *      usage="read-write"
 */
public class RightDetailDO  extends BaseDO {

    /**
     * <p>Full and detailed description of the entitlements the right
     * offers.</p>
     */
    private String description;
    /**
     * <p>Name of the right is a clear-text field which uniquely which
     * identifies what it is the group will be allowed to do. This name can
     * include spaces.</p>
     */
    private String name;
    /**
     * <p>Get full and detailed description of the entitlements the right
     * offers.</p>
     *
     * @return full and detailed description of the entitlements the right
     *     offers.
     * @hibernate.property
     */
    public final String getDescription() {
        return description;
    }
    /**
     * <p>Get the name of the right is a clear-text field which uniquely which
     * identifies what it is the group will be allowed to do. This name can
     * include spaces.</p>
     *
     * @return clear text name of the right, possibly including spaces.
     * @hibernate.property
     */
    public final String getName() {
        return name;
    }

    /**
     * <p>Set full and detailed description of the entitlements the right offers.</p>
     *
     * @param description full and detailed description of the entitlements the
     *     right offers.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * <p>Set the name of the right is a clear-text field which uniquely which identifies what it is the group will be allowed to do. This name can include spaces.</p>
     *
     * @param name clear text name of the right, possibly including spaces.
     */
    public final void setName(final String name) {
        this.name = name;
    }
}

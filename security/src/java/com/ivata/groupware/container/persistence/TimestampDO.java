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
 * $Log: TimestampDO.java,v $
 * Revision 1.3.2.1  2005/10/08 17:31:37  colinmacleod
 * Used @hibernate.version tag for modified field.
 *
 * Revision 1.3  2005/04/10 20:10:04  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:58  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:32  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/09/30 15:16:00  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.1  2004/07/13 19:41:15  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.container.persistence;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.ivata.groupware.admin.security.user.UserDO;

/**
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Mar 28, 2004
 * @version $Revision: 1.3.2.1 $
 */
public abstract class TimestampDO extends BaseDO {
    private static Logger log = Logger.getLogger(TimestampDO.class);
    /**
     * <p>The date and time when the DO was created.</p>
     */
    Timestamp created;

    /**
     * <p>User who created this DO.</p>
     */
    private UserDO createdBy;

    /**
     * <p>The date and time when the DO was last modified.</p>
     */
    Timestamp modified;

    /**
     * <p>User who last modified this DO.</p>
     */
    private UserDO modifiedBy;
    /**
     * <p>Get the date and time when the DO was created.</p>
     *
     * @return the date and time when the DO was created.
     * @hibernate.property
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * <p>Get the user who created this DO.</p>
     *
     * @return the user who created this DO.
     * @hibernate.many-to-one
     *      column="created_by"
     */
    public UserDO getCreatedBy() {
        return createdBy;
    }
    /**
     * <p>Get the date and time when the DO was last modified.</p>
     *
     * @return the date and time when the DO was last modified.
     * @hibernate.version
     */
    public Timestamp getModified() {
        return modified;
    }

    /**
     * <p>Get the username of the user who last modified this DO.</p>
     *
     * @return the user name for the user who last modified this DO.
     * @hibernate.many-to-one
     *      column="modified_by"
     */
    public UserDO getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Refer to {@link #getCreated}.
     * @param createdParam The created to set.
     */
    public void setCreated(Timestamp createdParam) {
        if (log.isDebugEnabled()) {
            log.debug("setCreated before: '" + created
                    + "', after: '" + createdParam + "': "
                    + toString());
        }
        assert (createdParam != null);
        created = createdParam;
    }
    /**
     * Refer to {@link #getCreatedBy}.
     * @param createdByParam The createdBy to set.
     */
    public void setCreatedBy(UserDO createdByParam) {
        if (log.isDebugEnabled()) {
            log.debug("setCreatedBy before: '" + createdBy
                    + "', after: '" + createdByParam + "': "
                    + toString());
        }
        assert (createdByParam != null);
        createdBy = createdByParam;
    }
    /**
     * Refer to {@link #getModified}.
     * @param modifiedParam The modified to set.
     */
    public void setModified(Timestamp modifiedParam) {
        if (log.isDebugEnabled()) {
            log.debug("setModified before: '" + modified
                    + "', after: '" + modifiedParam + "': "
                    + toString());
        }
        assert (modifiedParam != null);
        modified = modifiedParam;
    }
    /**
     * Refer to {@link #getModifiedBy}.
     * @param modifiedByParam The modifiedBy to set.
     */
    public void setModifiedBy(UserDO modifiedByParam) {
        if (log.isDebugEnabled()) {
            log.debug("setModifiedBy before: '" + modifiedBy
                    + "', after: '" + modifiedByParam + "': "
                    + toString());
        }
        assert (modifiedByParam != null);
        modifiedBy = modifiedByParam;
   }
    /**
     * Refer to {@link }.
     * Overridden to output current time and user info - useful for debugging.
     *
     * @return string with current class, id and user/time information.
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return super.toString()
                + "[ Created "
                + created
                + " by "
                + ((createdBy == null)
                        ? "[nobody]"
                        : createdBy.getDisplayName())
                + ", Modified "
                + modified
                + " by "
                + ((modifiedBy == null)
                        ? "[nobody]"
                                : modifiedBy.getDisplayName())
                + " ]";
    }
}

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
 * $Log: SettingDO.java,v $
 * Revision 1.3  2005/04/10 20:10:04  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:57  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:37  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/09/30 15:15:59  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.2  2004/07/13 19:54:31  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:05  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:46  colinmacleod
 * Moved ivata openportal to SourceForge..
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
 * Revision 1.4  2003/10/16 07:40:37  jano
 * fixing comments
 *
 * Revision 1.3  2003/10/15 13:13:59  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 13:04:20  colin
 * fixing for XDoclet
 *
 * Revision 1.4  2003/03/03 16:57:08  colin
 * converted localization to automatic paths
 * added labels
 * added mandatory fieldName attribute
 *
 * Revision 1.3  2003/02/25 16:58:08  peter
 * fixed bugs in create method, id and sequence name
 *
 * Revision 1.2  2003/02/25 14:38:12  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.1  2003/02/24 18:56:14  colin
 * added to admin
 *
 * Revision 1.13  2003/02/24 09:50:39  peter
 * the enable field is boolean now
 *
 * Revision 1.12  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.11  2003/02/04 08:00:28  peter
 * modified to handle null userName booth in getSetting and setSetting
 *
 * Revision 1.10  2002/11/12 09:36:08  colin
 * added ejbRemove
 *
 * Revision 1.9  2002/07/15 07:51:04  colin
 * added new Mail EJB and local interface to settings
 *
 * Revision 1.8  2002/07/05 10:32:52  colin
 * changes trying to move the project to jano
 *
 * Revision 1.6  2002/07/04 12:29:28  jano
 * i put readonly script to CVS and i will commit all SRC directory
 *
 * Revision 1.5  2002/07/02 14:58:21  colin
 * tried to fix jbuilder EJB designer
 *
 * Revision 1.4  2002/06/21 12:10:03  colin
 * cosmetic changes. tidied up documentation
 *
 * Revision 1.3  2002/06/17 07:28:58  colin
 * improved and extended javadoc documentation
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.setting;


import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.container.persistence.NamedDO;

/**
 * <p>This class represents a single setting within the system. Each setting can
 * either be global, or overridden for a single user. You can have integer,
 * string or boolean settings. These are identified by the type constants
 * defined in {@link SettingConstants SettingConstants}.</p>
 *
 * @since 2001-04-20
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 *
 * @hibernate.class
 *      table="setting"
 * @hibernate.cache
 *      usage="read-write"
 */
public class SettingDO  extends NamedDO {
    /**
     * <p>Clear text description of the purpose of the setting and what
     * elements of the intranet system this setting affects.</p>
     */
    private String description;

    /**
     * <p>Defines whether or not this setting is currently enabled.</p>
     */
    private boolean enabled = true;

    /**
     * <p>Name of this setting. This is a clear text identifier which
     * should be unique within the system. Use alphanumeric characters with no
     * spaces.</p>
     */
    private String name;

    /**
     * <p>Type of the settting. This can be any of the 'TYPE_...'
     * constants defined in {@link SettingConstant SettingConstant}.</p>
     */
    private int type;

    /**
     * <p>User this setting applies to, or <code>null</code> if it is
     * the default setting.</p>
     */
    private UserDO user;

    /**
     * <p>Value of the setting. Whilst all settings are stored as
     * strings, the content should represent the type of the setting so that
     * integer types will only contain numeric strings, and boolean types will
     * only contain 'true' or 'false'.</p>
     */
    private String value;
    /**
     * <p>Get a clear text description of the purpose of the setting and what
     * elements of the intranet system this setting affects.</p>
     *
     * @return a clear text description of the purpose of the setting and what
     *     elements of the intranet system this setting affects.
     *
     * @hibernate.property
     */
    public final String getDescription() {
        return description;
    }
    /**
     * <p>Get the name of this setting. This is a clear text identifier which
     * should be unique within the system. Use alphanumeric characters with no
     * spaces.</p>
     *
     * @return the name of the setting, a clear text identifier.
     *
     * @hibernate.property
     */
    public final String getName() {
        return name;
    }
    /**
     * <p>Get the type of the settting. This can be any of the 'TYPE_...'
     * constants defined in {@link SettingConstant SettingConstant}.</p>
     *
     * @return the type of the settting, one of the 'TYPE_...' constants defined
     *      in {@link SettingConstant SettingConstant}.
     *
     * @hibernate.property
     */
    public final int getType() {
        return type;
    }

    /**
     * <p>Get the user this setting applies to, or <code>null</code> if it is
     * the default setting.</p>
     *
     * @return the user this setting applies to, or <code>null</code> if it is
     *     the default setting.
     *
     * @hibernate.many-to-one
     *      column="person_user"
     */
    public UserDO getUser() {
        return user;
    }
    /**
     * <p>Get the value of the setting. Whilst all settings are stored as
     * strings, the content should represent the type of the setting so that
     * integer types will only contain numeric strings, and boolean types will
     * only contain 'true' or 'false'.</p>
     *
     * @return the value of the setting.
     *
     * @hibernate.property
     */
    public final String getValue() {
        return value;
    }
    /**
     * <p>Get whether or not this setting is currently enabled.</p>
     *
     * @return <code>true</code> if the setting may be used currently, otherwise
     *      <code>false</code>.
     *
     * @hibernate.property
     *      column="enable"
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * <p>Set a clear text description of the purpose of the setting and what
     * elements of the intranet system this setting affects.</p>
     *
     * @param description a clear text description of the purpose of the setting
     *      and what elements of the intranet system this setting affects.
     */
    public final void setDescription(final String description) {
        this.description = description;
    }

    /**
     * <p>Set this setting to be currently enabled.</p>
     *
     * @param enable <code>true</code> if the setting may be used currently,
     *     otherwise <code>false</code>.
     */
    public final void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * <p>Set the name of this setting. This is a clear text identifier which
     * should be unique within the system. Use alphanumeric characters with no
     * spaces.</p>
     *
     * @param name the name of the setting, a clear text identifier.
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * <p>Set the type of the setting. This can be any of the 'TYPE_...'
     * constants defined in {@link SettingConstant SettingConstant}.</p>
     *
     * @param type the type of the settting, one of the 'TYPE_...' constants
     *     defined in {@link SettingConstant SettingConstant}.
     */
    public final void setType(final int type) {
        this.type = type;
    }

    /**
     * <p>Set the user this setting applies to, or <code>null</code> if it is
     * the default setting.</p>
     *
     * @param user the user this setting applies to, or <code>null</code> if it
     *     is the default setting.
     */
    public final void setUser(final UserDO user) {
        this.user = user;
    }

    /**
     * <p>Set the value of the setting. Whilst all settings are stored as
     * strings, the content should represent the type of the setting so that
     * integer types will only contain numeric strings, and boolean types will
     * only contain 'true' or 'false'.</p>
     *
     * @param value the value of the setting.
     */
    public final void setValue(final String value) {
        this.value = value;
    }
}
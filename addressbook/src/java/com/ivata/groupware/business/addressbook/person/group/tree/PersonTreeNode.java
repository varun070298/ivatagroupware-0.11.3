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
 * $Log: PersonTreeNode.java,v $
 * Revision 1.4  2005/04/29 02:48:13  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.3  2005/04/10 20:09:37  colinmacleod
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
 * Revision 1.5  2004/07/13 19:41:14  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:07  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/10 19:57:22  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:00:32  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:52  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/10/17 12:36:12  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:18:02  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/02/24 19:09:21  colin
 * moved to business
 *
 * Revision 1.4  2003/02/04 17:43:45  colin
 * copyright notice
 *
 * Revision 1.3  2002/09/17 08:38:56  colin
 * added group tree right to make the tree faster
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.person.group.tree;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.ivata.groupware.business.addressbook.person.PersonDO;
import com.ivata.groupware.business.addressbook.person.group.GroupDO;
import com.ivata.groupware.web.tree.TreeNode;


/**
 * <p>Stores one node of a tree which can either contain just groups or groups
 * and people.</p>
 *
 * <p>If this is a node representing a group, the person element will be
 * <code>null</code>, otherwise the group element will contain the main group
 * details of the person represented by the node.</p>
 *
 * @since  2002-08-26
 * @author Jan Boros <janboros@sourceforge.net>
 * @version $Revision: 1.4 $
 */
public class PersonTreeNode implements Serializable, TreeNode {

    /**
     * <p>Stores the group data for this node as a data object.</p>
     */
    private GroupDO group;

    /**
     * <p>Stores the person data for this node as a data object.</p>
     */
    private PersonDO person;

    /**
     * <p>Default contructor.</p>
     */
    public PersonTreeNode() {}
    /**
     * Create a person tree node which represents a group of people.
     *
     * @param group group of people this tree node represents.
     */
    public PersonTreeNode(GroupDO group) {
        this.group = group;
    }

    /**
     * Create a person tree node which represents a person.
     *
     * @param person person this tree node represents.
     */
    public PersonTreeNode(PersonDO person) {
        this.person = person;
    }

    /**
     * <p>Get the group data for this node as a data object. If this
     * node represents a group, this gets the values of that group. Otherwise,
     * this gets the values of the main group associated with the person this
     * node represents.</p>
     *
     * @return the data of the group this node represents.
     *     In the case of the node representing a person, this method gets the
     *     details of that person's main group.
     */
    public final GroupDO getGroup() {
        return group;
    }

    /**
     * <p>Get the unique identifier for this tree node.</p>
     *
     * @return if the node represents a group, return the unique identifier for
     *     that group. Otherwise the <em>negative</em> value of the person's unique
     *     identifier is returned.
     */
    public final Integer getId() {
        if (person == null) {
            return group.getId();
        }
        return new Integer(0 - person.getId().intValue());
    }
    /**
     * <p>The name of this tree node is either the value of the
     * <code>fileAs<code> attribute of the person, or the <code>name</code>
     * of the group depending which this tree node represents.</p>
     *
     * @return if this is a person, the <code>fileAs</code> value for that
     *     person, otherwise the <code>name</code> of the group.
     */
    public final String getName() {
        return (person != null) ? person.getFileAs() : group.getName();
    }

    /**
     * <p>Get the person data for this node as a data object.</p>
     *
     * @return values of the person this node represents, or
     *     <code>null</code> if this node represents a group.
     */
    public PersonDO getPerson() {
        return person;
    }

    /**
     * <p>Serialize the object from the input stream provided.</p>
     *
     * @param ois the input stream to serialize the object from
     * @throws IOException thrown by <code>ObjectInputStream.defaultReadObject(  )</code>.
     * @throws ClassNotFoundException thrown by <code>ObjectInputStream.defaultReadObject(  )</code>.
     */
    private void readObject(final ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }

    /**
     * <p>Serialize the object to the output stream provided.</p>
     *
     * @param oos the output stream to serialize the object to
     * @throws IOException thrown by <code>ObjectOutputStream.defaultWriteObject(  )</code>
     */
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }
}

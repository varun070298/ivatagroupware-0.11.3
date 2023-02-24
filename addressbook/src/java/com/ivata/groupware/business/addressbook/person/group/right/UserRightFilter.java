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
 * $Log: UserRightFilter.java,v $
 * Revision 1.2  2005/04/09 17:19:07  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:35  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/07/13 19:41:14  colinmacleod
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
 * Revision 1.3  2002/06/21 12:48:58  colin
 * restructured com.ivata.groupware.web
 *
 * Revision 1.2  2002/06/17 07:28:44  colin
 * improved and extended javadoc documentation
 *
 * Revision 1.1  2002/06/13 11:21:24  colin
 * first version with rose model integration.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.addressbook.person.group.right;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * <p>Some classes access the system on the basis of user rights, and use this
 * class to filter the results according to the parameters given</p>
 *
 * <p>The parameters here are the same as for
 * {@link RightLocalHome#findTargetIdByUserNameAccessDetail
 * RightLocalHome.findTargetIdByUserNameAccessDetail}.</p>
 *
 * @since 2002-05-19
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @see RightLocalHome#findTargetIdByUserNameAccessDetail
 * @version $Revision: 1.2 $
 */
public class UserRightFilter implements Serializable {

    /**
     * <p>Default constructor.</p>
     */
    public UserRightFilter() {
        super();
    }

    /**
     * <p>Construct a user right filter from all its fields.</p>
     *
     * @param userName the user to filter results for. See {@link #setUserName
     *    setUserName}.
     * @param access the access type. See {@link #setAccess setAccess}.
     * @param detail the specific user right you want to filter against. See
     *    {@link #setDetail setDetail}.
     */
    public UserRightFilter(String userName, Integer access,
        Integer detail) {
        setUserName(userName);
        setAccess(access);
        setDetail(detail);
    }

    /**
     * <p>Stores the name of the user for whom the results will be filtered.</p>
     */
    private String userName = null;

    /**
     * <p>Stores the access type. This parameter should be set to one of the 'ACCESS_...'
     * constants in {@link RightConstants RightConstants}.</p>
     *
     * @see RightConstants
     */
    private Integer access = null;

    /**
     * <p>Get the access type. This parameter should be set to one of the 'ACCESS_...'
     * constants in {@link RightConstants RightConstants}.</p>
     *
     * @return the access type.
     * @see RightConstants
     */
    private Integer detail = null;

    /**
     * <p>Get the access type. This parameter should be set to one of the 'ACCESS_...'
     * constants in {@link RightConstants RightConstants}.</p>
     *
     * @return the value of the access type.
     * @see RightConstants
     */
    public final Integer getAccess() {
        return access;
    }

    /**
     * <p>Get the detail of the user right being checked. This parameter should
     * be set to one of the 'DETAIL_...' constants in
     * {@link RightConstants RightConstants}.</p>
     *
     * @return the user right detail.
     * @see RightConstants
     */
    public final Integer getDetail() {
        return detail;
    }

    /**
     * <p>Get the name of the user for whom the results will be filtered.</p>
     *
     * @return the name of the user for whom the results will be filtered.
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * <p>Set the access type. This parameter should be set to one of the 'ACCESS_...'
     * constants in {@link RightConstants RightConstants}.</p>
     *
     * @param access the new value of the access type.
     * @see RightConstants
     */
    public final void setAccess(final Integer access) {
        this.access = access;
    }

    /**
     * <p>Set the detail of the user right being checked. This parameter should
     * be set to one of the 'DETAIL_...' constants in
     * {@link RightConstants RightConstants}.</p>
     *
     * @param detail the new value of the user right detail.
     * @see RightConstants
     */
    public final void setDetail(final Integer detail) {
        this.detail = detail;
    }

    /**
     * <p>Set the name of the user for whom the results will be filtered.</p>
     *
     * @param userName new name for the user for whom the results will
     *     be filtered.
     */
    public final void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * <p>Serialize the object to the output stream provided.</p>
     *
     * @exception IOException thrown by
     *     <code>ObjectOutputStream.defaultWriteObject(  )</code>
     * @param oos the output stream to serialize the object to
     */
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    /**
     * <p>Serialize the object from the input stream provided.</p>
     *
     * @exception ClassNotFoundException thrown by
     *     <code>ObjectInputStream.defaultReadObject</code>.
     * @exception IOException thrown by
     *     <code>ObjectInputStream.defaultReadObject</code>.
     * @param ois the input stream to serialize the object from
     */
    private void readObject(final ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }

    /**
     * <p>Comparison method. See if the object supplied is a user right filter and,
     * if so, whether or not its contents are the same as this one.</p>
     *
     * @param compare the object to compare with this one.
     * @return <code>true</code> if the filter supplied in <code>compare</code>
     *     is effectively the same as this one, otherwise false.
     */
    public boolean equals(final Object compare) {
        // first check it is non-null and the class is right
        if ((compare == null) ||
            !(this.getClass().isInstance(compare))) {
            return false;
        }
        UserRightFilter filter = (UserRightFilter) compare;

        // check that the user names, access and detail are the same for both
        return (((userName == null) ?
                    (filter.userName == null) :
                    userName.equals(filter.userName)) &&
                ((access == null) ?
                    (filter.access == null) :
                    access.equals(filter.access)) &&
                ((detail == null) ?
                    (filter.detail == null) :
                    detail.equals(filter.detail)));
    }
}

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
 * $Log: LibraryForm.java,v $
 * Revision 1.3  2005/04/10 20:31:58  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:46  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:01  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/11/12 18:19:15  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.3  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:41  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/02/28 07:30:22  colin
 * implemented editing/displaying of faqs & notes
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.struts;

import com.ivata.mask.Mask;
import com.ivata.mask.web.struts.DialogForm;


/**
 * <p>Provides a base class for library form
 * classes, to retrieve the library objects.</p>
 *
 *
 * @since 2003-02-18
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public abstract class LibraryForm extends DialogForm {

    /**
     * <p>Indicates the current item/comment should be edited.</p>
     */
    private String edit = null;
    /**
     * <p>When submitting library items and comments, they must be
     * previewed once before submitting.</p>
     */
    private String preview = null;
    /**
     * <p>
     * Defines the base class of all objects in the value object list.
     * </p>
     */
    private Class baseClass;
    /**
     * <p>
     * Mask containing all the field definitions for this list.
     * </p>
     */
    private Mask mask;

    /**
     * <p>Default constructor. Overridden to set <code>bundle</code>.</p>
     *
     * @param maskParam
     *            Refer to {@link #getMask}.
     * @param baseClassParam
     *            Refer to {@link #getBaseClass}.
     */
    public LibraryForm() {
        // we want messages from the library
        setBundle("library");
    }

    /**
     * <p>Indicates the current item/comment should be edited.</p>
     *
     * @return the current value of edit.
     */
    public final String getEdit() {
        return edit;
    }

    /**
     * <p>When submitting library items and comments, they must be
     * previewed once before submitting.</p>
     *
     * @return the current value of preview.
     */
    public final String getPreview() {
        return preview;
    }

    /**
     * <p>Indicates the current item/comment should be edited.</p>
     *
     * @param edit the new value of edit.
     */
    public final void setEdit(final String edit) {
        this.edit = edit;
    }

    /**
     * <p>When submitting library items and comments, they must be
     * previewed once before submitting.</p>
     *
     * @param preview the new value of preview.
     */
    public final void setPreview(final String preview) {
        this.preview = preview;
    }

    /**
     * <p>
     * Defines the base class of all objects in the value object list.
     * </p>
     *
     * @return base class of all objects in the value object list.
     */
    public final Class getBaseClass() {
        return baseClass;
    }

    /**
     * <p>
     * Mask containing all the field definitions for this list.
     * </p>
     *
     * @return mask containing all the field definitions for this list.
     */
    public final Mask getMask() {
        return mask;
    }
}

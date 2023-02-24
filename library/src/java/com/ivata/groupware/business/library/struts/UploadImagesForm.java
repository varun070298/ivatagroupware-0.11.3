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
 * $Log: UploadImagesForm.java,v $
 * Revision 1.3  2005/04/10 20:31:57  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:46  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:05  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.6  2004/11/12 18:19:15  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.5  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/02/10 19:57:24  colinmacleod
 * Changed email address.
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:42  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.1  2003/07/08 06:31:33  peter
 * added to cvs
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.struts;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.upload.FormFile;

import com.ivata.mask.Mask;
import com.ivata.mask.validation.ValidationErrors;
import com.ivata.mask.web.struts.DialogForm;


/**
 * <p>Library image upload form. It's used when there are some local
 * images
 * in the uploaded HTML for library document.</p>
 *
 * @since 2003-07-04
 * @author Peter Illes
 * @version $Revision: 1.3 $
 */
public class UploadImagesForm extends DialogForm {

    /**
     * <p><code>Map</code> storing the version comments of images</p>
     */
    private Map comment = new HashMap();
    /**
     * <p><code>Map</code> of <code>FormFile</code>s, the uploaded
     * images</p>
     */
    private Map image = new HashMap();

    /**
     * <p><code>Vector</code> holding the names of images to upload</p>
     */
    private Vector imageFileName = null;
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
     * TODO
     *
     * @see com.ivata.mask.web.struts.MaskForm#clear()
     */
    protected void clear() {
        // TODO Auto-generated method stub

    }

    /**
     * <p>comments for uploaded images</p>
     * @param index the index in the vector
     * @return the comment with the given index
     */
    public final String getComment(final String index) {
        return (String) comment.get(index);
    }

    /**
     * <p><code>Vector</code> of <code>FormFile</code>s, the uploaded
     * images</p>
     * @param index
     * @return null , file input type can't be get to jsp
     */
    public final FormFile getImage(final String index) {
        return null;
    }

    /**
     * <p><code>Vector</code> holding the names of images to upload</p>
     * @return <code> <code>Vector</code> holding the names of images to
     * upload
     */
    public final Vector getImageFileName() {
        return imageFileName;
    }

    /**
     * <p>returns all uploaded images</p>
     * @return all uploaded images
     */
    public final Map getImages() {
        return image;
    }

    /**
     * <p>comment for uploaded image</p>
     * @param index the index in the vector
     * @comment the version comment
     */
    public final void setComment(final String index,
            final String comment) {
        this.comment.put(index, comment);
    }

    /**
     * <p><code>Vector</code> of <code>FormFile</code>s, the uploaded
     * images</p>
     * @param index
     * @param image the uploaded file
     */
    public final void setImage(final String index,
            final FormFile image) {
        this.image.put(index, image);
    }

    /**
     * <p><code>Vector</code> holding the names of images to upload</p>
     * @param imageFileName <code> <code>Vector</code> holding the names
     * of images to upload
     */
    public final void setImageFileName(final Vector imageFileName) {
        this.imageFileName = imageFileName;
    }

    /**
     * TODO
     *
     * @see com.ivata.mask.web.struts.MaskForm#validate(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession)
     */
    public ValidationErrors validate(final HttpServletRequest request,
            final HttpSession session) {
        // TODO Auto-generated method stub
        return null;
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

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
 * $Log: UploadHTMLAction.java,v $
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
 * Revision 1.10  2004/12/31 18:27:44  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.9  2004/12/23 21:01:29  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.8  2004/11/12 18:19:15  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.7  2004/11/03 15:31:51  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.6  2004/08/01 11:45:19  colinmacleod
 * Restructured search engine into separate subproject.
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
 * Revision 1.3  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/07/08 07:55:56  peter
 * fixed logic for already uploaded images
 * Revision 1.1  2003/07/08 06:31:33  peter
 * added to cvs
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.struts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.web.format.SanitizerFormat;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>Invoked when the user uploads a HTML file to a library
 * document.</p>
 *
 * @since 2003-07-02
 * @author Peter Illes
 * @version $Revision: 1.3 $
 *
 */
public class UploadHTMLAction extends MaskAction {
    /**
     * <p>
     * Constructor. Called by <strong>PicoContainer.</strong>.
     * </p>
     *
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public UploadHTMLAction(MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
    }
    /**
     * <p>Called from the other <code>execute</code> method, this can
     * be overridden by each subclass to provide the <em>ivata</em>-specific
     * processing required.</p>
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     */
    public String execute(ActionMapping mapping, ActionErrors errors,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response, HttpSession session)
            throws SystemException {
        return null;
    }

    /**
     * <p>This method is called if the ok or apply buttons are
     * pressed.</p>
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     * @param ok <code>true</code> if the ok button was pressed, otherwise
     * <code>false</code> if the apply button was pressed.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     */
    public String onConfirm(ActionMapping mapping,
            ActionErrors errors, ActionForm form, HttpServletRequest request,
            HttpServletResponse response, HttpSession session,
            final String defaultForward)
            throws SystemException {
        UploadHTMLForm uploadForm = (UploadHTMLForm) form;
        String returnForward = "utilClosePopUp";

        try {
            InputStream stream = uploadForm.getFile().getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            StringBuffer messageBuffer = new StringBuffer();
            char[] chbuf = new char[1024];
            int count;

            while ((count = in.read(chbuf)) != -1) {
                messageBuffer.append(chbuf, 0, count);
            }

            SanitizerFormat sanitizer = new SanitizerFormat();
            sanitizer.setOnlyBodyContents(true);
            sanitizer.setSourceName("user input");
// TODO            Map parseResult = sanitizer.format(messageBuffer.toString());
            Map parseResult = null;

            List pages = (List) parseResult.get("pages");

            if (!pages.isEmpty()) {
                ItemForm itemForm = (ItemForm) session.getAttribute(
                        "libraryItemForm");
//TODO                itemForm.getItem().setPages(new Vector(pages));
                session.setAttribute("libraryItemForm", itemForm);

                Set images = (Set) parseResult.get("images");
                Vector itemAttachments = null;

                // the attachmnets are either in upload (new item) or in drive
/*TODO                if (itemForm.getItem().getId() == null) {
                    itemAttachments = itemForm.getUploadingFileList();
                } else {
                    itemAttachments = itemForm.getFileList();
                }
*/
                Vector newImages = new Vector();

                // look for images not attached yet
                for (Iterator i = images.iterator(); i.hasNext();) {
                    String currentImage = (String) i.next();
                    boolean newImage = true;

/*TODO                    for (Iterator j = itemAttachments.iterator(); j.hasNext();) {
                        if (currentImage.endsWith(
                                    ((FileDO) j.next()).getFileName())) {
                            newImage = false;

                            break;
                        }
                    }
*/
                    if (newImage) {
                        newImages.add(currentImage);
                    }
                }

                // when there are ne images, go to image upload
                if (!newImages.isEmpty()) {
                    UploadImagesForm uploadImagesForm = new UploadImagesForm();
                    uploadImagesForm.setImageFileName(newImages);
                    request.setAttribute("libraryUploadImagesForm",
                        uploadImagesForm);
                    returnForward = "libraryImageUpload";
                } else {
                    request.setAttribute("openerPage", "/library/submit.action");
                }
            }
        } catch (FileNotFoundException efnf) {
            throw new SystemException(efnf);
        } catch (IOException eio) {
            throw new SystemException(eio);
        }

        return returnForward;
    }
}

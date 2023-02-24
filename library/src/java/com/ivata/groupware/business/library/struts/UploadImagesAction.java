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
 * $Log: UploadImagesAction.java,v $
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
 * Revision 1.9  2004/12/31 18:27:44  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.8  2004/12/23 21:01:29  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.7  2004/11/12 18:19:15  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.6  2004/11/03 15:31:51  colinmacleod
 * Change method interfaces to remove log.
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
 * Revision 1.4  2003/11/13 16:11:08  jano
 * commitng everything to CVS
 * can deploy and application is ruuning, can login into
 *
 * Revision 1.3  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.2  2003/07/16 14:47:04  jano
 * quotaHandling for uploading images during Uploading HTML
 * Revision 1.1  2003/07/08 06:31:33  peter
 * added to cvs
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>Library image upload action. It's used when there are some local
 * images
 * in the uploaded HTML for library document.</p>
 *
 * @since 2003-07-04
 * @author Peter Illes
 * @version $Revision: 1.3 $
 */
public class UploadImagesAction extends MaskAction {
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
    public UploadImagesAction(MaskFactory maskFactory, MaskAuthenticator authenticator) {
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
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
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
    public String onConfirm(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session,
            final String defaultForward) throws SystemException {
        UploadImagesForm uploadForm = (UploadImagesForm) form;

/* TODO        DriveRemote drive = getDriveFromSession(session);
        UsageRemote usage = getUsageFromSession(session);

        Map images = uploadForm.getImages();

        // only worth to do something when there's at least one file uploaded
        if (!images.isEmpty()) {
            Vector fileNameList = new Vector();

            try {
                int finalSize = 0;

                // count fileSize together and check if we can upload
                for (Iterator i = images.keySet().iterator(); i.hasNext();) {
                    String currentKey = (String) i.next();

                    FormFile formFile = (FormFile) images.get(currentKey);

                    // ignore empty files and files already uploaded
                    if ((formFile != null) && (formFile.getFileSize() > 0) &&
                            !fileNameList.contains(formFile.getFileName())) {
                        finalSize += formFile.getFileSize();
                    }
                }

                Integer canUpload = null;

                try {
                    canUpload = usage.canUpload(new Integer(finalSize / 1024),
                            (String) session.getAttribute("userName"));
                } catch (java.rmi.RemoteException e) {
                    throw new RuntimeException(e);
                }

                ResourceBundle adminBundle = ResourceBundle.getBundle("com.ivata.groupware.business.ApplicationResources",
                        (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY));

                if (canUpload.equals(
                            com.ivata.groupware.admin.usage.UsageConstants.CAN_UPLOAD_NEARLY_RUN_OUT_OF_QUOTA) ||
                        canUpload.equals(
                            com.ivata.groupware.admin.usage.UsageConstants.OK)) {
                    for (Iterator i = images.keySet().iterator(); i.hasNext();) {
                        String currentKey = (String) i.next();

                        FormFile formFile = (FormFile) images.get(currentKey);

                        // ignore empty files and files already uploaded
                        if ((formFile != null) && (formFile.getFileSize() > 0) &&
                                !fileNameList.contains(formFile.getFileName())) {
                            InputStream inStream = formFile.getInputStream();

                            // reading the content, writing it to an output stream
                            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                            int oneByte = 0;

                            while ((oneByte = inStream.read()) != -1) {
                                outStream.write(oneByte);
                            }

                            // storing the content to the fileContentDO
                            SerializedByteArray fileContent = new SerializedByteArray(outStream.toByteArray());
                            FileContentDO fileContentDO = new FileContentDO(fileContent,
                                    formFile.getContentType());

                            // store the file server-side, get the updated list
                            drive.uploadFile(fileContentDO,
                                formFile.getFileName(),
                                uploadForm.getComment(currentKey), userName);

                            fileNameList.add(formFile.getFileName());
                        }

                        if (canUpload.equals(
                                    com.ivata.groupware.admin.usage.UsageConstants.CAN_UPLOAD_NEARLY_RUN_OUT_OF_QUOTA)) {
                            request.setAttribute("javaScript",
                                "alert(\"" +
                                adminBundle.getString(
                                    "errors.upload.quota.runOut.nearly") +
                                "\");");
                        }
                    }
                } else if (canUpload.equals(
                            com.ivata.groupware.admin.usage.UsageConstants.NOT_FREE_SPACE_FOR_UPLOAD)) {
                    request.setAttribute("javaScript",
                        "alert(\"" +
                        adminBundle.getString("errors.upload.quota.runOut") +
                        "\");");
                }

                // if there were some uploads, move them to drive upload directory
                if (!fileNameList.isEmpty()) {
                    drive.moveUploads(fileNameList, "drive", userName);
                }
            } catch (java.rmi.RemoteException e) {
                throw new SystemException(e);
            } catch (FileNotFoundException e) {
                throw new SystemException(e);
            } catch (IOException e) {
                throw new SystemException(e);
            }
        }

        request.setAttribute("openerPage", "/library/submit.action");
*/
        return "utilClosePopUp";
    }
}

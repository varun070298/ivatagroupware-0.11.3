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
 * $Log: ItemForm.java,v $
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
 * Revision 1.6  2004/12/23 21:01:29  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.5  2004/11/12 18:19:15  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.4  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
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
 * Revision 1.3  2003/11/13 16:11:08  jano
 * commitng everything to CVS
 * can deploy and application is ruuning, can login into
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.11  2003/07/11 15:08:12  peter
 * version control comment added
 * Revision 1.10  2003/06/20 13:22:15  jano
 * we want deleteFile button in list of attached files
 *
 * Revision 1.9  2003/05/20 08:29:40  jano
 * maintaing attaching files to libray item
 *
 * Revision 1.8  2003/05/13 15:41:00  jano
 * new methods in DriveBean
 *
 * Revision 1.7  2003/05/07 14:01:59  jano
 * we want findFilesByPath
 *
 * Revision 1.6  2003/03/21 15:40:36  peter
 * fixed delete - super.reset was not called
 *
 * Revision 1.5  2003/03/12 14:14:48  jano
 * renaming newHeading -> newPoint
 * fixing problem arround that
 *
 * Revision 1.4  2003/03/03 20:55:19  colin
 * skipped validation if clear button pressed
 *
 * Revision 1.3  2003/02/28 09:37:13  jano
 * RuntimeException(e) -> IntrnetRuntimeException
 *
 * Revision 1.2  2003/02/28 07:30:22  colin
 * implemented editing/displaying of faqs & notes
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.struts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.business.library.Library;
import com.ivata.groupware.business.library.item.LibraryItemDO;
import com.ivata.mask.Mask;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.validation.ValidationError;
import com.ivata.mask.validation.ValidationErrors;


/**
 * <p>This form is used both to display and to submit a library
 * item</p>
 *
 * @since 2003-02-19
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.3 $
 */
public class ItemForm extends LibraryForm {
    /**
     * <p>Version control comment</p>
     */
    private String comment;

    /**
     * <p>Button for deleting attached files to item.</p>
     */
    private String deleteFileButton;

    /**
     * <p>Page to include with extra fields for this document type, on
     * display.</p>
     */
    private String displayIncludePage;
    /**
     * <p>If in display mode, indicates the page number to currently
     * show.</p>
     */
    private int displayPage;

    /**
     * <p>Defines list of DriveFileDO with are attached with this
     * item (if any).</p>
     */
    private List fileList = new Vector();

    /**
     * <p>Contains all of the information about the current library
     * item</p>
     */
    private LibraryItemDO item;

    private Library library;

    /**
     * <p>Defines the page to link to. This varies depending on
     * whether the item is being submitted or displayed.</p>
     */
    private String linkPage;

    /**
     * <p>If non-<code>null</code>, indicates a new page should be
     * addes for DOCUMENT, new heading for MEETING, new FAQ category for
     * FAQs.</p>
     */
    private String newPage;

    /**
     * <p>TRICK
     *
     * storing index of button which was click in submiting page of
     * Meeting or Faq.
     * index is store in first field of array.
     *
     * for MEETING it's creating new AgendaPoin in heading with that
     * index.
     * for FAQ it's creating new Question in faq category with that
     * index.</p>
     */
    private String[] newPoint;

    /**
     * <p>Link to link the next page to, or the empty string if there is
     * no next
     * page.</p>
     */
    private String nextPageLink;

    /**
     * <p>Contains the links with appear to the top right of displayed
     * items.</p>
     */
    private String pageLinks;

    /**
     * <p>Link to link the previous page to, or the empty string if there
     * is no
     * previous page.</p>
     */
    private String previousPageLink;

    /**
     * <p>All Ids of selected files by user from list of attached files.
     * We need maped propertties.</p>
     */
    private Map selectedAttachedFilesIds;

    /**
     * <p>All Ids of selected files by user from list of new files.</p>
     */
    private String[] selectedNewFilesIds;

    /**
     * <p>Page to include with extra fields for this document type, on
     * submission</p>
     */
    private String submitIncludePage;

    /**
     * <p>Key to the localized text for the summary field (submit
     * mode).</p>
     */
    private String summaryPromptKey;

    /**
     * <p>Specifies the theme name for the document summary section.</p>
     */
    private String summaryThemeName;

    /**
     * <p>Key to the localized text for the summary window (submit
     * mode).</p>
     */
    private String summaryTitleKey;

    /**
     * <p>Specifies the theme name for the body of the document.</p>
     */
    private String themeName;

    /**
     * <p>In submit mode, stores all of the captions of the topics we are
     * allowed to see.</p>
     */
    private Map topicCaptions;

    /**
     * <p>In submit mode, stores all of the unique identifiers of the
     * topics we are allowed to see.</p>
     */
    private Set topicIds;

    /**
     * <p>In submit mode, stores all of the image filenames of the topics
     * we are allowed to see.</p>
     */
    private Map topicImages;

    /**
     * <p>Defines list of FileDO with are goint to attch with this
     * item.</p>
     */
    private List uploadingFileList;
    private Mask mask;
    /**
     *
     * @param library
     * @param maskParam
     *            Refer to {@link #getMask}.
     * @param baseClassParam
     *            Refer to {@link #getBaseClass}.
     */
    public ItemForm(final Library library, final MaskFactory maskFactory) {
        this.library = library;
        mask = maskFactory.getMask(LibraryItemDO.class);
    }

    /**
     * TODO
     *
     * @see com.ivata.mask.web.struts.MaskForm#clear()
     */
    protected void clear() {
        comment = null;
        deleteFileButton = null;
        displayIncludePage = null;
        displayPage = -1;
        // TODO
        fileList = new Vector();
        item = null;
        linkPage = null;
        newPage = null;
        newPoint = new String[]{  };
        nextPageLink = "";
        pageLinks = "";
        previousPageLink = "";
        selectedAttachedFilesIds = null;
        selectedNewFilesIds = null;
        submitIncludePage = null;
        summaryPromptKey = null;
        summaryThemeName = null;
        summaryTitleKey = null;
        themeName = null;
        topicCaptions = null;
        topicIds = null;
        topicImages = null;
        uploadingFileList = null;
    }

    /**
       <p>Version control comment</p>
       @return the comment

     */
    public final String getComment() {
        return comment;
    }

    /**
     * <p>Button for deleting attached files to item.</p>
     *
     * @return the current value of deleteFileButton.
     */
    public final String getDeleteFileButton() {
        return this.deleteFileButton;
    }

    /**
     * <p>Page to include with extra fields for this document type, on
     * display.</p>
     *
     * @return the current value of displayIncludePage.
     */
    public final String getDisplayIncludePage() {
        return displayIncludePage;
    }

    /**
     * <p>If in display mode, indicates the page number to currently
     * show.</p>
     *
     * @return the current value of displayPage.
     */
    public final int getDisplayPage() {
        return displayPage;
    }

    /**
     * <p>Defines list of files DO's with are attched with this
     * item.</p>
     *
     * @return the current value of fileList.
     */
    public List getFileList() {
        return this.fileList;
    }

    /**
     * <p>Contains all of the information about the current library
     * item</p>
     *
     * @return the current value of item.
     */
    public final LibraryItemDO getItem() {
        return item;
    }

    /**
     * <p>Defines the page to link to. This varies depending on
     * whether the item is being submitted or displayed.</p>
     *
     * @return the current value of linkPage.
     */
    public final String getLinkPage() {
        return linkPage;
    }

    /**
     * <p>If non-<code>null</code>, indicates a new page should be
     * addes.</p>
     *
     * @return the current value of newPage.
     */
    public final String getNewPage() {
        return newPage;
    }

    /**
     * <p> TRICK
     * storing index of button which was click in submiting page of
     * Meeting or Faq.
     * return first field of array, ther is a index of click button.
     *
     * for MEETING it's creating new AgendaPoin in heading with that
     * index.
     * for FAQ it's creating new Question in faq category with that
     * index.</p>
     *
     * @return the current index of newPoint
     */
    public final String getNewPoint(final int index) {
        // it's should return value of field from arrray with that index
        // look to setter
        return this.newPoint[0];
    }

    /**
     * <p>Link to link the next page to, or the empty string if there is
     * no next
     * page.</p>
     *
     * @return the current value of nextPageLink.
     */
    public final String getNextPageLink() {
        return nextPageLink;
    }

    /**
     * <p>Contains the links with appear to the top right of displayed
     * items.</p>
     *
     * @return the current value of pageLinks.
     */
    public final String getPageLinks() {
        return pageLinks;
    }

    /**
     * <p>Link to link the previous page to, or the empty string if there
     * is no
     * previous page.</p>
     *
     * @return the current value of previoustPageLink.
     */
    public final String getPreviousPageLink() {
        return previousPageLink;
    }

    /**
     * <p>Geting selected file ID from maped properties.</p>
     *
     * @param key - which checkBox is selected
     */
    public final Object getSelectedAttachedFilesIds(final String key) {
        return this.selectedAttachedFilesIds.get(key);
    }

    /**
     * <p>All Ids of selected files by user from list of new files.</p>
     *
     * @return the current value of selectedNewFilesIds.
     */
    public final String[] getSelectedNewFilesIds() {
        return this.selectedNewFilesIds;
    }

    /**
     * <p>Page to include with extra fields for this document type, on
     * submission</p>
     *
     * @return the current value of submitIncludePage.
     */
    public final String getSubmitIncludePage() {
        return submitIncludePage;
    }

    /**
     * <p>Key to the localized text for the summary field (submit
     * mode).</p>
     *
     * @return the current value of summaryPromptKey.
     */
    public final String getSummaryPromptKey() {
        return summaryPromptKey;
    }

    /**
     * <p>Specifies the theme name for the document summary section.</p>
     *
     * @return the current value of summaryThemeName.
     */
    public final String getSummaryThemeName() {
        return summaryThemeName;
    }

    /**
     * <p>Key to the localized text for the summary window (submit
     * mode).</p>
     *
     * @return the current value of summaryTitleKey.
     */
    public final String getSummaryTitleKey() {
        return summaryTitleKey;
    }

    /**
     * <p>Specifies the theme name for the body of the document.</p>
     *
     * @return the current value of themeName.
     */
    public final String getThemeName() {
        return themeName;
    }

    /**
     * <p>In submit mode, stores all of the captions of the topics we are
     * allowed to see.</p>
     *
     * @return the current value of topicCaptions.
     */
    public final Map getTopicCaptions() {
        return topicCaptions;
    }

    /**
     * <p>In submit mode, stores all of the unique identifiers of the
     * topics we are allowed to see.</p>
     *
     * @return the current value of topicIds.
     */
    public final Set getTopicIds() {
        return topicIds;
    }

    /**
     * <p>In submit mode, stores all of the image filenames of the topics
     * we are allowed to see.</p>
     *
     * @return the current value of topicImages.
     */
    public final Map getTopicImages() {
        return topicImages;
    }

    /**
     * <p>Defines list of files DO's with are goint to attch with this
     * item.</p>
     *
     * @return the current value of uploadingFileList.
     */
    public List getUploadingFileList() {
        return this.uploadingFileList;
    }

    /**
     * <p>Reset all bean properties to their default state.  This method
     * is called before the properties are repopulated by the controller
     * servlet.</p>
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     *
     */
    public void reset(final ActionMapping mapping,
            final HttpServletRequest request) {
        super.reset(mapping, request);
        setEdit(null);
        newPage = null;
        comment = null;

        // remember only index of click button of new Point or new Question
        newPoint = new String[1];

        if (this.selectedAttachedFilesIds == null) {
            this.selectedAttachedFilesIds = new HashMap();
        } else {
            this.selectedAttachedFilesIds.clear();
        }

        this.selectedNewFilesIds = new String[0];

        /*
                if (item != null) {
                    // make this one larger than it needs to be, in case a new
                    // category is added!
                    if (item.getType().equals(LibraryItemConstants.ITEM_MEETING)) {
                        newPoint = null;
                    } else if (item.getType().equals(LibraryItemConstants.ITEM_FAQ)) {
                        newPoint = new String[item.getFaqCategories().size() + 1];

                    } else {
                        newPoint = null;
                    }
                }
        */
    }

    /**
       <p>Version control comment</p>
       @param comment the comment

     */
    public final void setComment(final String comment) {
        this.comment = comment;
    }

    /**
     * <p>Button for deleting attached files to item.</p>
     *
     * @param deleteFileButton the new value of deleteFileButton.
     */
    public final void setDeleteFileButton(final String deleteFileButton) {
        this.deleteFileButton = deleteFileButton;
    }

    /**
     * <p>Page to include with extra fields for this document type, on
     * display.</p>
     *
     * @param displayIncludePage the new value of displayIncludePage.
     */
    public final void setDisplayIncludePage(final String displayIncludePage) {
        this.displayIncludePage = displayIncludePage;
    }

    /**
     * <p>If in display mode, indicates the page number to currently
     * show.</p>
     *
     * @param displayPage the new value of displayPage.
     */
    public final void setDisplayPage(final int displayPage) {
        this.displayPage = displayPage;
    }

    /**
     * <p>Defines list of files DO's with are attched with this
     * item.</p>
     *
     * @param fileList the new value of fileList.
     */
    public final void setFileList(final List fileList) {
        this.fileList = fileList;
    }

    /**
     * <p>Contains all of the information about the current library
     * item</p>
     *
     * @param item the new value of item.
     */
    public final void setItem(final LibraryItemDO item) {
        this.item = item;
    }

    /**
     * <p>Defines the page to link to. This varies depending on
     * whether the item is being submitted or displayed.</p>
     *
     * @param linkPage the new value of linkPage.
     */
    public final void setLinkPage(final String linkPage) {
        this.linkPage = linkPage;
    }

    /**
     * <p>If non-<code>null</code>, indicates a new page should be
     * addes.</p>
     *
     * @param newPage the new value of newPage.
     */
    public final void setNewPage(final String newPage) {
        this.newPage = newPage;
    }

    /**
     * <p>TRICK
     * storing index of button which was click in submiting page of
     * Meeting or Faq.
     * set first field of array
     *
     * for MEETING it's creating new AgendaPoin in heading with that
     * index.
     * for FAQ it's creating new Question in faq category with that
     * index.</p>
     *
     * @param index is the index of heading or faq category
     * @param value is the value of button
     */
    public final void setNewPoint(final int index,
            final String value) {
        this.newPoint[0] = String.valueOf(index);
    }

    /**
     * <p>Link to link the next page to, or the empty string if there is
     * no next
     * page.</p>
     *
     * @param nextPageLink the new value of nextPageLink.
     */
    public final void setNextPageLink(final String nextPageLink) {
        this.nextPageLink = nextPageLink;
    }

    /**
     * <p>Contains the links with appear to the top right of displayed
     * items.</p>
     *
     * @param pageLinks the new value of pageLinks.
     */
    public final void setPageLinks(final String pageLinks) {
        this.pageLinks = pageLinks;
    }

    /**
     * <p>Link to link the previous page to, or the empty string if there
     * is no
     * previous page.</p>
     *
     * @param previoustPageLink the new value of previoustPageLink.
     */
    public final void setPreviousPageLink(final String previousPageLink) {
        this.previousPageLink = previousPageLink;
    }

    /**
     * <p>Seting selected file ID to maped properties.</p>
     *
     * @param key - which checkBox is selected
     * @param value - value which is setTo haspMap with that key
     */
    public final void setSelectedAttachedFilesIds(final String key,
            final Object value) {
        this.selectedAttachedFilesIds.put(key, value);
    }

    /**
     * <p>All Ids of selected files by user from list of new files.</p>
     *
     * @param selectedNewFilesIds the new value of selectedNewFilesIds.
     */
    public final void setSelectedNewFilesIds(final String[] selectedNewFilesIds) {
        this.selectedNewFilesIds = selectedNewFilesIds;
    }

    /**
     * <p>Page to include with extra fields for this document type, on
     * submission</p>
     *
     * @param submitIncludePage the new value of submitIncludePage.
     */
    public final void setSubmitIncludePage(final String submitIncludePage) {
        this.submitIncludePage = submitIncludePage;
    }

    /**
     * <p>Key to the localized text for the summary field (submit
     * mode).</p>
     *
     * @param summaryPromptKey the new value of summaryPromptKey.
     */
    public final void setSummaryPromptKey(final String summaryPromptKey) {
        this.summaryPromptKey = summaryPromptKey;
    }

    /**
     * <p>Specifies the theme name for the document summary section.</p>
     *
     * @param summaryThemeName the new value of summaryThemeName.
     */
    public final void setSummaryThemeName(final String summaryThemeName) {
        this.summaryThemeName = summaryThemeName;
    }

    /**
     * <p>Key to the localized text for the summary window (submit
     * mode).</p>
     *
     * @param summaryTitleKey the new value of summaryTitleKey.
     */
    public final void setSummaryTitleKey(final String summaryTitleKey) {
        this.summaryTitleKey = summaryTitleKey;
    }

    /**
     * <p>Specifies the theme name for the body of the document.</p>
     *
     * @param themeName the new value of themeName.
     */
    public final void setThemeName(final String themeName) {
        this.themeName = themeName;
    }

    /**
     * <p>In submit mode, stores all of the captions of the topics we are
     * allowed to see.</p>
     *
     * @param topicCaptions the new value of topicCaptions.
     */
    public final void setTopicCaptions(final Map topicCaptions) {
        this.topicCaptions = topicCaptions;
    }

    /**
     * <p>In submit mode, stores all of the unique identifiers of the
     * topics we are allowed to see.</p>
     *
     * @param topicIds the new value of topicIds.
     */
    public final void setTopicIds(final Set topicIds) {
        this.topicIds = topicIds;
    }

    /**
     * <p>In submit mode, stores all of the image filenames of the topics
     * we are allowed to see.</p>
     *
     * @param topicImages the new value of topicImages.
     */
    public final void setTopicImages(final Map topicImages) {
        this.topicImages = topicImages;
    }

    /**
     * <p>Defines list of files DO's with are goint to attch with this
     * item.</p>
     *
     * @param uploadingFileList the new value of uploadingFileList.
     */
    public final void setUploadingFileList(final List uploadingFileList) {
        this.uploadingFileList = uploadingFileList;
    }

    /**
     * Validate the properties that have been set for this HTTP request,
     * and return an <code>ActionMessages</code> object that encapsulates
     * any
     * validation errors that have been found.  If no errors are found,
     * return <code>null</code> or an <code>ActionMessages</code> object
     * with
     * no recorded error messages.
     * <p>
     * The default ipmlementation performs no validation and returns
     * <code>null</code>.  Subclasses must override this method to provide
     * any validation they wish to perform.
     *
     * @param request The servlet request we are processing.
     * @param session The sessuib we are processing.
     * @see com.ivata.mask.web.struts.MaskForm#validate(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpSession)
     */
    public ValidationErrors validate(final HttpServletRequest request,
            final HttpSession session) {
        ValidationErrors errors = new ValidationErrors();

        // if there is no item yet, or clear was pressed, just get out
        if ((item == null) || !StringHandling.isNullOrEmpty(getClear())) {
            return null;
        }

        // if a topic was specified make sure it is valid
        if ((item.getTopic() == null)
                || (item.getTopic().getId().equals(new Integer(-1)))) {
            errors.add(new ValidationError(
                    "submit",
                    Library.BUNDLE_PATH,
                    mask.getField("topicId"),
                    "errors.library.item.submit.noTopic"));
        }

        // if there is no ok/cancel, just report the topic error
        if (StringHandling.isNullOrEmpty(getOk())) {
            return errors;
        }

        // if it gets here - ok was pressed. validate on the server side
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        ValidationErrors libraryErrors = library.validate(securitySession, item);
        if (libraryErrors != null) {
            errors.addAll(libraryErrors);
        }

        return errors;
    }
}

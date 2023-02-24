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
 * $Log: SubmitAction.java,v $
 * Revision 1.6  2005/04/30 13:04:14  colinmacleod
 * Fixes reverting id type from String to Integer.
 *
 * Revision 1.5  2005/04/29 02:48:16  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.4  2005/04/26 15:07:07  colinmacleod
 * Consolidated print JSP into display pages (eliminating need for separate print pages).
 * Renamed Faq to FAQ.
 *
 * Revision 1.3  2005/04/10 20:31:58  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:46  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:04  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.12  2004/12/31 18:27:44  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.11  2004/12/23 21:01:29  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.10  2004/11/12 18:19:15  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.9  2004/11/12 15:57:16  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.8  2004/11/03 15:31:51  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.7  2004/08/01 11:45:19  colinmacleod
 * Restructured search engine into separate subproject.
 *
 * Revision 1.6  2004/07/19 22:00:46  colinmacleod
 * Fixed pages for note.
 *
 * Revision 1.5  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.4  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.3  2004/03/21 20:30:34  colinmacleod
 * Changed session variable called mailSession to securityServerSession.
 *
 * Revision 1.2  2004/02/01 22:07:31  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:58:42  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.4  2004/01/12 14:01:03  jano
 * fixing bugs
 *
 * Revision 1.3  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.20  2003/09/05 07:19:42  peter
 * changes in drive methods
 * Revision 1.19  2003/08/26 15:16:29  jano
 * fixing bug with new item
 *
 * Revision 1.18  2003/08/20 08:35:08  peter
 * directory issues fixed
 *
 * Revision 1.17  2003/08/18 11:38:55  peter
 * findByPath replaced by findByParentIdName
 *
 * Revision 1.16  2003/07/11 15:08:12  peter
 * version control comment added
 *
 * Revision 1.15  2003/07/08 13:24:25  jano
 * we have library items in CVS
 *
 * Revision 1.14  2003/06/20 13:22:15  jano
 * we want deleteFile button in list of attached files
 *
 * Revision 1.13  2003/06/20 06:56:50  jano
 * fixing removing uploading files
 *
 * Revision 1.12  2003/06/03 05:08:00  peter
 * changes due to posibility to change filelist from display mode
 *
 * Revision 1.11  2003/05/22 10:07:03  jano
 * we have difrent directory, we are not using "item" but "drive" for uploading files
 *
 * Revision 1.10  2003/05/20 08:29:40  jano
 * maintaing attaching files to libray item
 *
 * Revision 1.9  2003/05/13 15:41:00  jano
 * new methods in DriveBean
 *
 * Revision 1.8  2003/04/14 07:14:19  peter
 * helpKey logic
 *
 * Revision 1.7  2003/03/12 14:14:48  jano
 * renaming newHeading -> newPoint
 * fixing problem arround that
 *
 * Revision 1.6  2003/03/04 00:25:58  colin
 * added type after clear to save for new form after clear button
 *
 * Revision 1.5  2003/03/03 20:56:19  colin
 * added checking for null meeting/category
 *
 * Revision 1.4  2003/03/03 19:06:16  colin
 * fixed changed keys to key suffces for summary field
 *
 * Revision 1.3  2003/02/28 10:32:14  colin
 * fixed bug when cancel is pressed in library submit
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.security.user.UserDO;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.business.library.Library;
import com.ivata.groupware.business.library.NotificationException;
import com.ivata.groupware.business.library.faq.FAQDO;
import com.ivata.groupware.business.library.faq.category.FAQCategoryDO;
import com.ivata.groupware.business.library.item.LibraryItemConstants;
import com.ivata.groupware.business.library.item.LibraryItemDO;
import com.ivata.groupware.business.library.page.PageDO;
import com.ivata.groupware.business.library.right.LibraryRights;
import com.ivata.groupware.business.library.topic.TopicDO;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.util.ThrowableHandling;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p><code>Action</code> invoked whenever a library item is
 * entered new, or changed.</p>
 *
 * @since 2003-02-22
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.6 $
 */
public class SubmitAction extends DisplayAction {
    private Library library;
    private LibraryRights libraryRights;

    /**
     * TODO
     * @param library
     * @param libraryRights
     * @param settings
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public SubmitAction(Library library, LibraryRights libraryRights,
            Settings settings, MaskFactory maskFactory,
            MaskAuthenticator authenticator) {
        super(library, settings, maskFactory, authenticator);
        this.library = library;
        this.libraryRights = libraryRights;
    }

    /**
     * <p>Called when the clear button is pressed, or after an ok or
     * delete button, where the session should be restored to its default
     * state.</p>
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
     */
    public void clear(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        // so we don't lose the type when clear is pressed
        request.setAttribute("type", ((ItemForm) form).getItem().getType());
        ItemForm itemForm = (ItemForm) form;
        itemForm.clear();
    }
    /**
     * <p>Overridden to TODO:.</p>
     *
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName current user name from session. .
     * @param settings valid, non-null settings from session.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     *
     *
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        ItemForm itemForm = (ItemForm) form;
        LibraryItemDO item = itemForm.getItem();

        // if there is no item yet, or we are told to make a new one, make a new
        // empty one
        Integer requestType = StringHandling.integerValue(request.getParameter(
                    "type"));

        if (requestType == null) {
            requestType = (Integer) request.getAttribute("type");
        }

        if ((requestType != null) || (item == null)) {
            itemForm.reset(mapping, request);
            itemForm.setPreview(null);
            itemForm.setSubmitIncludePage(null);
            itemForm.setItem(item = new LibraryItemDO());
        }

        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        UserDO user = securitySession.getUser();
        // if we are amending an existing item, check this is ok
        // we can only amend it if we are the owner or we were given the right to do so
        if ((item.getId() != null) &&
                !(libraryRights.canAmendInTopic(securitySession, item.getTopic().getId()) ||
                user.equals(item.getCreatedBy()))) {
            errors.add(Globals.ERROR_KEY,
                new ActionMessage("errors.library.item.amend.rights"));

            return "libraryIndex";
        }

        // default the item type to plain old document
        if (item.getType() == null) {
            if (requestType != null) {
                item.setType(requestType);
            } else {
                item.setType(LibraryItemConstants.ITEM_DOCUMENT);
            }
        }

        // if the item doesn't contain a meetingDO and it is meeting Item,
        // then try to find the DO from a request parameter
        Integer meetingId = StringHandling.integerValue(request.getParameter(
                    "meetingId"));

        // only note is different
        itemForm.setSummaryPromptKey("");

        if ((meetingId != null) ||
                item.getType().equals(LibraryItemConstants.ITEM_MEETING)) {

            /*
             * TODO: fix this via subclassing/events
            MeetingDO meeting;


            if (meetingId != null) {
                CalendarRemote calendar;

                if ((calendar = (CalendarRemote) session.getAttribute(
                                "calendar")) == null) {
                    try {
                        CalendarRemoteHome home = (CalendarRemoteHome) ApplicationServer.findRemoteHome("CalendarRemote",
                                CalendarRemoteHome.class);

                        calendar = home.create();
                        session.setAttribute("calendar", calendar);
                    } catch (SystemException e) {
                        throw new SystemException(e);
                    } catch (NamingException e) {
                        throw new SystemException(e);
                    } catch (CreateException e) {
                        throw new SystemException(e);
                    }
                }

                try {
                    meeting = calendar.findMeetingByPrimaryKey(meetingId);
                } catch (SystemException e) {
                    throw new SystemException(e);
                }

                itemForm.setItem(item = new LibraryItemDO());
                item.setType(LibraryItemConstants.ITEM_MEETING);
                item.setMeeting(meeting);
                item.setSummary(meeting.getEvent().getDescription());
                item.setTitle(meeting.getEvent().getSubject());
            } else {
                meeting = item.getMeeting();
            }

            if (meeting == null) {
                item.setMeeting(meeting = new MeetingDO());
            }

            // strip out categories which are empty
            stripEmptyMeetingCategories(item, request);

            // if the new arrays are empty, or the add button was pressed
            // add a new category
            if ((meeting.getCategories().size() == 0) ||
                    !StringHandling.isNullOrEmpty(itemForm.getNewPage())) {
                MessageResources calendarMessages = getResources(request,
                        "calendar");

                meeting.addCategory(calendarMessages.getMessage(
                        (Locale) session.getAttribute(Globals.LOCALE_KEY),
                        "default.category",
                        String.valueOf(meeting.getCategories().size() + 1)),
                    new Vector(), new Vector());
            }
             */

            // see if we should add new agenda points to any of the categories
            // parameter Integer(0) is a TRICK
            String newHeading = itemForm.getNewPoint(0);
            int index = 0;

            /* TODO: fix this via subclassing/events
            for (Iterator i = meeting.getCategories().iterator(); i.hasNext();
                    ++index) {
                i.next();

                // if we have more categories as AgendaPoints, Minutes -> create new Vector
                while (meeting.getAgendaPoints().size() <= index) {
                    meeting.getAgendaPoints().add(new Vector());
                }

                while (meeting.getMinuteTexts().size() <= index) {
                    meeting.getMinuteTexts().add(new Vector());
                }

                // if you pressed the button...
                if (((newHeading != null) &&
                        (index == (Integer.valueOf(newHeading)).intValue())) ||
                        (((Vector) meeting.getAgendaPoints().get(index)).size() == 0)) {
                    ((Vector) meeting.getAgendaPoints().get(index)).add("");
                    ((Vector) meeting.getMinuteTexts().get(index)).add("");
                }
            }
            */
            // meetings have their own include
            itemForm.setSubmitIncludePage("/library/submitMeeting.jsp");
            itemForm.setSummaryTitleKey("submit.title.itemDetails.meeting");
            itemForm.setHelpKey("library.submit.meeting");


            // TODO:
            throw new RuntimeException("ERROR: illegal dependency on calendar from library");


            // frequently asked question stuff here
        } else if (item.getType().equals(LibraryItemConstants.ITEM_FAQ)) {
            // strip out pages which are empty
            stripEmptyFaqCategories(item, request);

            // if the new arrays are empty, or the add button was pressed
            // add a new category
            if ((item.getFAQCategories().size() == 0) ||
                    !StringHandling.isNullOrEmpty(itemForm.getNewPage())) {
                FAQCategoryDO faqCategory = new FAQCategoryDO();
                faqCategory.setFAQs(new Vector());
                item.getFAQCategories().add(faqCategory);
            }

            // see if we should add questions to any of the categories
            // parameter Integer(0) is a TRICK
            String newHeading = itemForm.getNewPoint(0);
            int index = 0;

            for (Iterator i = item.getFAQCategories().iterator(); i.hasNext();
                    ++index) {
                FAQCategoryDO faqCategory = (FAQCategoryDO) i.next();

                // if you pressed the button...
                if (((newHeading != null) &&
                        (index == (Integer.valueOf(newHeading)).intValue())) ||
                        (faqCategory.getFAQs().size() == 0)) {
                    faqCategory.getFAQs().add(new FAQDO());
                }
            }

            // faqs have their own include
            itemForm.setSubmitIncludePage("/library/submitFAQ.jsp");
            itemForm.setSummaryTitleKey("submit.title.itemDetails.faq");
            itemForm.setHelpKey("library.submit.faq");

            // Normal document stuff here
        } else {
            // strip out pages which are empty
            stripEmptyPages(item, request);

            // notes don't have an include page - only documents
            if (item.getType().equals(LibraryItemConstants.ITEM_NOTE)) {
                itemForm.setSummaryTitleKey("submit.title.itemDetails.note");
                itemForm.setSummaryPromptKey("note");
                itemForm.setHelpKey("library.submit.note");
            } else {
                itemForm.setSubmitIncludePage("/library/submitDocument.jsp");
                itemForm.setSummaryTitleKey("submit.title.itemDetails.document");
                itemForm.setHelpKey("library.submit.document");

                // make sure there is at least one page - unless this is a note
                if ((item.getPages().size() == 0) ||
                        !StringHandling.isNullOrEmpty(itemForm.getNewPage())) {
                    item.getPages().add(new PageDO());
                }
            }
        }

        // set up display information based on the item type
        chooseItemType(itemForm);

        // set the display page for the preview
        setPageNumber("/library/submit.action", request, response, itemForm);

/*TODO        try {
            // setUp uploadingFileList and attachedFileList
            itemForm.setUploadingFileList((java.util.Vector) drive.getUploads(
                    "drive", userName));

            if (item.getId() == null) {
                itemForm.setFileList(new Vector());
            } else {
                itemForm.setFileList((java.util.Vector) drive.findFilesByPath(
                        "/library/" + item.getId().toString(), userName));
            }

            // is user selected any file to remove and he did click on deleteFile button
            if (!StringHandling.isNullOrEmpty(itemForm.getDeleteFileButton())) {
                //remove uploads
                if (itemForm.getSelectedNewFilesIds().length > 0) {
                    itemForm.setUploadingFileList(new Vector(
                            drive.removeUploads(Arrays.asList(
                                    itemForm.getSelectedNewFilesIds()),
                                "drive", userName)));
                }

                //remove attached files
                for (Iterator i = itemForm.getFileList().iterator();
                        i.hasNext();) {
                    DriveFileDO tmpFile = (DriveFileDO) i.next();

                    if (itemForm.getSelectedAttachedFilesIds(
                                tmpFile.getId().toString()) != null) {
                        drive.removeFile(tmpFile.getId(), userName);
                    }
                }

                itemForm.setDeleteFileButton("");
            }

            // change the drive files only when it's an existing item
            if (item.getId() != null) {
                // add files to repository
                java.util.Vector uploadedFileNames = new java.util.Vector();

                try {
                    DirectoryDO attachmentDirectoryDO = drive.findDirectoryByParentIdName(DirectoryConstants.LIBRARY_DIRECTORY,
                            item.getId().toString(), userName);

                    for (Iterator i = itemForm.getUploadingFileList().iterator();
                            i.hasNext();) {
                        FileDO fileDO = (FileDO) i.next();

                        // carefull, maybe we did remove this new file
                        if ((Arrays.asList(itemForm.getSelectedNewFilesIds())).contains(
                                    fileDO.getFileName())) {
                            continue;
                        }

                        DriveFileDO driveFileDO = new DriveFileDO();

                        driveFileDO.setFileName(fileDO.getFileName());
                        driveFileDO.setDirectoryId(attachmentDirectoryDO.getId());
                        driveFileDO.setHeadRevision(new FileRevisionDO());
                        driveFileDO.getHeadRevision().setComment(fileDO.getComment());
                        driveFileDO.setCreatedBy(userName);
                        driveFileDO.setMimeType(fileDO.getMimeType());
                        driveFileDO.setSize(fileDO.getSize());
                        uploadedFileNames.add(fileDO.getFileName());

                        drive.commitFile(driveFileDO, userName, null);
                    }
                } catch (SystemException e) {
                    throw new SystemException(e);
                }

                // we add files to CVS -> to item, so ...
                // remove uploaded files
                try {
                    itemForm.setUploadingFileList(new Vector(
                            drive.removeUploads(uploadedFileNames, "drive",
                                userName)));
                } catch (SystemException e) {
                    throw new SystemException(e);
                }

                // no wonder that we request back the current attachment list....
                itemForm.setFileList((java.util.Vector) drive.findFilesByPath(
                        "/library/" + item.getId().toString(), userName));
            }
        } catch (SystemException e) {
            throw new SystemException(e);
        }
*/
        // get all the topics we can enter items in
        List topics = library.findTopics(securitySession);
        Iterator topicIterator = topics.iterator();
        Map topicCaptions = new HashMap();
        Map topicImages = new HashMap();
        while (topicIterator.hasNext()) {
            TopicDO topic = (TopicDO) topicIterator.next();
            Integer id = topic.getId();
            topicCaptions.put(id, topic.getCaption());
            topicImages.put(id, topic.getImage());
        }

        itemForm.setTopicCaptions(topicCaptions);
        Set topicIds = topicCaptions.keySet();
        itemForm.setTopicIds(topicIds);
        itemForm.setTopicImages(topicImages);

        // if there are no topics for you, return to the index
        if (topicIds.size() == 0) {
            errors.add(Globals.ERROR_KEY,
                new ActionMessage("errors.library.item.submit.noTopicRights"));

            return "libraryIndex";
        }

        return null;
    }

    /**
     * <p>This method is called if the ok or apply buttons are
     * pressed.</p>
     *
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     * @param defaultForward Refer to
     *            {@link com.ivata.mask.web.struts.MaskAction#onConfirm}.
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     *
     */
    public String onConfirm(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session,
            final String defaultForward) throws SystemException {
        ItemForm itemForm = (ItemForm) form;
        LibraryItemDO item = itemForm.getItem();
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");

        if (item.getType().equals(LibraryItemConstants.ITEM_MEETING)) {
            stripEmptyMeetingCategories(item, request);
        } else if (item.getType().equals(LibraryItemConstants.ITEM_FAQ)) {
            stripEmptyFaqCategories(item, request);
        } else {
            stripEmptyPages(item, request);
        }

        // insert a new item
        try {
            if (item.getId() == null) {
                item = library.addItem(securitySession, item,
                        itemForm.getComment());

                // update an existing item
            } else {
                library.amendItem(securitySession, item,
                    itemForm.getComment());
            }
        } catch(NotificationException e) {
            // handle any mail exceptions separately
            Throwable cause = ThrowableHandling.getCause(e);
            // this will get picked up by the library notice board page
            session.setAttribute("libraryNotificationException", e);
        }
/*TODO
        // now maintain files
        // add files to repository
        java.util.Vector uploadedFileNames = new java.util.Vector();

        try {
            DirectoryDO attachmentDirectoryDO = drive.findDirectoryByParentIdName(DirectoryConstants.LIBRARY_DIRECTORY,
                    item.getId().toString(), userName);

            for (Iterator i = itemForm.getUploadingFileList().iterator();
                    i.hasNext();) {
                FileDO fileDO = (FileDO) i.next();
                DriveFileDO driveFileDO = new DriveFileDO();

                driveFileDO.setFileName(fileDO.getFileName());
                driveFileDO.setDirectoryId(attachmentDirectoryDO.getId());
                driveFileDO.setHeadRevision(new FileRevisionDO());
                driveFileDO.getHeadRevision().setComment(fileDO.getComment());
                driveFileDO.setCreatedBy(userName);
                driveFileDO.setMimeType(fileDO.getMimeType());
                driveFileDO.setSize(fileDO.getSize());
                uploadedFileNames.add(fileDO.getFileName());

                drive.commitFile(driveFileDO, userName, null);
            }

            // remove uploaded files
            drive.removeUploads(uploadedFileNames, "drive", userName);
        } catch (SystemException e) {
            throw new SystemException(e);
        }
*/
        return defaultForward;
    }

    /**
     * <p>Helper method. Strip empty FAQ categories from the item.</p>
     *
     * @param item item to remove empty pages from.
     * @param request valid request.
     */
    private void stripEmptyFaqCategories(final LibraryItemDO item,
            final HttpServletRequest request) {
        List newFaqCategories = new Vector();

        if (item.getFAQCategories() != null) {
            for (Iterator i = item.getFAQCategories().iterator(); i.hasNext();) {
                FAQCategoryDO faqCategory = (FAQCategoryDO) i.next();
                List newFaqs = new Vector();
                Iterator faqIterator = faqCategory.getFAQs().iterator();

                while (faqIterator.hasNext()) {
                    FAQDO faq = (FAQDO) faqIterator.next();
                    String question = (String) faq.getQuestion();
                    String answer = (String) faq.getAnswer();

                    if (!StringHandling.isNullOrEmpty(question) ||
                            !StringHandling.isNullOrEmpty(answer)) {
                        newFaqs.add(faq);
                    }
                }

                if ((newFaqs.size() != 0) ||
                        !StringHandling.isNullOrEmpty(faqCategory.getName())) {
                    faqCategory.setFAQs(newFaqs);
                    newFaqCategories.add(faqCategory);
                }
            }
        }

        item.setFAQCategories(newFaqCategories);
    }

    /**
     * <p>Helper method. Strip empty meeting categories from the item.</p>
     *
     * @param item item to remove empty pages from.
     * @param request valid request.
     */
    private void stripEmptyMeetingCategories(final LibraryItemDO item,
            final HttpServletRequest request) {
        Vector newCategories = new Vector();
        Vector newAgendaPoints = new Vector();
        Vector newMinuteTexts = new Vector();
        MessageResources calendarMessages = this.getResources(request,
                "calendar");
        int count = 0;

        /*
         * TODO: fix this via subclassing/fire events
        MeetingDO meeting = item.getMeeting();
        for (int i = 0;
                (meeting.getCategories() != null) &&
                (i < meeting.getCategories().size()); ++i) {
            String defaultName = calendarMessages.getMessage((Locale) request.getSession()
                                                                             .getAttribute(Globals.LOCALE_KEY),
                    "default.category", new Integer(++count));
            String category = meeting.getCategory(i);
            Vector agendaPoints = (Vector) meeting.getAgendaPoints().get(i);
            Vector minuteTexts = (Vector) meeting.getMinuteTexts().get(i);

            if (StringHandling.isNullOrEmpty(category)) {
                // see if there are points or minutes
                // go throw all points because if there is not text in input boxes -> there is a empty String in Vector :-(((
                // checking !!!! ONLY !!!!  agenda points
                boolean empty = true;

                for (Iterator api = agendaPoints.iterator(); api.hasNext();) {
                    if (!StringHandling.isNullOrEmpty((String) api.next())) {
                        empty = false;

                        break;
                    }
                }

                if (!empty) {
                    // default the text to heading + number
                    category = defaultName;
                } else {
                    --count;

                    continue;
                }
            }

            // only add if it is not-empty or was set above
            Vector newSubAgendaPoints = new Vector();
            Vector newSubMinuteTexts = new Vector();
            Iterator minuteTextsIterator = minuteTexts.iterator();

            for (Iterator agendaPointsIterator = agendaPoints.iterator();
                    agendaPointsIterator.hasNext();) {
                String agendaPoint = (String) agendaPointsIterator.next();
                String minuteText = (String) minuteTextsIterator.next();

                if (!StringHandling.isNullOrEmpty(agendaPoint) ||
                        !StringHandling.isNullOrEmpty(minuteText)) {
                    newSubAgendaPoints.add(StringHandling.getNotNull(
                            agendaPoint));
                    newSubMinuteTexts.add(StringHandling.getNotNull(minuteText));
                }
            }

            newCategories.add(category);
            newAgendaPoints.add(newSubAgendaPoints);
            newMinuteTexts.add(newSubMinuteTexts);
        }

        meeting.setCategories(newCategories);
        meeting.setAgendaPoints(newAgendaPoints);
        meeting.setMinuteTexts(newMinuteTexts);
         */
    }

    /**
     * <p>Helper method. Strip empty pages from the item.</p>
     *
     * @param item item to remove empty pages from.
     * @param request valid request.
     */
    private void stripEmptyPages(final LibraryItemDO item,
            final HttpServletRequest request) {
        List newPages = new Vector();

        if (item.getPages() != null) {
            for (Iterator i = item.getPages().iterator(); i.hasNext();) {
                PageDO page = (PageDO) i.next();
                String pageText = page.getText();

                if (pageText.trim().length() > 0) {
                    newPages.add(page);
                }
            }
        }
        item.setPages(newPages);

    }
}

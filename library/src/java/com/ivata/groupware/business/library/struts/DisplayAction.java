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
 * $Log: DisplayAction.java,v $
 * Revision 1.8  2005/04/30 13:04:13  colinmacleod
 * Fixes reverting id type from String to Integer.
 *
 * Revision 1.7  2005/04/29 02:48:16  colinmacleod
 * Data bugfixes.
 * Changed primary key back to Integer.
 *
 * Revision 1.6  2005/04/28 18:47:07  colinmacleod
 * Fixed XHMTL, styles and resin compatibility.
 * Added support for URL rewriting.
 *
 * Revision 1.5  2005/04/26 15:07:07  colinmacleod
 * Consolidated print JSP into display pages (eliminating need for separate print pages).
 * Renamed Faq to FAQ.
 *
 * Revision 1.4  2005/04/22 09:53:33  colinmacleod
 * Removed table surrounding the page
 * links.
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
 * Revision 1.7  2004/11/12 15:57:16  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.6  2004/11/03 15:31:51  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.5  2004/08/01 11:45:19  colinmacleod
 * Restructured search engine into separate subproject.
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
 * Revision 1.5  2004/01/20 15:12:13  jano
 * fixing problems with new sslext
 *
 * Revision 1.4  2004/01/19 21:13:14  colin
 * Removed minutes for ivata groupware v0.9
 *
 * Revision 1.3  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.14  2003/09/05 07:19:42  peter
 * changes in drive methods
 * Revision 1.13  2003/08/20 08:35:08  peter
 * directory issues fixed
 *
 * Revision 1.12  2003/08/18 11:38:55  peter
 * findByPath replaced by findByParentIdName
 *
 * Revision 1.11  2003/07/08 13:24:25  jano
 * we have library items in CVS
 *
 * Revision 1.10  2003/06/30 13:07:26  jano
 * we want new link in LinksOfpage
 *
 * Revision 1.9  2003/06/20 13:22:15  jano
 * we want deleteFile button in list of attached files
 *
 * Revision 1.8  2003/06/03 05:08:00  peter
 * changes due to posibility to change filelist from display mode
 *
 * Revision 1.7  2003/05/20 08:29:40  jano
 * maintaing attaching files to libray item
 *
 * Revision 1.6  2003/05/07 14:01:59  jano
 * we want findFilesByPath
 *
 * Revision 1.5  2003/03/27 15:40:46  jano
 * read pageInt from form befor seting to lastPage or 0
 *
 * Revision 1.4  2003/03/04 16:01:52  colin
 * fixed page for print.jsp
 *
 * Revision 1.3  2003/02/28 10:52:22  colin
 * when edit is pressed, now forwards to SubmitAction (was submit.jsp)
 *
 * Revision 1.2  2003/02/28 07:30:22  colin
 * implemented editing/displaying of faqs & notes
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.struts;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.MessageResources;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.business.library.Library;
import com.ivata.groupware.business.library.faq.category.FAQCategoryDO;
import com.ivata.groupware.business.library.item.LibraryItemConstants;
import com.ivata.groupware.business.library.item.LibraryItemDO;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.RewriteHandling;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p><code>Action</code> invoked whenever a library item is
 * displayed.</p>
 *
 * @since 2003-02-18
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.8 $
 */
public class DisplayAction extends MaskAction {
    private Library library;
    private Settings settings;

    /**
     * TODO
     * @param library
     * @param settings
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public DisplayAction(Library library, Settings settings,
            MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.library = library;
        this.settings = settings;
    }

    /**
     * <p>Set information to display the item type correcly.</p>
     *
     * @param itemForm the fom which should be set up fo the given
     * type.
     */
    public void chooseItemType(final ItemForm itemForm) {
        LibraryItemDO item = itemForm.getItem();

        // find out which page we're on (starting with 0)
        itemForm.setDeleteKey("display.alert.delete");
        Integer itemType = item.getType();
        assert (itemType != null);

        if (itemType.equals(LibraryItemConstants.ITEM_MEETING)) {
            itemForm.setThemeName("meeting");
            itemForm.setSummaryThemeName("meetingSummary");
            itemForm.setDeleteKey("display.alert.delete.isMeeting");
            itemForm.setDisplayIncludePage("/library/displayMeeting.jsp");
        } else if (itemType.equals(LibraryItemConstants.ITEM_NOTE)) {
            itemForm.setThemeName("note");
            itemForm.setSummaryThemeName("note");
            itemForm.setDisplayIncludePage(null);
            itemForm.setDisplayIncludePage(null);
        } else if (itemType.equals(LibraryItemConstants.ITEM_FAQ)) {
            itemForm.setDisplayIncludePage("/library/displayFAQ.jsp");
            itemForm.setThemeName("fAQ");
            itemForm.setSummaryThemeName("fAQ");
            itemForm.setDisplayIncludePage("/library/displayFAQ.jsp");
        } else {
            itemForm.setDisplayIncludePage("/library/displayMeeting.jsp");
            itemForm.setThemeName("document");
            itemForm.setSummaryThemeName("documentSummary");
            itemForm.setDisplayIncludePage("/library/displayDocument.jsp");
        }
    }
    /**
     * <p>Overridden to proved flow for <code>onEdit</code> and
     * <code>onPreview</code>.</p>
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
        Integer requestId = StringHandling.integerValue(request.getParameter(
                    "id"));
        Integer requestMeetingId = StringHandling.integerValue(request.getParameter(
                    "meetingId"));
        MessageResources resources = getResources(request);
        MessageResources libraryResources = getResources(request, "library");
        Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);

        // used in computeURL
        JspFactory factory = JspFactory.getDefaultFactory();
        PageContext pageContext = factory.getPageContext(getServlet(), request,
                response, "", true, 512, true);

        // if there is an id in the request, that means find a new item
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");
        if (requestId != null) {
            itemForm.setItem(item = library.findItemByPrimaryKey(securitySession,
                    requestId));
            itemForm.setPreview(null);
            itemForm.setEdit(null);

            // otherwise, if there is a meeting id in the request, find the item
            // by the meeting id
/*            } else if (requestMeetingId != null) {
            itemForm.setItem(item = library.findItemByMeetingId(
                        requestMeetingId));
            itemForm.setPreview(null);
            itemForm.setEdit(null);
*/
        }

/*TODO
            DirectoryDO attachmentDirectoryDO = drive.findDirectoryByParentIdName(DirectoryConstants.LIBRARY_DIRECTORY,
                    itemForm.getItem().getId().toString(), userName);

            // the list of already attached files
            itemForm.setFileList((java.util.Vector) drive.findFilesByPath(
                    "/library/" + item.getId().toString(), userName));

            //list of new uploads
            Vector uploadList = (java.util.Vector) drive.getUploads("drive",
                    userName);

            itemForm.setUploadingFileList(uploadList);

            // is user selected any file to remove and he did click on deleteFile button
            if (!StringHandling.isNullOrEmpty(itemForm.getDeleteFileButton())) {
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

            // attach is a flag set by javaScript, when something attachment
            // related happened
            if (!StringHandling.isNullOrEmpty(request.getParameter("attach"))) {
                // if there are some uploads, commit them
                if (!uploadList.isEmpty()) {
                    Vector uploadedFileNames = new Vector();

                    for (Iterator i = uploadList.iterator(); i.hasNext();) {
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

                        try {
                            drive.commitFile(driveFileDO, userName, null);
                        } catch (RemoteException e) {
                            throw new SystemException(e);
                        }
                    }

                    // remove uploaded files
                    try {
                        itemForm.setUploadingFileList(new Vector(
                                drive.removeUploads(uploadedFileNames, "drive",
                                    userName)));
                    } catch (RemoteException e) {
                        throw new SystemException(e);
                    }
                }

                // no wonder that we request back the current attachment list....
                itemForm.setFileList((java.util.Vector) drive.findFilesByPath(
                        "/library/" + item.getId().toString(), userName));
            }
*/

        // if edit was pressed, forward to the submit
        if (!StringHandling.isNullOrEmpty(itemForm.getEdit())) {
            return "librarySubmitAction";
        }

        chooseItemType(itemForm);
        setPageNumber("/library/display.action", request, response, itemForm);

        // add a printer to the page links, if we're not in submit
        HashMap printParameters = new HashMap();
        TagUtils tagUtils = TagUtils.getInstance();

        printParameters.put("id", item.getId().toString());
        printParameters.put("print", "true");

        String pageLink1 = "";
        String pageLink2 = "";

        try {
            pageLink1 = tagUtils.computeURL(pageContext, null, null,
                    "/library/display.action", null, null, printParameters,
                    null, true);
// TODO             pageLink2 = RequestUtils.computeURL(pageContext, null, null,
//                    "/library/downLoad.jsp", null, printParameters, null, true);
        } catch (MalformedURLException e) {
            throw new SystemException(e);
        }

        StringBuffer newLinks = new StringBuffer();
        if (request.getParameter("print") == null) {
            newLinks.append(itemForm.getPageLinks());
            newLinks.append("<a href='");
            newLinks.append(pageLink1);
            newLinks.append("' target='_blank'><img class='printer' src='");
            newLinks.append(RewriteHandling.getContextPath(request));
            newLinks.append("/library/images/printer.gif' border='0' alt='");
            newLinks.append(libraryResources.getMessage(locale,
                    "displayItem.label.print"));
            newLinks.append("' title='");
            newLinks.append(libraryResources.getMessage(locale,
                    "displayItem.label.print"));
            newLinks.append("' width='32' height='32'/></a>");
/* TODO
        newLinks.append(
            "<td><nobr><img src='/images/empty.gif' width='20' height='1'/><a href='");
        newLinks.append(pageLink2);
        newLinks.append("' target='_blank'><img src='");
        newLinks.append(RewriteHandling.getContextPath(request));
        newLinks.append("/library/images/downloadHTML.gif' border='0' alt='");
        newLinks.append(libraryResources.getMessage(locale,
                "displayItem.label.download"));
        newLinks.append("' title='");
        newLinks.append(libraryResources.getMessage(locale,
                "displayItem.label.download"));
        newLinks.append(
            "' width='32' height='32'/></a></nobr></td>");
*/
        }
        itemForm.setPageLinks(newLinks.toString());

        return null;
    }

    /**
     * <p>This method is called if the delete (confirm, not warn) button
     * is pressed.</p>
     * @param mapping current action mapping from <em>Struts</em> config.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
     * @param log valid logging object to write messages to.
     * @param userName valid, non-null user name from session.
     * @param settings valid, non-null settings from session.
     *
     * @exception SystemException if there is any problem which
     * prevents processing. It will result in the webapp being forwarded
     * to
     * the standard error page.
     * @return this method returns the string used to identify the correct
     * <em>Struts</em> <code>ActionForward</code> which should follow this
     * page, or <code>null</code> if it should return to the input.
     *
     */
    public String onDelete(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session, final String defaultForward) throws SystemException {
        LibraryItemDO item = ((ItemForm) form).getItem();
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");

        // remove library item
        library.removeItem(securitySession, item);
/*TODO
        // remove files
        for (Iterator i = ((ItemForm) form).getFileList().iterator();
                i.hasNext();) {
            drive.removeFile(((DriveFileDO) i.next()).getId(), userName);
        }
*/

        return "libraryIndex";
    }

    /**
     * <p>Implementation of
     * <code>setPageNumber</code> for document types.</p>
     *
     * @pageContext used to create links.
     * @param linkPage the page to link the page numbers to.
     * @param request current request to check for a 'page' parameter
     * and to create links.
     * @param itemForm form to set page number <code>displayPage</code>.
     * @throws SystemException if there is any exception creating
     * the <code>URL</code>s.
     * @see setPageNumber
     */
    protected void setDocumentPageNumber(final PageContext pageContext,
            final String linkPage,
            final HttpServletRequest request,
            final ItemForm itemForm)
        throws SystemException {
        int pageInt = itemForm.getDisplayPage();
        LibraryItemDO item = itemForm.getItem();
        MessageResources libraryResources = getResources(request, "library");
        Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
        StringBuffer links = new StringBuffer();

        if (item.getPages().size() > 1) {
            HashMap pageLinkParameters = new HashMap();
            int numberOfPages = item.getPages().size();

            for (int i = 0; i < numberOfPages; ++i) {
                // don't link to the current page; just show it
                if (i == pageInt) {
                    links.append("&nbsp;");
                    links.append(i + 1);
                } else {
                    pageLinkParameters.put("page", new Integer(i + 1).toString());

                    String pageLink;

                    try {
                        pageLink = TagUtils.getInstance().computeURL(pageContext, null,
                                null, linkPage, null, null, pageLinkParameters, null, true);
                    } catch (MalformedURLException e) {
                        throw new SystemException(e);
                    }

                    links.append("&nbsp;<a href='");
                    links.append(pageLink);
                    links.append("'>");
                    links.append(i + 1);
                    links.append("</a>");
                }
            }
        }

        itemForm.setPageLinks(links.toString());
    }

    /**
     * <p>Implementation of
     * <code>setPageNumber</code> for FAQ types.</p>
     *
     * @pageContext used to create links.
     * @param linkPage the page to link the page numbers to.
     * @param request current request to check for a 'page' parameter
     * and to create links.
     * @param itemForm form to set page number <code>displayPage</code>.
     * @throws SystemException if there is any exception creating
     * the <code>URL</code>s.
     * @see setPageNumber
     */
    protected void setFaqPageNumber(final PageContext pageContext,
            final String linkPage,
            final HttpServletRequest request,
            final ItemForm itemForm)
        throws SystemException {
        int pageInt = itemForm.getDisplayPage();
        LibraryItemDO item = itemForm.getItem();
        MessageResources libraryResources = getResources(request, "library");
        Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);

        // always show the contents
        // in this case, don't show a link as the contents is already displayed
        String link;
        HashMap linkParameters = new HashMap();
        StringBuffer links = new StringBuffer();

        // first put out the agenda
        if (pageInt == 0) {
            links = new StringBuffer(libraryResources.getMessage(locale,
                        "displayItem.label.contents"));
        } else {
            linkParameters.put("page", "0");

            try {
                link = TagUtils.getInstance().computeURL(pageContext, null, null,
                        linkPage, null, null, linkParameters, null, true);
            } catch (MalformedURLException e) {
                throw new SystemException(e);
            }

            links = new StringBuffer("<a href='");
            links.append(link);
            links.append("'>");
            links.append(libraryResources.getMessage(locale,
                    "displayItem.label.contents"));
            links.append("</a>");
        }

        // now show each category numbered from 1
        int categoryLinkNumber = 0;

        for (Iterator i = item.getFAQCategories().iterator(); i.hasNext();) {
            FAQCategoryDO category = (FAQCategoryDO) i.next();

            // don't link to the current page; just show it
            if (++categoryLinkNumber == itemForm.getDisplayPage()) {
                links.append("&nbsp;");
                links.append(categoryLinkNumber);
            } else {
                linkParameters.put("page",
                    new Integer(categoryLinkNumber).toString());

                try {
                    link = TagUtils.getInstance().computeURL(pageContext, null, null,
                            linkPage, null, null, linkParameters, null, true);
                } catch (MalformedURLException e) {
                    throw new SystemException(e);
                }

                links.append("&nbsp;<a href='");
                links.append(link);
                links.append("' title='");
                links.append(category.getName());
                links.append("'>");
                links.append(categoryLinkNumber);
                links.append("</a>");
            }
        }

        itemForm.setPageLinks(links.toString());
    }

    /**
     * <p>Implementation of
     * <code>setPageNumber</code> for meeting types.</p>
     *
     * @pageContext used to create links.
     * @param linkPage the page to link the page numbers to.
     * @param request current request to check for a 'page' parameter
     * and to create links.
     * @param itemForm form to set page number <code>displayPage</code>.
     * @throws SystemException if there is any exception creating
     * the <code>URL</code>s.
     * @see setPageNumber
     */
    protected void setMeetingPageNumber(final PageContext pageContext,
            final String linkPage,
            final HttpServletRequest request,
            final ItemForm itemForm)
        throws SystemException {
        int pageInt = itemForm.getDisplayPage();
        LibraryItemDO item = itemForm.getItem();

        MessageResources libraryResources = getResources(request, "library");
        Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);

        // always show the agenda
        // in this case, don't show a link as the agenda is already displayed
        String agendaLink;
        HashMap agendaLinkParameters = new HashMap();
        StringBuffer links = new StringBuffer();

        // first put out the agenda
        if (pageInt == 0) {
            links = new StringBuffer(libraryResources.getMessage(locale,
                        "displayItem.label.agenda"));
        } else {
            agendaLinkParameters.put("page", "0");

            try {
                agendaLink = TagUtils.getInstance().computeURL(pageContext, null,
                        null, linkPage, null, null, agendaLinkParameters, null,
                        true);
            } catch (MalformedURLException e) {
                throw new SystemException(e);
            }

            links = new StringBuffer("<a href='");
            links.append(agendaLink);
            links.append("'>");
            links.append(libraryResources.getMessage(locale,
                    "displayItem.label.agenda"));
            links.append("</a>");
        }

        // now show each category numbered from 1
        int categoryLinkNumber = 0;

        /**
         * TODO: we should subclass/fire events to get around this
        MeetingDO meeting = item.getMeeting();
        for (Iterator i = meeting.getCategories().iterator(); i.hasNext();) {
            String category = (String) i.next();

            // don't link to the current page; just show it
            if (++categoryLinkNumber == itemForm.getDisplayPage()) {
                links.append("&nbsp;");
                links.append(categoryLinkNumber);
            } else {
                agendaLinkParameters.put("page",
                    new Integer(categoryLinkNumber).toString());

                try {
                    agendaLink = RequestUtils.computeURL(pageContext, null,
                            null, linkPage, agendaLinkParameters, null, false,
                            true);
                } catch (MalformedURLException e) {
                    throw new SystemException(e);
                }

                links.append("&nbsp;<a href='");
                links.append(agendaLink);
                links.append("' title='");
                links.append(category);
                links.append("'>");
                links.append(categoryLinkNumber);
                links.append("</a>");
            }
        }
         */

        itemForm.setPageLinks(links.toString());
    }

    /**
     * <p>Set the current page number from the request or form to the
     * <code>displayPage</code> attribute on the form.</p>
     *
     * <p>This method also sets the appropriate page links.</p>
     *
     * @param linkPage the page to link the page numbers to.
     * @param request current request to check for a 'page' parameter
     * and to create links.
     * @param response used to create links.
     * @param itemForm form to set page number <code>displayPage</code>.
     * @throws SystemException if there is any exception creating
     * the <code>URL</code>s.
     */
    public final void setPageNumber(final String linkPage,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final ItemForm itemForm)
        throws SystemException {
        LibraryItemDO item = itemForm.getItem();
        JspFactory factory = JspFactory.getDefaultFactory();
        PageContext pageContext = factory.getPageContext(getServlet(), request,
                response, "", true, 512, true);

        itemForm.setLinkPage(linkPage);

        // offset is the difference between the counting routine and the request
        // parameter - we want page 1 to be 1, not 0 for documents
        int lastPage;

        // offset is the difference between the counting routine and the request
        // parameter - we want page 1 to be 1, not 0 for documents
        int offset;

        // find last page for meeeting
        Integer itemType = item.getType();
        assert (itemType != null);
        /**
         * TODO: fix this via subclassing/fire events
        if (itemType.equals(LibraryItemConstants.ITEM_MEETING)) {
            lastPage = item.getMeeting().getCategories().size();
            offset = 0;

            // calculate last page for fequently asked questions
        } else         */
         if (itemType.equals(LibraryItemConstants.ITEM_FAQ)) {
            lastPage = item.getFAQCategories().size();
            offset = 0;

            // calculate last page for normal pages
        } else {
            lastPage = item.getPages().size() - 1;
            offset = 1;
        }

        int pageInt = -1;

        if (request.getParameter("page") != null) {
            try {
                pageInt = new Integer(request.getParameter("page")).intValue() -
                    offset;
            } catch (NumberFormatException e) { // ignore all number conversion exceptions: the page will default to the first one
            }
        } else {
            pageInt = itemForm.getDisplayPage();
        }

        if (pageInt > lastPage) {
            pageInt = lastPage;
        }

        // page indices can't normally be < 0
        if (pageInt < 0) {
            pageInt = 0;
        }

        itemForm.setDisplayPage(pageInt);

        // calculate links for meetings
        if (itemType.equals(LibraryItemConstants.ITEM_MEETING)) {
            setMeetingPageNumber(pageContext, linkPage, request, itemForm);

            // calculate links for fequently asked questions
        } else if (itemType.equals(LibraryItemConstants.ITEM_FAQ)) {
            setFaqPageNumber(pageContext, linkPage, request, itemForm);

            // calculate links for normal pages
        } else {
            setDocumentPageNumber(pageContext, linkPage, request, itemForm);
        }

        // now the links for the next and previous page
        HashMap displayDocumentParameters = new HashMap();
        StringBuffer previousPageLink = new StringBuffer();
        StringBuffer nextPageLink = new StringBuffer();
        MessageResources libraryResources = getResources(request, "library");
        Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);

        // no previous page for the first page!
        if (pageInt > 0) {
            // 'human-readable' numbers start with 1
            displayDocumentParameters.put("page",
                new Integer((pageInt + offset) - 1).toString());
            previousPageLink.append("<a href='");

            try {
                previousPageLink.append(TagUtils.getInstance().computeURL(pageContext,
                        null, null, linkPage, null, null,
                        displayDocumentParameters, null, true));
            } catch (MalformedURLException e) {
                throw new SystemException(e);
            }

            previousPageLink.append("'>");
            previousPageLink.append(libraryResources.getMessage(locale,
                    "displayDocument.link.previousPage"));
            previousPageLink.append("</a>");
        }

        itemForm.setPreviousPageLink(previousPageLink.toString());

        // no next page for the last page!
        if (pageInt < lastPage) {
            displayDocumentParameters.put("page",
                new Integer(pageInt + offset + 1).toString());
            nextPageLink.append("<a href='");

            try {
                nextPageLink.append(TagUtils.getInstance().computeURL(
                        pageContext, null, null, linkPage, null,
                        null, displayDocumentParameters, null, true));
            } catch (MalformedURLException e) {
                throw new SystemException(e);
            }

            nextPageLink.append("'>");
            nextPageLink.append(libraryResources.getMessage(locale,
                    "displayDocument.link.nextPage"));
            nextPageLink.append("</a>");
        }

        itemForm.setNextPageLink(nextPageLink.toString());
    }
}

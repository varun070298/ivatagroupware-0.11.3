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
 * $Log: IndexAction.java,v $
 * Revision 1.4  2005/05/01 08:47:32  colinmacleod
 * Cosmetic (line length) changes.
 *
 * Revision 1.3  2005/04/10 18:47:39  colinmacleod
 * Changed i tag to em and b tag to strong.
 *
 * Revision 1.2  2005/04/09 17:19:46  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:59  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.6  2004/12/31 18:27:44  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.5  2004/12/23 21:01:29  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.4  2004/11/12 18:19:15  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.3  2004/11/12 15:57:16  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/11/03 15:31:51  colinmacleod
 * Change method interfaces to remove log.
 *
 * Revision 1.1  2004/07/13 19:47:29  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.struts;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.picocontainer.PicoContainer;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.business.addressbook.AddressBook;
import com.ivata.groupware.business.addressbook.person.group.right.RightConstants;
import com.ivata.groupware.business.library.Library;
import com.ivata.groupware.business.library.item.LibraryItemDO;
import com.ivata.groupware.business.mail.Mail;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.persistence.PersistenceManager;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.format.HTMLFormatter;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>
 * Retrieves a list of recent library items.
 * </p>
 *
 * @since 2004-06-27
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 */
public class IndexAction extends MaskAction {
    private Library library;
    private Settings settings;
    /**
     * TODO
     * @param securitySession
     * @param settings
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public IndexAction(SecuritySession securitySession, Settings settings,
            MaskFactory maskFactory, MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        PicoContainer container = securitySession.getContainer();
        Object object = container.getComponentInstance(PersistenceManager.class);
        object = container.getComponentInstance(AddressBook.class);
        object = container.getComponentInstance(Mail.class);
        object = container.getComponentInstance(Settings.class);
        object = container.getComponentInstance(HTMLFormatter.class);
        this.library = (Library) container.getComponentInstance(Library.class);
//        this.library = library;
        this.settings = settings;
    }


    /**
     * <p>
     * Retrieves a list of recent library items.
     * </p>
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
     */
    public String execute(final ActionMapping mapping,
            final ActionErrors errors,
            final ActionForm form,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpSession session) throws SystemException {
        SecuritySession securitySession = (SecuritySession) session.getAttribute("securitySession");

        try {
            Integer libraryHome = settings.getIntegerSetting(securitySession,
                    "libraryHome", securitySession.getUser());
            Integer libraryRecent = settings.getIntegerSetting(securitySession,
                    "libraryRecent", securitySession.getUser());
            int numberOfItems = libraryHome.intValue();
            int numberOfRecent = libraryRecent.intValue();
            int numberToRead = (numberOfRecent > numberOfItems) ? numberOfRecent : numberOfItems;

            Collection items = library.findRecentItems(securitySession, new Integer(numberToRead),
                    RightConstants.ACCESS_VIEW, null);
            PropertyUtils.setProperty(form, "items", items);

            Iterator itemIterator = items.iterator();

            Map commentsForItem = new HashMap();
            while(itemIterator.hasNext()) {
                LibraryItemDO item = (LibraryItemDO) itemIterator.next();
                int commentCount = library.countCommentsForItem(securitySession,
                        item.getId());
                commentsForItem.put(item.getId(), new Integer(commentCount));
            }
            PropertyUtils.setProperty(form, "commentsForItem", commentsForItem);

            Collection unacknowledgedComments = library.findUnacknowledgedComments(securitySession, new Integer(10));
            PropertyUtils.setProperty(form, "unacknowledgedComments", unacknowledgedComments);
        } catch (IllegalAccessException e) {
            throw new SystemException(e);
        } catch (InvocationTargetException e) {
            throw new SystemException(e);
        } catch (NoSuchMethodException e) {
            throw new SystemException(e);
        }

        // this list always goes to the same page
        return null;
    }
}

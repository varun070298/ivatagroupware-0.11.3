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
 * $Log: AddFavoriteAction.java,v $
 * Revision 1.4  2005/04/28 18:47:09  colinmacleod
 * Fixed XHMTL, styles and resin compatibility.
 * Added support for URL rewriting.
 *
 * Revision 1.3  2005/04/10 20:32:01  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:10  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:50:36  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.9  2004/12/31 18:27:43  colinmacleod
 * Added MaskFactory to constructor of MaskAction.
 *
 * Revision 1.8  2004/12/23 21:01:26  colinmacleod
 * Updated Struts to v1.2.4.
 * Changed base classes to use ivata masks.
 *
 * Revision 1.7  2004/11/12 18:19:14  colinmacleod
 * Change action and form classes to extend MaskAction, MaskForm respectively.
 *
 * Revision 1.6  2004/11/12 15:57:07  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.5  2004/11/03 15:37:33  colinmacleod
 * Changed todo comments to all caps.
 *
 * Revision 1.4  2004/07/13 19:41:17  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.3  2004/03/21 21:16:19  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.2  2004/02/01 22:00:34  colinmacleod
 * Added full names to author tags
 *
 * Revision 1.1.1.1  2004/01/27 20:57:59  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.3  2003/10/17 12:36:13  jano
 * fixing problems with building
 * converting intranet -> portal
 * Eclipse building
 *
 * Revision 1.2  2003/10/15 13:49:57  colin
 * fixing fo Xdoclet
 *
 * Revision 1.2  2003/06/06 09:18:08  peter
 * fixed for URL non-URL rewriting
 *
 * Revision 1.1  2003/03/04 14:22:37  colin
 * first version in cvs
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.navigation.struts;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.ivata.groupware.admin.security.server.SecuritySession;
import com.ivata.groupware.admin.setting.Settings;
import com.ivata.groupware.navigation.menu.MenuConstants;
import com.ivata.groupware.navigation.menu.MenuDO;
import com.ivata.groupware.navigation.menu.item.MenuItemDO;
import com.ivata.mask.MaskFactory;
import com.ivata.mask.persistence.PersistenceManager;
import com.ivata.mask.persistence.PersistenceSession;
import com.ivata.mask.util.StringHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.web.RewriteHandling;
import com.ivata.mask.web.struts.MaskAction;
import com.ivata.mask.web.struts.MaskAuthenticator;


/**
 * <p>This action is called when the user clicks on the
 * 'add to favorites' heart in the title frame.</p>
 *
 * @since 2003-03-04
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.4 $
 */
public class AddFavoriteAction extends MaskAction {
    /**
     * Refer to {@link Logger}.
     */
    private static Logger log = Logger.getLogger(AddFavoriteAction.class);
    /**
     * <p>
     * Used to retrieve favorites menu and add new items to it.
     * </p>
     */
    private PersistenceManager persistenceManager;

    /**
     * <p>
     * Settings implementation.
     * </p>
     */
    private Settings settings;

    /**
     * Construct the action.
     *
     * @param persisitenceManager used to retrieve favorites menu, and add new
     * items to it.
     * @param settings settings implementation.
     * @param maskFactory This factory is needed to access the masks and groups
     * of masks.
     * @param authenticator used to confirm whether or not the
     * user should be allowed to continue, in the <code>execute</code> method.
     */
    public AddFavoriteAction(final PersistenceManager persistenceManagerParam,
            final Settings settings,
            final MaskFactory maskFactory,
            final MaskAuthenticator authenticator) {
        super(maskFactory, authenticator);
        this.persistenceManager = persistenceManagerParam;
        this.settings = settings;
    }

    /**
     * <p>Add the title and URL in request paramters to the favorites
     * and forward to the left frame refreshed.</p>
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param log valid logging object to write messages to.
     * @param errors valid errors object to append errors to. If there are
     * any errors, the action will return to the input.
     * @param form optional ActionForm bean for this request (if any)
     * @param request non-HTTP request we are processing
     * @param response The non-HTTP response we are creating
     * @param session  returned from the <code>request</code> parameter.
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
        if (log.isDebugEnabled()) {
            log.debug("In AddFavoriteAction.execute.");
        }
        // TODO: might want to make favorite id a setting somewhere
        // - for now, it is hard-coded to 0 :-)

        // store the favorite, only store the path without the contextPath and
        // strip the jsessionid
        String favorite, uRL;
        try {
            favorite = (String) PropertyUtils.getSimpleProperty(form, "favorite");;
            uRL = (String) PropertyUtils.getSimpleProperty(form, "uRL");;
            if (log.isDebugEnabled()) {
                log.debug("Favorite title is '"
                        + favorite
                        + "', URL is '"
                        + uRL
                        + "'");
            }
        } catch (NoSuchMethodException e) {
            throw new SystemException(e);
        } catch (InvocationTargetException e) {
            throw new SystemException(e);
        } catch (IllegalAccessException e) {
            throw new SystemException(e);
        }

        // do nothing when URL or title empty
        if (StringHandling.isNullOrEmpty(favorite)
                || StringHandling.isNullOrEmpty(uRL) ) {
            log.warn("Either favorite title ("
                    + favorite
                    + ") or URL ("
                    + uRL
                    + ") is null --> returning null and not adding favorite");
            return null;
        }
        SecuritySession securitySession =
            (SecuritySession) session.getAttribute("securitySession");

        // remove the context path - we want relative links
        uRL = uRL.substring(uRL.lastIndexOf(
                RewriteHandling.getContextPath(request)));


        // remove the " - sitename" from the title
        StringBuffer standardEnding = new StringBuffer("- ");
        standardEnding.append(settings.getStringSetting(securitySession,
                "siteTitle", securitySession.getUser()));
        if (favorite.endsWith(standardEnding.toString())) {
            favorite = favorite.substring(0, favorite.length()
                    - standardEnding.length()).trim();
        }

        // URL rewriting case, the jsessionid part has to be stripped out
        if (uRL.indexOf("jsessionid")!=-1) {
            int jsessionStart = uRL.indexOf(";");
            int jsessionEnd = uRL.lastIndexOf("?");
            if (jsessionStart < jsessionEnd) {
                uRL = uRL.substring(0, jsessionStart) +
                                              uRL.substring(jsessionEnd);
            } else {
                uRL = uRL.substring(0,jsessionStart);
            }
        }
        // get the favorites menu
        PersistenceSession persistenceSession = persistenceManager
            .openSession(securitySession);
        try {
            MenuDO favoritesMenu = (MenuDO)persistenceManager.findByPrimaryKey(
                    persistenceSession,
                    MenuDO.class,
                    MenuConstants.ID_FAVORITES
                    );

            MenuItemDO menuItem = new MenuItemDO();

            menuItem.setMenu(favoritesMenu);
            menuItem.setUser(securitySession.getUser());
            menuItem.setImage(null);
            menuItem.setText(favorite);
            menuItem.setURL(uRL);
            // use the inverse count to ensure the new item always appears at
            // the top!
            menuItem.setPriority(new Integer(0
                    - favoritesMenu.getItems().size()));
            persistenceManager.add(persistenceSession, menuItem);
        } finally {
            persistenceSession.close();
        }

        // this should always go to left.jsp
        return null;
    }
}

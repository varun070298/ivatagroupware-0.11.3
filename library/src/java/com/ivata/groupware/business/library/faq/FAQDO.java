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
 * $Log: FAQDO.java,v $
 * Revision 1.1  2005/04/26 15:21:53  colinmacleod
 * Renamed Faq to FAQ.
 *
 * Revision 1.3  2005/04/10 20:09:44  colinmacleod
 * Added new themes.
 * Changed id type to String.
 * Changed i tag to em and b tag to strong.
 * Improved PicoContainerFactory with NanoContainer scripts.
 *
 * Revision 1.2  2005/04/09 17:19:45  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:59  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/07/13 19:47:28  colinmacleod
 * Moved project to POJOs from EJBs.
 * Applied PicoContainer to services layer (replacing session EJBs).
 * Applied Hibernate to persistence layer (replacing entity EJBs).
 *
 * Revision 1.2  2004/03/21 21:16:29  colinmacleod
 * Shortened name to ivata op.
 *
 * Revision 1.1.1.1  2004/01/27 20:58:38  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.5  2003/11/03 11:29:44  jano
 * commiting library,
 * tryinjg to fix deploying problem
 *
 * Revision 1.4  2003/10/28 13:16:14  jano
 * commiting library,
 * still fixing compile and building openGroupware project
 *
 * Revision 1.3  2003/10/15 14:23:29  jano
 * converting to XDoclet
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.4  2003/05/01 12:13:22  jano
 * tidy up names of sequeneces
 *
 * Revision 1.3  2003/02/28 07:29:23  colin
 * fixed code for creation
 * added id dispenser
 * moved CMRs to post create
 *
 * Revision 1.2  2003/02/25 14:38:13  colin
 * implemented setModified methods on entity beans thro IvataEntityBean superclass
 *
 * Revision 1.1  2003/02/24 19:09:22  colin
 * moved to business
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.library.faq;

import com.ivata.groupware.business.library.faq.category.FAQCategoryDO;
import com.ivata.groupware.container.persistence.BaseDO;


/**
 * <p>Represents a single question/answer pair as stored in frequently asked
 * question library items.</p>
 *
 * @hibernate.class
 *      table="library_faq"
 */
public class FAQDO  extends BaseDO {

    /**
     * <p>The answer to the question.</p>
     */
    private String answer;

    /**
     * <p>The category which contains this question/answer.</p>
     */
    private FAQCategoryDO fAQCategory;
    /**
     * <p>The question.</p>
     */
    private String question;
    /**
     * <p>Get the answer to the question.</p>
     *
     * @return the answer to the question.
     * @hibernate.property
     */
    public final String getAnswer() {
       return answer;
    }

    /**
     * <p>Get the category which contains this question/answer.</p>
     *
     * @return the category which contains this question/answer.
     * @hibernate.many-to-one
     *      column="library_faq_category"
     */
    public final FAQCategoryDO getFAQCategory() {
        return fAQCategory;
    }
    /**
     * <p>Get the question.</p>
     *
     * @return the question being asked.
     * @hibernate.property
     */
    public final String getQuestion() {
        return question;
    }

    /**
     * <p>Set the answer to the question.</p>
     *
     * @param answer the answer to the question.
     */
    public final void setAnswer(final String answer) {
        this.answer = answer;
    }

    /**
     * <p>Set the category which contains this question/answer.</p>
     *
     * @param faqCategory the category which contains this question/answer.
     */
    public final void setFAQCategory(final FAQCategoryDO faqCategory) {
        this.fAQCategory = faqCategory;
    }

    /**
     * <p>Set the question.</p>
     *
     * @param question the question being asked.
     */
    public final void setQuestion(final String question) {
        this.question = question;
    }
}

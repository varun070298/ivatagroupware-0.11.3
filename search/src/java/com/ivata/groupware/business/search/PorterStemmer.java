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
 * $Log: PorterStemmer.java,v $
 * Revision 1.2  2005/04/09 17:19:55  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:52:38  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.1  2004/08/01 11:40:30  colinmacleod
 * Moved search classes to separate subproject.
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
 * Revision 1.1.1.1  2004/01/27 20:58:43  colinmacleod
 * Moved ivata openportal to SourceForge..
 *
 * Revision 1.2  2003/10/15 14:16:53  colin
 * fixing for XDoclet
 *
 * Revision 1.3  2003/07/17 06:54:42  peter
 * added exception haandling on stemming steps
 *
 * Revision 1.2  2003/04/19 10:46:48  peter
 * fixed handling of patterns ending with non-letters
 *
 * Revision 1.1  2003/02/24 19:09:24  colin
 * moved to business
 *
 * Revision 1.6  2003/02/04 17:43:50  colin
 * copyright notice
 *
 * Revision 1.5  2002/11/20 13:22:21  jano
 * we want STEM_MAXLENGTH not 14
 *
 * Revision 1.4  2002/11/18 08:20:12  peter
 * maximum length of a stem set
 *
 * Revision 1.3  2002/10/31 16:10:15  peter
 * wordlength limit changed to 2
 *
 * Revision 1.2  2002/10/24 15:52:43  peter
 * changed initial conditions for the input String, can contain digits too
 *
 * Revision 1.1  2002/09/19 11:05:26  peter
 * took the source code, changed all methods to static and
 * added end-of-word (end-of-sentence) dots removal
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.business.search;


/**
 * <p>This class contains methods to get the stem of an english word.
 * Don't create instances of this class, use it as static,
 * call <code>stemmedWord = PorterStemmer.stem(inputWord)</code>.</p>
 *
 * @since 2002-09-19
 * @author Peter Illes
 * @version $Revision: 1.2 $
 */



public class PorterStemmer {

    /**
     * the maximum legth of a returned stem
     */
    private static final int STEM_MAXLENGTH = 14;


    public static final String stem(String str) {
        // check for zero length
        if (str.length() > 1) {
            // all characters must be letters
            char[] c = str.toCharArray();
            int len = c.length - 1;
            for (int i = 0; i <= len; i++) {
                if (!Character.isLetter(c[i])) {
                    //if the character is digit,
                    if (Character.isDigit(c[i])) {
                        //and it's the last character, return the (cut) input, no stemming
                        if  (i==len)  {
                            return str.length()>STEM_MAXLENGTH?
                                str.substring(0,STEM_MAXLENGTH):str;
                        //if there are more characters, let's go on
                        } else {
                            continue;
                        }
                    //it's not a letter and not a digit, no indexing
                    } else {
                        return "";
                    }
                }
            }
        } else {
            return "";
        }

        try {
            str = step1a(str);
            str = step1b(str);
            str = step1c(str);
            str = step2(str);
            str = step3(str);
            str = step4(str);
            str = step5a(str);
            str = step5b(str);
        // this happens when a pattern gets too short during the stemming steps
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }

        return str.length()>STEM_MAXLENGTH?str.substring(0,STEM_MAXLENGTH):str;
    }

    private static final String step1a (String str) {
        // SSES -> SS
        if (str.endsWith("sses")) {
            return str.substring(0, str.length() - 2);
        // IES -> I
        } else if (str.endsWith("ies")) {
            return str.substring(0, str.length() - 2);
        // SS -> S
        } else if (str.endsWith("ss")) {
            return str;
        // S ->
        } else if (str.endsWith("s")) {
            return str.substring(0, str.length() - 1);
        } else {
            return str;
        }
    } // end step1a

    private static final String step1b (String str) {
        // (m > 0) EED -> EE
        if (str.endsWith("eed")) {
            if (stringMeasure(str.substring(0, str.length() - 3)) > 0)
                return str.substring(0, str.length() - 1);
            else
                return str;
        // (*v*) ED ->
        } else if ((str.endsWith("ed")) &&
                   (containsVowel(str.substring(0, str.length() - 2)))) {
            return step1b2(str.substring(0, str.length() - 2));
        // (*v*) ING ->
        } else if ((str.endsWith("ing")) &&
                   (containsVowel(str.substring(0, str.length() - 3)))) {
            return step1b2(str.substring(0, str.length() - 3));
        } // end if
        return str;
    } // end step1b

    private static final String step1b2 (String str) {
        // AT -> ATE
        if (str.endsWith("at") ||
            str.endsWith("bl") ||
            str.endsWith("iz")) {
            return str + "e";
        } else if ((endsWithDoubleConsonent(str)) &&
                   (!(str.endsWith("l") || str.endsWith("s") || str.endsWith("z")))) {
            return str.substring(0, str.length() - 1);
        } else if ((stringMeasure(str) == 1) &&
                   (endsWithCVC(str))) {
            return str + "e";
        } else {
            return str;
        }
    } // end step1b2

    private static final String step1c(String str) {
        // (*v*) Y -> I
        if (str.endsWith("y")) {
            if (containsVowel(str.substring(0, str.length() - 1)))
                return str.substring(0, str.length() - 1) + "i";
        } // end if
        return str;
    } // end step1c

    private static final String step2 (String str) {
        // (m > 0) ATIONAL -> ATE
        if ((str.endsWith("ational")) &&
            (stringMeasure(str.substring(0, str.length() - 5)) > 0)) {
            return str.substring(0, str.length() - 5) + "e";
        // (m > 0) TIONAL -> TION
        } else if ((str.endsWith("tional")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 0)) {
            return str.substring(0, str.length() - 2);
        // (m > 0) ENCI -> ENCE
        } else if ((str.endsWith("enci")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 0)) {
            return str.substring(0, str.length() - 2);
        // (m > 0) ANCI -> ANCE
        } else if ((str.endsWith("anci")) &&
            (stringMeasure(str.substring(0, str.length() - 1)) > 0)) {
            return str.substring(0, str.length() - 1) + "e";
        // (m > 0) IZER -> IZE
        } else if ((str.endsWith("izer")) &&
            (stringMeasure(str.substring(0, str.length() - 1)) > 0)) {
            return str.substring(0, str.length() - 1);
        // (m > 0) ABLI -> ABLE
        } else if ((str.endsWith("abli")) &&
            (stringMeasure(str.substring(0, str.length() - 1)) > 0)) {
            return str.substring(0, str.length() - 1) + "e";
        // (m > 0) ENTLI -> ENT
        } else if ((str.endsWith("alli")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 0)) {
            return str.substring(0, str.length() - 2);
        // (m > 0) ELI -> E
        } else if ((str.endsWith("entli")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 0)) {
            return str.substring(0, str.length() - 2);
        // (m > 0) OUSLI -> OUS
        } else if ((str.endsWith("eli")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 0)) {
            return str.substring(0, str.length() - 2);
        // (m > 0) IZATION -> IZE
        } else if ((str.endsWith("ousli")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 0)) {
            return str.substring(0, str.length() - 2);
        // (m > 0) IZATION -> IZE
        } else if ((str.endsWith("ization")) &&
            (stringMeasure(str.substring(0, str.length() - 5)) > 0)) {
            return str.substring(0, str.length() - 5) + "e";
        // (m > 0) ATION -> ATE
        } else if ((str.endsWith("ation")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 0)) {
            return str.substring(0, str.length() - 3) + "e";
        // (m > 0) ATOR -> ATE
        } else if ((str.endsWith("ator")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 0)) {
            return str.substring(0, str.length() - 2) + "e";
        // (m > 0) ALISM -> AL
        } else if ((str.endsWith("alism")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 0)) {
           return str.substring(0, str.length() - 3);
        // (m > 0) IVENESS -> IVE
        } else if ((str.endsWith("iveness")) &&
            (stringMeasure(str.substring(0, str.length() - 4)) > 0)) {
            return str.substring(0, str.length() - 4);
        // (m > 0) FULNESS -> FUL
        } else if ((str.endsWith("fulness")) &&
            (stringMeasure(str.substring(0, str.length() - 4)) > 0)) {
            return str.substring(0, str.length() - 4);
        // (m > 0) OUSNESS -> OUS
        } else if ((str.endsWith("ousness")) &&
            (stringMeasure(str.substring(0, str.length() - 4)) > 0)) {
            return str.substring(0, str.length() - 4);
        // (m > 0) ALITII -> AL
        } else if ((str.endsWith("aliti")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 0)) {
            return str.substring(0, str.length() - 3);
        // (m > 0) IVITI -> IVE
        } else if ((str.endsWith("iviti")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 0)) {
            return str.substring(0, str.length() - 3) + "e";
        // (m > 0) BILITI -> BLE
        } else if ((str.endsWith("biliti")) &&
            (stringMeasure(str.substring(0, str.length() - 5)) > 0)) {
            return str.substring(0, str.length() - 5) + "le";
        } // end if
        return str;
    } // end step2


    private static final String step3 (String str) {
        // (m > 0) ICATE -> IC
        if ((str.endsWith("icate")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 0)) {
            return str.substring(0, str.length() - 3);
        // (m > 0) ATIVE ->
        } else if ((str.endsWith("ative")) &&
            (stringMeasure(str.substring(0, str.length() - 5)) > 0)) {
            return str.substring(0, str.length() - 5);
        // (m > 0) ALIZE -> AL
        } else if ((str.endsWith("alize")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 0)) {
            return str.substring(0, str.length() - 3);
        // (m > 0) ICITI -> IC
        } else if ((str.endsWith("iciti")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 0)) {
            return str.substring(0, str.length() - 3);
        // (m > 0) ICAL -> IC
        } else if ((str.endsWith("ical")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 0)) {
            return str.substring(0, str.length() - 2);
        // (m > 0) FUL ->
        } else if ((str.endsWith("ful")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 0)) {
            return str.substring(0, str.length() - 3);
        // (m > 0) NESS ->
        } else if ((str.endsWith("ness")) &&
            (stringMeasure(str.substring(0, str.length() - 4)) > 0)) {
            return str.substring(0, str.length() - 4);
        } // end if
        return str;
    } // end step3


    private static final String step4 (String str) {
        if ((str.endsWith("al")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 1)) {
            return str.substring(0, str.length() - 2);
            // (m > 1) ANCE ->
        } else if ((str.endsWith("ance")) &&
            (stringMeasure(str.substring(0, str.length() - 4)) > 1)) {
            return str.substring(0, str.length() - 4);
        // (m > 1) ENCE ->
        } else if ((str.endsWith("ence")) &&
            (stringMeasure(str.substring(0, str.length() - 4)) > 1)) {
            return str.substring(0, str.length() - 4);
        // (m > 1) ER ->
        } else if ((str.endsWith("er")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 1)) {
            return str.substring(0, str.length() - 2);
        // (m > 1) IC ->
        } else if ((str.endsWith("ic")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 1)) {
            return str.substring(0, str.length() - 2);
        // (m > 1) ABLE ->
        } else if ((str.endsWith("able")) &&
            (stringMeasure(str.substring(0, str.length() - 4)) > 1)) {
            return str.substring(0, str.length() - 4);
        // (m > 1) IBLE ->
        } else if ((str.endsWith("ible")) &&
            (stringMeasure(str.substring(0, str.length() - 4)) > 1)) {
            return str.substring(0, str.length() - 4);
        // (m > 1) ANT ->
        } else if ((str.endsWith("ant")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 1)) {
            return str.substring(0, str.length() - 3);
        // (m > 1) EMENT ->
        } else if ((str.endsWith("ement")) &&
            (stringMeasure(str.substring(0, str.length() - 5)) > 1)) {
            return str.substring(0, str.length() - 5);
        // (m > 1) MENT ->
        } else if ((str.endsWith("ment")) &&
            (stringMeasure(str.substring(0, str.length() - 4)) > 1)) {
            return str.substring(0, str.length() - 4);
        // (m > 1) ENT ->
        } else if ((str.endsWith("ent")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 1)) {
            return str.substring(0, str.length() - 3);
        // (m > 1) and (*S or *T) ION ->
        } else if ((str.endsWith("sion") || str.endsWith("tion")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 1)) {
            return str.substring(0, str.length() - 3);
        // (m > 1) OU ->
        } else if ((str.endsWith("ou")) &&
            (stringMeasure(str.substring(0, str.length() - 2)) > 1)) {
            return str.substring(0, str.length() - 2);
        // (m > 1) ISM ->
        } else if ((str.endsWith("ism")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 1)) {
            return str.substring(0, str.length() - 3);
        // (m > 1) ATE ->
        } else if ((str.endsWith("ate")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 1)) {
            return str.substring(0, str.length() - 3);
        // (m > 1) ITI ->
        } else if ((str.endsWith("iti")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 1)) {
            return str.substring(0, str.length() - 3);
        // (m > 1) OUS ->
        } else if ((str.endsWith("ous")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 1)) {
            return str.substring(0, str.length() - 3);
        // (m > 1) IVE ->
        } else if ((str.endsWith("ive")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 1)) {
            return str.substring(0, str.length() - 3);
        // (m > 1) IZE ->
        } else if ((str.endsWith("ize")) &&
            (stringMeasure(str.substring(0, str.length() - 3)) > 1)) {
            return str.substring(0, str.length() - 3);
        } // end if
        return str;
    } // end step4


    private static final String step5a (String str) {
        // (m > 1) E ->
        if ((stringMeasure(str.substring(0, str.length() - 1)) > 1) &&
            str.endsWith("e"))
            return str.substring(0, str.length() -1);
        // (m = 1 and not *0) E ->
        else if ((stringMeasure(str.substring(0, str.length() - 1)) == 1) &&
                 (!endsWithCVC(str.substring(0, str.length() - 1))) &&
                 (str.endsWith("e")))
            return str.substring(0, str.length() - 1);
        else
            return str;
    } // end step5a


    private static final String step5b (String str) {
        // (m > 1 and *d and *L) ->
        if (str.endsWith("l") &&
            endsWithDoubleConsonent(str) &&
            (stringMeasure(str.substring(0, str.length() - 1)) > 1)) {
            return str.substring(0, str.length() - 1);
        } else {
            return str;
        }
    } // end step5b


    /*
       -------------------------------------------------------
       The following are functions to help compute steps 1 - 5
       -------------------------------------------------------
    */

    // does string end with 's'?
    private static final boolean endsWithS(String str) {
        return str.endsWith("s");
    } // end function

    // does string contain a vowel?
    private static final boolean containsVowel(String str) {
        char[] strchars = str.toCharArray();
        for (int i = 0; i < strchars.length; i++) {
            if (isVowel(strchars[i]))
                return true;
        }
        // no aeiou but there is y
        if (str.indexOf('y') > -1)
            return true;
        else
            return false;
    } // end function

    // is char a vowel?
    private static final boolean isVowel(char c) {
        if ((c == 'a') ||
            (c == 'e') ||
            (c == 'i') ||
            (c == 'o') ||
            (c == 'u'))
            return true;
        else
            return false;
    } // end function

    // does string end with a double consonent?
    private static final boolean endsWithDoubleConsonent(String str) {
        char c = str.charAt(str.length() - 1);
        if (c == str.charAt(str.length() - 2))
            if (!containsVowel(str.substring(str.length() - 2))) {
                return true;
        }
        return false;
    } // end function

    // returns a CVC measure for the string
    private  static final int stringMeasure(String str) {
        int count = 0;
        boolean vowelSeen = false;
        char[] strchars = str.toCharArray();

        for (int i = 0; i < strchars.length; i++) {
            if (isVowel(strchars[i])) {
                vowelSeen = true;
            } else if (vowelSeen) {
                count++;
                vowelSeen = false;
            }
        } // end for
        return count;
    } // end function

    // does stem end with CVC?
    private static final boolean endsWithCVC (String str) {
        char c, v, c2 = ' ';
        if (str.length() >= 3) {
            c = str.charAt(str.length() - 1);
            v = str.charAt(str.length() - 2);
            c2 = str.charAt(str.length() - 3);
        } else {
            return false;
        }

        if ((c == 'w') || (c == 'x') || (c == 'y')) {
            return false;
        } else if (isVowel(c)) {
            return false;
        } else if (!isVowel(v)) {
            return false;
        } else if (isVowel(c2)) {
            return false;
        } else {
            return true;
        }
    }
}

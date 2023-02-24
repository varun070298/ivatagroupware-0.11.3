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
 * $Log: ExternalScriptExecutor.java,v $
 * Revision 1.2  2005/04/09 17:19:56  colinmacleod
 * Changed copyright text to GPL v2 explicitly.
 *
 * Revision 1.1.1.1  2005/03/10 17:51:39  colinmacleod
 * Restructured ivata op around Hibernate/PicoContainer.
 * Renamed ivata groupware.
 *
 * Revision 1.4  2004/11/12 18:16:07  colinmacleod
 * Ordered imports.
 *
 * Revision 1.3  2004/11/12 15:57:18  colinmacleod
 * Removed dependencies on SSLEXT.
 * Moved Persistence classes to ivata masks.
 *
 * Revision 1.2  2004/11/03 16:03:47  colinmacleod
 * Added checking for null arguments.
 * Now throws validation exception on errors.
 *
 * Revision 1.1  2004/09/30 15:15:57  colinmacleod
 * Split off addressbook elements into security subproject.
 *
 * Revision 1.1  2004/07/13 19:12:33  colinmacleod
 * New classes as part of conversion to PicoContainer.
 * -----------------------------------------------------------------------------
 */
package com.ivata.groupware.admin.script;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ivata.groupware.admin.security.server.SecurityServerException;
import com.ivata.mask.util.CollectionHandling;
import com.ivata.mask.util.SystemException;
import com.ivata.mask.validation.ValidationError;
import com.ivata.mask.validation.ValidationErrors;
import com.ivata.mask.validation.ValidationException;
import com.ivata.mask.web.format.URLFormat;

/**
 * Simple class to let you run scripts which exist outside the JVM world.
 *
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @since   Apr 6, 2004
 * @version $Revision: 1.2 $
 */
public class ExternalScriptExecutor implements ScriptExecutor {

    /**
     * Refer to {@link Logger}.
     */
    private static Logger log = Logger.getLogger(ExternalScriptExecutor.class);
    /**
     * <p>
     * All environment variables to be set in the script environment.
     * </p>
     */
    String [] environmentVariables;
    /**
     * <p>
     * Full path to the location where the scripts are stored for this executor.
     * </p>
     */
    private String scriptPath;
    /**
     * <p>
     * This is used to format variables to avoid problems with command line
     * spaces.
     * </p>
     */
    URLFormat uRLFormat;
    /**
     * <p>
     * Construct a script executor to execute external scripts.
     * </p>
     *
     * @param scriptPath full path to the location where the scripts are stored
     * for this executor.
     * @param environmentVariables all environment variables to be set in the
     * script environemnt, separated by line feeds.
     * @param uRLFormat used to format variables to avoid problems with command
     * line spaces.
     */
    public ExternalScriptExecutor(URLFormat URLFormat, String scriptPath,
            String environmentVariables) {
        this.scriptPath = scriptPath;
        this.environmentVariables = (String[])
            CollectionHandling.convertFromLines(environmentVariables).toArray(new String[]{});
        this.uRLFormat = URLFormat;
    }
    /**
     * <p>Execute a command and handle any error that occurs.</p>
     *
     * @param scriptName name of the script to be executed.
     * @param argv command name and all arguments of to be executed. The first
     *     argument should always be the script name
     * @throws SecurityServerException if the command returns non-zero, or if
     * there is
     * an input/output exception.
     * @return all lines of the program output as a <code>String</code>.
     */
    public String exec(final String scriptName,
            final String[] arguments) throws SystemException {
        String [] externalArguments = new String[arguments.length + 1];

        try {
            externalArguments[0] = scriptPath
                + File.separator
                + scriptName;

            // put quotes round each of the arguments
            // TODO: this will probably not work in an Windows environment -
            // possible solutions are replacing spaces somehow here and
            // (better) providing perl/other script wrappers to parse arguments
            for (int index = 0; index < arguments.length; ++index) {
                if ((arguments[index] == null)
                        || (arguments[index].length() == 0)) {
                    externalArguments[index + 1] = "%00";
                } else {
                    externalArguments[index + 1] = uRLFormat.format(arguments[index]);
                }
            }

            Process process;
            try {
                process = Runtime.getRuntime().exec(externalArguments,
                        environmentVariables, new File(scriptPath));
            } catch (IOException e) {
                log.error(e);
                String argumentsString = CollectionHandling.convertToLines(
                        Arrays.asList(arguments), ',');
                throw new ValidationException(new ValidationError(
                        "errors.admin.script",
                        Arrays.asList(new Object[] {
                                scriptName,
                                argumentsString,
                                "IOException: "
                                    + e.getMessage()
                        })));
            }

            if (process.waitFor() != 0) {
                String errorText = extractText(process.getErrorStream());
                List lines  = CollectionHandling.convertFromLines(errorText);
                ValidationErrors errors = new ValidationErrors();
                Iterator linesIterator = lines.iterator();
                while (linesIterator.hasNext()) {
                    String line = (String) linesIterator.next();
                    // if it timed out waiting for a password, that is almost
                    // definitely a sudo issue - add a comment to help whoever
                    // installed ivata groupware.
                    if ((lines.size() == 1)
                            && "Password:".equals(line)) {
                        line += " (This looks like a user rights issue. Check "
                            + "visudo is installed properly and is set up for "
                            + "the user who is running the program. If you "
                            + "used the install script to install ivata "
                            + "groupware, change the value of USER_APP_SERVER "
                            + "at the start of the script and run "
                            + "setup.pl again.)";
                    }
                    // some scripts have been tuned to give out error message
                    // keys when they fail - see if this is one of those
                    if ((line.indexOf("error.") != -1)
                                || (line.indexOf("errors.") != -1)) {
                            List errorArguments = new Vector();
                        errorArguments.add(scriptName);
                        errorArguments.addAll(Arrays.asList(arguments));
                        errors.add(new ValidationError(
                                line,
                                errorArguments));
                    } else {
                        // nothing for it - we'll just have to use a generic
                        // 'script failed' error message
                        String argumentsString = CollectionHandling.convertToLines(
                                Arrays.asList(arguments), ',');
                        errors.add(new ValidationError(
                                "errors.admin.script",
                                Arrays.asList(new Object[] {
                                        scriptName,
                                        argumentsString,
                                        line
                                })));
                    }
                }
                throw new ValidationException(errors);
            }

            return extractText(process.getInputStream());
        } catch (IOException e) {
            throw new SystemException("There was an input/output exception:",
                e);
        } catch (InterruptedException e) {
            throw new SystemException("The script process was interrupted",
                e);
        }
    }

    /**
     * <p>Called internally to evaluate the text from the stream
     * provided.</p>
     *
     * @param stream stream containing text to extract.
     * @throws IOException thrown by <code>BufferedReader</code>.
     * @return the textual contents of the stream provided.
     */
    private String extractText(final InputStream stream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        StringBuffer messageBuffer = new StringBuffer();
        char[] chbuf = new char[1024];
        int count;

        while ((count = in.read(chbuf)) != -1) {
            messageBuffer.append(chbuf, 0, count);
        }

        return messageBuffer.toString();
    }
}

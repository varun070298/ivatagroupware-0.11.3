/*
 * Copyright (c) 2001 - 2005 ivata limited.
 * All rights reserved.
 * ---------------------------------------------------------
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
 * ---------------------------------------------------------
 * $Log: HibernateSetupConstants.java,v $
 * Revision 1.5  2005/05/10 20:07:09  colinmacleod
 * Workaround for tomcat 5.0.x (creates
 * copy of 'res' HSQLDB).
 *
 * Revision 1.4  2005/04/30 13:02:36  colinmacleod
 * Added checking and removal of previous
 * hibernate lock file.
 *
 * Revision 1.3  2005/04/27 15:08:46  colinmacleod
 * Converted Windows file separator to /
 *
 * Revision 1.2  2005/04/22 09:31:53  colinmacleod
 * Reordered the file alphabetically.
 *
 * Revision 1.1  2005/04/11 10:03:44  colinmacleod
 * Added setup feature.
 *
 * ---------------------------------------------------------
 */
package com.ivata.groupware.container.persistence.hibernate;

/**
 * @since ivata groupware 0.11 (08-Apr-2005)
 * @author Colin MacLeod
 * <a href='mailto:colin.macleod@ivata.com'>colin.macleod@ivata.com</a>
 * @version $Revision: 1.5 $
 */

public final class HibernateSetupConstants {
    /**
     * Dialect used when the database is created automatically.
     */
    public final static String AUTOMATIC_DATABASE_DIALECT
        = "net.sf.hibernate.dialect.HSQLDialect";
    /**
     * Driver used when the database is created automatically.
     * Refer to {@link #getAutomaticDatabaseDriver}.
     */
    public final static String AUTOMATIC_DATABASE_DRIVER
        = "org.hsqldb.jdbcDriver";
    /**
     * Database URL used by default when the in-memory database is used.
     */
    public final static String AUTOMATIC_DATABASE_MEMORY_URL =
        "jdbc:hsqldb:res:"
        + HibernateSetupConstants.START_DB;
    /**
     * Database password used when the database is created automatically.
     */
    public final static String AUTOMATIC_DATABASE_PASSWORD = "";
    /**
     * Database URL used by default when the database is created
     * automatically for temporary use (i.e. when the 'res' type fails
     * on tomcat 5.0.x).
     */
    public final static String AUTOMATIC_DATABASE_TMP_URL = "jdbc:hsqldb:file:"
        + System.getProperty("user.home").replace('\\', '/')
        + "/.ivatagroupware/tmpdb/tmp";
    /**
     * Database URL used by default when the database is created
     * automatically.
     */
    public final static String AUTOMATIC_DATABASE_URL = "jdbc:hsqldb:file:"
        + System.getProperty("user.home").replace('\\', '/')
        + "/.ivatagroupware/db/hsqldb";
    /**
     * Default database URL start - all automatic URLs must begin like this.
     */
   public static final String AUTOMATIC_DATABASE_URL_START
       = "jdbc:hsqldb:file:";
    /**
     * User name used when the database is created automatically.
     */
    public final static String AUTOMATIC_DATABASE_USER_NAME = "sa";
    /**
     * If the program is still running with the memory database, but the user
     * has confirmed this is OK, then an application scoped attribute is set
     * in the page context, to the value <code>Boolean.TRUE</code>. This is
     * the name of that attribute
     */
    public final static String CONFIRM_ATTRIBUTE = "setupConfirmed";
    /**
     * Names of the <strong>Hibernate</strong> dialects supported.
     */
    public static final String[] DATABASE_DIALECTS = {
            "",
            "net.sf.hibernate.dialect.DB2Dialect",
            "net.sf.hibernate.dialect.DB2400Dialect",
            "net.sf.hibernate.dialect.DB2390Dialect",
            "net.sf.hibernate.dialect.FirebirdDialect",
            "net.sf.hibernate.dialect.FrontbaseDialect",
            "net.sf.hibernate.dialect.HSQLDialect",
            "net.sf.hibernate.dialect.InformixDialect",
            "net.sf.hibernate.dialect.IngresDialect",
            "net.sf.hibernate.dialect.InterbaseDialect",
            "net.sf.hibernate.dialect.MckoiDialect",
            "net.sf.hibernate.dialect.SQLServerDialect",
            "net.sf.hibernate.dialect.MySQLDialect",
            "net.sf.hibernate.dialect.OracleDialect",
            "net.sf.hibernate.dialect.Oracle9Dialect",
            "net.sf.hibernate.dialect.PointbaseDialect",
            "net.sf.hibernate.dialect.PostgreSQLDialect",
            "net.sf.hibernate.dialect.ProgressDialect",
            "net.sf.hibernate.dialect.SAPDBDialect",
            "net.sf.hibernate.dialect.SybaseAnywhereDialect",
            "net.sf.hibernate.dialect.SybaseDialect",
    };
    /**
     * Names of the default database drivers for each dialect.
     */
    public static final String[] DATABASE_DRIVERS = {
            "",
            "COM.ibm.db2.jdbc.net.DB2Driver",
            "COM.ibm.db2.jdbc.net.DB2Driver",
            "COM.ibm.db2.jdbc.net.DB2Driver",
            "org.firebirdsql.jdbc.FBDriver",
            "com.frontbase.jdbc.FBJDriver",
            "org.hsqldb.jdbcDriver",
            "com.informix.jdbc.IfxDriver",
            "ca.edbc.jdbc.EdbcDriver",
            "interbase.interclient.Driver",
            "com.mckoi.JDBCDriver",
            "com.internetcds.jdbc.tds.Driver",
            "org.gjt.mm.mysql.Driver",
            "oracle.jdbc.driver.OracleDriver",
            "oracle.jdbc.driver.OracleDriver",
            "com.pointbase.net.netJDBCDriver",
            "org.postgresql.Driver",
            "com.progress.sql.jdbc.JdbcProgressDriver",
            "com.sap.dbtech.jdbc.DriverSapDB",
            "com.sybase.jdbc2.jdbc.SybDriver",
            "com.sybase.jdbc2.jdbc.SybDriver"
    };
    /**
     * These are the text names of the database types supported.
     */
    public static final String[] DATABASE_TYPES = {
            "Choose one...",
            "DB2",
            "DB2 AS/400",
            "DB2 OS390",
            "Firebird",
            "FrontBase",
            "HypersonicSQL",
            "Informix",
            "Ingres",
            "Interbase",
            "Mckoi SQL",
            "Microsoft SQL Server",
            "MySQL",
            "Oracle (any version)",
            "Oracle 9/10g",
            "Pointbase",
            "PostgreSQL",
            "Progress",
            "SAP DB",
            "Sybase",
            "Sybase Anywhere"
    };
    /**
     * Names of the default database URLs for each dialect.
     */
    public static final String[] DATABASE_URLS = {
            "",
            "jdbc:db2//{host_name}:6789/{dbname}",
            "jdbc:db2//{host_name}:6789/{dbname}",
            "jdbc:db2//{host_name}:6789/{dbname}",
            "jdbc:firebirdsql:[//host[:port]/]{database}",
            "jdbc:FrontBase://{host}:{port}/{database}",
            "jdbc:hsqldb:http://{server}[:{1476}]",
            "jdbc:informix-sqli://{host}:{port}/{dbname}: INFORMIXSERVER={ServerName}",
            "jdbc:edbc://{host}:21072/{host}::{dbname}",
            "jdbc:interbase://{server}/{full_db_path}",
            "jdbc:mckoi://{host}[:9157][/{schema}]",
            "jdbc:freetds:sqlserver://{hostname}[:{4100}]/{dbname}[;{property}={value}[;...]]",
            "jdbc:mysql://{hostname}[{:3306}]/{dbname}",
            "jdbc:oracle:thin:@{server}[:{1521}]:{dbname}",
            "jdbc:oracle:thin:@{server}[:{1521}]:{dbname}",
            "jdbc:pointbase:embedded:{dbname}",
            "jdbc:postgresql:[{//host}[{:5432}/]]{dbname}",
            "jdbc:progress:T:{host}:{port|service-name}:{dbname} -user {userid} -password {password}",
            "jdbc:sapdb:[//host/]dbname[?name=value[&amp;name=value]*]",
            "jdbc:sybase:Tds:{host}:{port}/{DBNAME}",
            "jdbc:sybase:Tds:{host}:{port}?ServiceName={DBNAME}"
    };
    /**
     * Webapp-relative path to the hibernate config file.
     */
    public static final String HIBERNATE_CONFIG = "/WEB-INF/classes/hibernate"
            + ".cfg.xml";

    /**
     * Name of the hibernate dialect property.
     */
    public final static String HIBERNATE_PROPERTY_DATABASE_DIALECT =
        "dialect";
    /**
     * Name of the hibernate connection driver property.
     */
    public final static String HIBERNATE_PROPERTY_DATABASE_DRIVER =
        "hibernate.connection.driver_class";
    /**
     * Name of the hibernate database password property.
     */
    public final static String HIBERNATE_PROPERTY_DATABASE_PASSWORD =
        "hibernate.connection.password";
    /**
     * Name of the hibernate connection URL property.
     */
    public final static String HIBERNATE_PROPERTY_DATABASE_URL =
        "hibernate.connection.url";
    /**
     * Name of the hibernate connection user name property.
     */
    public final static String HIBERNATE_PROPERTY_DATABASE_USER_NAME =
        "hibernate.connection.username";
    /**
     * File extension of the <strong>HypersonicSQL</strong> database lock file.
     */
    public static final String HSQLDB_LOCK_SUFFIX = ".lck.lck";
    /**
     * File extension of the <strong>HypersonicSQL</strong> database properties.
     */
    public static final String HSQLDB_PROPERTIES_SUFFIX = ".properties";
    /**
     * File extension of the <strong>HypersonicSQL</strong> database script.
     */
    public static final String HSQLDB_SCRIPT_SUFFIX = ".script";
    /**
     * Path and filename of the start <strong>HypersonicSQL</strong> database
     * we'll copy over.
     */
    public static final String START_DB = "/db/igwstart";

    /**
     * Private default constructor enforces utility class behavior.
     */
    private HibernateSetupConstants() {
    }
}

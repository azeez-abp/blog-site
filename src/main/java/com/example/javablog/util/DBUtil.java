
/*package com.example.javablog.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/java_blog";
    private static final String USER = "brizit-user-002";
    private static final String PASSWORD = "__Brizit2024__";
    private static final Logger logger = LogManager.getLogger("com.example.javablog.logs.error");

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
          try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error("Database connection failed", e);
            return null;
        }
    }
}
*/
package com.example.javablog.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.javablog.config.DBConfig;

public class DBUtil {
    private static final Logger logger = LogManager.getLogger(DBUtil.class);
    private static final String DB_URL;
    private static final String DB_USER;
    private static final String DB_PASSWORD;

    static {
        try {
            // Load environment variables
            String host = DBConfig.getHost();
            String port = DBConfig.getPort();
            String dbName = DBConfig.getDbName();
            DB_URL = DBConfig.getUrl();
            DB_USER = DBConfig.getUser();
            DB_PASSWORD = DBConfig.getPassword();

            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("MySQL JDBC Driver not found", e);
            throw new RuntimeException("Failed to load MySQL JDBC Driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            logger.error("Failed to connect to database: " + DB_URL, e);
            throw e;
        }
    }
}
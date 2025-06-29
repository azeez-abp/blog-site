package com.example.javablog.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/java_blog";
    private static final String USER = "brizit-user-002";
    private static final String PASSWORD = "__Brizit2024__";
    private static final Logger logger = LogManager.getLogger(/*DBUtil.class*/ "com.example.javablog.logs.error");

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

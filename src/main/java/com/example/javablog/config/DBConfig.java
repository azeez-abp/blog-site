
package com.example.javablog.config;

import io.github.cdimascio.dotenv.Dotenv;

public class DBConfig{
    private static final Dotenv dotenv = Dotenv.load();
    private static final String HOST = dotenv.get("DB_HOST", "localhost");
    private static final String PORT = dotenv.get("DB_PORT", "3306");
    private static final String DB_NAME = dotenv.get("DB_NAME", "java_blog");
    private static final String URL = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC", HOST, PORT, DB_NAME);
    private static final String USER = dotenv.get("DB_USER", "brizit-user-002");
    private static final String PASSWORD = dotenv.get("DB_PASS", "__Brizit2024__");

    public static String getUrl() {
        return URL;
    }
    public static String getUser() {
        return USER;    
    }

    public static String getPassword() {
        return PASSWORD;
    }
    public static String getHost() {
        return HOST;
    }
    public static String getPort() {
        return PORT;
    }
    public static String getDbName() {
        return DB_NAME;
    }

}
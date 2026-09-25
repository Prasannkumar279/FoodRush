package com.tap.Utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String DB_HOST =
            System.getenv().getOrDefault("DB_HOST", "localhost");

    private static final String DB_PORT =
            System.getenv().getOrDefault("DB_PORT", "3306");

    private static final String DB_NAME =
            System.getenv().getOrDefault("DB_NAME", "instantfood");

    private static final String USERNAME =
            System.getenv().getOrDefault("DB_USERNAME", "root");

    private static final String PASSWORD =
            System.getenv().getOrDefault("DB_PASSWORD", "root");

    private static final String URL =
            "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
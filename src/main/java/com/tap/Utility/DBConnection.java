package com.tap.Utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/instandfood";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection con;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
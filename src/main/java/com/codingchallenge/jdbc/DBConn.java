package com.codingchallenge.jdbc;

import java.sql.*;

public class DBConn {
    private static Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/finance_management_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "shivamlovespizza";

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}
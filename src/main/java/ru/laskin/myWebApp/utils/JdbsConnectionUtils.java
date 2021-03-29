package ru.laskin.myWebApp.utils;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbsConnectionUtils {
    static String url = "jdbc:postgresql://localhost:5433/postgres";
    static String userName = "user";
    static String password = "password";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }
}

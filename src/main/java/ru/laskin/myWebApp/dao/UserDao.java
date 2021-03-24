package ru.laskin.myWebApp.dao;

import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.utils.JdbsConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
   private static Connection connection;


    static {
        try {
            connection = JdbsConnectionUtils.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<User> getAllUsers () throws SQLException {
        Statement statement = connection.createStatement();

        List<User> users = new ArrayList<>();

        try (ResultSet rs = statement.executeQuery("select * from users")) {
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String login = rs.getString("login");
                String email = rs.getString("email");
                Boolean adminRole = rs.getBoolean("admin_role");

                users.add(new User(id, login, email, adminRole));
            }
        }
        return users;
    }
}

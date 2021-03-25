package ru.laskin.myWebApp.dao;

import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.utils.JdbsConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
   private static Connection connection;

    static {
        try {
            connection = JdbsConnectionUtils.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers () throws SQLException {
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

    public void saveUser(User user) throws SQLException {

        String login = user.getLogin();
        String email = user.getEmail();
        Boolean adminRole = user.isAdminRole();

        PreparedStatement statement = connection.prepareStatement("insert into users (login, email, admin_role) VALUES (?, ?, ?)");
        statement.setString(1, login);
        statement.setString(2, email);
        statement.setBoolean(3, adminRole);
        statement.execute();
    }
}

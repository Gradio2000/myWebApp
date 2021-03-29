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
        ResultSet rs = statement.executeQuery("select * from users order by user_id");
        return createUserList(rs);
    }

    public void saveUser(User user) throws SQLException {
        String name = user.getName();
        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();
        String adminRole = user.getAdminRole();

        PreparedStatement statement = connection.prepareStatement("insert into users (name, login, password, email, admin_role) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, name);
        statement.setString(2, login);
        statement.setString(3, password);
        statement.setString(4, email);
        statement.setString(5, adminRole);
        statement.execute();
    }

    public User getUserById(int id) {
        List<User> users = null;
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from users where user_id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);
            users = createUserList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users.get(0);
    }

    public User getUserByLogin(String login){
        List<User> users = null;
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from users where login = " + "'" + login + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            users = createUserList(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            User user = users.get(0);
            return user;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void deleteUser(int id){
        try {
            PreparedStatement statement = connection.prepareStatement("delete from users where user_id=?");
            statement.setInt(1, id);
            statement.executeQuery();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> createUserList(ResultSet rs){
        List<User> users = null;
        try {
            users = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String adminRole = rs.getString("admin_role");
                users.add(new User(id, name, login, password, email, adminRole));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }


    public void updateUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("update users set name = ?, login = ?, email = ?, admin_role = ? where user_id = ?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getAdminRole());
            statement.setInt(5, user.getUserId());
            statement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

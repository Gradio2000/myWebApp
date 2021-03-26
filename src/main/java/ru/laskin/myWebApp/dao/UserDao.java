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
        ResultSet rs = statement.executeQuery("select * from users");
        return createUserList(rs);
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
                String login = rs.getString("login");
                String email = rs.getString("email");
                Boolean adminRole = rs.getBoolean("admin_role");
                users.add(new User(id, login, email, adminRole));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }


}

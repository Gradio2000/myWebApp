package ru.laskin.myWebApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.utils.JdbsConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Connection connection;

    static {
        try {
            connection = JdbsConnectionUtils.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers () throws SQLException {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY user_id", new BeanPropertyRowMapper<>(User.class));
    }

    public void saveUser(User user) throws SQLException {
        jdbcTemplate.update("INSERT INTO users (name, login, password, email, admin_role, position) " +
                        "VALUES (?,?,?,?,?,?)",
                user.getName(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getAdminRole(),
                user.getPosition()
        );
    }

    public User getUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE user_id = ?", new BeanPropertyRowMapper<>(User.class), id)
                .stream()
                .findAny().orElse(null);
    }

    public User getUserByLogin(String login){
        return jdbcTemplate.query("SELECT * FROM users WHERE login = ?", new BeanPropertyRowMapper<>(User.class), login)
                .stream().findAny().orElse(null);
    }

    public void deleteUser(int id){
        jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", id);

    }

    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE users set name=?, login=?, email=?, admin_role=?, position=? WHERE user_id=?",
                user.getName(),
                user.getLogin(),
                user.getEmail(),
                user.getAdminRole(),
                user.getPosition(),
                user.getUserId()
        );

    }
}

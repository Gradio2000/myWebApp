package ru.laskin.myWebApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.User;

import java.sql.*;
import java.util.List;

@Component
public class UserDao {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers () throws SQLException {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY user_id", userRowMapper);
    }

    public void saveUser(User user) {
        jdbcTemplate.update("INSERT INTO users (name, login, password, email, admin_role, position, key) " +
                        "VALUES (?,?,?,?,?,?,?)",
                user.getName(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getAdminRole(),
                user.getPosition(),
                user.getKey()
        );
    }

    public User getUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE user_id = ?", userRowMapper, id)
                .stream()
                .findAny().orElse(null);
    }

    public User getUserByLogin(String login){
        return jdbcTemplate.query("SELECT * FROM users WHERE login = ?", userRowMapper, login)
                .stream().findAny().orElse(null);
    }

    public void deleteUser(int id){
        jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", id);

    }

    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE users set name=?, login=?, email=?, admin_role=?, position=?, registered=? WHERE user_id=?",
                user.getName(),
                user.getLogin(),
                user.getEmail(),
                user.getAdminRole(),
                user.getPosition(),
                user.isRegistered(),
                user.getUserId()
        );
    }

    public void changePassword(int id, String password) {
        jdbcTemplate.update("UPDATE users set password=? WHERE user_id=?", password, id);
    }
}

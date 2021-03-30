package ru.laskin.myWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.UserDao;
import ru.laskin.myWebApp.model.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (SQLException throwables) {
            System.out.println("Ошибка выполнения UserServiceImpl.getAllUsers()");
            return null;
        }
    }

    public void saveUser(User user) throws SQLException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAdminRole("USER");
        userDao.saveUser(user);
    }

    public User getUserById(int id){
        return userDao.getUserById(id);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    public void updateUser(User user) {
        if (user.getAdminRole() == ""){
            user.setAdminRole("USER");
        }
        userDao.updateUser(user);
    }

    public User getUserBylogin(String login) {
       return userDao.getUserByLogin(login);
    }
}

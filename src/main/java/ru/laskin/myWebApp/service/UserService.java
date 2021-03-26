package ru.laskin.myWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.UserDao;
import ru.laskin.myWebApp.model.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (SQLException throwables) {
            System.out.println("Ошибка выполнения UserServiceImpl.getAllUsers()");
            return null;
        }
    }

    public void saveUser(User user) throws SQLException {
        userDao.saveUser(user);
    }

    public User getUserById(int id){
        return userDao.getUserById(id);
    }


    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }
}

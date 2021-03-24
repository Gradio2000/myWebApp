package ru.laskin.myWebApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.laskin.myWebApp.dao.UserDao;
import ru.laskin.myWebApp.model.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
//        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
//        System.out.println("Bean definition names: " + Arrays.toString(appCont.getBeanDefinitionNames()));

        List<User> userList = UserDao.getAllUsers();
        for (User user : userList){
            System.out.println(user.getUserId() + " " + user.getLogin() +
                    " " + user.getEmail());
        }
    }
}

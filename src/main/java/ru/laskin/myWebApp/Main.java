package ru.laskin.myWebApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.UserService;

import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext appCont = new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/dispatcher-servlet.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCont.getBeanDefinitionNames()));

        UserService service = appCont.getBean("userService", UserService.class);
        User user = service.getUserBylogin("ddd");
        System.out.println(user.getUserId() + " " + user.getLogin() + " " + user.getPassword());
    }
    
}

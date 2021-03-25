package ru.laskin.myWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.laskin.myWebApp.dao.UserDao;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.UserService;

import java.sql.SQLException;


@Controller
public class MainController {

    private UserService service;

    @Autowired
    public MainController(UserService service) {
        this.service = service;
    }

    @GetMapping("/main")
    public String getStart(Model model) throws SQLException {
        model.addAttribute("users", service.getAllUsers());
        return "index";
    }

    @GetMapping("/add_user")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return"add_user";
    }

    @PostMapping("/new_user")
    public String formUser (@ModelAttribute User user) throws SQLException {
        service.getAllUsers();
        return "redirect:/main";
    }
}

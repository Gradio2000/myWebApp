package ru.laskin.myWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private TestService testService;

    @GetMapping("/greeting")
    public String greeting(Model model){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getAdminRole().equals("ADMIN")){
            return "allUsers";
        }
        SecurityContextHolder.getContext().getAuthentication().getCredentials();
        model.addAttribute("authUser", authUser.getName());
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("principal", principal);
        Set<String> posSet = positionService.getAllPosition();
        model.addAttribute("posSet", posSet);
        User user = userService.getUserById(authUser.getUserId());
        model.addAttribute("user", user);
        return "greeting";
    }

    @GetMapping("/new_user")
    public String formUser(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/new_user")
    public String formUser (@ModelAttribute User user) throws SQLException {
        userService.saveUser(user);
        return "login";
    }

    @PostMapping("/reUpdate")
    public String reUpdate(@ModelAttribute User user, Model model){
        userService.updateUser(user);

        model.addAttribute("allTest", testService.getAllTests());
        return "testPage";
    }
}

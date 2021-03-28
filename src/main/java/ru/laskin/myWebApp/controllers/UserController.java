package ru.laskin.myWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/greeting")
    public String greeting(Model model){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SecurityContextHolder.getContext().getAuthentication().getCredentials();
        model.addAttribute("authUser", authUser.getName());
        return "greeting";
    }
}

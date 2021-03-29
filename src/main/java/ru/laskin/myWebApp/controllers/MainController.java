package ru.laskin.myWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.UserService;


@Controller
public class MainController {

    private UserService service;

    @Autowired
    public MainController(UserService service) {
        this.service = service;
    }

    @GetMapping("/main")
    public String main (){
        return "main";
    }

    @GetMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error, Model model){
        if (Boolean.TRUE.equals(error)){
            model.addAttribute("error", true);
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
}

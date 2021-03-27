package ru.laskin.myWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.laskin.myWebApp.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/greeting")
    public String greeting(){
        return "greeting";
    }

}

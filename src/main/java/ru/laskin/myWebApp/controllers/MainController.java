package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.laskin.myWebApp.model.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    static List<User> users = new ArrayList<>();

    @GetMapping("/main")
    public String getStart(Model model){
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/add_user")
    public String addUser(){
        return"add_user";
    }

    @PostMapping("/new_user")
    public String formUser (@ModelAttribute User user){
        users.add(user);
        return "redirect:/main";
    }
}

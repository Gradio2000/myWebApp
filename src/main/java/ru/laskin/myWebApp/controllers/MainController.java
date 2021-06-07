package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.model.User;


@Controller
public class MainController {

    @GetMapping("/login")
    public String login(@RequestParam(required = false) Boolean error, Model model){
        model.addAttribute("user", new User());
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute User user, BindingResult bindingResult, Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

}

package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.User;


@Controller
public class MainController {

    @GetMapping("/")
    public String enter(){
        return "redirect:login";
    }

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

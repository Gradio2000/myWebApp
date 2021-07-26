package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.controllers.userModule.ResultController;
import ru.laskin.myWebApp.model.User;

import java.util.logging.Logger;


@Controller
public class MainController {
    private static final Logger logger = Logger.getLogger(ResultController.class.getName());

    @GetMapping("/")
    public String enter(){
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) Boolean error, Model model){
        logger.info("вход");
        model.addAttribute("user", new User());
        model.addAttribute("error", error);
        logger.info("выход");
        return "login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute User user, Model model){
        logger.info("вход" + user.getName());
        model.addAttribute("user", new User());
        logger.info("выход" + user.getName());
        return "registration";
    }

}

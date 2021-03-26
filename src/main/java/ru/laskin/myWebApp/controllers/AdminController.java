package ru.laskin.myWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class AdminController {

    @Autowired
    UserService service;

    @GetMapping("/allUsers")
    public String getStart(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "list_users";
    }

    @PostMapping("/new_user")
    public String formUser (@ModelAttribute User user, HttpServletRequest request, Model model) throws SQLException {
        if (!request.getParameter("userId").equals("0")){
            service.updateUser(user);
            return "redirect:/allUsers";
        }

        service.saveUser(user);
        return "redirect:/allUsers";
    }

    @GetMapping("/users/update")
    public String updateUser(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        User user1 = service.getUserById(id);
        model.addAttribute("user", user1);
        return"add_user";
    }

    @GetMapping("/users/delete")
    public String deleteUser(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteUser(id);
        return "redirect:/allUsers";
    }
}

package ru.laskin.myWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    UserService service;

    @GetMapping("/allUsers")
    public String getStart(Model model) {
        model.addAttribute("users", service.getAllUsers());
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authUser", authUser.getName());
        return "list_users";
    }

    @GetMapping("/users/update")
    public String updateUser(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        return"registration";
    }

    @GetMapping("/users/delete")
    public String deleteUser(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteUser(id);
        return "redirect:/allUsers";
    }
}

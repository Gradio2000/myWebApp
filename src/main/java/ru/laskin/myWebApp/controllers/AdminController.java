package ru.laskin.myWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.Position;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private UserService service;
    private PositionService positionService;

    public AdminController(UserService service, PositionService positionService) {
        this.service = service;
        this.positionService = positionService;
    }

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
        List<Position> positionSet = positionService.getAllPosition();
        model.addAttribute("posSet", positionSet);
        return"updateForm";
    }

    @GetMapping("/users/delete")
    public String deleteUser(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteUser(id);
        return "redirect:/allUsers";
    }

    @PostMapping("/updateUser")
    public String formUser (@ModelAttribute User user,
                            HttpServletRequest request,
                            Model model) throws SQLException {
            service.updateUser(user);
            return "redirect:/allUsers";
    }
}

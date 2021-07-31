package ru.laskin.myWebApp.controllers.adminModule;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.controllers.ExceptionController;
import ru.laskin.myWebApp.model.Position;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.PositionService;
import ru.laskin.myWebApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private static final Logger log = Logger.getLogger(AdminController.class.getName());

    private final UserService userService;
    private final PositionService positionService;
    private final ExceptionController exceptionController;

    public UserController(UserService userService, PositionService positionService, ExceptionController exceptionController) {
        this.userService = userService;
        this.positionService = positionService;
        this.exceptionController = exceptionController;
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model, HttpServletRequest request) {
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getUserById(authUser.getUserId());
            request.setAttribute("user", user);

            model.addAttribute("users", userService.getAllUsers(user.getCompany().getIdCompany()));
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "list_users";
    }

    @GetMapping("/users/update")
    public String updateUser(HttpServletRequest request, Model model){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", authUser);

            int id = Integer.parseInt(request.getParameter("id"));
            User user = userService.getUserById(id);
            model.addAttribute("user", user);
            List<Position> positionSet = positionService.getAllPositionWithoutAdminRole(user.getCompany().getIdCompany());
            model.addAttribute("posSet", positionSet);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return"updateForm";
    }

    @PostMapping("/updateUser")
    public String formUser (@ModelAttribute User user, HttpServletRequest request){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", authUser);
            userService.updateUser(user);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/allUsers";
    }

    @GetMapping("/users/delete")
    public String deleteUser(HttpServletRequest request, @RequestParam Integer id){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userService.deleteUser(id);
            request.setAttribute("user", authUser);
            log.info("Выход");
        } catch (NumberFormatException e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/allUsers";
    }
}

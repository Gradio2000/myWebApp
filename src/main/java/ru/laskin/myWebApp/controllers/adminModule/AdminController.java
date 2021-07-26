package ru.laskin.myWebApp.controllers.adminModule;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.laskin.myWebApp.controllers.ExceptionController;
import ru.laskin.myWebApp.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private static final Logger log = Logger.getLogger(AdminController.class.getName());

    private final ExceptionController exceptionController;

    public AdminController(ExceptionController exceptionController) {
        this.exceptionController = exceptionController;
    }

    @GetMapping("/adminModule")
    public String openAdminModule(HttpServletRequest request){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            request.setAttribute("user", authUser);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "adminModule";
    }
}

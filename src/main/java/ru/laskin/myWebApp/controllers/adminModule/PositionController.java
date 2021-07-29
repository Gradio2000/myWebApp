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
import ru.laskin.myWebApp.validation.DeletePositionValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class PositionController {
    private static final Logger log = Logger.getLogger(AdminController.class.getName());

    private final PositionService positionService;
    private final DeletePositionValidation deletePositionValidation;
    private final ExceptionController exceptionController;
    private final UserService userService;

    public PositionController(PositionService positionService, DeletePositionValidation deletePositionValidation,
                              ExceptionController exceptionController, UserService userService) {
        this.positionService = positionService;
        this.deletePositionValidation = deletePositionValidation;
        this.exceptionController = exceptionController;
        this.userService = userService;
    }


    @GetMapping("/allPosition")
    public String getAllPositions(Model model, HttpServletRequest request, HttpSession session){
        log.info("Вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getUserById(authUser.getUserId());
            model.addAttribute("user", user);

            session.setAttribute("positions", positionService.getAllPosition(user.getCompany().getIdCompany()));
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "list_positions";
    }

    @PostMapping("/editPosition")
    public String editPositions(HttpServletRequest request, HttpSession session){
        log.info("Вход");
        try {
            positionService.updateAllPosition(request, session);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/greeting";
    }

    @GetMapping("/deletePosition")
    public String deletePosition(@RequestParam String posId, HttpServletRequest request){
        log.info("Вход");
        try {
            Position position = positionService.getPositionById(Integer.parseInt(posId));
            if (deletePositionValidation.validate(position)){
                request.setAttribute("errorMessage", "Внимание! Эту должность удалять нельзя, т.к. за ней закреплен пользователь!");
                return "list_positions";
            }
            positionService.deletePosition(Integer.parseInt(posId));
            log.info("Выход");
        } catch (NumberFormatException e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/allPosition";
    }

    @PostMapping("/addPosition")
    public String addPosition(@ModelAttribute Position pos, HttpServletRequest request){
        log.info("Вход");
        try {
            positionService.addPosition(pos);
            log.info("Выход");
        } catch (Exception e) {
            exceptionController.printException(request, log, e);
            return "exception";
        }
        return "redirect:/allPosition";
    }
}

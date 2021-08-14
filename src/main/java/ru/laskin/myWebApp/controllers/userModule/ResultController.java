package ru.laskin.myWebApp.controllers.userModule;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.Statistic;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
public class ResultController {
    private static final Logger logger = Logger.getLogger(ResultController.class.getName());

    private final TestService testService;
    private final UserService userService;

    public ResultController(TestService testService, UserService userService) {
        this.testService = testService;
        this.userService = userService;
    }

    @PostMapping("/finish")
    public String finish(@RequestParam Integer attemptId, HttpServletRequest request, HttpSession session){
        logger.log(Level.INFO, "вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getUserById(authUser.getUserId());

            Statistic statistic = testService.recordAttemptAndCheckResults(attemptId, request, session);

            request.setAttribute("user", user);
            session.setAttribute("statistic", statistic);

            logger.log(Level.INFO, "выход");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return "testResult";
    }

    @PostMapping("/detailResult")
    public String detailResult(HttpSession session, HttpServletRequest request, Model model){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(authUser.getUserId());

        Statistic statistic = (Statistic) session.getAttribute("statistic");
        model.addAttribute(statistic);
        request.setAttribute("user", user);
        return "detailResult";
    }

    @PostMapping("detailResultForAdmin")
    public String detailResultForAdmin(Model model, HttpSession session, HttpServletRequest request){
        List<Statistic> statisticList = (List<Statistic>) session.getAttribute("statisticList");
        User user = (User) session.getAttribute("userForStatistic");
        Map<String, String[]> map = request.getParameterMap();
        String statisticId = map.get("statisticId")[0];
        Statistic statistic = statisticList.get(Integer.parseInt(statisticId));
        model.addAttribute(statistic);
        model.addAttribute(user);
        return "detailResult";
    }

    @GetMapping("users/statistic")
    public String statisticOfUser(HttpServletRequest request, @RequestParam Integer id, HttpSession session){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        testService.getStatistic(id, session, request);
        return "statistic";
    }
}



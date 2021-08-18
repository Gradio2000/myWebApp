package ru.laskin.myWebApp.controllers.userModule;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.NewStatistic;
import ru.laskin.myWebApp.model.Statistic;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.ResultTestService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
public class ResultController {
    private static final Logger logger = Logger.getLogger(ResultController.class.getName());

    private final TestService testService;
    private final UserService userService;
    private final ResultTestService resultTestService;

    public ResultController(TestService testService, UserService userService, ResultTestService resultTestService) {
        this.testService = testService;
        this.userService = userService;
        this.resultTestService = resultTestService;
    }

    @PostMapping("/finish")
    public String finish(@RequestParam Integer attemptId, HttpServletRequest request, HttpSession session){
        logger.log(Level.INFO, "вход");
        try {
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.getUserById(authUser.getUserId());

            Statistic statistic = resultTestService.recordAttemptAndCheckResults(attemptId, request, session);

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
        return "detailResultForUser";
    }

    @GetMapping("detailResultForAdmin")
    public String detailResultForAdmin(HttpServletRequest request,
                                       @RequestParam String date,
                                       @RequestParam String testName,
                                       @RequestParam  int amountFalseAnswers,
                                       @RequestParam  int amountTrueAnswer,
                                       @RequestParam  String testResult,
                                       @RequestParam String time,
                                       @RequestParam  int amountQues,
                                       @RequestParam  double result,
                                       @RequestParam  double criteria,
                                       @RequestParam  int attemptId,
                                       @RequestParam int userId
                                       ){
        User user = userService.getUserById(userId);
        Statistic statistic = resultTestService.getDetailResultForAdmin(attemptId);
        NewStatistic newStatistic = new NewStatistic(date, testName, amountFalseAnswers, amountTrueAnswer, testResult,
                time, amountQues, result, criteria, attemptId);
        request.setAttribute("newStatistic", newStatistic);
        request.setAttribute("user", user);
        request.setAttribute("statistic", statistic);
        return "detailResultForAdmin";
    }

    @GetMapping("users/statistic")
    public String statisticOfUser(HttpServletRequest request, @RequestParam Integer id){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        resultTestService.getStatistic(id, request);
        return "statistic";
    }
}



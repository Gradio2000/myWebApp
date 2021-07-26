package ru.laskin.myWebApp.controllers.userModule;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.AttemptTest;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.AttemptTestService;
import ru.laskin.myWebApp.service.ResultTestService;
import ru.laskin.myWebApp.service.TestService;
import ru.laskin.myWebApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;


@Controller
public class TestProcessingController {

    private final ResultTestService resultTestService;
    private final TestService testService;
    private final AttemptTestService attemptTestService;
    private final UserService userService;

    public TestProcessingController(ResultTestService resultTestService, TestService testService, AttemptTestService attemptTestService, UserService userService) {
        this.resultTestService = resultTestService;
        this.testService = testService;
        this.attemptTestService = attemptTestService;
        this.userService = userService;
    }

    @GetMapping("/getTest")
    public String getTest(@RequestParam String testId, @RequestParam(required = false) Integer attemptId,
                          Model model, HttpServletRequest request, HttpSession session){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(authUser.getUserId());

        Test test = testService.getTestById(Integer.parseInt(testId));

        if (test.getQuesAmount() != null){
            //выбрать случайные вопросы в заданном в настройках теста количестве
            if (test.isQuesMix()){
                test = testService.getShuffleTest(test);
            }
        }

        //записываем в БД новую попытку
        if (attemptId == null) {
            Timestamp timestamp = new Timestamp(new Date().getTime());
            AttemptTest attemptTest = new AttemptTest(timestamp, test.getTestId(), authUser.getUserId(), 0, test.getQuestions().size());
            attemptId = attemptTestService.saveAttemptTest(attemptTest);
        }

        //записываем в бд заданные юзеру вопросы
        testService.registerTest(attemptId, test);

        session.setAttribute("tests", test);
        model.addAttribute("user", user);
        model.addAttribute("attemptId", attemptId);
        return "testProcessing";
    }

    @PostMapping("/oper")
    public String saveUserAnswer(HttpServletRequest request,
                                 @RequestParam Integer questionId,
                                 @RequestParam Integer attemptId){
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("user", authUser);

        resultTestService.saveResultTest(request, questionId, attemptId);
        return "testProcessing";
    }

}

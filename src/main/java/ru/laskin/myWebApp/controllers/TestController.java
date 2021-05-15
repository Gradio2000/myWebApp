package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.AttemptTest;
import ru.laskin.myWebApp.model.ResultTest;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.service.AttemptTestService;
import ru.laskin.myWebApp.service.MainService;
import ru.laskin.myWebApp.service.ResultTestService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    private final AttemptTestService attemptTestService;
    private final ResultTestService resultTestService;
    private final MainService mainService;

    public TestController(AttemptTestService attemptTestService,
                          ResultTestService resultTestService,
                          MainService mainService) {
        this.attemptTestService = attemptTestService;
        this.resultTestService = resultTestService;
        this.mainService = mainService;
    }

    //первый вариант
    @PostMapping("/testResult")
    public String testResult (@RequestParam("testId") int testId,
                              @RequestParam("userId") int userId,
                              @RequestParam("questionId") List<Integer> questionId,
                              @RequestParam("attemptId") Integer attemptId,
                              HttpServletRequest request,
                              Model model){

        //Это массив check из testProcessing (ответы пользователя)
        String[] userChecks = request.getParameterValues("check");

        //Создаем ResultTestы
        for (Integer integer : questionId) {
            for (String userCheck : userChecks) {
                ResultTest resultTest = new ResultTest(attemptId, integer, Integer.parseInt(userCheck));
                resultTestService.saveResultTest(resultTest);
            }
        }



            //Получаем из БД ResultTest для этой попытки и отправляем в представление
//            List<String> result = mainService.getResult(attemptId, timestamp, testId, userId);
//            model.addAttribute("result", result);
            return "testResult";
    }

    @PostMapping("/saveUserAnswer")
    public String saveUserAnswer(HttpServletRequest request,
                                 @RequestParam Integer questionId,
                                 @RequestParam Integer attemptId){
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] checks = parameterMap.get("check");
        String testId = parameterMap.get("testId")[0];

        //Создаем ResultTestы
        if (checks != null){
            for (int i = 0; i < checks.length; i++) {
                ResultTest resultTest = new ResultTest(attemptId, questionId, Integer.parseInt(checks[i]));
                resultTestService.saveResultTest(resultTest);
            }
        }


        return "redirect:getTest?testId=" + testId + "&attemptId=" + attemptId;
    }

}

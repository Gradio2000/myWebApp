package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.laskin.myWebApp.model.AttemptTest;
import ru.laskin.myWebApp.model.ResultTest;
import ru.laskin.myWebApp.service.AttemptTestService;
import ru.laskin.myWebApp.service.MainService;
import ru.laskin.myWebApp.service.ResultTestService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @PostMapping("/testResult")
    public String testResult (@RequestParam("testId") int testId,
                              @RequestParam("userId") int userId,
                              @RequestParam("questionId") List<Integer> questionId,
                              HttpServletRequest request,
                              Model model){

        //создаем попытку и сохраняем ее в БД
        Timestamp timestamp = new Timestamp(new Date().getTime());
        AttemptTest attemptTest = new AttemptTest(timestamp, testId, userId);
        int attemptId = attemptTestService.saveAttemptTest(attemptTest);

        //Это массив check из testProcessing (ответы пользователя)
        String[] answerId = request.getParameterValues("check");


        //главная логика проверки теста. Надо перенести в сервис
            for (int i = 0; i < questionId.size(); i++) {
                //это служебный questionId=0. Его просто игнорируем.
                if (questionId.get(i) == 0) continue;

                //создаем ResultTest и отправляем его в БД.
                if (answerId != null && i <= answerId.length - 1){
                    resultTestService.saveResultTest(new ResultTest(attemptId, questionId.get(i), Integer.parseInt(answerId[i])));
                }
                else {
                    resultTestService.saveResultTest(new ResultTest(attemptId, questionId.get(i)));
                }
            }

            //Получаем из БД ResultTest для этой попытки и отправляем в представление
            List<String> result = mainService.getResult(attemptId, timestamp, testId, userId);
            model.addAttribute("result", result);
            return "testResult";
    }
}

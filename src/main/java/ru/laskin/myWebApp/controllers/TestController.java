package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laskin.myWebApp.model.*;
import ru.laskin.myWebApp.service.AttemptTestService;
import ru.laskin.myWebApp.service.MainService;
import ru.laskin.myWebApp.service.ResultTestService;
import ru.laskin.myWebApp.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class TestController {

    private final ResultTestService resultTestService;
    private final TestService testService;

    public TestController(ResultTestService resultTestService, TestService testService) {
        this.resultTestService = resultTestService;
        this.testService = testService;
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

    @PostMapping("/oper")
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
        return "testProcessing";
    }

    @GetMapping("/finish")
    public String testFinish(@RequestParam Integer attemptId, @RequestParam Integer testId){
        List<ResultTest> resultTestList = resultTestService.getResultTest(attemptId);
        Map<Integer, List<Integer>> mapOfUserAnswers = new HashMap<>();
        for (ResultTest resultTest : resultTestList){
            List<Integer> answersIdList = mapOfUserAnswers.get(resultTest.getQuestionId());
            if (answersIdList == null) answersIdList = new ArrayList<>();
            answersIdList.add(resultTest.getAnswerId());
            mapOfUserAnswers.put(resultTest.getQuestionId(), answersIdList);
        }

        Test test = testService.getTestById(testId);
        List<Question> questionList = test.getQuestions();

        for (Question question : questionList){
            List<Answer> answerList = question.getAnswers();
            for (Answer answer : answerList){
                if (answer.isRight()){
                    if (mapOfUserAnswers.get(question.getQuestionId()).contains(answer.getAnswerId())){
                        System.out.println("на ответ " + answer.getAnswerId() + "дан правильный ответ");
                    }
                    else {
                        System.out.println("на ответ " + answer.getAnswerId() + "нужно было ответить");
                    }
                }
                else {
                   if (mapOfUserAnswers.get(question.getQuestionId()).contains(answer.getAnswerId())){
                       System.out.println("на ответ " + answer.getAnswerId() + "зря ответил. Это не правильный ответ");
                   }
                }
            }
        }

        return "testResult";
    }

}

package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.service.TestService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {

    private final TestService testService;

    public FileController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String uploadFile (){
        return "loadFile";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        File newFile = new File("/Users/aleksejlaskin/Documents/111.txt");
        file.transferTo(newFile);

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(newFile)));

        String line;
        Question question;
        Answer answer;
        List<Question> questionList = new ArrayList<>();
        List<Answer> answerList;

        int i = 0;
        while ((line = reader.readLine()) != null) {
            String[] lineMass = line.split(";");

            //проверяем первую ячейку
            if (!lineMass[0].equals("")){
                question = new Question();
                question.setQuestionName(lineMass[0]);
                answerList = new ArrayList<>();
                i++;
            }
            else {
                if (questionList.size() != 0){
                    question = questionList.remove(i - 1);
                    answerList = question.getAnswers();
                }
                else {
                    question = new Question();
                    answerList = new ArrayList<>();
                }
            }

            answer = new Answer();

            //проверяем вторую ячейку
            if (!lineMass[1].equals("")){
                answer.setAnswerName(lineMass[1]);
            }

            //проверяем третью ячейку
            if (lineMass.length == 3) {
                answer.setRight(true);
            }

            answerList.add(answer);
            question.setAnswers(answerList);
            questionList.add(question);
        }
        reader.close();

        Test test = new Test("Загруженный тест", questionList);
        testService.saveTest(test);

        return "You successfully uploaded file=" + file.getName();
    }

}

package ru.laskin.myWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;
import ru.laskin.myWebApp.service.TestService;

import javax.servlet.http.HttpServletRequest;
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
    public String uploadFile (@RequestParam Integer id, HttpServletRequest request){
        request.setAttribute("id", id);
        return "loadFile";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam MultipartFile file, @RequestParam Integer id) throws IOException {


        try {
            File newFile = File.createTempFile("temp", null, null);
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

            Test test = testService.getTestById(id);
            test.setQuestions(questionList);
            testService.updateTest(test);

            newFile.deleteOnExit();
        } catch (IOException | IllegalStateException e) {
            return "errors/error";
        } catch (ArrayIndexOutOfBoundsException e){
            return "errors/error_array";
        }

        return "redirect:/tests/update?id=" + id;
    }

}

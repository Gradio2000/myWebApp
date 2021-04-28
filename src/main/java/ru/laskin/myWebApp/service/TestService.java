package ru.laskin.myWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.QuestionHiberDao;
import ru.laskin.myWebApp.dao.TestDao;
import ru.laskin.myWebApp.dao.TestHiberDao;
import ru.laskin.myWebApp.model.GroupTest;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;

import java.util.List;

@Service
public class TestService {

    private TestDao testDao;
    private TestHiberDao testHiberDao;
    private QuestionHiberDao questionHiberDao;


    public TestService(TestDao testDao, TestHiberDao testHiberDao, QuestionHiberDao questionHiberDao) {
        this.testDao = testDao;
        this.testHiberDao = testHiberDao;
        this.questionHiberDao = questionHiberDao;
    }

    public List<Test> getAllTests(){
        return testHiberDao.getAllTests();
    }

    public Test getTestById(int testId){
        return testHiberDao.getTestById(testId);
    }

    public List<Question> getAllQuestions(){
        return testDao.getAllQuestions();
    }

    public Question getQuestionById(int id){
        return testDao.getQuestionById(id);
    }

    public void updateTest(Test test){
        testHiberDao.updateTest(test);
    }

    public void saveTest(Test test){
        testHiberDao.saveTest(test);
    }

    public void deleteTestById(int id){
        testHiberDao.deleteTestById(id);
    }

    public void saveQuestion(Question question){
        questionHiberDao.saveQuestion(question);
    }

    public List<GroupTest> getAllGroupTest(){
        return testHiberDao.getAllGroup();
    }


}

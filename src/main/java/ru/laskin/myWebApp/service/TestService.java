package ru.laskin.myWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.TestDao;
import ru.laskin.myWebApp.dao.TestHiberDao;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;

import java.util.List;

@Service
public class TestService {

    private TestDao testDao;
    private TestHiberDao testHiberDao;


    public TestService(TestDao testDao, TestHiberDao testHiberDao) {
        this.testDao = testDao;
        this.testHiberDao = testHiberDao;
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


}

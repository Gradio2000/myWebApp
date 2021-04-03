package ru.laskin.myWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.TestDao;
import ru.laskin.myWebApp.model.Question;
import ru.laskin.myWebApp.model.Test;

import java.util.List;

@Service
public class TestService {

    private TestDao testDao;

    public TestService(TestDao testDao) {
        this.testDao = testDao;
    }

    public List<Test> getAllTests(){
        return testDao.getAllTests();
    }

    public List<Question> getAllQuestions(){
        return testDao.getAllQuestions();
    }

    public Question getQuestionById(int id){
        return testDao.getQuestionById(id);
    }
}

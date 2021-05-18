package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.ResultTestDao;
import ru.laskin.myWebApp.model.ResultTest;

import java.util.List;

@Service
public class ResultTestService {

    private ResultTestDao resultTestDao;

    public ResultTestService(ResultTestDao resultTestDao) {
        this.resultTestDao = resultTestDao;
    }

    public void saveResultTest(ResultTest resultTest){
        resultTestDao.saveResultTest(resultTest);
    }

    public List<ResultTest> getResultTest(Integer attemptId) {
        return resultTestDao.getAllResultByAttempt(attemptId);
    }
}

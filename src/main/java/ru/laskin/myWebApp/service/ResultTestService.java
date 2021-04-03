package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.ResultTestDao;
import ru.laskin.myWebApp.model.ResultTest;

@Service
public class ResultTestService {

    private ResultTestDao resultTestDao;

    public ResultTestService(ResultTestDao resultTestDao) {
        this.resultTestDao = resultTestDao;
    }

    public void saveResultTest(ResultTest resultTest){
        resultTestDao.saveResultTest(resultTest);
    }
}

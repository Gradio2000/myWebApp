package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.AttemptTestDao;
import ru.laskin.myWebApp.model.AttemptTest;

import java.util.List;

@Service
public class AttemptTestService {
    private AttemptTestDao attemptTestDao;

    public AttemptTestService(AttemptTestDao attemptTestDao) {
        this.attemptTestDao = attemptTestDao;
    }

    public Integer saveAttemptTest(AttemptTest attemptTest){
       return attemptTestDao.saveAttemptTest(attemptTest);
    }

    public AttemptTest getAttemptById(int id){
        return attemptTestDao.getAttemptById(id);
    }

    public List<AttemptTest> getAllAttemptByUserId(Integer id) {
        return attemptTestDao.getAllAttemptByUserId(id);
    }

    public void saveTimeOfAttempt(int attemptId, Integer timeOfAttempt) {
        attemptTestDao.updateAttemptTest(attemptId, timeOfAttempt);
    }
}

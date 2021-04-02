package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.AttemptTestDao;
import ru.laskin.myWebApp.model.AttemptTest;

@Service
public class AttemptTestService {
    private AttemptTestDao attemptTestDao;

    public AttemptTestService(AttemptTestDao attemptTestDao) {
        this.attemptTestDao = attemptTestDao;
    }

    public void saveAttemptTest(AttemptTest attemptTest){
        attemptTestDao.saveAttemptTest(attemptTest);
    }
}

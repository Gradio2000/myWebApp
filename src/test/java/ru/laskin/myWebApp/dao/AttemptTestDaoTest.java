package ru.laskin.myWebApp.dao;


import org.junit.jupiter.api.Test;
import ru.laskin.myWebApp.model.AttemptTest;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AttemptTestDaoTest {
    @Test
    void getAttemptById(){
        AttemptTestDao attemptTestDao = new AttemptTestDao();
        AttemptTest attemptTest = new AttemptTest(1, 1, new Timestamp(new Date().getTime()), 100);
        int id = attemptTestDao.saveAttemptTest(attemptTest);
        attemptTest.setAttemptId(id);
        AttemptTest attemptTestFromDB = attemptTestDao.getAttemptById(id);
        assertTrue(attemptTestFromDB != null);
        assertTrue(attemptTest.equals(attemptTestFromDB));
        attemptTestDao.deleteAttempt(attemptTest);
    }
}

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

    public String getTime(int timeOfAttempt) {
        if (timeOfAttempt == 0){
            return "Не учитывалось";
        }
        else {
            String seconds = "0" + (int) Math.floor(timeOfAttempt % 60);
            String minutes = "0" + (int) Math.floor((timeOfAttempt / 60.0) % 60);
            String hours = "0" + (int) Math.floor((timeOfAttempt / (60.0 * 60)) % 24);

            seconds = seconds.substring(seconds.length() - 2);
            minutes = minutes.substring(minutes.length() - 2);
            hours = hours.substring(hours.length() - 2);

            return hours + " : " + minutes + " : " + seconds;
        }
    }
}

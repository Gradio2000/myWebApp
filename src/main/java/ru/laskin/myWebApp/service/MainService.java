package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.*;
import ru.laskin.myWebApp.model.Answer;
import ru.laskin.myWebApp.model.AttemptTest;
import ru.laskin.myWebApp.model.ResultTest;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.utils.TimeUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    private final ResultTestDao resultTestDao;
    private final TestDao testDao;
    private final UserDao userDao;
    private final AnswersDao answersDao;
    private final TimeUtils timeUtils;

    public MainService(ResultTestDao resultTestDao, TestDao testDao,
                       UserDao userDao, AnswersDao answersDao, TimeUtils timeUtils) {
        this.resultTestDao = resultTestDao;
        this.testDao = testDao;
        this.userDao = userDao;
        this.answersDao = answersDao;
        this.timeUtils = timeUtils;
    }

}

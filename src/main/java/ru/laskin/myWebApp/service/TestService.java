package ru.laskin.myWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.TestDao;
import ru.laskin.myWebApp.model.Test;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestDao testDao;

    public List<Test> getAllTests(){
       return testDao.getAllTests();

    }
}

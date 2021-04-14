package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.GroupTestDao;
import ru.laskin.myWebApp.model.GroupTest;

import java.util.List;

@Service
public class GroupTestService {
    private GroupTestDao groupTestDao;

    public GroupTestService(GroupTestDao groupTestDao) {
        this.groupTestDao = groupTestDao;
    }

    public List<GroupTest> getAllGroupTest(){
        return groupTestDao.getAllGroupTest();
    }
}

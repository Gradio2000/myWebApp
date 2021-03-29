package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.PositionDao;

import java.util.Set;

@Service
public class PositionService {

    PositionDao positionDao;

    public PositionService(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public Set<String> getAllPosition(){
       return positionDao.getAllPosition();
    }
}

package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.PositionDao;
import ru.laskin.myWebApp.model.Position;

import java.util.List;

@Service
public class PositionService {

    PositionDao positionDao;

    public PositionService(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public List<Position> getAllPosition(){
       return positionDao.getAllPosition();
    }
}

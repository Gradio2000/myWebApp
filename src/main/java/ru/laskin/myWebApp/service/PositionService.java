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

    public Position getPositionById(Integer pos_id) {
        return positionDao.getPositionById(pos_id);
    }

    public void updateAllPosition(List<Position> positionList) {
        for (Position position : positionList){
            positionDao.updatePosition(position);
        }
    }

    public void deletePosition(int id) {
        positionDao.deletePosition(id);
    }

    public void addPosition(Position position) {
        positionDao.addPosition(position);
    }
}

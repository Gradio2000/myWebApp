package ru.laskin.myWebApp.service;

import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.dao.PositionDao;
import ru.laskin.myWebApp.model.Position;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PositionService {

    PositionDao positionDao;

    public PositionService(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public List<Position> getAllPosition(int company_id){
       return positionDao.getAllPosition(company_id);
    }

    public List<Position> getAllPositionWithoutAdminRole(int company_id){
        return positionDao.getAllPositionWithoutAdminRole(company_id);
    }

    public Position getPositionById(Integer pos_id) {
        return positionDao.getPositionById(pos_id);
    }

    public void updateAllPosition(HttpServletRequest request, HttpSession session) {
        Map<String, String[]> map = request.getParameterMap();
        String[] idPosition = map.get("idPosition");
        String[] positionName = map.get("position");
        Map<Integer, String> positionMapFromView = new HashMap<>();
        for (int i = 0; i < idPosition.length; i++) {
            positionMapFromView.put(Integer.parseInt(idPosition[i]), positionName[i]);
        }

        List<Position> positionList = (List<Position>) session.getAttribute("positions");
        for (Position position : positionList){
            position.setPosition(positionMapFromView.get(position.getIdPosition()));
        }

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

    public Map<Integer, String> getNamesAllPosition(Integer id) {
        List<Position> positionList = positionDao.getAllPositionWithoutAdminRole(id);
        Map<Integer, String> positionNamesMap = new HashMap<>();
        for (Position position : positionList){
            positionNamesMap.put(position.getIdPosition(), position.getPosition());
        }
        return positionNamesMap;
    }
}

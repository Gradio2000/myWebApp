package ru.laskin.myWebApp.controllers.userModule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.laskin.myWebApp.service.PositionService;

import java.util.Map;

@RestController
public class ControllerRest {
    private final PositionService positionService;

    public ControllerRest(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/allPositionByCompanyId/{id}")
    public @ResponseBody Map<Integer, String> getAllPositionByCompanyId(@PathVariable Integer id){
        return positionService.getNamesAllPosition(id);
    }
}

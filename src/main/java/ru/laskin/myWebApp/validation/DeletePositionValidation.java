package ru.laskin.myWebApp.validation;


import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.Position;

@Component
public class DeletePositionValidation {
    public boolean validate(Position position) {
       return position.getUserList().size() != 0;
    }
}

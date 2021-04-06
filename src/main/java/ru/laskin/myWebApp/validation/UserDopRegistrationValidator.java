package ru.laskin.myWebApp.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.laskin.myWebApp.model.User;


@Component
public class UserDopRegistrationValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "EmptyField");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "EmptyField");

        if (user.getPosition() == null || user.getPosition().equals("")){
            errors.rejectValue("position", "needToChoosePosition");
        }
    }
}

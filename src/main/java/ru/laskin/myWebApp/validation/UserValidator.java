package ru.laskin.myWebApp.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.UserService;

import java.util.HashMap;
import java.util.Map;


@Component
public class UserValidator implements Validator {
    UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "EmptyField");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "EmptyField");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "EmptyField");
        if (!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

        if (userService.checkUserRegistration(user)){
            errors.rejectValue("login", "UserExists");
        }
    }

    public Map<String, String> validate(User user) {
        Map<String, String> errorMap = new HashMap<>();
        if (userService.checkUserRegistration(user)){
            errorMap.put("loginError", "Пользователь с таким логином зарегистрирован ранее");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())){
            errorMap.put("confirmPassword", "Пароль и подтверждение не совпадают");
        }
        return errorMap;
    }
}

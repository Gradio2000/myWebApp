package ru.laskin.myWebApp.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.laskin.myWebApp.model.ResultTest;
import ru.laskin.myWebApp.service.ResultTestService;

@Component
public class TestValidator implements Validator {

    ResultTestService resultTestService;

    public TestValidator(ResultTestService resultTestService) {
        this.resultTestService = resultTestService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ResultTest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ResultTest resultTest = (ResultTest) o;

    }
}

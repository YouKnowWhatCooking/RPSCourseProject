package com.validator;

import com.model.Users;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Users.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Users users = (Users) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        if (users.getLogin().length() < 8 || users.getLogin().length() > 32) {
            errors.rejectValue("login", "Size.userForm.login");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (users.getPassword().length() < 8 || users.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}


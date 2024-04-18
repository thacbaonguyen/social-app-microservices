package com.thacbao.social.usersevice.annotation.processor;

import com.thacbao.social.usersevice.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null || password.equals("")){
            return false;
        }
        boolean isUpperCase = !password.equals(password.toLowerCase());
        boolean isDigit = false;
        for (char c : password.toCharArray()){
            if (Character.isDigit(c)) {
                isDigit = true;
            }
        }
        return isDigit&isUpperCase;
    }
}

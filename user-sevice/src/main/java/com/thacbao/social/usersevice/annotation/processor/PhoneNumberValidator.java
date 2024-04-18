package com.thacbao.social.usersevice.annotation.processor;

import com.thacbao.social.usersevice.annotation.ValidPhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        String regex = "^(\\+?84|0)?[1-9][0-9]{8}$";
        return phoneNumber.matches(regex);
    }
}

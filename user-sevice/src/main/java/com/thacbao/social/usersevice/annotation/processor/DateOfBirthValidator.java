package com.thacbao.social.usersevice.annotation.processor;

import com.thacbao.social.usersevice.annotation.ValidDateOfBirth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate eighteenYearAgo = LocalDate.now().minusYears(18);
        if (localDate == null || !localDate.isBefore(eighteenYearAgo)){
            return false;
        }
        return true;
    }
}

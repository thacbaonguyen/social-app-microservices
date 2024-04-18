package com.thacbao.social.usersevice.annotation;

import com.thacbao.social.usersevice.annotation.processor.DateOfBirthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
public @interface ValidDateOfBirth {
    public String message() default "Ngày sinh không hợp lệ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

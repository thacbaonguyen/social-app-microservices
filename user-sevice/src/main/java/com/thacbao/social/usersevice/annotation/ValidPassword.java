package com.thacbao.social.usersevice.annotation;

import com.thacbao.social.usersevice.annotation.processor.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.FIELD})
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
    public String message() default "Mật khẩu không hợp lệ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

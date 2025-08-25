package com.exercise.validation.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UserRequestValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserRequestConstraint {
    String message() default "Invalid user request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

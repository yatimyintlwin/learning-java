package com.exercise.validation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeLimitValidator.class)
public @interface AgeLimit {
    String message() default "User does not meet the age requirement";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

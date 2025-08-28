package com.exercise.validation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailOrPhoneValidator.class)
public @interface EmailOrPhoneRequired {
    String message() default "Either email or phone number must be provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

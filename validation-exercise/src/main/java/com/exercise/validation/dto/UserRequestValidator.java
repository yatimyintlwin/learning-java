package com.exercise.validation.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserRequestValidator implements ConstraintValidator<UserRequestConstraint, UserRequest> {

    @Override
    public boolean isValid(UserRequest user, ConstraintValidatorContext context) {
        boolean valid = true;

        context.disableDefaultConstraintViolation();

        if ((user.getPhone() == null || user.getPhone().isEmpty())
                && (user.getEmail() == null || user.getEmail().isEmpty())) {
            context.buildConstraintViolationWithTemplate("Either phone or email must be provided")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            valid = false;
        }

        if ("Male".equalsIgnoreCase(user.getGender()) && (user.getAge() != null && user.getAge() < 16)) {
            context.buildConstraintViolationWithTemplate("Male user must be at least 16 years old")
                    .addPropertyNode("age")
                    .addConstraintViolation();
            valid = false;
        } else if ("Female".equalsIgnoreCase(user.getGender()) && (user.getAge() != null && user.getAge() < 17)) {
            context.buildConstraintViolationWithTemplate("Female user must be at least 17 years old")
                    .addPropertyNode("age")
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}

package com.exercise.validation.validator;

import com.exercise.validation.dto.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailOrPhoneValidator implements ConstraintValidator<EmailOrPhoneRequired, User> {

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if (user == null) return true;

        String email = user.getEmail();
        String phoneNo = user.getPhoneNo();

        boolean valid = (email != null && !email.isBlank()) || (phoneNo != null && !phoneNo.isBlank());

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Either email or phone number must be provided")
                    .addPropertyNode("email")
                    .addConstraintViolation();
        }

        return valid;
    }
}

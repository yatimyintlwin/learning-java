package com.exercise.validation.validator;

import com.exercise.validation.dto.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AgeLimitValidator implements ConstraintValidator<AgeLimit, User> {

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if (user == null) return true;

        String gender = user.getGender();
        LocalDate birthDate = user.getBirthDate();

        if (gender == null || birthDate == null) return true;

        int minAge;
        if ("female".equalsIgnoreCase(gender)) {
            minAge = 17;
        } else if ("male".equalsIgnoreCase(gender)) {
            minAge = 16;
        } else {
            minAge = 16;
        }

        LocalDate today = LocalDate.now();
        LocalDate minimumBirthDate = today.minusYears(minAge);

        boolean valid = !birthDate.isAfter(minimumBirthDate);

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "User must be at least " + minAge + " years old based on gender"
            ).addPropertyNode("birthDate").addConstraintViolation();
        }

        return valid;
    }
}

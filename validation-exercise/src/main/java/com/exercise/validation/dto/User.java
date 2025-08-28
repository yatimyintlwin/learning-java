package com.exercise.validation.dto;

import com.exercise.validation.validator.AgeLimit;
import com.exercise.validation.validator.EmailOrPhoneRequired;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@AgeLimit
@EmailOrPhoneRequired
public class User {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Gender cannot be blank")
    private String gender;

    @NotNull(message = "Birth date cannot be null")
    private LocalDate birthDate;

    private String email;

    private String phoneNo;
}

package com.exercise.validation.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@UserRequestConstraint
public class UserRequest {

    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    private String phone;

    private String email;

    @NotBlank(message = "Gender cannot be blank")
    private String gender;

    @NotNull(message = "Age cannot be null")
    @Max(value = 60, message = "Age must be at most 60")
    private Integer age;
}

package com.practice.auth.basic.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "Username is required")
    @Schema(description = "Username", example = "alex")
    private String username;

    @NotBlank(message = "Password is required")
    @Schema(description = "User password", example = "password123")
    private String password;

    @Schema(description = "Role", example = "admin")
    private String role;
}

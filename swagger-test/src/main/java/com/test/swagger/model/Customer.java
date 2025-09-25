package com.test.swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Customer {
    @Schema(description = "Id", example = "1")
    private Long id;

    @Schema(description = "Username", example = "alex")
    private String name;

    @Schema(description = "Email", example = "alex@example.com")
    private String email;
}
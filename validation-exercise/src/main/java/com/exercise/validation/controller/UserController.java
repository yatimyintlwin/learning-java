package com.exercise.validation.controller;

import com.exercise.validation.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public String createUser(@Valid @RequestBody UserRequest userRequest) {
        return "User created successfully: " + userRequest.getName();
    }
}

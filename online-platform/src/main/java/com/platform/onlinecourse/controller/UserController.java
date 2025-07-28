package com.platform.onlinecourse.controller;

import com.platform.onlinecourse.model.AppUser;
import com.platform.onlinecourse.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        AppUser deletedAppUser = userService.deleteUser(username);
        if (deletedAppUser == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("AppUser deleted successfully");
    }
}
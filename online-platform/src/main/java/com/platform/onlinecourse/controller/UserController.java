package com.platform.onlinecourse.controller;

import com.platform.onlinecourse.model.User;
import com.platform.onlinecourse.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User savedUser = userService.register(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser) {
        User user = userService.getUserByUsername(loginUser.getUsername());

        if (user == null || !user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        return ResponseEntity.ok("Login successful for user: " + user.getUsername());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        User deletedUser = userService.deleteUser(username);
        if (deletedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("User deleted successfully");
    }
}

package com.practice.auth.basic.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/public")
    public String publicApi() {
        return "This is a public API.";
    }

    @PreAuthorize("hasRole('GUEST')")
    @GetMapping("/api/guest/info")
    public String guestApi() {
        return "Guest access granted.";
    }

    @PreAuthorize("hasRole('GUEST')")
    @GetMapping("/api/guest/detail")
    public String guestDetail() {
        return "Guest access granted.";
    }

    @GetMapping("/api/guest/access")
    public String guestAccess() {
        return "Guest access granted.";
    }

    @Secured("ROLE_USER")
    @GetMapping("/api/user/info")
    public String userApi() {
        return "User access granted.";
    }

    @PreAuthorize("hasRole('MANAGER') and authentication.name == 'manager'")
    @GetMapping("/api/manager/info")
    public String managerApi() {
        return "Manager access granted.";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/info")
    public String adminApi() {
        return "Admin access granted.";
    }
}

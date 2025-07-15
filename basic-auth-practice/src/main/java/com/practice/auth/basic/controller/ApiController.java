package com.practice.auth.basic.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/public")
    public String publicApi() {
        return "This is a public API.";
    }

    @GetMapping("/api/guest/info")
    public String guestApi() {
        return "Guest access granted.";
    }

    @GetMapping("/api/guest/detail")
    public String guestDetail() {
        return "Guest access granted.";
    }

    @GetMapping("/api/user/info")
    @Secured("ROLE_USER")
    public String userApi() {
        return "User access granted.";
    }

    @GetMapping("/api/manager/info")
    @PreAuthorize("hasRole('MANAGER')")
    public String managerApi() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return "Manager access granted.";
    }

    @GetMapping("/api/admin/info")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminApi() {
        return "Admin access granted.";
    }
}

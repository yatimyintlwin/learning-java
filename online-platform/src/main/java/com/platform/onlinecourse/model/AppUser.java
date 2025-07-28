package com.platform.onlinecourse.model;

import lombok.Data;

@Data
public class AppUser {
    private String id;
    private String username;
    private String password;
    private String role;
    private boolean enabled;
}
package com.platform.onlinecourse.model;

import lombok.Data;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String role;
    private boolean enabled;
}

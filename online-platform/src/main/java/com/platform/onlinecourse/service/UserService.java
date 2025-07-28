package com.platform.onlinecourse.service;

import com.platform.onlinecourse.dto.LoginRequest;
import com.platform.onlinecourse.dto.RegisterRequest;
import com.platform.onlinecourse.model.AppUser;

public interface UserService {
    AppUser register(RegisterRequest request);
    String login(LoginRequest request);
    AppUser deleteUser(String username);
}

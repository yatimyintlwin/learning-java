package com.platform.onlinecourse.service;

import com.platform.onlinecourse.dto.LoginRequest;
import com.platform.onlinecourse.dto.RegisterRequest;
import com.platform.onlinecourse.model.User;

public interface UserService {
    User register(RegisterRequest request);
    String login(LoginRequest request);
    User deleteUser(String username);
}

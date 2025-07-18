package com.platform.onlinecourse.service;

import com.platform.onlinecourse.model.User;

public interface UserService {
    User register(User user);
    User getUserByUsername(String username);
    User deleteUser(String username);
}


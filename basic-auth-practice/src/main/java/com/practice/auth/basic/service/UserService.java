package com.practice.auth.basic.service;

import com.practice.auth.basic.model.AuthRequest;

public interface UserService {
    void registerUser(AuthRequest request);
}

package com.platform.onlinecourse.repository;

import com.platform.onlinecourse.model.User;

public interface UserRepository {
    User save(User user);
    User findByUsername(String username);
    User deleteById(String username);
}

package com.platform.onlinecourse.repository;

import com.platform.onlinecourse.model.AppUser;

public interface UserRepository {
    AppUser save(AppUser appUser);
    AppUser findByUsername(String username);
    AppUser deleteByUsername(String username);
}

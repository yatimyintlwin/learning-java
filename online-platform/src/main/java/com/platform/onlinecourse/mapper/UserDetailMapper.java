package com.platform.onlinecourse.mapper;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailMapper {
    public static UserDetails mapToUserDetail(String username, String password, String role) {
        return User.builder()
                .username(username)
                .password(password)
                .roles(role)
                .build();
    }
}

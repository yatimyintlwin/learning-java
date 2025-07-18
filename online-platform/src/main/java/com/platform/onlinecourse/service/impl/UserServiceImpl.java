package com.platform.onlinecourse.service.impl;

import com.platform.onlinecourse.model.User;
import com.platform.onlinecourse.repository.UserRepository;
import com.platform.onlinecourse.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User deleteUser(String username) {
        return userRepository.deleteById(username);
    }
}

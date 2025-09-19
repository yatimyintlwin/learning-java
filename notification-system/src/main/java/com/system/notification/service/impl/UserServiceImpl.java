package com.system.notification.service.impl;

import com.system.notification.model.User;
import com.system.notification.exception.UserNotFoundException;
import com.system.notification.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public List<User> getUsersByDomain(String domain) {
        return users.stream()
                .filter(user -> user.getEmail().endsWith("@" + domain))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> filterUsersByNameLength(int length) {
        return users.stream()
                .filter(user -> user.getName().length() == length)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }
}

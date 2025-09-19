package com.system.notification.service;

import com.system.notification.exception.UserNotFoundException;
import com.system.notification.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> getUsersByDomain(String domain);
    List<User> filterUsersByNameLength(int length);
    User getUserById(int id) throws UserNotFoundException;
}

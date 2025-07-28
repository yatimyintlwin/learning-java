package com.platform.onlinecourse.service.impl;

import com.platform.onlinecourse.dto.LoginRequest;
import com.platform.onlinecourse.dto.RegisterRequest;
import com.platform.onlinecourse.exception.InvalidCredentialsException;
import com.platform.onlinecourse.exception.UserNotFoundException;
import com.platform.onlinecourse.model.AppUser;
import com.platform.onlinecourse.repository.UserRepository;
import com.platform.onlinecourse.service.UserService;
import com.platform.onlinecourse.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           JwtUtil jwtUtil,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser register(RegisterRequest request) {
        log.info("Attempting to register user: {}", request.getUsername());

        if (userRepository.findByUsername(request.getUsername()) != null) {
            log.warn("Registration failed: Username already exists - {}", request.getUsername());
            throw new InvalidCredentialsException("Username already exists");
        }

        AppUser appUser = new AppUser();
        appUser.setId(UUID.randomUUID().toString());
        appUser.setUsername(request.getUsername());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        appUser.setRole(request.getRole());
        appUser.setEnabled(true);

        AppUser savedUser = userRepository.save(appUser);
        log.info("User registered successfully: {}", savedUser.getUsername());
        return savedUser;
    }

    @Override
    public String login(LoginRequest request) {
        log.info("Login attempt for user: {}", request.getUsername());

        AppUser appUser = userRepository.findByUsername(request.getUsername());

        if (appUser == null) {
            log.warn("Login failed: User not found - {}", request.getUsername());
            throw new UserNotFoundException("AppUser not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), appUser.getPassword())) {
            log.warn("Login failed: Invalid credentials for user - {}", request.getUsername());
            throw new InvalidCredentialsException("Invalid password or username");
        }

        String token = jwtUtil.generateToken(appUser);
        log.info("Login successful for user: {}", request.getUsername());
        return token;
    }

    @Override
    public AppUser deleteUser(String username) {
        log.info("Attempting to delete user: {}", username);

        AppUser deleted = userRepository.deleteById(username);
        if (deleted == null) {
            log.error("User deletion failed: User not found - {}", username);
            throw new UserNotFoundException("AppUser not found for deletion");
        }

        log.info("User deleted successfully: {}", deleted.getUsername());
        return deleted;
    }
}

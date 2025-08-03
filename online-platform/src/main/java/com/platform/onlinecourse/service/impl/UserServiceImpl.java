package com.platform.onlinecourse.service.impl;

import com.platform.onlinecourse.dto.LoginRequest;
import com.platform.onlinecourse.dto.RegisterRequest;
import com.platform.onlinecourse.exception.InvalidCredentialsException;
import com.platform.onlinecourse.exception.UserAlreadyExistException;
import com.platform.onlinecourse.exception.UserNotFoundException;
import com.platform.onlinecourse.model.AppUser;
import com.platform.onlinecourse.repository.UserRepository;
import com.platform.onlinecourse.service.UserService;
import com.platform.onlinecourse.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository,
                           JwtUtil jwtUtil,
                           BCryptPasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AppUser register(RegisterRequest request) {
        log.info("Attempting to register user: {}", request.getUsername());

        if (userRepository.findByUsername(request.getUsername()) != null) {
            log.warn("Registration failed: Username already exists - {}", request.getUsername());
            throw new UserAlreadyExistException("Username already exists");
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

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails user = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(user);
            log.info("Login successful for user: {}", user.getUsername());
            return token;

        } catch (AuthenticationException e) {
            log.warn("Login failed: Invalid credentials for user - {}", request.getUsername());
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }

    @Override
    public AppUser deleteUser(String username) {
        log.info("Attempting to delete user: {}", username);

        AppUser deleted = userRepository.deleteByUsername(username);
        if (deleted == null) {
            log.error("User deletion failed: User not found - {}", username);
            throw new UserNotFoundException("AppUser not found for deletion");
        }

        log.info("User deleted successfully: {}", deleted.getUsername());
        return deleted;
    }
}

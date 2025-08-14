package com.platform.onlinecourse.service.impl;

import com.platform.onlinecourse.dto.LoginRequest;
import com.platform.onlinecourse.dto.RegisterRequest;
import com.platform.onlinecourse.exception.InvalidCredentialsException;
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
//        if (userRepository.findByUsername(request.getUsername()) != null) {
//            log.warn("Failed to register user: username '{}' already exists", request.getUsername());
//            throw new UserAlreadyExistException("Username already exists");
//        }

        AppUser appUser = new AppUser();
        appUser.setId(UUID.randomUUID().toString());
        appUser.setUsername(request.getUsername());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        appUser.setRole(request.getRole());
        appUser.setEnabled(true);

            return userRepository.save(appUser);
    }

    @Override
    public String login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            UserDetails user = (UserDetails) authentication.getPrincipal();
            return jwtUtil.generateToken(user);

        } catch (AuthenticationException e) {
            log.warn("Failed login attempt for username '{}'", request.getUsername());
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }

    @Override
    public AppUser deleteUser(String username) {
        return userRepository.deleteByUsername(username);
    }
}

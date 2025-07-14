package com.practice.auth.basic.service;

import com.practice.auth.basic.model.AppUserByH2;
import com.practice.auth.basic.model.AuthRequest;
import com.practice.auth.basic.repository.H2UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Profile("h2")
public class UserServiceByH2 implements UserService {
    private final H2UserRepository h2UserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceByH2(H2UserRepository h2UserRepository, PasswordEncoder passwordEncoder) {
        this.h2UserRepository = h2UserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(AuthRequest request) {
        if (h2UserRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        AppUserByH2 user = new AppUserByH2(null, request.getUsername(),
                passwordEncoder.encode(request.getPassword()), request.getRole());
        h2UserRepository.save(user);
    }
}

package com.practice.auth.basic.service;

import com.practice.auth.basic.model.AppUserByH2;
import com.practice.auth.basic.model.AuthRequest;
import com.practice.auth.basic.repository.H2UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Profile("h2")
@RequiredArgsConstructor
public class RegisterServiceByH2 implements RegisterService {
    private final H2UserRepository h2UserRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(AuthRequest request) {
        if (h2UserRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        AppUserByH2 user = new AppUserByH2(null, request.getUsername(),
                passwordEncoder.encode(request.getPassword()), request.getRole());
        h2UserRepository.save(user);
    }
}

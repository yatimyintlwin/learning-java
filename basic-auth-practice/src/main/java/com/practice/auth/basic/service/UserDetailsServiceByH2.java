package com.practice.auth.basic.service;

import com.practice.auth.basic.model.AppUserByH2;
import com.practice.auth.basic.repository.H2UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("H2")
@Profile("h2")
public class UserDetailsServiceByH2 implements UserDetailsService {
    private final H2UserRepository userRepository;

    public UserDetailsServiceByH2(H2UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserByH2 user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
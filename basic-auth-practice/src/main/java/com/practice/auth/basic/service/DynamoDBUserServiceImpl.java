package com.practice.auth.basic.service;

import com.practice.auth.basic.model.AppUserByDynamoDB;
import com.practice.auth.basic.repository.DynamoDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("DynamoDB")
@RequiredArgsConstructor
public class DynamoDBUserServiceImpl implements UserDetailsService {
    private final DynamoDBRepository dynamoDBRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserByDynamoDB user = dynamoDBRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}

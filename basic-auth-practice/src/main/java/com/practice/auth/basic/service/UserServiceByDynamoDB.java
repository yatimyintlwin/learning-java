package com.practice.auth.basic.service;

import com.practice.auth.basic.model.AppUserByDynamoDB;
import com.practice.auth.basic.model.AuthRequest;
import com.practice.auth.basic.repository.DynamoDBRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Profile("dynamodb")
public class UserServiceByDynamoDB implements UserService {
    private final DynamoDBRepository dynamoDBRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceByDynamoDB(DynamoDBRepository dynamoDBRepository, PasswordEncoder passwordEncoder) {
        this.dynamoDBRepository = dynamoDBRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(AuthRequest request) {
        if (dynamoDBRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        AppUserByDynamoDB user = new AppUserByDynamoDB(request.getUsername(),
                passwordEncoder.encode(request.getPassword()), request.getRole());
        dynamoDBRepository.save(user);
    }
}

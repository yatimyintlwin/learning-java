package com.practice.auth.basic.repository;

import com.practice.auth.basic.model.AppUserByDynamoDB;

import java.util.Optional;

public interface DynamoDBRepository {
    Optional<AppUserByDynamoDB> findByUsername(String username);

    void save(AppUserByDynamoDB user);
}

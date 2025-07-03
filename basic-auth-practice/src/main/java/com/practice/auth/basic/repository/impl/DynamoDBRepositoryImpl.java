package com.practice.auth.basic.repository.impl;

import com.practice.auth.basic.model.AppUserByDynamoDB;
import com.practice.auth.basic.repository.DynamoDBRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import java.util.Optional;

@Repository
@Profile("dynamodb")
public class DynamoDBRepositoryImpl implements DynamoDBRepository {
    private final DynamoDbEnhancedClient enhancedClient;
    private final TableSchema<AppUserByDynamoDB> tableSchema;
    private final DynamoDbTable<AppUserByDynamoDB> table;

    public DynamoDBRepositoryImpl(DynamoDbEnhancedClient enhancedClient) {
        this.enhancedClient = enhancedClient;
        this.tableSchema = TableSchema.fromBean(AppUserByDynamoDB.class);
        this.table = enhancedClient.table("AppUser", tableSchema);
    }

    @Override
    public Optional<AppUserByDynamoDB> findByUsername(String username) {
        return Optional.ofNullable(table.getItem(Key.builder().partitionValue(username).build()));
    }

    @Override
    public void save(AppUserByDynamoDB user) {
        table.putItem(user);
    }
}
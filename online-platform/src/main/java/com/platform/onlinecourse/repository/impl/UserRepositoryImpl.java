package com.platform.onlinecourse.repository.impl;

import com.platform.onlinecourse.exception.UserAlreadyExistException;
import com.platform.onlinecourse.exception.UserNotFoundException;
import com.platform.onlinecourse.model.AppUser;
import com.platform.onlinecourse.repository.UserRepository;
import com.platform.onlinecourse.utils.NormalizationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;

import static com.platform.onlinecourse.mapper.UserMapper.mapToUser;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "OnlinePlatForm";

    public UserRepositoryImpl(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public AppUser save(AppUser appUser) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("pk", AttributeValue.fromS("USERS"));
        item.put("sk", AttributeValue.fromS("USERNAME#" + NormalizationUtil.normalize(appUser.getUsername())));
        item.put("id", AttributeValue.fromS(appUser.getId()));
        item.put("username", AttributeValue.fromS(appUser.getUsername()));
        item.put("password", AttributeValue.fromS(appUser.getPassword()));
        item.put("role", AttributeValue.fromS(appUser.getRole().toUpperCase()));
        item.put("enabled", AttributeValue.fromBool(appUser.isEnabled()));

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .conditionExpression("attribute_not_exists(username)")
                .returnValues(ReturnValue.ALL_OLD)
                .build();

        try {
            dynamoDbClient.putItem(request);
            return appUser;
        } catch (ConditionalCheckFailedException e) {
            log.warn("Failed to save user '{}': username already exists", appUser.getUsername());
            throw new UserAlreadyExistException("Username already exists");
        }
    }

    @Override
    public AppUser findByUsername(String username) {
        String normalizedUsername = NormalizationUtil.normalize(username);

        Map<String, AttributeValue> key = new HashMap<>();
        key.put("pk", AttributeValue.fromS("USERS"));
        key.put("sk", AttributeValue.fromS("USERNAME#" + normalizedUsername));

        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        Map<String, AttributeValue> item = dynamoDbClient.getItem(request).item();
        if (item == null || item.isEmpty()) {
            return null;
        }
        return mapToUser(item);
    }

    @Override
    public AppUser deleteByUsername(String username) {
        String normalizedUsername = NormalizationUtil.normalize(username);

        Map<String, AttributeValue> key = new HashMap<>();
        key.put("pk", AttributeValue.fromS("USERS"));
        key.put("sk", AttributeValue.fromS("USERNAME#" + normalizedUsername));

        DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .conditionExpression("attribute_exists(pk) AND attribute_exists(sk)")
                .returnValues(ReturnValue.ALL_OLD)
                .build();

        try {
            DeleteItemResponse response = dynamoDbClient.deleteItem(deleteRequest);
            Map<String, AttributeValue> deletedItem = response.attributes();
            log.info("Deleted user: {}", deletedItem);
            return mapToUser(deletedItem);
        } catch (ConditionalCheckFailedException e) {
            log.warn("Failed to delete user: '{}'", username);
            throw new UserNotFoundException("User with name '" + username + "' not found");
        }
    }
}

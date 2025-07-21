package com.platform.onlinecourse.repository.impl;

import com.platform.onlinecourse.model.User;
import com.platform.onlinecourse.repository.UserRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "OnlinePlatForm";

    public UserRepositoryImpl(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public User save(User user) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("pk", AttributeValue.fromS("USERS"));
        item.put("sk", AttributeValue.fromS("USERNAME#" + user.getUsername()));
        item.put("id", AttributeValue.fromS(user.getId()));
        item.put("username", AttributeValue.fromS(user.getUsername()));
        item.put("password", AttributeValue.fromS(user.getPassword()));
        item.put("role", AttributeValue.fromS(user.getRole().toUpperCase()));
        item.put("enabled", AttributeValue.fromBool(user.isEnabled()));

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        QueryRequest request = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("pk = :pk AND sk = :sk")
                .expressionAttributeValues(Map.of(
                        ":pk", AttributeValue.fromS("USERS"),
                        ":sk", AttributeValue.fromS("USERNAME#" + username)
                ))
                .build();

        QueryResponse response = dynamoDbClient.query(request);

        if (response.count() == 0) {
            return null;
        }

        return mapToUser(response.items().get(0));
    }

    @Override
    public User deleteById(String username) {
        User user = findByUsername(username);
        if (user == null) {
            return null;
        }

        Map<String, AttributeValue> key = new HashMap<>();
        key.put("pk", AttributeValue.fromS("USERS"));
        key.put("sk", AttributeValue.fromS("USERNAME#" + username));

        DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        dynamoDbClient.deleteItem(deleteRequest);
        return user;
    }

    private User mapToUser(Map<String, AttributeValue> item) {
        User user = new User();
        user.setId(item.get("id").s().replace("USER#", ""));
        user.setUsername(item.get("username").s());
        user.setPassword(item.get("password").s());
        user.setRole(item.get("role").s());
        user.setEnabled(item.get("enabled").bool());
        return user;
    }
}

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
    private final String tableName = "OnlinePlatform";

    public UserRepositoryImpl(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public User save(User user) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("pk", AttributeValue.fromS(user.getUsername()));
        item.put("sk", AttributeValue.fromS("ROLE#" + user.getRole()));
        item.put("id", AttributeValue.fromS(user.getId()));
        item.put("password", AttributeValue.fromS(user.getPassword()));
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
                .keyConditionExpression("pk = :username")
                .expressionAttributeValues(Map.of(":username", AttributeValue.fromS(username)))
                .build();

        QueryResponse response = dynamoDbClient.query(request);

        if (!response.hasItems() || response.items().isEmpty()) {
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
        key.put("pk", AttributeValue.fromS(username));
        key.put("sk", AttributeValue.fromS("ROLE#" + user.getRole()));

        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        dynamoDbClient.deleteItem(request);
        return user;
    }

    private User mapToUser(Map<String, AttributeValue> item) {
        User user = new User();
        user.setId(item.get("id").s());
        user.setUsername(item.get("pk").s());
        user.setRole(item.get("sk").s().replace("ROLE#", ""));
        user.setPassword(item.get("password").s());
        user.setEnabled(item.get("enabled").bool());
        return user;
    }
}

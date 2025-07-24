package com.platform.onlinecourse.repository.impl;

import com.platform.onlinecourse.model.User;
import com.platform.onlinecourse.repository.UserRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;

import static com.platform.onlinecourse.mapper.UserMapper.mapToUser;

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
        item.put("sk", AttributeValue.fromS("USERNAME#" + user.getUsername().toLowerCase().replaceAll("\\s+", "")));
        item.put("id", AttributeValue.fromS(user.getId()));
        item.put("username", AttributeValue.fromS(user.getUsername()));
        item.put("password", AttributeValue.fromS(user.getPassword()));
        item.put("role", AttributeValue.fromS(user.getRole().toUpperCase()));
        item.put("enabled", AttributeValue.fromBool(user.isEnabled()));

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .returnValues(ReturnValue.ALL_OLD)
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
                .returnValues(ReturnValue.ALL_OLD)
                .build();

        dynamoDbClient.deleteItem(deleteRequest);
        return user;
    }
}

package com.practice.auth.basic.model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserByDynamoDB {
    private String username;
    private String password;
    private String role;

    @DynamoDbPartitionKey
    public String getUsername() {
        return username;
    }
}
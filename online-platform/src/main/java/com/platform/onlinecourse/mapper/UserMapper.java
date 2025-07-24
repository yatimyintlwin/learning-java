package com.platform.onlinecourse.mapper;

import com.platform.onlinecourse.model.User;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class UserMapper {
    public static User mapToUser(Map<String, AttributeValue> item) {
        User user = new User();
        user.setId(item.get("id").s().replace("USER#", ""));
        user.setUsername(item.get("username").s());
        user.setPassword(item.get("password").s());
        user.setRole(item.get("role").s());
        user.setEnabled(item.get("enabled").bool());
        return user;
    }
}

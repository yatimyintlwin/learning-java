package com.platform.onlinecourse.mapper;

import com.platform.onlinecourse.model.AppUser;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class UserMapper {
    public static AppUser mapToUser(Map<String, AttributeValue> item) {
        AppUser appUser = new AppUser();
        appUser.setId(item.get("id").s().replace("USER#", ""));
        appUser.setUsername(item.get("username").s());
        appUser.setPassword(item.get("password").s());
        appUser.setRole(item.get("role").s());
        appUser.setEnabled(item.get("enabled").bool());
        return appUser;
    }
}

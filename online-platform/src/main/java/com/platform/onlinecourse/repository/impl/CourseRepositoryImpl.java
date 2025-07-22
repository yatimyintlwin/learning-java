package com.platform.onlinecourse.repository.impl;

import com.platform.onlinecourse.model.Course;
import com.platform.onlinecourse.repository.CourseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "OnlinePlatForm";

    public CourseRepositoryImpl(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public Course save(Course course) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("pk", AttributeValue.fromS("COURSE"));
        item.put("sk", AttributeValue.fromS("COURSE#" + course.getTitle()));
        item.put("id", AttributeValue.fromS(course.getId()));
        item.put("title", AttributeValue.fromS(course.getTitle()));
        item.put("description", AttributeValue.fromS(course.getDescription()));
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
        return course;
    }

    @Override
    public List<Course> findAll() {
        QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("pk = :pk")
                .expressionAttributeValues(Map.of(":pk", AttributeValue.fromS("COURSE")))
                .build();

        List<Map<String, AttributeValue>> items = dynamoDbClient.query(queryRequest).items();

        List<Course> courses = new ArrayList<>();
        for (Map<String, AttributeValue> item : items) {
            courses.add(Course.builder()
                    .id(item.get("id").s())
                    .title(item.get("title").s())
                    .description(item.get("description").s())
                    .build());
        }
        return courses;
    }
}

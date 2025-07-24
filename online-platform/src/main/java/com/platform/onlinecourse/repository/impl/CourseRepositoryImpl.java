package com.platform.onlinecourse.repository.impl;

import com.platform.onlinecourse.mapper.CourseMapper;
import com.platform.onlinecourse.model.Course;
import com.platform.onlinecourse.repository.CourseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

import static com.platform.onlinecourse.mapper.CourseMapper.mapToCourse;

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
        item.put("sk", AttributeValue.fromS("COURSE#" + course.getTitle().toLowerCase().replaceAll("\\s+", "")));
        item.put("id", AttributeValue.fromS(course.getId()));
        item.put("title", AttributeValue.fromS(course.getTitle()));
        item.put("description", AttributeValue.fromS(course.getDescription()));
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .returnValues(ReturnValue.ALL_OLD)
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

        return items.stream()
                .map(CourseMapper::mapToCourse)
                .toList();
    }

    @Override
    public Course findByCourseTitle(String title) {
        QueryRequest request = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("pk = :pk AND sk = :sk")
                .expressionAttributeValues(Map.of(
                        ":pk", AttributeValue.fromS("COURSE"),
                        ":sk", AttributeValue.fromS("COURSE#" + title)
                ))
                .build();

        QueryResponse response = dynamoDbClient.query(request);

        if (response.count() == 0) {
            return null;
        }

        return mapToCourse(response.items().get(0));
    }
}

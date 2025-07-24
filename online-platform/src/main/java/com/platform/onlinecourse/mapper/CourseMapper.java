package com.platform.onlinecourse.mapper;

import com.platform.onlinecourse.model.Course;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class CourseMapper {
    public static Course mapToCourse(Map<String, AttributeValue> item) {
        Course course = new Course();
        course.setId(item.get("id").s());
        course.setTitle(item.get("title").s());
        course.setDescription(item.get("description").s());
        return course;
    }
}

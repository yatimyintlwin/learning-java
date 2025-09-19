package com.exercise.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachingExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CachingExerciseApplication.class, args);
    }
}

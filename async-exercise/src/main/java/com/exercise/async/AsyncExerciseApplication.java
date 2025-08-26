package com.exercise.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsyncExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncExerciseApplication.class, args);
    }

}

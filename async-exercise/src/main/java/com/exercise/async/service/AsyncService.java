package com.exercise.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async
    public void longRunningTask(String taskName) {
        try {
            System.out.println("Started: " + taskName + " on " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("Completed: " + taskName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
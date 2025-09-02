package com.exercise.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService3 {
    @Async
    public void task1_3() {
        try {
            System.out.println("  Started: Task-1.3 on " + Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println("  Completed: Task-1.3");

        } catch (Exception e) {
            System.out.println("  Error in Task-1.3 -> " + e.getMessage());
        }
    }
}

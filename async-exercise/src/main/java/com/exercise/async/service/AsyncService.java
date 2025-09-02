package com.exercise.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    private final AsyncService2 asyncService2;

    public AsyncService(AsyncService2 asyncService2) {
        this.asyncService2 = asyncService2;
    };

    @Async
    public void longRunningTask(String taskName) {
        try {
            System.out.println("Started: " + taskName + " on " + Thread.currentThread().getName());
//            Thread.sleep(3000);

//            if ("Task-1".equals(taskName)) {
//                task1_1();
//            }

//            System.out.println("Completed: " + taskName);
        } catch (Exception e) {
            System.out.println("Error in " + taskName + " -> " + e.getMessage());
        }
    }

    @Async
    public void task1_1() {
        try {
            System.out.println("  Started: Task-1.1 on " + Thread.currentThread().getName());
            Thread.sleep(2000);

//            if (true) {
//                throw new RuntimeException("Simulated exception in Task-1.1");
//            }

            System.out.println("  Completed: Task-1.1");

            asyncService2.task1_2();

        } catch (Exception e) {
            System.out.println("  Error in Task-1.1 -> " + e.getMessage());
        }
    }
}

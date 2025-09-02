package com.exercise.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService2 {

    private final AsyncService3 asyncService3;

    public AsyncService2(AsyncService3 asyncService3) {
        this.asyncService3 = asyncService3;
    }

    @Async
    public void task1_2() {
        try {
            System.out.println("    Started: Task-1.2 on " + Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println("    Completed: Task-1.2");
            asyncService3.task1_3();

        } catch (Exception e) {
            System.out.println("    Error in Task-1.2 -> " + e.getMessage());
        }
    }
}

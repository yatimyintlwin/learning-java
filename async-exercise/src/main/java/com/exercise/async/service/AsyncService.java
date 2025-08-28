package com.exercise.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async
    public void taskChain1() {
        run("Task-1");
        run("Task-1.1");
        run("Task-1.2");
    }

    @Async("taskExecutor1")
    public void taskChain2() {
        run("Task-2");
        run("Task-2.1");
    }

    @Async
    public void taskChain3() {
        run("Task-3");
        run("Task-3.1");
    }

    private void run(String taskName) {
        try {
            System.out.println("Started: " + taskName + " on " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("Completed: " + taskName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

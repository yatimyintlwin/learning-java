package com.exercise.async.controller;

import com.exercise.async.service.AsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/run-async")
    public String runAsyncTasks() {
        asyncService.longRunningTask("Task-1");
        asyncService.longRunningTask("Task-2");
        asyncService.longRunningTask("Task-3");
//        asyncService.longRunningTask("Task-4");
//        asyncService.longRunningTask("Task-5");
//        asyncService.longRunningTask("Task-6");
//        asyncService.longRunningTask("Task-7");
//        asyncService.longRunningTask("Task-8");
//        asyncService.longRunningTask("Task-9");
//        asyncService.longRunningTask("Task-10");
        return "Tasks started! (Check console logs)";
    }

    @GetMapping("/run-task1-1")
    public String runTask1_1() {
        asyncService.task1_1();
        return "Task-1.1 started. Check console logs!";
    }
}
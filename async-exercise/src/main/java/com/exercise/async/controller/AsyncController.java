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
        return "Tasks started! (Check console logs)";
    }
}

package com.exercise.async.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;

@Configuration
public class AsyncManagerConfig implements AsyncConfigurer {

    private final Executor taskExecutor2;

    public AsyncManagerConfig(Executor taskExecutor2) {
        this.taskExecutor2 = taskExecutor2;
    }

    @Override
    public Executor getAsyncExecutor() {
        return taskExecutor2;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) ->
                System.out.println("Exception in async method: " + method.getName() + " -> " + throwable);
    }
}

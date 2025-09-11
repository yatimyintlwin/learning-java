package com.example.scheduledretry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyScheduledService {

    private int fixedRateCounter = 0;
    private int fixedDelayCounter = 0;
    private int cronCounter = 0;

    @Scheduled(fixedRate = 5000)
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public void fixedRateTask() {
        fixedRateCounter++;
        log.info("[FixedRateTask] Attempt #{} on {}", fixedRateCounter, Thread.currentThread().getName());
        throw new RuntimeException("FixedRateTask failed!");
    }

    @Scheduled(fixedDelay = 5000)
    @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 1000))
    public void fixedDelayTask() {
        fixedDelayCounter++;
        log.info("[FixedDelayTask] Attempt #{} on {}", fixedDelayCounter, Thread.currentThread().getName());
        throw new RuntimeException("FixedDelayTask failed!");
    }

    @Scheduled(cron = "0 * * * * *")
    @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 1500))
    public void cronTask() {
        cronCounter++;
        log.info("[CronTask] Attempt #{} on {}", cronCounter, Thread.currentThread().getName());
        throw new ArithmeticException("CronTask failed!");
    }

    @Recover
    public void recover(RuntimeException e) {
        log.error("[Recover] Task failed after retries. Error: {}", e.getMessage());
    }

    @Recover
    public void recover(ArithmeticException e) {
        log.error("[Recover] Arithmetic Exception: {}", e.getMessage());
    }
}

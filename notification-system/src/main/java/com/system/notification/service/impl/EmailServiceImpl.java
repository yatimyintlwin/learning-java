package com.system.notification.service.impl;

import com.system.notification.service.EmailService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service()
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending Email " + message);
    }
}

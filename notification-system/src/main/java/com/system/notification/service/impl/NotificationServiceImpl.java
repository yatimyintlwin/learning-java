package com.system.notification.service.impl;


import com.system.notification.service.MessageService;
import com.system.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final MessageService messageService;

    @Autowired
    public NotificationServiceImpl(@Qualifier("emailService") MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void sendNotification(String message) {
        messageService.sendMessage(message);
    }
}

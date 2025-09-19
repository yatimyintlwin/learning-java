package com.system.notification.service.impl;

import com.system.notification.service.SMSService;
import org.springframework.stereotype.Service;

@Service("SMSClass")
public class SMSServiceImpl implements SMSService {
    public String test = "class";

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending SMS " + message + test);
    }
}

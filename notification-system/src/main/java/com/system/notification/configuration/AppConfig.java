package com.system.notification.configuration;

import com.system.notification.service.impl.EmailServiceImpl;
import com.system.notification.service.impl.SMSServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.system.notification")
public class AppConfig {
    @Bean
    @Primary
    public EmailServiceImpl emailService() {
        return new EmailServiceImpl();
    }

    @Bean
    public SMSServiceImpl SMSService() {
//        return new SMSServiceImpl();
        SMSServiceImpl smsService = new SMSServiceImpl();
        smsService.test = "method";
        return smsService;
    }
}

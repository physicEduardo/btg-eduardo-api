package com.ceiba.btg.services;

import org.springframework.stereotype.Service;

@Service
public class FactoryNotificationService {

    private final EmailNotificationService emailNotificationService;
    private final SmsNotificationService smsNotificationService;

    public FactoryNotificationService(EmailNotificationService emailNotificationService, SmsNotificationService smsNotificationService) {
        this.emailNotificationService = emailNotificationService;
        this.smsNotificationService = smsNotificationService;
    }

    public void notify(String message, String via) {
        if ("sms".equals(via)) {
            smsNotificationService.notify(message);
        }
        else if ("email".equals(via)) {
            emailNotificationService.notify(message);
        }
        else {
            throw new RuntimeException("Not allowed notification system!");
        }
    }
}

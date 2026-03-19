package com.ceiba.btg.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FactoryNotificationServiceTest {
    private EmailNotificationService emailService;
    private SmsNotificationService smsService;
    private FactoryNotificationService factoryService;

    @BeforeEach
    void setUp() {
        emailService = mock(EmailNotificationService.class);
        smsService = mock(SmsNotificationService.class);
        factoryService = new FactoryNotificationService(emailService, smsService);
    }

    @Test
    void notify_email_callsEmailService() {
        factoryService.notify("msg", "email");
        verify(emailService, times(1)).notify("msg");
        verifyNoInteractions(smsService);
    }

    @Test
    void notify_sms_callsSmsService() {
        factoryService.notify("msg", "sms");
        verify(smsService, times(1)).notify("msg");
        verifyNoInteractions(emailService);
    }

    @Test
    void notify_invalidType_throwsException() {
        assertThrows(RuntimeException.class, () -> factoryService.notify("msg", "push"));
    }
}

package com.ceiba.btg.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmsNotificationServiceTest {
    @Test
    void notify_printsSmsMessage() {
        SmsNotificationService service = new SmsNotificationService();
        assertDoesNotThrow(() -> service.notify("Test sms"));
    }
}

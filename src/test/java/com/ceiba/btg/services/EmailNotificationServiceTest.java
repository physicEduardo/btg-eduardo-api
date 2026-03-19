package com.ceiba.btg.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailNotificationServiceTest {
    @Test
    void notify_printsEmailMessage() {
        EmailNotificationService service = new EmailNotificationService();
        assertDoesNotThrow(() -> service.notify("Test email"));
    }
}

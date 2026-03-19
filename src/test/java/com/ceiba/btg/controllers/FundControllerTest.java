package com.ceiba.btg.controllers;

import com.ceiba.btg.dto.requests.CancelFundRequest;
import com.ceiba.btg.dto.requests.SubscribeFundRequest;
import com.ceiba.btg.services.FundsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FundControllerTest {
    private FundsService fundsService;
    private FundController controller;

    @BeforeEach
    void setUp() {
        fundsService = mock(FundsService.class);
        controller = new FundController(fundsService);
    }

    @Test
    void subscribeToNewFund_returnsOk() {
        SubscribeFundRequest request = mock(SubscribeFundRequest.class);
        when(request.fundName()).thenReturn("FundA");
        ResponseEntity<String> response = controller.subscribeToNewFund(request);
        assertEquals("Subscribed to fund!", response.getBody());
        verify(fundsService).subscribe(eq("FundA"), any());
    }

    @Test
    void cancelFundSubscription_returnsOk() {
        CancelFundRequest request = mock(CancelFundRequest.class);
        when(request.fundName()).thenReturn("FundA");
        ResponseEntity<String> response = controller.cancelFundSubscription(request);
        assertEquals("Canceled fund!", response.getBody());
        verify(fundsService).cancelSubscription(eq("FundA"), any());
    }
}

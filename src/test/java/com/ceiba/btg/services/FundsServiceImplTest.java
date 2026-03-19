package com.ceiba.btg.services;

import com.ceiba.btg.exceptions.BalanceLimitReachedException;
import com.ceiba.btg.exceptions.FundNotAssignedException;
import com.ceiba.btg.exceptions.UserOrFundException;
import com.ceiba.btg.persistence.entities.Fund;
import com.ceiba.btg.persistence.entities.Client;
import com.ceiba.btg.persistence.entities.Operation;
import com.ceiba.btg.persistence.repository.ClientRepository;
import com.ceiba.btg.persistence.repository.FundRepository;
import com.ceiba.btg.persistence.repository.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FundsServiceImplTest {
    private FundRepository fundRepository;
    private ClientRepository clientRepository;
    private FactoryNotificationService notificationService;
    private OperationRepository operationRepository;
    private FundsServiceImpl fundsService;

    @BeforeEach
    void setUp() {
        fundRepository = mock(FundRepository.class);
        clientRepository = mock(ClientRepository.class);
        notificationService = mock(FactoryNotificationService.class);
        operationRepository = mock(OperationRepository.class);
        fundsService = new FundsServiceImpl(fundRepository, clientRepository, notificationService, operationRepository);
    }

    @Test
    void subscribe_successful() {
        Fund fund = new Fund("FundA", 100L, "cat");
        Client client = mock(Client.class);
        when(fundRepository.findByName("FundA")).thenReturn(fund);
        when(clientRepository.findByUsername("user")).thenReturn(client);
        when(client.getBalance()).thenReturn(200L);
        when(client.getFunds()).thenReturn(Collections.emptyList());
        doNothing().when(client).setBalance(anyLong());
        doNothing().when(client).updateFund(anyString());
        fundsService.subscribe("FundA", "user");
        verify(client).setBalance(100L);
        verify(client).updateFund("FundA");
        verify(clientRepository).save(client);
        verify(operationRepository).save(any(Operation.class));
        verify(notificationService).notify(contains("FundA"), any());
    }

    @Test
    void subscribe_throwsUserOrFundException_whenClientOrFundNull() {
        when(fundRepository.findByName("FundA")).thenReturn(null);
        when(clientRepository.findByUsername("user")).thenReturn(null);
        assertThrows(UserOrFundException.class, () -> fundsService.subscribe("FundA", "user"));
    }

    @Test
    void subscribe_throwsBalanceLimitReachedException_whenInsufficientBalance() {
        Fund fund = new Fund("FundA", 100L, "cat");
        Client client = mock(Client.class);
        when(fundRepository.findByName("FundA")).thenReturn(fund);
        when(clientRepository.findByUsername("user")).thenReturn(client);
        when(client.getBalance()).thenReturn(50L);
        assertThrows(BalanceLimitReachedException.class, () -> fundsService.subscribe("FundA", "user"));
    }

    @Test
    void cancelSubscription_successful() {
        Fund fund = new Fund("FundA", 100L, "cat");
        Client client = mock(Client.class);
        when(fundRepository.findByName("FundA")).thenReturn(fund);
        when(clientRepository.findByUsername("user")).thenReturn(client);
        when(client.getFunds()).thenReturn(Collections.singletonList("FundA"));
        doNothing().when(client).setBalance(anyLong());
        doNothing().when(client).removeFund(anyString());
        fundsService.cancelSubscription("FundA", "user");
        verify(client).setBalance(anyLong());
        verify(client).removeFund("FundA");
        verify(clientRepository).save(client);
        verify(operationRepository).save(any(Operation.class));
    }

    @Test
    void cancelSubscription_throwsUserOrFundException_whenClientOrFundNull() {
        when(fundRepository.findByName("FundA")).thenReturn(null);
        when(clientRepository.findByUsername("user")).thenReturn(null);
        assertThrows(UserOrFundException.class, () -> fundsService.cancelSubscription("FundA", "user"));
    }

    @Test
    void cancelSubscription_throwsFundNotAssignedException_whenFundNotAssigned() {
        Fund fund = new Fund("FundA", 100L, "cat");
        Client client = mock(Client.class);
        when(fundRepository.findByName("FundA")).thenReturn(fund);
        when(clientRepository.findByUsername("user")).thenReturn(client);
        when(client.getFunds()).thenReturn(Collections.emptyList());
        assertThrows(FundNotAssignedException.class, () -> fundsService.cancelSubscription("FundA", "user"));
    }
}

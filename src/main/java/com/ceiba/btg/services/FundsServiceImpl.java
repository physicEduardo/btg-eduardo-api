package com.ceiba.btg.services;

import com.ceiba.btg.exceptions.BalanceLimitReachedException;
import com.ceiba.btg.exceptions.FundNotAssignedException;
import com.ceiba.btg.exceptions.UserOrFundException;
import com.ceiba.btg.persistence.entities.Operation;
import com.ceiba.btg.persistence.repository.ClientRepository;
import com.ceiba.btg.persistence.repository.FundRepository;
import com.ceiba.btg.persistence.repository.OperationRepository;
import org.springframework.stereotype.Service;

@Service
public class FundsServiceImpl implements FundsService {

    private final FundRepository fundRepository;
    private final ClientRepository clientRepository;
    private final FactoryNotificationService notificationService;
    private final OperationRepository operationRepository;

    public FundsServiceImpl(FundRepository fundRepository, ClientRepository clientRepository,
                            FactoryNotificationService notificationService, OperationRepository operationRepository) {
        this.fundRepository = fundRepository;
        this.clientRepository = clientRepository;
        this.notificationService = notificationService;
        this.operationRepository = operationRepository;
    }

    @Override
    public void subscribe(String fundName, String username) {
        var fund = fundRepository.findByName(fundName);
        var client = clientRepository.findByUsername(username);
        if (client == null || fund == null) {
            throw new UserOrFundException("Not allowed fund or client");
        }

        if (client.getBalance() < fund.getMinimalAmount()) {
            throw new BalanceLimitReachedException("No tiene saldo disponible para vincularse al fondo " + fundName);
        }
        client.setBalance(client.getBalance() - fund.getMinimalAmount());
        client.updateFund(fund.getName());
        clientRepository.save(client);

        // store operation
        operationRepository.save(new Operation(username, fundName, "subscribe"));

        // notify operation
        notificationService.notify("You were subscribed to " + fundName, client.getNotificationSystem());

    }

    @Override
    public void cancelSubscription(String fundName, String username) {
        var fund = fundRepository.findByName(fundName);
        var client = clientRepository.findByUsername(username);
        if (client == null || fund == null) {
            throw new UserOrFundException("Not allowed fund or client");
        }
        if (!client.getFunds().contains(fundName)) {
            throw new FundNotAssignedException("Fund not assigned");
        }
        client.setBalance(client.getBalance() + fund.getMinimalAmount());
        client.removeFund(fundName);
        clientRepository.save(client);

        // store operation
        operationRepository.save(new Operation(username, fundName, "cancel"));
    }
}

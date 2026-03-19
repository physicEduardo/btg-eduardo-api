package com.ceiba.btg.services;

public interface FundsService {
    void subscribe(String fundName, String username);
    void cancelSubscription(String fundName, String username);
}

package com.ceiba.btg.persistence.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "clients")
public class Client {
    @Transient
    public static final String SEQUENCE_NAME = "client_seq";

    @Id
    private BigInteger id;
    private String username;
    private String password;
    private Long balance;
    private List<String> funds;
    private String notificationSystem;

    public Client(String username, String password, String notificationSystem) {
        this.username = username;
        this.password = password;
        // default value
        this.balance = 500000L;
        this.funds = new ArrayList<String>();
        this.notificationSystem = notificationSystem;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public List<String> getFunds() {
        return funds;
    }

    public void setFunds(List<String> funds) {
        this.funds = funds;
    }

    public String getNotificationSystem() {
        return notificationSystem;
    }

    public void setNotificationSystem(String notificationSystem) {
        this.notificationSystem = notificationSystem;
    }

    public void updateFund(String fundName) {
        this.funds.add(fundName);
    }

    public void removeFund(String fundName) {
        this.funds.remove(fundName);
    }
}

package com.ceiba.btg.persistence.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "operations")
public class Operation {

    @Transient
    public static final String SEQUENCE_NAME = "fund_seq";

    @Id
    private BigInteger transactionId;
    private String username;
    private String fundName;
    private String operation;

    public Operation(String username, String fundName, String operation) {
        this.username = username;
        this.fundName = fundName;
        this.operation = operation;
    }

    public BigInteger getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(BigInteger transactionId) {
        this.transactionId = transactionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}

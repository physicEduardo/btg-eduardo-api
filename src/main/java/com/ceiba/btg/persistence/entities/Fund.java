package com.ceiba.btg.persistence.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document(collection = "funds")
public class Fund {
    @Transient
    public static final String SEQUENCE_NAME = "funds_seq";

    @Id
    private BigInteger id;
    private String name;
    private Long minimalAmount;
    private String category;

    public Fund(String name, Long minimalAmount, String category) {
        this.name = name;
        this.minimalAmount = minimalAmount;
        this.category = category;
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMinimalAmount() {
        return minimalAmount;
    }

    public void setMinimalAmount(Long minimalAmount) {
        this.minimalAmount = minimalAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

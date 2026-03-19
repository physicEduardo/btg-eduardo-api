package com.ceiba.btg.persistence.repository;

import com.ceiba.btg.persistence.entities.Fund;
import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FundRepository extends MongoRepository<Fund, String> {
    @Nullable
    Fund findByName(String name);
}

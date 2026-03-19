package com.ceiba.btg.persistence.repository;

import com.ceiba.btg.persistence.entities.Client;
import com.ceiba.btg.persistence.entities.Fund;
import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
    @Nullable
    Client findByUsername(String username);

    @Nullable
    Boolean existsByUsername(String username);
}

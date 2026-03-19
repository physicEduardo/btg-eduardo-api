package com.ceiba.btg.persistence.repository;

import com.ceiba.btg.persistence.entities.Client;
import com.ceiba.btg.persistence.entities.Operation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperationRepository extends MongoRepository<Operation, String> {
    Page<Operation> findAll(Pageable pageable);
}

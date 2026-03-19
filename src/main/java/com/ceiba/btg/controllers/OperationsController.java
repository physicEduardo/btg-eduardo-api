package com.ceiba.btg.controllers;

import com.ceiba.btg.persistence.entities.Operation;
import com.ceiba.btg.persistence.repository.OperationRepository;
import com.mongodb.internal.operation.Operations;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    private final OperationRepository operationRepository;

    public OperationsController(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @GetMapping("/list")
    @io.swagger.v3.oas.annotations.Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<Page<Operation>> listOperations(Pageable pageable) {
        return ResponseEntity.ok(operationRepository.findAll(pageable));
    }
}

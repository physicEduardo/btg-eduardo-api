package com.ceiba.btg.controllers;

import com.ceiba.btg.persistence.entities.Operation;
import com.ceiba.btg.persistence.repository.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OperationsControllerTest {
    private OperationRepository operationRepository;
    private OperationsController controller;

    @BeforeEach
    void setUp() {
        operationRepository = mock(OperationRepository.class);
        controller = new OperationsController(operationRepository);
    }

    @Test
    void listOperations_returnsPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Operation> page = new PageImpl<>(Collections.emptyList());
        when(operationRepository.findAll(pageable)).thenReturn(page);
        ResponseEntity<Page<Operation>> response = controller.listOperations(pageable);
        assertEquals(page, response.getBody());
        verify(operationRepository).findAll(pageable);
    }
}

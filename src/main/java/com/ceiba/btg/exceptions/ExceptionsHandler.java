package com.ceiba.btg.exceptions;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            UserOrFundException.class,
            BalanceLimitReachedException.class,
            FundNotAssignedException.class
    })
    @Nullable
    ResponseEntity<String> handleConflict(BtgException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }
}

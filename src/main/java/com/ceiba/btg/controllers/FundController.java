package com.ceiba.btg.controllers;

import com.ceiba.btg.dto.requests.CancelFundRequest;
import com.ceiba.btg.dto.requests.SubscribeFundRequest;
import com.ceiba.btg.services.FundsService;
import com.ceiba.btg.utils.AuthenticationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funds")
public class FundController {

    private final FundsService fundsService;

    public FundController(FundsService fundsService) {
        this.fundsService = fundsService;
    }

    @PostMapping("/subscribe")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<String> subscribeToNewFund(@RequestBody SubscribeFundRequest request) {
        // get username from auth
        var username = AuthenticationUtil.getUsernameFromAuthentication(SecurityContextHolder.getContext().getAuthentication());
        fundsService.subscribe(request.fundName(), username);
        return ResponseEntity.ok("Subscribed to fund!");
    }

    @PostMapping("/cancel")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<String> cancelFundSubscription(@RequestBody CancelFundRequest request) {
        // get username from auth
        var username = AuthenticationUtil.getUsernameFromAuthentication(SecurityContextHolder.getContext().getAuthentication());
        fundsService.cancelSubscription(request.fundName(), username);
        return ResponseEntity.ok("Canceled fund!");
    }
}


package com.ceiba.btg.controllers;

import com.ceiba.btg.dto.requests.LoginRequest;
import com.ceiba.btg.dto.requests.RegisterRequest;
import com.ceiba.btg.persistence.entities.Client;
import com.ceiba.btg.persistence.repository.ClientRepository;
import com.ceiba.btg.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtils;
    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;

    public LoginController(AuthenticationManager authenticationManager,
                           ClientRepository clientRepository,
                           JwtUtil jwtUtils, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.clientRepository = clientRepository;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(jwtUtils.generateToken(userDetails.getUsername()));
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest loginRequest) {
        if (Boolean.TRUE.equals(clientRepository.existsByUsername(loginRequest.username()))) {
            return "Error: Username is already taken!";
        }

        Client newClient = new Client(
                loginRequest.username(),
                encoder.encode(loginRequest.password()),
                loginRequest.notificationSystem()
        );
        clientRepository.save(newClient);
        return "User registered successfully with $500000COP!";
    }
}

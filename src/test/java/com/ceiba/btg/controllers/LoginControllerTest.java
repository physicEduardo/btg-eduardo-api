package com.ceiba.btg.controllers;

import com.ceiba.btg.dto.requests.LoginRequest;
import com.ceiba.btg.dto.requests.RegisterRequest;
import com.ceiba.btg.persistence.entities.Client;
import com.ceiba.btg.persistence.repository.ClientRepository;
import com.ceiba.btg.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private ClientRepository clientRepository;
    private PasswordEncoder encoder;
    private LoginController controller;

    @BeforeEach
    void setUp() {
        authenticationManager = mock(AuthenticationManager.class);
        jwtUtil = mock(JwtUtil.class);
        clientRepository = mock(ClientRepository.class);
        encoder = mock(PasswordEncoder.class);
        controller = new LoginController(authenticationManager, clientRepository, jwtUtil, encoder);
    }

    @Test
    void login_returnsToken() {
        LoginRequest request = mock(LoginRequest.class);
        when(request.username()).thenReturn("user");
        when(request.password()).thenReturn("pass");
        Authentication auth = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);
        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("user");
        when(jwtUtil.generateToken("user")).thenReturn("token");
        ResponseEntity<String> response = controller.login(request);
        assertEquals("token", response.getBody());
    }

    @Test
    void registerUser_success() {
        RegisterRequest request = mock(RegisterRequest.class);
        when(request.username()).thenReturn("user");
        when(request.password()).thenReturn("pass");
        when(request.notificationSystem()).thenReturn("email");
        when(clientRepository.existsByUsername("user")).thenReturn(false);
        when(encoder.encode("pass")).thenReturn("encoded");
        String result = controller.registerUser(request);
        assertTrue(result.contains("User registered successfully"));
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    void registerUser_usernameTaken() {
        RegisterRequest request = mock(RegisterRequest.class);
        when(request.username()).thenReturn("user");
        when(clientRepository.existsByUsername("user")).thenReturn(true);
        String result = controller.registerUser(request);
        assertTrue(result.contains("Error: Username is already taken!"));
    }
}

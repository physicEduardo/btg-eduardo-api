package com.ceiba.btg.utils;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationUtilTest {
    @Test
    void getUsernameFromAuthentication_returnsUsername_whenAuthenticated() {
        Authentication auth = new UsernamePasswordAuthenticationToken("user", "pass");
        String username = AuthenticationUtil.getUsernameFromAuthentication(auth);
        assertEquals("user", username);
    }

    @Test
    void getUsernameFromAuthentication_returnsNull_whenAnonymous() {
        Authentication auth = null;
        String username = AuthenticationUtil.getUsernameFromAuthentication(auth);
        assertNull(username);
    }
}

package com.ceiba.btg.utils;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "jwtSecret", "mysecretkeymysecretkeymysecretkey12");
        ReflectionTestUtils.setField(jwtUtil, "jwtExpirationMs", 3600000);
        jwtUtil.init();
    }

    @Test
    void generateToken_and_getUsernameFromToken_workCorrectly() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);
        String extracted = jwtUtil.getUsernameFromToken(token);
        assertEquals(username, extracted);
        assertTrue(jwtUtil.validateJwtToken(token));
    }

    @Test
    void validateJwtToken_returnsFalse_forMalformedToken() {
        String malformedToken = "abc.def.ghi";
        assertFalse(jwtUtil.validateJwtToken(malformedToken));
    }

    @Test
    void validateJwtToken_returnsFalse_forEmptyToken() {
        assertFalse(jwtUtil.validateJwtToken(""));
    }

    @Test
    void validateJwtToken_returnsFalse_forExpiredToken() {
        ReflectionTestUtils.setField(jwtUtil, "jwtExpirationMs", -1000); // Token expirado
        jwtUtil.init();
        String token = jwtUtil.generateToken("expireduser");
        assertFalse(jwtUtil.validateJwtToken(token));
    }
}

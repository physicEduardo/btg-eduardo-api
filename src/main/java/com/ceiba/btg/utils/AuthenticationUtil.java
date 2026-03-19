package com.ceiba.btg.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationUtil {
    public static String getUsernameFromAuthentication(Authentication authentication) {
        if (authentication != null && !(authentication instanceof org.springframework.security.authentication.AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}

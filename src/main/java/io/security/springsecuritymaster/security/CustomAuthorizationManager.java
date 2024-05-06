package io.security.springsecuritymaster.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    
    public final static String REQUIRED_ROLE = "ROLE_SECURE";
    
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        
        Authentication auth = authentication.get();
        
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return new AuthorizationDecision(false);
        }
        
        boolean roleCheck = auth.getAuthorities()
                .stream()
                .anyMatch(role -> REQUIRED_ROLE.equals(role.getAuthority()));
        
        
        return new AuthorizationDecision(roleCheck);
    }
}

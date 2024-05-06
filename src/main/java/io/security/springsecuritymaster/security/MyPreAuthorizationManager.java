package io.security.springsecuritymaster.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;

import java.util.function.Supplier;

/**
 * 인증을 받은 사용자만 메서드에 진입할수있다.
 */
public class MyPreAuthorizationManager implements AuthorizationManager<MethodInvocation> {
    
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, MethodInvocation object) {
        Authentication auth = authentication.get();
        
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return new AuthorizationDecision(false);
        }
        
        return new AuthorizationDecision(auth.isAuthenticated());
    }
}

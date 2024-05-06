package io.security.springsecuritymaster.security;

import io.security.springsecuritymaster.controller.Account;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.method.MethodInvocationResult;
import org.springframework.security.core.Authentication;

import java.util.function.Supplier;

/**
 * 인증 받은 사용자만 메서드의 반환값에 접근할수있따.
 */
public class MyPostAuthorizationManager implements AuthorizationManager<MethodInvocationResult> {
    
    
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, MethodInvocationResult object) {
        
        Authentication auth = authentication.get();
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return new AuthorizationDecision(false);
        }
        Account account = (Account) object.getResult();
        boolean isGranted = account.getOwner().equals(auth.getName());
        
        return new AuthorizationDecision(isGranted);
    }
}

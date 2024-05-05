package io.security.springsecuritymaster.controller;

import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.stereotype.Component;

@Component("MyAuthorizer")
public class CustomAuthorizer {
    
    public boolean isUser(MethodSecurityExpressionOperations root) {
        return root.hasAuthority("ROLE_USER");
    }
}

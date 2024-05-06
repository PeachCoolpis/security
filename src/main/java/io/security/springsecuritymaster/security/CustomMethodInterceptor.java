package io.security.springsecuritymaster.security;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomMethodInterceptor implements MethodInterceptor {
    
    private final AuthorizationManager<MethodInvocation> authorizationManager;
    
    public CustomMethodInterceptor(AuthorizationManager<MethodInvocation> authorizationManager) {
        this.authorizationManager = authorizationManager;
    }
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        
        boolean granted = authorizationManager.check(() -> authentication, invocation).isGranted(); // true면 허용하겠다는뜻
        if (granted) {
           return invocation.proceed(); // 실제 객체의 진짜 메서드 호출
        }
        
        throw new AccessDeniedException("Access Denied");
        
    }
}

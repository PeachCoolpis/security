package io.security.springsecuritymaster.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;


public class CustomAuthenticationProvider2 implements AuthenticationProvider {
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        String loginId = authentication.getName(); // 로그인 아이디
        String password = (String) authentication.getCredentials(); // 비밀번호
        System.out.println("클래스패스 = " + System.getProperty("java.class.path"));
        
        // 아이디 검증
        
        // 비밀번호 검증
        
        return new UsernamePasswordAuthenticationToken(loginId, password, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        // 토큰 타입으로 리턴
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}

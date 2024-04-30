package io.security.springsecuritymaster.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


// 여기서 Bean으로 정의하면 Security Config에다가는 따로 추가할 필요없음
// 왜냐하면 빈으로 이미 정의되면 자동적으로 이 빈을 가지고 가서 DaoAuthenticationProvider를 대체함
//@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    
    private final UserDetailsService userDetailsService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        String loginId = authentication.getName(); // 로그인 아이디
        String password = (String) authentication.getCredentials(); // 비밀번호
        //System.out.println("클래스패스 = " + System.getProperty("java.class.path"));
        
        // 아이디 검증
        UserDetails user = userDetailsService.loadUserByUsername(loginId);
        if (user == null) {
            throw new UsernameNotFoundException("사용자 없음");
        }
        // 비밀번호 검증
        
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        // 토큰 타입으로 리턴
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}

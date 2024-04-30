package io.security.springsecuritymaster.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public CustomAuthenticationFilter(HttpSecurity httpSecurity) {
        super(new AntPathRequestMatcher("/api/login", "/GET"));
        setSecurityContextRepository(getSecurityContextRepository(httpSecurity));
    }
    
    private SecurityContextRepository getSecurityContextRepository(HttpSecurity httpSecurity) {
        SecurityContextRepository contextRepository = httpSecurity.getSharedObject(SecurityContextRepository.class);
        if (contextRepository == null) {
            contextRepository =
                    new DelegatingSecurityContextRepository(
                            new HttpSessionSecurityContextRepository()
                            , new RequestAttributeSecurityContextRepository()
                    );
        }
        return contextRepository;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        
        return this.getAuthenticationManager().authenticate(token);
    }
}

package io.security.springsecuritymaster.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailService implements UserDetailsService {
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDto accountDto = new AccountDto("user", "{noop}1234", List.of(new SimpleGrantedAuthority("ROLE_USER")));
        
        return new CustomUserDetails(accountDto);
    }
}

package io.security.springsecuritymaster.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class AccountDto {

    private String username;
    private String password;
    private Collection<GrantedAuthority> roles;
    
    public AccountDto(String username, String password, Collection<GrantedAuthority> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}

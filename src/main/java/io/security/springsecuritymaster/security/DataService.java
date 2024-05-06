package io.security.springsecuritymaster.security;


import io.security.springsecuritymaster.controller.Account;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    
    
    @PreAuthorize("")
    public String getUser() {
        return "user";
    }
    
    
    @PostAuthorize("")
    public Account getOwner(String name) {
        return new Account(name, false);
    }
    
    public String display() {
        return "display";
    }
}

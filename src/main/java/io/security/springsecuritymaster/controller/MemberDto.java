package io.security.springsecuritymaster.controller;

import lombok.*;


@Setter
public class MemberDto {
    private String username;
    private String password;
    
    public MemberDto() {
    }
    
    public MemberDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
}

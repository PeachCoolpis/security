package io.security.springsecuritymaster.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class IndexController {
    
    private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
    
    @GetMapping("/")
    public String index() {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication) ? "anonymous" : "authenticated";
    }
    
    @GetMapping("/user")
    public User user(@AuthenticationPrincipal User user) {
        return user;
    }
    
    
    @GetMapping("/user2")
    public String user(@AuthenticationPrincipal(expression = "username") String user) {
        return user;
    }
    
    @GetMapping("/currentUser")
    public User currentUser(@CurrentUser User user) {
        return user;
    }
    @GetMapping("/currentUser2")
    public String currentUsername(@CurrentUsername String user) {
        return user;
    }
    
    @GetMapping("/db")
    public String db() {
        return "db";
    }
    
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}


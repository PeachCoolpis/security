package io.security.springsecuritymaster.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class IndexController {
    
  
    
    @GetMapping("/")
    public Authentication index() {
        SecurityContext context = SecurityContextHolder.getContextHolderStrategy().getContext();
        return context.getAuthentication();
    }
    
    
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    
    
    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }
    
    @GetMapping("/anonymous")
    public String anonymous() {
        return "anonymous";
    }
    
    @GetMapping("/anonymousContext")
    public String anonymousContext(@CurrentSecurityContext SecurityContext context) {
        return context.getAuthentication().getName();
    }
    
    @GetMapping("/authentication")
    public String authentication(Authentication authentication) {
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "anonymous";
        }
        return "null";
    }
    
    @GetMapping("/logoutSuccess")
    public String logoutSuccess() {
        return "logoutSuccess";
    }
    @GetMapping("/expiredUrl")
    public String expiredUrl() {
        return "expiredUrl";
    }
    @GetMapping("/invalidSessionUrl")
    public String invalidSessionUrl() {
        return "invalidSessionUrl";
    }
    @GetMapping("/login")
    public String login() {
        return "loginPage";
    }
    
    @GetMapping("/denied")
    public String denied() {
        return "denied";
    }
    
    @PostMapping("/csrf")
    public String csrf() {
        return "csrf 적용됨";
    }
    
    @PostMapping("/ignoreCsrf")
    public String ignoreCsrf() {
        return "ignoreCsrf";
    }
    
}


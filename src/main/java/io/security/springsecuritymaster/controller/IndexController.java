package io.security.springsecuritymaster.controller;

import io.security.springsecuritymaster.security.SecurityContextService;
import io.security.springsecuritymaster.security.SessionInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class IndexController {
    
    private final SessionInfoService sessionInfoService;
    
    @GetMapping("/")
    public String index() {
        SecurityContext context = SecurityContextHolder.getContextHolderStrategy().getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println("authentication = " + authentication);
        
        return "index";
    }
    
    @GetMapping("/sessionInfo")
    public String sessionInfo() {
        sessionInfoService.sessionInfo();
        return "sessionInfo";
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
}

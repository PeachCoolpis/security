package io.security.springsecuritymaster.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/custom")
    public String custom(){
        return "custom";
    }
    
    @GetMapping("/user/{name}")
    public String userName(@PathVariable(value = "name") String name){
        return name;
    }
    
    @GetMapping("/admin/db")
    public String admin(){
        return "admin";
    }
}


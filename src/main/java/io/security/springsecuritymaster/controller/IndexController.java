package io.security.springsecuritymaster.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class IndexController {
    
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/user")
    public String user() {
        return "user";
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


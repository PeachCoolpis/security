package io.security.springsecuritymaster.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {
    
    @GetMapping("/form")
    public String form() {
        return "form";
    }
    
    @GetMapping("/cookie")
    public String cookie() {
        return "cookie";
    }
}

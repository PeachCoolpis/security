package io.security.springsecuritymaster.controller;


import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.Pipe;

@RestController
public class MethodController {
    
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin() {
        return "admin";
    }
    
    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public String user() {
        return "user";
    }
    @GetMapping("/isAuthenticated")
    @PreAuthorize("isAuthenticated()")
    public String isAuthenticated() {
        return "isAuthenticated";
    }
    @GetMapping("/user/{id}")
    @PreAuthorize("#id == authentication.name")
    public String authentication(@PathVariable(name = "id") String id){
        return id;
    }
    
    @GetMapping("/owner")
    @PostAuthorize("returnObject.owner == authentication.name")
    public Account owner(@RequestParam(value = "name") String name) {
        return new Account(name, false);
    }
    
    @GetMapping("/isSecure")
    @PostAuthorize("hasAuthority('ROLE_ADMIN') and  returnObject.secure == true ")
    public Account isSecure(@RequestParam(name = "name") String name , @RequestParam(name = "secure") String secure) {
        return new Account(name, "Y".equals(secure));
    }
    
}

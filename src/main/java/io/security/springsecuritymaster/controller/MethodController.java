package io.security.springsecuritymaster.controller;


import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 메서드 요청 권한 요청
 */
@RestController
@RequiredArgsConstructor
public class MethodController {
    
    @GetMapping("/user")
    @Secured("ROLE_USER")
    public String user() {
        return "user";
    }
    
    @GetMapping("/admin")
    @RolesAllowed("ADMIN")
    public String admin() {
        return "admin";
    }
    
    @GetMapping("/permitAll")
    @PermitAll
    public String permitAll() {
        return "permitAll";
    }
    
    @GetMapping("/denyAll")
    @DenyAll
    public String denyAll() {
        return "denyAll";
    }
    
    @GetMapping("/isAdmin")
    @IsAdmin
    public String isAdmin() {
        return "isAdmin";
    }
    
    @GetMapping("/ownerShip")
    @OwnerShip
    public Account ownerShip(@RequestParam(name = "name") String name) {
        return new Account(name, false);
    }
    @GetMapping("/delete")
    @PreAuthorize("@MyAuthorizer.isUser(#root)")
    public String delete() {
        return "delete";
    }
}

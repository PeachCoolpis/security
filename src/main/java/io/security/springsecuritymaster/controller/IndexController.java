package io.security.springsecuritymaster.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@RestController
public class IndexController {
    
    @GetMapping("/")
    public String index(HttpServletRequest request) {
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
    
    @GetMapping("/login")
    public String login(HttpServletRequest request
            ,MemberDto memberDto) throws ServletException {
        request.login(memberDto.getUsername(), memberDto.getPassword());
        System.out.println("login 성공");
        return "login";
    }
    @GetMapping("/users")
    public List<MemberDto> users(
            HttpServletRequest request
            , HttpServletResponse response
        ) throws ServletException, IOException {
        boolean authenticate = request.authenticate(response);
        if (authenticate) {
            return List.of(new MemberDto("user","1234"));
        }
        return Collections.emptyList();
    }
}


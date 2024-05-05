package io.security.springsecuritymaster.security;


import io.security.springsecuritymaster.controller.CustomRequestMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultHttpSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http , HandlerMappingIntrospector introspector) throws Exception {
//
//
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/user/{name}").access(new WebExpressionAuthorizationManager("#name == authentication.name"))
//
//                        .requestMatchers("/admin/db")
//                        .access(new WebExpressionAuthorizationManager("hasAnyAuthority('ROLE_DB') or hasAnyAuthority('ROLE_ADMIN')"))
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//
//
//        ;
//        return http.build();
//    }
//
//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http , ApplicationContext context) throws Exception {
//
//    DefaultHttpSecurityExpressionHandler expressionHandler = new DefaultHttpSecurityExpressionHandler();
//    expressionHandler.setApplicationContext(context);
//
//    WebExpressionAuthorizationManager authorizationManager = new WebExpressionAuthorizationManager("@customWebSecurity.check(authentication,request)");
//    authorizationManager.setExpressionHandler(expressionHandler);
//
//    http
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/custom/**").access(authorizationManager)
//                    .anyRequest().authenticated())
//            .formLogin(Customizer.withDefaults())
//
//
//    ;
//    return http.build();
//}
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http , ApplicationContext context) throws Exception {
    

    
    http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(new CustomRequestMatcher("/admin/**")).hasAnyAuthority("ROLE_ADMIN")
                    .anyRequest().authenticated())
            .formLogin(Customizer.withDefaults())
    
    
    ;
    return http.build();
}
    
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("user")
                .password("{noop}1234")
                .roles("USER")
                .build();
        
        UserDetails user2 = User.withUsername("manager")
                .password("{noop}1234")
                .roles("MANAGER")
                .build();
        
        UserDetails user3 = User.withUsername("admin")
                .password("{noop}1234")
                .roles("ADMIN","WRITE")
                .build();
        return new InMemoryUserDetailsManager(user1,user2,user3);
        
    }
}

package io.security.springsecuritymaster.security;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true , jsr250Enabled = true)
@Configuration
public class SecurityConfig {
    
    private final String[] paths = {"/css/**", "/images/**", "/js/**", "webjars/**", "/favicon.*", "/*/icon-*"};
    
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(paths).permitAll()
                        .anyRequest().permitAll()) // 요청 기반 권한요청
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
        ;
        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("user")
                .password("{noop}1234")
                .roles("USER")
                .build();
        
        UserDetails user2 = User.withUsername("db")
                .password("{noop}1234")
                .roles("DB")
                .build();
        
        UserDetails user3 = User.withUsername("admin")
                .password("{noop}1234")
                .roles("ADMIN", "SECURE")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
        
    }
}

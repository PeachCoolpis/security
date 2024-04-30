package io.security.springsecuritymaster.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/logoutSuccess").permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(Customizer.withDefaults())
        
                
                
        
                
        ;
        return http.build();
    }
    
  
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }
    
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("user")
//                .password("{noop}1234")
//                .roles("USER")
//                .build();
//        UserDetails user2 = User.withUsername("user")
//                .password("{noop}1234")
//                .roles("USER")
//                .build();
//        UserDetails user3 = User.withUsername("user")
//                .password("{noop}1234")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user1);
//
//    }
}

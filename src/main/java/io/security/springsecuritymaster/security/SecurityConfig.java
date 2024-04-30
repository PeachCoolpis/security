package io.security.springsecuritymaster.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http
            , AuthenticationManagerBuilder builder
            , AuthenticationConfiguration configuration) throws Exception {
        
        AuthenticationManagerBuilder managerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder.authenticationProvider(coustomAuthenticationProvider());
        
        ProviderManager authenticationManager = (ProviderManager) configuration.getAuthenticationManager();
        authenticationManager.getProviders().remove(0); // parant에 있는것 삭제
        builder.authenticationProvider(new DaoAuthenticationProvider());
        
        //AuthenticationManagerBuilder managerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        //managerBuilder.authenticationProvider(new CustomAuthenticationProvider());
        //managerBuilder.authenticationProvider(new CustomAuthenticationProvider2()); 아래에 있는 http.authenticationProvider 에 추가해도 동일
        
        
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/logoutSuccess").permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(Customizer.withDefaults())
//                .authenticationProvider(new CustomAuthenticationProvider())
//                .authenticationProvider(new CustomAuthenticationProvider2())
                
                
        
                
        ;
        return http.build();
    }
    
    @Bean
    public AuthenticationProvider coustomAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("user")
                .password("{noop}1234")
                .roles("USER")
                .build();
//        UserDetails user2 = User.withUsername("user")
//                .password("{noop}1234")
//                .roles("USER")
//                .build();
//        UserDetails user3 = User.withUsername("user")
//                .password("{noop}1234")
//                .roles("USER")
//                .build();
        return new InMemoryUserDetailsManager(user1);
        
    }
}

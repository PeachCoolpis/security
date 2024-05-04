package io.security.springsecuritymaster.security;


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
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http , HandlerMappingIntrospector introspector) throws Exception {
        
        
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll() //
                        .requestMatchers("/user").hasAnyAuthority("ROLE_USER") // user 엔드 포인트에 대해 USER 권한이 필요
                        .requestMatchers("/myPage/**").hasRole("USER") // mypage 에 관련된 모드 엔드포인트는 USER 권한이 필요
                        .requestMatchers(HttpMethod.POST).hasAnyAuthority("ROLE_WRITE") // HTTP POST 메소드는 WRITE 권한 필요
                        .requestMatchers(new AntPathRequestMatcher("/manager")).hasAnyAuthority("ROLE_MANAGER") // manager 엔드 포인트는 MANAGER 권한이 필요
                        .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN","ROLE_MANAGER") // "/admin" 및 하위 디렉터리에 대해 "ADMIN" 또는 "MANAGER" 권한 중 하나를 요구합니다.
                        .requestMatchers(new MvcRequestMatcher(introspector,"/admin/payment")).hasAnyAuthority("ROLE_ADMIN") // "/manager" 및 하위 디렉터리에 대해 "MANAGER" 권한을 요구합니다. AntPathRequestMatcher 사용.
                        .requestMatchers(new RegexRequestMatcher("/resource/[A-Za-z0-9]+",null)).hasAnyAuthority("ROLE_MANAGER")
                        .anyRequest().authenticated()) // 위에서 정의한 규칙외의 모든 요청은 인증을 필요로 한다.
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
        
        ;
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/csrf", "/csrfToken","/form","/formCsrf").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//
//
//        ;
//        return http.build();
//    }
//
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailService();
//    }
    
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

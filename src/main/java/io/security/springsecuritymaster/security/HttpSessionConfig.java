package io.security.springsecuritymaster.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableSpringHttpSession
public class HttpSessionConfig {
    
    
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setUseHttpOnlyCookie(true); // 보안 쿠키 설정
        cookieSerializer.setUseSecureCookie(true); // http 통신에만 사용
        cookieSerializer.setSameSite("Lax"); // 속성 설정 Strict , Lax , None
        return cookieSerializer;
    }
    
    @Bean
    public SessionRepository<MapSession> sessionSessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }
}

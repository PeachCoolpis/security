package io.security.springsecuritymaster.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
    
    private boolean flag;
    
    
    @Override
    public void init(HttpSecurity http) throws Exception {
        super.init(http);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        MyCustomFilter myCustomFilter = new MyCustomFilter();
        myCustomFilter.setFlag(flag);
        http.addFilterBefore(myCustomFilter, SecurityContextHolderAwareRequestFilter.class);
    }
    
    public boolean setFlag(boolean value) {
        this.flag = value;
        return flag;
    }
    
    public static MyCustomDsl customDsl() {
        return new MyCustomDsl();
    }
    
}

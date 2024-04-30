package io.security.springsecuritymaster.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    
    private final AccountDto accountDto;
    
    public CustomUserDetails(AccountDto accountDto) {
        this.accountDto = accountDto;
    }
    
    /**
     * 사용자에게 부여된 권한을 반환하며 null을 반환 할수없다.
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return accountDto.getRoles();
    }
    
    /**
     * 사용자 이늦ㅇ에 사용된 비밀번호를 반환한다.
     * @return
     */
    @Override
    public String getPassword() {
        return accountDto.getPassword();
    }
    
    /**
     * 사용자 인증에 사용된 사용자 이름을 반환하며 null을 반환할수 없다.
     * @return
     */
    @Override
    public String getUsername() {
        return accountDto.getUsername();
    }
    
    /**
     * 사용자 계정의 유효기간이 지났는지ㅏ를 나타내며 기간이 만료된 계정은 인증할수없다.
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    /**
     * 사용자가 잠겨있는지 아닌지를 나타내며 잠긴 사용자는 인증할수없다.
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    /**
     * 사용자의 비밀번호가 유효기간을 지났는지를 확인하며 유효기간이 지난 비밀번호는 인증할수 없다.
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    /**
     * 사용자가 활성화 되었는지 비활성화 되었는지를 나타내며 비활성화된 사용자는 인증할수 없다.
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

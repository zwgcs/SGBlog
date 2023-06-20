package com.sangeng.domain.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.sangeng.domain.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author shiquan Email:1718048299@qq.com
 * @Date 2023/3/31 8:20
 * @Since version 1.0
 */
@Data
public class UserLogin implements UserDetails{

    private User user;

    private List<String> authorityList;

    public UserLogin(User user, List<String> authorityList) {
        this.user = user;
        this.authorityList = authorityList;
    }

    public UserLogin(User user){
        this.user = user;
    }

    public UserLogin() {
    }

    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities == null && authorityList!=null){
            authorities = authorityList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return authorities;
    }



    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

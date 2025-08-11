package com.example.Post.security;

import com.example.Post.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserDetailsImpl implements UserDetails {
    
    private final User user;

    public UserDetailsImpl(User user){
        this.user = user;
    }


    public User getUser(){
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();  
    }

    @Override
    public boolean isAccountNonExpired(){
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
    public Collection <? extends GrantedAuthority> getAuthorities(){
        return Collections.emptyList(); // 권한이 필요하지 않다면 빈 리스트 반환
    }
}

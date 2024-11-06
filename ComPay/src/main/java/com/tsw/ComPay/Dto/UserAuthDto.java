package com.tsw.ComPay.Dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class UserAuthDto implements UserDetails {

     private  String username;
     private String password;
     private List<GroupDto> group;
     private String email;

     public UserAuthDto(UserDto user, List<GroupDto> group) {
         this.username = user.getUsername();
         this.password = user.getPassword();
         this.group = group;
         this.email = user.getEmail();

     }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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
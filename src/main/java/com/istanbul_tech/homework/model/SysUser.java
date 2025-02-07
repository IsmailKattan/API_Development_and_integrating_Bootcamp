package com.istanbul_tech.homework.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document(collection = "user")
@Getter
@Setter
@ToString
public class SysUser implements UserDetails {
    @Id
    private String id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private List<String> addressIds;

    private boolean enabled;

    private boolean accountNonExpired;

    private boolean credentialsNonExpired;

    private boolean accountNonLocked;

    private List<GrantedAuthority> authorities;

    public SysUser(String username, String password, String name, String surname, String email, String phone, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.enabled = true;
        this.accountNonExpired = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
        this.authorities = authorities;
        this.addressIds = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

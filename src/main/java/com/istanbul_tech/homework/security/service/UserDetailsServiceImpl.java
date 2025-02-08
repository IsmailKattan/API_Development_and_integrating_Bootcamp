package com.istanbul_tech.homework.security.service;

import com.istanbul_tech.homework.exception.custom.NullParamException;
import com.istanbul_tech.homework.exception.custom.UsernameNotFoundException;
import com.istanbul_tech.homework.model.SysUser;
import com.istanbul_tech.homework.repo.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username == null || username.isEmpty()) {
            throw new NullParamException(
                    "username",
                    null,
                    Map.of(
                            "username", "null or empty"
                    ),
                    "UserDetailsServiceImpl",
                    null
            );
        }
        SysUser sysUser = sysUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        null,
                        Map.of(
                                "username", username
                        ),
                        "UserDetailsServiceImpl",
                        null
                )
        );
        return new User(
                sysUser.getUsername(),
                sysUser.getPassword(),
                sysUser.isEnabled(),
                sysUser.isAccountNonExpired(),
                sysUser.isCredentialsNonExpired(),
                sysUser.isAccountNonLocked(),
                sysUser.getAuthorities()
        );
    }
}

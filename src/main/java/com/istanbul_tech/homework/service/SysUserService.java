package com.istanbul_tech.homework.service;

import com.istanbul_tech.homework.dto.LoginDto;
import com.istanbul_tech.homework.dto.ToRegisterUserDto;
import com.istanbul_tech.homework.dto.body.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SysUserService {

    ApiResponse register(ToRegisterUserDto toRegisterUserDto);

    ApiResponse login(LoginDto loginDto);

    ApiResponse getUser(String username);

    ApiResponse updateUser(String username, ToRegisterUserDto toRegisterUserDto);

    String getUserIdByUsername(String username);
}

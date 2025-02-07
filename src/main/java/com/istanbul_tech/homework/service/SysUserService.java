package com.istanbul_tech.homework.service;

import com.istanbul_tech.homework.dto.LoginDto;
import com.istanbul_tech.homework.dto.ToRegisterUserDto;
import com.istanbul_tech.homework.dto.body.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface SysUserService {

    ApiResponse register(ToRegisterUserDto toRegisterUserDto);

    ApiResponse login(LoginDto loginDto);
}

package com.istanbul_tech.homework.controller;

import com.istanbul_tech.homework.dto.LoginDto;
import com.istanbul_tech.homework.dto.ToRegisterUserDto;
import com.istanbul_tech.homework.dto.body.ApiResponse;
import com.istanbul_tech.homework.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/register")
    @Operation(
            summary = "Register a new user",
            description = "Register a new user with the given information")
    public ResponseEntity<?> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,description = "Required user info for register") @RequestBody ToRegisterUserDto toRegisterUserDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(sysUserService.register(toRegisterUserDto));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login",
            description = "Login with the given information")
    public ResponseEntity<?> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Required user credential for login") @RequestBody LoginDto loginDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(sysUserService.login(loginDto));
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    @Operation(
            summary = "Get user",
            description = "Get user with username given as path variable")
    public ResponseEntity<?> getUser(
            @PathVariable String username
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(sysUserService.getUser(username));
    }

    @PutMapping("/user/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    @Operation(
            summary = "Update user",
            description = "Update user with username given as path variable")
    public ResponseEntity<?> updateUser(
            @PathVariable String username,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Required user info for update") @RequestBody ToRegisterUserDto toRegisterUserDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(sysUserService.updateUser(username, toRegisterUserDto));
    }

}

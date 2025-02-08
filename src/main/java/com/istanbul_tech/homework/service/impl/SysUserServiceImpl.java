package com.istanbul_tech.homework.service.impl;

import com.istanbul_tech.homework.dto.AddressDto;
import com.istanbul_tech.homework.dto.LoginDto;
import com.istanbul_tech.homework.dto.ToRegisterUserDto;
import com.istanbul_tech.homework.dto.UserDto;
import com.istanbul_tech.homework.dto.body.ApiResponse;
import com.istanbul_tech.homework.exception.custom.UsernameInUseException;
import com.istanbul_tech.homework.exception.custom.UsernameNotFoundException;
import com.istanbul_tech.homework.model.SysUser;
import com.istanbul_tech.homework.repo.SysUserRepository;
import com.istanbul_tech.homework.security.jwt.JwtUtils;
import com.istanbul_tech.homework.service.AddressService;
import com.istanbul_tech.homework.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {



    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private AddressService addressService;

    @Autowired
    public void setAddressService(@Lazy AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public ApiResponse register(ToRegisterUserDto toRegisterUserDto) {
        // check if user already exists
        if (sysUserRepository.existsByUsername(toRegisterUserDto.getUsername())) {
            throw new UsernameInUseException(
                    null,
                    Map.of("username", toRegisterUserDto.getUsername()),
                    "UserService",
                    null
            );
        }

        // create user
        SysUser sysUser = new SysUser(
                toRegisterUserDto.getUsername(),
                passwordEncoder.encode(toRegisterUserDto.getPassword()),
                toRegisterUserDto.getName(),
                toRegisterUserDto.getSurname(),
                toRegisterUserDto.getEmail(),
                toRegisterUserDto.getPhone(),
                new ArrayList<>()
        );
        sysUser.setAddressIds(List.of());
        sysUserRepository.save(sysUser);
        UserDto userDto = UserDto.builder()
                .username(sysUser.getUsername())
                .name(sysUser.getName())
                .surname(sysUser.getSurname())
                .email(sysUser.getEmail())
                .phone(sysUser.getPhone())
                .build();
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK.name())
                .message("User registered successfully")
                .data(userDto)
                .httpCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public ApiResponse login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        String jwt = jwtUtils.generateToken(userDetails);
        SysUser sysUser = sysUserRepository.findByUsername(loginDto.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException(
                        null,
                        Map.of("username", loginDto.getUsername()),
                        "UserService",
                        null
                )
        );
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK.name())
                .message("User logged in successfully")
                .data(
                        Map.of(
                                "id", sysUser.getId(),
                                "username", sysUser.getUsername(),
                                "name", sysUser.getName(),
                                "surname", sysUser.getSurname(),
                                "email", sysUser.getEmail(),
                                "token", jwt
                        )
                )
                .httpCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public ApiResponse getUser(String username) {
        SysUser sysUser = sysUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        null,
                        Map.of("username", username),
                        "UserService",
                        null
                )
        );
        // get user addresses from address service
        List<AddressDto> addresses = addressService.getAddressListOfUser(sysUser.getId());
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK.name())
                .message("User retrieved successfully")
                .data(
                        UserDto.builder()
                                .id(sysUser.getId())
                                .username(sysUser.getUsername())
                                .name(sysUser.getName())
                                .surname(sysUser.getSurname())
                                .email(sysUser.getEmail())
                                .phone(sysUser.getPhone())
                                .addresses(addresses)
                                .build()
                )
                .httpCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public ApiResponse updateUser(String username, ToRegisterUserDto toRegisterUserDto) {
        // get user
        SysUser sysUser = sysUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        null,
                        Map.of("username", username),
                        "UserService",
                        null
                )
        );
        // update user
        sysUser.setName(toRegisterUserDto.getName());
        sysUser.setSurname(toRegisterUserDto.getSurname());
        sysUser.setEmail(toRegisterUserDto.getEmail());
        sysUser.setPhone(toRegisterUserDto.getPhone());
        sysUserRepository.save(sysUser);
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK.name())
                .message("User updated successfully")
                .data(
                        UserDto.builder()
                                .id(sysUser.getId())
                                .username(sysUser.getUsername())
                                .name(sysUser.getName())
                                .surname(sysUser.getSurname())
                                .email(sysUser.getEmail())
                                .phone(sysUser.getPhone())
                                .build()
                )
                .httpCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public String getUserIdByUsername(String username) {
        SysUser sysUser = sysUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        null,
                        Map.of("username", username),
                        "UserService",
                        null
                )
        );
        return sysUser.getId();
    }

}

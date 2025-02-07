package com.istanbul_tech.homework.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ToRegisterUserDto {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String username;
}

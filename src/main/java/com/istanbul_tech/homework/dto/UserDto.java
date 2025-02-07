package com.istanbul_tech.homework.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private List<AddressDto> addresses;
}

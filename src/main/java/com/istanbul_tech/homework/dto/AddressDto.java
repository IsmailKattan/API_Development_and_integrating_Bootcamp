package com.istanbul_tech.homework.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    private String id;
    private String city;
    private String district;
    private String street;
    private String no;
    private String zipCode;
    private String userId;
}

package com.istanbul_tech.homework.dto;

import com.istanbul_tech.homework.model.Address;
import lombok.*;

import java.util.Collections;
import java.util.List;

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

    public static List<AddressDto> convertToDto(List<Address> addresses) {
        if (addresses == null) {
            return Collections.emptyList();
        }
        // convert addresses to AddressDtos
        return addresses.stream().map(address -> AddressDto.builder()
                .id(address.getId())
                .city(address.getCity())
                .district(address.getDistrict())
                .street(address.getStreet())
                .no(address.getNo())
                .zipCode(address.getZipCode())
                .userId(address.getUserId())
                .build()).toList();
    }
}

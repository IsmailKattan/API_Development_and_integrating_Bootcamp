package com.istanbul_tech.homework.service;

import com.istanbul_tech.homework.dto.AddressDto;
import com.istanbul_tech.homework.dto.body.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    List<AddressDto> getAddressListOfUser(String id);

    ApiResponse createAddress(AddressDto addressDto);

    ApiResponse getAddressesOfUser(String username);

    ApiResponse getAddress(String id);

    ApiResponse updateAddress(String id, AddressDto addressDto);

    ApiResponse deleteAddress(String id);

    String getAddressOwnerId(String id);

    boolean isAddressOwner(String username, String id);
}

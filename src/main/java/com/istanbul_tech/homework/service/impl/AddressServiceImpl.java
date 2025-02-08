package com.istanbul_tech.homework.service.impl;

import com.istanbul_tech.homework.dto.AddressDto;
import com.istanbul_tech.homework.dto.body.ApiResponse;
import com.istanbul_tech.homework.exception.custom.NotFoundException;
import com.istanbul_tech.homework.model.Address;
import com.istanbul_tech.homework.repo.AddressRepository;
import com.istanbul_tech.homework.service.AddressService;
import com.istanbul_tech.homework.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("addressService")
public class AddressServiceImpl implements AddressService {


    private AddressRepository addressRepository;
    private SysUserService sysUserService;

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Autowired
    public void setSysUserService(@Lazy SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    public List<AddressDto> getAddressListOfUser(String id) {
        // get addresses of user with given id
        List<Address> addresses = addressRepository.findByUserId(id);
        // convert addresses to AddressDto
        return AddressDto.convertToDto(addresses);
    }

    @Override
    public ApiResponse createAddress(AddressDto addressDto) {
        // get user from security context
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // get user id from username
        String userId = sysUserService.getUserIdByUsername(username);
        // create address
        Address address = Address.builder()
                .userId(userId)
                .city(addressDto.getCity())
                .district(addressDto.getDistrict())
                .street(addressDto.getStreet())
                .no(addressDto.getNo())
                .zipCode(addressDto.getZipCode())
                .build();
        // save address
        addressRepository.save(address);
        // return response
        return new ApiResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                "Address created successfully",
                AddressDto.builder()
                        .id(address.getId())
                        .city(address.getCity())
                        .district(address.getDistrict())
                        .street(address.getStreet())
                        .no(address.getNo())
                        .zipCode(address.getZipCode())
                        .userId(address.getUserId())
                        .build()
        );
    }

    @Override
    public ApiResponse getAddressesOfUser(String username) {
        // get user addresses from address service
        List<AddressDto> addresses = getAddressListOfUser(sysUserService.getUserIdByUsername(username));
        return new ApiResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                "User retrieved successfully",
                addresses
        );
    }

    @Override
    public ApiResponse getAddress(String id) {
        // get address
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Address not found", Map.of("id", id), "AddressService", null, null)
        );
        // return response
        return new ApiResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                "Address retrieved successfully",
                AddressDto.builder()
                        .id(address.getId())
                        .city(address.getCity())
                        .district(address.getDistrict())
                        .street(address.getStreet())
                        .no(address.getNo())
                        .zipCode(address.getZipCode())
                        .userId(address.getUserId())
                        .build()
        );
    }

    @Override
    public ApiResponse updateAddress(String id, AddressDto addressDto) {
        // get address
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Address not found", Map.of("id", id), "AddressService", null, null)
        );
        // update address
        address.setCity(addressDto.getCity());
        address.setDistrict(addressDto.getDistrict());
        address.setStreet(addressDto.getStreet());
        address.setNo(addressDto.getNo());
        address.setZipCode(addressDto.getZipCode());
        // save address
        addressRepository.save(address);
        // return response
        return new ApiResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                "Address updated successfully",
                AddressDto.builder()
                        .id(address.getId())
                        .city(address.getCity())
                        .district(address.getDistrict())
                        .street(address.getStreet())
                        .no(address.getNo())
                        .zipCode(address.getZipCode())
                        .userId(address.getUserId())
                        .build()
        );
    }

    @Override
    public ApiResponse deleteAddress(String id) {
        // get address
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Address not found", Map.of("id", id), "AddressService", null, null)
        );
        // delete address
        addressRepository.delete(address);
        // return response
        return new ApiResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                "Address deleted successfully",
                null
        );
    }


    @Override
    public String getAddressOwnerId(String id) {
        // get address
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Address not found", Map.of("id", id), "AddressService", null, null)
        );
        return address.getUserId();
    }

    @Override
    public boolean isAddressOwner(String username, String id) {
        // get user id from username
        String userId = sysUserService.getUserIdByUsername(username);
        // get address
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Address not found", Map.of("id", id), "AddressService", null, null)
        );
        return address.getUserId().equals(userId);
    }

}

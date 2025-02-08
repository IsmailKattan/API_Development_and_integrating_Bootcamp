package com.istanbul_tech.homework.controller;

import com.istanbul_tech.homework.dto.AddressDto;
import com.istanbul_tech.homework.service.AddressService;
import com.istanbul_tech.homework.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;
    private final SysUserService sysUserService;

    @Autowired
    public AddressController(AddressService addressService, SysUserService sysUserService) {
        this.addressService = addressService;
        this.sysUserService = sysUserService;
    }

    // create address
    @PostMapping("/create")
    @Operation(
            summary = "Create address",
            description = "Create a new address")
    public ResponseEntity<?> createAddress(
            @io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody AddressDto addressDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.createAddress(addressDto));
    }

    // get addresses of user
    @GetMapping("/user/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    @Operation(
            summary = "Get addresses of user",
            description = "Get addresses of user with given username as path variable")
    public ResponseEntity<?> getAddressesOfUser(
            @PathVariable String username
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressesOfUser(username));
    }

    // get specific address
    // TODO: Add @PreAuthorize annotation to the getAddress method
    @GetMapping("/address/{id}")
    @PreAuthorize("@addressService.isAddressOwner(authentication.principal.username,#id)")
    @Operation(
            summary = "Get address",
            description = "Get address with given id as path variable")
    public ResponseEntity<?> getAddress(
            @PathVariable String id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddress(id));
    }

    // update address
    @PutMapping("/address/{id}")
    @PreAuthorize("@addressService.isAddressOwner(authentication.principal.username,#id)")
    @Operation(
            summary = "Update address",
            description = "Update address with given id as path variable")
    public ResponseEntity<?> updateAddress(
            @PathVariable String id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody AddressDto addressDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.updateAddress(id, addressDto));
    }

    // delete address
    @DeleteMapping("/address/{id}")
    @PreAuthorize("@addressService.isAddressOwner(authentication.principal.username,#id)")
    @Operation(
            summary = "Delete address",
            description = "Delete address with given id as path variable")
    public ResponseEntity<?> deleteAddress(
            @PathVariable String id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.deleteAddress(id));
    }

}

package com.dentalcura.webapp.controller;


import com.dentalcura.webapp.dto.address.CreateAddressRequest;
import com.dentalcura.webapp.dto.address.AddressResponse;
import com.dentalcura.webapp.dto.address.UpdateAddressRequest;
import com.dentalcura.webapp.service.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    IAddressService addressService;

    @GetMapping()
    public ResponseEntity<List<AddressResponse>> getAddressAll() {
        return new ResponseEntity<>(addressService.selectAllAddress(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable Long id) {
        AddressResponse addressResponse = addressService.selectAddressByID(id);

        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createAddress(@RequestBody CreateAddressRequest createAddressRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("address_created", "true");  // Adding a custom header
        String message = "Address created successfully!";

        addressService.insertAddress(createAddressRequest);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAddress(@PathVariable Long id, @RequestBody UpdateAddressRequest updateAddressRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("address_updated", "true");  // Adding a custom header
        String message = "Address updated successfully!";

        addressService.updateAddressByID(id, updateAddressRequest);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("address_deleted", "true");  // Adding a custom header
        String message = "Address deleted successfully!";

        addressService.deleteAddressByID(id);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

}

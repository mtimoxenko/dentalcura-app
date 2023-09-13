package com.dentalcura.webapp.dto.address;

public record CreateAddressRequest(
        String streetName,
        Integer streetNumber,
        Integer floor,
        String department
){ }

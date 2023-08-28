package com.dentalcura.webapp.dto.address;

public record UpdateAddressRequest(
        String streetName,
        Integer streetNumber,
        Integer floor,
        String department
){ }
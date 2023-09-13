package com.dentalcura.webapp.dto.address;

public record AddressResponse(
        String streetName,
        Integer streetNumber,
        Integer floor,
        String department
) { }

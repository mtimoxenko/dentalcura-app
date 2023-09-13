package com.dentalcura.bookingapp.dto.patient;

import com.dentalcura.bookingapp.model.Address;

public record UpdatePatientRequest(
        String name,
        String surname,
        String streetName,
        Integer streetNumber,
        Integer floor,
        String department
) { }
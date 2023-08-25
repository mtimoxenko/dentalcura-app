package com.dentalcura.bookingapp.dto.patient;

import com.dentalcura.bookingapp.model.Address;

public record UpdatePatientRequest(
        Long id,
        String name,
        String surname,
        Integer niNumber,
        String registrationDate,
        String streetName,
        Integer streetNumber,
        Integer floor,
        String department
) { }
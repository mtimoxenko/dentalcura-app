package com.dentalcura.bookingapp.dto.patient;

import com.dentalcura.bookingapp.model.Address;

public record PatientResponse(
        String name,
        String surname,
        Integer niNumber,
        String streetName,
        Integer streetNumber,
        Integer floor,
        String department
) { }

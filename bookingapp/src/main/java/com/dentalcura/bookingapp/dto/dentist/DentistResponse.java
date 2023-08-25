package com.dentalcura.bookingapp.dto.dentist;

public record DentistResponse(
        String name,
        String surname,
        Integer licenseNumber
) { }

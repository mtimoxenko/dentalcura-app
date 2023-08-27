package com.dentalcura.bookingapp.dto.dentist;

public record DentistResponse(
        Long id,
        String name,
        String surname,
        Integer licenseNumber
) { }

package com.dentalcura.bookingapp.dto.dentist;

public record CreateDentistRequest(
        String name,
        String surname,
        Integer licenseNumber
) { }

package com.dentalcura.webapp.dto.dentist;

public record CreateDentistRequest(
        String name,
        String surname,
        Integer licenseNumber
) { }

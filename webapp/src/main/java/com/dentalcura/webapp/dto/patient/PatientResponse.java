package com.dentalcura.webapp.dto.patient;

public record PatientResponse(
        Long id,
        String name,
        String surname,
        Integer niNumber,
        String streetName,
        Integer streetNumber,
        Integer floor,
        String department
) { }

package com.dentalcura.webapp.dto.patient;

public record UpdatePatientRequest(
        String name,
        String surname,
        String streetName,
        Integer streetNumber,
        Integer floor,
        String department
) { }
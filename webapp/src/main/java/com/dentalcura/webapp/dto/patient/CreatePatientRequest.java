package com.dentalcura.webapp.dto.patient;

public record CreatePatientRequest(
        String name,
        String surname,
        Integer niNumber,
        String registrationDate,
        String streetName,
        Integer streetNumber,
        Integer floor,
        String department
) { }

package com.dentalcura.webapp.dto.patient;

import com.dentalcura.webapp.model.Address;

public record CreatePatientRequest(
        String name,
        String surname,
        Integer niNumber,
        String registrationDate,
        Address address

) { }

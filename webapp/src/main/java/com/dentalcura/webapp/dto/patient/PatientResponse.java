package com.dentalcura.webapp.dto.patient;


public record PatientResponse(
        Long id,
        String name,
        String surname,
        Integer niNumber,
        com.dentalcura.webapp.dto.address.AddressResponse address

) { }

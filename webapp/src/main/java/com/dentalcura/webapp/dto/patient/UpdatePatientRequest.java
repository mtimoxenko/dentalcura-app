package com.dentalcura.webapp.dto.patient;

import com.dentalcura.webapp.model.Address;

public record UpdatePatientRequest(
        String name,
        String surname,
        Address address

) { }
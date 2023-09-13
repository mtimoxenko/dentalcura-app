package com.dentalcura.webapp.dto.dentist;

import java.util.List;

public record DentistResponse(
        Long id,
        String name,
        String surname,
        Integer licenseNumber,
        List<com.dentalcura.webapp.dto.appointment.AppointmentResponseToDentist> appointment
) { }

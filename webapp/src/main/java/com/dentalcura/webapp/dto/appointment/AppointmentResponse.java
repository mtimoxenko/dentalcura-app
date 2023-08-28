package com.dentalcura.webapp.dto.appointment;

public record AppointmentResponse(
        Long id,
        String date,
        String patientFullName,
        String dentistFullName
) { }

package com.dentalcura.webapp.dto.appointment;

public record CreateAppointmentRequest(
        String date,
        Long patientId,
        Long dentistId
) { }

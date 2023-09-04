package com.dentalcura.webapp.dto.appointment;

public record AppointmentResponseToDentist(
        Long id,
        String date,
        com.dentalcura.webapp.dto.patient.PatientResponseToDentist patient
) { }

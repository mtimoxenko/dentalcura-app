package com.dentalcura.webapp.dto.appointment;

import com.dentalcura.webapp.model.Patient;

public record AppointmentResponse(
        Long id,
        String date,
        String patient
) { }

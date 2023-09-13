package com.dentalcura.webapp.dto.appointment;

import com.dentalcura.webapp.model.Dentist;
import com.dentalcura.webapp.model.Patient;

public record CreateAppointmentRequest(
        String date,
        Patient patient,
        Dentist dentist
) { }

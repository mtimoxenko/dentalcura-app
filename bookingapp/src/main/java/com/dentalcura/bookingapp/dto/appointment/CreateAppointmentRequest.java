package com.dentalcura.bookingapp.dto.appointment;

import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.Patient;

public record CreateAppointmentRequest(
        Long id,
        String date,
        Long patientId,
        Long dentistId
) { }

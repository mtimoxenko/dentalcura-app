package com.dentalcura.bookingapp.dto.appointment;

import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.Patient;

public record CreateAppointmentRequest(
        String date,
        Long patientId,
        Long dentistId
) { }

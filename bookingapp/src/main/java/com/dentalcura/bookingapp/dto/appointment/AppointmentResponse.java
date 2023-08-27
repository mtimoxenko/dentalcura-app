package com.dentalcura.bookingapp.dto.appointment;

import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.Patient;

public record AppointmentResponse(
        Long id,
        String date,
        String patientFullName,
        String dentistFullName
) { }

package com.dentalcura.webapp.dto.dentist;

import com.dentalcura.webapp.dto.appointment.AppointmentResponse;
import com.dentalcura.webapp.model.Appointment;

import java.util.List;

public record DentistResponse(
        Long id,
        String name,
        String surname,
        Integer licenseNumber,
        List<AppointmentResponse> appointment
) { }

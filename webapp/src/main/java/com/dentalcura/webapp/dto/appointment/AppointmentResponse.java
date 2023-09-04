package com.dentalcura.webapp.dto.appointment;

import com.dentalcura.webapp.dto.dentist.DentistResponseToAppointment;

public record AppointmentResponse(
        Long id,
        String date,
        com.dentalcura.webapp.dto.patient.PatientResponseToAppointment patient,
        DentistResponseToAppointment dentist
) { }

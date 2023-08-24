package com.dentalcura.bookingapp.dto.appointment;

public record CreateAppointmentRequest(
        Long id,
        String name,
        String surname,
        Integer licenseNumber
) { }

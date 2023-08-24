package com.dentalcura.bookingapp.dto.appointment;

public record UpdateAppointmentRequest(
        String name,
        String surname
) { }
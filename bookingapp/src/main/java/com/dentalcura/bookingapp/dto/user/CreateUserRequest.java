package com.dentalcura.bookingapp.dto.patient;

public record CreatePatientRequest(Long id, String name, String surname, Integer licenseNumber) {
}

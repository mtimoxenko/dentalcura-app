package com.dentalcura.bookingapp.dto.dentist;

public record CreateDentistRequest(Long id, String name, String surname, Integer licenseNumber) {
}

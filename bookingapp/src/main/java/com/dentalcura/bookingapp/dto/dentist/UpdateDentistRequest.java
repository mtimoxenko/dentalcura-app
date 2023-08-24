package com.dentalcura.bookingapp.dto.dentist;

public record UpdateDentistRequest(Long id, String name, String surname, Integer licenseNumber) {

}
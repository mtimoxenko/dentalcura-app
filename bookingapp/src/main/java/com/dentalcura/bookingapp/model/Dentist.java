package com.dentalcura.bookingapp.model;

public record Dentist(Long id, String name, String surname, Integer licenseNumber) {

    @Override
    public String toString() {
        return "Dentist: " + name + " " + surname +
                ", License no.: " + licenseNumber;
    }
}

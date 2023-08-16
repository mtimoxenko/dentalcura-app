package com.dentalcura.bookingapp.model;

public record Dentist(Long id, String name, String surname, int licenseNumber) {

    @Override
    public String toString() {
        return name + " " + surname +
                ", License no.: " + licenseNumber;
    }
}

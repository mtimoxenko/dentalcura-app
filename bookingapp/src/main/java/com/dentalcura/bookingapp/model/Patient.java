package com.dentalcura.bookingapp.model;
public record Patient(
        Long id,
        String name,
        String surname,
        Integer niNumber,
        String registrationDate,
        Address address
) {

    @Override
    public String toString() {
        return "Patient: " + name + " " + surname +
                ", ni_number: " + niNumber;
    }
}

package com.dentalcura.bookingapp.model;


public record Patient(Long id, String name, String surname, Address address, int niNumber, String registrationDate) {

    @Override
    public String toString() {
        return "Patient: " + name + " " + surname +
                ", ni_number: " + niNumber;
    }
}

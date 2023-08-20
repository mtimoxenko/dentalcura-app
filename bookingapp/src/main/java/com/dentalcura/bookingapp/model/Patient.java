package com.dentalcura.bookingapp.model;


public record Patient(Long id, String name, String surname, int niNumber, String registrationDate, Address address) {

    @Override
    public String toString() {
        return name + " " + surname +
                ", ni_number: " + niNumber;
    }
}

package com.dentalcura.bookingapp.model;


public record Patient(Long id, String name, String surname, String address, int niNumber, String registrationDate) {

    @Override
    public String toString() {
        return name + " " + surname +
                ", ni_number: " + niNumber;
    }
}

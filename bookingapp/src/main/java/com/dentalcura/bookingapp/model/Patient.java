package com.dentalcura.bookingapp.model;
<<<<<<< HEAD


<<<<<<< HEAD
public record Patient(int id, String name, String surname, int niNumber, String registrationDate, Address address) {
=======
public record Patient(Long id, String name, String surname, Integer niNumber, String registrationDate, Address address) {
>>>>>>> 4588f004ee96bc8a2c6acaab2caed74899fa30a0
=======
public record Patient(
        Long id,
        String name,
        String surname,
        Integer niNumber,
        String registrationDate,
        Address address
) {
>>>>>>> 74ad6c0d65a3d7f0c6bbdcd1cbdf13f05bded0e5

    @Override
    public String toString() {
        return name + " " + surname +
                ", ni_number: " + niNumber;
    }
}

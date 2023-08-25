package com.dentalcura.bookingapp.model;

<<<<<<< HEAD
<<<<<<< HEAD
public record Dentist(int id, String name, String surname, int licenseNumber) {
=======
public record Dentist(Long id, String name, String surname, Integer licenseNumber) {
>>>>>>> 4588f004ee96bc8a2c6acaab2caed74899fa30a0

=======
public record Dentist(
        Long id,
        String name,
        String surname,
        Integer licenseNumber
) {
>>>>>>> 74ad6c0d65a3d7f0c6bbdcd1cbdf13f05bded0e5
    @Override
    public String toString() {
        return name + " " + surname +
                ", License no.: " + licenseNumber;
    }
}

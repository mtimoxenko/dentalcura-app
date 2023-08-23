package com.dentalcura.bookingapp.model;

<<<<<<< HEAD
public record Dentist(int id, String name, String surname, int licenseNumber) {
=======
public record Dentist(Long id, String name, String surname, Integer licenseNumber) {
>>>>>>> 4588f004ee96bc8a2c6acaab2caed74899fa30a0

    @Override
    public String toString() {
        return name + " " + surname +
                ", License no.: " + licenseNumber;
    }
}

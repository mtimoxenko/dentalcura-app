package com.dentalcura.bookingapp.model;


<<<<<<< HEAD
public record Address (int id, String streetName, int streetNumber, int floor, String department){
=======
public record Address (Long id, String streetName, Integer streetNumber, Integer floor, String department){
>>>>>>> 4588f004ee96bc8a2c6acaab2caed74899fa30a0

    @Override
    public String toString() {
        return "Address: " + streetName +
                " " + streetNumber + ", " + floor +
                "-" + department;
    }

}

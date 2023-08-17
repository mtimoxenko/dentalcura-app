package com.dentalcura.bookingapp.model;


public record Address (Long id, String streetName, int streetNumber, int floor, String department){

    @Override
    public String toString() {
        return "Address: " + streetName +
                ", " + streetNumber + " " + floor +
                " - " + department;
    }

}

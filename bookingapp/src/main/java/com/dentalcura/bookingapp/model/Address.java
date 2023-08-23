package com.dentalcura.bookingapp.model;


public record Address (Long id, String streetName, Integer streetNumber, Integer floor, String department){

    @Override
    public String toString() {
        return "Address: " + streetName +
                " " + streetNumber + ", " + floor +
                "-" + department;
    }

}

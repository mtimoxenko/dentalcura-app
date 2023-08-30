package com.dentalcura.bookingapp.model;


public record User(
        Long id,
        String name,
        String surname,
        String email,
        String password,
        Boolean admin
) {
    @Override
    public String toString() {
        return "User: " + name + " " + surname +
                ", email: " + email +
                ", admin: " + admin;
    }
}

package com.dentalcura.bookingapp.model;


<<<<<<< HEAD
public record User(int id, String name, String surname, String email, String password, Boolean admin) {

=======
public record User(
        Long id,
        String name,
        String surname,
        String email,
        String password,
        Boolean admin
) {
>>>>>>> 74ad6c0d65a3d7f0c6bbdcd1cbdf13f05bded0e5
    @Override
    public String toString() {
        return "User: " + name + " " + surname +
                ", email: " + email +
                ", admin: " + admin;
    }
}

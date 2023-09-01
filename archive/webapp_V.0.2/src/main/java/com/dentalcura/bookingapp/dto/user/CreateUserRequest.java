package com.dentalcura.bookingapp.dto.user;

public record CreateUserRequest(
        String name,
        String surname,
        String email,
        String password,
        Boolean admin
) { }

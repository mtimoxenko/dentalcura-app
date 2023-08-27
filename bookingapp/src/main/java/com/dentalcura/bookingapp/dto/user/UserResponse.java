package com.dentalcura.bookingapp.dto.user;

public record UserResponse(
        String name,
        String surname,
        String email,
        Boolean admin
) { }

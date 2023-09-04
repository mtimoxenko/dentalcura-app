package com.dentalcura.bookingapp.dto.user;

public record UserResponse(
        Long id,
        String name,
        String surname,
        String email,
        Boolean admin
) { }

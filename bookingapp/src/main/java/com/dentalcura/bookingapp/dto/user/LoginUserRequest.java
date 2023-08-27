package com.dentalcura.bookingapp.dto.user;

public record LoginUserRequest(
        String email,
        String password
) { }

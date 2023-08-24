package com.dentalcura.bookingapp.dto.user;

public record CreateUserRequest(Long id, String name, String surname, String email, String password, Boolean admin) {
}

package com.dentalcura.bookingapp.dto.user;

public record UpdateUserRequest(Long id, String name, String surname, String email, String password, Boolean admin) {

}
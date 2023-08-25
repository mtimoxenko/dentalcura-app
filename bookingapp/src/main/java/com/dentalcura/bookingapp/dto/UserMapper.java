package com.dentalcura.bookingapp.dto;

import com.dentalcura.bookingapp.dto.user.CreateUserRequest;
import com.dentalcura.bookingapp.dto.user.LoginUserRequest;
import com.dentalcura.bookingapp.dto.user.UserResponse;
import com.dentalcura.bookingapp.dto.user.UpdateUserRequest;
import com.dentalcura.bookingapp.model.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    // Response DTO for @GetMapping (Retrieving a User)
    public static UserResponse userToDtoResponse(User user) {
        return new UserResponse(
                user.id(),
                user.name(),
                user.surname(),
                user.email(),
                user.admin()
        );
    }
    // Response DTO for @GetMapping (Retrieving a List<User>)
    public static List<UserResponse> usersToDtoResponse(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> userResponses.add(userToDtoResponse(user)));

        return userResponses;
    }

    // Request DTO for @PostMapping (Creating a User)
    public static User dtoPostRequestToUser(CreateUserRequest createUserRequest) {
        return new User(
                createUserRequest.id(),
                createUserRequest.name(),
                createUserRequest.surname(),
                createUserRequest.email(),
                createUserRequest.password(),
                createUserRequest.admin()
        );
    }

    // Request DTO for @PostMapping (Login a User)
    public static User dtoLoginRequestToUser(LoginUserRequest loginUserRequest) {
        return new User(
                null,
                null,
                null,
                loginUserRequest.email(),
                loginUserRequest.password(),
                null
        );
    }

    // Request DTO for @PutMapping (Updating a User)
    public static User dtoPutRequestToUser(Long id, UpdateUserRequest updateUserRequest) {
        return new User(
                id,
                updateUserRequest.name(),
                updateUserRequest.surname(),
                updateUserRequest.email(),
                updateUserRequest.password(),
                updateUserRequest.admin()
        );
    }

}


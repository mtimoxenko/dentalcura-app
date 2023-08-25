package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.dao.impl.DentistDAOH2;
import com.dentalcura.bookingapp.dao.impl.UserDAOH2;
import com.dentalcura.bookingapp.dto.DentistMapper;
import com.dentalcura.bookingapp.dto.UserMapper;
import com.dentalcura.bookingapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.bookingapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.bookingapp.dto.user.*;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.User;
import com.dentalcura.bookingapp.service.DentistService;
import com.dentalcura.bookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import static com.dentalcura.bookingapp.BookingAppApplication.userListAll;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public ResponseEntity<List<UserResponse>> getUserAll() {
//        userService.setUserIDao(new UserDAOH2());
        List<UserResponse> userResponses = UserMapper.usersToDtoResponse(userService.selectAllUser());

        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
//        userService.setUserIDao(new UserDAOH2());
        UserResponse userResponse = UserMapper.userToDtoResponse(userService.selectUserByID(id));

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest) {
//        userService.setUserIDao(new UserDAOH2());
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("customHeaderName", "customHeaderValue");  // Adding a custom header
        String message = "User created successfully!";

        userService.insertUser(UserMapper.dtoPostRequestToUser(createUserRequest));
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }



    @PostMapping("/login")
    public ResponseEntity<Integer> loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        List<User> userList = userService.selectAllUser();
        int login = 0;
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("customHeaderName", "customHeaderValue");  // Adding a custom header

        for (User user: userList) {
            if(user.email().equals(loginUserRequest.email()) && user.password().equals(loginUserRequest.password())){
                login = user.admin() ? 33 : 1;
            }
        }

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(login);
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
//        userService.setUserIDao(new UserDAOH2());
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("customHeaderName", "customHeaderValue");  // Adding a custom header
        String message = "User updated successfully!";

        userService.updateUserByID(UserMapper.dtoPutRequestToUser(updateUserRequest));
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
//        return userService.updateUserByID(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
//        userService.setUserIDao(new UserDAOH2());
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("customHeaderName", "customHeaderValue");  // Adding a custom header
        String message = "User deleted successfully!";

        userService.deleteUserByID(id);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

}

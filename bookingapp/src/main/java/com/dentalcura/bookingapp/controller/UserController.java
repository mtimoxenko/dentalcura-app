package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.dao.impl.DentistDAOH2;
import com.dentalcura.bookingapp.dao.impl.UserDAOH2;
import com.dentalcura.bookingapp.dto.DentistMapper;
import com.dentalcura.bookingapp.dto.UserMapper;
import com.dentalcura.bookingapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.bookingapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.bookingapp.dto.user.CreateUserRequest;
import com.dentalcura.bookingapp.dto.user.LoginUserRequest;
import com.dentalcura.bookingapp.dto.user.UpdateUserRequest;
import com.dentalcura.bookingapp.dto.user.UserResponse;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.User;
import com.dentalcura.bookingapp.service.DentistService;
import com.dentalcura.bookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public List<UserResponse> getUserAll() {
        userService.setUserIDao(new UserDAOH2());
        return UserMapper.usersToDtoResponse(userService.selectAllUser());
//        return userService.selectAllUser();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        userService.setUserIDao(new UserDAOH2());
        return UserMapper.userToDtoResponse(userService.selectUserByID(id));
//        return userService.selectUserByID(id);
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.setUserIDao(new UserDAOH2());
        return userService.insertUser(UserMapper.dtoPostRequestToUser(createUserRequest));
//        return userService.insertUser(user);
    }


    // QUE HAGO CON ESTE USER?
//    @PostMapping("/login")
//    public User loginUser(@RequestBody LoginUserRequest loginUserRequest) {
//        userService.setUserIDao(new UserDAOH2());
//        return userService.insertUser(UserMapper.dtoPostRequestToUser(createUserRequest));
//    }




    @PutMapping("/{id}")
    public User updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        userService.setUserIDao(new UserDAOH2());
        return userService.updateUserByID(UserMapper.dtoPutRequestToUser(updateUserRequest));
//        return userService.updateUserByID(user);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Long id) {
        userService.setUserIDao(new UserDAOH2());
        return userService.deleteUserByID(id);
    }

}

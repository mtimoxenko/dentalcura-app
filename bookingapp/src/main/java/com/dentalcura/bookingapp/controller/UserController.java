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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dentalcura.bookingapp.BookingAppApplication.userListAll;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public ResponseEntity<List<UserResponse>> getUserAll() {
        userService.setUserIDao(new UserDAOH2());
        return new ResponseEntity<>(
                UserMapper.usersToDtoResponse(userService.selectAllUser()),
                HttpStatus.OK
        );
//        return UserMapper.usersToDtoResponse(userService.selectAllUser());
//        return userService.selectAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        userService.setUserIDao(new UserDAOH2());
        return new ResponseEntity<>(
                UserMapper.userToDtoResponse(userService.selectUserByID(id)),
                HttpStatus.OK
        );
//        return UserMapper.userToDtoResponse(userService.selectUserByID(id));
//        return userService.selectUserByID(id);
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.setUserIDao(new UserDAOH2());
        return userService.insertUser(UserMapper.dtoPostRequestToUser(createUserRequest));
//        return userService.insertUser(user);
    }


    // QUE HAGO CON ESTE USER?
    @PostMapping("/login")
    public ResponseEntity<Integer> loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        List<User> userList = userListAll();
        int login = 0;
        HttpStatus httpStatus = HttpStatus.NO_CONTENT;
        for (User user: userList) {
            if(user.email().equals(loginUserRequest.email()) && user.password().equals(loginUserRequest.password())){
                login = user.admin() ? 33 : 1;
                httpStatus=HttpStatus.OK;
            }
        }
        return new ResponseEntity <>(login, httpStatus);
    }




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

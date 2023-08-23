package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.dao.impl.DentistDAOH2;
import com.dentalcura.bookingapp.dao.impl.UserDAOH2;
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
    public List<User> getUserAll() {
        userService.setUserIDao(new UserDAOH2());
        return userService.selectAllUser();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        userService.setUserIDao(new UserDAOH2());
        return userService.selectUserByID(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        userService.setUserIDao(new UserDAOH2());
        return userService.insertUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user) {
        userService.setUserIDao(new UserDAOH2());
        return userService.updateUserByID(user);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable int id) {
        userService.setUserIDao(new UserDAOH2());
        return userService.deleteUserByID(id);
    }

}

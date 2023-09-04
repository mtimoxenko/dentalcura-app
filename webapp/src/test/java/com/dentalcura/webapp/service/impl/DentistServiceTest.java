package com.dentalcura.webapp.service.impl;

import com.dentalcura.webapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.webapp.dto.user.CreateUserRequest;
import com.dentalcura.webapp.dto.user.UpdateUserRequest;
import com.dentalcura.webapp.model.Dentist;
import com.dentalcura.webapp.model.User;
import com.dentalcura.webapp.repository.IDentistRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistServiceTest {

    @Autowired
    IDentistRepository dentistRepository;

    @Autowired
    ObjectMapper mapper;

    DentistServiceTest() {
    }

    @Test
    void insertDentist() {

        CreateDentistRequest createDentistRequest = new CreateDentistRequest(
                "testName",
                "testSurname",
                1111
        );

        Dentist dentist = mapper.convertValue(createDentistRequest, Dentist.class);

        dentistRepository.save(dentist);

        List<Dentist> dentists = dentistRepository.findAll();

        boolean found = false;

        for (Dentist findDentist: dentists) {
            if (dentist.getLicenseNumber().equals(findDentist.getLicenseNumber())) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

//    @Test
//    void updateDentistByID() {
//
//        CreateUserRequest createUserRequest = new CreateUserRequest(
//                "testName",
//                "testSurname",
//                "test@email.com",
//                "testPassword",
//                true
//        );
//
//        User user = mapper.convertValue(createUserRequest, User.class);
//        userRepository.save(user);
//
//        UpdateUserRequest updateUserRequest = new UpdateUserRequest(
//                "testNameUpdate",
//                "testSurnameUpdate",
//                "testUpdate@email.com",
//                "testPasswordUpdate",
//                false
//        );
//
//        User userUpdate = mapper.convertValue(updateUserRequest, User.class);
//        userUpdate.setId(1L);
//        userRepository.save(userUpdate);
//
//        Optional<User> optionalUser = userRepository.findById(1L);
//        User newUser = null;
//
//        if(optionalUser.isPresent())
//            newUser = optionalUser.get();
//
//
//        assertEquals(userUpdate, newUser);
//    }

    @Test
    void deleteDentistByID() {
    }
}
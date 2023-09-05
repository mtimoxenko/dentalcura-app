package com.dentalcura.webapp.service.impl;

import com.dentalcura.webapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.webapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.webapp.model.Dentist;
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

    @Test
    void updateDentistByID() {

        CreateDentistRequest createDentistRequest = new CreateDentistRequest(
                "testName",
                "testSurname",
                1111
        );

        Dentist dentist = mapper.convertValue(createDentistRequest, Dentist.class);
        dentistRepository.save(dentist);

        UpdateDentistRequest updateDentistRequest = new UpdateDentistRequest(
                "testNameUpdate",
                "testSurnameUpdate"
        );

        Dentist dentistUpdate = mapper.convertValue(updateDentistRequest, Dentist.class);
        dentistUpdate.setId(1L);
        dentistRepository.save(dentistUpdate);

        Optional<Dentist> optionalDentist = dentistRepository.findById(1L);
        Dentist newDentist = null;

        if(optionalDentist.isPresent())
            newDentist = optionalDentist.get();


        assertEquals(dentistUpdate.getLicenseNumber(), newDentist.getLicenseNumber());
    }

    @Test
    void deleteDentistByID() {

        CreateDentistRequest createDentistRequest = new CreateDentistRequest(
                "testName",
                "testSurname",
                2222
        );

        Dentist dentistToDelete = mapper.convertValue(createDentistRequest, Dentist.class);
        dentistRepository.save(dentistToDelete);

        dentistRepository.deleteById(4L);

        List<Dentist> dentists = dentistRepository.findAll();

        boolean found = false;

        for (Dentist findDentist: dentists) {
            if (findDentist.equals(dentistToDelete)) {
                found = true;
                break;
            }
        }

        assertFalse(found);
        }
}
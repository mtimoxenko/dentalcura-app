package com.dentalcura.webapp.service.impl;

import com.dentalcura.webapp.dto.patient.CreatePatientRequest;
import com.dentalcura.webapp.dto.patient.CreatePatientRequest;
import com.dentalcura.webapp.dto.patient.UpdatePatientRequest;
import com.dentalcura.webapp.dto.patient.CreatePatientRequest;
import com.dentalcura.webapp.model.Patient;
import com.dentalcura.webapp.model.Patient;
import com.dentalcura.webapp.model.Patient;
import com.dentalcura.webapp.repository.IPatientRepository;
import com.dentalcura.webapp.repository.IPatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PatientServiceTest {
    
    PatientServiceTest(){
    }

    @Autowired
    IPatientRepository patientRepository;

    @Autowired
    ObjectMapper mapper;

    @Test
    void insertPatient() {

        CreatePatientRequest createPatientRequest = new CreatePatientRequest(
                "testName",
                "testSurname",
                1111,
                "00/11/22",
                null
        );

        Patient patient = mapper.convertValue(createPatientRequest, Patient.class);

        patientRepository.save(patient);

        List<Patient> patients = patientRepository.findAll();

        boolean found = false;

        for (Patient findPatient: patients) {
            if (patient.getNiNumber().equals(findPatient.getNiNumber())) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    void updatePatientByID() {
        
        CreatePatientRequest createPatientRequest = new CreatePatientRequest(
                "testName",
                "testSurname",
                1111,
                "00/11/22",
                null
        );

        Patient patient = mapper.convertValue(createPatientRequest, Patient.class);
        patientRepository.save(patient);

        UpdatePatientRequest updatePatientRequest = new UpdatePatientRequest(
                "testNameUpdate",
                "testSurnameUpdate",
                null
        );

        Patient patientUpdate = mapper.convertValue(updatePatientRequest, Patient.class);
        patientUpdate.setId(1L);
        patientRepository.save(patientUpdate);

        Optional<Patient> optionalPatient = patientRepository.findById(1L);
        Patient newPatient = null;

        if(optionalPatient.isPresent())
            newPatient = optionalPatient.get();


        assert newPatient != null;
        assertEquals(patientUpdate.getName(), newPatient.getName());
    }

    @Test
    void deletePatientByID() {

        CreatePatientRequest createPatientRequest = new CreatePatientRequest(
                "testName",
                "testSurname",
                1111,
                "00/11/22",
                null
        );

        Patient patientToDelete = mapper.convertValue(createPatientRequest, Patient.class);
        patientRepository.save(patientToDelete);

        patientRepository.deleteById(4L);

        List<Patient> patients = patientRepository.findAll();

        boolean found = false;

        for (Patient findPatient: patients) {
            if (findPatient.equals(patientToDelete)) {
                found = true;
                break;
            }
        }

        assertFalse(found);
    }
}
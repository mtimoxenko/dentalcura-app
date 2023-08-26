package com.dentalcura.bookingapp.dto;

import com.dentalcura.bookingapp.dto.patient.CreatePatientRequest;
import com.dentalcura.bookingapp.dto.patient.UpdatePatientRequest;
import com.dentalcura.bookingapp.dto.patient.PatientResponse;
import com.dentalcura.bookingapp.model.Address;
import com.dentalcura.bookingapp.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientMapper {

    // Response DTO for @GetMapping (Retrieving a Patient)
    public static PatientResponse patientToDtoResponse(Patient patient) {
        return new PatientResponse(
                patient.id(),
                patient.name(),
                patient.surname(),
                patient.niNumber(),
                patient.address().streetName(),
                patient.address().streetNumber(),
                patient.address().floor(),
                patient.address().department()
        );
    }
    // Response DTO for @GetMapping (Retrieving a List<Patient>)
    public static List<PatientResponse> patientsToDtoResponse(List<Patient> patients) {
        List<PatientResponse> patientResponses = new ArrayList<>();
        patients.forEach(patient -> patientResponses.add(patientToDtoResponse(patient)));

        return patientResponses;
    }

    // Request DTO for @PostMapping (Creating a Patient)
    public static Patient dtoPostRequestToPatient(CreatePatientRequest createPatientRequest) {
        return new Patient(
                null,
                createPatientRequest.name(),
                createPatientRequest.surname(),
                createPatientRequest.niNumber(),
                createPatientRequest.registrationDate(),
                new Address(
                        null,
                        createPatientRequest.streetName(),
                        createPatientRequest.streetNumber(),
                        createPatientRequest.floor(),
                        createPatientRequest.department()
                )
        );
    }

    // Request DTO for @PutMapping (Updating a Patient)
    public static Patient dtoPutRequestToPatient(Long id, UpdatePatientRequest updatePatientRequest) {
        return new Patient(
                id,
                updatePatientRequest.name(),
                updatePatientRequest.surname(),
                null,
                null,
                new Address(
                        id,
                        updatePatientRequest.streetName(),
                        updatePatientRequest.streetNumber(),
                        updatePatientRequest.floor(),
                        updatePatientRequest.department()
                )
        );
    }

}


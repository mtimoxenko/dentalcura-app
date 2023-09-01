package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.dao.impl.PatientDAOH2;
import com.dentalcura.bookingapp.dto.DentistMapper;
import com.dentalcura.bookingapp.dto.PatientMapper;
import com.dentalcura.bookingapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.bookingapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.bookingapp.dto.patient.CreatePatientRequest;
import com.dentalcura.bookingapp.dto.patient.PatientResponse;
import com.dentalcura.bookingapp.dto.patient.UpdatePatientRequest;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping()
    public ResponseEntity<List<PatientResponse>> getPatientAll() {
        patientService.setPatientIDao(new PatientDAOH2());

        return new ResponseEntity<>(
                PatientMapper.patientsToDtoResponse(patientService.selectAllPatient()),
                HttpStatus.OK
        );


        List<PatientResponse> patientResponses = PatientMapper.patientsToDtoResponse(patientService.selectAllPatient());

        return new ResponseEntity<>(patientResponses, HttpStatus.OK);

//        return PatientMapper.patientsToDtoResponse(patientService.selectAllPatient());
//        return patientService.selectAllPatient();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatient(@PathVariable Long id) {
        patientService.setPatientIDao(new PatientDAOH2());

        return new ResponseEntity<>(
                PatientMapper.patientToDtoResponse(patientService.selectPatientByID(id)),
                HttpStatus.OK
        );


        PatientResponse patientResponse = PatientMapper.patientToDtoResponse(patientService.selectPatientByID(id));

        return new ResponseEntity<>(patientResponse, HttpStatus.OK);

//        return PatientMapper.patientToDtoResponse(patientService.selectPatientByID(id));
//        return patientService.selectPatientByID(id);
    }

    @PostMapping
    public ResponseEntity<String> createPatient(@RequestBody CreatePatientRequest createPatientRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("customHeaderName", "customHeaderValue");  // Adding a custom header
        String message = "Patient created successfully!";

        patientService.setPatientIDao(new PatientDAOH2());
        patientService.insertPatient(PatientMapper.dtoPostRequestToPatient(createPatientRequest));
//        return patientService.insertPatient(patient);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody UpdatePatientRequest updatePatientRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("customHeaderName", "customHeaderValue");  // Adding a custom header
        String message = "Patient updated successfully!";

        patientService.setPatientIDao(new PatientDAOH2());
        patientService.updatePatientByID(PatientMapper.dtoPutRequestToPatient(id, updatePatientRequest));
//        return patientService.updatePatientByID(patient);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("customHeaderName", "customHeaderValue");  // Adding a custom header
        String message = "Patient deleted successfully!";

        patientService.setPatientIDao(new PatientDAOH2());
        patientService.deletePatientByID(id);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

}

package com.dentalcura.webapp.controller;


import com.dentalcura.webapp.dto.patient.CreatePatientRequest;
import com.dentalcura.webapp.dto.patient.PatientResponse;
import com.dentalcura.webapp.dto.patient.UpdatePatientRequest;
import com.dentalcura.webapp.service.IPatientService;
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
    IPatientService patientService;

    @GetMapping()
    public ResponseEntity<List<PatientResponse>> getPatientAll() {
        return new ResponseEntity<>(patientService.selectAllPatient(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatient(@PathVariable Long id) {
        PatientResponse patientResponse = patientService.selectPatientByID(id);

        return new ResponseEntity<>(patientResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createPatient(@RequestBody CreatePatientRequest createPatientRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("patient_created", "true");  // Adding a custom header
        String message = "Patient created successfully!";

        patientService.insertPatient(createPatientRequest);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody UpdatePatientRequest updatePatientRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("patient_updated", "true");  // Adding a custom header
        String message = "Patient updated successfully!";

        patientService.updatePatientByID(id, updatePatientRequest);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("patient_deleted", "true");  // Adding a custom header
        String message = "Patient deleted successfully!";

        patientService.deletePatientByID(id);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

}

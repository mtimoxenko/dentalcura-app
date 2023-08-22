package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.model.Patient;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/patient")
public class PatientController {



    @GetMapping()
    public String getPatientAll() {
        return "get all patients";
    }

    @GetMapping("/{id}")
    public String getPatient(@PathVariable Long id) {
        return "get patient id: " + id;
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patient;
    }

    @PutMapping("/{id}")
    public String updatePatient(@PathVariable Long id) {
        return "update patient: " + id;
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id) {
        return "delete patient: " + id;
    }

}

package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.dao.impl.PatientDAOH2;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping()
    public List<Patient> getPatientAll() {
        patientService.setPatientIDao(new PatientDAOH2());
        return patientService.selectAllPatient();
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable int id) {
        patientService.setPatientIDao(new PatientDAOH2());
        return patientService.selectPatientByID(id);
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        patientService.setPatientIDao(new PatientDAOH2());
        return patientService.insertPatient(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@RequestBody Patient patient) {
        patientService.setPatientIDao(new PatientDAOH2());
        return patientService.updatePatientByID(patient);
    }

    @DeleteMapping("/{id}")
    public Patient deletePatient(@PathVariable int id) {
        patientService.setPatientIDao(new PatientDAOH2());
        return patientService.deletePatientByID(id);
    }

}

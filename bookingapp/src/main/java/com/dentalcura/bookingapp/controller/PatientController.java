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
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping()
    public List<PatientResponse> getPatientAll() {
        patientService.setPatientIDao(new PatientDAOH2());
        return PatientMapper.patientsToDtoResponse(patientService.selectAllPatient());
//        return patientService.selectAllPatient();
    }

    @GetMapping("/{id}")
<<<<<<< HEAD
    public Patient getPatient(@PathVariable int id) {
=======
    public PatientResponse getPatient(@PathVariable Long id) {
>>>>>>> 74ad6c0d65a3d7f0c6bbdcd1cbdf13f05bded0e5
        patientService.setPatientIDao(new PatientDAOH2());
        return PatientMapper.patientToDtoResponse(patientService.selectPatientByID(id));
//        return patientService.selectPatientByID(id);
    }

    @PostMapping
    public Patient createPatient(@RequestBody CreatePatientRequest createPatientRequest) {
        patientService.setPatientIDao(new PatientDAOH2());
        return patientService.insertPatient(PatientMapper.dtoPostRequestToPatient(createPatientRequest));
//        return patientService.insertPatient(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@RequestBody UpdatePatientRequest updatePatientRequest) {
        patientService.setPatientIDao(new PatientDAOH2());
        return patientService.updatePatientByID(PatientMapper.dtoPutRequestToPatient(updatePatientRequest));
//        return patientService.updatePatientByID(patient);
    }

    @DeleteMapping("/{id}")
    public Patient deletePatient(@PathVariable int id) {
        patientService.setPatientIDao(new PatientDAOH2());
        return patientService.deletePatientByID(id);
    }

}

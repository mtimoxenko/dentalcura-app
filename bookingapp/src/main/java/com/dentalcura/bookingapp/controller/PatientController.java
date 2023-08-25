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
//        return PatientMapper.patientToDtoResponse(patientService.selectPatientByID(id));
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
    public Patient deletePatient(@PathVariable Long id) {
        patientService.setPatientIDao(new PatientDAOH2());
        return patientService.deletePatientByID(id);
    }

}

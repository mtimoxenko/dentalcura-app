package com.dentalcura.webapp.service;

import com.dentalcura.webapp.dto.patient.CreatePatientRequest;
import com.dentalcura.webapp.dto.patient.UpdatePatientRequest;
import com.dentalcura.webapp.dto.patient.PatientResponse;

import java.util.List;


public interface IPatientService {

    void insertPatient(CreatePatientRequest createPatientRequest);
    List<PatientResponse> selectAllPatient();
    PatientResponse selectPatientByID(Long id);
    void updatePatientByID(UpdatePatientRequest updatePatientRequest);
    void deletePatientByID(Long id);

}

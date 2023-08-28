package com.dentalcura.webapp.service.impl;


import com.dentalcura.webapp.dto.patient.CreatePatientRequest;
import com.dentalcura.webapp.dto.patient.UpdatePatientRequest;
import com.dentalcura.webapp.dto.patient.PatientResponse;
import com.dentalcura.webapp.model.Patient;
import com.dentalcura.webapp.repository.IPatientRepository;
import com.dentalcura.webapp.service.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter @Setter
@Service
public class PatientService implements IPatientService {

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void insertPatient(CreatePatientRequest createPatientRequest) {
        Patient patient = mapper.convertValue(createPatientRequest, Patient.class);
        patientRepository.save(patient);
    }

    @Override
    public List<PatientResponse> selectAllPatient() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponse> patientResponses = new ArrayList<>();

        for(Patient patient: patients){
            patientResponses.add(mapper.convertValue(patient, PatientResponse.class));
        }
         
        return patientResponses;
    }

    @Override
    public PatientResponse selectPatientByID(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        PatientResponse patientResponse = null;

        if(patient.isPresent())
            patientResponse = mapper.convertValue(patient, PatientResponse.class);

        return patientResponse;
    }

    @Override
    public void updatePatientByID(Long id, UpdatePatientRequest updatePatientRequest) {
        Patient patient = mapper.convertValue(updatePatientRequest, Patient.class);
        patient.setId(id);
        patientRepository.save(patient);
    }

    @Override
    public void deletePatientByID(Long id) {
        patientRepository.deleteById(id);
    }
}

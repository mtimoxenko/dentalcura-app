package com.dentalcura.webapp.service.impl;

import com.dentalcura.webapp.dto.address.AddressResponse;
import com.dentalcura.webapp.dto.patient.CreatePatientRequest;
import com.dentalcura.webapp.dto.patient.UpdatePatientRequest;
import com.dentalcura.webapp.dto.patient.PatientResponse;
import com.dentalcura.webapp.model.Address;
import com.dentalcura.webapp.model.Patient;
import com.dentalcura.webapp.model.User;
import com.dentalcura.webapp.repository.IPatientRepository;
import com.dentalcura.webapp.service.IPatientService;
import com.dentalcura.webapp.utils.exceptions.CustomNotFoundException;
import com.dentalcura.webapp.utils.exceptions.DuplicateEmailException;
import com.dentalcura.webapp.utils.exceptions.DuplicateNiNumberException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Getter @Setter
@Service
public class PatientService implements IPatientService {

    private final static Logger LOGGER = Logger.getLogger(PatientService.class);

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void insertPatient(CreatePatientRequest createPatientRequest) {
        if (isNiNumberDuplicated(createPatientRequest.niNumber())) {
            throw new DuplicateNiNumberException("NI Number [" + createPatientRequest.niNumber() + "] is already in use.");
        }

        Patient patient = mapper.convertValue(createPatientRequest, Patient.class);
        Address address = patient.getAddress();

        address.setPatient(patient);
        patient.setAddress(address);

        patientRepository.save(patient);
        LOGGER.info("New patient was registered [" + patient.getName() + " " +patient.getSurname() + "]");
    }

    @Override
    public List<PatientResponse> selectAllPatient() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponse> patientResponses = new ArrayList<>();

        for(Patient patient: patients){
            patientResponses.add(
                    new PatientResponse(
                            patient.getId(),
                            patient.getName(),
                            patient.getSurname(),
                            patient.getNiNumber(),
                            new AddressResponse(
                                    patient.getAddress().getStreetName(),
                                    patient.getAddress().getStreetNumber(),
                                    patient.getAddress().getFloor(),
                                    patient.getAddress().getDepartment()
                            )
                    )
            );
        }
         
        return patientResponses;
    }

    @Override
    public PatientResponse selectPatientByID(Long id) {
        if (!patientRepository.existsById(id))
            throw new CustomNotFoundException("Patient id [" + id + "] not found");

        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if(optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            Address address = patient.getAddress();

            return new PatientResponse(
                    patient.getId(),
                    patient.getName(),
                    patient.getSurname(),
                    patient.getNiNumber(),
                    new AddressResponse(
                            address.getStreetName(),
                            address.getStreetNumber(),
                            address.getFloor(),
                            address.getDepartment()
                            )
                    );
        }

        return null;
    }

    @Override
    public void updatePatientByID(Long id, UpdatePatientRequest updatePatientRequest) {
        if (!patientRepository.existsById(id))
            throw new CustomNotFoundException("Patient id [" + id + "] not found");

        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            LOGGER.info("Request to update patient id [" + id + "]");

            Address address = patient.getAddress();


            patient.setName(updatePatientRequest.name());
            patient.setSurname(updatePatientRequest.surname());

            address.setStreetName(updatePatientRequest.address().getStreetName());
            address.setStreetNumber(updatePatientRequest.address().getStreetNumber());
            address.setFloor(updatePatientRequest.address().getFloor());
            address.setDepartment(updatePatientRequest.address().getDepartment());

            address.setPatient(patient);
            patient.setAddress(address);

            patientRepository.save(patient);
            LOGGER.info("Patient updated to [" + patient.getName() + " " +patient.getSurname() + "]");

        }
    }

    @Override
    public void deletePatientByID(Long id) {
        if (!patientRepository.existsById(id))
            throw new CustomNotFoundException("Patient id [" + id + "] not found");

        patientRepository.deleteById(id);
        LOGGER.info("Patient deleted from DB");
    }



    private boolean isNiNumberDuplicated(Integer niNum){
        List<Patient> patients = patientRepository.findAll();
        boolean isDuplicated = false;

        for(Patient patient: patients){
            if (patient.getNiNumber().equals(niNum)) {
                isDuplicated = true;
                break;
            }
        }

        return isDuplicated;
    }
}

package com.dentalcura.webapp.service.impl;


import com.dentalcura.webapp.dto.appointment.AppointmentResponseToDentist;
import com.dentalcura.webapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.webapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.webapp.dto.dentist.DentistResponse;
import com.dentalcura.webapp.dto.patient.PatientResponseToDentist;
import com.dentalcura.webapp.model.Appointment;
import com.dentalcura.webapp.model.Dentist;
import com.dentalcura.webapp.model.Patient;
import com.dentalcura.webapp.repository.IDentistRepository;
import com.dentalcura.webapp.service.IDentistService;
import com.dentalcura.webapp.utils.exceptions.CustomNotFoundException;
import com.dentalcura.webapp.utils.exceptions.DuplicateNiNumberException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter @Setter
@Service
public class DentistService implements IDentistService {

    private final static Logger LOGGER = Logger.getLogger(DentistService.class);

    @Autowired
    private IDentistRepository dentistRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void insertDentist(CreateDentistRequest createDentistRequest) {
        if (isLicenseNumberDuplicated(createDentistRequest.licenseNumber())) {
            throw new DuplicateNiNumberException("License number [" + createDentistRequest.licenseNumber() + "] is already in use.");
        }

        Dentist dentist = mapper.convertValue(createDentistRequest, Dentist.class);
        dentistRepository.save(dentist);
        LOGGER.info("New dentist was registered [" + dentist.getName() + " " + dentist.getSurname() + "]");
    }

    @Override
    public List<DentistResponse> selectAllDentist() {
        List<Dentist> dentists = dentistRepository.findAll();

        List<DentistResponse> dentistResponses = new ArrayList<>();


        for(Dentist dentist: dentists){
            List<AppointmentResponseToDentist> appointmentResponses = new ArrayList<>();
            for(Appointment appointment: dentist.getAppointments()){
                appointmentResponses.add(
                        new AppointmentResponseToDentist(
                                appointment.getId(),
                                appointment.getDate(),
                                new PatientResponseToDentist(
                                        appointment.getPatient().getName(),
                                        appointment.getPatient().getSurname()
                                )
                        )
                );
            }
            dentistResponses.add(
                    new DentistResponse(
                            dentist.getId(),
                            dentist.getName(),
                            dentist.getSurname(),
                            dentist.getLicenseNumber(),
                            appointmentResponses
                            ));

        }
         
        return dentistResponses;
    }

    @Override
    public DentistResponse selectDentistByID(Long id) {
        if (!dentistRepository.existsById(id))
            throw new CustomNotFoundException("Dentist id [" + id + "] not found");

        Optional<Dentist> optionalDentist = dentistRepository.findById(id);


        if(optionalDentist.isPresent()) {
            Dentist dentist = optionalDentist.get();
            List<AppointmentResponseToDentist> appointmentResponses = new ArrayList<>();

            for(Appointment appointment: dentist.getAppointments()){


                appointmentResponses.add(
                        new AppointmentResponseToDentist(
                                appointment.getId(),
                                appointment.getDate(),
                                new PatientResponseToDentist(
                                        appointment.getPatient().getName(),
                                        appointment.getPatient().getSurname()
                                )
                        )
                );
            }

            return new DentistResponse(
                    dentist.getId(),
                    dentist.getName(),
                    dentist.getSurname(),
                    dentist.getLicenseNumber(),
                    appointmentResponses
            );

        }
            return null;
    }

    @Override
    public void updateDentistByID(Long id, UpdateDentistRequest updateDentistRequest) {
        if (!dentistRepository.existsById(id))
            throw new CustomNotFoundException("Dentist id [" + id + "] not found");

        Optional<Dentist> optionalDentist = dentistRepository.findById(id);

        if (optionalDentist.isPresent()) {
            Dentist dentist = optionalDentist.get();
            LOGGER.info("Request to update dentist id [" + id + "]");

            dentist.setName(updateDentistRequest.name());
            dentist.setSurname(updateDentistRequest.surname());

            dentistRepository.save(dentist);
            LOGGER.info("Dentist updated to [" + dentist.getName() + " " +dentist.getSurname() + "]");
        }
    }

    @Override
    public void deleteDentistByID(Long id) {
        if (!dentistRepository.existsById(id))
            throw new CustomNotFoundException("Dentist id [" + id + "] not found");

        dentistRepository.deleteById(id);
        LOGGER.info("Dentist deleted from DB");
    }


    private boolean isLicenseNumberDuplicated(Integer licNum){
        List<Dentist> dentists = dentistRepository.findAll();
        boolean isDuplicated = false;

        for(Dentist dentist: dentists){
            if (dentist.getLicenseNumber().equals(licNum)) {
                isDuplicated = true;
                break;
            }
        }

        return isDuplicated;
    }

}

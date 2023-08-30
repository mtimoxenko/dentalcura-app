package com.dentalcura.webapp.service.impl;


import com.dentalcura.webapp.dto.appointment.AppointmentResponse;
import com.dentalcura.webapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.webapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.webapp.dto.dentist.DentistResponse;
import com.dentalcura.webapp.model.Address;
import com.dentalcura.webapp.model.Appointment;
import com.dentalcura.webapp.model.Dentist;
import com.dentalcura.webapp.model.Patient;
import com.dentalcura.webapp.repository.IAppointmentRepository;
import com.dentalcura.webapp.repository.IDentistRepository;
import com.dentalcura.webapp.repository.IPatientRepository;
import com.dentalcura.webapp.service.IDentistService;
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
public class DentistService implements IDentistService {

    @Autowired
    private IDentistRepository dentistRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void insertDentist(CreateDentistRequest createDentistRequest) {
        Dentist dentist = mapper.convertValue(createDentistRequest, Dentist.class);
        dentistRepository.save(dentist);
    }

    @Override
    public List<DentistResponse> selectAllDentist() {
        List<Dentist> dentists = dentistRepository.findAll();

        List<DentistResponse> dentistResponses = new ArrayList<>();


        for(Dentist dentist: dentists){
            List<AppointmentResponse> appointmentResponses = new ArrayList<>();
            for(Appointment appointment: dentist.getAppointments()){
                appointmentResponses.add(
                        new AppointmentResponse(
                                appointment.getId(),
                                appointment.getDate(),
                                appointment.getPatient().getName() + " " + appointment.getPatient().getSurname()
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
        Optional<Dentist> optionalDentist = dentistRepository.findById(id);


        if(optionalDentist.isPresent()) {
            Dentist dentist = optionalDentist.get();
            List<AppointmentResponse> appointmentResponses = new ArrayList<>();

            for(Appointment appointment: dentist.getAppointments()){


                appointmentResponses.add(
                        new AppointmentResponse(
                                appointment.getId(),
                                appointment.getDate(),
                                appointment.getPatient().getName() + " " + appointment.getPatient().getSurname()
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
        Optional<Dentist> optionalDentist = dentistRepository.findById(id);

        if (optionalDentist.isPresent()) {
            Dentist dentist = optionalDentist.get();

            dentist.setName(updateDentistRequest.name());
            dentist.setSurname(updateDentistRequest.surname());

            dentistRepository.save(dentist);
        }
    }

    @Override
    public void deleteDentistByID(Long id) {
        dentistRepository.deleteById(id);
    }
}

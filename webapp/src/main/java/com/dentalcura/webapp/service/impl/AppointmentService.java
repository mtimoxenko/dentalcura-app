package com.dentalcura.webapp.service.impl;


import com.dentalcura.webapp.dto.appointment.CreateAppointmentRequest;
import com.dentalcura.webapp.dto.appointment.UpdateAppointmentRequest;
import com.dentalcura.webapp.dto.appointment.AppointmentResponse;
import com.dentalcura.webapp.dto.dentist.DentistResponseToAppointment;
import com.dentalcura.webapp.dto.patient.PatientResponse;
import com.dentalcura.webapp.dto.patient.PatientResponseToAppointment;
import com.dentalcura.webapp.model.Appointment;
import com.dentalcura.webapp.model.Dentist;
import com.dentalcura.webapp.model.Patient;
import com.dentalcura.webapp.repository.IAppointmentRepository;
import com.dentalcura.webapp.service.IAppointmentService;
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
public class AppointmentService implements IAppointmentService {

    private final static Logger LOGGER = Logger.getLogger(DentistService.class);

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void insertAppointment(CreateAppointmentRequest createAppointmentRequest) {
        Appointment appointment = mapper.convertValue(createAppointmentRequest, Appointment.class);
        appointmentRepository.save(appointment);
        LOGGER.info("New appointment was registered [" +createAppointmentRequest.date()+ "]");
    }

    @Override
    public List<AppointmentResponse> selectAllAppointment() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();

        for(Appointment appointment: appointments){
            appointmentResponses.add(
                    new AppointmentResponse(
                        appointment.getId(),
                        appointment.getDate(),
                        new PatientResponseToAppointment(
                                appointment.getPatient().getName(),
                                appointment.getPatient().getSurname()
                        ),
                        new DentistResponseToAppointment(
                                appointment.getDentist().getName(),
                                appointment.getDentist().getSurname()
                        )));
        }
         
        return appointmentResponses;
    }

    @Override
    public AppointmentResponse selectAppointmentByID(Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            Patient patient = appointment.getPatient();
            Dentist dentist = appointment.getDentist();

            return new AppointmentResponse(
                    appointment.getId(),
                    appointment.getDate(),
                    new PatientResponseToAppointment(
                            patient.getName(),
                            patient.getSurname()
                    ),
                    new DentistResponseToAppointment(
                            dentist.getName(),
                            dentist.getSurname()
                    )
            );

        }

        return null;
    }

    @Override
    public void updateAppointmentByID(Long id, UpdateAppointmentRequest updateAppointmentRequest) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if(optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            LOGGER.info("Request to update appointment id [" + id + "]");

            appointment.setDate(updateAppointmentRequest.date());
            appointmentRepository.save(appointment);
            LOGGER.info("Appointment updated to [" + appointment.getDate() + "]");
        }

//        Appointment appointment = mapper.convertValue(updateAppointmentRequest, Appointment.class);
//        appointment.setId(id);

    }

    @Override
    public void deleteAppointmentByID(Long id) {
        appointmentRepository.deleteById(id);
        LOGGER.info("Appointment deleted from DB");
    }
}

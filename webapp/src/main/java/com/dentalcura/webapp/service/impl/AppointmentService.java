package com.dentalcura.webapp.service.impl;


import com.dentalcura.webapp.dto.appointment.CreateAppointmentRequest;
import com.dentalcura.webapp.dto.appointment.UpdateAppointmentRequest;
import com.dentalcura.webapp.dto.appointment.AppointmentResponse;
import com.dentalcura.webapp.model.Appointment;
import com.dentalcura.webapp.repository.IAppointmentRepository;
import com.dentalcura.webapp.service.IAppointmentService;
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
public class AppointmentService implements IAppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void insertAppointment(CreateAppointmentRequest createAppointmentRequest) {
        Appointment appointment = mapper.convertValue(createAppointmentRequest, Appointment.class);
        appointmentRepository.save(appointment);
    }

    @Override
    public List<AppointmentResponse> selectAllAppointment() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();

        for(Appointment appointment: appointments){
            appointmentResponses.add(mapper.convertValue(appointment, AppointmentResponse.class));
        }
         
        return appointmentResponses;
    }

    @Override
    public AppointmentResponse selectAppointmentByID(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        AppointmentResponse appointmentResponse = null;

        if(appointment.isPresent())
            appointmentResponse = mapper.convertValue(appointment, AppointmentResponse.class);

        return appointmentResponse;
    }

    @Override
    public void updateAppointmentByID(Long id, UpdateAppointmentRequest updateAppointmentRequest) {
        Appointment appointment = mapper.convertValue(updateAppointmentRequest, Appointment.class);
        appointment.setId(id);
        appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointmentByID(Long id) {
        appointmentRepository.deleteById(id);
    }
}

package com.dentalcura.webapp.service.impl;

import com.dentalcura.webapp.dto.appointment.CreateAppointmentRequest;
import com.dentalcura.webapp.dto.appointment.UpdateAppointmentRequest;
import com.dentalcura.webapp.model.Appointment;
import com.dentalcura.webapp.repository.IAppointmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceTest {

    @Autowired
    IAppointmentRepository appointmentRepository;

    @Autowired
    ObjectMapper mapper;

    AppointmentServiceTest() {
    }

    @Test
    void insertAppointment() {

        CreateAppointmentRequest createAppointmentRequest = new CreateAppointmentRequest(
                "test",
                null,
                null
        );

        Appointment appointment = mapper.convertValue(createAppointmentRequest, Appointment.class);

        appointmentRepository.save(appointment);

        List<Appointment> appointments = appointmentRepository.findAll();

        boolean found = false;

        for (Appointment findAppointment: appointments) {
            if (appointment.equals(findAppointment)) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    void updateAppointmentByID() {
        CreateAppointmentRequest createAppointmentRequest = new CreateAppointmentRequest(
                "test",
                null,
                null
        );

        Appointment appointment = mapper.convertValue(createAppointmentRequest, Appointment.class);
        appointmentRepository.save(appointment);

        UpdateAppointmentRequest updateAppointmentRequest = new UpdateAppointmentRequest("newDate");

        Appointment appointmentUpdate = mapper.convertValue(updateAppointmentRequest, Appointment.class);
        appointmentUpdate.setId(2L);
        appointmentRepository.save(appointmentUpdate);

        Optional<Appointment> optionalAppointment = appointmentRepository.findById(2L);
        Appointment newAppointment = null;

        if(optionalAppointment.isPresent())
            newAppointment = optionalAppointment.get();


        assert newAppointment != null;
        assertEquals(appointmentUpdate.getDate(), newAppointment.getDate());
    }
    

    @Test
    void deleteAppointmentByID() {
        
        CreateAppointmentRequest createAppointmentRequest = new CreateAppointmentRequest(
                "dateToDelete",
                null,
                null
        );

        Appointment appointmentToDelete = mapper.convertValue(createAppointmentRequest, Appointment.class);
        appointmentRepository.save(appointmentToDelete);

        appointmentRepository.deleteById(6L);

        List<Appointment> appointments = appointmentRepository.findAll();

        boolean found = false;

        for (Appointment findAppointment: appointments) {
            if (findAppointment.equals(appointmentToDelete)) {
                System.out.println(findAppointment);
                found = true;
                break;
            }
        }

        assertFalse(found);
        
    }
}
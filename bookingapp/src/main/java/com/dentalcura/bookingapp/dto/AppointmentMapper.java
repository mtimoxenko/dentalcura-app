package com.dentalcura.bookingapp.dto;

import com.dentalcura.bookingapp.dto.appointment.CreateAppointmentRequest;
import com.dentalcura.bookingapp.dto.appointment.AppointmentResponse;
import com.dentalcura.bookingapp.dto.appointment.UpdateAppointmentRequest;
import com.dentalcura.bookingapp.model.Address;
import com.dentalcura.bookingapp.model.Appointment;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class AppointmentMapper {

    // Response DTO for @GetMapping (Retrieving an Appointment)
    public static AppointmentResponse appointmentToDtoResponse(Appointment appointment) {
        return new AppointmentResponse(
                appointment.id(),
                appointment.date(),
                appointment.patient().name() + " " + appointment.patient().surname(),
                appointment.dentist().name() + " " + appointment.dentist().surname()
        );
    }
    // Response DTO for @GetMapping (Retrieving a List<Appointment>)
    public static List<AppointmentResponse> appointmentsToDtoResponse(List<Appointment> appointments) {
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        appointments.forEach(appointment -> appointmentResponses.add(appointmentToDtoResponse(appointment)));

        return appointmentResponses;
    }

    // Request DTO for @PostMapping (Creating an Appointment)
    public static Appointment dtoPostRequestToAppointment(
            CreateAppointmentRequest createAppointmentRequest,
            Patient patient,
            Dentist dentist
    ) {
        return new Appointment(
                createAppointmentRequest.id(),
                createAppointmentRequest.date(),
                patient,
                dentist
        );
    }

    // Request DTO for @PutMapping (Updating an Appointment)
    public static Appointment dtoPutRequestToAppointment(Long id, UpdateAppointmentRequest updateAppointmentRequest) {
        Patient patient = new Patient(1L,"a","a",1,"a",(new Address(1L,"a",1,1,"a")));
        Dentist dentist = new Dentist(1L,"a","a",1);
        return new Appointment(
                id,
                updateAppointmentRequest.date(),
                patient,
                dentist
        );
    }

}


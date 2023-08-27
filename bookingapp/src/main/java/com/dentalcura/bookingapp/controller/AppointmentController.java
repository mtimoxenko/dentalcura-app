package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.dao.impl.AppointmentDAOH2;
import com.dentalcura.bookingapp.dao.impl.PatientDAOH2;
import com.dentalcura.bookingapp.dto.AppointmentMapper;
import com.dentalcura.bookingapp.dto.DentistMapper;
import com.dentalcura.bookingapp.dto.appointment.AppointmentResponse;
import com.dentalcura.bookingapp.dto.appointment.CreateAppointmentRequest;
import com.dentalcura.bookingapp.dto.appointment.UpdateAppointmentRequest;
import com.dentalcura.bookingapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.bookingapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.bookingapp.model.Appointment;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.service.AppointmentService;
import com.dentalcura.bookingapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping()
    public ResponseEntity<List<AppointmentResponse>> getAppointmentAll() {
        appointmentService.setAppointmentIDao(new AppointmentDAOH2());
        return new ResponseEntity<>(
                AppointmentMapper.appointmentsToDtoResponse(appointmentService.selectAllAppointment()),
                HttpStatus.OK
        );
//        return AppointmentMapper.appointmentsToDtoResponse(appointmentService.selectAllAppointment());
//        return appointmentService.selectAllAppointment();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointment(@PathVariable Long id) {
        appointmentService.setAppointmentIDao(new AppointmentDAOH2());
        return new ResponseEntity<>(
                AppointmentMapper.appointmentToDtoResponse(appointmentService.selectAppointmentByID(id)),
                HttpStatus.OK
        );
//        return AppointmentMapper.appointmentToDtoResponse(appointmentService.selectAppointmentByID(id));
//        return appointmentService.selectAppointmentByID(id);
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody CreateAppointmentRequest createAppointmentRequest) {
        appointmentService.setAppointmentIDao(new AppointmentDAOH2());
        return appointmentService.insertAppointment(AppointmentMapper.dtoPostRequestToAppointment(createAppointmentRequest));
//        return appointmentService.insertAppointment(appointment);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@RequestBody UpdateAppointmentRequest updateAppointmentRequest) {
        appointmentService.setAppointmentIDao(new AppointmentDAOH2());
        return appointmentService.updateAppointmentByID(AppointmentMapper.dtoPutRequestToAppointment(updateAppointmentRequest));
//        return appointmentService.updateAppointmentByID(appointment);
    }

    @DeleteMapping("/{id}")
    public Appointment deleteAppointment(@PathVariable Long id) {
        appointmentService.setAppointmentIDao(new AppointmentDAOH2());
        return appointmentService.deleteDAppointmentByID(id);
    }

}

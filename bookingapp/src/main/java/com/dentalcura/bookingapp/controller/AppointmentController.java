package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.dao.impl.AppointmentDAOH2;
import com.dentalcura.bookingapp.dao.impl.PatientDAOH2;
import com.dentalcura.bookingapp.model.Appointment;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.service.AppointmentService;
import com.dentalcura.bookingapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping()
    public List<Appointment> getAppointmentAll() {
        appointmentService.setAppointmentIDao(new AppointmentDAOH2());
        return appointmentService.selectAllAppointment();
    }

    @GetMapping("/{id}")
    public Appointment getAppointment(@PathVariable Long id) {
        appointmentService.setAppointmentIDao(new AppointmentDAOH2());
        return appointmentService.selectAppointmentByID(id);
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        appointmentService.setAppointmentIDao(new AppointmentDAOH2());
        return appointmentService.insertAppointment(appointment);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
        appointmentService.setAppointmentIDao(new AppointmentDAOH2());
        return appointmentService.updateAppointmentByID(appointment);
    }

    @DeleteMapping("/{id}")
    public Appointment deleteAppointment(@PathVariable Long id) {
        appointmentService.setAppointmentIDao(new AppointmentDAOH2());
        return appointmentService.deleteDAppointmentByID(id);
    }

}

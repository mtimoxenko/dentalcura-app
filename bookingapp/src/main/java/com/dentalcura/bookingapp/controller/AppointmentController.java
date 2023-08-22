package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.model.Appointment;
import com.dentalcura.bookingapp.model.Patient;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {



    @GetMapping()
    public String getAppointmentAll() {
        return "get all appointment";
    }

    @GetMapping("/{id}")
    public String getAppointment(@PathVariable Long id) {
        return "get appointment id: " + id;
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointment;
    }

    @PutMapping("/{id}")
    public String updateAppointment(@PathVariable Long id) {
        return "update appointment: " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        return "delete appointment: " + id;
    }

}

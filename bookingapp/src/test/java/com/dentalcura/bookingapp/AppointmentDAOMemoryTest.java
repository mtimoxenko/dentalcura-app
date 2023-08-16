package com.dentalcura.bookingapp;

import com.dentalcura.bookingapp.dao.impl.AppointmentDAOMemory;
import com.dentalcura.bookingapp.model.Appointment;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.service.AppointmentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentDAOMemoryTest {
    @Test
    void insert() {
        Patient patientA = new Patient(3L,"Bender", "Bot", "San Martin", 300, "15/11/2022");
        Patient patientB = new Patient(3L,"Leela", "Up", "B", 10, "X/X/X");

        Dentist dentistA = new Dentist(3L,"Dr","Test",200);
        Dentist dentistB = new Dentist(3L,"Doctor","Fry",100);

        Appointment appointment = new Appointment(1L,"11/22/33", patientA, dentistA);
        Appointment appointment2 = new Appointment(2L,"12/22/33", patientA, dentistA);
        Appointment appointment3 = new Appointment(3L,"12/22/33", patientB, dentistA);
        Appointment appointment4 = new Appointment(4L,"12/22/33", patientB, dentistB);

        Appointment appointment5 = new Appointment(2L,"15/22/33", patientA, dentistB);
        Appointment appointment6 = new Appointment(22L,"15/22/33", patientA, dentistA);

        AppointmentService appointmentService = new AppointmentService();
        appointmentService.setAppointmentIDao(new AppointmentDAOMemory());

        appointmentService.insertAppointment(appointment);
        appointmentService.insertAppointment(appointment2);
        appointmentService.insertAppointment(appointment3);
        appointmentService.insertAppointment(appointment4);
        appointmentService.insertAppointment(appointment5);
        appointmentService.selectAppointmentByID(30L);
        appointmentService.selectAppointmentByID(3L);
        appointmentService.updateAppointmentByID(appointment6);
        appointmentService.updateAppointmentByID(appointment5);
        appointmentService.deleteDAppointmentByID(40L);
        appointmentService.deleteDAppointmentByID(3L);

        assertEquals(3,appointmentService.selectAllAppointment().size());
    }

}
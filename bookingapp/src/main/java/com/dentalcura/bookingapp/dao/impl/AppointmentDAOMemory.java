package com.dentalcura.bookingapp.dao.impl;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Appointment;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.model.Dentist;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


@Slf4j
public class AppointmentDAOMemory implements IDao<Appointment>{
    private final List<Appointment> appointmentsList = new ArrayList<>();


    @Override
    public void createTable() {
    }

    @Override
    public Appointment insert(Appointment appointment) {
        log.info("Insert appointment, running");

        for (Appointment appointment1 : appointmentsList) {
            if (Objects.equals(appointment1.getId(),appointment.getId())) {
                log.info("Appointment not added, appointment id already exists");
                return null;
            }
        }
        appointmentsList.add(appointment);
        log.info("Data saved in memory: " + appointment);
        return appointment;
    }

    @Override
    public List<Appointment> selectAll() {
        log.info("Retrieving appointment data from memory...");
        appointmentsList.forEach(appointment -> log.info(String.valueOf(appointment)));
        return appointmentsList;
    }

    @Override
    public Appointment selectByID(Long id) {
        log.info("Search appointment by id");

        for (Appointment appointment : appointmentsList) {
            if(Objects.equals(id,appointment.getId())){
                log.info("Appointment selected from memory by ID: " + id + ". " + appointment);
                return appointment;
            }
        }
        log.info("Appointment not found");
        return null;
    }

    @Override
    public Appointment updateByID(Appointment appointment) {
        log.info("Update appointment internal memory, running");

        for (Appointment appointment1 : appointmentsList) {
            if(Objects.equals(appointment1.getId(),appointment.getId())){
                appointmentsList.add(appointment);
                appointmentsList.remove(appointment1);
                log.info("Appointment ID: " + appointment1.getId() + ", successfully updated. " + appointment);
                return appointment;
            }
        }
        log.info("Appointment not found, appointment not updated");
        return null;
    }

    @Override
    public Appointment deleteByID(Long id) {
        log.info("Delete appointment internal memory, running");

        for (Appointment appointment : appointmentsList) {
            if(Objects.equals(id,appointment.getId())){
                appointmentsList.remove(appointment);
                log.info("Appointment deleted by ID: " + id + ". " + appointment);
                return appointment;
            }
        }
        log.info("Appointment not found");
        return null;
    }

    @Override
    public String toString() {
        return "internal-Memory";
    }
}

package com.dentalcura.bookingapp.dao.impl;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Dentist;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


@Slf4j
public class DentistDAOMemory implements IDao<Dentist>{
    private final List<Dentist> dentistList = new ArrayList<>();


    @Override
    public void createTable() {
    }

    @Override
    public Dentist insert(Dentist dentist) {
        log.info("Insert dentist, running");

        for (Dentist dentist1 : dentistList) {
            if (Objects.equals(dentist.id(), dentist1.id())){
                log.info("Dentist not added, id already exists");
                return null;
            }
        }
        dentistList.add(dentist);
        log.info("Data saved in memory: " +dentist);
        return dentist;
    }

    @Override
    public List<Dentist> selectAll() {
        log.info("Retrieving dentist data from memory...");
        dentistList.forEach(dentist -> log.info("Dentist: " + dentist));
        return dentistList;
    }

    @Override
    public Dentist selectByID(Long id) {
        log.info("Search dentist by id");

        for (Dentist dentist : dentistList) {
            if(Objects.equals(id, dentist.id())){
                log.info("Dentist selected from memory by ID: " + id + ". " + dentist);
                return dentist;
            }
        }
        log.info("Dentist not found");
        return null;
    }

    @Override
    public Dentist updateByID(Dentist dentist) {
        log.info("Update dentist internal memory, running");

        for (Dentist dentist1 : dentistList) {
            if (Objects.equals(dentist.id(), dentist1.id())){
                dentistList.add(dentist);
                dentistList.remove(dentist1);
                log.info("Dentist ID: " + dentist.id() + ", successfully updated. " + dentist);
                return dentist;
            }
        }
        log.info("Dentist not updated, id not found");
        return null;
    }

    @Override
    public Dentist deleteByID(Long id) {
        log.info("Delete dentist internal memory, running");

        for (Dentist dentist : dentistList) {
            if(Objects.equals(id, dentist.id())){
                dentistList.remove(dentist);
                log.info("Dentist deleted by ID: " + id + ". " + dentist);
                return dentist;
            }
        }
        log.info("Dentist not found, id not found");
        return null;
    }

    @Override
    public String toString() {
        return "internal-Memory";
    }
}

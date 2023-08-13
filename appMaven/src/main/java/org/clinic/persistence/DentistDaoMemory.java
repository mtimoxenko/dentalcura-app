package org.clinic.persistence;

import org.clinic.entities.Dentist;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DentistDaoMemory implements IDao<Dentist>{

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(DentistDaoMemory.class));
    private final List<Dentist> dentistList = new ArrayList<>();


    @Override
    public void createTable() {
    }

    @Override
    public Dentist insert(Dentist dentist) {
        dentistList.add(dentist);
        LOGGER.info("Data saved in memory: " +dentist.getName() + " " + dentist.getSurname() + ", license no.: " + dentist.getLicenseNumber());
        return dentist;
    }

    @Override
    public List<Dentist> selectAll() {
        LOGGER.info("Retrieving data from memory...");
        dentistList.forEach(dentist ->  LOGGER.info(dentist.toString()));
        return dentistList;
    }

    @Override
    public Dentist selectByID(Long id) {
        return null;
    }

    @Override
    public Dentist updateByID(Dentist dentist) {
        return null;
    }

    @Override
    public Dentist deleteByID(Long id) {
        return null;
    }

    @Override
    public String toString() {
        return "In-Memory";
    }
}

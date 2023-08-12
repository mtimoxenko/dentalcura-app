package clinic.persistence;

import clinic.entities.Dentist;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DentistDaoMemory implements IDao<Dentist>{

    private final static Logger LOGGER = Logger.getLogger(DentistDaoMemory.class);
    private final List<Dentist> dentistList = new ArrayList<>();


    @Override
    public void createTable() {
    }

    @Override
    public Dentist insert(Dentist dentist) {
        dentistList.add(dentist);
        LOGGER.info("Data saved in memory: " +dentist.name() + " " + dentist.surname() + ", license no.: " + dentist.licenseNumber());
        return dentist;
    }

    @Override
    public List<Dentist> selectAll() {
        LOGGER.info("Retrieving data from memory...");
        dentistList.forEach(LOGGER::info);
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

package clinic.persistence;

import clinic.entities.Dentist;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DentistDAOInMemory implements IDao<Dentist>{

    private final static Logger LOGGER = Logger.getLogger(DentistDAOInMemory.class);
    private final List<Dentist> dentistList = new ArrayList<>();


    @Override
    public void createTable() {}

    @Override
    public Dentist insert(Dentist dentist) {
        dentistList.add(dentist);
        LOGGER.info("Successfully created In-Memory: " + dentist.name() + " " + dentist.surname());
        return null;
    }

    @Override
    public List<Dentist> selectAll() {
        LOGGER.info("Reading data In-Memory...");
        dentistList.forEach(LOGGER::info);
        return dentistList;
    }

    @Override
    public String toString() {
        return "In-Memory";
    }
}

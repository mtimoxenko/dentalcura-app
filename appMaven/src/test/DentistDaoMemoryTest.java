package test;

import org.clinic.entities.Dentist;
import org.clinic.persistence.DentistDaoMemory;
import org.clinic.service.DentistService;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;


public class DentistDaoMemoryTest {
    private final static Logger LOGGER = Logger.getLogger(String.valueOf(DentistDaoMemoryTest.class));

    @Test
    public void selectAllTest(){

        LOGGER.info("initializing TEST");

        Dentist dentist = new Dentist(6L,"Test","Hack", 1235);
        Dentist dentist2 = new Dentist(11L,"TestList","Hacker", 2323);

        DentistService dentistService = new DentistService();
        dentistService.setDentistIDao(new DentistDaoMemory());

        dentistService.insertDentist(dentist);
        dentistService.insertDentist(dentist2);


        Assert.assertEquals("[Dentist: Test Hack, License no.: 1235, Dentist: TestList Hacker, License no.: 2323]", dentistService.selectAllDentist().toString());
        LOGGER.info("Test OK!");

    }



}
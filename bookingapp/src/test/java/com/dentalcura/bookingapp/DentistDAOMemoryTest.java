package com.dentalcura.bookingapp;

import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.dao.impl.DentistDAOMemory;
import com.dentalcura.bookingapp.service.DentistService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DentistDAOMemoryTest {
    private final static Logger LOGGER = Logger.getLogger(DentistDAOMemoryTest.class);

    @Test
    void selectAllTest(){

        LOGGER.info("initializing TEST");

        Dentist dentist = new Dentist(6L,"Test","Hack", 1235);
        Dentist dentist2 = new Dentist(11L,"TestList","Hacker", 2323);
        Dentist dentist3 = new Dentist(11L,"Rename","Update",1010);

        DentistService dentistService = new DentistService();
        dentistService.setDentistIDao(new DentistDAOMemory());

        dentistService.insertDentist(dentist);
        dentistService.insertDentist(dentist2);


        Assertions.assertNull(dentistService.insertDentist(dentist3));
        Assertions.assertEquals("Rename Update, License no.: 1010", dentistService.updateDentistByID(dentist3).toString());

        Assertions.assertEquals("[Test Hack, License no.: 1235, Rename Update, License no.: 1010]", dentistService.selectAllDentist().toString());
        dentistService.deleteDentistByID(11L);
        dentistService.deleteDentistByID(1L);
        Assertions.assertEquals("[Test Hack, License no.: 1235]", dentistService.selectAllDentist().toString());
        Assertions.assertNull(dentistService.updateDentistByID(dentist2));
        LOGGER.info("Test OK!");

    }
}
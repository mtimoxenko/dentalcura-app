package clinic.testing;

import clinic.entities.Dentist;
import clinic.persistence.DentistDAOInMemory;
import clinic.service.DentistService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DentistDaoEnMemoriaTest {

    private final static Logger LOGGER = Logger.getLogger(DentistDaoEnMemoriaTest.class);

    @Test
    void listarTodos() {
        LOGGER.info("Iniciando TEST");

        Dentist dentist = new Dentist(6L,"Test","Hack", 1235);
        Dentist dentist2 = new Dentist(11L,"TestList","Hacker", 2323);

        DentistService dentistService = new DentistService();
        dentistService.setDentistIDao(new DentistDAOInMemory());

        dentistService.insertDentist(dentist);
        dentistService.insertDentist(dentist2);


        Assertions.assertEquals("[Odontologo: Test Hack, Matricula: 1235, Odontologo: TestList Hacker, Matricula: 2323]", dentistService.selectAllDentist().toString());
        LOGGER.info("Test OK!");
    }
}
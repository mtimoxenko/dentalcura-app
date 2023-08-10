package clinic.testing;

import clinic.entities.Odontologo;
import clinic.persistence.OdontologoDaoEnMemoria;
import clinic.service.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class OdontologoDaoEnMemoriaTest {

    private final static Logger LOGGER = Logger.getLogger(OdontologoDaoEnMemoriaTest.class);

    @Test
    void listarTodos() {
        LOGGER.info("Iniciando TEST");

        Odontologo odontologo = new Odontologo(6L,"Test","Hack", 1235);
        Odontologo odontologo2 = new Odontologo(11L,"TestList","Hacker", 2323);

        OdontologoService odontologoService = new OdontologoService();
        odontologoService.setOdontologoIDao(new OdontologoDaoEnMemoria());

        odontologoService.guardarOdontologo(odontologo);
        odontologoService.guardarOdontologo(odontologo2);


        Assertions.assertEquals("[Odontologo: Test Hack, Matricula: 1235, Odontologo: TestList Hacker, Matricula: 2323]", odontologoService.listarTodosOdontologos().toString());
        LOGGER.info("Test OK!");
    }
}
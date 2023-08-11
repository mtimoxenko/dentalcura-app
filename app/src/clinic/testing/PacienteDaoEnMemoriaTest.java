package clinic.testing;

import clinic.entities.Paciente;
import clinic.persistence.PacienteDAOH2;
import clinic.persistence.PacienteDaoEnMemoria;
import clinic.service.PacienteService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacienteDaoEnMemoriaTest {
    private final static Logger LOGGER = Logger.getLogger(PacienteDaoEnMemoriaTest.class);
    @Test
    void guardar() {
        LOGGER.info("Iniciando TEST guardar paciente");

        Paciente paciente = new Paciente(40L,"Tomas", "Dozo", "Test 2628", 33223344, "01/01/2020");

        PacienteService pacienteService = new PacienteService();
        pacienteService.setPacienteIDao(new PacienteDaoEnMemoria());

        assertEquals(33223344,pacienteService.guardarPaciente(paciente).dni());
        assertEquals("Tomas",pacienteService.guardarPaciente(paciente).nombre());

    }
}
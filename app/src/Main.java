import clinic.entities.Odontologo;
import clinic.entities.Paciente;
import clinic.persistence.PacienteDAOH2;
import clinic.service.PacienteService;
import org.apache.log4j.Logger;
import clinic.persistence.OdontologoDAOH2;
import clinic.persistence.OdontologoDaoEnMemoria;
import clinic.service.OdontologoService;

import java.time.LocalDate;
import java.util.Date;

// Pasar a INGLES -> atributos/metodos - LOGGERS - tablas en la DB
// TESTS
// METODOS devuelven algo?
// BORRAR archivo LOG

public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("Iniciando la secuencia de codigo... ODONTOLOGO");

        Odontologo odontologo = new Odontologo(1L,"Ramiro","Ranalli", 123456);
        Odontologo odontologo2 = new Odontologo(2L,"Javier","Mascherano", 654321);

        OdontologoService odontologoService = new OdontologoService();
        // seteamos una estrategia de persistencia
        odontologoService.setOdontologoIDao(new OdontologoDAOH2());
        LOGGER.info("Estrategia de persistencia: " + odontologoService.getOdontologoIDao());

        // creamos la tabla en la DB
        odontologoService.crearTablaOdontologo();

        // insertamos objetos
        odontologoService.guardarOdontologo(odontologo);
        odontologoService.guardarOdontologo(odontologo2);

        // listamos todos los registros
        odontologoService.listarTodosOdontologos();



        // cambiamos la estrategia de persistencia
        odontologoService.setOdontologoIDao(new OdontologoDaoEnMemoria());
        LOGGER.info("Estrategia de persistencia: " + odontologoService.getOdontologoIDao());

        odontologoService.guardarOdontologo(odontologo);
        odontologoService.guardarOdontologo(odontologo2);
        odontologoService.listarTodosOdontologos();







        





        LOGGER.info("Iniciando la secuencia de codigo... PACIENTE");

        Paciente paciente = new Paciente(1L,"Juan", "Perez", "Cuba 2628", 11223344, "01/01/2020");
        Paciente paciente2 = new Paciente(2L,"Lola", "Rodriguez", "San Martin 1270", 93153234, "15/11/2022");


        PacienteService pacienteService = new PacienteService();
//        // seteamos una estrategia de persistencia
        pacienteService.setPacienteIDao(new PacienteDAOH2());

        LOGGER.info("Estrategia de persistencia: " + pacienteService.getPacienteIDao());
//
//        // creamos la tabla en la DB
        pacienteService.crearTablaPaciente();
//
//        // insertamos objetos
        pacienteService.guardarPaciente(paciente);
        pacienteService.guardarPaciente(paciente2);
//
//        // listamos todos los registros
        pacienteService.listarTodosPacientes();

//        // cambiamos la estrategia de persistencia
//        odontologoService.setOdontologoIDao(new OdontologoDaoEnMemoria());
//        LOGGER.info("Estrategia de persistencia: " + odontologoService.getOdontologoIDao());

//
//        odontologoService.guardarOdontologo(odontologo);
//        odontologoService.guardarOdontologo(odontologo2);
//        odontologoService.listarTodosOdontologos();
//
        LOGGER.info("El programa termino su ejecucion.");

    }

}
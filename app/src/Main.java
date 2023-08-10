import clinic.entities.Odontologo;
import org.apache.log4j.Logger;
import clinic.persistence.OdontologoDAOH2;
import clinic.persistence.OdontologoDaoEnMemoria;
import clinic.service.OdontologoService;


// TESTS
// METODOS devuelven algo?
// BORRAR archivo LOG

public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Iniciando la secuencia de codigo...");

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

        LOGGER.info("El programa termino su ejecucion.");

    }

}
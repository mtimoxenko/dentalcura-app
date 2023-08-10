package clinic.persistence;

import clinic.entities.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoEnMemoria implements IDao<Odontologo>{

    private final static Logger LOGGER = Logger.getLogger(OdontologoDaoEnMemoria.class);
    private final List<Odontologo> odontologoList = new ArrayList<>();


    @Override
    public void crearTablas() {}

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologoList.add(odontologo);
        LOGGER.info("Se agrego el odontologo " + odontologo.apellido() + ", nro. mat: " + odontologo.numeroMatricula() + ", a la lista de almacenamiento en memoria");
        return null;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Leyendo registros en memoria...");
        odontologoList.forEach(LOGGER::info);
        return odontologoList;
    }

    @Override
    public String toString() {
        return "En Memoria";
    }
}

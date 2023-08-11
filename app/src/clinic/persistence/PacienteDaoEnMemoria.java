package clinic.persistence;

import clinic.entities.Paciente;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PacienteDaoEnMemoria implements IDao<Paciente>{

    private final static Logger LOGGER = Logger.getLogger(PacienteDaoEnMemoria.class);
    private final List<Paciente> pacientesList = new ArrayList<>();

    @Override
    public void crearTablas() {
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        pacientesList.add(paciente);
        LOGGER.info("Se agrego el paciente " + paciente.apellido() + ", dni: " + paciente.dni() + ", a la lista de almacenamiento en memoria");
        return paciente;
    }

    @Override
    public List<Paciente> listarTodos() {
        LOGGER.info("Leyendo registro de pacientes en memoria...");
        pacientesList.forEach(LOGGER::info);
        return pacientesList;
    }
}

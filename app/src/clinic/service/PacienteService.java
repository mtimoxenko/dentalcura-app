package clinic.service;

import clinic.entities.Paciente;
import clinic.persistence.IDao;

import java.util.List;

public class PacienteService {
    private IDao<Paciente> pacienteIDao;

    public IDao<Paciente> getPacienteIDao() {
        return pacienteIDao;
    }

    public void setPacienteIDao(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public void crearTablaPaciente(){
        pacienteIDao.crearTablas();
    }

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteIDao.guardar(paciente);
    }

    public List<Paciente> listarTodosPacientes(){
        return pacienteIDao.listarTodos();
    }
}

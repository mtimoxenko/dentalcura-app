package clinic.service;

import clinic.entities.Odontologo;
import clinic.persistence.IDao;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public IDao<Odontologo> getOdontologoIDao() {
        return odontologoIDao;
    }

    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public void crearTablaOdontologo(){
        odontologoIDao.crearTablas();
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> listarTodosOdontologos(){
        return odontologoIDao.listarTodos();
    }
}

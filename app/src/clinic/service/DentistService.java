package clinic.service;

import clinic.entities.Dentist;
import clinic.persistence.IDao;

import java.util.List;

public class DentistService {
    private IDao<Dentist> dentistIDao;

    public IDao<Dentist> getDentistIDao() {
        return dentistIDao;
    }

    public void setDentistIDao(IDao<Dentist> dentistIDao) {
        this.dentistIDao = dentistIDao;
    }

    public void createTableDentist(){
        dentistIDao.createTable();
    }

    public Dentist insertDentist(Dentist dentist){
        return dentistIDao.insert(dentist);
    }

    public List<Dentist> selectAllDentist(){
        return dentistIDao.selectAll();
    }
}

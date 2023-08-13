package org.clinic.service;

import org.clinic.entities.Dentist;
import org.clinic.persistence.IDao;

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

    public Dentist selectDentistByID(Long id){
        return dentistIDao.selectByID(id);
    }

    public Dentist updateDentistByID(Dentist dentist){
        return dentistIDao.updateByID(dentist);
    }

    public Dentist deleteDentistByID(Long id){
        return dentistIDao.deleteByID(id);
    }
}

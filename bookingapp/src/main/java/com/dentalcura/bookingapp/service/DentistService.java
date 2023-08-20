package com.dentalcura.bookingapp.service;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Dentist;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DentistService {
    private IDao<Dentist> dentistIDao;


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

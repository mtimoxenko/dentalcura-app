package com.dentalcura.bookingapp.service;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Dentist;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter @Setter
@Service
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

    public Dentist selectDentistByID(int id){
        return dentistIDao.selectByID(id);
    }

    public Dentist updateDentistByID(Dentist dentist){
        return dentistIDao.updateByID(dentist);
    }

    public Dentist deleteDentistByID(int id){
        return dentistIDao.deleteByID(id);
    }
}

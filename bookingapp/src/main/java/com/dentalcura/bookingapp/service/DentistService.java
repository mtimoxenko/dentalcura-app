package com.dentalcura.bookingapp.service;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter @Setter
@Service
public class DentistService {
    private IDao<Dentist> dentistIDao;


//    @Autowired
//    public DentistService(@Qualifier("dentistDAOH2") IDao<Dentist> dentistIDao) {
//        this.dentistIDao = dentistIDao;
//    }

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

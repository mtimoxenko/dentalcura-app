package com.dentalcura.bookingapp.service;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter @Setter
@Service
public class PatientService {
    private IDao<Patient> patientIDao;

//    @Autowired
//    public PatientService(@Qualifier("patientDAOH2") IDao<Patient> patientIDao) {
//        this.patientIDao = patientIDao;
//    }


    public Patient insertPatient(Patient patient){
        return patientIDao.insert(patient);
    }

    public List<Patient> selectAllPatient(){
        return patientIDao.selectAll();
    }

    public Patient selectPatientByID(Long id){
        return patientIDao.selectByID(id);
    }

    public Patient updatePatientByID(Patient patient){
        return patientIDao.updateByID(patient);
    }

    public Patient deletePatientByID(Long id){
        return patientIDao.deleteByID(id);
    }
}

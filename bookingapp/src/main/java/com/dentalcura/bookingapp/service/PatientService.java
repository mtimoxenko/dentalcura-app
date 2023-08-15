package com.dentalcura.bookingapp.service;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Patient;

import java.util.List;

public class PatientService {
    private IDao<Patient> patientIDao;

    public IDao<Patient> getPatientIDao() {
        return patientIDao;
    }

    public void setPatientIDao(IDao<Patient> patientIDao) {
        this.patientIDao = patientIDao;
    }



    public void createTablePatient(){
        patientIDao.createTable();
    }

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

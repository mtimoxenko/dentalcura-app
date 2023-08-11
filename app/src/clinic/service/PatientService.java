package clinic.service;

import clinic.entities.Patient;
import clinic.persistence.IDao;

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
}

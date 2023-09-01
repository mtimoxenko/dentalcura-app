package clinic.service;

import clinic.entities.Appointment;
import clinic.persistence.IDao;

import java.util.List;

public class AppointmentService {
    private IDao<Appointment> appointmentIDao;

    public IDao<Appointment> getAppointmentIDao() {
        return appointmentIDao;
    }

    public void setAppointmentIDao(IDao<Appointment> appointmentIDao) {
        this.appointmentIDao = appointmentIDao;
    }



    public void createTableAppointment(){
        appointmentIDao.createTable();
    }

    public Appointment insertAppointment(Appointment appointment){
        return appointmentIDao.insert(appointment);
    }

    public List<Appointment> selectAllAppointment(){
        return appointmentIDao.selectAll();
    }

    public Appointment selectAppointmentByID(Long id){
        return appointmentIDao.selectByID(id);
    }

    public Appointment updateAppointmentByID(Appointment appointment){
        return appointmentIDao.updateByID(appointment);
    }

    public Appointment deleteDAppointmentByID(Long id){
        return appointmentIDao.deleteByID(id);
    }
}

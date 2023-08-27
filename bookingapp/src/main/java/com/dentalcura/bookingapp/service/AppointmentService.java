package com.dentalcura.bookingapp.service;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Appointment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
@Getter @Setter
@Service
public class AppointmentService {
    private IDao<Appointment> appointmentIDao;


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

package com.dentalcura.webapp.service;

import com.dentalcura.webapp.dto.appointment.CreateAppointmentRequest;
import com.dentalcura.webapp.dto.appointment.UpdateAppointmentRequest;
import com.dentalcura.webapp.dto.appointment.AppointmentResponse;

import java.util.List;


public interface IAppointmentService {

    void insertAppointment(CreateAppointmentRequest createAppointmentRequest);
    List<AppointmentResponse> selectAllAppointment();
    AppointmentResponse selectAppointmentByID(Long id);
    void updateAppointmentByID(UpdateAppointmentRequest updateAppointmentRequest);
    void deleteAppointmentByID(Long id);

}

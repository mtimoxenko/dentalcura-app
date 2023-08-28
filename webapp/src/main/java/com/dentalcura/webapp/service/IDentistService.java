package com.dentalcura.webapp.service;

import com.dentalcura.webapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.webapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.webapp.dto.dentist.DentistResponse;

import java.util.List;


public interface IDentistService {

    void insertDentist(CreateDentistRequest createDentistRequest);
    List<DentistResponse> selectAllDentist();
    DentistResponse selectDentistByID(Long id);
    void updateDentistByID(Long id, UpdateDentistRequest updateDentistRequest);
    void deleteDentistByID(Long id);

}

package com.dentalcura.webapp.service.impl;


import com.dentalcura.webapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.webapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.webapp.dto.dentist.DentistResponse;
import com.dentalcura.webapp.model.Dentist;
import com.dentalcura.webapp.repository.IDentistRepository;
import com.dentalcura.webapp.service.IDentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter @Setter
@Service
public class DentistService implements IDentistService {

    @Autowired
    private IDentistRepository dentistRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void insertDentist(CreateDentistRequest createDentistRequest) {
        Dentist dentist = mapper.convertValue(createDentistRequest, Dentist.class);
        dentistRepository.save(dentist);
    }

    @Override
    public List<DentistResponse> selectAllDentist() {
        List<Dentist> dentists = dentistRepository.findAll();
        List<DentistResponse> dentistResponses = new ArrayList<>();

        for(Dentist dentist: dentists){
            dentistResponses.add(mapper.convertValue(dentist, DentistResponse.class));
        }
         
        return dentistResponses;
    }

    @Override
    public DentistResponse selectDentistByID(Long id) {
        Optional<Dentist> dentist = dentistRepository.findById(id);
        DentistResponse dentistResponse = null;

        if(dentist.isPresent())
            dentistResponse = mapper.convertValue(dentist, DentistResponse.class);

        return dentistResponse;
    }

    @Override
    public void updateDentistByID(UpdateDentistRequest updateDentistRequest) {
        Dentist dentist = mapper.convertValue(updateDentistRequest, Dentist.class);
        dentistRepository.save(dentist);
    }

    @Override
    public void deleteDentistByID(Long id) {
        dentistRepository.deleteById(id);
    }
}

package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.dao.impl.DentistDAOH2;
import com.dentalcura.bookingapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.bookingapp.dto.dentist.DentistResponse;
import com.dentalcura.bookingapp.dto.DentistMapper;
import com.dentalcura.bookingapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    private DentistService dentistService;
//    private DentistMapper dentistMapper;


    @GetMapping()
    public ResponseEntity<List<DentistResponse>> getDentistAll() {
        dentistService.setDentistIDao(new DentistDAOH2());
        return new ResponseEntity<>(
                DentistMapper.dentistsToDtoResponse(dentistService.selectAllDentist()),
                HttpStatus.OK
        );
//        return DentistMapper.dentistsToDtoResponse(dentistService.selectAllDentist());
//        return dentistService.selectAllDentist();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistResponse> getDentist(@PathVariable Long id) {
        dentistService.setDentistIDao(new DentistDAOH2());
        return new ResponseEntity<>(
                DentistMapper.dentistToDtoResponse(dentistService.selectDentistByID(id)),
                HttpStatus.OK
        );
//        return DentistMapper.dentistToDtoResponse(dentistService.selectDentistByID(id));
//        return dentistService.selectDentistByID(id);
    }

    @PostMapping
    public Dentist createDentist(@RequestBody CreateDentistRequest createDentistRequest) {
        dentistService.setDentistIDao(new DentistDAOH2());
        return dentistService.insertDentist(DentistMapper.dtoPostRequestToDentist(createDentistRequest));
//        return dentistService.insertDentist(dentist);
    }

    @PutMapping("/{id}")
    public Dentist updateDentist(@RequestBody UpdateDentistRequest updateDentistRequest) {
        dentistService.setDentistIDao(new DentistDAOH2());
        return dentistService.updateDentistByID(DentistMapper.dtoPutRequestToDentist(updateDentistRequest));
//        return dentistService.updateDentistByID(dentist);
    }

    @DeleteMapping("/{id}")
    public Dentist deleteDentist(@PathVariable Long id) {
        dentistService.setDentistIDao(new DentistDAOH2());
        return dentistService.deleteDentistByID(id);
    }

}


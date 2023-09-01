package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.dao.impl.DentistDAOH2;
import com.dentalcura.bookingapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.bookingapp.dto.dentist.DentistResponse;
import com.dentalcura.bookingapp.dto.DentistMapper;
import com.dentalcura.bookingapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;

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


        List<DentistResponse> dentistResponses = DentistMapper.dentistsToDtoResponse(dentistService.selectAllDentist());

        return new ResponseEntity<>(dentistResponses, HttpStatus.OK);

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


        DentistResponse dentistResponse = DentistMapper.dentistToDtoResponse(dentistService.selectDentistByID(id));

        return new ResponseEntity<>(dentistResponse, HttpStatus.OK);

//        return DentistMapper.dentistToDtoResponse(dentistService.selectDentistByID(id));
//        return dentistService.selectDentistByID(id);
    }

    @PostMapping
    public ResponseEntity<String> createDentist(@RequestBody CreateDentistRequest createDentistRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("customHeaderName", "customHeaderValue");  // Adding a custom header
        String message = "Dentist created successfully!";

        dentistService.setDentistIDao(new DentistDAOH2());
        dentistService.insertDentist(DentistMapper.dtoPostRequestToDentist(createDentistRequest));
//        return dentistService.insertDentist(dentist);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDentist(@PathVariable Long id, @RequestBody UpdateDentistRequest updateDentistRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("customHeaderName", "customHeaderValue");  // Adding a custom header
        String message = "Dentist updated successfully!";

        dentistService.setDentistIDao(new DentistDAOH2());
        dentistService.updateDentistByID(DentistMapper.dtoPutRequestToDentist(id, updateDentistRequest));
//        return dentistService.updateDentistByID(dentist);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("customHeaderName", "customHeaderValue");  // Adding a custom header
        String message = "Dentist deleted successfully!";

        dentistService.setDentistIDao(new DentistDAOH2());
        dentistService.deleteDentistByID(id);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(message);
    }

}


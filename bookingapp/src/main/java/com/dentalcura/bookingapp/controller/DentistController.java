package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.dao.impl.DentistDAOH2;
import com.dentalcura.bookingapp.dao.impl.PatientDAOH2;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.service.DentistService;
import com.dentalcura.bookingapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    private DentistService dentistService;


    @GetMapping()
    public List<Dentist> getDentistAll() {
        dentistService.setDentistIDao(new DentistDAOH2());
        return dentistService.selectAllDentist();
    }

    @GetMapping("/{id}")
    public Dentist getDentist(@PathVariable Long id) {
        dentistService.setDentistIDao(new DentistDAOH2());
        return dentistService.selectDentistByID(id);
    }

    @PostMapping
    public Dentist createDentist(@RequestBody Dentist dentist) {
        dentistService.setDentistIDao(new DentistDAOH2());
        return dentistService.insertDentist(dentist);
    }

    @PutMapping("/{id}")
    public Dentist updateDentist(@RequestBody Dentist dentist) {
        dentistService.setDentistIDao(new DentistDAOH2());
        return dentistService.updateDentistByID(dentist);
    }

    @DeleteMapping("/{id}")
    public Dentist deleteDentist(@PathVariable Long id) {
        dentistService.setDentistIDao(new DentistDAOH2());
        return dentistService.deleteDentistByID(id);
    }

}

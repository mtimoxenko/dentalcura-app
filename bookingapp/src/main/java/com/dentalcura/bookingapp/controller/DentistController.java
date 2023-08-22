package com.dentalcura.bookingapp.controller;

import com.dentalcura.bookingapp.model.Dentist;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dentist")
public class DentistController {



    @GetMapping()
    public String getDentistAll() {
        return "get all dentists";
    }

    @GetMapping("/{id}")
    public String getDentist(@PathVariable Long id) {
        return "get dentist id: " + id;
    }

    @PostMapping
    public Dentist createDentist(@RequestBody Dentist dentist) {
        return dentist;
    }

    @PutMapping("/{id}")
    public String updateDentist(@PathVariable Long id) {
        return "update dentist: " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteDentist(@PathVariable Long id) {
        return "delete dentist: " + id;
    }

}

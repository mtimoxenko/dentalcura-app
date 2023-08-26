package com.dentalcura.bookingapp.dto;

import com.dentalcura.bookingapp.dto.dentist.CreateDentistRequest;
import com.dentalcura.bookingapp.dto.dentist.DentistResponse;
import com.dentalcura.bookingapp.dto.dentist.UpdateDentistRequest;
import com.dentalcura.bookingapp.model.Dentist;

import java.util.ArrayList;
import java.util.List;

public class DentistMapper {
//    private static final List<DentistResponse> dentistResponses = new ArrayList<>();

    // Response DTO for @GetMapping (Retrieving a Dentist)
    public static DentistResponse dentistToDtoResponse(Dentist dentist) {
        return new DentistResponse(
                dentist.id(),
                dentist.name(),
                dentist.surname(),
                dentist.licenseNumber()
        );
    }
    // Response DTO for @GetMapping (Retrieving a List<Dentist>)
    public static List<DentistResponse> dentistsToDtoResponse(List<Dentist> dentists) {
        List<DentistResponse> dentistResponses = new ArrayList<>();
        dentists.forEach(dentist -> dentistResponses.add(dentistToDtoResponse(dentist)));

        return dentistResponses;
    }

    // Request DTO for @PostMapping (Creating a Dentist)
    public static Dentist dtoPostRequestToDentist(CreateDentistRequest createDentistRequest) {
        return new Dentist(
                createDentistRequest.id(),
                createDentistRequest.name(),
                createDentistRequest.surname(),
                createDentistRequest.licenseNumber()
        );
    }

    // Request DTO for @PutMapping (Updating a Dentist)
    public static Dentist dtoPutRequestToDentist(Long id, UpdateDentistRequest updateDentistRequest) {
        return new Dentist(
                id,
                updateDentistRequest.name(),
                updateDentistRequest.surname(),
                null
        );
    }

}


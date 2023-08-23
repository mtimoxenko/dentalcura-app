package com.dentalcura.bookingapp.mapper;

import com.dentalcura.bookingapp.dto.CreateDentistRequest;
import com.dentalcura.bookingapp.dto.DentistResponse;
import com.dentalcura.bookingapp.dto.UpdateDentistRequest;
import com.dentalcura.bookingapp.model.Dentist;

public class DentistMapper {

    // Response DTO for @GetMapping (Retrieving a Dentist)
    public static DentistResponse dentistToDtoResponse(Dentist dentist) {
        return new DentistResponse(
                dentist.name(),
                dentist.surname(),
                dentist.licenseNumber()
        );
    }

    // Request DTO for @PostMapping (Creating a Dentist)
    public static Dentist dtoPostRequestToDentist(CreateDentistRequest createDentistRequest) {
        return new Dentist(
                null,
                createDentistRequest.name(),
                createDentistRequest.surname(),
                createDentistRequest.licenseNumber()
        );
    }

    // Request DTO for @PutMapping (Updating a Dentist)
    public static Dentist dtoPutRequestToDentist(UpdateDentistRequest updateDentistRequest) {
        return new Dentist(
                null,
                updateDentistRequest.name(),
                updateDentistRequest.surname(),
                null
        );
    }

}

package com.dentalcura.webapp.service;

import com.dentalcura.webapp.dto.address.CreateAddressRequest;
import com.dentalcura.webapp.dto.address.UpdateAddressRequest;
import com.dentalcura.webapp.dto.address.AddressResponse;

import java.util.List;


public interface IAddressService {

    void insertAddress(CreateAddressRequest createAddressRequest);
    List<AddressResponse> selectAllAddress();
    AddressResponse selectAddressByID(Long id);
    void updateAddressByID(Long id, UpdateAddressRequest updateAddressRequest);
    void deleteAddressByID(Long id);

}

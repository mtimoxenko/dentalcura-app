package com.dentalcura.webapp.service.impl;


import com.dentalcura.webapp.dto.address.CreateAddressRequest;
import com.dentalcura.webapp.dto.address.UpdateAddressRequest;
import com.dentalcura.webapp.dto.address.AddressResponse;
import com.dentalcura.webapp.model.Address;
import com.dentalcura.webapp.repository.IAddressRepository;
import com.dentalcura.webapp.service.IAddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Getter @Setter
@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void insertAddress(CreateAddressRequest createAddressRequest) {
        Address address = mapper.convertValue(createAddressRequest, Address.class);
        addressRepository.save(address);
    }

    @Override
    public List<AddressResponse> selectAllAddress() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressResponse> addressResponses = new ArrayList<>();

        for(Address address: addresses){
            addressResponses.add(mapper.convertValue(address, AddressResponse.class));
        }
         
        return addressResponses;
    }

    @Override
    public AddressResponse selectAddressByID(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        AddressResponse addressResponse = null;

        if(address.isPresent())
            addressResponse = mapper.convertValue(address, AddressResponse.class);

        return addressResponse;
    }

    @Override
    public void updateAddressByID(Long id, UpdateAddressRequest updateAddressRequest) {
        Address address = mapper.convertValue(updateAddressRequest, Address.class);
        address.setId(id);
        addressRepository.save(address);
    }

    @Override
    public void deleteAddressByID(Long id) {
        addressRepository.deleteById(id);
    }
}

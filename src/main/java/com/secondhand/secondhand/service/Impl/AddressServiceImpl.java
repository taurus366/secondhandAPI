package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.entity.AddressEntity;
import com.secondhand.secondhand.repository.AddressRepository;
import com.secondhand.secondhand.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public List<AddressEntity> getAddressesByUserId(Long userId) {

//        return this.addressRepository
//                .findAllByUserId(userId);
        return null;
    }
}

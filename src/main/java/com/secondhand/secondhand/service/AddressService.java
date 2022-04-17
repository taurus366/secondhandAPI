package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.entity.AddressEntity;

import java.util.List;

public interface AddressService {

    List<AddressEntity> getAddressesByUserId(Long userId);
}

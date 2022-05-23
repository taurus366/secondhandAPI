package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.CityDTO;
import com.secondhand.secondhand.model.dto.SpeedyAddressFormDTO;
import com.secondhand.secondhand.model.entity.SpeedyCityEntity;
import com.secondhand.secondhand.model.service.JsonSpeedyCityServiceModel;

import java.util.List;

public interface SpeedyCityService {

    List<SpeedyCityEntity> getAllCities();
    void postAllCities(List<JsonSpeedyCityServiceModel> jsonSpeedyCityServiceModels);
    List<CityDTO> getCityByStringLike(String city);
    List<SpeedyAddressFormDTO> getSpeedyAddressesByCityId(Long cityId);
    SpeedyCityEntity getCityByIdTogetherAddresses(Long id);
}

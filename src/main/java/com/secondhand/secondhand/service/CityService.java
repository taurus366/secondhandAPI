package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.CityDTO;
import com.secondhand.secondhand.model.entity.CityEntity;
import com.secondhand.secondhand.model.service.JsonCityVillageServiceModel;

import java.util.List;

public interface CityService {

    List<CityDTO> getAllCitiesVillages();
    void postCitiesVillages(List<JsonCityVillageServiceModel> serviceModel);
    List<CityDTO> getCityByStringLike(String wordLike);
}

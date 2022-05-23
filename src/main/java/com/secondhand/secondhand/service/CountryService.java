package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.entity.CountryEntity;
import com.secondhand.secondhand.model.service.JsonCountryServiceModel;

import java.util.List;

public interface CountryService {

    List<CountryEntity> getAllCountries();

    void postCountries(List<JsonCountryServiceModel> countries);
}

package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.entity.CountryEntity;
import com.secondhand.secondhand.model.service.JsonCountryServiceModel;
import com.secondhand.secondhand.repository.CountryRepository;
import com.secondhand.secondhand.service.CountryService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryEntity> getAllCountries() {
        return this.countryRepository
                .findAll();
    }

    @Override
    public void postCountries(List<JsonCountryServiceModel> countries) {

        this.countryRepository
                .saveAll(
                        countries
                                .stream()
                                .map(this::AsCountryEntity)
                                .collect(Collectors.toList())
                );
    }

    private CountryEntity AsCountryEntity(JsonCountryServiceModel serviceModel) {
        System.out.println(serviceModel.getName());
        CountryEntity countryEntity = new CountryEntity();
        countryEntity
                .setName(serviceModel.getName())
                .setCode(serviceModel.getCode())
                .setCreated(Instant.now())
                .setModified(Instant.now());

        return countryEntity;

    }
}

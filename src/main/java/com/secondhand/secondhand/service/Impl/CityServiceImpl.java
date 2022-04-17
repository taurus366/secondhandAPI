package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.dto.CityDTO;
import com.secondhand.secondhand.model.entity.CityEntity;
import com.secondhand.secondhand.model.service.JsonCityVillageServiceModel;
import com.secondhand.secondhand.repository.CityRepository;
import com.secondhand.secondhand.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CityDTO> getAllCitiesVillages() {
        return this.cityRepository.findAll()
                .stream()
                .map(this::asCityDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void postCitiesVillages(List<JsonCityVillageServiceModel> serviceModel) {

        this.cityRepository
                .saveAll(
                        serviceModel
                                .stream()
                                .map(this::asCityEntity)
                                .collect(Collectors.toList())
                );

    }

    @Override
    public List<CityDTO> getCityByStringLike(String wordLike) {

        return this.cityRepository
                .findAllByCitiesBtStringLike(wordLike)
                .stream()
                .map(this::asCityDTO)
                .collect(Collectors.toList());
    }

//    FROM SERVICE MODEL TO --> CityEntity
    private CityEntity asCityEntity(JsonCityVillageServiceModel serviceModel) {
        CityEntity map = modelMapper.map(serviceModel, CityEntity.class);
        map
                .setCreated(Instant.now())
                .setModified(Instant.now());

        return map;
    }

//    FROM CityEntity TO --> CityDTO
    private CityDTO asCityDTO(CityEntity cityEntity) {

        return modelMapper
                .map(cityEntity,CityDTO.class);
    }
}

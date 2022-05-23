package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.entity.RegionEntity;
import com.secondhand.secondhand.model.service.JsonCountryServiceModel;
import com.secondhand.secondhand.model.service.JsonRegionServiceModel;
import com.secondhand.secondhand.repository.RegionRepository;
import com.secondhand.secondhand.service.RegionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<RegionEntity> getAllRegions() {
        return this.regionRepository
                .findAll();
    }

    @Override
    public void postRegions(List<JsonRegionServiceModel> regionServiceModels) {

        this.regionRepository
                .saveAll(regionServiceModels
                        .stream()
                        .map(this::asRegionEntity)
                        .collect(Collectors.toList()));
    }

    private RegionEntity asRegionEntity(JsonRegionServiceModel serviceModel){

        RegionEntity region = new RegionEntity();
        region
                .setName(serviceModel.getName())
                .setCreated(Instant.now())
                .setModified(Instant.now());

        return region;
    }
}

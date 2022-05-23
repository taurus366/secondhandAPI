package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.entity.RegionEntity;
import com.secondhand.secondhand.model.service.JsonRegionServiceModel;

import java.util.List;

public interface RegionService {


    List<RegionEntity> getAllRegions();
    void postRegions(List<JsonRegionServiceModel> regionServiceModels);
}

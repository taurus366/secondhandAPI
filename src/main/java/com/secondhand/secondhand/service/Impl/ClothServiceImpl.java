package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.entity.ClothBrand;
import com.secondhand.secondhand.model.entity.ClothCompositionEntity;
import com.secondhand.secondhand.model.entity.ClothEntity;
import com.secondhand.secondhand.model.entity.ClothTypeEntity;
import com.secondhand.secondhand.model.entity.enums.ClothColorEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSeasonEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSexEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSizeEnum;
import com.secondhand.secondhand.model.service.ClothCreateServiceModel;
import com.secondhand.secondhand.repository.ClothBrandRepository;
import com.secondhand.secondhand.repository.ClothCompositionRepository;
import com.secondhand.secondhand.repository.ClothRepository;
import com.secondhand.secondhand.repository.ClothTypeRepository;
import com.secondhand.secondhand.service.ClothService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ClothServiceImpl implements ClothService {

    private final ClothRepository clothRepository;
    private final ClothBrandRepository clothBrandRepository;
    private final ClothTypeRepository clothTypeRepository;
    private final ClothCompositionRepository clothCompositionRepository;
    private final ModelMapper modelMapper;

    public ClothServiceImpl(ClothRepository clothRepository, ClothBrandRepository clothBrandRepository, ClothTypeRepository clothTypeRepository, ClothCompositionRepository clothCompositionRepository, ModelMapper modelMapper) {
        this.clothRepository = clothRepository;
        this.clothBrandRepository = clothBrandRepository;
        this.clothTypeRepository = clothTypeRepository;
        this.clothCompositionRepository = clothCompositionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ClothCreateServiceModel createNewCloth(ClothCreateServiceModel clothServiceModel) {

        ClothBrand brandNameFromRepository = clothBrandRepository.findByName(clothServiceModel.getBrand());
        ClothTypeEntity typeNameFromRepository = clothTypeRepository.findByName(clothServiceModel.getType());
        ClothCompositionEntity compositionNameFromRepository = clothCompositionRepository.findByName(clothServiceModel.getComposition());

        ClothEntity newCloth = new ClothEntity();
        newCloth
                .setClothBrand(brandNameFromRepository)
                .setClothType(typeNameFromRepository)
                .setClothComposition(compositionNameFromRepository)
                .setClothColor(ClothColorEnum.valueOf(clothServiceModel.getColor().name()))
                .setClothSex(ClothSexEnum.valueOf(clothServiceModel.getSex().name()))
                .setClothSeason(ClothSeasonEnum.valueOf(clothServiceModel.getSeason().name()))
                .setClothSize(ClothSizeEnum.valueOf(clothServiceModel.getSize().name()))
                .setDescription(clothServiceModel.getDescription())
                .setLikes(0)
                .setStartPrice(clothServiceModel.getStartPrice())
                .setNewPrice(clothServiceModel.getNewPrice())
                .setCreated(Instant.now())
                .setModified(Instant.now());

        ClothEntity savedClothEntity = clothRepository.save(newCloth);

        return modelMapper.map(savedClothEntity, ClothCreateServiceModel.class);
    }
}

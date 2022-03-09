package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.entity.*;
import com.secondhand.secondhand.model.entity.enums.ClothColorEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSeasonEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSexEnum;
import com.secondhand.secondhand.model.entity.enums.ClothSizeEnum;
import com.secondhand.secondhand.model.service.ClothCreateServiceModel;
import com.secondhand.secondhand.repository.*;
import com.secondhand.secondhand.service.ClothService;
import com.secondhand.secondhand.service.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;

@Service
public class ClothServiceImpl implements ClothService {

    private final ClothRepository clothRepository;
    private final ClothBrandRepository clothBrandRepository;
    private final ClothTypeRepository clothTypeRepository;
    private final ClothCompositionRepository clothCompositionRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;

    public ClothServiceImpl(ClothRepository clothRepository, ClothBrandRepository clothBrandRepository, ClothTypeRepository clothTypeRepository, ClothCompositionRepository clothCompositionRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService, PictureRepository pictureRepository) {
        this.clothRepository = clothRepository;
        this.clothBrandRepository = clothBrandRepository;
        this.clothTypeRepository = clothTypeRepository;
        this.clothCompositionRepository = clothCompositionRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
    }


    @Override
    public ClothCreateServiceModel createNewCloth(ClothCreateServiceModel clothServiceModel) throws IOException {

        ClothBrandEntity brandNameFromRepository = clothBrandRepository.findByName(clothServiceModel.getBrand());
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

        PictureEntity uploadedCoverPicture = createPictureEntity(clothServiceModel.getCoverPicture());
        uploadedCoverPicture
                .setCreated(Instant.now())
                .setModified(Instant.now());

        PictureEntity uploadedFrontPicture = createPictureEntity(clothServiceModel.getFrontPicture());
        uploadedFrontPicture
                .setCreated(Instant.now())
                .setModified(Instant.now());

        PictureEntity uploadedThirdPicture = createPictureEntity(clothServiceModel.getThirdPicture());
        uploadedThirdPicture
                .setCreated(Instant.now())
                .setModified(Instant.now());

        PictureEntity uploadedFourthPicture = createPictureEntity(clothServiceModel.getFourthPicture());
        uploadedFourthPicture
                .setCreated(Instant.now())
                .setModified(Instant.now());

        newCloth.getSidePictures().add(uploadedThirdPicture);
        newCloth.getSidePictures().add(uploadedFourthPicture);


        newCloth
                .setCoverPicture(uploadedCoverPicture)
                .setFrontPicture(uploadedFrontPicture);


        ClothEntity savedClothEntity = clothRepository.save(newCloth);

        return modelMapper.map(savedClothEntity, ClothCreateServiceModel.class);
    }


    private PictureEntity createPictureEntity(MultipartFile picture) throws IOException {
        CloudinaryImage uploadedImg = cloudinaryService.upload(picture);


        return new PictureEntity()
                .setPublicId(uploadedImg.getPublicId())
                .setUrl(uploadedImg.getUrl());
    }
}

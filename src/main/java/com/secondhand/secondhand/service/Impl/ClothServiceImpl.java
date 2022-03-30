package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.common.MapperFunction;
import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.model.entity.*;
import com.secondhand.secondhand.model.entity.enums.*;
import com.secondhand.secondhand.model.service.ClothCreateServiceModel;
import com.secondhand.secondhand.repository.*;
import com.secondhand.secondhand.service.ClothService;
import com.secondhand.secondhand.service.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
public class ClothServiceImpl implements ClothService {

    private final ClothRepository clothRepository;
    private final ClothBrandRepository clothBrandRepository;
    private final ClothTypeRepository clothTypeRepository;
    private final ClothCompositionRepository clothCompositionRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;
    private final GuestTokenRepository guestTokenRepository;
    private final MapperFunction toClothDTO;

    public ClothServiceImpl(ClothRepository clothRepository, ClothBrandRepository clothBrandRepository, ClothTypeRepository clothTypeRepository, ClothCompositionRepository clothCompositionRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService, PictureRepository pictureRepository, GuestTokenRepository guestTokenRepository, MapperFunction toClothDTO) {
        this.clothRepository = clothRepository;
        this.clothBrandRepository = clothBrandRepository;
        this.clothTypeRepository = clothTypeRepository;
        this.clothCompositionRepository = clothCompositionRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
        this.guestTokenRepository = guestTokenRepository;
        this.toClothDTO = toClothDTO;
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
                .setQuantity(1)
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


    @Override
    public Page<ClothDTO> getAllClothes(int pageNo, int pageSize, String brand, String size, Long discount, String color, Long priceLow, Long priceHigh,String sex, List<String> type, String itemType, String sortBy) {

        Sort sortPrice = sortBy.equals("desc") ? Sort.by("newPrice").descending() : Sort.by("newPrice").ascending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sortPrice);

        String checkBrand = brand.equals("null") ? null : brand;

        String checkSize = size.equals("null") ? null : size;
        ClothSizeEnum sizeEnum = checkSize == null ? null : checkIfExists(size.toUpperCase(),"size") ? ClothSizeEnum.valueOf(size.toUpperCase()) : null;

        Long checkDiscount = discount == -1 ? null : discount;
        Long checkDiscountStart = null;
        if (checkDiscount != null){
             checkDiscountStart = checkDiscount == 30 ? 0L : checkDiscount == 60 ? 30L : checkDiscount == 100 ? 60L : null;
        }

        String checkColor = color.equals("null") ? null : color;
        ClothColorEnum colorEnum = checkColor == null ? null : checkIfExists(color.toUpperCase(),"color") ? ClothColorEnum.valueOf(color.toUpperCase()) : null;

       Long checkPriceLow = priceLow == -1 ? null : priceLow;
       Long checkPriceHigh = priceHigh == -1 ? null : priceHigh;

       String checkSex = sex.equals("null") ? null : sex;
       ClothSexEnum sexEnum = checkSex == null ? null : checkIfExists(sex.toUpperCase(),"sex") ? ClothSexEnum.valueOf(sex.toUpperCase()) : null;

       List<String> checkType = type.get(0).equals("null") ? null : type;

       ItemTypeEnum checkItemType = itemType.equals("null") ? null : checkIfExists(itemType.toUpperCase(),"itemType") ? ItemTypeEnum.valueOf(itemType.toUpperCase()) : null;

        if (sexEnum != null && sexEnum.equals(ClothSexEnum.CHILDREN)) {
            return clothRepository
                    .getAllClothesByList(List.of(ClothSexEnum.BOYS, ClothSexEnum.GIRLS), checkBrand, sizeEnum, colorEnum, checkDiscount, checkDiscountStart, checkItemType, pageable)
                    .map(this::asClothDTO);

        }

        return clothRepository
                .getAllClothesByParamsOr(checkBrand,
                        sizeEnum,checkDiscount, checkDiscountStart,colorEnum,checkPriceLow,checkPriceHigh, sexEnum, checkType, checkItemType, pageable)
                .map(this::asClothDTO);
    }

    @Override
    public ClothDTO getClothByID(Long id) {
        ClothEntity clothById = this.clothRepository.findById(id).orElseThrow();

        return asClothDTO(clothById);
    }

//    @Override
//    public List<ClothDTO> getUserCart(Long userId) {
//
//        return this.clothRepository
//                .findAllByUserId(userId)
//                .stream()
//                .map(this::asClothDTO)
//                .collect(Collectors.toList());
//    }

//    @Override
//    public List<ClothDTO> getGuestCart(String cookie) {
//
//        GuestTokenEntity guest = this.guestTokenRepository
//                .findByToken(cookie);
//
//
//        return this.clothRepository
//                .findAllByGuestId(guest.getId())
//                .stream()
//                .map(this::asClothDTO)
//                .collect(Collectors.toList());
//    }

//    @Override
//    public void changeGuestClothToUserCloth(HttpServletRequest request, HttpServletResponse response,Long userId) {
//
//        Cookie[] cookies = request.getCookies();
//        String cookieValue = "";
//        if (cookies != null && cookies.length > 0){
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("GSESSIONID") && cookie.getValue().length() > 0){
//                    cookieValue = cookie.getValue();
//                    break;
//                }
//            }
//        }
//
//        if (cookieValue.length() > 0) {
//            GuestTokenEntity guest = this.guestTokenRepository.findByToken(cookieValue);
////            IS EXISTS IN GUEST DB
//            if (guest != null){
//
//                List<ClothEntity> allByGuestId = clothRepository.findAllByGuestId(guest.getId());
//
////                IF GUEST HAS CLOTHES INTO CART
//                if (allByGuestId.size() > 0) {
////                    TODO
//
//
//                    List<ClothEntity> allByUserId = this.clothRepository
//                            .findAllByUserId(userId);
//
//                    allByUserId
//                    .addAll(allByGuestId);
//                    clothRepository.saveAll(allByUserId);
//
//
//                }
//
//
//            }
//        }
//
//
//    }

    private boolean checkIfExists(String string,String type) {

        try {
            switch (type) {
                case "size" -> ClothSizeEnum.valueOf(string);
                case "color" -> ClothColorEnum.valueOf(string);
                case "sex" -> ClothSexEnum.valueOf(string);
                case "itemType" -> ItemTypeEnum.valueOf(string);
            }
        }catch (Exception e){
            return false;
        }

        return true;
    }

    private ClothDTO asClothDTO(ClothEntity clothEntity) {

        return toClothDTO.asClothDTO(clothEntity);
    }

    private PictureEntity createPictureEntity(MultipartFile picture) throws IOException {
        CloudinaryImage uploadedImg = cloudinaryService.upload(picture);


        return new PictureEntity()
                .setPublicId(uploadedImg.getPublicId())
                .setUrl(uploadedImg.getUrl());
    }
}

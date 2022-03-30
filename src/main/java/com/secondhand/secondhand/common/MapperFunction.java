package com.secondhand.secondhand.common;

import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.model.dto.PictureDTO;
import com.secondhand.secondhand.model.entity.ClothEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperFunction {


    public ClothDTO asClothDTO(ClothEntity clothEntity,String ...arg){
        PictureDTO coverPicture = new PictureDTO();
        coverPicture.setUrl(clothEntity.getCoverPicture().getUrl())
                .setPublicId(clothEntity.getCoverPicture().getPublicId());

        PictureDTO frontPicture = new PictureDTO();
        frontPicture.setUrl(clothEntity.getFrontPicture().getUrl())
                .setPublicId(clothEntity.getFrontPicture().getPublicId());

        List<PictureDTO> sidePictures = null;

        if (arg.length == 0){

           sidePictures = clothEntity.getSidePictures()
                    .stream()
                    .map(pictureEntity -> {
                        return new PictureDTO()
                                .setUrl(pictureEntity.getUrl())
                                .setPublicId(pictureEntity.getPublicId());
                    })
                    .collect(Collectors.toList());
        }




        ClothDTO clothDTO = new ClothDTO();
        clothDTO
                .setId(clothEntity.getId())
                .setType(clothEntity.getClothType().getName())
                .setBrand(clothEntity.getClothBrandEntity().getName())
                .setSize(clothEntity.getClothSize().name())
                .setSex(clothEntity.getClothSex().name())
                .setColor(clothEntity.getClothColor().name())
                .setSeason(clothEntity.getClothSeason().name())
                .setComposition(clothEntity.getClothComposition().getName())
                .setDescription(clothEntity.getDescription())
                .setStartPrice(clothEntity.getStartPrice())
                .setNewPrice(clothEntity.getNewPrice())
                .setLikes(clothEntity.getLikes())
                .setSidePictures(sidePictures)
                .setCoverPicture(coverPicture)
                .setFrontPicture(frontPicture)
                .setQuantity(clothEntity.getQuantity());

        return clothDTO;
    }
}
